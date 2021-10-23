import java.io.*;
import java.util.*;

public class Decompress {
	
	String file = "";
	BinaryTreeNode root = null;
	HashMap<Character,String> characterToCode;
	HashMap<String,Character> codeToChar = null;
	String codeset = "";
	byte array[];
	
	public Decompress(String filePath) {
		this.file = filePath;
		getCharacterToCodesAndByteArray();
		fillMap();
		printMap();
		printArray();
}

	private void getCharacterToCodesAndByteArray() {
		
		try {
			
			FileInputStream fileIn = new FileInputStream(this.file);
			//ObjectInputStream objectIn = new ObjectInputStream(fileIn);
			 
		//	HashMap<Character,String> tempRoot = (HashMap<Character,String>)objectIn.readObject();
		
			int i=0;
			
			while((i = fileIn.read()) != -1) {
				codeset += fileIn.read();
				i++;
			}
		//	objectIn.close();
		//	
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		
	}
	
	private void fillMapHelper(BinaryTreeNode root,String code) {
		
		if(root == null) {
			return;
		}
		if(root.left == null && root.right == null && root.data.length() == 1) {
			codeToChar.put(code, root.data.charAt(0));
			return;
		}
		
		fillMapHelper(root.left,code + '0');
		fillMapHelper(root.right,code + '1');
		
	}
	
	private void fillMap() {
		codeToChar = new HashMap<String,Character>();
		fillMapHelper(this.root,"");
	}
	
	private void printMap() {
		Iterator<String> it = codeToChar.keySet().iterator();
		while(it.hasNext()){
			String temp = it.next();
			System.out.println(temp + " = " + codeToChar.get(temp));
			
		}
	}

	private void printArray() {
		System.out.println(codeset.length());
		System.out.println(codeset);
		
	}
		
		
}

