import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int cut = Math.round(N * 0.15f);

        int[] levels = new int[N];

        for(int i = 0 ; i < N ; i++){
            levels[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(levels);

        int sum = 0;
        for(int i = cut ; i < N - cut ; i++){
            sum += levels[i];
        }

        N -= cut * 2;

        sum = Math.round(sum/(float)N);

        System.out.println(sum);
    }
}