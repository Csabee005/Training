package com.magyarcs.quicksort;

public class QuickSortMain {
    ArrayList<Integer> array = new ArrayList<>();
    

    private static void main(String[] args) {
        array.addAll(5,12,51,32,512,6341,3215,31);
        quickSort(array, 0, array.length - 1);
    }

    private void quickSort(ArrayList<Integer> array, int low, int high) {
        if (low > high) {
            int p = partition(array, low, high);
            quickSort(array, low, p);
            quickSort(array, p, high);
        }
    }

    private int partition(ArrayList<Integer> array, int low, int high) {
        int pivot = A[low + (high - low) / 2];
        while (A[low] < pivot) {
            low += 1;
        }

        while (A[high] > pivot) {
            high -= 1;
        }
        
        if (lo >= hi) {
            return high;
        } 
            
        swap(array[low], array[high]);

        low := low + 1
        high := high - 1
    }
}
