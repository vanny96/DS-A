package main;

import interfaces.CountSortable;
import sort.SortAlgorythms;
import structures.AVLtree;

import java.util.Arrays;

public class MainApp {
    public static void main(String[] args) {
        CountSortable[] array = new CountSortable[]{
            new CountSortable(10),
            new CountSortable(9),
            new CountSortable(8),
            new CountSortable(7),
            new CountSortable(6),
            new CountSortable(5),
            new CountSortable(4)
        };
        System.out.println("Array is: " + Arrays.toString(array) +
                "\nSorted id: " + Arrays.toString(SortAlgorythms.countingSort(array)));
    }
}
