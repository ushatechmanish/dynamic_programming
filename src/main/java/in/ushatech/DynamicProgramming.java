package in.ushatech;

import java.util.HashMap;
import java.util.Map;

public final class DynamicProgramming {
   
    public static void main(String[] args) 
    {
        // The concept is simple. Save the solved problem and use the result in finding the answer 
        // So that you do not have to calculate the sub problem again. 

        // These are used for optimization  be it maximize or minimize

        // On the other hand Greedy algorithm never reconsiders its choices.

        // In divide and conquer the sub problems are independent whereas in DP , the subparts may have overlapping sub problems 

        // There are 2 approaches . Top Down (Memoization ) , Bottom up approach (Tabulation)

        int fibonnaciInput=5;
        nthFibonacci(fibonnaciInput);

    }
    // Bottom Up approach. 
    // This is helpful when you know the upper limit of the n 
    // and you have to find many fibonacci numbers 
    // Here all sub problems are calculated whereas in Memoization only required sub problems are calculates 
    // This approach saves the space also as multiple stacks are not created 
    // return the answer modulo 1000000007.
    static int nthFibonacci(int n)
    {
        //System.out.println("Trying to find fibonacci number at"+n);
        int[] dp = new int[n+1];
        // seeds
        dp[0]=0;
        dp[1]=1;
        for(int i=2; i<=n; ++i)
        {

            dp[i]=(dp[i-1]%1000000007+dp[i-2]%1000000007)%1000000007;
        }
        return dp[n];
    }

    // Function to find the length of longest common subsequence in two strings.
    static int lcs(int n, int m, String str1, String str2) 
    {
       Map<String,Integer> map = new HashMap<>();
        return helperLcs(n, m, str1, str2, map);
    }
    static int helperLcs(int n, int m, String str1, String str2, Map<String,Integer> dp)
    {
        if(m==0 || n==0) return 0 ;
        String key = Integer.toString(n)+":"+Integer.toString(m);
        if(dp.containsKey(key)) return dp.get(key);
        int char1 = str1.charAt(n-1);
        int char2 = str2.charAt(m-1);
        int result ; 
        if(char1==char2)
        {
            result = 1+ helperLcs(n-1,m-1,str1,str2,dp);
        }
        else
        {
            result = Math.max(helperLcs(n,m-1,str1,str2,dp),helperLcs(n-1,m,str1,str2,dp));
        }
        dp.put(key,result);
        return result;
    }

}
