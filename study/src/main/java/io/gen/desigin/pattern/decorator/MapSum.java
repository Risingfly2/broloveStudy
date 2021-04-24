package io.gen.desigin.pattern.decorator;

public class MapSum {


    class TrieNode{
        TrieNode[] child = new TrieNode[26];
        int val;
    }
    private TrieNode root;

    /** Initialize your data structure here. */
    public MapSum() {
        root = new TrieNode();
    }

    public void insert(String key, int val) {
        TrieNode cur = root;
        for (char c: key.toCharArray()){
            int index = c - 'a';
            if (cur.child[index] == null){
                cur.child[index] = new TrieNode();
            }
            cur = cur.child[index];
        }
        cur.val = val;
    }


    public int sum(String prefix) {
        TrieNode cur = root;
        for (char c: prefix.toCharArray()){
            int index = c - 'a';
            if (cur.child[index] == null){
                return 0;
            }
            cur = cur.child[index];
        }
        return dfs(cur);
    }

    private int dfs(TrieNode node){
        int sum = 0;
        for (int i = 0; i < 26; i++) {
            if (node.child[i] != null){
                sum += dfs(node.child[i]);
            }
        }
        return sum + node.val;
    }
}
