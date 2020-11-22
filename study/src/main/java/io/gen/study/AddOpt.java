package io.gen.study;

public class AddOpt implements Node{

    private String left;
    private String right;
    private String op;

    public AddOpt(String left, String op, String right) {
        this.left = left;
        this.right = right;
        this.op = op;
    }

    @Override
    public void accept(NodeVisitor visitor) {
        visitor.visit(this);
    }

    public String getLeft() {
        return left;
    }

    public String getRight() {
        return right;
    }

    public String getOp() {
        return op;
    }
}
