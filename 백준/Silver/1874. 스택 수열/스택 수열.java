import java.util.*;
import java.io.*;

public class Main {
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());

        Deque<Integer> stack = new ArrayDeque<>();

        int next = 1;

        int[] targets = new int[N];

        for(int i = 0 ; i < N ; i++){
            targets[i] = Integer.parseInt(br.readLine());
        }

        for(int target : targets){
            while(next <= target){
                stack.push(next);
                next++;
                sb.append('+').append("\n");
            }
            if(stack.peek() == target){
                stack.pop();
                sb.append('-').append("\n");
            }
            else {
                System.out.println("NO");
                return;
            }
        }

        sb.setLength(sb.length() - 1);
        System.out.println(sb);
    }
}