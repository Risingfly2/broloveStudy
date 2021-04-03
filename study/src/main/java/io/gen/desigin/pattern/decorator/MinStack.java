package io.gen.desigin.pattern.decorator;

import java.util.LinkedList;
import java.util.Stack;

public class MinStack {

    private LinkedList<Integer> list;
    private Stack<Integer> stack, min;

    public MinStack() {
        list = new LinkedList<>();
        stack = new Stack<>();
        min = new Stack<>();
    }

    public void push(int val) {
        list.push(val);
        stack.push(val);
        if (min.isEmpty()){
            min.push(val);
        }
        if (!min.isEmpty() && min.peek() >= val){
            min.push(val);
        }
    }

    public void pop() {
        list.pop();
        int tmp = stack.pop();
        if (tmp == min.peek()){
            min.pop();
        }
    }

    public int top() {
        if (!list.isEmpty()){
            return list.peek();
        }else {
            stack.peek();
            return -1;
        }

    }

    public int getMin() {

        int min = Integer.MAX_VALUE;
        for (int num: list){
            if (num < min){
                min = num;
            }
        }
        return min;
    }
}
