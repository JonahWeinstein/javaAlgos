package compression;


public class RunLengthEncoding {
    
    public static void expand() {
        boolean b = false;
        while(!BinaryStdIn.isEmpty()) {
            char count = BinaryStdIn.readChar();
            for(int i =0; i < count; i++) {
                BinaryStdOut.write(b);
            }
            b = !b;
        }
        BinaryStdIn.close();
    }
    public static void compress() {
        // will always start reading 0s 
        char count = 0;
        // old keeps track of current run value
        boolean b, old = false;
        while(!BinaryStdIn.isEmpty()) {
            b = BinaryStdIn.readBoolean();
            // value has changed aso run is over
            if( b!= old) {
                BinaryStdOut.write(count);
                count = 0;
                old = !old;
            }
            else {
                // check if count is maxed and if so print it and also a 0 for other value run
                if (count == 255) {
                    BinaryStdOut.write(count);
                    count = 0;
                    BinaryStdOut.write(count);
                }
            }
            // increment count of current/new run
            count++;


        }
        // finally write the count of last run
        BinaryStdOut.write(count);
        BinaryStdOut.close();
    }

    public static void main(String[] args) {
        if (args[0] == "-") compress();
        else if (args[0] == "+") expand();
        
    }
}
