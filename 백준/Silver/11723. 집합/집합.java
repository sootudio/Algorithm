import java.util.*;
import java.io.*;

public class Main {
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer("");
        StringBuilder sb = new StringBuilder();

        int M = Integer.parseInt(br.readLine());

        Set<Integer> set = new HashSet<>();

        String command = "";
        int x = 0;

        for(int i = 0 ; i < M ; i++){
            st = new StringTokenizer(br.readLine());

            command = st.nextToken();
            
            switch(command){
                case "add":
                    x = Integer.parseInt(st.nextToken());
                    set.add(x);
                    break;
                case "remove":
                    x = Integer.parseInt(st.nextToken());
                    if(set.contains(x)) set.remove(x);
                    break;
                case "check":
                    x = Integer.parseInt(st.nextToken());
                    sb.append(set.contains(x)? 1 : 0).append("\n");
                    break;
                case "toggle":
                    x = Integer.parseInt(st.nextToken());
                    if(set.contains(x)) set.remove(x);
                    else set.add(x);
                    break;
                case "all":
                    set.clear();
                    for(int j = 1 ; j <= 20 ; j++ ) set.add(j);
                    break;
                case "empty":
                    set.clear();
                    break;
            }
        }

        if(sb.length() > 0) sb.setLength(sb.length() - 1);
        System.out.println(sb);
    }
}