package dataStructures;

public class TrieNode {
    public char value;
    public boolean isWord;
    public TrieNode[] children;

    public TrieNode(char val) {
        this.value = val;
        isWord = false;
        this.children = new TrieNode[26];
    }
}