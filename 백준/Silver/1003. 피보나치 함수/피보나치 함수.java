import java.io.*;

public class Main {
    
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        int tc = 0;
       
        while(tc < T){
            int N = Integer.parseInt(br.readLine());

            int[][] arr = new int[N+1][2];

            arr[0][0] = 1;
            arr[0][1] = 0;

            if(N > 0){
                arr[1][0] = 0;
                arr[1][1] = 1;
            }
            
            if(N > 1){
                arr[2][0] = 1;
                arr[2][1] = 1;
            }

            if(N > 2){
                for(int i = 3 ; i <= N ; i++){
                    arr[i][0] = arr[i-1][0] + arr[i-2][0];
                    arr[i][1] = arr[i-1][1] + arr[i-2][1];
                }
            }

            sb.append(arr[N][0]).append(" ").append(arr[N][1]).append("\n");

            tc++;
        }

        if(sb.length() > 0) sb.setLength(sb.length() - 1);
        System.out.println(sb);
    }
}