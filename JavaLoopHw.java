public class JavaLoopHw {
   public static void main(String[] args) {
      int[] Array = {0,0,1,2,2,3,5,5,8,9,9,11,14,14,21,23,25,28,69,120,420,2020}; // Step 1: Initialize list
      for (int value : Array){    //Step 2: loops over all ints in the array
         if (value%2==0){          //Step 3: Tests if the given int is even and prints the number
            System.out.println(value);
         }
      }
   }
}