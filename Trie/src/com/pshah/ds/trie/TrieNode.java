package com.pshah.ds.trie;


class TrieNode {
	char content;
	boolean endMarker;
	int isPrefix;
	TrieNode[] childList;

	/* Constructor */
	public TrieNode(char c) {
		childList = new TrieNode[26];
		endMarker = false;
		content = c;
		isPrefix = 0;
	}

	public TrieNode subNode(char c) {
		return childList[c - 97];
	}
}