package helpers;

public class PrintArray {

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
