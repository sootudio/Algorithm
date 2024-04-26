import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 정수 A와 B 입력받기
        int A = scanner.nextInt();
        int B = scanner.nextInt();

        // A/B 출력
        System.out.println((double) A / B);

        scanner.close();
    }
}