import java.io.*;

public class Main {
    
    public static void main(String[] arsg)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int result = 0;

        if(N == 1) result = 1;
        else if(N == 2) result = 2;
        else{
            int[] dp = new int[N+1];
            dp[1] = 1;
            dp[2] = 2;
            for(int i = 3 ; i <= N ; i++){
                dp[i] = (dp[i-1] + dp[i-2]) % 10007;
            }
            result = dp[N];
        }

        System.out.println(result);

       
    }
}