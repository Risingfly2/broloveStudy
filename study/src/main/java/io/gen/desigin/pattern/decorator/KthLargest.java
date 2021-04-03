package io.gen.desigin.pattern.decorator;

import java.util.*;

public class KthLargest {

    private int k;
    private PriorityQueue<Integer> res;
    public KthLargest(int k, int[] nums) {
        this.k = k;
        res = new PriorityQueue<>(k);
        for (int num: nums){
            add(num);
        }
    }

    public int add(int val) {
        if (res.size() < k){
            res.offer(val);
        }else if (res.peek() < val){
            res.poll();
            res.offer(val);
        }
        return res.peek();
    }
}
