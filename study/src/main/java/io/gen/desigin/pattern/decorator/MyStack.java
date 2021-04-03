package io.gen.desigin.pattern.decorator;

import java.util.LinkedList;
import java.util.Queue;

public class MyStack {


    private Queue<Integer> in = new LinkedList<>();
//    private Queue<Integer> out = new LinkedList<>();

    /** Initialize your data structure here. */
    public MyStack() {

    }

    /** Push element x onto stack. */
    public void push(int x) {
        in.add(x);
        for (int i = 0; i < in.size() - 1; i++) {
            in.add(in.poll());
        }
    }

    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
        return in.remove();
    }

    /** Get the top element. */
    public int top() {

        return in.peek();
    }

    /** Returns whether the stack is empty. */
    public boolean empty() {

        return in.isEmpty();
    }
}
