package structures;

import algorythms.FindAlgorythms;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MinHeap<T> {
    int[] keys = new int[0];
    Map<Integer, T> elementsMap = new HashMap<>();

    public boolean changeKey(int previousKey, int newKey){
        int index = FindAlgorythms.linearSearch(keys, previousKey);

        if(index != -1){
            keys[index] = newKey;

            T element = elementsMap.remove(newKey);
            elementsMap.put(newKey, element);

            if(!minHeapify(index)){
                do{
                    index = (index+1)/2-1;
                }while (index >= 0 && minHeapify(index));
            }

            return true;
        } else {
            return false;
        }
    }

    public T extractMin(){
        int supportKey = keys[0];

        keys[0] = keys[keys.length-1];
        keys = Arrays.copyOf(keys, keys.length-1);

        minHeapify(0);

        return elementsMap.remove(supportKey);
    }

    public void insert(int key, T element){
        int[] newKeys = new int[keys.length+1];
        System.arraycopy(keys, 0, newKeys, 0, keys.length);
        keys = newKeys;

        keys[keys.length-1] = key;
        elementsMap.put(key, element);

        int index = keys.length-1;
        do{
            index = (index+1)/2-1;
        }while (index >= 0 && minHeapify(index));
    }

    private boolean minHeapify(int i){
        int[] childrenIndexes = getChildrensKeys(i);
        int minChildIndex = getMinIndex(childrenIndexes);

        if(minChildIndex != -1 && keys[i] > keys[minChildIndex]){
            int supportKey = keys[i];
            keys[i] = keys[minChildIndex];
            keys[minChildIndex] = supportKey;

            minHeapify(minChildIndex);
            return true;
        } else {
            return false;
        }
    }

    private int[] getChildrensKeys(int parentKey){
        // Logica strana per convertire indici 0,1,2... a 1,2,3... and back
        parentKey++;

        if(parentKey * 2 + 1 <= keys.length){
            return new int[]{parentKey * 2 - 1, parentKey * 2};
        } else if(parentKey * 2 <= keys.length){
            return new int[]{parentKey * 2 - 1};
        } else {
            return new int[0];
        }
    }

    private int getMinIndex(int[] indexes){
        int min = Integer.MAX_VALUE;
        int minIndex = -1;
        for(int value : indexes){
            if(keys[value] < min){
                min = keys[value];
                minIndex = value;
            }
        }
        return minIndex;
    }

    @Override
    public String toString() {
        return Arrays.toString(keys);
    }
}
