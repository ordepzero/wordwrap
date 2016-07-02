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
        
        String s = "aa bb ccc ddddd";
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
        
        int[][] extras = new int[n+1][n+1];
        int[][] lineCost = new int[n+1][n+1];
        int[] cost = new int[n+1];
        int[] p = new int[n+1];
        
        for(int i = 1; i <= n; i++){
            extras[i][i] = M - words.get(i-1).length();
            for(int j = i + 1; j <= n; j++){
                extras[i][j] = extras[i][j-1] - words.get(i-1).length() - 1;
            }
        }
        
        for(int i = 1; i <= n; i++){
            for(int j = i; j <= n; j++){
                if(extras[i][j] < 0){
                    lineCost[i][j] = Integer.MAX_VALUE;
                }else if(j == n && extras[i][j] >= 0){
                    lineCost[i][j] = 0;
                }else{
                    lineCost[i][j] = (int)Math.pow(extras[i][j], 2);
                }
            }
        }
        
        cost[0] = 0;
        for(int j = 1; j <= n; j++){
            cost[j] = Integer.MAX_VALUE;
            for(int i = 1; i <= j; i++){
                if(cost[i-1] != Integer.MAX_VALUE && lineCost[i][j] != Integer.MAX_VALUE && (cost[i-1] + lineCost[i][j] < cost[j])){
                    cost[j] = cost[i-1] + lineCost[i][j];
                    p[j] = i;
                }
            }
        }
        
        WordWrap.printSolution(p,n);
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
    
    public void giveLines(int[] p,int j){
        int i = p[j];
    }
}