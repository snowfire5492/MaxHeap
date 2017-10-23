/**
 * CS 241
 * Professor: Dr. Wei Sophie
 *
 * Project #2
 *
 * <create a Max-heap from at least 100 integers using both sequential and optimal 
 * methods of insertion , and test software using (1) 20 sets of 100 randomly generated
 * integers to build 20 heaps and find average number of swaps for both methods, and 
 * (2) with fixed integers 1 - 100, printing first 10, the average number of swaps for both 
 * optimal and insertion methods, and then perform 10 removal operations and output first
 * 10 in order again. Program will print to console and to output.txt located in EricSchenck2.zip>
 *
 * @author Eric Schenck
 * last modified: 10/23/17
 */
public interface MaxHeapInterface {
		
	/**
	 * Adds values to MaxHeap array Optimal Method
	 * @param entries an array of integers 
	 * @return number of swaps to complete add 
	 */
	public int addOptimal(int[] entries);
	
	/**
	 * Adds values to MaxHeap array, comparing values systematically to
	 * form correct MaxHeap structure when completed ( uses simple Sequential method)
	 * @param entry new value
	 * @return number of swaps to complete add 
	 */
	public int addSequential(int entry);
	
	/**
	 * removes max value of heap, rootIndex, and performs reheap
	 * @return
	 */
	public int removeMax();
	
	/**
	 * Performs adjustment to heap 
	 * @param rootIndex
	 * @return number of swaps to complete reheap()
	 */
	public int reheap(int rootIndex);
	
	/**
	 * returns a value from Heap at specific index
	 * @param index location
	 * @return value at index
	 */
	public int getIndexValue(int index);
}
