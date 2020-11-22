package io.gen.study;

public class MulOpt implements Node {

    private String left;
    private String right;
    private String op;

    public MulOpt(String left, String op, String right) {
        this.left = left;
        this.right = right;
        this.op = op;
    }

    public void validate() {
        if (left == null || right == null || op == null) {
            throw new IllegalArgumentException("operator should not be null");
        }
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
