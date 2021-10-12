public class SortingAlgos {

    public static void main(String args[]) {

        int[] unsortedArray = {9,200,11};

        /*int[] sortedArray = bubbleSort(unsortedArray);*/

        int[] sortedArray = insertionSort(unsortedArray);

        System.out.println("FINAL SORTED ARRAY -->");

        printArray(sortedArray);

    }


    private static int[] insertionSort(int[] unsortedArray) {

        int lengthOfArray = unsortedArray.length;
        int timeComplexity = 1;
        for (int index = 1; index < lengthOfArray; ++index) {
            int key = unsortedArray[index];
            int previousIndex = index - 1;

            while (previousIndex > -1 && unsortedArray[previousIndex] > key) {
                unsortedArray[previousIndex + 1] = unsortedArray[previousIndex];
                previousIndex = previousIndex - 1;
                timeComplexity++;
            }
            unsortedArray[previousIndex + 1] = key;
            System.out.println("Array after " + timeComplexity + " Iteration");
            printArray(unsortedArray);

        }

        return unsortedArray;

    }








    private static int[] bubbleSort(int[] unsortedArray) {
        int timeComplexity = 0;
        for(int index = 0; index < unsortedArray.length; index++) {
            for(int secondIndex = index + 1; secondIndex < unsortedArray.length; secondIndex++) {

                //If current number is greater than the other number then swap them
                if(unsortedArray[index] > unsortedArray[secondIndex]) {
                    int currentNumber = unsortedArray[index];
                    unsortedArray[index] = unsortedArray[secondIndex];
                    unsortedArray[secondIndex] = currentNumber;
                }
                System.out.println("Array after " + timeComplexity + " Iteration");
                timeComplexity++;
                printArray(unsortedArray);
            }
        }
        return unsortedArray;
    }

    private static void printArray(int[] inputArray) {
        System.out.println("------------------");
        for(int i : inputArray) {
            System.out.print(i + ", ");
        }
        System.out.println(" ");
        System.out.println("------------------");
    }
}
