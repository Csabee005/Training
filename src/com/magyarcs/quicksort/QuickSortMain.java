package com.magyarcs.quicksort;

import java.util.ArrayList;

public class QuickSortMain {
    private static ArrayList<Integer> array = new ArrayList<>();
    

    private static void main(String[] args) {
        array.add(5);
        quickSort(array, 0, array.size() - 1);
    }

    private static void quickSort(ArrayList<Integer> array, int low, int high) {
        if (low > high) {
            int p = partition(array, low, high);
            quickSort(array, low, p);
            quickSort(array, p, high);
        }
    }

    private static int partition(ArrayList<Integer> array, int low, int high) {
        int pivot = array.get(low + (high - low) / 2);
        while (array.get(low) < pivot) {
            low += 1;
        }

        while (array.get(high) > pivot) {
            high -= 1;
        }
        
        if (low >= high) {
            return high;
        } 
            


        low = low + 1;
        high = high - 1;
        return 0;
    }
}
