

import java.util.*;
import java.io.*;

public class Main {
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        int[][] people = new int[N][3];

        for(int i= 0 ; i < N ; i++){
            st = new StringTokenizer(br.readLine());
            people[i][0] = Integer.parseInt(st.nextToken());
            people[i][1] = Integer.parseInt(st.nextToken());
        }

        for(int i = 0 ; i < N ; i++){
            for(int j = 0 ; j < N; j++){
                if(i == j) continue;
                else if(people[i][0] < people[j][0]){
                    if(people[i][1] < people[j][1]) people[i][2] ++;
                    else continue; 
                } else continue;
            }
            sb.append(people[i][2]+1).append(" ");
        }

        sb.setLength(sb.length() - 1);

        System.out.println(sb);
    }
}
