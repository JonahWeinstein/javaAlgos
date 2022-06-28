package searching.problems;

public class MultipleOf5 {

    public static Boolean isMultiple(String s) {
        int state = 0;
        int N = s.length();

        for (int i = 0; i < N; i++) {
            int bit = s.charAt(i);

            switch(state) {
                
                case 0:
                    if(bit == '1') state = 1;
                    break;
                case 1:
                    if(bit == '0') state = 2;
                    else state = 3;
                    break;
                case 2:
                    if(bit == '1') state = 0;
                    else state = 4;
                    break;
                case 3:
                    if(bit == '1') state = 2;
                    else state = 1;
                    break;
                case 4:
                    if(bit == '0') state = 3;
                    break;
            }
        }
        if (state == 0) return true;
        return false;
      
    }

    public static void main(String[] args) {
        String test = "1110001001"; // 905
        if (isMultiple(test))
            System.out.println (test + " is a multiple of 5");
        else
            System.out.println (test + " is not a multiple of 5");
    }
}