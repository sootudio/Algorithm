import java.io.*;

public class Main {

    static int[] nums = {1, 2, 3};
    static int N, cnt = 0;

    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        int tc = 0;

        while(tc < T){
            N = Integer.parseInt(br.readLine());
            cnt = 0;
            count(0);
            sb.append(cnt).append("\n");
            tc++;
        }

        if(sb.length() > 0) sb.setLength(sb.length() - 1);
        System.out.println(sb);
    }

    private static void count(int sum){
        if(sum >= N){
            if(sum == N) cnt++;
            return;
        }
        for(int i = 0 ; i < 3 ; i++){
            count(sum + nums[i]);
        }
    }
}