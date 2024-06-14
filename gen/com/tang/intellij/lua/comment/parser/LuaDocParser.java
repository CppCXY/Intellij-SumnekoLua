// This is a generated file. Not intended for manual editing.
package com.tang.intellij.lua.comment.parser;

import com.intellij.lang.PsiBuilder;
import com.intellij.lang.PsiBuilder.Marker;
import static com.tang.intellij.lua.comment.psi.LuaDocTypes.*;
import static com.tang.intellij.lua.psi.LuaParserUtil.*;
import com.intellij.psi.tree.IElementType;
import com.intellij.lang.ASTNode;
import com.intellij.psi.tree.TokenSet;
import com.intellij.lang.PsiParser;
import com.intellij.lang.LightPsiParser;

@SuppressWarnings({"SimplifiableIfStatement", "UnusedAssignment"})
public class LuaDocParser implements PsiParser, LightPsiParser {

  public ASTNode parse(IElementType t, PsiBuilder b) {
    parseLight(t, b);
    return b.getTreeBuilt();
  }

  public void parseLight(IElementType t, PsiBuilder b) {
    boolean r;
    b = adapt_builder_(t, b, this, null);
    Marker m = enter_section_(b, 0, _COLLAPSE_, null);
    r = parse_root_(t, b);
    exit_section_(b, 0, m, t, r, true, TRUE_CONDITION);
  }

  protected boolean parse_root_(IElementType t, PsiBuilder b) {
    return parse_root_(t, b, 0);
  }

  static boolean parse_root_(IElementType t, PsiBuilder b, int l) {
    return doc(b, l + 1);
  }

