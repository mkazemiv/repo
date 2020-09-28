/**
 * @author Mohammad A. Kazemivarnamkhasti
 */
public class ArraySumDriver {
	private final static int ARRAY_SIZE = 6;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int index = 0;

		Integer[] myArray = new Integer[ARRAY_SIZE];
		
		myArray[index++] = 13;
		myArray[index++] = 5;
		myArray[index++] = 12;
		myArray[index++] = 6;
		
		int sum = sumOfArray(myArray, 3);
		System.out.println(sum); // 36
		
		myArray[index++] = 7;
		myArray[index++] = 1;
		
		sum = sumOfArray(myArray, 5);
		System.out.println(sum); // 44
		
		int[] myArray2 = new int[10]; // Increase size of array to use method with indeces > 9
		myArray2[0] = 1;
		myArray2[1] = 1;
		// Fibonacci sequence: 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377, 610, 987, 1597, 2584...
		System.out.println("Value of index 4 (5th value) in Fibonacci sequence: " + fibonacciNumbers(4, myArray2));
		System.out.println("Value of index 9 (10th value) in Fibonacci sequence: " + fibonacciNumbers(9, myArray2));
		
	}
	
	/**
	 * Recursive method for generating sum of values in array
	 * @param arr array of Integers
	 * @param num index of array to sum all previous index values (including num)
	 * @return sum of array values
	 */
	public static int sumOfArray(Integer[] arr, int num) {
		int sum = arr[num];
		if(num > 0)
			sum += sumOfArray(arr, num-1);
		return sum;
	}
	
	/**
	 * Recursive method which calculates any value in the Fibonacci sequence
	 * @param f index of number in Fibonacci sequence
	 * @param arr array of int's used to store values of Fibonacci sequence
	 * @return value of Fibonacci sequence at given index
	 */
	public static int fibonacciNumbers(int f, int[] arr) {
		if(f == 0 || f == 1)
			return arr[f];
		else { 
			arr[f] = fibonacciNumbers(f-1, arr) + fibonacciNumbers(f-2, arr);
			return arr[f];
		}
	}

}