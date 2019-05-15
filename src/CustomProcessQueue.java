

public class CustomProcessQueue implements WaitingQueueADT<CustomProcess> {

	// The initial capacity of the heap
	private static final int INITIAL_CAPACITY = 20;
	// Array-based min heap storing the data - an over-sized array
	private CustomProcess[] heap;
	// Number of CustomProcesses present in this CustomProcessQueue
	private int size;
	// Current size of the heap
	private int currentCapacity = INITIAL_CAPACITY;

	/**
	 * Constructs sets up the customprocess[] heap and initializes size
	 */
	
	public CustomProcessQueue() {
		// Initialize heap to capacity + 1 since index 0 always remains unused
		heap = new CustomProcess[INITIAL_CAPACITY + 1];
		size = 0;
	}

	/**
	 * Add a process in the appropriate spot in the heap
	 * 
	 * @param newObject      CustomProcess to be added
	 */
	public void enqueue(CustomProcess newObject) {
		if (size == 0) {
			heap[1] = newObject;
		} else {
			resize();
			int index = getFirstIndex();
			heap[index] = newObject;
			minHeapPercolateUp(index);
		}
		size++;
	}

	/**
	 * Get the first index is null
	 * 
	 * @return index of first null value
	 */
	private int getFirstIndex() {
		for (int i = 1; i < currentCapacity; i++) {
			if (heap[i] == null) {
				return i;
			}
		}

		return -1;
	}

	/**
	 * Check that the size of the heap
	 */
	private void resize() {
		if (size == currentCapacity - 1) {
			CustomProcess[] temp = new CustomProcess[(currentCapacity * 2) + 1];
			for (int i = 1; i <= currentCapacity; i++) {
				temp[i] = heap[i];
			}
			heap = temp;
			currentCapacity = currentCapacity * 2;
		}
	}

	/**
	 * Removes the top process and swaps the last value in the heap
	 * 
	 * @return temp 
	 */
	public CustomProcess dequeue() {
		if (size == 0) {
			return null;
		} else if (size == 1) {
			CustomProcess temp = heap[1];
			heap[1] = null;
			size--;
			return temp;
		} else {
			CustomProcess temp = heap[1];
			heap[1] = null;
			
			swapValues(1, size);
		
			minHeapPercolateDown(1);
			size--;
			return temp;
		}
	}

	/**
	 * Returns (but does not remove) the minimum element in the heap.
	 * 
	 * @return heap top (first) value
	 */
	public CustomProcess peek() {
		return this.heap[1];
	}

	/**
	 * get the size of heap 
	 * 
	 * @return current size of heap
	 */
	public int size() {
		return this.size;
	}

	 /**
     * Performs the minHeapPercolateUp operation to place a newly inserted element 
     * (i.e. the element that is at the size index) in its correct place so 
     * that the heap maintains the min-heap order property.
     * 
     * @parm index element 
     */
	private void minHeapPercolateUp(int index) {
		while (true) {
			if (index > 1) {
				int parentIndex = getParentIndex(index);
			
				if (heap[parentIndex].getBurstTime() > heap[index].getBurstTime()) {
					
					swapValues(parentIndex, index);
				
					index = parentIndex;
					
				} else if (heap[parentIndex].getBurstTime() == heap[index].getBurstTime()) {
					if (heap[parentIndex].getProcessId() > heap[index].getProcessId()) {
						swapValues(parentIndex, index);
					
						index = parentIndex;
					}
					
				} else {
					break;
				}
				
			} else {
				break;
			}
		}
	}

	/**
	 * Swap a parent and child value in the heap
	 * 
	 * @param parentIndex  
	 * @param childIndex 
	 *           
	 */
	private void swapValues(int parentIndex, int childIndex) {
		CustomProcess temp = heap[parentIndex];
		heap[parentIndex] = heap[childIndex];
		heap[childIndex] = temp;
	}

	/**
	 * Gets the parent index of a a custom process at a specified index
	 * 
	 * @return Index of the parent of the specified CustomProcess
	 */
	private int getParentIndex(int index) {
	 return (index / 2);
		
		
	}

	  /**
     * Performs the minHeapPercolateDown operation to place the element that is at the 
     * root of the heap in its correct place so that the heap maintains the 
     * min-heap order property.
     * 
     * @param index element
     */
	
	
	private void minHeapPercolateDown(int index) {
		while (true) {

			if (heap[getLeftChildIndex(index)] != null
					&& heap[index].getBurstTime() > heap[getLeftChildIndex(index)].getBurstTime()) {

				swapValues(index, getLeftChildIndex(index));

				index = getLeftChildIndex(index);

			} else if (heap[getRightChildIndex(index)] != null
					&& heap[index].getBurstTime() > heap[getRightChildIndex(index)].getBurstTime()){

				swapValues(index, getRightChildIndex(index));

				index = getRightChildIndex(index);

			} else if (heap[getRightChildIndex(index)] != null
					&& heap[index].getBurstTime() == heap[getRightChildIndex(index)].getBurstTime()) 
			{

				if (heap[index].getProcessId() > heap[getRightChildIndex(index)].getProcessId()) {

					swapValues(index, getRightChildIndex(index));

					index = getRightChildIndex(index);
				}
			} else {
				break;
			}
		}
	}

	/**
	 * Get left child index 
	 * 
	 * @param index left child 
	 * @return left child position
	 */
	private int getLeftChildIndex(int index) {
		return (index * 2);
	}

	/**
	 * Get right child index 
	 * 
	 * @param index right child 
	 * @return right child position
	 */
	private int getRightChildIndex(int index) {
		return ((index * 2) + 1);
	}

	/**
     * Returns true if the heap has no elements; false otherwise.
     */
	public boolean isEmpty() {
		return (size == 0);
	}
}
