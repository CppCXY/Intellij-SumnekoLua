package com.tang.intellij.lua.comment.lexer;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;
import com.tang.intellij.lua.comment.psi.LuaDocTypes;

%%

%class _LuaDocLexer
%implements FlexLexer, LuaDocTypes


%unicode
%public

%function advance
%type IElementType

%eof{ return;
%eof}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////// User code //////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

%{ // User code
    private int _typeLevel = 0;
    private boolean _typeReq = false;
    public _LuaDocLexer() {
        this((java.io.Reader) null);
    }

    private void beginType() {
        _typeLevel = 0;
        _typeReq = true;
    }
%}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////// LuaDoc lexems ////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

EOL="\r"|"\n"|"\r\n"
LINE_WS=[\ \t\f]
WHITE_SPACE=({LINE_WS}|{EOL})+
STRING=[^\r\n\t\f]*
ID=[:jletter:] ([:jletterdigit:]|\.)*
AT=@
//三个-以上
DOC_DASHES = --+
//Strings
DOUBLE_QUOTED_STRING=\"([^\\\"]|\\\S|\\[\r\n])*\"?  //\"([^\\\"\r\n]|\\[^\r\n])*\"?
SINGLE_QUOTED_STRING='([^\\\']|\\\S|\\[\r\n])*'?    //'([^\\'\r\n]|\\[^\r\n])*'?

%state xTAG
%state xTAG_WITH_ID
%state xTAG_NAME
%state xCOMMENT_STRING
%state xLANGUAGE

%%

<YYINITIAL> {
    {EOL}                      { yybegin(YYINITIAL); return com.intellij.psi.TokenType.WHITE_SPACE;}
    {LINE_WS}+                 { return com.intellij.psi.TokenType.WHITE_SPACE; }
    {DOC_DASHES}               { return DASHES; }
    "@"                        { yybegin(xTAG_NAME); return AT; }
    .                          { yybegin(xCOMMENT_STRING); yypushback(yylength()); }
}

<xTAG, xTAG_WITH_ID, xTAG_NAME, xCOMMENT_STRING, xLANGUAGE> {
    {EOL}                      { yybegin(YYINITIAL);return com.intellij.psi.TokenType.WHITE_SPACE;}
    {LINE_WS}+                 { return com.intellij.psi.TokenType.WHITE_SPACE; }
}

<xTAG_NAME> {
    "field"                    {  yybegin(xCOMMENT_STRING); return TAG_NAME_FIELD; }
    "param"                    {  yybegin(xCOMMENT_STRING); return TAG_NAME_PARAM; }
    "vararg"                   {  yybegin(xCOMMENT_STRING); return TAG_NAME_VARARG; }
    "class"                    {  yybegin(xCOMMENT_STRING); return TAG_NAME_CLASS; }
    "module"                   {  yybegin(xCOMMENT_STRING); return TAG_NAME_MODULE; }
    "return"                   {  yybegin(xCOMMENT_STRING); return TAG_NAME_RETURN; }
    "type"                     {  yybegin(xCOMMENT_STRING); return TAG_NAME_TYPE;}
    "overload"                 {  yybegin(xCOMMENT_STRING); return TAG_NAME_OVERLOAD; }
    "private"                  {  yybegin(xCOMMENT_STRING); return TAG_NAME_PRIVATE; }
    "protected"                {  yybegin(xCOMMENT_STRING); return TAG_NAME_PROTECTED; }
    "public"                   {  yybegin(xCOMMENT_STRING); return TAG_NAME_PUBLIC; }
    "language"                 {  yybegin(xLANGUAGE); return TAG_NAME_LANGUAGE; }
    "generic"                  {  yybegin(xCOMMENT_STRING); return TAG_NAME_GENERIC; }
    "see"                      {  yybegin(xCOMMENT_STRING); return TAG_NAME_SEE; }
    "alias"                    {  yybegin(xCOMMENT_STRING); return TAG_NAME_ALIAS; }
    "suppress"                 {  yybegin(xCOMMENT_STRING); return TAG_NAME_SUPPRESS; }
    {ID}                       {  yybegin(xCOMMENT_STRING); return TAG_NAME; }
    [^]                        { return com.intellij.psi.TokenType.BAD_CHARACTER; }
}

<xLANGUAGE> {
    {ID}                       { yybegin(xCOMMENT_STRING); return ID; }
    [^]                        { return com.intellij.psi.TokenType.BAD_CHARACTER; }
}

<xTAG> {
    "@"                        { yybegin(xCOMMENT_STRING); return STRING_BEGIN; }
    "#"                        { yybegin(xCOMMENT_STRING); return STRING_BEGIN; }
    {ID}                       { return ID; }
    [^]                        { return com.intellij.psi.TokenType.BAD_CHARACTER; }
}

<xCOMMENT_STRING> {
    {STRING}                   { yybegin(YYINITIAL); return STRING; }
}

[^]                            { return com.intellij.psi.TokenType.BAD_CHARACTER; }