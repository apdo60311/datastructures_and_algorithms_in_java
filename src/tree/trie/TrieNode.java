package tree.trie;

import java.util.HashMap;
import java.util.Map;

public class TrieNode {
    final char character;
    boolean isEndOfWord;
    Map<Character , TrieNode> children = new HashMap<>();

    public TrieNode(char character) {
        this.character = character;
        isEndOfWord = false;
    }
}
