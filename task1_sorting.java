import java.util.*;
class mfc_task1
{
    public static void bubbleSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false; // Optimization
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    // Swap
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true;
                }
            }
            if (!swapped) break; // If no swaps, array is sorted
        }
    }
    
    public static void selectionSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            // Swap
            int temp = arr[minIndex];
            arr[minIndex] = arr[i];
            arr[i] = temp;
        }
    }
    
    public static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(arr, low, high);
            quickSort(arr, low, pivotIndex - 1);
            quickSort(arr, pivotIndex + 1, high);
        }
    }

    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[high]; 
        int i = low - 1; 
        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                i++;
                // Swap
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        // Swap pivot to correct position
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        return i + 1;
    }
    
    public static void insertionSort(int[] arr) {
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            int key = arr[i];
            int j = i - 1;

            // Shift elements of arr[0..i-1] that are greater than key
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key; // Place key at its correct position
        }
    }

    public static void main(String args[])
    {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter length of array1 : ");
        int n1=sc.nextInt();
        int arr1[]=new int[n1];
        System.out.println("Enter elements of array :");
        for(int i=0;i<n1;i++)
        {
            arr1[i]=sc.nextInt();
        }
        bubbleSort(arr1);
        System.out.println("Sorted using bubble sort :");
        for(int i=0;i<n1;i++)
        {
            System.out.print(arr1[i]+" ");
        }
        System.out.println();
        System.out.println("Enter length of array2 : ");
        int n2=sc.nextInt();
        int arr2[]=new int[n2];
        System.out.println("Enter elements of array :");
        for(int i=0;i<n2;i++)
        {
            arr2[i]=sc.nextInt();
        }
        selectionSort(arr2);
        System.out.println("Sorted using Selection sort :");
        for(int i=0;i<n1;i++)
        {
            System.out.print(arr2[i]+" ");
        }
        System.out.println();
        System.out.println("Enter length of array3 : ");
        int n3=sc.nextInt();
        int arr3[]=new int[n3];
        System.out.println("Enter elements of array :");
        for(int i=0;i<n3;i++)
        {
            arr3[i]=sc.nextInt();
        }
        quickSort(arr3,0,n3-1);
        System.out.println("Sorted using quick sort :");
        for(int i=0;i<n1;i++)
        {
            System.out.print(arr3[i]+" ");
        }
        System.out.println();
        System.out.println("Enter length of array4 : ");
        int n4=sc.nextInt();
        int arr4[]=new int[n4];
        System.out.println("Enter elements of array :");
        for(int i=0;i<n4;i++)
        {
            arr4[i]=sc.nextInt();
        }
        insertionSort(arr4);
        System.out.println("Sorted using insertion sort :");
        for(int i=0;i<n4;i++)
        {
            System.out.print(arr4[i]+" ");
        }
        System.out.println();
    }
}
/**     NOTES ON THE SORTING MECHANISMS

1️1. ***Selection Sort

Working:

Find the minimum element in the unsorted part and swap it with the first element.

Move the boundary between the sorted and unsorted sections one step forward.

Repeat until the entire array is sorted.


Example:

Unsorted array: [64, 25, 12, 22, 11]

1. Find the smallest (11) → Swap with 1st element → [11, 25, 12, 22, 64]


2. Find next smallest (12) → Swap with 2nd element → [11, 12, 25, 22, 64]


3. Find next smallest (22) → Swap with 3rd element → [11, 12, 22, 25, 64]


4. Array is sorted!



Time Complexity:

Best Case: O(n) (No early termination even if sorted)

Average Case: O(n^2)

Worst Case: O(n^2)

Space Complexity: O(1)  (In-place sorting)


Pros & Cons:

1. Simple to implement
2. Works well for small datasets
3. Inefficient for large datasets


---

2️.*** Bubble Sort

Working:

Compare adjacent elements and swap if needed.

The largest element "bubbles up" to its correct position in each pass.

Repeat until the array is sorted.


Example:

Unsorted array: [5, 1, 4, 2, 8]

1. Compare & swap adjacent elements → [1, 5, 2, 4, 8]


2. Repeat → [1, 2, 4, 5, 8]


3. No swaps? Stop early!



Time Complexity:

Best Case: O(n) (If already sorted, stops early)

Average Case: O(n^2)

Worst Case: O(n^2)

Space Complexity: O(1) 


Pros & Cons:

1. Easy to implement
2. Can detect already sorted lists quickly.
3. Very slow for large datasets


---

3️.*** Quick Sort

Working:

Pick a pivot (usually last element).

Partition the array such that elements < pivot go left, and elements > pivot go right.

Recursively apply the same logic to left & right subarrays.


Example (Lomuto Partitioning):

Unsorted array: [10, 80, 30, 90, 40, 50, 70]

1. Choose pivot = 70, partition around it → [10, 30, 40, 50, 70, 90, 80]


2. Recursively quick sort left [10, 30, 40, 50] and right [90, 80]


3. Continue until sorted.



Time Complexity:

Best Case: O(nlogn) (Balanced partitioning)

Average Case: O(nlogn)

Worst Case: O(n^2) (If pivot selection is bad, e.g., already sorted array with last element as pivot)

Space Complexity:  O(log n)(Recursive calls stack)


Pros & Cons:

1. Fastest general-purpose sorting algorithm
2. In-place sorting (no extra memory)
3. Worst case is bad if pivot choice is poor


---

4️. *** Insertion Sort

Working:

Take one element at a time and insert it into its correct position.Think of sorting playing cards in your hand!


Example:

Unsorted array: [12, 11, 13, 5, 6]

1. Compare 11 with 12, insert → [11, 12, 13, 5, 6]


2. Compare 13, no change → [11, 12, 13, 5, 6]


3. Insert 5 at the correct position → [5, 11, 12, 13, 6]


4. Insert 6 at the correct position → [5, 6, 11, 12, 13]



Time Complexity:

Best Case: O(n) (If already sorted)

Average Case: O(n^2)

Worst Case: O(n^2)

Space Complexity: O(1)


Pros & Cons:

1. Efficient for small or nearly sorted arrays
2. Stable sorting (doesn’t change relative order of equal elements)
3. Not great for large datasets
*/
