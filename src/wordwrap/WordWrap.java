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
public class WordWrap {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        String s = "aa bb c ccc ee";
        String[] wordsT = s.split(" ");
        List<String> words = new ArrayList<String>();
        for(int i = 0; i < wordsT.length; i++){
            words.add(wordsT[i]);
        }
        wordwrap(words,6);
        //System.out.println(ss);
    }
    
    public static void wordwrap(List<String> words, int M) {
        int n = words.size();
        
        int[][] free = new int[n+1][n+1];
        int[] cost = new int[n+1];
        int[] p = new int[n+1];
        int lineCost;
        
        for(int i = 1; i <= n; i++){
            free[i][i] = M - words.get(i-1).length();
            for(int j = i + 1; j <= n; j++){
                free[i][j] = free[i][j-1] - words.get(j-1).length() - 1;
            }
        }
        
        cost[0] = 0;
        for(int j = 1; j <= n; j++){
            cost[j] = Integer.MAX_VALUE;
            for(int i = 1; i <= j; i++){
                
                if(j == n && free[i][j] >= 0){
                    lineCost = 0;
                }else{
                    lineCost = (int)Math.pow(free[i][j]+1, 2);
                }                
                
                if(cost[i-1] != Integer.MAX_VALUE && lineCost != Integer.MAX_VALUE && (cost[i-1] + lineCost < cost[j])){
                    cost[j] = cost[i-1] + lineCost;
                    p[j] = i;
                }
            }
        }
        
        printSolution(p,n);
    }
    
    public static int printSolution (int p[], int n){
        int k;
        if (p[n] == 1){
            k = 1;
        }else{
            k = printSolution(p, p[n]-1) + 1;
        }
        System.out.println("Line number"+k+" From word no."+p[n]+"to "+n+" \n");

        return k;
    }
    
}
