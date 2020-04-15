package main;

import structures.MinHeap;

import java.util.Scanner;

public class MainApp {
    public static void main(String[] args) {
        MinHeap<Integer> map = new MinHeap<>(10);
        Scanner scanner = new Scanner(System.in);
        int a;
        do{
            a = Integer.parseInt(scanner.nextLine());
            if(a != -1) {
                map.insert(a, 100);
                System.out.println(map);
            }
        }while (a != -1);

        map.decreaseKey(4, 0);
        System.out.println(map);
    }
}
