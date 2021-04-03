package io.gen.desigin.pattern.decorator;

import java.util.HashMap;

public class LRUCache {
    class DLinkedNode{
        int key;
        int value;
        DLinkedNode pre;
        DLinkedNode post;
    }

    private HashMap<Integer, DLinkedNode> cache = new HashMap<>();
    private int count;
    private int capacity;
    private DLinkedNode head, tail;

    public LRUCache(int capacity){
        this.count = 0;
        this.capacity = capacity;

        head = new DLinkedNode();
        head.pre = null;

        tail = new DLinkedNode();
        tail.post = null;

        head.post = tail;
        tail.pre = head;
    }

    public int get(int key){
        DLinkedNode node = cache.get(key);
        if (node == null){
            return -1;
        }
        this.moveToHead(node);
        return node.value;
    }

    public void moveToHead(DLinkedNode node){
        this.removeNode(node);
        this.addNode(node);
    }

    public void removeNode(DLinkedNode node){
        DLinkedNode pre = node.pre;
        DLinkedNode post = node.post;
        pre.post = post;
        post.pre = pre;
    }

    public void addNode(DLinkedNode node){
        node.pre = head;
        node.post = head.post;

        head.post.pre = node;
        head.post = node;
    }

    public void put(int key, int value){
        DLinkedNode node = cache.get(key);
        if (node == null){
            DLinkedNode newNode = new DLinkedNode();
            newNode.key = key;
            newNode.value = value;
            this.cache.put(key, newNode);
            this.addNode(newNode);
            ++count;
            if (count > capacity){
                DLinkedNode tail = this.popTail();
                this.cache.remove(tail.key);
                --count;
            }
        }else {
            node.value = value;
            this.moveToHead(node);
        }
    }

    public DLinkedNode popTail(){
        DLinkedNode res = tail.pre;
        this.removeNode(res);
        return res;
    }
}
