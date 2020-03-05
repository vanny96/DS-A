package algorythms;

import util.Util;

import java.util.Arrays;

public class HashAlgorythms {

    // maxValue better be NOT a power of 2
    public static Integer divideHash(Integer value, Integer maxValue){
        return value % maxValue;
    }

    // maxValue better be a power of 2
    public static Integer stupidMultiplyHash(Integer value, Integer maxValue){
        // Knuth constant
        double constant = (Math.sqrt(5) - 1) / 2d;

        // Round up to closer integer after multiplying the maxVaue for the division with only decimals
        return (int) Math.floor(maxValue * ((value / constant) - (int) (value / constant)));
    }

    public static Integer cleverMultiplyHash(Integer value, Integer maxValue){
        /*
        Formula is: (a * k) mod 2^w >> (w - r) where:
        a = A * 2^w     A = (sqrt(5) - 1) / 2
        k = value to hash
        w = number of bits allowed for system word (32bit)
        r = number of bits needed to rappresent maxValue values - 1
         */
        double A = (Math.sqrt(5) -  1) / 2;
        int w = 32;
        double a = A * Math.pow(2, w);
        int k = value;
        int r = (int) (Math.log(maxValue) / Math.log(2));

        return (int) ((long) ((a * k) % Math.pow(2, w)) >> (w - r));
    }


}
