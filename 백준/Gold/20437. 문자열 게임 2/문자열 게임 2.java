import java.io.*;
import java.util.*;
 
public class Main {
 
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
 
        int t = Integer.parseInt(br.readLine());
        for(int i = 0; i < t; i++) {
            String str = br.readLine();
            int k = Integer.parseInt(br.readLine()); 
 
            if(k == 1) {
                System.out.println("1 1");
                continue;
            }
 
            int[] alphabet = new int[26];
            for(int j = 0; j < str.length(); j++) {
                alphabet[str.charAt(j) - 'a']++;
            }
 
            int min = Integer.MAX_VALUE;
            int max = -1;
            for(int j = 0; j < str.length(); j++) {
                if(alphabet[str.charAt(j) - 'a'] < k) continue;
 
                int count = 1;
                for(int l = j + 1; l < str.length(); l++) {
                    if(str.charAt(j) == str.charAt(l)) count++;
                    if(count == k) {
                        min = Math.min(min, l - j + 1);
                        max = Math.max(max, l - j + 1);
                        break;
                    }
                }
            }
            if(min == Integer.MAX_VALUE || max == -1) System.out.println("-1");
            else System.out.println(min + " " + max);
        }
    }        
}
