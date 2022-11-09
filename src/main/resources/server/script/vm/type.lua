---@class vm
local vm        = require 'vm.vm'
local guide     = require 'parser.guide'
local config    = require 'config.config'
local util      = require 'utility'
local lang      = require 'language'

---@alias typecheck.err vm.node.object|string|vm.node

---@param object vm.node.object
---@return string?
local function getNodeName(object)
    if object.type == 'global' and object.cate == 'type' then
        ---@cast object vm.global
        return object.name
    end
    if object.type == 'nil'
    or object.type == 'boolean'
    or object.type == 'number'
    or object.type == 'string'
    or object.type == 'table'
    or object.type == 'function'
    or object.type == 'integer' then
        return object.type
    end
    if object.type == 'doc.type.boolean' then
        return 'boolean'
    end
    if object.type == 'doc.type.integer' then
        return 'integer'
    end
    if object.type == 'doc.type.function' then
        return 'function'
    end
    if object.type == 'doc.type.table' then
        return 'table'
    end
    if object.type == 'doc.type.array' then
        return 'table'
    end
    if object.type == 'doc.type.string' then
        return 'string'
    end
    if object.type == 'doc.field.name' then
        return 'string'
    end
    return nil
end

---@param parentName string
---@param child      vm.node.object
---@param uri        uri
---@param err        typecheck.err[]
---@return boolean?
local function checkEnum(parentName, child, uri, err)
    local parentClass = vm.getGlobal('type', parentName)
    if not parentClass then
        return nil
    end
    local enums
    for _, set in ipairs(parentClass:getSets(uri)) do
        if set.type == 'doc.enum' then
            enums = vm.getEnums(set)
            break
        end
    end
    if not enums then
        return nil
    end
    if child.type == 'global' then
        ---@cast child vm.global
        for _, enum in ipairs(enums) do
            if vm.isSubType(uri, child, vm.compileNode(enum)) then
                return true
            end
        end
        err[#err+1] = 'TYPE_ERROR_ENUM_GLOBAL_DISMATCH'
        err[#err+1] = child
        err[#err+1] = parentClass
        return false
    elseif child.type == 'generic' then
        ---@cast child vm.generic
        err[#err+1] = 'TYPE_ERROR_ENUM_GENERIC_UNSUPPORTED'
        err[#err+1] = child
        return false
    else
        ---@cast child parser.object
        local childName = getNodeName(child)
        if childName == 'number'
        or childName == 'integer'
        or childName == 'boolean'
        or childName == 'string' then
            for _, enum in ipairs(enums) do
                for nd in vm.compileNode(enum):eachObject() do
                    if childName == getNodeName(nd) and nd[1] == child[1] then
                        return true
                    end
                end
            end
            err[#err+1] = 'TYPE_ERROR_ENUM_LITERAL_DISMATCH'
            err[#err+1] = child[1]
            err[#err+1] = parentClass
            return false
        elseif childName == 'function'
        or     childName == 'table' then
            for _, enum in ipairs(enums) do
                for nd in vm.compileNode(enum):eachObject() do
                    if child == nd then
                        return true
                    end
                end
            end
            err[#err+1] = 'TYPE_ERROR_ENUM_OBJECT_DISMATCH'
            err[#err+1] = child
            err[#err+1] = parentClass
            return false
        end
        err[#err+1] = 'TYPE_ERROR_ENUM_NO_OBJECT'
        err[#err+1] = child
        return false
    end
end

---@param parent vm.node.object
---@param child  vm.node.object
---@param mark   table
---@param err    typecheck.err[]
---@return boolean
local function checkValue(parent, child, mark, err)
    if parent.type == 'doc.type.integer' then
        if child.type == 'integer'
        or child.type == 'doc.type.integer'
        or child.type == 'number' then
            if parent[1] ~= child[1] then
                err[#err+1] = 'TYPE_ERROR_INTEGER_DISMATCH'
                err[#err+1] = child[1]
                err[#err+1] = parent[1]
                return false
            end
        end
        return true
    end

    if parent.type == 'doc.type.string'
    or parent.type == 'doc.field.name' then
        if child.type == 'string'
        or child.type == 'doc.type.string'
        or child.type == 'doc.field.name' then
            if parent[1] ~= child[1] then
                err[#err+1] = 'TYPE_ERROR_STRING_DISMATCH'
                err[#err+1] = child[1]
                err[#err+1] = parent[1]
                return false
            end
        end
        return true
    end

    if parent.type == 'doc.type.boolean' then
        if child.type == 'boolean'
        or child.type == 'doc.type.boolean' then
            if parent[1] ~= child[1] then
                err[#err+1] = 'TYPE_ERROR_BOOLEAN_DISMATCH'
                err[#err+1] = child[1]
                err[#err+1] = parent[1]
                return false
            end
        end
        return true
    end

    if parent.type == 'doc.type.table' then
        if child.type == 'doc.type.table' then
            ---@cast parent parser.object
            ---@cast child parser.object
            local uri = guide.getUri(parent)
            local tnode = vm.compileNode(child)
            for _, pfield in ipairs(parent.fields) do
                local knode = vm.compileNode(pfield.name)
                local cvalues = vm.getTableValue(uri, tnode, knode, true)
                if not cvalues then
                    err[#err+1] = 'TYPE_ERROR_TABLE_NO_FIELD'
                    err[#err+1] = pfield.name
                    return false
                end
                local pvalues = vm.compileNode(pfield.extends)
                if vm.isSubType(uri, cvalues, pvalues, mark, err) == false then
                    err[#err+1] = 'TYPE_ERROR_TABLE_FIELD_DISMATCH'
                    err[#err+1] = pfield.name
                    err[#err+1] = cvalues
                    err[#err+1] = pvalues
                    return false
                end
            end
        end
        return true
    end

    return true
end

---@param name string
---@param suri uri
---@return boolean
local function isAlias(name, suri)
    local global = vm.getGlobal('type', name)
    if not global then
        return false
    end
    for _, set in ipairs(global:getSets(suri)) do
        if set.type == 'doc.alias' then
            return true
        end
    end
    return false
end

---@param uri uri
---@param child  vm.node|string|vm.node.object
---@param parent vm.node|string|vm.node.object
---@param mark?  table
---@param err? typecheck.err[]
---@return boolean|nil
---@return typecheck.err[] # errors
function vm.isSubType(uri, child, parent, mark, err)
    mark = mark or {}
    err  = err  or {}

    if type(child) == 'string' then
        local global = vm.getGlobal('type', child)
        if not global then
            return nil, err
        end
        child = global
    elseif child.type == 'vm.node' then
        if config.get(uri, 'Lua.type.weakUnionCheck') then
            local hasKnownType = 0
            for n in child:eachObject() do
                if getNodeName(n) then
                    hasKnownType = hasKnownType + 1
                    if vm.isSubType(uri, n, parent, mark, err) == true then
                        return true, err
                    end
                end
            end
            if hasKnownType > 0 then
                if hasKnownType > 1 then
                    err[#err+1] = 'TYPE_ERROR_CHILD_ALL_DISMATCH'
                    err[#err+1] = child
                    err[#err+1] = parent
                end
                return false, err
            end
            return true, err
        else
            local weakNil = config.get(uri, 'Lua.type.weakNilCheck')
            for n in child:eachObject() do
                local nodeName = getNodeName(n)
                if  nodeName
                and not (nodeName == 'nil' and weakNil)
                and vm.isSubType(uri, n, parent, mark, err) == false then
                    err[#err+1] = 'TYPE_ERROR_UNION_DISMATCH'
                    err[#err+1] = n
                    err[#err+1] = parent
                    return false, err
                end
            end
            if not weakNil and child:isOptional() then
                if vm.isSubType(uri, 'nil', parent, mark, err) == false then
                    err[#err+1] = 'TYPE_ERROR_OPTIONAL_DISMATCH'
                    err[#err+1] = parent
                    return false, err
                end
            end
            return true, err
        end
    end

    ---@cast child  vm.node.object
    local childName = getNodeName(child)
    if childName == 'any'
    or childName == 'unknown' then
        return true, err
    end

    if not childName
    or isAlias(childName, uri) then
        return nil, err
    end

    if type(parent) == 'string' then
        local global = vm.getGlobal('type', parent)
        if not global then
            return false, err
        end
        parent = global
    elseif parent.type == 'vm.node' then
        local hasKnownType = 0
        for n in parent:eachObject() do
            if getNodeName(n) then
                hasKnownType = hasKnownType + 1
                if vm.isSubType(uri, child, n, mark, err) == true then
                    return true, err
                end
            end
        end
        if parent:isOptional() then
            if vm.isSubType(uri, child, 'nil', mark, err) == true then
                return true, err
            end
        end
        if hasKnownType > 0 then
            if hasKnownType > 1 then
                err[#err+1] = 'TYPE_ERROR_PARENT_ALL_DISMATCH'
                err[#err+1] = child
                err[#err+1] = parent
            end
            return false, err
        end
        return true, err
    end

    ---@cast parent vm.node.object

    local parentName = getNodeName(parent)
    if parentName == 'any'
    or parentName == 'unknown' then
        return true, err
    end

    if not parentName
    or isAlias(parentName, uri) then
        return nil, err
    end

    if childName == parentName then
        if not checkValue(parent, child, mark, err) then
            return false, err
        end
        return true, err
    end

    if parentName == 'number' and childName == 'integer' then
        return true, err
    end

    if parentName == 'integer' and childName == 'number' then
        if config.get(uri, 'Lua.type.castNumberToInteger') then
            return true, err
        end
        if  child.type == 'number'
        and child[1]
        and not math.tointeger(child[1]) then
            err[#err+1] = 'TYPE_ERROR_NUMBER_LITERAL_TO_INTEGER'
            err[#err+1] = child[1]
            return false, err
        end
        if  child.type == 'global'
        and child.cate == 'type' then
            err[#err+1] = 'TYPE_ERROR_NUMBER_TYPE_TO_INTEGER'
            return false, err
        end
        return true, err
    end

    local isEnum = checkEnum(parentName, child, uri, err)
    if isEnum ~= nil then
        return isEnum, err
    end

    if parentName == 'table' and not guide.isBasicType(childName) then
        return true, err
    end
    if childName == 'table' and not guide.isBasicType(parentName) then
        return true, err
    end

    -- check class parent
    if childName and not mark[childName] then
        mark[childName] = true
        local isBasicType = guide.isBasicType(childName)
        local childClass = vm.getGlobal('type', childName)
        if childClass then
            for _, set in ipairs(childClass:getSets(uri)) do
                if set.type == 'doc.class' and set.extends then
                    for _, ext in ipairs(set.extends) do
                        if  ext.type == 'doc.extends.name'
                        and (not isBasicType or guide.isBasicType(ext[1]))
                        and vm.isSubType(uri, ext[1], parent, mark, err) == true then
                            return true, err
                        end
                    end
                end
            end
        end
        mark[childName] = nil
    end

    --[[
    ---@class A: string

    ---@type A
    local x = '' --> `string` set to `A`
    ]]
    if  guide.isBasicType(childName)
    and guide.isLiteral(child)
    and vm.isSubType(uri, parentName, childName, mark) then
        return true, err
    end

    err[#err+1] = 'TYPE_ERROR_DISMATCH'
    err[#err+1] = child
    err[#err+1] = parent
    return false, err
end

---@param node string|vm.node|vm.object
function vm.isUnknown(node)
    if type(node) == 'string' then
        return node == 'unknown'
    end
    if node.type == 'vm.node' then
        return not node:hasKnownType()
    end
    return false
end

---@param uri uri
---@param tnode vm.node
---@param knode vm.node|string
---@param inversion? boolean
---@return vm.node?
function vm.getTableValue(uri, tnode, knode, inversion)
    local result = vm.createNode()
    for tn in tnode:eachObject() do
        if tn.type == 'doc.type.table' then
            for _, field in ipairs(tn.fields) do
                if field.extends then
                    if inversion then
                        if vm.isSubType(uri, vm.compileNode(field.name), knode) then
                            result:merge(vm.compileNode(field.extends))
                        end
                    else
                        if vm.isSubType(uri, knode, vm.compileNode(field.name)) then
                            result:merge(vm.compileNode(field.extends))
                        end
                    end
                end
            end
        end
        if tn.type == 'doc.type.array' then
            result:merge(vm.compileNode(tn.node))
        end
        if tn.type == 'table' then
            if vm.isUnknown(knode) then
                goto CONTINUE
            end
            for _, field in ipairs(tn) do
                if  field.type == 'tableindex'
                and field.value then
                    result:merge(vm.compileNode(field.value))
                end
                if  field.type == 'tablefield'
                and field.value then
                    if inversion then
                        if vm.isSubType(uri, 'string', knode) then
                            result:merge(vm.compileNode(field.value))
                        end
                    else
                        if vm.isSubType(uri, knode, 'string') then
                            result:merge(vm.compileNode(field.value))
                        end
                    end
                end
                if  field.type == 'tableexp'
                and field.value
                and field.tindex == 1 then
                    if inversion then
                        if vm.isSubType(uri, 'integer', knode)  then
                            result:merge(vm.compileNode(field.value))
                        end
                    else
                        if vm.isSubType(uri, knode, 'integer')  then
                            result:merge(vm.compileNode(field.value))
                        end
                    end
                end
                if field.type == 'varargs' then
                    result:merge(vm.compileNode(field))
                end
            end
        end
        ::CONTINUE::
    end
    if result:isEmpty() then
        return nil
    end
    return result
end

---@param uri uri
---@param tnode vm.node
---@param vnode vm.node|string|vm.object
---@param reverse? boolean
---@return vm.node?
function vm.getTableKey(uri, tnode, vnode, reverse)
    local result = vm.createNode()
    for tn in tnode:eachObject() do
        if tn.type == 'doc.type.table' then
            for _, field in ipairs(tn.fields) do
                if  field.name.type ~= 'doc.field.name'
                and field.extends then
                    if reverse then
                        if vm.isSubType(uri, vm.compileNode(field.extends), vnode) then
                            result:merge(vm.compileNode(field.name))
                        end
                    else
                        if vm.isSubType(uri, vnode, vm.compileNode(field.extends)) then
                            result:merge(vm.compileNode(field.name))
                        end
                    end
                end
            end
        end
        if tn.type == 'doc.type.array' then
            result:merge(vm.declareGlobal('type', 'integer'))
        end
        if tn.type == 'table' then
            if vm.isUnknown(tnode) then
                goto CONTINUE
            end
            for _, field in ipairs(tn) do
                if field.type == 'tableindex' then
                    if field.index then
                        result:merge(vm.compileNode(field.index))
                    end
                end
                if field.type == 'tablefield' then
                    result:merge(vm.declareGlobal('type', 'string'))
                end
                if field.type == 'tableexp' then
                    result:merge(vm.declareGlobal('type', 'integer'))
                end
            end
        end
        ::CONTINUE::
    end
    if result:isEmpty() then
        return nil
    end
    return result
end

---@param uri uri
---@param defNode vm.node
---@param refNode vm.node
---@return boolean
---@return typecheck.err[]?
function vm.canCastType(uri, defNode, refNode)
    local defInfer = vm.getInfer(defNode)
    local refInfer = vm.getInfer(refNode)

    if defInfer:hasAny(uri) then
        return true
    end
    if refInfer:hasAny(uri) then
        return true
    end
    if defInfer:view(uri) == 'unknown' then
        return true
    end
    if refInfer:view(uri) == 'unknown' then
        return true
    end
    if defInfer:view(uri) == 'nil' then
        return true
    end

    if vm.isSubType(uri, refNode, 'nil') then
        -- allow `local x = {};x = nil`,
        -- but not allow `local x ---@type table;x = nil`
        if  defInfer:hasType(uri, 'table')
        and not defNode:hasType 'table' then
            return true
        end
    end

    if vm.isSubType(uri, refNode, 'number') then
        -- allow `local x = 0;x = 1.0`,
        -- but not allow `local x ---@type integer;x = 1.0`
        if  defInfer:hasType(uri, 'integer')
        and not defNode:hasType 'integer' then
            return true
        end
    end

    local suc, err = vm.isSubType(uri, refNode, defNode)

    if suc then
        return true
    end

    return false, err
end

local ErrorMessageMap = {
    TYPE_ERROR_ENUM_GLOBAL_DISMATCH       = {'child', 'parent'},
    TYPE_ERROR_ENUM_GENERIC_UNSUPPORTED   = {'child'},
    TYPE_ERROR_ENUM_LITERAL_DISMATCH      = {'child', 'parent'},
    TYPE_ERROR_ENUM_OBJECT_DISMATCH       = {'child', 'parent'},
    TYPE_ERROR_ENUM_NO_OBJECT             = {'child'},
    TYPE_ERROR_INTEGER_DISMATCH           = {'child', 'parent'},
    TYPE_ERROR_STRING_DISMATCH            = {'child', 'parent'},
    TYPE_ERROR_BOOLEAN_DISMATCH           = {'child', 'parent'},
    TYPE_ERROR_TABLE_NO_FIELD             = {'key'},
    TYPE_ERROR_TABLE_FIELD_DISMATCH       = {'key', 'child', 'parent'},
    TYPE_ERROR_CHILD_ALL_DISMATCH         = {'child', 'parent'},
    TYPE_ERROR_PARENT_ALL_DISMATCH        = {'child', 'parent'},
    TYPE_ERROR_UNION_DISMATCH             = {'child', 'parent'},
    TYPE_ERROR_OPTIONAL_DISMATCH          = {'parent'},
    TYPE_ERROR_NUMBER_LITERAL_TO_INTEGER  = {'child'},
    TYPE_ERROR_NUMBER_TYPE_TO_INTEGER     = {},
    TYPE_ERROR_DISMATCH                   = {'child', 'parent'},
}

---@param uri uri
---@param errs typecheck.err[]
---@return string
function vm.viewTypeErrorMessage(uri, errs)
    local lines = {}
    local index = 1
    while true do
        local name = errs[index]
        if not name then
            break
        end
        index = index + 1
        local params = ErrorMessageMap[name]
        local lparams = {}
        for _, paramName in ipairs(params) do
            local value = errs[index]
            if type(value) == 'string'
            or type(value) == 'number'
            or type(value) == 'boolean' then
                lparams[paramName] = util.viewLiteral(value)
            elseif value.type == 'global' then
                lparams[paramName] = value.name
            elseif value.type == 'vm.node' then
                ---@cast value vm.node
                lparams[paramName] = vm.getInfer(value):view(uri)
            elseif value.type == 'table' then
                lparams[paramName] = 'table'
            elseif value.type == 'generic' then
                ---@cast value vm.generic
                lparams[paramName] = vm.viewObject(value, uri)
            else
                ---@cast value -string, -vm.global, -vm.node, -vm.generic
                if paramName == 'key' then
                    lparams[paramName] = vm.viewKey(value, uri)
                else
                    lparams[paramName] = vm.viewObject(value, uri)
                                      or vm.getInfer(value):view(uri)
                end
            end
            index = index + 1
        end
        local line = lang.script(name, lparams)
        lines[#lines+1] = '- ' .. line
    end
    util.revertTable(lines)
    return table.concat(lines, '\n')
end
