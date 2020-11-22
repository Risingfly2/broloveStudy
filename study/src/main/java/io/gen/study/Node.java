package io.gen.study;

public interface Node<T> {

    void accept(NodeVisitor visitor);
}
