package dataStructures;

import java.util.List;

public class Trie {

    private TrieNode root;

    public Trie(List<String> dict) {
        this.root = new TrieNode(' ');
        constructTrie(dict);
    }

    private void constructTrie(List<String> dict) {
        for (String word : dict) {
            insert(word);
        }
    }
    public void insert(String word) {
        TrieNode node = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            int index = c - 'a';
            if (node.children[index] == null) {
                node.children[index] = new TrieNode(c);
            }
            if (i == word.length() - 1) {
                node.children[index].isWord = true;
            }
            node = node.children[index];
        }
    }
    public TrieNode search(String word) {
        TrieNode node = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            int index = c - 'a';
            if (node.children[index] != null) {
                if (i == word.length() - 1) {
                    return node.children[index];
                }
                node = node.children[index];
            } else {
                break;
            }
        }
        return null;
    }
}
