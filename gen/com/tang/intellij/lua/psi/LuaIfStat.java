// This is a generated file. Not intended for manual editing.
package com.tang.intellij.lua.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface LuaIfStat extends LuaStatement {

  @Nullable
  LuaElseClause getElseClause();

  @NotNull
  List<LuaElseifClause> getElseifClauseList();

  @Nullable
  LuaExpr getExpr();

}
