package structures;

import algorythms.HashAlgorythms;

import java.util.LinkedList;
import java.util.ListIterator;

public class ChainingMap<K, T> {
    private int mapLength = 2;
    private LinkedList<MapNode<K, T>>[] map;
    private int size;

    @SuppressWarnings("unchecked")
    public ChainingMap() {
        map = new LinkedList[mapLength];
    }

    @SuppressWarnings("unchecked")
    private ChainingMap(int mapDefaultLength){
        this.mapLength = mapDefaultLength;
        this.map = new LinkedList[mapDefaultLength];
    }

    public void insert(K key, T element){
        // Hash the key
        int hash = hash(key);
        MapNode<K, T> newNode = new MapNode<>(key, element);

        // Check if the list is instantiated, if not instantiate and add element
        if(map[hash] == null){
            map[hash] = new LinkedList<>();
            map[hash].add(newNode);
        } else {

            // If instantiated check the elements so that you can override the existing value
            ListIterator<MapNode<K, T>> iterator = map[hash].listIterator();
            while (iterator.hasNext()){
                if(iterator.next().key == key){
                    iterator.set(newNode);
                    return;
                }
            }

            // If value is found, method returns without updating the size, otherwise adds the node to
            // existing list
            map[hash].add(newNode);
        }

        size++;

        if(size > mapLength){
            changeMapLength(length -> length * 2);
        }
    }

    public void delete(K key){
        int hash = hash(key);

        if(map[hash] != null){
            ListIterator<MapNode<K, T>> iterator = map[hash].listIterator();
            while (iterator.hasNext()){
                if(iterator.next().key == key){
                    iterator.remove();
                    size--;

                    if(size < mapLength / 4)
                        changeMapLength(length -> length / 2);

                    return;
                }
            }
        }

    }

    public T get(K key){
        int hash = hash(key);

        if(map[hash] == null)
            return null;

        for(MapNode<K, T> node : map[hash]){
            if(node.key == key)
                return node.element;
        }

        return null;
    }

    public int size(){
        return size;
    }

    protected void changeMapLength(MapLengthOperation operation){
        this.mapLength = operation.invoke(this.mapLength);

        ChainingMap<K, T> newMap = new ChainingMap<>(this.mapLength);

        for(LinkedList<MapNode<K, T>> list : map){
            if(list != null){
                for(MapNode<K, T> node : list){
                    newMap.insert(node.key, node.element);
                }
            }
        }

        this.map = newMap.map;
    }

    protected int preHash(K key){
        return key.hashCode();
    }

    protected int hash(K key){
        return HashAlgorythms.cleverMultiplyHash(preHash(key), mapLength);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        for(LinkedList<MapNode<K, T>> list : map){
            if(list != null) {
                for (MapNode<K, T> node : list) {
                    builder.append("{")
                            .append(node.key)
                            .append(", ")
                            .append(node.element)
                            .append(" }");
                }
            }
        }
        builder.append("]");
        return builder.toString();
    }

    private static class MapNode<K, T>{
        private K key;
        private T element;

        public MapNode(K key, T element) {
            this.key = key;
            this.element = element;
        }
    }

    @FunctionalInterface
    public interface MapLengthOperation{
        int invoke(int mapLength);
    }
}
