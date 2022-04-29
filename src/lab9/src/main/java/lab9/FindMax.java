package lab9;

/**
 * 
 * COMP 3021
 * 
This is a class that prints the maximum value of a given array of 90 elements

This is a single threaded version.

Create a multi-thread version with 3 threads:

one thread finds the max among the cells [0,29] 
another thread the max among the cells [30,59] 
another thread the max among the cells [60,89]

Compare the results of the three threads and print at console the max value.

 * 
 * @author valerio
 *
 */
public class FindMax {
	// this is an array of 90 elements
	// the max value of this array is 9999
	static int[] array = { 1, 34, 5, 6, 343, 5, 63, 5, 34, 2, 78, 2, 3, 4, 5, 234, 678, 543, 45, 67, 43, 2, 3, 4543,
			234, 3, 454, 1, 2, 3, 1, 9999, 34, 5, 6, 343, 5, 63, 5, 34, 2, 78, 2, 3, 4, 5, 234, 678, 543, 45, 67, 43, 2,
			3, 4543, 234, 3, 454, 1, 2, 3, 1, 34, 5, 6, 5, 63, 5, 34, 2, 78, 2, 3, 4, 5, 234, 678, 543, 45, 67, 43, 2,
			3, 4543, 234, 3, 454, 1, 2, 3 };

	public static void main(String[] args) {
		new FindMax().printMax();
	}

	public void printMax() {
		// this is a single threaded version
		int max = 0;
		MaxTask max1 = new MaxTask(0, 29);
		MaxTask max2 =new MaxTask(30, 59);
		MaxTask max3 =new MaxTask(60, 89);
		Thread task1 = new Thread(max1);
		Thread task2 = new Thread(max2);
		Thread task3 = new Thread(max3);

		task1.start();
		task2.start();
		task3.start();

		try {
			task1.join();
			task2.join();
			task3.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		max = Math.max(max1.getLocalMax(), max2.getLocalMax());
		max = Math.max(max, max3.getLocalMax());
		System.out.println("the max value is " + max);
	}
	class MaxTask implements Runnable {
		private int localMax;
		private int start;
		private int end;
		public MaxTask(int start, int end) {
			this.start = start;
			this.end = end;
		}

		public int getLocalMax() {
			return localMax;
		}

		@Override
		public void run() {
			localMax = findMax(start, end);
		}
	}

	/**
	 * returns the max value in the array within a give range [begin,range]
	 * 
	 * @param begin
	 * @param end
	 * @return
	 */
	private int findMax(int begin, int end) {
		// you should NOT change this function
		int max = array[begin];
		for (int i = begin + 1; i <= end; i++) {
			if (array[i] > max) {
				max = array[i];
			}
		}
		return max;
	}
}
