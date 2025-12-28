import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        Deque<Character> stack = new ArrayDeque<>();

        String input = "";
        for(int i = 0 ; i < T ; i++){
            input = br.readLine();
            if(input.charAt(0) == ')'){
                sb.append("NO").append("\n");
                continue;
            } 
            stack.push(input.charAt(0));
            for(int j = 1 ; j < input.length() ; j++){
                char value = input.charAt(j);
                if(value == ')'){
                    if(stack.isEmpty()){
                        stack.push('A');
                        break;
                    }
                    else stack.pop();
                }
                else stack.push(value);
            }
            sb.append(stack.isEmpty() ? "YES" : "NO").append("\n");
            stack = new ArrayDeque<>();
        }

        sb.setLength(sb.length() - 1);

        System.out.println(sb);
    }
    
}