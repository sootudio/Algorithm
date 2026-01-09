import java.util.*;
import java.io.*;

public class Main {
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        Map<String, Integer> nList = new HashMap<>();
        List<String> mList = new ArrayList<>();

        for(int i = 0 ; i < N ; i++){
            nList.put(br.readLine(), i);
        }

        for(int i = 0 ; i < M ; i++){
            String input = br.readLine();
            if(nList.get(input) != null) mList.add(input);
        }

        int size = mList.size();

        String[] arr = new String[mList.size()];
        mList.toArray(arr);

        Arrays.sort(arr);

        sb.append(size).append("\n");
        for(int i = 0 ; i < size; i++){
            sb.append(arr[i]).append("\n");
        }

        sb.setLength(sb.length() - 1);
        System.out.println(sb);
    }
}