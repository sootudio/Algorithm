import java.util.*;
import java.io.*;

public class Main {
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        Map<String, String> map = new HashMap<>();

        for(int i = 0 ; i < N ; i++){
            st = new StringTokenizer(br.readLine());
            map.put(st.nextToken(), st.nextToken());
        }

        StringBuilder sb = new StringBuilder();

        for(int i = 0 ; i < M ; i++){
            sb.append(map.get(br.readLine())).append("\n");
        }

        if(sb.length() > 0) sb.setLength(sb.length() - 1);
        System.out.println(sb);
    }
}