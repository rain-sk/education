package main;

import java.util.Iterator;

import evilHangman.Trie;

public class TrieIteratorMain {

	/**
	 * @param args
	 *            Unused
	 */
	public static void main(String[] args) {
		String[] words = { "cat", "mouse", "ball", "dog", "balloon", "fish" };
		Trie wordsTrie = new Trie();

		// copy all words into trie
		for (String word : words) {
			System.out.println("adding " + word);
			wordsTrie.addWord(word);
		}

		System.out.println();

		Iterator<String> iter = wordsTrie.iterator();
		while (iter.hasNext()) {
			String word = iter.next();
			System.out.println("Got word: " + word);
			if (word.equals("dog"))
				iter.remove();
		}

		System.out.println();

		for (String word : wordsTrie) {
			System.out.println("Got word: " + word);
		}
	}
}
