import java.util.*;
import java.io.*;

public class Main {
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        int[][] dots = new int[N][2];
        
        for(int i = 0 ; i < N ; i++){
            st = new StringTokenizer(br.readLine());
            dots[i][0] = Integer.parseInt(st.nextToken());
            dots[i][1] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(dots, (a, b) -> {
            if(a[1] == b[1]) return Integer.compare(a[0], b[0]);
            return Integer.compare(a[1], b[1]);
        });

        for(int i = 0 ; i < N ; i++){
            sb.append(dots[i][0]).append(" ").append(dots[i][1]).append("\n");
        }

        sb.setLength(sb.length() - 1);

        System.out.println(sb);
    }
}
