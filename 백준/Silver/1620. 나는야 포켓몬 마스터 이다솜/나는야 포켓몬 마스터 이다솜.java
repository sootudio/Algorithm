import java.util.*;
import java.io.*;

public class Main {
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        Map<Integer, String> numKey = new HashMap<>();
        Map<String, Integer> nameKey = new HashMap<>();

        for(int i = 1; i <= N ; i++){
            String input = br.readLine();
            numKey.put(i, input);
            nameKey.put(input, i);
        }

        for(int i = 0 ; i < M ; i++){
            String quiz = br.readLine();
            char first = quiz.charAt(0);

            if(first <= '9'){ // 캐릭터로 비교
                int num = Integer.parseInt(quiz);
                sb.append(numKey.get(num)).append("\n");
            }
            else{
                sb.append(nameKey.get(quiz)).append("\n");
            }
        }

        sb.setLength(sb.length() - 1);
        System.out.println(sb);
    }
}