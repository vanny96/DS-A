package structures;

import java.util.LinkedList;
import java.util.Queue;

public class AugmentedBinarySeatchTree {
    private BinarySearchNode firstNode;

    public AugmentedBinarySeatchTree(int[] array, SearchOperation checkOperation) {
        this.firstNode = new BinarySearchNode(array[0]);

        for(int i = 1; i < array.length; i++){
            insert(array[i], checkOperation);
        }
    }

    public int rank(int t){
        return rank(t, firstNode);
    }

    private int rank(int t, BinarySearchNode node){
        if(node == null) {
            return 0;
        }

        if(node.element <= t){
            return 1 + rank(t, node.rightChild) + (node.leftChild == null ? 0 : node.leftChild.size);
        } else {
            return rank(t, node.leftChild);
        }
    }

    public boolean insert(int value, SearchOperation checkOperation){
        return insert(value, checkOperation, firstNode);
    }

    private boolean insert(int value, SearchOperation checkOperation, BinarySearchNode node){
        if(checkOperation.check(value, node.element)){
            return false;
        }

        if(value < node.element){
            if(node.leftChild == null){
                node.leftChild = new BinarySearchNode(value);
                node.size++;
                return true;
            }

            boolean inserted = insert(value, checkOperation, node.leftChild);
            if(inserted)
                node.size++;
            return inserted;
        } else {
            if(node.rightChild == null){
                node.rightChild = new BinarySearchNode(value);
                node.size++;
                return true;
            }

            boolean inserted = insert(value, checkOperation, node.rightChild);
            if(inserted)
                node.size++;
            return inserted;
        }
    }

    @Override
    public String toString() {
        Queue<BinarySearchNode> queue = new LinkedList<>();
        queue.add(firstNode);

        StringBuilder stamp = new StringBuilder("[");
        while(!queue.isEmpty()){
            BinarySearchNode node = queue.poll();

            if(node.leftChild != null){
                queue.add(node.getLeftChild());
            }

            if(node.rightChild != null){
                queue.add(node.getRightChild());
            }

            stamp.append(node.toString()).append(",");
        }
        stamp.deleteCharAt(stamp.length()-1).append("]");
        return stamp.toString();
    }

    @FunctionalInterface
    public interface SearchOperation{
        boolean check(int a, int b);
    }

    private static class BinarySearchNode{
        private BinarySearchNode parent;
        private BinarySearchNode leftChild;
        private BinarySearchNode rightChild;
        private int size = 1;
        private int element;

        public BinarySearchNode(int element) {
            this.element = element;
        }

        public int getSize() {
            return size;
        }

        public BinarySearchNode setSize(int size) {
            this.size = size;
            return this;
        }

        public BinarySearchNode getParent() {
            return parent;
        }

        public BinarySearchNode setParent(BinarySearchNode parent) {
            this.parent = parent;
            return this;
        }

        public BinarySearchNode getLeftChild() {
            return leftChild;
        }

        public BinarySearchNode setLeftChild(BinarySearchNode leftChild) {
            this.leftChild = leftChild;
            return this;
        }

        public BinarySearchNode getRightChild() {
            return rightChild;
        }

        public BinarySearchNode setRightChild(BinarySearchNode rightChild) {
            this.rightChild = rightChild;
            return this;
        }

        public int getElement() {
            return element;
        }

        public BinarySearchNode setElement(int element) {
            this.element = element;
            return this;
        }

        @Override
        public String toString() {
            return "{"+element+", "+size+"}";
        }
    }
}
