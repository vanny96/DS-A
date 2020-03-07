package main;

public class SecondaryApp {
    public static void main(String[] args) {
        int a = 0b01001000;
        int b = 0xf;
        int c = a & b;
        int d = a % b;
        System.out.println( a + ", " + b + ", " + c + ", " + d);
    }
}
