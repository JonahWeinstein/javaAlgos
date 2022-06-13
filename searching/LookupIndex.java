package src.searching;

import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

/* This file creates a symbol table out of a file whose lines are formatted 
as key value value value...
and creates and index with 
each key(ex: movies) getting a queue with all its related values( ex: actors in 
that movie). It also creates a reverse index with each value(actor) getting a 
queue with all its related keys(movies they've acted in) 

To Run: $ java .../LookupIndex filename character_delimiter

*/

public class LookupIndex {
    public static void main(String[] args) {

        try {
            File myFile = new File(args[0]);
            String seperator = args[1];
            Scanner in = new Scanner(myFile); // index database

            Hashtable<String, Queue<String>> st;
            st = new Hashtable<String, Queue<String>>();

            Hashtable<String, Queue<String>> ts;
            ts = new Hashtable<String, Queue<String>>();

            while (in.hasNextLine()) {
                String temp = in.nextLine();
                String[] a = temp.split(seperator);
                String key = a[0];
                for (int i = 1; i < a.length; i++) {
                    
                    String val = a[i];
                    if (!st.containsKey(key))
                        st.put(key, new LinkedList<String>()); // if key not already add it then add it with a new empty
                                                               // queue for values
                    if (!ts.containsKey(val))
                        ts.put(val, new LinkedList<String>()); // does the same for the reverse index
                    st.get(key).add(val); // add value to the values associated with this key
                    ts.get(val).add(key); // add key to the keys associated with this value
                }
            }
            in.close();
            System.out.println("please enter your search ");
            Scanner sc = new Scanner(System.in);  //read in the search key
            while(sc.hasNextLine()){
                String query = sc.nextLine();
                if (st.containsKey(query)) {
                    for (String s : st.get(query)) 
                        System.out.println(" " + s);
                }
                if (ts.containsKey(query)) {
                    for (String s : ts.get(query))
                        System.out.println(" " + s);
                        
                }
            }
            sc.close();
            
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }
}
