import java.util.*;
import java.io.*;
public class HuffmanAlgorithm {

	String text="";
	String codeset = "";
	HashMap<Character,Integer> characterToFrequency;
	HashMap<Character,String> characterToCodes;
	BinaryTreeNode root = null;
	MinPriorityQueue queue;
	String fileName;
	
	
	//Constructor
	//Pass the file name as a string to the constructor and keep the file in following path
	//C:\Users\LENOVO\Desktop\desktop\New folder (2)\Huffman Encodeing
	public HuffmanAlgorithm(String fileName){
		this.fileName = fileName;
		try {
			
			fillMap();
			fillPriorityQueue();
			buildTree();
			createCodes();
			fillCodeset();
			write();
			printText();
			printMap();
		}catch(IOException e) {
			System.out.println("File Not Found...");
		}
		
	}
	
	
	// this Function reads the file from the secondary storage and stores the characters and frequency count in the map "characterToFrequency"
	private void fillMap() throws IOException {
		characterToFrequency = new HashMap<Character,Integer>();
		try {
			FileInputStream inputStream = new FileInputStream(this.fileName);
			int i = 0;
			while ((i = inputStream.read()) != -1) {
				char temp = (char)i;
				text += temp+"";
				if(characterToFrequency.containsKey(temp)) {
					characterToFrequency.put(temp, characterToFrequency.get(temp)+1);
				}else {
					characterToFrequency.put(temp,1);
				}
			}
			inputStream.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	// this method creates priority fills priority queue using the data of hashMap
	private void fillPriorityQueue() {
		queue = new MinPriorityQueue();
		Iterator <Character> it = characterToFrequency.keySet().iterator();
		while(it.hasNext()) {
			char key = it.next();
			int value = characterToFrequency.get(key);
			BinaryTreeNode node = new  BinaryTreeNode(key+"",value); 
			node.left = null;
			node.right = null;
			queue.insert(node);
		}
	}
	
	
	// this method builds huffman tree by using priority queue...removes two nodes from the queue with minimum frequency and
	// parent nodes frequency will be sum of two frequencies
	private void buildTree() {
		
		while(queue.size() > 1) {
			try {
				
				BinaryTreeNode left = queue.remove();
				BinaryTreeNode right = queue.remove();
				
				BinaryTreeNode node = new BinaryTreeNode("",left.frequency+right.frequency);
				
				node.left = left;
				node.right = right;
				
				queue.insert(node);
				root = node;
				
			}catch(EmptyQueueException e) {
				//never reach here...
			}
		}
	}

	
	//filling characterToCodes HashMap...
	private void createCodesHelper(BinaryTreeNode root1,String s) {
		
		if(root1 == null) {
			System.out.println("Enter at least two characters...");
			return;
		}
		
		if(root1.left == null && root1.right == null && root1.data.length() == 1) {
			characterToCodes.put(root1.data.charAt(0),s);
			return;
		}
		createCodesHelper(root1.left,s + "0");
		createCodesHelper(root1.right,s + "1");
		
	}
	
	private void createCodes() {
		characterToCodes = new HashMap<Character,String>();
		createCodesHelper(root,"");
	}

	// this method created file named "compress.txt" in which stores the generated custom codes...
	private void write() throws IOException {
		
		FileOutputStream fileOut = new FileOutputStream("Compress.txt");
		BitSet b = new BitSet(codeset.length());
			for(int i=0;i<codeset.length();i++) {
				if(codeset.charAt(i)=='1') {
					b.set(i);
				}
			}
		
		byte[] array = b.toByteArray();
		fileOut.write(array);
		fileOut.close();
	}
	
	// Utility methods to display output on the console
	private void fillCodeset() {
		for(int i=0;i<text.length();i++) {
			codeset += characterToCodes.get(text.charAt(i));
		}
	}
	
	public void printText() {
		System.out.println(text);
		System.out.println(codeset);
	}
	
	public void printMap() {
		
		Iterator<Character> it = characterToCodes.keySet().iterator();
		while(it.hasNext()) {
			char key = (char)it.next();
			System.out.println(key + " = " + characterToCodes.get(key));
			
		}
	
		
	}
	
	
	
	
}
