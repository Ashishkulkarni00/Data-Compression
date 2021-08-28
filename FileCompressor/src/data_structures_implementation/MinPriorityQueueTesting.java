package data_structures_implementation;

import java.util.ArrayList;


public class MinPriorityQueueTesting {

	private ArrayList<BinaryTreeNode> heap;

	
	//Constructor
	public MinPriorityQueueTesting() {
		heap = new ArrayList<BinaryTreeNode>();
	}
	
	
	private ArrayList<BinaryTreeNode> getHeap(){
		return heap;
	}
	
	public boolean isEmpty() {
		return (heap.size() == 0);
	}

	public int size() {
		return heap.size();
	}

	public int getMin() throws EmptyQueueException {
		if (heap.size() == 0) {
			throw new EmptyQueueException();
		}
		return heap.get(0).frequency;
	}

	public void insert(BinaryTreeNode input) {
		heap.add(input);

		int childIndex = heap.size() - 1;
		int parentIndex = (childIndex - 1) / 2;
		while (childIndex > 0) {
			if (heap.get(childIndex).frequency < heap.get(parentIndex).frequency) {
				BinaryTreeNode temp = heap.get(childIndex);		
				heap.set(childIndex, heap.get(parentIndex));
				heap.set(parentIndex, temp);
				childIndex = parentIndex;
				parentIndex = (childIndex - 1) / 2;
			} else {
				return;
			}
		}

	} 

	public BinaryTreeNode remove() throws EmptyQueueException {
		
		if(isEmpty()){
            throw new EmptyQueueException();
        }	
		BinaryTreeNode temp = heap.get(0);
        int index = 0;
        int minIndex = index;
        int leftChildIndex = 1;
        int rightChildIndex = 2;
        heap.set(0,heap.get(heap.size()-1));
        heap.remove(heap.size()-1);
        
        while(leftChildIndex < heap.size()){
            if(heap.get(leftChildIndex).frequency < heap.get(minIndex).frequency){
                minIndex = leftChildIndex;
            }
            if(rightChildIndex < heap.size() && heap.get(rightChildIndex).frequency < heap.get(minIndex).frequency){
                minIndex = rightChildIndex;
            }
            if(minIndex == index){
                break;
            }else{
            	BinaryTreeNode temp1 = heap.get(index);
                heap.set(index,heap.get(minIndex));
                heap.set(minIndex,temp1);
                index = minIndex;
                leftChildIndex = 2 * index + 1;
                rightChildIndex = 2 * index + 2;
            }
        }
        return temp;
        
    }
		

	
	
}
