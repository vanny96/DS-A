package algorythms;

import structures.ChainingMap;
import structures.DTA;

import java.util.Arrays;

public class FindAlgorythms {

    public static <T> int linearSearch(T[] array, T value){
        for(int i = 0; i < array.length; i++){
            if(array[i].equals(value)){
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

    //O(n + k)ò
    public static Integer stringMatching(String value, String text){
        ChainingMap<String, Integer> textMap = new ChainingMap<>();
        for(int i = 0; i <= text.length() - value.length(); i++){
            textMap.insert(text.substring(i, i + value.length()), i);
        }

        return textMap.get(value);
    }

    public static Integer karpRabinStringMatching(String value, String text){
        DTA valueDta = new DTA();
        for(char c : value.toCharArray()){
            valueDta.append(c);
        }

        DTA textDta = new DTA();
        for(char c : text.substring(0, value.length()).toCharArray()){
            textDta.append(c);
        }

        if(valueDta.getPreHash() == textDta.getPreHash() &&
        value.equals(text.substring(0, 3))){
            return 0;
        }
        for (int i = value.length(); i < text.length(); i++){
            textDta.skip(text.charAt(i - value.length()));
            textDta.append(text.charAt(i));

            if(textDta.getPreHash() == valueDta.getPreHash() &&
                value.equals(text.substring(i- value.length() + 1, i + 1))) {

                return i - value.length() + 1;
            }
        }

        return null;
    }
}
