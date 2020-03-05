package util;

public class Util {
    public static byte[] binaryConverter(int value){
        byte[] binaryArray = new byte[32];

        for(int i = 31; i >= 0; i--){
            binaryArray[i] = (byte) (value % 2);
            value = value / 2;
        }

        return binaryArray;
    }
}
