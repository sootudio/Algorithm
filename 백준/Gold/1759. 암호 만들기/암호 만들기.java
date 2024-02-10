import java.util.*;

public class Main {
    
    static int L, C;
    static char[] chars;
    static StringBuilder sb = new StringBuilder();
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        L = sc.nextInt();
        C = sc.nextInt();
        sc.nextLine(); // 개행 문자 제거
        chars = new char[C];
        String[] input = sc.nextLine().split(" ");
        for (int i = 0; i < C; i++) {
            chars[i] = input[i].charAt(0);
        }
        Arrays.sort(chars); // 정렬
        
        combination(0, 0);
        
        sc.close();
    }
    
    static void combination(int start, int depth) {
        if (depth == L) {
            if (check()) {
                System.out.println(sb.toString());
            }
            return;
        }
        
        for (int i = start; i < C; i++) {
            sb.append(chars[i]);
            combination(i + 1, depth + 1);
            sb.setLength(sb.length() - 1);
        }
    }
    
    static boolean check() {
        int vowels = 0;
        int consonants = 0;
        for (int i = 0; i < sb.length(); i++) {
            char ch = sb.charAt(i);
            if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u') {
                vowels++;
            } else {
                consonants++;
            }
        }
        return vowels >= 1 && consonants >= 2;
    }
}