import java.util.*;
import java.io.*;

public class Main {
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[] arr = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());

        for(int i = 0 ; i < N ; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        int sum = arr[0];
        int wait = 0;

        if(N > 1){
            for(int i = 1 ; i < N ; i++){
                wait += arr[i-1];
                sum += wait + arr[i];
            }
        }

        System.out.println(sum);
        
    }
}
