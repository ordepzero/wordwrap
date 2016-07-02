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
public class WordWrapTeste {
    
    public static int[] OPT = new int[1000];
    public static int[] p = new int[1000];

    public static void main(String[] args) {
        // TODO code application logic here
        
        String s = "aaa bb c ccc ee";
        String[] wordsT = s.split(" ");
        List<String> words = new ArrayList<String>();
        for(int i = 0; i < wordsT.length; i++){
            words.add(wordsT[i]);
        }
        formata_word_wrap(words,6);
        //System.out.println(ss);
    }
    
    public static int custo_linha(List<String> lista_de_palavras,int largura_pagina,int i,int j){
        int soma = 0;
        int custo = 0;

        for (  ; i < j; i++){
            soma += lista_de_palavras.get(i-1).length() + 1;
        }
        
        soma += lista_de_palavras.get(i-1).length();

        custo = largura_pagina - soma;
        if (custo < 0)
            return Integer.MAX_VALUE;
        else
            return (custo * custo);
    }
    
    public static void formata_word_wrap(List<String> lista_de_palavras, int largura_pagina){
        
        int i, j;

        OPT[0] = 0;
        p[0] = 0;

//        if (lista_de_palavras.size() == 0){
//            resultado.OTP = NULL;
//            resultado.P   = NULL;
//            return resultado;
//        }


        for (j = 1; j <= lista_de_palavras.size(); j++){
            int min_custo = Integer.MAX_VALUE;
            
            for (i = 1; i <= j; i++){
                int custo_atual;
                custo_atual = OPT[i-1] + custo_linha(lista_de_palavras, largura_pagina, i,j);
                
                if (custo_atual < min_custo){
                    min_custo = custo_atual;
                    OPT[j] = min_custo;
                    p[j] = i;
                }
            }
        }
        
        printSolution(p,lista_de_palavras.size());
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