  /* ********************************************************** */
  // PRIVATE | PUBLIC | PROTECTED | TAG_NAME_PRIVATE | TAG_NAME_PUBLIC | TAG_NAME_PROTECTED
  public static boolean access_modifier(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "access_modifier")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, ACCESS_MODIFIER, "<access modifier>");
    r = consumeToken(b, PRIVATE);
    if (!r) r = consumeToken(b, PUBLIC);
    if (!r) r = consumeToken(b, PROTECTED);
    if (!r) r = consumeToken(b, TAG_NAME_PRIVATE);
    if (!r) r = consumeToken(b, TAG_NAME_PUBLIC);
    if (!r) r = consumeToken(b, TAG_NAME_PROTECTED);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // doc_item | STRING
  static boolean after_dash(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "after_dash")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_);
    r = doc_item(b, l + 1);
    if (!r) r = consumeToken(b, STRING);
    exit_section_(b, l, m, r, false, LuaDocParser::after_dash_recover);
    return r;
  }

  /* ********************************************************** */
  // !(DASHES)
  static boolean after_dash_recover(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "after_dash_recover")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NOT_);
    r = !consumeToken(b, DASHES);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // STRING_BEGIN? STRING?
  public static boolean comment_string(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "comment_string")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, COMMENT_STRING, "<comment string>");
    r = comment_string_0(b, l + 1);
    r = r && comment_string_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // STRING_BEGIN?
  private static boolean comment_string_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "comment_string_0")) return false;
    consumeToken(b, STRING_BEGIN);
    return true;
  }

  // STRING?
  private static boolean comment_string_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "comment_string_1")) return false;
    consumeToken(b, STRING);
    return true;
  }

  /* ********************************************************** */
  // (DASHES after_dash?)*
  static boolean doc(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "doc")) return false;
    while (true) {
      int c = current_position_(b);
      if (!doc_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "doc", c)) break;
    }
    return true;
  }

  // DASHES after_dash?
  private static boolean doc_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "doc_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, DASHES);
    r = r && doc_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // after_dash?
  private static boolean doc_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "doc_0_1")) return false;
    after_dash(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // '@' (tag_param
  //     | tag_alias
  //     | tag_suppress
  //     | tag_vararg
  //     | tag_return
  //     | tag_class
  //     | tag_field
  //     | tag_type
  //     | tag_lan
  //     | tag_overload
  //     | tag_see
  //     | tag_def
  //     | access_modifier
  //     | tag_generic_list
  //     | tag_other)
  static boolean doc_item(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "doc_item")) return false;
    if (!nextTokenIs(b, AT)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, AT);
    r = r && doc_item_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // tag_param
  //     | tag_alias
  //     | tag_suppress
  //     | tag_vararg
  //     | tag_return
  //     | tag_class
  //     | tag_field
  //     | tag_type
  //     | tag_lan
  //     | tag_overload
  //     | tag_see
  //     | tag_def
  //     | access_modifier
  //     | tag_generic_list
  //     | tag_other
  private static boolean doc_item_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "doc_item_1")) return false;
    boolean r;
    r = tag_param(b, l + 1);
    if (!r) r = tag_alias(b, l + 1);
    if (!r) r = tag_suppress(b, l + 1);
    if (!r) r = tag_vararg(b, l + 1);
    if (!r) r = tag_return(b, l + 1);
    if (!r) r = tag_class(b, l + 1);
    if (!r) r = tag_field(b, l + 1);
    if (!r) r = tag_type(b, l + 1);
    if (!r) r = tag_lan(b, l + 1);
    if (!r) r = tag_overload(b, l + 1);
    if (!r) r = tag_see(b, l + 1);
    if (!r) r = tag_def(b, l + 1);
    if (!r) r = access_modifier(b, l + 1);
    if (!r) r = tag_generic_list(b, l + 1);
    if (!r) r = tag_other(b, l + 1);
    return r;
  }

  /* ********************************************************** */
  // TAG_NAME_ALIAS
  public static boolean tag_alias(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "tag_alias")) return false;
    if (!nextTokenIs(b, TAG_NAME_ALIAS)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, TAG_NAME_ALIAS);
    exit_section_(b, m, TAG_ALIAS, r);
    return r;
  }

  /* ********************************************************** */
  // TAG_NAME_CLASS|TAG_NAME_MODULE
  public static boolean tag_class(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "tag_class")) return false;
    if (!nextTokenIs(b, "<tag class>", TAG_NAME_CLASS, TAG_NAME_MODULE)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, TAG_CLASS, "<tag class>");
    r = consumeToken(b, TAG_NAME_CLASS);
    if (!r) r = consumeToken(b, TAG_NAME_MODULE);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // TAG_NAME_NAME
  public static boolean tag_def(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "tag_def")) return false;
    if (!nextTokenIs(b, TAG_NAME_NAME)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, TAG_NAME_NAME);
    exit_section_(b, m, TAG_DEF, r);
    return r;
  }

  /* ********************************************************** */
  // TAG_NAME_FIELD
  public static boolean tag_field(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "tag_field")) return false;
    if (!nextTokenIs(b, TAG_NAME_FIELD)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, TAG_NAME_FIELD);
    exit_section_(b, m, TAG_FIELD, r);
    return r;
  }

  /* ********************************************************** */
  // TAG_NAME_GENERIC
  public static boolean tag_generic_list(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "tag_generic_list")) return false;
    if (!nextTokenIs(b, TAG_NAME_GENERIC)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, TAG_NAME_GENERIC);
    exit_section_(b, m, TAG_GENERIC_LIST, r);
    return r;
  }

  /* ********************************************************** */
  // TAG_NAME_LANGUAGE ID
  public static boolean tag_lan(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "tag_lan")) return false;
    if (!nextTokenIs(b, TAG_NAME_LANGUAGE)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, TAG_LAN, null);
    r = consumeTokens(b, 1, TAG_NAME_LANGUAGE, ID);
    p = r; // pin = 1
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // TAG_NAME
  public static boolean tag_other(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "tag_other")) return false;
    if (!nextTokenIs(b, TAG_NAME)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, TAG_NAME);
    exit_section_(b, m, TAG_OTHER, r);
    return r;
  }

  /* ********************************************************** */
  // TAG_NAME_OVERLOAD
  public static boolean tag_overload(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "tag_overload")) return false;
    if (!nextTokenIs(b, TAG_NAME_OVERLOAD)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, TAG_NAME_OVERLOAD);
    exit_section_(b, m, TAG_OVERLOAD, r);
    return r;
  }

  /* ********************************************************** */
  // TAG_NAME_PARAM
  public static boolean tag_param(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "tag_param")) return false;
    if (!nextTokenIs(b, TAG_NAME_PARAM)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, TAG_NAME_PARAM);
    exit_section_(b, m, TAG_PARAM, r);
    return r;
  }

  /* ********************************************************** */
  // TAG_NAME_RETURN
  public static boolean tag_return(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "tag_return")) return false;
    if (!nextTokenIs(b, TAG_NAME_RETURN)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, TAG_NAME_RETURN);
    exit_section_(b, m, TAG_RETURN, r);
    return r;
  }

  /* ********************************************************** */
  // TAG_NAME_SEE
  public static boolean tag_see(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "tag_see")) return false;
    if (!nextTokenIs(b, TAG_NAME_SEE)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, TAG_NAME_SEE);
    exit_section_(b, m, TAG_SEE, r);
    return r;
  }

  /* ********************************************************** */
  // TAG_NAME_SUPPRESS
  public static boolean tag_suppress(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "tag_suppress")) return false;
    if (!nextTokenIs(b, TAG_NAME_SUPPRESS)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, TAG_NAME_SUPPRESS);
    exit_section_(b, m, TAG_SUPPRESS, r);
    return r;
  }

  /* ********************************************************** */
  // TAG_NAME_TYPE
  public static boolean tag_type(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "tag_type")) return false;
    if (!nextTokenIs(b, TAG_NAME_TYPE)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, TAG_NAME_TYPE);
    exit_section_(b, m, TAG_TYPE, r);
    return r;
  }

  /* ********************************************************** */
  // TAG_NAME_VARARG
  public static boolean tag_vararg(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "tag_vararg")) return false;
    if (!nextTokenIs(b, TAG_NAME_VARARG)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, TAG_NAME_VARARG);
    exit_section_(b, m, TAG_VARARG, r);
    return r;
  }

}
