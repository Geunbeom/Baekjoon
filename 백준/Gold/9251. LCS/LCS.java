import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String str1 = br.readLine();
		String str2 = br.readLine();
		
		int size1 = str1.length();
		int size2 = str2.length();
		int[][] dp = new int[size1+1][size2+1];
		
		for (int i=1; i<=size1; i++) {
			for (int j=1; j<=size2; j++) {
				if (str1.charAt(i-1)==str2.charAt(j-1)) {
					dp[i][j] = dp[i-1][j-1] + 1;
				}else {
					dp[i][j] = Math.max(dp[i][j-1], dp[i-1][j]);
				}
			}
		}
		System.out.println(dp[size1][size2]);
		
	}
}
