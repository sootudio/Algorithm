import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        String[][] arr = new String[N][2];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            arr[i][0] = st.nextToken(); // age
            arr[i][1] = st.nextToken(); // name
        }

        Arrays.sort(arr, (e1, e2) -> {
            int a1 = Integer.parseInt(e1[0]);
            int a2 = Integer.parseInt(e2[0]);
            return Integer.compare(a1, a2); // 나이만 비교(같으면 0 -> 입력순 유지)
        });

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            sb.append(arr[i][0]).append(' ').append(arr[i][1]).append('\n');
        }

        sb.setLength(sb.length() - 1);
        System.out.print(sb);
    }
}

