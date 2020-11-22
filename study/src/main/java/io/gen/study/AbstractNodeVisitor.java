package io.gen.study;

public abstract class AbstractNodeVisitor implements NodeVisitor{

    @Override
    public void visit(MulOpt node) {
        throw new IllegalArgumentException("must implements the visit MulOpt method");
    }

    public void visit(AddOpt node){
        throw new IllegalArgumentException("must implements the visit AddOpt method");
    }
}
