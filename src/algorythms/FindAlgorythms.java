package algorythms;

import structures.ChainingMap;

import java.util.Arrays;

public class FindAlgorythms {

    public static int linearSearch(int[] array, int value){
        for(int i = 0; i < array.length; i++){
            if(array[i] == value){
                return i;
            }
        }
        return -1;
    }

    public static int binarySearch(int[] array, int value){
        return binarySearch(array, value, array.length / 2);
    }

    private static int binarySearch(int[] array, int value, int localizer){
        if(array[array.length / 2] == value){
            return localizer;
        } else if(array.length <= 1) {
            return -1;
        }else if (array[array.length / 2] < value){
            return binarySearch(Arrays.copyOfRange(array, array.length / 2 + 1, array.length),
                    value,
                    ++localizer + array.length / 4);
        } else {
            return binarySearch(Arrays.copyOfRange(array, 0, array.length / 2),
                    value,
                    --localizer - array.length / 4);
        }
    }

    public static Integer stringMatching(String value, String text){
        ChainingMap<String, Integer> textMap = new ChainingMap<>();
        for(int i = 0; i <= text.length() - value.length(); i++){
            textMap.insert(text.substring(i, i + value.length()), i);
        }

        return textMap.get(value);
    }
}
