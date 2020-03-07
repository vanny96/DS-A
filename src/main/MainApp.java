package main;

import algorythms.HashAlgorythms;
import structures.ChainingMap;

import java.util.Scanner;

public class MainApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ChainingMap<Integer, String> map = new ChainingMap<Integer, String>(){
            {
                insert(1, "Ciao");
                insert(1, "Amico");
                insert(3, "Come va?");
            }
        };

        System.out.println(map.get(1));
        System.out.println(map.get(2));
        System.out.println(map.get(3));
    }
}
