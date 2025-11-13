import java.util.*;
import java.io.*;

public class Main{

    static int N;
    static int[] arr;

    public static void main(String args[]) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        
        for(int i = 0 ; i < N ; i++){
            arr[i] = Integer.parseInt(br.readLine());
        }

        bottomUpMergeSort();

        for(int i = 0 ; i < N ; i++){
            sb.append(arr[i]).append("\n");
        }

        System.out.println(sb);
    }

    private static void bottomUpMergeSort(){
        int[] tmp = new int[N];

        for(int width = 1; width < N ; width *= 2){
            for(int i = 0 ; i < N; i += 2*width){
                int left = i;
                int mid = Math.min(i + width, N);
                int right = Math.min(i+2*width, N);
                merge(tmp, left, mid, right);
            }
            System.arraycopy(tmp, 0, arr, 0, N);
        }
    }

    private static void merge(int[] tmp, int left, int mid, int right){
        int i = left;
        int j = mid;
        int k = left;

        while(i < mid && j < right){
            if(arr[i] <= arr[j]) tmp[k++] = arr[i++];
            else tmp[k++] = arr[j++];
        }

        while (i < mid) tmp[k++] = arr[i++];
        while (j < right) tmp[k++] = arr[j++];
    }
};