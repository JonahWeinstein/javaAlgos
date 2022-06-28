package searching.problems;

// Java program to check if the binary String is divisible
// by 3.

 
class GFG
{
   
// Function to check if the binary String is divisible by 3.
static void CheckDivisibilty(String A)
{
    int oddbits = 0, evenbits = 0;
    for (int counter = 0; counter < A.length(); counter++)
    {
       
        // checking if the bit is nonzero
        if (A.charAt(counter) == '1')
        {
           
            // checking if the nonzero bit is at even
            // position
            if (counter % 2 == 0) {
                evenbits++;
            }
            else {
                oddbits++;
            }
        }
    }
   
    // Checking if the difference of non-zero oddbits and
    // evenbits is divisible by 3.
    if (Math.abs(oddbits - evenbits) % 5 == 0) {
        System.out.print("Yes" +"\n");
    }
    else {
        System.out.print("No" +"\n");
    }
}
   
// Driver Program
public static void main(String[] args)
{
    String A = "1110001001";
    CheckDivisibilty(A);
}
}
 
// This code is contributed by umadevi9616 {
    

