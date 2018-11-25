package main;

import evilHangman.Trie;

public class TrieMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String[] words = { "cat", "ball", "dog", "balloon", "mouse" };
		Trie wordsTrie = new Trie();

		// copy all words into trie
		for (String word : words) {
			wordsTrie.addWord(word);
		}
		System.out.println("has " + wordsTrie.nodeCount() + " nodes in trie");

		System.out.println("dog in trie? " + wordsTrie.hasWord("dog"));
		wordsTrie.deleteWord("dog");
		System.out.println("dog in trie? " + wordsTrie.hasWord("dog"));
		System.out.println("has " + wordsTrie.nodeCount() + " nodes in trie");

		System.out.println("ball in trie? " + wordsTrie.hasWord("ball"));
		wordsTrie.deleteWord("ball");
		System.out.println("ball in trie? " + wordsTrie.hasWord("ball"));
		System.out.println("has " + wordsTrie.nodeCount() + " nodes in trie");

		System.out.println("ball in trie? " + wordsTrie.hasWord("ball"));
		wordsTrie.addWord("ball");
		System.out.println("ball in trie? " + wordsTrie.hasWord("ball"));
		System.out.println("has " + wordsTrie.nodeCount() + " nodes in trie");

		System.out.println("balloon in trie? " + wordsTrie.hasWord("balloon"));
		wordsTrie.addWord("balloon");
		System.out.println("balloon in trie? " + wordsTrie.hasWord("balloon"));
		System.out.println("has " + wordsTrie.nodeCount() + " nodes in trie");

		System.out.println("dog in trie? " + wordsTrie.hasWord("dog"));
		wordsTrie.addWord("dog");
		System.out.println("dog in trie? " + wordsTrie.hasWord("dog"));
		System.out.println("has " + wordsTrie.nodeCount() + " nodes in trie");

		for (String word : wordsTrie)
			System.out.println(word);

	}
}
