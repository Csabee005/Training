package com.magyarcs.quicksort;

import java.util.ArrayList;

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
            
        swap(array.get(low), array.get(high);

        low := low + 1;
        high := high - 1;
    }
}
