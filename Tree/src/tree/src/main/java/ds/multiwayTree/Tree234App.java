package ds.multiwayTree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Tree234App {
	
	public static void main(String[] args) throws IOException {
		double value;
		Tree234 tree = new Tree234();
		
		tree.insert(50);
		tree.insert(40);
		tree.insert(60);
		tree.insert(30);
		tree.insert(70);
		
		while(true){
			putText("Enter First Letter of ");
			putText("show, insert, or find: ");
			char choice = getChar();
			switch (choice) {
			case 's':
				tree.displayTree();
				break;
			case 'i':
				putText("Enter value to insert: ");
				value = getInt();
				tree.insert(value);
				break;
			case 'f':
				putText("Enter value to find: ");
				value = getInt();
				int found = tree.find(value);
				if(found != -1)
					System.out.println("Found " + value);
				else
					System.out.println("Not found " + value);
				break;
			default:
				putText("Invalid entry\n");
				break;
			}
		}
	}
	
	public static void putText(String s){
		System.out.println(s);
		System.out.flush();
	}
	
	public static String getString() throws IOException{
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		String s = br.readLine();
		return s;
	}
	
	public static char getChar() throws IOException{
		String s = getString();
		return s.charAt(0);
	}
	
	public static int getInt() throws IOException{
		String s = getString();
		return Integer.parseInt(s);
	}
}
