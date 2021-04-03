package io.gen.desigin.pattern.decorator;

public class MyHashMap {

    class Node{
        int key;
        int val;
        Node pre;
        Node next;
        public Node(int key, int val){
            this.key = key;
            this.val = val;
        }
    }
    private int size = 1000;
    private Node[] bucket;
    /** Initialize your data structure here. */
    public MyHashMap() {
        bucket = new Node[size];
    }

    /** value will always be non-negative. */
    public void put(int key, int value) {
        int index = hash(key);
        Node tmp = bucket[index];
        if (tmp != null){
            // has same key, directly wiped it
            if (tmp.key == key){
                tmp.val = value;
            }else {
                // key conflict use chain to solve it
                Node p = tmp;
                while (p.next != null){
                    p = p.next;
                }
                p.next = new Node(key, value);
            }
        }else {
            bucket[index] = new Node(key, value);
        }
    }

    /** Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key */
    public int get(int key) {
        int index = hash(key);
        Node tmp = bucket[index];
        if (tmp != null){
            if (tmp.key == key){
                return tmp.val;
            }else {
                Node p = tmp;
                while (p.next != null){
                    if (p.key == key){
                        return p.val;
                    }else {
                        p = p.next;
                    }
                }
            }
        }

        return -1;
    }

    /** Removes the mapping of the specified value key if this map contains a mapping for the key */
    public void remove(int key) {
        int index = hash(key);
        Node tmp = bucket[index];
        if (tmp != null){
            if (tmp.key == key){
                bucket[index] = null;
            }else {
                Node p = tmp;
                while (p.next != null){
                    if (p.key == key){
                        bucket[index] = null;
                        break;
                    }else {
                        p = p.next;
                    }
                }
            }
        }

    }

    private int hash(int key){
        return key % size;
    }
}
