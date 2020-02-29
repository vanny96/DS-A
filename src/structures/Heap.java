package structures;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

// Priority queue, has insert, max-value, extract-max-value and change-key as methods
public class Heap<T> {

    public int[] keys = new int[0];
    private Map<Integer, T> elements = new HashMap<>();

    public T extracMax(){
        int c = keys[0];
        keys[0] = keys[keys.length-1];
        keys[keys.length-1] = c;

        keys = Arrays.copyOf(keys, keys.length-1);
        buildMaxHeap();

        return elements.remove(c);
    }

    public void insert(Integer key, T element){
        // Aggiunge la nuova chiave alla fine dell'array allungandolo
        int[] newKeys = new int[keys.length+1];
        System.arraycopy(keys, 0, newKeys, 0, keys.length);
        newKeys[keys.length] = key;
        keys = newKeys;

        keys[keys.length-1] = key;

        elements.put(key, element);

        for(int i = keys.length/2-1; i>=0; i=(i+1)/2-1){
            maxHeapify(i);
        }
    }

    public void buildMaxHeap(){
        for(int i = keys.length / 2; i >= 0; i--){
            maxHeapify(i);
        }
    }

    // If left(i) and right(i) are max-heaps it works
    private void maxHeapify(int i){
        int[] childrensIndexes = getChildrensKeys(i);
        int maxIndex = getMaxIndex(childrensIndexes);

        if(maxIndex != -1 && keys[i] < keys[maxIndex]){
            int c = keys[i];
            keys[i] = keys[maxIndex];
            keys[maxIndex] = c;
        }

        for(int childrenIndex : childrensIndexes){
            maxHeapify(childrenIndex);
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

    private int getMaxIndex(int[] indexes){
        int max = -1;
        int maxIndex = -1;
        for(int value : indexes){
            if(keys[value] > max){
                max = keys[value];
                maxIndex = value;
            }
        }
        return maxIndex;
    }

    @Override
    public String toString() {
        return Arrays.toString(keys);
    }
}
