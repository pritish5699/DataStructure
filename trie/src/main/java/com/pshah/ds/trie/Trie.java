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
			current.prefix++;
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
		return current.endMarker == true;
	}

	/* Function to remove a word */
	public boolean remove(String word) {
		if(search(word))   // Check for string is already present, if not it cannot be deleted
		{
			TrieNode current = root;
			TrieNode temp;  // To keep track of parent
			while (current != null) {
				//Loop over the length of string
				for(int i=0; i < word.length(); i++)
				{
					temp = current;
					//System.out.println(s.charAt(i));
					current = current.subNode(word.charAt(i));
					//System.out.println(current.prefix);
					current.prefix--;
					if(current.prefix == 0)
					{
						//System.out.println(current.content);
						temp.childList[current.content - 97] = null;
						break;
					}
				}
				// remover the endmarker to indicate the word removal
				current.endMarker = false;
				current = null;
				return true;
			}
		}
		return false;
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
