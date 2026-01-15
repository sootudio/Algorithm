import java.io.*;

public class Main {

    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        int tc = 0;

        long[] trian = new long[101];

        for(int i = 1 ; i <= 3 ; i++){
            trian[i] = 1;
        }

        for(int i = 4 ; i <= 5 ; i++){
            trian[i] = 2;
        }

        for(int i = 6 ; i <= 100 ; i++){
            trian[i] = trian[i-1] + trian[i-5];
        }
        

        while(tc < T){
            int N = Integer.parseInt(br.readLine());
            sb.append(trian[N]).append("\n");
            tc++;
        }

        if(sb.length() > 0) sb.setLength(sb.length() - 1);
        System.out.println(sb);
    }
}