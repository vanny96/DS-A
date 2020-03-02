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
        Arrays.fill(keys, new LinkedList<>());

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

    public static <T extends CountSortable> T[] radixSort(T[] elements){
        Class<?> elementClass = null;
        if(elements.length > 0){
            elementClass = elements[0].getClass();
        } else {
            return null;
        }

        // Get useful data
        Integer max = Integer.MIN_VALUE;
        Integer min = Integer.MAX_VALUE;
        for(T element : elements){
            if(element.getId() < min)
                min = element.getId();
            if(element.getId() > max)
                max = element.getId();
        }
        Integer numberOfDigitsAtBaseN = (int) (Math.log(max - min + 2) / Math.log(elements.length)) + 1;

        // Prepare sortable elemnets
        @SuppressWarnings("unchecked")
        RadixSortUtility<T>[] alteredElements = new RadixSortUtility[elements.length];
        for(int i = 0; i < elements.length; i++){
            Integer[] idAsBaseN = changeIntBase(elements[i].getId() - min, elements.length, numberOfDigitsAtBaseN);
            alteredElements[i] = new RadixSortUtility<T>(idAsBaseN);
            alteredElements[i].object = elements[i];
        }

        // Sort
        for(int i = numberOfDigitsAtBaseN - 1; i >= 0; i--){
            alteredElements = alteredCountingSort(alteredElements, i);
        }

        // Retrieve objects
        @SuppressWarnings("unchecked")
        T[] newArray = (T[]) Array.newInstance(elementClass, elements.length);
        for(int i = 0; i < newArray.length; i++){
            newArray[i] = alteredElements[i].object;
        }

        return newArray;
    }

    private static <T extends CountSortable> RadixSortUtility<T>[] alteredCountingSort(RadixSortUtility<T>[] elements, int index) {
        // N time
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for(RadixSortUtility element : elements){
            element.setId(element.ids[index]);
            if(element.getId() > max)
                max = element.getId();
            if(element.getId() < min)
                min = element.getId();
        }

        // K time
        @SuppressWarnings("unchecked")
        LinkedList<RadixSortUtility<T>>[] keys = new LinkedList[max - min + 1];
        for(int i = 0; i < keys.length; i++){
            keys[i] = new LinkedList<>();
        };

        // N time
        for(RadixSortUtility element : elements){
            keys[element.getId() - min].add(element);
        }

        // K + N time
        @SuppressWarnings("unchecked")
        RadixSortUtility<T>[] finalArray = (RadixSortUtility<T>[]) Array.newInstance(RadixSortUtility.class, elements.length);

        int counter = 0;
        for (LinkedList<RadixSortUtility<T>> key : keys) { // This takes K time
            for (RadixSortUtility<T> t : key) { // This take N time in total
                finalArray[counter] = t;
                counter++;
            }
        }

        return finalArray;
    }

    private static Integer[] changeIntBase(Integer value, Integer base, Integer numberOfDigits) {
        Integer[] intAsNewBase = new Integer[numberOfDigits];
        int number = value;

        for(int i = intAsNewBase.length - 1; i >= 0; i--){
            intAsNewBase[i] = value % base;
            value = value / base;
        }

        return intAsNewBase;
    }

    private static class RadixSortUtility<T extends CountSortable> extends CountSortable{
        private T object;
        private Integer[] ids;

        public RadixSortUtility(Integer[] ids) {
            this.ids = ids;
        }
    }
}
