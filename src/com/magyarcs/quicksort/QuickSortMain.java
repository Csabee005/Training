package com.magyarcs.quicksort;

import java.util.ArrayList;

public class QuickSortMain {
    private static ArrayList<Integer> array = new ArrayList<>();


    public static void main(String[] args) {
        array.add(5);
        array.add(15);
        array.add(52);
        array.add(6);
        array.add(95);
        array.add(63);
        array.add(27);
        quickSort(array, 0, array.size() - 1);
        System.out.println(array);
    }

    private static void quickSort(ArrayList<Integer> array, int low, int high) {
        if (low < high) {
            int p = partition(array, low, high);
            quickSort(array, low, p - 1);
            quickSort(array, p + 1, high);
        }
    }

    private static int partition(ArrayList<Integer> array, int low, int high) {
        int pivot = array.get(high);
        int i = (low);

        for (int j = low; j < high; j++) {
            if (array.get(j) <= pivot) {
                int swapTemp = array.get(i);
                array.set(i, array.get(j));
                array.set(j, swapTemp);
                i++;
            }
        }

        int swapTemp = array.get(i);
        array.set(i, array.get(high));
        array.set(high, swapTemp);

        return i;
    }
}
