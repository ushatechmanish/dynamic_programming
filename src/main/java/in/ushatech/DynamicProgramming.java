package in.ushatech;

import java.util.HashMap;
import java.util.Map;

public final class DynamicProgramming {

    public static void main(String[] args) {
        // The concept is simple. Save the solved problem and use the result in finding
        // the answer
        // So that you do not have to calculate the sub problem again.

        // These are used for optimization be it maximize or minimize

        // On the other hand Greedy algorithm never reconsiders its choices.

        // In divide and conquer the sub problems are independent whereas in DP , the
        // subparts may have overlapping sub problems

        // There are 2 approaches . Top Down (Memoization ) , Bottom up approach
        // (Tabulation)

        int fibonnaciInput = 5;
        nthFibonacci(fibonnaciInput);

    }

    // Bottom Up approach.
    // This is helpful when you know the upper limit of the n
    // and you have to find many fibonacci numbers
    // Here all sub problems are calculated whereas in Memoization only required sub
    // problems are calculates
    // This approach saves the space also as multiple stacks are not created
    // return the answer modulo 1000000007.
    static int nthFibonacci(int n) {
        // System.out.println("Trying to find fibonacci number at"+n);
        int[] dp = new int[n + 1];
        // seeds
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i <= n; ++i) {

            dp[i] = (dp[i - 1] % 1000000007 + dp[i - 2] % 1000000007) % 1000000007;
        }
        return dp[n];
    }

    // Function to find the length of longest common subsequence in two strings.
    // Bottom approach :- no recursion required
    static int lcs(int n, int m, String str1, String str2) {
        int[][] dp = new int[n + 1][m + 1];

        // row 0 and column 0 already initialized to 0

        // iterate over the dp
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (str1.charAt(i) == str2.charAt(j)) {
                    dp[i + 1][j + 1] = 1 + dp[i][j];
                } else {
                    dp[i + 1][j + 1] = Math.max(dp[i + 1][j], dp[i][j + 1]);
                }
            }
        }

        // To Print the LCS , start in reverse order from maximum value

        StringBuilder sb = new StringBuilder();
        int i = n, j = m;

        while (i > 0 && j > 0) {
            // Diagonal move (match)
            if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                sb.append(str1.charAt(i - 1));
                i--;
                j--;
            }
            // Move up (value came from above)
            else if (dp[i - 1][j] >= dp[i][j - 1]) {
                i--;
            }
            // Move left (value came from the left)
            else {
                j--;
            }
        }

        // Reverse the StringBuilder to get the correct LCS order
        System.out.println(sb.reverse().toString());

        return dp[n][m];
    }

    // Using direction table to store the direction
    static String lcs2(int n, int m, String str1, String str2) {
        int[][] dp = new int[n + 1][m + 1];
        char[][] direction = new char[n + 1][m + 1]; // 'D' for diagonal, 'U' for up, 'L' for left

        // Fill the dp table and direction table
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                    direction[i][j] = 'D'; // Diagonal
                } else if (dp[i - 1][j] >= dp[i][j - 1]) {
                    dp[i][j] = dp[i - 1][j];
                    direction[i][j] = 'U'; // Up
                } else {
                    dp[i][j] = dp[i][j - 1];
                    direction[i][j] = 'L'; // Left
                }
            }
        }

        // Reconstruct the LCS using the direction table
        StringBuilder sb = new StringBuilder();
        int i = n, j = m;
        while (i > 0 && j > 0) {
            if (direction[i][j] == 'D') {
                sb.append(str1.charAt(i - 1));
                i--;
                j--;
            } else if (direction[i][j] == 'U') {
                i--;
            } else { // direction[i][j] == 'L'
                j--;
            }
        }

        return sb.reverse().toString();
    }

    // convert the following recurrence to code
    // T(0) =T(1)=2
    // T(n) = Sigma_1^n-1 2xT(i)*T(i-1), for n>1

    public int p1(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 2;
        dp[1] = 2;
        for (int i = 2; i <= n; ++i) {

            dp[i] = dp[i - 1] + dp[i - 1] * dp[i - 2];

        }
        return dp[n];
    }


     // arr: input array
    // Function to find the sum of contiguous subarray with maximum sum.
    long maxSubarraySum(int[] arr) 
    {
        if(arr==null || arr.length==0) return 0;
        long max = 0;
        for (int i = 0; i < arr.length; i++) { // starting point
            for (int j = 0; j < arr.length; j++) { // end point
                long sum=0;
                for(int k=i; k<=j;++k)
                {
                    sum+=arr[k];
                }
                max=Math.max(sum, max);
            }
        }
        return max;
    }

}
