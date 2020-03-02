package main;

import interfaces.CountSortable;
import sort.SortAlgorythms;
import structures.AVLtree;

import java.util.Arrays;

public class MainApp {
    public static void main(String[] args) {
        CountSortable[] array = new CountSortable[]{
                new CountSortable(5),
                new CountSortable(4),
                new CountSortable(3),
                new CountSortable(2),
                new CountSortable(1)
        };

        System.out.println("Array: " + Arrays.toString(array) + "\nSorted Array: "
                + Arrays.toString(SortAlgorythms.radixSort(array)));
    }
}
