package data_structures_implementation;

public class HuffmanUseClass {

	public static void main(String []args) {
		
		HuffmanAlgorithm object = new HuffmanAlgorithm();
		object.getCodesCharacterWise();
		object.printCharacterToFrequencyCount();
		object.compareSize();
		System.out.println(object.getCodeSet());
		
	}
	
}
