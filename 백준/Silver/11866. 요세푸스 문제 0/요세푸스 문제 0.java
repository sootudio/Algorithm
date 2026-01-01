import java.util.*;
import java.io.*;

public class Main {
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        Deque<Integer> q = new ArrayDeque<>();

        for(int i = 1 ; i <= N ; i++){
            q.add(i);
        }

        sb.append("<");

        while(!q.isEmpty()){
            for(int i = 0 ; i < K-1 ; i++){
                q.addLast(q.removeFirst());
            }
            sb.append(q.removeFirst()).append(", ");
        }

        sb.setLength(sb.length() - 2);
        sb.append(">");

        System.out.println(sb);
    }
}