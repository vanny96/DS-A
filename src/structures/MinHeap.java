package structures;

import algorythms.FindAlgorythms;

import java.util.Arrays;

public class MinHeap<T> {
    private int[] keys;
    private T[] elements;

    private int size = 0;

    @SuppressWarnings("unchecked")
    public MinHeap(int capacity) {
        this.keys = new int[capacity];
        this.elements = (T[]) new Object[capacity];
    }

    public void decreaseKey(int position, int newKey){
        if(keys[position] < newKey)
            throw new IllegalArgumentException("newKey is greater than existing value");

        keys[position] = newKey;
        do{
            position = (position+1)/2-1;
        }while (position >= 0 && minHeapify(position));
    }

    public T extractMin(){
        changeElementPosition(0, size-1);
        T element = elements[size-1];
        reduceSize();
        minHeapify(0);
        return element;
    }

    public void insert(int key, T element){
        if(size == keys.length)
            throw new IndexOutOfBoundsException("Heap is full");

        keys[size] = key;
        elements[size] = element;
        size++;

        int index = size-1;
        do{
            index = (index+1)/2-1;
        }while (index >= 0 && minHeapify(index));
    }

    private boolean minHeapify(int i){
        int[] childrenIndexes = getChildrensKeys(i);
        int minChildIndex = getMinIndex(childrenIndexes);

        if(minChildIndex != -1 && keys[i] > keys[minChildIndex]){
            changeElementPosition(i, minChildIndex);

            minHeapify(minChildIndex);
            return true;
        } else {
            return false;
        }
    }

    public int size(){
        return keys.length;
    }

    private int[] getChildrensKeys(int parentKey){
        // Logica strana per convertire indici 0,1,2... a 1,2,3... and back
        parentKey++;

        if(parentKey * 2 < size){
            return new int[]{parentKey * 2 - 1, parentKey * 2};
        } else if(parentKey * 2 - 1 < size){
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

    private void changeElementPosition(int positionA, int positionB){
        int supportKey = keys[positionA];
        keys[positionA] = keys[positionB];
        keys[positionB] = supportKey;

        T supportElement = elements[positionA];
        elements[positionA] = elements[positionB];
        elements[positionB] = supportElement;
    }

    private void reduceSize(){
        elements[size-1] = null;
        keys[size-1] = 0;
        size--;
    }

    public int indexOf(T element){
        return FindAlgorythms.linearSearch(
                Arrays.copyOfRange(elements, 0, size),
                element
        );
    }

    @Override
    public String toString() {
        return Arrays.toString(Arrays.copyOfRange(keys, 0, size));
    }
}
