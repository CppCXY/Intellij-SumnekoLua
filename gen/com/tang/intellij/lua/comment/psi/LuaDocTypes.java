// This is a generated file. Not intended for manual editing.
package com.tang.intellij.lua.comment.psi;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.PsiElement;
import com.intellij.lang.ASTNode;
import com.tang.intellij.lua.lang.LuaParserDefinitionKt;
import com.tang.intellij.lua.comment.psi.impl.*;

public interface LuaDocTypes {

  IElementType ACCESS_MODIFIER = LuaParserDefinitionKt.createDocType("ACCESS_MODIFIER");
  IElementType COMMENT_STRING = LuaParserDefinitionKt.createDocType("COMMENT_STRING");
  IElementType GENERIC_DEF = LuaParserDefinitionKt.createDocType("GENERIC_DEF");
  IElementType TAG_ALIAS = LuaParserDefinitionKt.createDocType("TAG_ALIAS");
  IElementType TAG_CLASS = LuaParserDefinitionKt.createDocType("TAG_CLASS");
  IElementType TAG_DEF = LuaParserDefinitionKt.createDocType("TAG_DEF");
  IElementType TAG_FIELD = LuaParserDefinitionKt.createDocType("TAG_FIELD");
  IElementType TAG_GENERIC_LIST = LuaParserDefinitionKt.createDocType("TAG_GENERIC_LIST");
  IElementType TAG_LAN = LuaParserDefinitionKt.createDocType("TAG_LAN");
  IElementType TAG_OTHER = LuaParserDefinitionKt.createDocType("TAG_OTHER");
  IElementType TAG_OVERLOAD = LuaParserDefinitionKt.createDocType("TAG_OVERLOAD");
  IElementType TAG_PARAM = LuaParserDefinitionKt.createDocType("TAG_PARAM");
  IElementType TAG_RETURN = LuaParserDefinitionKt.createDocType("TAG_RETURN");
  IElementType TAG_SEE = LuaParserDefinitionKt.createDocType("TAG_SEE");
  IElementType TAG_SUPPRESS = LuaParserDefinitionKt.createDocType("TAG_SUPPRESS");
  IElementType TAG_TYPE = LuaParserDefinitionKt.createDocType("TAG_TYPE");
  IElementType TAG_VARARG = LuaParserDefinitionKt.createDocType("TAG_VARARG");

  IElementType AT = new LuaDocTokenType("@");
  IElementType CLASS_NAME_REF = new LuaDocTokenType("class_name_ref");
  IElementType COMMA = new LuaDocTokenType(",");
  IElementType DASHES = new LuaDocTokenType("DASHES");
  IElementType EQ = new LuaDocTokenType("=");
  IElementType EXTENDS = new LuaDocTokenType(":");
  IElementType GT = new LuaDocTokenType(">");
  IElementType ID = new LuaDocTokenType("ID");
  IElementType LCURLY = new LuaDocTokenType("{");
  IElementType LPAREN = new LuaDocTokenType("(");
  IElementType LT = new LuaDocTokenType("<");
  IElementType OR = new LuaDocTokenType("|");
  IElementType PRIVATE = new LuaDocTokenType("PRIVATE");
  IElementType PROTECTED = new LuaDocTokenType("PROTECTED");
  IElementType PUBLIC = new LuaDocTokenType("PUBLIC");
  IElementType RCURLY = new LuaDocTokenType("}");
  IElementType RPAREN = new LuaDocTokenType(")");
  IElementType STRING = new LuaDocTokenType("STRING");
  IElementType STRING_BEGIN = new LuaDocTokenType("STRING_BEGIN");
  IElementType STRING_LITERAL = new LuaDocTokenType("STRING_LITERAL");
  IElementType TAG_NAME = new LuaDocTokenType("TAG_NAME");
  IElementType TAG_NAME_ALIAS = new LuaDocTokenType("alias");
  IElementType TAG_NAME_CLASS = new LuaDocTokenType("class");
  IElementType TAG_NAME_FIELD = new LuaDocTokenType("field");
  IElementType TAG_NAME_GENERIC = new LuaDocTokenType("generic");
  IElementType TAG_NAME_LANGUAGE = new LuaDocTokenType("language");
  IElementType TAG_NAME_MODULE = new LuaDocTokenType("module");
  IElementType TAG_NAME_NAME = new LuaDocTokenType("TAG_NAME_NAME");
  IElementType TAG_NAME_OVERLOAD = new LuaDocTokenType("overload");
  IElementType TAG_NAME_PARAM = new LuaDocTokenType("param");
  IElementType TAG_NAME_PRIVATE = new LuaDocTokenType("private");
  IElementType TAG_NAME_PROTECTED = new LuaDocTokenType("protected");
  IElementType TAG_NAME_PUBLIC = new LuaDocTokenType("public");
  IElementType TAG_NAME_RETURN = new LuaDocTokenType("return");
  IElementType TAG_NAME_SEE = new LuaDocTokenType("see");
  IElementType TAG_NAME_SUPPRESS = new LuaDocTokenType("suppress");
  IElementType TAG_NAME_TYPE = new LuaDocTokenType("type");
  IElementType TAG_NAME_VARARG = new LuaDocTokenType("vararg");

  class Factory {
    public static PsiElement createElement(ASTNode node) {
      IElementType type = node.getElementType();
      if (type == ACCESS_MODIFIER) {
        return new LuaDocAccessModifierImpl(node);
      }
      else if (type == COMMENT_STRING) {
        return new LuaDocCommentStringImpl(node);
      }
      else if (type == GENERIC_DEF) {
        return new LuaDocGenericDefImpl(node);
      }
      else if (type == TAG_ALIAS) {
        return new LuaDocTagAliasImpl(node);
      }
      else if (type == TAG_CLASS) {
        return new LuaDocTagClassImpl(node);
      }
      else if (type == TAG_DEF) {
        return new LuaDocTagDefImpl(node);
      }
      else if (type == TAG_FIELD) {
        return new LuaDocTagFieldImpl(node);
      }
      else if (type == TAG_GENERIC_LIST) {
        return new LuaDocTagGenericListImpl(node);
      }
      else if (type == TAG_LAN) {
        return new LuaDocTagLanImpl(node);
      }
      else if (type == TAG_OTHER) {
        return new LuaDocTagOtherImpl(node);
      }
      else if (type == TAG_OVERLOAD) {
        return new LuaDocTagOverloadImpl(node);
      }
      else if (type == TAG_PARAM) {
        return new LuaDocTagParamImpl(node);
      }
      else if (type == TAG_RETURN) {
        return new LuaDocTagReturnImpl(node);
      }
      else if (type == TAG_SEE) {
        return new LuaDocTagSeeImpl(node);
      }
      else if (type == TAG_SUPPRESS) {
        return new LuaDocTagSuppressImpl(node);
      }
      else if (type == TAG_TYPE) {
        return new LuaDocTagTypeImpl(node);
      }
      else if (type == TAG_VARARG) {
        return new LuaDocTagVarargImpl(node);
      }
      throw new AssertionError("Unknown element type: " + type);
    }
  }
}
