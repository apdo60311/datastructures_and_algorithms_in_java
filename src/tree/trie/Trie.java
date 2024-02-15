package tree.trie;

import java.util.*;
import java.util.function.BiConsumer;

public class Trie implements ITrie {

    TrieNode root;

    public Trie() {
        root = new TrieNode(' ');
    }

    @Override
    public ITrie insert(String word) {
        TrieNode currentNode = root;
        Map<Character , TrieNode> children = currentNode.children;

        for (int i = 0; i < word.length(); i++) {
            char currentCharacter = word.charAt(i);

            if (children.containsKey(currentCharacter)) {
                currentNode = children.get(currentCharacter);
            } else {
                currentNode = new TrieNode(currentCharacter);
                children.put(currentCharacter, currentNode);
            }

            children = currentNode.children;
        }
        currentNode.isEndOfWord = true;
        return this;
    }

    @Override
    public boolean contains(String word) {
        TrieNode currentNode = search(word);
        return currentNode != null && currentNode.isEndOfWord;
    }

    private TrieNode search(String word) {
        TrieNode currentNode = root;
        Map<Character , TrieNode> children = currentNode.children;

        for (int i = 0; i < word.length(); i++) {
            char currentCharacter = word.charAt(i);
            if (children.containsKey(currentCharacter)) {
                currentNode = children.get(currentCharacter);
                children = currentNode.children;
            } else {
                return null;
            }
        }
        return currentNode;
    }

    @Override
    public boolean containsPrefix(String prefix) {
        TrieNode currentNode = search(prefix);
        return currentNode != null;
    }

    @Override
    public List<String> wordsWithPrefix(String prefix) {
        TrieNode node = search(prefix);
        List<String> words = new ArrayList<>();
        if (node != null) {
            addMatchedWords(node , prefix , words);
        }

        return words;
    }

    private void addMatchedWords(TrieNode node , String word, List<String> words) {
        if (node.isEndOfWord) {
            words.add(word);
        }
        Collection<TrieNode> children = node.children.values();
        children.forEach((child) -> {
            addMatchedWords(child,word.concat(String.valueOf(child.character)),words);
        });
    }

    @Override
    public void delete(String word) {

        List<TrieNode> visitedNodes = new ArrayList<>();

        var children = root.children;

        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);

            if (!children.containsKey(c)) {
                break;
            }

            TrieNode currentNode = children.get(c);

            visitedNodes.add(currentNode);
            children = currentNode.children;
        }

        // case word not found
        if (visitedNodes.isEmpty() || visitedNodes.size() != word.length()) {
            return;
        }

        visitedNodes.getLast().isEndOfWord = false;

        for (int i = 0; i < visitedNodes.size() - 2; i++) {
            TrieNode currentNode = visitedNodes.get(i);
            if (currentNode.children.isEmpty()) {
                visitedNodes.get(i - 1).children.remove(currentNode.character);
            } else if (currentNode.isEndOfWord) {
                break;
            }
        }
    }
}
