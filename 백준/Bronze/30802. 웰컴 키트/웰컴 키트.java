import java.util.*;
import java.io.*;

public class Main {
    private static int N;
    private static int[] shirts;
    private static int T;
    private static int P;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        shirts = new int[6];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < 6 ; i++){
            shirts[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        T = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());

        int cnt = 0;
        for(int i = 0 ; i < 6; i++){
            cnt += shirts[i]/T;
            if(shirts[i]%T != 0) cnt ++;
        }
        System.out.println(cnt);
        System.out.println(N/P + " " + N%P);
    }
}
