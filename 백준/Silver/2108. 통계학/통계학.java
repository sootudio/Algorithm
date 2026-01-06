import java.io.*;

public class Main {
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int sum = 0;
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        int[] count = new int[8001];
    
        for(int i = 0 ; i < N ; i++){
            int input = Integer.parseInt(br.readLine());
            sum += input;
            max = Math.max(max, input);
            min = Math.min(min, input);
            count[input+4000] ++;
        }

        // 산술평균
        int avg = (int) Math.round( (double)sum / N);

        // 중앙값
        int midTarget = (N + 1) / 2;
        int acc = 0;
        int median = 0;
        for(int i = 0 ; i < 8001 ; i++){
            acc += count[i];
            if(acc >= midTarget){
                median = i - 4000;
                break;
            }
        }

        // 최빈값
        int maxFreq = 0;
        for(int i = 0 ; i < 8001 ; i++){
            maxFreq = Math.max(maxFreq, count[i]);
        }
        
        int mode = 0;
        boolean foundFirst = false;
        for(int i = 0 ; i < 8001 ; i++){
            if(count [i] == maxFreq){
                if(!foundFirst){
                    mode = i - 4000;
                    foundFirst = true;
                } else {
                    mode = i - 4000;
                    break;
                }
            }
        }

        // 범위
        int range = max - min;

        System.out.println(avg);
        System.out.println(median);
        System.out.println(mode);
        System.out.println(range);
    }
}