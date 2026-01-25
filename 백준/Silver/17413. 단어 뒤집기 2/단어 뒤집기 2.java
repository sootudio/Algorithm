import java.util.*;
import java.io.*;

public class Main {

    static String S;
    static StringBuilder sb;
    static int idx;
    
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        S = br.readLine();

        for(idx = 0; idx < S.length() ; idx++){
            char c = S.charAt(idx);

            if(c == '<') tag();
            else word();
        }

        System.out.println(sb);
    }

    private static void tag(){
        sb.append(S.charAt(idx));
        idx++;

        while(idx < S.length()){
            if(S.charAt(idx) == '>'){
                sb.append(S.charAt(idx));
                break;
            }
            else sb.append(S.charAt(idx));
            idx ++;
        }
        return;
    }

    private static void word(){
        Deque<Character> st = new ArrayDeque<>();
        st.push(S.charAt(idx));
        idx++;

        while(idx < S.length()){
            if(S.charAt(idx) == ' ' || S.charAt(idx) == '<' ) break;
            else st.push(S.charAt(idx));
            idx++;
        }

        while(!st.isEmpty()){
            sb.append(st.pop());
        }

        if(idx == S.length()) return;
        else if(S.charAt(idx) == ' ') sb.append(" ");
        else if(S.charAt(idx) == '<') idx--;
        return;
    }
}