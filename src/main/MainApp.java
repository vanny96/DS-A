package main;

import structures.MinHeap;
import util.HorseChessPiece;

import java.awt.*;
import java.util.Scanner;

public class MainApp {
    public static void main(String[] args) {
        MinHeap<Integer> map = new MinHeap<>();
        Scanner scanner = new Scanner(System.in);
        int a;
        do{
            a = Integer.parseInt(scanner.nextLine());
            if(a != -1) {
                map.insert(a, 100);
                System.out.println(map);
            }
        }while (a != -1);

        map.changeKey(0, 10);
        System.out.println(map);
    }
}
