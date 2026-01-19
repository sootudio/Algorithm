import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[] dp = new int[N+1];

        dp[0] = 0;

        for(int i = 1 ; i <= N ; i++){
            int min = i;
            for(int j = 1 ; (int) Math.pow((double)j, 2) <= i ; j++){
                min = Math.min(min, dp[i - (int) Math.pow((double) j, 2)] + 1);
            }
            dp[i] = min;
        }

        System.out.println(dp[N]);
    }
}