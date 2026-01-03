import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        boolean[] isComposite = new boolean[N+1];
        isComposite[0] = true;
        isComposite[1] = true;

        int limit = (int) Math.sqrt(N); // N의 제곱까지만 검사
        // 지우기 시작
        for(int i = 2 ; i <= limit ; i++){
            if(!isComposite[i]){ // 소수이면
                for(int p = i * i ; p <= N ; p += i){
                    isComposite[p] = true; // 합성수 처리
                }
            }
        }

        for(int i = M ; i <= N ; i++){
            if(!isComposite[i]) sb.append(i).append("\n");
        }

        sb.setLength(sb.length() - 1);
        System.out.println(sb);
    }
}