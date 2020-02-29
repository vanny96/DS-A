package structures;

import java.util.LinkedList;
import java.util.Queue;

public class BinarySearchTree {
    public BinarySearchNode firstNode;

    public BinarySearchTree() {
    }

    public BinarySearchTree(int[] array){
        firstNode = new BinarySearchNode(array[0]);

        for(int i = 1; i < array.length; i++){
            insert(array[i]);
        }
    }

    // Insert methods
    public void insert(int value){
        insert(value, firstNode);
    }

    private void insert(int value, BinarySearchNode node){
        if(value < node.element) {
            if(node.leftChild == null){
                node.leftChild = new BinarySearchNode(value);
                node.leftChild.parent = node;
            } else {
                insert(value, node.leftChild);
            }
        } else {
            if(node.rightChild == null){
                node.rightChild = new BinarySearchNode(value);
                node.rightChild.parent = node;
            } else {
                insert(value, node.rightChild);
            }
        }
    }

    /*
    It needs 3 operations:
    1) child of parent -> rightChild of node
    2) leftChild of rightChild -> node
    3) rightChild of node -> leftChild of rightChild
    */
    public void leftRotate(BinarySearchNode node){
        //1) child of parent -> rightChild of node
        if(node.parent != null) {
            if (node.parent.rightChild == node) {
                node.parent.rightChild = node.rightChild;
            } else {
                node.parent.leftChild = node.rightChild;
            }
        } else {
            firstNode = node.rightChild;
        }
        node.parent = node.rightChild;

        //2) leftChild of rightChild -> node
        BinarySearchNode newRightChild = node.rightChild.leftChild;
        node.rightChild.leftChild = node;

        //3) rightChild of node -> leftChild of rightChild
        node.rightChild = newRightChild;
    }

    protected void rightRotate(BinarySearchNode node){
        if(node.parent != null) {
            if (node.parent.rightChild == node) {
                node.parent.rightChild = node.leftChild;
            } else {
                node.parent.leftChild = node.leftChild;
            }
        } else {
            firstNode = node.leftChild;
        }
        node.parent = node.leftChild;

        BinarySearchNode newLeftChild = node.leftChild.rightChild;
        node.leftChild.rightChild = node;

        node.leftChild = newLeftChild;
    }

    // Search operation
    public boolean search(int value, SearchOperation checkOperation){
        return search(value, checkOperation, firstNode);
    }

    private boolean search(int value, SearchOperation checkOperation, BinarySearchNode node){
        if(checkOperation.check(node.element, value)){
            return true;
        }

        return value < node.getElement()
                ? node.getLeftChild() != null && search(value, checkOperation, node.getLeftChild())
                : node.getRightChild() != null && search(value, checkOperation, node.getRightChild());
    }

    @FunctionalInterface
    public interface SearchOperation{
        boolean check(int a, int b);
    }

    @Override
    public String toString() {
        Queue<BinarySearchNode> queue = new LinkedList<>();
        queue.add(firstNode);

        StringBuilder stamp = new StringBuilder("[");
        while(!queue.isEmpty()){
            BinarySearchNode node = queue.poll();

            if(node.getLeftChild() != null){
                queue.add(node.getLeftChild());
            }

            if(node.getRightChild() != null){
                queue.add(node.getRightChild());
            }

            stamp.append(node.getElement()).append(",");
        }
        stamp.deleteCharAt(stamp.length()-1).append("]");
        return stamp.toString();
    }

    protected static class BinarySearchNode{
        private BinarySearchNode parent;
        private BinarySearchNode leftChild;
        private BinarySearchNode rightChild;
        private int element;

        public BinarySearchNode(int element) {
            this.element = element;
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
            return String.valueOf(element);
        }
    }
}


