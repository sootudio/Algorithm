/*
문제 조건
- 정수가 하나 적혀있는 숫자 카드 N개
    - 1 <= N <= 500,000
    - -10,000,000 <= 정수 <= 10,000,000
- 정수 M개
    - 1 <= M <= 500,000
    - -10,000,000 <= 정수 <= 10,000,000
문제에서 구하고자 하는 것
- M개의 수에 대해서 N개의 카드 중 몇개를 가지고 있는지 출력
문제 해결을 위한 고민
- HashMap으로 해결
- 값을 입력받으면서 수를 센다.
*/

import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        Map<Integer, Integer> freq = new HashMap<>();

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < N ; i++){
            int input = Integer.parseInt(st.nextToken());
            freq.put(input, freq.getOrDefault(input, 0) + 1);
        }

        int M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());

        StringBuilder sb = new StringBuilder();
        for(int i = 0 ; i < M ; i++){
            int findNum = Integer.parseInt(st.nextToken());
            sb.append(freq.getOrDefault(findNum, 0)).append(" ");
        }

        sb.setLength(sb.length() - 1);

        System.out.println(sb);

    }
}
