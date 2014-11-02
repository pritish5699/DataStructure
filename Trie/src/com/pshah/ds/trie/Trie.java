package com.pshah.ds.trie;

class Trie {
	private TrieNode root;

	/* Constructor */
	public Trie() {
		root = new TrieNode(' ');
	}

	/* Function to insert word */
	public void insert(String word) {
		System.out.println("Word: " + word);
		if (search(word) == true)
			return;
		
		TrieNode current = root;
		for (char ch : word.toCharArray()) {
			TrieNode child = current.subNode(ch);
		
			if (child != null)
				current = child;
			else {
				current.childList[ch - 97] = new TrieNode(ch);
				current = current.subNode(ch);
			}
			current.isPrefix++;
		}
		current.endMarker = true;
	}

	/* Function to search for word */
	public boolean search(String word) {
		TrieNode current = root;
		for (char ch : word.toCharArray()) {
			if (current.subNode(ch) == null)
				return false;
			else
				current = current.subNode(ch);
		}
		if (current.endMarker == true)
			return true;
		return false;
	}

	/* Function to remove a word */
	public void remove(String word) {
		if (search(word) == false) {
			System.out.println(word + " does not exist in trie\n");
			return;
		}
		TrieNode current = root;
		for (char ch : word.toCharArray()) {
			TrieNode child = current.subNode(ch);
			if (child.isPrefix == 1) {
				current.childList[child.content - 97] = null;
				return;
			} else {
				child.isPrefix--;
				current = child;
			}
		}
		current.endMarker = false;
	}

	public void printTree(TrieNode root, int level, char[] branch) {
		if (root == null)
			return;

		for (int i = 0; i < root.childList.length; i++) {
			branch[level] = root.content;
			printTree(root.childList[i], level + 1, branch);
		}

		if (root.endMarker) {
			for (int j = 1; j <= level; j++)
				System.out.print(branch[j]);
			System.out.println();
		}
	}

	public static void main(String[] args)
    {
        
        Trie obj = new Trie();
        String[] words = {"happy", "happiness"};
        for (int i = 0; i < words.length; i++)
            obj.insert(words[i]);
        
        char[] branch = new char[50];
        obj.printTree(obj.root, 0, branch);
        
        String searchWord = "allot";
        if (obj.search(searchWord))
        {
            System.out.println("The word was found");
        }
        else
        {
            System.out.println("The word was NOT found");
        }
    }
}
