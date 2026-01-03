import java.util.*;
import java.io.*;

public class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        int tc = 0;

        while(tc < T){
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            Deque<Integer> pq = new ArrayDeque<>();
            int[] priority = new int[N];

            st = new StringTokenizer(br.readLine());

            for(int i = 0 ; i < N ; i++){
                priority[i] = Integer.parseInt(st.nextToken());
                pq.add(i);
            }

            int cnt = 0;
            while(!pq.isEmpty()){
                int paper = pq.peek();
                boolean isLower = false;
                for(int i = 0 ; i < N ; i++){
                    if(priority[paper] < priority[i]) isLower = true;
                }
                if(!isLower){ // 1순위라면
                    cnt++;
                    paper = pq.pop();
                    priority[paper] = -1;
                    if(paper == M){
                        sb.append(cnt).append("\n");
                        break;
                    }
                }
                else{
                    pq.addLast(pq.removeFirst()); // 맨 뒤로 보내버리기
                }
            }
            tc++;
        }

        sb.setLength(sb.length() - 1);
        System.out.println(sb);
    }
    
}
