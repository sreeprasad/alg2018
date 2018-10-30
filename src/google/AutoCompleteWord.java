package google;

import dataStructures.Trie;
import dataStructures.TrieNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 你有一个词典(new, to, tonight, total, today, tasd,rfe,ref,wef,hn,wfwe)，就是一个人输入几个字母(相当于prefix）,
 * 然后你要返回一堆数，然后预测用户想要哪个数，比如你输入to,然后应该返回to, tonight, total, today
 *
 * 思路是先构建字典树，然后找到prefix的点，然后用dfs找出所有的子节点
 */
public class AutoCompleteWord {

    private Trie trie;

    public AutoCompleteWord(List<String> dict) {
        trie = new Trie(dict);
    }
    private List<String> autoComplete(String input) {
        List<String> output = new ArrayList<>();
        TrieNode node = trie.search(input);
        dfs(output, input, node);
        return output;
    }
    private void dfs(List<String> output, String path, TrieNode node) {
        if (node == null) {
            return;
        }
        if (node.isWord) {
            output.add(path);
        }
        for (TrieNode child : node.children) {
            if (child != null) {
                dfs(output, path + child.value, child);
            }
        }
    }
    public static void main(String[] args) {
        List<String> dict = Arrays.asList("new", "to", "tonight", "total", "today", "tasd", "rfe", "ref", "wef", "hn", "wfwe");
        AutoCompleteWord acw = new AutoCompleteWord(dict);
        System.out.println(acw.autoComplete("to"));
        System.out.println(acw.autoComplete("w"));
    }
}