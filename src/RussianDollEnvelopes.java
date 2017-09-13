import java.util.Arrays;

public class RussianDollEnvelopes {

    private static int maxEnvelopes(int[][] envelopes) {
        if(envelopes == null || envelopes.length == 0)
            return 0;

        // using lambda instead of Comparator
        Arrays.sort(envelopes, (int[] a, int[] b) -> a[0]!=b[0] ? a[0]-b[0] : a[1]-b[1]);

//        Arrays.sort(envelopes, new Comparator<int[]>(){
//            public int compare(int[] a, int[] b){
//                if(a[0] != b[0]){
//                    return a[0] - b[0];
//                }else{
//                    return a[1] - b[1];
//                }
//            }
//        });

        int max = 1;
        int[] dp = new int[envelopes.length];
        for(int i = 0; i < envelopes.length; i++){
            dp[i] = 1;
            for(int j = i-1; j >= 0; j--){
                if(envelopes[i][0] > envelopes[j][0] && envelopes[i][1] > envelopes[j][1]){
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            max = Math.max(max, dp[i]);
        }

        return max;
    }

    public static void main(String... args) {
        System.out.println("Hello world!");
        int[][] input = {{5, 4}, {6, 4}, {6, 7}, {2, 3}};
        System.out.println(maxEnvelopes(input));
    }
}