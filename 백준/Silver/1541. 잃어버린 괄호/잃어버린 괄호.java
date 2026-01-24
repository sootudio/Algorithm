import java.io.*;

public class Main {

    static String formula = "";
    static int idx;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        formula = br.readLine();

        int sum = 0;
        idx = 0;

        while(idx < formula.length()){
            char c = formula.charAt(idx);
            if(c == '+'){
                idx++;
                sum += number();
            }
            else if(c == '-'){
                idx++;
                sum -= minus();
            }
            else{
                sum += number();
            }
        }

        System.out.println(sum);
    }

    private static int minus(){
        int minusNum = 0;
        while(idx < formula.length()){
            char input = formula.charAt(idx);
            if(input == '-') break;
            else if(input == '+'){
                idx++;
                minusNum += number();
            }
            else{
                minusNum += number();
            }
            idx++;
        }
        return minusNum;
    }

    private static int number(){
        char[] nums = new char[5];
        nums[0] = formula.charAt(idx);
        int cnt = 1;
        int num = 0;
        idx ++;
        while(idx < formula.length()){
            char input = formula.charAt(idx);
            if(input == '+' || input == '-'){
                break;
            }
            else{
                nums[cnt] = input;
                cnt ++;
            }
            idx ++;
        }
        num += nums[0] -'0';
        for(int j = 1 ; j < cnt ; j++){
            num =  num * 10 + (nums[j] - '0');
        }
        return num;
    }
}