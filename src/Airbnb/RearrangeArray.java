package Airbnb;

import helpers.PrintArray;

import java.util.Arrays;

/**
 * 给一个array，要重排一下，使得 所有index为奇数的元素比所有index为偶数的元素大。虽然没见过但算很简单的题了，用个heap直接做就行。
 * Rearrange array to make all numbers at odd indexes larger than numbers at even indexes
 */
public class RearrangeArray {
    public static int[] rearrange(int[] array) {
        int[] copy = array.clone();
        Arrays.sort(copy);
        int index = 1;
        for (int i = copy.length - 1; i >= 0; i--) {
            if (index % 2 == 1 && index >= array.length) {
                index = 0;
            }
            if (index % 2 ==0 && index >= array.length) {
                return array;
            }
            array[index] = copy[i];
            index += 2;
        }
        return array;
    }
    public static void main(String[] args) {
        PrintArray.printArray(rearrange(new int[]{2,4,1,5,9}));
    }
}
