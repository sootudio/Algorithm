import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int num = Integer.parseInt(br.readLine());
        int[][] dots = new int[num][2];

        for(int i = 0 ; i < num ; i++){
            st = new StringTokenizer(br.readLine());
            dots[i][0] = Integer.parseInt(st.nextToken());
            dots[i][1] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(dots, (e1, e2) -> {
            int x1 = e1[0];
            int x2 = e2[0];
            int y1 = e1[1];
            int y2 = e2[1];
            if(x1 != x2)
                return Integer.compare(x1, x2);
            else
                return Integer.compare(y1, y2);
        });

        StringBuilder sb = new StringBuilder();
        for(int i = 0 ; i < num ; i++){
            sb.append(dots[i][0]).append(" ").append(dots[i][1]).append("\n");
        }

        sb.setLength(sb.length() -1);

        System.out.println(sb);
    }
    
}
