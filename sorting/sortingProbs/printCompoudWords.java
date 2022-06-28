package sorting.sortingProbs;


import java.util.Arrays;

public class printCompoudWords {

    public static void printWords(String[] input){
        int N=input.length;
        Arrays.sort(input);
        for(int i=0; i<N; i++){
            for(int j=0;j<i;j++){
            if(input[i].contains(input[j])){
                System.out.println(input[i]);
                break;
            }
        }
 
        }
    }
    public static void main(String[] args){
        String[] test ={"hi","after","thought","aftertime","afterthought","hellohi","pie","pieplane","hello","hellomy"};
        printWords(test);
    }
    
}
