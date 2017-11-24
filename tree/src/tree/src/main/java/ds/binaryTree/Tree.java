package ds.binaryTree;

public class Tree {

	private Node root;

	public void insert(int key) {
		Node newNode = new Node(key);

		if (root == null) {
			root = newNode;

		} else {
			Node parent;
			Node current = root;
			while (true) {
				parent = current;
				if (key < current.key) {
					current = current.leftChild;
					if (current == null) {
						parent.leftChild = newNode;
						return;
					}
				} else {
					current = current.rightChild;
					if (current == null) {
						parent.rightChild = newNode;
						return;
					}

				}

			}
		}

	}

	public Node find(int key) {
		Node current = root;
		while (current.key != key) {
			if (key < current.key)
				current = current.leftChild;
			else
				current = current.rightChild;
			if (current == null)
				return null;
		}
		return current;
	}

	public boolean delete(int key) {

		boolean isLeftChild = true;
		Node current = root;
		Node parent = root;

		// finding node in a tree.
		while (current.key != key) {
			parent = current;
			if (key < current.key) {
				isLeftChild = true;
				current = current.leftChild;
			} else {
				isLeftChild = false;
				current = current.rightChild;
			}
			if (current == null)
				return false;
		}

		// if both children are null.
		if (current.leftChild == null && current.rightChild == null) {
			if (root == current)
				root = null;
			else if (isLeftChild)
				parent.leftChild = null;
			else
				parent.leftChild = null;
		}
		// if only left child is null.
		else if (current.leftChild == null) {
			if (root == current)
				root = current.rightChild;
			else if (isLeftChild)
				parent.leftChild = current.rightChild;
			else
				parent.rightChild = current.rightChild;
		}
		// if only right child is null.
		else if (current.rightChild == null) {
			if (root == current)
				root = current.leftChild;
			else if (isLeftChild)
				parent.leftChild = current.leftChild;
			else
				parent.rightChild = current.rightChild;
		}
		// have both children.
		else {
			Node successor = findLeftMostChild(current);

			if (root == current) {
				root = successor;
			} else if (isLeftChild)
				parent.leftChild = successor;
			else
				parent.rightChild = successor;
			successor.leftChild = current.leftChild;
		}
		return true;
	}

	private Node findLeftMostChild(Node delNode) {
		Node successorParent = delNode;
		Node successor = delNode;
		Node current = delNode.rightChild;
		while (current != null) {
			successorParent = successor;
			successor = current;
			current = current.leftChild;
		}

		if (successor != delNode.rightChild) {
			successorParent.leftChild = successor.rightChild;
			successor.rightChild = delNode.rightChild;
		}

		return successor;

	}

	public Node minimum() {
		Node current = root;
		Node parent = null;
		while (current != null) {
			parent = current;
			current = current.leftChild;
		}

		return parent;
	}

	public Node maximum() {
		Node current = root;
		Node parent = null;
		while (current != null) {
			parent = current;
			current = current.rightChild;
		}
		return parent;

	}

	public void inOrder(Node localNode) {
		if (localNode != null) {
			inOrder(localNode.leftChild);
			System.out.print(localNode.key + " ");
			inOrder(localNode.rightChild);
		}
	}

	public void preOrder(Node localNode) {
		if (localNode != null) {
			System.out.print(localNode.key + " ");
			preOrder(localNode.leftChild);
			preOrder(localNode.rightChild);
		}
	}

	public void postOrder(Node localNode) {
		if (localNode != null) {
			postOrder(localNode.leftChild);
			postOrder(localNode.rightChild);
			System.out.print(localNode.key + " ");
		}
	}
	
	public static void main(String[] args) {
		
		Tree tree = new Tree();
		
		tree.insert(50);
		tree.insert(25);
		tree.insert(75);
		tree.insert(12);
		tree.insert(37);
		tree.insert(50);
		tree.insert(43);
		tree.insert(30);
		tree.insert(33);
		tree.insert(87);
		tree.insert(93);
		tree.insert(97);
		System.out.println("Root: " + tree.root.key);
		System.out.println(" Inorder: ");
		tree.inOrder(tree.root);
		System.out.println("\n PreOrder");
		tree.preOrder(tree.root);
		System.out.println("\n PostOrder: ");
		tree.postOrder(tree.root);
		
		System.out.println();
		Node find = tree.find(93);
		System.out.println(find == null ? "Not Found" : "Found");
		
		System.out.println();
		find = tree.find(98);
		System.out.println(find == null ? "Not Found" : "Found");
		
		tree.delete(50);
		tree.delete(25);
		tree.delete(93);
		System.out.println();
		tree.inOrder(tree.root);
	}
}
