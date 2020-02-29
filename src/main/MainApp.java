package main;

import structures.BinarySearchTree;

public class MainApp {
    public static void main(String[] args) {
        BinarySearchTree tree = new BinarySearchTree(new int[]{1,2,3});
        System.out.println(tree);

        tree.leftRotate(tree.firstNode);
        System.out.println(tree);
    }
}
