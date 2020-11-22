package io.gen.study;

public class MulOptVisitor extends AbstractNodeVisitor{
    @Override
    public void visit(MulOpt node) {
        int left = Integer.parseInt(node.getLeft());
        int right = Integer.parseInt(node.getRight());
        if ("*".equals(node.getOp())) {
            System.out.println("multiply res = " + left * right);
        }
        if ("/".equals(node.getOp())) {
            System.out.println("div res= " + left / right);
        }
    }


}
