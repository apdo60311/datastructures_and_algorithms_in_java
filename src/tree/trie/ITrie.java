package tree.trie;

import java.util.List;

public interface ITrie {
    ITrie insert(String word);
    boolean contains(String word);
    boolean containsPrefix(String prefix);
    List<String> wordsWithPrefix(String prefix);
    void delete(String word);
}
