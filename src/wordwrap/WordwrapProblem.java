/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package wordwrap;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author PeDeNRiQue
 */
public class WordwrapProblem {
    public static void main(String[] args) {
        // TODO code application logic here
        
        String s = "aaa bb c ccc ee";
        String[] wordsT = s.split(" ");
        List<String> words = new ArrayList<String>();
        for(int i = 0; i < wordsT.length; i++){
            words.add(wordsT[i]);
        }
        
        int[] palavras = {3,2,1,3,2};
        solveWordWrap(palavras,5,6);
        //System.out.println(ss);
    }

    
    public static void solveWordWrap (int l[], int n, int M){
        int[][] extras = new int[n+1][n+1];  

        int[][] lc = new int[n+1][n+1];

        int[] c = new int[n+1];

        int[] p = new int[n+1];

        int i, j;

        for (i = 1; i <= n; i++)
        {
            extras[i][i] = M - l[i-1];
            for (j = i+1; j <= n; j++)
                extras[i][j] = extras[i][j-1] - l[j-1] - 1;
        }

        for (i = 1; i <= n; i++)
        {
            for (j = i; j <= n; j++)
            {
                if (extras[i][j] < 0)
                    lc[i][j] = Integer.MAX_VALUE;
                else if (j == n && extras[i][j] >= 0)
                    lc[i][j] = 0;
                else
                    lc[i][j] = extras[i][j]*extras[i][j];
            }
        }

        c[0] = 0;
        for (j = 1; j <= n; j++)
        {
            c[j] = Integer.MAX_VALUE;
            for (i = 1; i <= j; i++)
            {
                if (c[i-1] != Integer.MAX_VALUE && lc[i][j] != Integer.MAX_VALUE && (c[i-1] + lc[i][j] < c[j]))
                {
                    c[j] = c[i-1] + lc[i][j];
                    p[j] = i;
                }
            }
        }

        printSolution(p, n);
    }
    
    public static int printSolution (int p[], int n)
    {
        int k;
        if (p[n] == 1)
            k = 1;
        else
            k = printSolution (p, p[n]-1) + 1;
            System.out.printf("Line number %d: From word no. %d to %d \n", k, p[n], n);
        return k;
    }
}
