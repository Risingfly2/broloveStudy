package io.gen.desigin.pattern.decorator;

public class Trie {

    public class TrieNode{
        public char data;
        public TrieNode[] children = new TrieNode[26];
        public boolean isEndingChar;
        public TrieNode(char data){
            this.data = data;
        }
    }
    TrieNode root = new TrieNode('/');

    public void insert(String word){
        char[] words = word.toCharArray();
        TrieNode p = root;
        for (char c: words){
            if (p.children[c - 'a'] == null){
                TrieNode node = new TrieNode(c);
                p.children[c - 'a'] = node;
            }
            p = p.children[c - 'a'];
        }
        p.isEndingChar = true;
    }

    public boolean search(String pattern){
        char[] patterns = pattern.toCharArray();
        TrieNode p = root;
        for (char c: patterns){
            if (p.children[c - 'a'] == null){
                return false;
            }
            p = p.children[c - 'a'];
        }
        return p.isEndingChar;
    }
}
