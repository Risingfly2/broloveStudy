package io.gen;

public class Trie {

    public class TrieNode{
        private boolean isWord;
        private TrieNode[] children = new TrieNode[26];
        public TrieNode(boolean isWord){
            this.isWord = isWord;
        }

        public boolean isWord() {
            return isWord;
        }

        public void setWord(boolean word) {
            isWord = word;
        }
    }

    private TrieNode root = new TrieNode(false);
    /** Initialize your data structure here. */
    public Trie() {

    }

    /** Inserts a word into the trie. */
    public void insert(String word) {

        TrieNode p = root;
        for (char ch: word.toCharArray()){
            if (p.children[ch - 'a'] == null){
                p.children[ch - 'a'] = new TrieNode(false);
            }
            p = p.children[ch - 'a'];
        }
        p.setWord(true);
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {

        TrieNode p = root;
        for (char ch: word.toCharArray()){
            if (p.children[ch - 'a'] == null){
                return false;
            }
            p = p.children[ch - 'a'];
        }
        return p.isWord;
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {

        TrieNode p = root;
        for (char ch: prefix.toCharArray()){
            if (p.children[ch - 'a'] == null){
                return false;
            }
            p = p.children[ch - 'a'];
        }
        return true;
    }
// T second way to implement Trie   
//    private TrieNode root = new TrieNode('/');
//
//    public void insert(char[] text){
//        TrieNode p = root;
//        for (int i = 0; i < text.length; i++) {
//            int index = text[i] - 'a';
//            if (p.children[index] == null){
//                TrieNode newNode = new TrieNode(text[i]);
//                p.children[index] = newNode;
//            }
//            p = p.children[index];
//        }
//        p.isEndingChar = true;
//    }
//
//
//    public boolean find(char[] pattern){
//        TrieNode p = root;
//        for (int i = 0; i < pattern.length; i++) {
//            int index = pattern[i] - 'a';
//            if (p.children[index] == null){
//                return false;
//            }
//            p = p.children[index];
//        }
//        if (!p.isEndingChar){
//            return false;
//        }else {
//            return true;
//        }
//    }
}
