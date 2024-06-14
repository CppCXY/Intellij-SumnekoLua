// Generated by JFlex 1.9.1 http://jflex.de/  (tweaked for IntelliJ platform)
// source: doc.flex

package com.tang.intellij.lua.comment.lexer;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;
import com.tang.intellij.lua.comment.psi.LuaDocTypes;


public class _LuaDocLexer implements FlexLexer, LuaDocTypes {

  /** This character denotes the end of file */
  public static final int YYEOF = -1;

  /** initial size of the lookahead buffer */
  private static final int ZZ_BUFFERSIZE = 16384;

  /** lexical states */
  public static final int YYINITIAL = 0;
  public static final int xTAG = 2;
  public static final int xTAG_WITH_ID = 4;
  public static final int xTAG_NAME = 6;
  public static final int xCOMMENT_STRING = 8;
  public static final int xLANGUAGE = 10;

  /**
   * ZZ_LEXSTATE[l] is the state in the DFA for the lexical state l
   * ZZ_LEXSTATE[l+1] is the state in the DFA for the lexical state l
   *                  at the beginning of a line
   * l is of the form l = 2*k, k a non negative integer
   */
  private static final int ZZ_LEXSTATE[] = {
     0,  0,  1,  1,  2,  2,  3,  3,  4,  4,  5, 5
  };

  /**
   * Top-level table for translating characters to character classes
   */
  private static final int [] ZZ_CMAP_TOP = zzUnpackcmap_top();

  private static final String ZZ_CMAP_TOP_PACKED_0 =
    "\1\0\1\u0100\1\u0200\1\u0300\1\u0400\1\u0500\1\u0600\1\u0700"+
    "\1\u0800\1\u0900\1\u0a00\1\u0b00\1\u0c00\1\u0d00\1\u0e00\1\u0f00"+
    "\1\u1000\1\u0100\1\u1100\1\u1200\1\u1300\1\u0100\1\u1400\1\u1500"+
    "\1\u1600\1\u1700\1\u1800\1\u1900\1\u1a00\1\u1b00\1\u0100\1\u1c00"+
    "\1\u1d00\1\u1e00\12\u1f00\1\u2000\1\u2100\1\u2200\1\u1f00\1\u2300"+
    "\1\u2400\2\u1f00\31\u0100\1\u2500\121\u0100\1\u2600\4\u0100\1\u2700"+
    "\1\u0100\1\u2800\1\u2900\1\u2a00\1\u2b00\1\u2c00\1\u2d00\53\u0100"+
    "\1\u2e00\10\u2f00\31\u1f00\1\u0100\1\u3000\1\u3100\1\u0100\1\u3200"+
    "\1\u3300\1\u3400\1\u3500\1\u3600\1\u3700\1\u3800\1\u3900\1\u3a00"+
    "\1\u0100\1\u3b00\1\u3c00\1\u3d00\1\u3e00\1\u3f00\1\u4000\1\u4100"+
    "\1\u4200\1\u4300\1\u4400\1\u4500\1\u4600\1\u4700\1\u4800\1\u4900"+
    "\1\u4a00\1\u4b00\1\u4c00\1\u4d00\1\u4e00\1\u1f00\1\u4f00\1\u5000"+
    "\1\u5100\1\u5200\3\u0100\1\u5300\1\u5400\1\u5500\12\u1f00\4\u0100"+
    "\1\u5600\17\u1f00\2\u0100\1\u5700\41\u1f00\2\u0100\1\u5800\1\u5900"+
    "\2\u1f00\1\u5a00\1\u5b00\27\u0100\1\u5c00\4\u0100\1\u5d00\1\u5e00"+
    "\42\u1f00\1\u0100\1\u5f00\1\u6000\11\u1f00\1\u6100\24\u1f00\1\u6200"+
    "\1\u6300\1\u1f00\1\u6400\1\u6500\1\u6600\1\u6700\2\u1f00\1\u6800"+
    "\5\u1f00\1\u6900\1\u6a00\1\u6b00\5\u1f00\1\u6c00\1\u6d00\2\u1f00"+
    "\1\u6e00\1\u1f00\1\u6f00\14\u1f00\1\u7000\4\u1f00\246\u0100\1\u7100"+
    "\20\u0100\1\u7200\1\u7300\25\u0100\1\u7400\34\u0100\1\u7500\14\u1f00"+
    "\2\u0100\1\u7600\5\u1f00\23\u0100\1\u7700\u0aec\u1f00\1\u7800\1\u7900"+
    "\u02fe\u1f00";

