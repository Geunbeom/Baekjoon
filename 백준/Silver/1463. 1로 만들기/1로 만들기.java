import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main{

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(st.nextToken());
		int[] dp = new int[N+1];
		
		for (int i=2; i<=N; i++) {
			dp[i] = dp[i-1]+1;
			
			if (i%2 == 0) {
				dp[i] = Math.min(dp[i/2]+1, dp[i]);
			}
			
			if (i%3 == 0) {
				dp[i] = Math.min(dp[i/3]+1, dp[i]);
			}
		}
		
		System.out.println(dp[N]);
		
	}
	
}