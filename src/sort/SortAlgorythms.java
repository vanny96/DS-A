package sort;

import interfaces.CountSortable;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;

public class SortAlgorythms {

    public static int[] bubbleSort(int[] array){
        boolean flag = true;

        while (flag) {
            flag = false;
            for (int i = 1; i < array.length; i++) {
                if (array[i] < array[i - 1]) {
                    int c = array[i - 1];
                    array[i - 1] = array[i];
                    array[i] = c;
                    flag = true;
                }
            }
        }

        return array;
    }

    public static int[] insertionSort(int[] array){

        for(int i = 1; i < array.length; i++){
            int scale = 0;

            while(i-scale-1 >= 0 && array[i] < array[i-scale-1]){
                scale++;
            }

            int c = array[i];

            for(int j = i; j > i-scale; j--){
                array[j] = array[j-1];
            }

            array[i-scale] = c;
        }

        return array;
    }

    public static int[] mergeSort(int[] array){

        if(array.length <= 1){
            return array;
        }

        int[] leftArray = mergeSort(Arrays.copyOfRange(array, 0, array.length / 2));
        int[] rightArray = mergeSort(Arrays.copyOfRange(array, (array.length/2) , array.length));

        int[] newArray = new int[array.length];

        int i = 0;
        int j = 0;

        while(i + j < array.length){
            if (j >= rightArray.length || (i < leftArray.length && leftArray[i] < rightArray[j])){
                newArray[i+j] = leftArray[i];
                i++;
            } else if (i >= leftArray.length || (rightArray[j] < leftArray[i])){
                newArray[i+j] = rightArray[j];
                j++;
            }
        }

        return newArray;
    }

    public static <T extends CountSortable> T[] countingSort(T[] elements){
        Class<?> elementClass = null;
        if(elements.length > 0){
            elementClass = elements[0].getClass();
        } else {
            return null;
        }

        // N time
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for(T element : elements){
            if(element.getId() > max)
                max = element.getId();
            if(element.getId() < min)
                min = element.getId();
        }

        // K time
        @SuppressWarnings("unchecked")
        LinkedList<T>[] keys = new LinkedList[max - min + 1];
        for(int i = 0; i < keys.length; i++){
            keys[i] = new LinkedList<>();
        }

        // N time
        for(T element : elements){
            keys[element.getId() - min].add(element);
        }

        // K + N time
        @SuppressWarnings("unchecked")
        T[] finalArray = (T[]) Array.newInstance(elementClass, elements.length);
        int counter = 0;
        for (LinkedList<T> key : keys) { // This takes K time
            for (T t : key) { // This take N time in total
                finalArray[counter] = t;
                counter++;
            }
        }

        return finalArray;
    }
}
