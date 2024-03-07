import java.util.Arrays;
class Solution {
    public int solution(int k, int[] tangerine) {
        int[] count = new int[10_000_001]; // 같은 크기 개수 세는 배열
		int num = 0;
		
		for(int i : tangerine) {
			count[i]++;
		}
		
		Arrays.sort(count);
		
		for(int i = 0 ; i< count.length/2 ; i++) {
			int temp = count[i];
			count[i] = count[count.length - 1 - i];
			count[count.length - 1 - i] = temp;
		}
		
		int idx = 0;
		while(num < k) {
			num += count[idx];
			idx++;
		}
        
        return idx;
    }
}