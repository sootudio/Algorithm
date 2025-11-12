import java.io.*;

public class Main {
    
    public static void main(String args[]) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int A = Integer.parseInt(br.readLine());
        int B = Integer.parseInt(br.readLine());
        int C = Integer.parseInt(br.readLine());

        System.out.println(A+B-C);

        String AS = Integer.toString(A);
        String BS = Integer.toString(B);
        String ABS = AS + BS;

        int AB = Integer.parseInt(ABS);
        System.out.println(AB - C);
    }
}