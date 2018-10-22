package helpers;

public class PrintArray {

    public static void printArray(int[] arr) {
        for (int a : arr) {
            System.out.print(a + " ");
        }
        System.out.println();
    }

    public static void printLongArray(long[] arr) {
        for (long a : arr) {
            System.out.print(a + " ");
        }
        System.out.println();
    }

    // print 2-dim array
    public static void print2dimArray(int[][] arr) {
        for (int[] row : arr) {
            System.out.print("[");
            for (int ele : row) {
                System.out.print(ele + " ");
            }
            System.out.println("]");
        }
    }
}
