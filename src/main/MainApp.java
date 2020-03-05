package main;

import algorythms.HashAlgorythms;

import java.util.Scanner;

public class MainApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int a = 0;

        while (a != -1){
            System.out.println("Insert value to hash: ");
            a = Integer.parseInt(scanner.nextLine());

            String format = "%35s %s\n";
            System.out.format(format, "Divide hash (max 173): ", HashAlgorythms.divideHash(a, 173));
            System.out.format(format, "Stupid multiply hash (max 256): ", HashAlgorythms.stupidMultiplyHash(a, 256));
            System.out.format(format, "Clever multiply hash (max 256): ", HashAlgorythms.cleverMultiplyHash(a, 256));
        }
    }
}
