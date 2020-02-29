package sort;

import java.util.Arrays;

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
}
