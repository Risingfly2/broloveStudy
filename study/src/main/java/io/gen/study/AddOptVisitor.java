package io.gen.study;

public class AddOptVisitor extends AbstractNodeVisitor{

    @Override
    public void visit(AddOpt node) {
        int left = Integer.parseInt(node.getLeft());
        int right = Integer.parseInt(node.getRight());
        if ("+".equals(node.getOp())) {
            System.out.println("add res = " + (left + right));
        }
        if ("-".equals(node.getOp())) {
            System.out.println("minus res= " + (left - right));
        }
    }
}
