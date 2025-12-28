import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int K = Integer.parseInt(br.readLine());
        Deque<Integer> stack = new ArrayDeque<>();

        for(int i = 0 ; i < K ; i++){
            int input = Integer.parseInt(br.readLine());
            if(input == 0) stack.pop();
            else stack.push(input);
        }

        int result = 0;

        while(!stack.isEmpty()){
            result += stack.pop();
        }

        System.out.println(result);
    }
}