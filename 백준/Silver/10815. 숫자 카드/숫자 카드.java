import java.util.*;
import java.io.*;

public class Main {
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        Set<Integer> set = new HashSet<>();

        StringTokenizer st = new StringTokenizer(br.readLine());

        for(int i = 0 ; i < N ; i++){
            set.add(Integer.parseInt(st.nextToken()));
        }

        int M = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());

        for(int i = 0 ; i < M ; i++){
            int num = Integer.parseInt(st.nextToken());
            if(set.contains(num)) sb.append("1 ");
            else sb.append("0 ");
        }

        sb.setLength(sb.length() - 1);
        System.out.println(sb);
    }
}
