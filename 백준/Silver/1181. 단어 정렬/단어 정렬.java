import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        Set<String> set = new HashSet<>();

        int N = Integer.parseInt(br.readLine());
        
        for(int i = 0 ; i < N ; i++){
            set.add(br.readLine());
        }

        List<String> list = new ArrayList<>(set);

        list.sort((a,b) -> {
            if(a.length() != b.length()) return a.length() - b.length();
            return a.compareTo(b);
        });
        
        for(int i = 0 ; i < set.size() ; i++){
            sb.append(list.get(i)).append("\n");
        }

        sb.setLength(sb.length()-1);

        System.out.println(sb);
    }
}
