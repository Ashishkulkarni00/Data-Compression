package data_structures_implementation;
import java.util.Iterator;
import data_structures_implementation.BinaryTreeNode;
import data_structures_implementation.MinPriorityQueueTesting;
import java.util.HashMap;
import java.util.Scanner;

class Pair{
	MinPriorityQueueTesting priorityQueue;
	String text;
	HashMap<Character,Integer> Map;
}

public class HuffmanAlgorithm {
	
	
	HashMap<Character,Integer> characterToFrequency;
	HashMap<Character,String> characterToCode;
	String Text = "";
	String codeSet = "";
	private Pair fillData(String text) {
		
		characterToFrequency = new HashMap<Character,Integer>();
		for(int i=0;i<text.length();i++) {
			if(characterToFrequency.containsKey(text.charAt(i))) {
				characterToFrequency.put(text.charAt(i),characterToFrequency.get(text.charAt(i))+1);
			}else {
				characterToFrequency.put(text.charAt(i),1);
			}
		}
		
		MinPriorityQueueTesting priorityQueue = new MinPriorityQueueTesting();
		Iterator <Character> it = characterToFrequency.keySet().iterator();
		while(it.hasNext()) {
			char key = it.next();
			int value = characterToFrequency.get(key);
			BinaryTreeNode obj = new BinaryTreeNode(key+"",value);
			obj.left = null;
			obj.right = null;
			priorityQueue.insert(obj);
		}
		
		Pair obj = new Pair();
		obj.text = text;
		obj.priorityQueue = priorityQueue;
		obj.Map = characterToFrequency;
		return obj;
	}
	
	private BinaryTreeNode buildTree(MinPriorityQueueTesting priorityQueue) throws EmptyQueueException{
		//Exception can never be thrown...
		BinaryTreeNode root = null;
		while(priorityQueue.size() > 1) {
			
			BinaryTreeNode node1 = priorityQueue.remove();
			BinaryTreeNode node2 = priorityQueue.remove();
			
			BinaryTreeNode node = new BinaryTreeNode(node1.data+node2.data,node1.frequency+node2.frequency);
			node.left = node1;
			node.right = node2;
			priorityQueue.insert(node);
			root = node;
		}
		return root;
	}
	
	public String getCodeSet() {
		return codeSet;
	}
	
	private void fillCodeSet() {
		for(int i=0;i<Text.length();i++) {
			codeSet = codeSet + characterToCode.get(Text.charAt(i));
		}
	}
	
	public void compareSize() {
		System.out.println("Size of file before compression = " + Text.length() + " Bytes...");
		int temp = codeSet.length() / 8;
		System.out.println("Size of file after compression is approximately = "+temp +" Bytes...");
	}
	
	private void printCodeHelper(BinaryTreeNode root,String s) {
		
		if(root == null) {
			System.out.println("Enter atleast two Characters....");
			return;
		}
		if(root.left == null && root.right == null && root.data.length() == 1) {
			System.out.println(root.data + " : " + s);
			characterToCode.put(root.data.charAt(0),s);
			return;
		}
		printCodeHelper(root.left,s + "0");
		printCodeHelper(root.right,s + "1");
		
	}
	
	private void printCode(BinaryTreeNode root,String s) {
		System.out.println("Character wise codes...");
		characterToCode = new HashMap<Character,String>();
		printCodeHelper(root,s);
	}
	
	public void printCharacterToFrequencyCount() {
		
		
		System.out.println("Character to frequency count...");
		Iterator<Character> it = characterToFrequency.keySet().iterator();
		while(it.hasNext()) {
			char temp = it.next();
			int value = characterToFrequency.get(temp);
			System.out.println("Character: "+ temp + " = " + value);
		}
		
		
	}
	
	private String takeInput() {
		Scanner input = new Scanner(System.in);
		System.out.println("Enter text...");
		String text = input.nextLine();
		Text = text;
		input.close();
		return text;
	}
	
	public void getCodesCharacterWise() {
		String text = takeInput();
		Pair object = fillData(text);
		BinaryTreeNode root = null;
		try {
			root = buildTree(object.priorityQueue);
		} catch (EmptyQueueException e) {
			//we will never reach here...
		}
		printCode(root,"");
		fillCodeSet();
	}
	
}
	


