package io.shiftleft.fuzzyc2cpg.ast.expressions;

import io.shiftleft.fuzzyc2cpg.ast.walking.ASTNodeVisitor;

public class PrimaryExpression extends Expression {

  public void accept(ASTNodeVisitor visitor) {
    visitor.visit(this);
  }
}
