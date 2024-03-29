// This is a generated file. Not intended for manual editing.
package com.tang.intellij.lua.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static com.tang.intellij.lua.psi.LuaTypes.*;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.tang.intellij.lua.psi.*;

public class LuaNameListImpl extends ASTWrapperPsiElement implements LuaNameList {

  public LuaNameListImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull LuaVisitor visitor) {
    visitor.visitNameList(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof LuaVisitor) accept((LuaVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<LuaAttribute> getAttributeList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, LuaAttribute.class);
  }

  @Override
  @NotNull
  public List<LuaNameDef> getNameDefList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, LuaNameDef.class);
  }

}