  private static int [] zzUnpackcmap_top() {
    int [] result = new int[4352];
    int offset = 0;
    offset = zzUnpackcmap_top(ZZ_CMAP_TOP_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackcmap_top(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /**
   * Second-level tables for translating characters to character classes
   */
  private static final int [] ZZ_CMAP_BLOCKS = zzUnpackcmap_blocks();

  private static final String ZZ_CMAP_BLOCKS_PACKED_0 =
    "\11\0\1\1\1\2\1\3\1\1\1\4\16\0\4\5"+
    "\1\6\2\5\1\7\1\10\10\5\1\11\1\0\1\5"+
    "\12\0\6\5\1\12\32\10\4\5\1\10\1\5\1\13"+
    "\1\14\1\15\1\16\1\17\1\20\1\21\1\10\1\22"+
    "\2\10\1\23\1\24\1\25\1\26\1\27\1\10\1\30"+
    "\1\31\1\32\1\33\1\34\2\10\1\35\1\10\4\5"+
    "\6\0\1\36\32\0\2\5\4\10\4\5\1\10\2\5"+
    "\1\0\7\5\1\10\4\5\1\10\5\5\27\10\1\5"+
    "\37\10\1\5\u01ca\10\4\5\14\10\16\5\5\10\7\5"+
    "\1\10\1\5\1\10\21\5\160\0\5\10\1\5\2\10"+
    "\2\5\4\10\1\5\1\10\6\5\1\10\1\5\3\10"+
    "\1\5\1\10\1\5\24\10\1\5\123\10\1\5\213\10"+
    "\1\5\5\0\2\5\246\10\1\5\46\10\2\5\1\10"+
    "\6\5\51\10\6\5\1\10\1\5\55\0\1\5\1\0"+
    "\1\5\2\0\1\5\2\0\1\5\1\0\10\5\33\10"+
    "\4\5\4\10\15\5\6\0\5\5\1\10\4\5\13\0"+
    "\1\5\1\0\3\5\53\10\37\0\4\5\2\10\1\0"+
    "\143\10\1\5\1\10\10\0\1\5\6\0\2\10\2\0"+
    "\1\5\4\0\2\10\12\0\3\10\2\5\1\10\17\5"+
    "\1\0\1\10\1\0\36\10\33\0\2\5\131\10\13\0"+
    "\1\10\16\5\12\0\41\10\11\0\2\10\4\5\1\10"+
    "\2\5\1\0\30\10\4\0\1\10\11\0\1\10\3\0"+
    "\1\10\5\0\22\5\31\10\3\0\4\5\13\10\65\5"+
    "\25\10\1\5\22\10\13\5\61\0\66\10\3\0\1\10"+
    "\22\0\1\10\7\0\12\10\2\0\2\5\12\0\1\5"+
    "\20\10\3\0\1\5\10\10\2\5\2\10\2\5\26\10"+
    "\1\5\7\10\1\5\1\10\3\5\4\10\2\5\1\0"+
    "\1\10\7\0\2\5\2\0\2\5\3\0\1\10\10\5"+
    "\1\0\4\5\2\10\1\5\3\10\2\0\2\5\12\0"+
    "\4\10\7\5\2\10\1\5\1\0\2\5\3\0\1\5"+
    "\6\10\4\5\2\10\2\5\26\10\1\5\7\10\1\5"+
    "\2\10\1\5\2\10\1\5\2\10\2\5\1\0\1\5"+
    "\5\0\4\5\2\0\2\5\3\0\3\5\1\0\7\5"+
    "\4\10\1\5\1\10\7\5\14\0\3\10\1\0\13\5"+
    "\3\0\1\5\11\10\1\5\3\10\1\5\26\10\1\5"+
    "\7\10\1\5\2\10\1\5\5\10\2\5\1\0\1\10"+
    "\10\0\1\5\3\0\1\5\3\0\2\5\1\10\17\5"+
    "\2\10\2\0\2\5\12\0\1\5\1\10\7\5\1\10"+
    "\6\0\1\5\3\0\1\5\10\10\2\5\2\10\2\5"+
    "\26\10\1\5\7\10\1\5\2\10\1\5\5\10\2\5"+
    "\1\0\1\10\7\0\2\5\2\0\2\5\3\0\7\5"+
    "\3\0\4\5\2\10\1\5\3\10\2\0\2\5\12\0"+
    "\1\5\1\10\20\5\1\0\1\10\1\5\6\10\3\5"+
    "\3\10\1\5\4\10\3\5\2\10\1\5\1\10\1\5"+
    "\2\10\3\5\2\10\3\5\3\10\3\5\14\10\4\5"+
    "\5\0\3\5\3\0\1\5\4\0\2\5\1\10\6\5"+
    "\1\0\16\5\12\0\11\5\1\10\6\5\5\0\10\10"+
    "\1\5\3\10\1\5\27\10\1\5\20\10\3\5\1\10"+
    "\7\0\1\5\3\0\1\5\4\0\7\5\2\0\1\5"+
    "\3\10\5\5\2\10\2\0\2\5\12\0\20\5\1\10"+
    "\3\0\1\5\10\10\1\5\3\10\1\5\27\10\1\5"+
    "\12\10\1\5\5\10\2\5\1\0\1\10\7\0\1\5"+
    "\3\0\1\5\4\0\7\5\2\0\7\5\1\10\1\5"+
    "\2\10\2\0\2\5\12\0\1\5\2\10\15\5\4\0"+
    "\11\10\1\5\3\10\1\5\51\10\2\0\1\10\7\0"+
    "\1\5\3\0\1\5\4\0\1\10\5\5\3\10\1\0"+
    "\7\5\3\10\2\0\2\5\12\0\12\5\6\10\1\5"+
    "\3\0\1\5\22\10\3\5\30\10\1\5\11\10\1\5"+
    "\1\10\2\5\7\10\3\5\1\0\4\5\6\0\1\5"+
    "\1\0\1\5\10\0\6\5\12\0\2\5\2\0\15\5"+
    "\60\10\1\0\2\10\7\0\4\5\10\10\10\0\1\5"+
    "\12\0\47\5\2\10\1\5\1\10\1\5\5\10\1\5"+
    "\30\10\1\5\1\10\1\5\12\10\1\0\2\10\11\0"+
    "\1\10\2\5\5\10\1\5\1\10\1\5\6\0\2\5"+
    "\12\0\2\5\4\10\40\5\1\10\27\5\2\0\6\5"+
    "\12\0\13\5\1\0\1\5\1\0\1\5\1\0\4\5"+
    "\2\0\10\10\1\5\44\10\4\5\24\0\1\5\2\0"+
    "\5\10\13\0\1\5\44\0\11\5\1\0\71\5\53\10"+
    "\24\0\1\10\12\0\6\5\6\10\4\0\4\10\3\0"+
    "\1\10\3\0\2\10\7\0\3\10\4\0\15\10\14\0"+
    "\1\10\17\0\2\5\46\10\1\5\1\10\5\5\1\10"+
    "\2\5\53\10\1\5\115\10\1\5\4\10\2\5\7\10"+
    "\1\5\1\10\1\5\4\10\2\5\51\10\1\5\4\10"+
    "\2\5\41\10\1\5\4\10\2\5\7\10\1\5\1\10"+
    "\1\5\4\10\2\5\17\10\1\5\71\10\1\5\4\10"+
    "\2\5\103\10\2\5\3\0\40\5\20\10\20\5\126\10"+
    "\2\5\6\10\3\5\u016c\10\2\5\21\10\1\5\32\10"+
    "\5\5\113\10\3\5\13\10\7\5\15\10\1\5\4\10"+
    "\3\0\13\5\22\10\3\0\13\5\22\10\2\0\14\5"+
    "\15\10\1\5\3\10\1\5\2\0\14\5\64\10\40\0"+
    "\3\5\1\10\3\5\2\10\1\0\2\5\12\0\41\5"+
    "\4\0\1\5\12\0\6\5\131\10\7\5\5\10\2\0"+
    "\42\10\1\0\1\10\5\5\106\10\12\5\37\10\1\5"+
    "\14\0\4\5\14\0\12\5\12\0\36\10\2\5\5\10"+
    "\13\5\54\10\4\5\32\10\6\5\12\0\46\5\27\10"+
    "\5\0\4\5\65\10\12\0\1\5\35\0\2\5\13\0"+
    "\6\5\12\0\15\5\1\10\10\5\16\0\1\5\2\0"+
    "\77\5\5\0\57\10\21\0\7\10\4\5\12\0\21\5"+
    "\11\0\14\5\3\0\36\10\15\0\2\10\12\0\54\10"+
    "\16\0\14\5\44\10\24\0\10\5\12\0\3\5\3\10"+
    "\12\0\44\10\2\5\11\10\7\5\53\10\2\5\3\10"+
    "\20\5\3\0\1\5\25\0\4\10\1\0\6\10\1\0"+
    "\2\10\3\0\1\10\5\5\300\10\72\0\1\5\5\0"+
    "\26\10\2\5\6\10\2\5\46\10\2\5\6\10\2\5"+
    "\10\10\1\5\1\10\1\5\1\10\1\5\1\10\1\5"+
    "\37\10\2\5\65\10\1\5\7\10\1\5\1\10\3\5"+
    "\3\10\1\5\7\10\3\5\4\10\2\5\6\10\4\5"+
    "\15\10\5\5\3\10\1\5\7\10\16\5\5\0\30\5"+
    "\2\3\5\0\20\5\2\10\23\5\1\10\13\5\5\0"+
    "\1\5\12\0\1\5\1\10\15\5\1\10\20\5\15\10"+
    "\3\5\40\10\20\5\15\0\4\5\1\0\3\5\14\0"+
    "\21\5\1\10\4\5\1\10\2\5\12\10\1\5\1\10"+
    "\3\5\5\10\6\5\1\10\1\5\1\10\1\5\1\10"+
    "\1\5\4\10\1\5\13\10\2\5\4\10\5\5\5\10"+
    "\4\5\1\10\21\5\51\10\u0177\5\57\10\1\5\57\10"+
    "\1\5\205\10\6\5\4\10\3\0\2\10\14\5\46\10"+
    "\1\5\1\10\5\5\1\10\2\5\70\10\7\5\1\10"+
    "\17\5\1\0\27\10\11\5\7\10\1\5\7\10\1\5"+
    "\7\10\1\5\7\10\1\5\7\10\1\5\7\10\1\5"+
    "\7\10\1\5\7\10\1\5\40\0\57\5\1\10\325\5"+
    "\3\10\31\5\11\10\6\0\1\5\5\10\2\5\5\10"+
    "\4\5\126\10\2\5\2\0\2\5\3\10\1\5\132\10"+
    "\1\5\4\10\5\5\53\10\1\5\136\10\21\5\40\10"+
    "\60\5\320\10\100\5\375\10\3\5\215\10\103\5\56\10"+
    "\2\5\15\10\3\5\20\10\12\0\2\10\24\5\57\10"+
    "\1\0\4\5\12\0\1\5\37\10\2\0\120\10\2\0"+
    "\45\5\11\10\2\5\147\10\2\5\65\10\2\5\11\10"+
    "\52\5\15\10\1\0\3\10\1\0\4\10\1\0\27\10"+
    "\5\0\4\5\1\0\13\5\1\10\7\5\64\10\14\5"+
    "\2\0\62\10\22\0\12\5\12\0\6\5\22\0\6\10"+
    "\3\5\1\10\1\5\2\10\13\0\34\10\10\0\2\5"+
    "\27\10\15\0\14\5\35\10\3\5\4\0\57\10\16\0"+
    "\16\5\1\10\12\0\6\5\5\10\1\0\12\10\12\0"+
    "\5\10\1\5\51\10\16\0\11\5\3\10\1\0\10\10"+
    "\2\0\2\5\12\0\6\5\27\10\3\5\1\10\3\0"+
    "\62\10\1\0\1\10\3\0\2\10\2\0\5\10\2\0"+
    "\1\10\1\0\1\10\30\5\3\10\2\5\13\10\5\0"+
    "\2\5\3\10\2\0\12\5\6\10\2\5\6\10\2\5"+
    "\6\10\11\5\7\10\1\5\7\10\1\5\53\10\1\5"+
    "\16\10\6\5\163\10\10\0\1\5\2\0\2\5\12\0"+
    "\6\5\244\10\14\5\27\10\4\5\61\10\4\5\u0100\3"+
    "\156\10\2\5\152\10\46\5\7\10\14\5\5\10\5\5"+
    "\1\10\1\0\12\10\1\5\15\10\1\5\5\10\1\5"+
    "\1\10\1\5\2\10\1\5\2\10\1\5\154\10\41\5"+
    "\153\10\22\5\100\10\2\5\66\10\50\5\15\10\3\5"+
    "\20\0\20\5\20\0\3\5\2\10\30\5\3\10\31\5"+
    "\1\10\6\5\5\10\1\5\207\10\2\5\1\0\4\5"+
    "\1\10\13\5\12\0\7\5\32\10\4\5\1\10\1\5"+
    "\32\10\13\5\131\10\3\5\6\10\2\5\6\10\2\5"+
    "\6\10\2\5\3\10\3\5\2\10\3\5\2\10\22\5"+
    "\3\0\4\5\14\10\1\5\32\10\1\5\23\10\1\5"+
    "\2\10\1\5\17\10\2\5\16\10\42\5\173\10\105\5"+
    "\65\10\210\5\1\0\202\5\35\10\3\5\61\10\17\5"+
    "\1\0\37\5\40\10\15\5\36\10\5\5\46\10\5\0"+
    "\5\5\36\10\2\5\44\10\4\5\10\10\1\5\5\10"+
    "\52\5\236\10\2\5\12\0\6\5\44\10\4\5\44\10"+
    "\4\5\50\10\10\5\64\10\234\5\67\10\11\5\26\10"+
    "\12\5\10\10\230\5\6\10\2\5\1\10\1\5\54\10"+
    "\1\5\2\10\3\5\1\10\2\5\27\10\12\5\27\10"+
    "\11\5\37\10\101\5\23\10\1\5\2\10\12\5\26\10"+
    "\12\5\32\10\106\5\70\10\6\5\2\10\100\5\1\10"+
    "\3\0\1\5\2\0\5\5\4\0\4\10\1\5\3\10"+
    "\1\5\35\10\2\5\3\0\4\5\1\0\40\5\35\10"+
    "\3\5\35\10\43\5\10\10\1\5\34\10\2\0\31\5"+
    "\66\10\12\5\26\10\12\5\23\10\15\5\22\10\156\5"+
    "\111\10\67\5\63\10\15\5\63\10\15\5\44\10\4\0"+
    "\10\5\12\0\u0146\5\52\10\1\5\2\0\3\5\2\10"+
    "\116\5\35\10\12\5\1\10\10\5\26\10\13\0\137\5"+
    "\25\10\33\5\27\10\11\5\3\0\65\10\17\0\37\5"+
    "\12\0\17\5\4\0\55\10\13\0\2\5\1\0\17\5"+
    "\1\0\2\5\31\10\7\5\12\0\6\5\3\0\44\10"+
    "\16\0\1\5\12\0\4\5\1\10\2\0\1\10\10\5"+
    "\43\10\1\0\2\5\1\10\11\5\3\0\60\10\16\0"+
    "\4\10\4\5\4\0\1\5\14\0\1\10\1\5\1\10"+
    "\43\5\22\10\1\5\31\10\14\0\6\5\1\0\101\5"+
    "\7\10\1\5\1\10\1\5\4\10\1\5\17\10\1\5"+
    "\12\10\7\5\57\10\14\0\5\5\12\0\6\5\4\0"+
    "\1\5\10\10\2\5\2\10\2\5\26\10\1\5\7\10"+
    "\1\5\2\10\1\5\5\10\1\5\2\0\1\10\7\0"+
    "\2\5\2\0\2\5\3\0\2\5\1\10\6\5\1\0"+
    "\5\5\5\10\2\0\2\5\7\0\3\5\5\0\213\5"+
    "\65\10\22\0\4\10\5\5\12\0\4\5\1\0\3\10"+
    "\36\5\60\10\24\0\2\10\1\5\1\10\10\5\12\0"+
    "\246\5\57\10\7\0\2\5\11\0\27\5\4\10\2\0"+
    "\42\5\60\10\21\0\3\5\1\10\13\5\12\0\46\5"+
    "\53\10\15\0\1\10\7\5\12\0\66\5\33\10\2\5"+
    "\17\0\4\5\12\0\306\5\54\10\17\0\145\5\100\10"+
    "\12\0\25\5\10\10\2\5\1\10\2\5\10\10\1\5"+
    "\2\10\1\5\30\10\6\0\1\5\2\0\2\5\4\0"+
    "\1\10\1\0\1\10\2\0\14\5\12\0\106\5\10\10"+
    "\2\5\47\10\7\0\2\5\7\0\1\10\1\5\1\10"+
    "\1\0\33\5\1\10\12\0\50\10\7\0\1\10\4\0"+
    "\10\5\1\0\10\5\1\10\13\0\56\10\20\0\3\5"+
    "\1\10\42\5\71\10\7\5\11\10\1\5\45\10\10\0"+
    "\1\5\10\0\1\10\17\5\12\0\30\5\36\10\2\5"+
    "\26\0\1\5\16\0\111\5\7\10\1\5\2\10\1\5"+
    "\46\10\6\0\3\5\1\0\1\5\2\0\1\5\7\0"+
    "\1\10\1\0\10\5\12\0\6\5\6\10\1\5\2\10"+
    "\1\5\40\10\5\0\1\5\2\0\1\5\5\0\1\10"+
    "\7\5\12\0\u0136\5\23\10\4\0\271\5\1\10\54\5"+
    "\4\10\37\5\232\10\146\5\157\10\21\5\304\10\274\5"+
    "\57\10\1\5\11\0\307\5\107\10\271\5\71\10\7\5"+
    "\37\10\1\5\12\0\146\5\36\10\2\5\5\0\13\5"+
    "\60\10\7\0\11\5\4\10\14\5\12\0\11\5\25\10"+
    "\5\5\23\10\260\5\100\10\200\5\113\10\4\5\1\0"+
    "\1\10\67\0\7\5\4\0\15\10\100\5\2\10\1\5"+
    "\1\10\1\0\13\5\2\0\16\5\370\10\10\5\326\10"+
    "\52\5\11\10\367\5\37\10\61\5\3\10\21\5\4\10"+
    "\10\5\u018c\10\4\5\153\10\5\5\15\10\3\5\11\10"+
    "\7\5\12\10\3\5\2\0\1\5\4\0\301\5\5\0"+
    "\3\5\26\0\2\5\7\0\36\5\4\0\224\5\3\0"+
    "\273\5\125\10\1\5\107\10\1\5\2\10\2\5\1\10"+
    "\2\5\2\10\2\5\4\10\1\5\14\10\1\5\1\10"+
    "\1\5\7\10\1\5\101\10\1\5\4\10\2\5\10\10"+
    "\1\5\7\10\1\5\34\10\1\5\4\10\1\5\5\10"+
    "\1\5\1\10\3\5\7\10\1\5\u0154\10\2\5\31\10"+
    "\1\5\31\10\1\5\37\10\1\5\31\10\1\5\37\10"+
    "\1\5\31\10\1\5\37\10\1\5\31\10\1\5\37\10"+
    "\1\5\31\10\1\5\10\10\2\5\151\0\4\5\62\0"+
    "\10\5\1\0\16\5\1\0\26\5\5\0\1\5\17\0"+
    "\120\5\7\0\1\5\21\0\2\5\7\0\1\5\2\0"+
    "\1\5\5\0\325\5\55\10\3\5\7\0\7\10\2\5"+
    "\12\0\4\5\1\10\u0171\5\54\10\16\0\5\5\306\10"+
    "\13\5\7\0\51\5\104\10\7\0\1\10\4\5\12\0"+
    "\u0156\5\1\10\117\5\4\10\1\5\33\10\1\5\2\10"+
    "\1\5\1\10\2\5\1\10\1\5\12\10\1\5\4\10"+
    "\1\5\1\10\1\5\1\10\6\5\1\10\4\5\1\10"+
    "\1\5\1\10\1\5\1\10\1\5\3\10\1\5\2\10"+
    "\1\5\1\10\2\5\1\10\1\5\1\10\1\5\1\10"+
    "\1\5\1\10\1\5\1\10\1\5\2\10\1\5\1\10"+
    "\2\5\4\10\1\5\7\10\1\5\4\10\1\5\4\10"+
    "\1\5\1\10\1\5\12\10\1\5\21\10\5\5\3\10"+
    "\1\5\5\10\1\5\21\10\u0134\5\12\0\6\5\336\10"+
    "\42\5\65\10\13\5\336\10\2\5\u0182\10\16\5\u0131\10"+
    "\37\5\36\10\342\5\113\10\266\5\1\0\36\5\140\0"+
    "\200\5\360\0\20\5";

  private static int [] zzUnpackcmap_blocks() {
    int [] result = new int[31232];
    int offset = 0;
    offset = zzUnpackcmap_blocks(ZZ_CMAP_BLOCKS_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackcmap_blocks(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }

  /**
   * Translates DFA states to action switch labels.
   */
  private static final int [] ZZ_ACTION = zzUnpackAction();

  private static final String ZZ_ACTION_PACKED_0 =
    "\4\0\1\1\1\0\1\2\1\3\1\4\1\5\1\4"+
    "\1\2\1\6\2\7\1\10\1\11\15\12\1\1\1\3"+
    "\1\13\1\14\33\12\1\15\20\12\1\16\1\12\1\17"+
    "\1\20\1\21\4\12\1\22\10\12\1\23\3\12\1\24"+
    "\1\25\1\12\1\26\1\27\2\12\1\30\2\12\1\31"+
    "\1\32\1\12\1\33\1\34";

  private static int [] zzUnpackAction() {
    int [] result = new int[115];
    int offset = 0;
    offset = zzUnpackAction(ZZ_ACTION_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAction(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /**
   * Translates a state to a row index in the transition table
   */
  private static final int [] ZZ_ROWMAP = zzUnpackRowMap();

  private static final String ZZ_ROWMAP_PACKED_0 =
    "\0\0\0\37\0\76\0\135\0\174\0\233\0\272\0\331"+
    "\0\272\0\272\0\370\0\u0117\0\272\0\272\0\u0136\0\272"+
    "\0\u0155\0\u0174\0\u0193\0\u01b2\0\u01d1\0\u01f0\0\u020f\0\u022e"+
    "\0\u024d\0\u026c\0\u028b\0\u02aa\0\u02c9\0\u02e8\0\u0307\0\u0326"+
    "\0\u0345\0\u0117\0\u0364\0\u0383\0\u03a2\0\u03c1\0\u03e0\0\u03ff"+
    "\0\u041e\0\u043d\0\u045c\0\u047b\0\u049a\0\u04b9\0\u04d8\0\u04f7"+
    "\0\u0516\0\u0535\0\u0554\0\u0573\0\u0592\0\u05b1\0\u05d0\0\u05ef"+
    "\0\u060e\0\u062d\0\u064c\0\u066b\0\u068a\0\u0174\0\u06a9\0\u06c8"+
    "\0\u06e7\0\u0706\0\u0725\0\u0744\0\u0763\0\u0782\0\u07a1\0\u07c0"+
    "\0\u07df\0\u07fe\0\u081d\0\u083c\0\u085b\0\u087a\0\u0174\0\u0899"+
    "\0\u0174\0\u0174\0\u0174\0\u08b8\0\u08d7\0\u08f6\0\u0915\0\u0174"+
    "\0\u0934\0\u0953\0\u0972\0\u0991\0\u09b0\0\u09cf\0\u09ee\0\u0a0d"+
    "\0\u0174\0\u0a2c\0\u0a4b\0\u0a6a\0\u0174\0\u0174\0\u0a89\0\u0174"+
    "\0\u0174\0\u0aa8\0\u0ac7\0\u0174\0\u0ae6\0\u0b05\0\u0174\0\u0174"+
    "\0\u0b24\0\u0174\0\u0174";

  private static int [] zzUnpackRowMap() {
    int [] result = new int[115];
    int offset = 0;
    offset = zzUnpackRowMap(ZZ_ROWMAP_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackRowMap(String packed, int offset, int [] result) {
    int i = 0;  /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length() - 1;
    while (i < l) {
      int high = packed.charAt(i++) << 16;
      result[j++] = high | packed.charAt(i++);
    }
    return j;
  }

  /**
   * The transition table of the DFA
   */
  private static final int [] ZZ_TRANS = zzUnpacktrans();

  private static final String ZZ_TRANS_PACKED_0 =
    "\1\7\1\10\1\11\1\12\1\13\1\7\1\10\2\7"+
    "\1\14\1\15\23\7\2\12\1\10\1\16\1\12\1\17"+
    "\1\12\1\10\1\20\1\21\1\12\1\20\23\21\2\12"+
    "\1\10\1\16\1\12\1\17\1\12\1\10\31\12\1\10"+
    "\1\16\1\12\1\17\1\12\1\10\1\12\1\22\2\12"+
    "\1\23\1\22\1\24\2\22\1\25\1\26\1\22\1\27"+
    "\1\30\1\22\1\31\1\32\1\33\1\34\1\35\1\22"+
    "\1\36\1\22\1\12\1\37\1\10\1\16\1\37\1\17"+
    "\1\37\1\40\30\37\1\12\1\10\1\16\1\12\1\17"+
    "\1\12\1\10\1\12\1\41\2\12\23\41\1\12\40\0"+
    "\1\10\4\0\1\10\32\0\1\11\45\0\1\42\27\0"+
    "\1\16\34\0\1\21\7\0\1\21\2\0\24\21\1\22"+
    "\7\0\1\22\2\0\25\22\7\0\1\22\2\0\10\22"+
    "\1\43\14\22\7\0\1\22\2\0\10\22\1\44\14\22"+
    "\7\0\1\22\2\0\7\22\1\45\15\22\7\0\1\22"+
    "\2\0\4\22\1\46\20\22\7\0\1\22\2\0\1\47"+
    "\24\22\7\0\1\22\2\0\13\22\1\50\11\22\7\0"+
    "\1\22\2\0\21\22\1\51\3\22\7\0\1\22\2\0"+
    "\1\52\14\22\1\53\2\22\1\54\4\22\7\0\1\22"+
    "\2\0\4\22\1\55\20\22\7\0\1\22\2\0\4\22"+
    "\1\56\13\22\1\57\4\22\7\0\1\22\2\0\22\22"+
    "\1\60\2\22\7\0\1\22\2\0\1\61\23\22\1\37"+
    "\2\0\1\37\1\0\33\37\1\10\1\0\1\37\1\0"+
    "\1\37\1\40\30\37\1\41\7\0\1\41\2\0\24\41"+
    "\1\22\7\0\1\22\2\0\7\22\1\62\15\22\7\0"+
    "\1\22\2\0\1\63\24\22\7\0\1\22\2\0\4\22"+
    "\1\64\20\22\7\0\1\22\2\0\12\22\1\65\12\22"+
    "\7\0\1\22\2\0\12\22\1\66\12\22\7\0\1\22"+
    "\2\0\3\22\1\67\21\22\7\0\1\22\2\0\4\22"+
    "\1\70\20\22\7\0\1\22\2\0\15\22\1\71\7\22"+
    "\7\0\1\22\2\0\7\22\1\72\3\22\1\73\11\22"+
    "\7\0\1\22\2\0\1\22\1\74\23\22\7\0\1\22"+
    "\2\0\17\22\1\75\5\22\7\0\1\22\2\0\4\22"+
    "\1\76\20\22\7\0\1\22\2\0\14\22\1\77\10\22"+
    "\7\0\1\22\2\0\14\22\1\100\10\22\7\0\1\22"+
    "\2\0\15\22\1\101\7\22\7\0\1\22\2\0\1\102"+
    "\24\22\7\0\1\22\2\0\16\22\1\103\6\22\7\0"+
    "\1\22\2\0\10\22\1\104\14\22\7\0\1\22\2\0"+
    "\4\22\1\105\20\22\7\0\1\22\2\0\6\22\1\106"+
    "\16\22\7\0\1\22\2\0\20\22\1\107\4\22\7\0"+
    "\1\22\2\0\15\22\1\110\7\22\7\0\1\22\2\0"+
    "\1\111\24\22\7\0\1\22\2\0\21\22\1\112\3\22"+
    "\7\0\1\22\2\0\17\22\1\113\5\22\7\0\1\22"+
    "\2\0\10\22\1\114\14\22\7\0\1\22\2\0\20\22"+
    "\1\115\4\22\7\0\1\22\2\0\14\22\1\116\10\22"+
    "\7\0\1\22\2\0\4\22\1\117\20\22\7\0\1\22"+
    "\2\0\1\120\24\22\7\0\1\22\2\0\16\22\1\121"+
    "\6\22\7\0\1\22\2\0\16\22\1\122\6\22\7\0"+
    "\1\22\2\0\3\22\1\123\21\22\7\0\1\22\2\0"+
    "\15\22\1\124\7\22\7\0\1\22\2\0\20\22\1\125"+
    "\4\22\7\0\1\22\2\0\10\22\1\126\14\22\7\0"+
    "\1\22\2\0\10\22\1\127\14\22\7\0\1\22\2\0"+
    "\11\22\1\130\13\22\7\0\1\22\2\0\1\131\24\22"+
    "\7\0\1\22\2\0\4\22\1\132\20\22\7\0\1\22"+
    "\2\0\7\22\1\133\15\22\7\0\1\22\2\0\15\22"+
    "\1\134\7\22\7\0\1\22\2\0\15\22\1\135\7\22"+
    "\7\0\1\22\2\0\15\22\1\136\7\22\7\0\1\22"+
    "\2\0\7\22\1\137\15\22\7\0\1\22\2\0\1\140"+
    "\24\22\7\0\1\22\2\0\4\22\1\141\20\22\7\0"+
    "\1\22\2\0\13\22\1\142\11\22\7\0\1\22\2\0"+
    "\17\22\1\143\5\22\7\0\1\22\2\0\2\22\1\144"+
    "\22\22\7\0\1\22\2\0\2\22\1\145\22\22\7\0"+
    "\1\22\2\0\12\22\1\146\12\22\7\0\1\22\2\0"+
    "\4\22\1\147\20\22\7\0\1\22\2\0\6\22\1\150"+
    "\16\22\7\0\1\22\2\0\2\22\1\151\22\22\7\0"+
    "\1\22\2\0\6\22\1\152\16\22\7\0\1\22\2\0"+
    "\1\153\24\22\7\0\1\22\2\0\4\22\1\154\20\22"+
    "\7\0\1\22\2\0\17\22\1\155\5\22\7\0\1\22"+
    "\2\0\16\22\1\156\6\22\7\0\1\22\2\0\4\22"+
    "\1\157\20\22\7\0\1\22\2\0\3\22\1\160\21\22"+
    "\7\0\1\22\2\0\4\22\1\161\20\22\7\0\1\22"+
    "\2\0\16\22\1\162\6\22\7\0\1\22\2\0\3\22"+
    "\1\163\20\22";

  private static int [] zzUnpacktrans() {
    int [] result = new int[2883];
    int offset = 0;
    offset = zzUnpacktrans(ZZ_TRANS_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpacktrans(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      value--;
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /* error codes */
  private static final int ZZ_UNKNOWN_ERROR = 0;
  private static final int ZZ_NO_MATCH = 1;
  private static final int ZZ_PUSHBACK_2BIG = 2;

  /* error messages for the codes above */
  private static final String[] ZZ_ERROR_MSG = {
    "Unknown internal scanner error",
    "Error: could not match input",
    "Error: pushback value was too large"
  };

  /**
   * ZZ_ATTRIBUTE[aState] contains the attributes of state {@code aState}
   */
  private static final int [] ZZ_ATTRIBUTE = zzUnpackAttribute();

  private static final String ZZ_ATTRIBUTE_PACKED_0 =
    "\4\0\1\1\1\0\1\11\1\1\2\11\2\1\2\11"+
    "\1\1\1\11\143\1";

  private static int [] zzUnpackAttribute() {
    int [] result = new int[115];
    int offset = 0;
    offset = zzUnpackAttribute(ZZ_ATTRIBUTE_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAttribute(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }

  /** the input device */
  private java.io.Reader zzReader;

  /** the current state of the DFA */
  private int zzState;

  /** the current lexical state */
  private int zzLexicalState = YYINITIAL;

  /** this buffer contains the current text to be matched and is
      the source of the yytext() string */
  private CharSequence zzBuffer = "";

  /** the textposition at the last accepting state */
  private int zzMarkedPos;

  /** the current text position in the buffer */
  private int zzCurrentPos;

  /** startRead marks the beginning of the yytext() string in the buffer */
  private int zzStartRead;

  /** endRead marks the last character in the buffer, that has been read
      from input */
  private int zzEndRead;

  /** zzAtEOF == true <=> the scanner is at the EOF */
  private boolean zzAtEOF;

  /** Number of newlines encountered up to the start of the matched text. */
  @SuppressWarnings("unused")
  private int yyline;

  /** Number of characters from the last newline up to the start of the matched text. */
  @SuppressWarnings("unused")
  protected int yycolumn;

  /** Number of characters up to the start of the matched text. */
  @SuppressWarnings("unused")
  private long yychar;

  /** Whether the scanner is currently at the beginning of a line. */
  @SuppressWarnings("unused")
  private boolean zzAtBOL = true;

  /** Whether the user-EOF-code has already been executed. */
  private boolean zzEOFDone;

  /* user code: */
    private int _typeLevel = 0;
    private boolean _typeReq = false;
    public _LuaDocLexer() {
        this((java.io.Reader) null);
    }

    private void beginType() {
        _typeLevel = 0;
        _typeReq = true;
    }


  /**
   * Creates a new scanner
   *
   * @param   in  the java.io.Reader to read input from.
   */
  public _LuaDocLexer(java.io.Reader in) {
    this.zzReader = in;
  }


  /** Returns the maximum size of the scanner buffer, which limits the size of tokens. */
  private int zzMaxBufferLen() {
    return Integer.MAX_VALUE;
  }

  /**  Whether the scanner buffer can grow to accommodate a larger token. */
  private boolean zzCanGrow() {
    return true;
  }

  /**
   * Translates raw input code points to DFA table row
   */
  private static int zzCMap(int input) {
    int offset = input & 255;
    return offset == input ? ZZ_CMAP_BLOCKS[offset] : ZZ_CMAP_BLOCKS[ZZ_CMAP_TOP[input >> 8] | offset];
  }

  public final int getTokenStart() {
    return zzStartRead;
  }

  public final int getTokenEnd() {
    return getTokenStart() + yylength();
  }

  public void reset(CharSequence buffer, int start, int end, int initialState) {
    zzBuffer = buffer;
    zzCurrentPos = zzMarkedPos = zzStartRead = start;
    zzAtEOF  = false;
    zzAtBOL = true;
    zzEndRead = end;
    yybegin(initialState);
  }

  /**
   * Refills the input buffer.
   *
   * @return      {@code false}, iff there was new input.
   *
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  private boolean zzRefill() throws java.io.IOException {
    return true;
  }


  /**
   * Returns the current lexical state.
   */
  public final int yystate() {
    return zzLexicalState;
  }


  /**
   * Enters a new lexical state
   *
   * @param newState the new lexical state
   */
  public final void yybegin(int newState) {
    zzLexicalState = newState;
  }


  /**
   * Returns the text matched by the current regular expression.
   */
  public final CharSequence yytext() {
    return zzBuffer.subSequence(zzStartRead, zzMarkedPos);
  }


  /**
   * Returns the character at position {@code pos} from the
   * matched text.
   *
   * It is equivalent to yytext().charAt(pos), but faster
   *
   * @param pos the position of the character to fetch.
   *            A value from 0 to yylength()-1.
   *
   * @return the character at position pos
   */
  public final char yycharat(int pos) {
    return zzBuffer.charAt(zzStartRead+pos);
  }


  /**
   * Returns the length of the matched text region.
   */
  public final int yylength() {
    return zzMarkedPos-zzStartRead;
  }


  /**
   * Reports an error that occurred while scanning.
   *
   * In a wellformed scanner (no or only correct usage of
   * yypushback(int) and a match-all fallback rule) this method
   * will only be called with things that "Can't Possibly Happen".
   * If this method is called, something is seriously wrong
   * (e.g. a JFlex bug producing a faulty scanner etc.).
   *
   * Usual syntax/scanner level error handling should be done
   * in error fallback rules.
   *
   * @param   errorCode  the code of the errormessage to display
   */
  private void zzScanError(int errorCode) {
    String message;
    try {
      message = ZZ_ERROR_MSG[errorCode];
    }
    catch (ArrayIndexOutOfBoundsException e) {
      message = ZZ_ERROR_MSG[ZZ_UNKNOWN_ERROR];
    }

    throw new Error(message);
  }


  /**
   * Pushes the specified amount of characters back into the input stream.
   *
   * They will be read again by then next call of the scanning method
   *
   * @param number  the number of characters to be read again.
   *                This number must not be greater than yylength()!
   */
  public void yypushback(int number)  {
    if ( number > yylength() )
      zzScanError(ZZ_PUSHBACK_2BIG);

    zzMarkedPos -= number;
  }


  /**
   * Contains user EOF-code, which will be executed exactly once,
   * when the end of file is reached
   */
  private void zzDoEOF() {
    if (!zzEOFDone) {
      zzEOFDone = true;
    
    }
  }


  /**
   * Resumes scanning until the next regular expression is matched,
   * the end of input is encountered or an I/O-Error occurs.
   *
   * @return      the next token
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  public IElementType advance() throws java.io.IOException
  {
    int zzInput;
    int zzAction;

    // cached fields:
    int zzCurrentPosL;
    int zzMarkedPosL;
    int zzEndReadL = zzEndRead;
    CharSequence zzBufferL = zzBuffer;

    int [] zzTransL = ZZ_TRANS;
    int [] zzRowMapL = ZZ_ROWMAP;
    int [] zzAttrL = ZZ_ATTRIBUTE;

    while (true) {
      zzMarkedPosL = zzMarkedPos;

      zzAction = -1;

      zzCurrentPosL = zzCurrentPos = zzStartRead = zzMarkedPosL;

      zzState = ZZ_LEXSTATE[zzLexicalState];

      // set up zzAction for empty match case:
      int zzAttributes = zzAttrL[zzState];
      if ( (zzAttributes & 1) == 1 ) {
        zzAction = zzState;
      }


      zzForAction: {
        while (true) {

          if (zzCurrentPosL < zzEndReadL) {
            zzInput = Character.codePointAt(zzBufferL, zzCurrentPosL);
            zzCurrentPosL += Character.charCount(zzInput);
          }
          else if (zzAtEOF) {
            zzInput = YYEOF;
            break zzForAction;
          }
          else {
            // store back cached positions
            zzCurrentPos  = zzCurrentPosL;
            zzMarkedPos   = zzMarkedPosL;
            boolean eof = zzRefill();
            // get translated positions and possibly new buffer
            zzCurrentPosL  = zzCurrentPos;
            zzMarkedPosL   = zzMarkedPos;
            zzBufferL      = zzBuffer;
            zzEndReadL     = zzEndRead;
            if (eof) {
              zzInput = YYEOF;
              break zzForAction;
            }
            else {
              zzInput = Character.codePointAt(zzBufferL, zzCurrentPosL);
              zzCurrentPosL += Character.charCount(zzInput);
            }
          }
          int zzNext = zzTransL[ zzRowMapL[zzState] + zzCMap(zzInput) ];
          if (zzNext == -1) break zzForAction;
          zzState = zzNext;

          zzAttributes = zzAttrL[zzState];
          if ( (zzAttributes & 1) == 1 ) {
            zzAction = zzState;
            zzMarkedPosL = zzCurrentPosL;
            if ( (zzAttributes & 8) == 8 ) break zzForAction;
          }

        }
      }

      // store back cached position
      zzMarkedPos = zzMarkedPosL;

      if (zzInput == YYEOF && zzStartRead == zzCurrentPos) {
        zzAtEOF = true;
            zzDoEOF();
        return null;
      }
      else {
        switch (zzAction < 0 ? zzAction : ZZ_ACTION[zzAction]) {
          case 1:
            { yybegin(YYINITIAL); return STRING;
            }
          // fall through
          case 29: break;
          case 2:
            { yybegin(xCOMMENT_STRING); yypushback(yylength());
            }
          // fall through
          case 30: break;
          case 3:
            { return com.intellij.psi.TokenType.WHITE_SPACE;
            }
          // fall through
          case 31: break;
          case 4:
            { yybegin(YYINITIAL); return com.intellij.psi.TokenType.WHITE_SPACE;
            }
          // fall through
          case 32: break;
          case 5:
            { return com.intellij.psi.TokenType.BAD_CHARACTER;
            }
          // fall through
          case 33: break;
          case 6:
            { yybegin(xTAG_NAME); return AT;
            }
          // fall through
          case 34: break;
          case 7:
            { yybegin(YYINITIAL);return com.intellij.psi.TokenType.WHITE_SPACE;
            }
          // fall through
          case 35: break;
          case 8:
            { yybegin(xCOMMENT_STRING); return STRING_BEGIN;
            }
          // fall through
          case 36: break;
          case 9:
            { return ID;
            }
          // fall through
          case 37: break;
          case 10:
            { yybegin(xCOMMENT_STRING); return TAG_NAME;
            }
          // fall through
          case 38: break;
          case 11:
            { yybegin(xCOMMENT_STRING); return ID;
            }
          // fall through
          case 39: break;
          case 12:
            { return DASHES;
            }
          // fall through
          case 40: break;
          case 13:
            { yybegin(xCOMMENT_STRING); return TAG_NAME_SEE;
            }
          // fall through
          case 41: break;
          case 14:
            { yybegin(xCOMMENT_STRING); return TAG_NAME_TYPE;
            }
          // fall through
          case 42: break;
          case 15:
            { yybegin(xCOMMENT_STRING); return TAG_NAME_ALIAS;
            }
          // fall through
          case 43: break;
          case 16:
            { yybegin(xCOMMENT_STRING); return TAG_NAME_CLASS;
            }
          // fall through
          case 44: break;
          case 17:
            { yybegin(xCOMMENT_STRING); return TAG_NAME_FIELD;
            }
          // fall through
          case 45: break;
          case 18:
            { yybegin(xCOMMENT_STRING); return TAG_NAME_PARAM;
            }
          // fall through
          case 46: break;
          case 19:
            { yybegin(xCOMMENT_STRING); return TAG_NAME_MODULE;
            }
          // fall through
          case 47: break;
          case 20:
            { yybegin(xCOMMENT_STRING); return TAG_NAME_PUBLIC;
            }
          // fall through
          case 48: break;
          case 21:
            { yybegin(xCOMMENT_STRING); return TAG_NAME_RETURN;
            }
          // fall through
          case 49: break;
          case 22:
            { yybegin(xCOMMENT_STRING); return TAG_NAME_VARARG;
            }
          // fall through
          case 50: break;
          case 23:
            { yybegin(xCOMMENT_STRING); return TAG_NAME_GENERIC;
            }
          // fall through
          case 51: break;
          case 24:
            { yybegin(xCOMMENT_STRING); return TAG_NAME_PRIVATE;
            }
          // fall through
          case 52: break;
          case 25:
            { yybegin(xLANGUAGE); return TAG_NAME_LANGUAGE;
            }
          // fall through
          case 53: break;
          case 26:
            { yybegin(xCOMMENT_STRING); return TAG_NAME_OVERLOAD;
            }
          // fall through
          case 54: break;
          case 27:
            { yybegin(xCOMMENT_STRING); return TAG_NAME_SUPPRESS;
            }
          // fall through
          case 55: break;
          case 28:
            { yybegin(xCOMMENT_STRING); return TAG_NAME_PROTECTED;
            }
          // fall through
          case 56: break;
          default:
            zzScanError(ZZ_NO_MATCH);
          }
      }
    }
  }


}
