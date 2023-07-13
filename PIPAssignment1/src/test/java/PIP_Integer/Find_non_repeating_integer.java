package PIP_Integer;

public class Find_non_repeating_integer {
	
	 public static void findNonRepeatingIntegers(int[] arr) {  //Creating public static method so its can access from any class without object.
	        for (int i = 0; i < arr.length; i++) {
	            int current = arr[i];
	            boolean isRepeating = false;

	         // Check if the current element appears elsewhere in the array
	            for (int j = 0; j < arr.length; j++) {
	                if (i != j && current == arr[j]) { // i != j is use because i don't compare same index.
	                    isRepeating = true;
	                    break;
	                }
	            }

	            // If the current element is not repeating, print it
	            if (!isRepeating) {
	                System.out.println(current);
	            }
	        }
	    }
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Hello world");
		int[] testArray = {1,2,3,4,5,4,5,2,1};
     findNonRepeatingIntegers(testArray); // Calling non-integer repeating method.
		
}

}
