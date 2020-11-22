package io.gen.study;

import io.gen.antlr.MathBaseVisitor;
import io.gen.antlr.MathParser;


public class CalVisitor extends MathBaseVisitor<Node> {

    public MulOpt visitMulDiv(MathParser.MulDivContext ctx){
        String op = ctx.op.getText();
        String left = ctx.expr(0).getText();
        String right = ctx.expr(1).getText();
        return new MulOpt(left, op, right);
    }

    public AddOpt visitAddSub(MathParser.AddSubContext ctx){
        String op = ctx.op.getText();
        String left = ctx.expr(0).getText();
        String right = ctx.expr(1).getText();
        return new AddOpt(left, op, right);
    }
}
