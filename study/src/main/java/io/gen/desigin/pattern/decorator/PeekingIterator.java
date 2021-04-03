package io.gen.desigin.pattern.decorator;

import java.util.Iterator;

public class PeekingIterator implements Iterator<Integer> {
    private Iterator<Integer> iterator;
    Integer next;
    public PeekingIterator(Iterator<Integer> iterator) {
        // initialize any member here.
        this.iterator = iterator;
        if (this.iterator.hasNext()){
            next = this.iterator.next();
        }
    }

    // Returns the next element in the iteration without advancing the iterator.
    public Integer peek() {
        return next;
    }

    // hasNext() and next() should behave the same as in the Iterator interface.
    // Override them if needed.
    @Override
    public Integer next() {
        Integer res = next;
        next = iterator.hasNext() ? iterator.next() : null;
        return res;
    }

    @Override
    public boolean hasNext() {
        return next != null;
    }
}
