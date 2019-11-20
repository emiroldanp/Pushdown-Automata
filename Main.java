/**
 * Java program to validate string acceptance from a determined grammar via top down parsing.
 * 
 * @author Carlos Maldonado A016
 * @author Emiliano Roldan A01650141
 */

import java.util.Scanner;
import java.util.Stack;
import java.util.ArrayList;
import java.util.HashMap;
import java.io.*;

public class Main {
        public static HashMap<Character, ArrayList<String>> trans = new HashMap<>();
        public static HashMap<Character, Boolean> lambda = new HashMap<>();
        public static boolean accepted = false;
        /**
         * 
         * @param args
         * @throws IOException
         * Main class that validates if the string is accepted or not.
         */
        public static void main(String[] args) throws IOException {
            File file;
            Scanner in;
            try {
                file = new File("/Users/emilianoroldan/Documents/GitHub/Top-down Parsing/test2.txt");
                in = new Scanner(file);
            } catch (FileNotFoundException e1) {
                throw e1;
            }

            String s = "";
            if(in.hasNext()) {
                System.out.println("Set of non-terminal symbols: " + in.next());
                System.out.println("Set of terminal symbols: " + in.next());
                s = in.next();
            }
            Character begin = s.charAt(0);
            System.out.println("The starting point of the tree is: " + begin);

            while (in.hasNext()) {
                String productions = in.next();
                Character nonTerm = productions.charAt(0);
                String prod = productions.substring(3);
                if(!trans.containsKey(nonTerm)) {
                    trans.put(nonTerm, new ArrayList<String>());
                }
                if(prod.equals("lmd")) 
                    lambda.put(nonTerm, true);
                else {
                    if(!lambda.containsKey(nonTerm))
                        lambda.put(nonTerm, false);
                }
                trans.get(nonTerm).add(prod);
            }
            in.close();

            System.out.println("Production of the grammar: " + trans);
            System.out.println("Production of the grammar: " + lambda);
            Stack<Character> A = new Stack<>();
            A.add(begin);
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter the string to test");
            String test = scanner.nextLine();
            scanner.close();
            System.out.println("string to test: " + test);
            verifyString(A, test);
            if(accepted)
                System.out.println("String is accepted");
            else 
                System.out.println("String is not accepted");
        }

        /**
         * 
         * @param A
         * @param test
         * Function that will check if the given String is produced by the grammar.
         */
        private static void verifyString(Stack<Character> A, String test) {
            if(!accepted) {
                if(A.size() == 1 && test.equals("")) {
                    if(lambda.containsKey(A.peek()) && lambda.get(A.peek()))
                        accepted = true;
                    
                    if (test.equals("") && !A.empty()) return;
                    if (!test.equals("") && A.empty()) return;
                    if (A.empty() && test.equals(""))
                        accepted = true;
                if(!A.empty()) {
                    char ch = A.pop();
                    if (trans.containsKey(ch)) {
                        int count = 0;
                        if(lambda.containsKey(ch))
                            count++;
                        
                        StringBuilder sBuilder = new StringBuilder();
                        while(!A.empty()) {
                            if(lambda.containsKey(A.peek()) && lambda.get(A.peek()))  
                                count++;
                            sBuilder.append(A.pop());
                        }

                        if(sBuilder.length()+1 > test.length()+count)
                            return;
                        
                        for(int i=0; i<trans.get(ch).size(); i++) {
                            Stack<Character> temp = new Stack<>();
                            for(int j=sBuilder.length()-1; j>=0; j--)
                                temp.add(sBuilder.charAt(j));
                            String t = trans.get(ch).get(i);
                            if(!t.equals("lmd")) {
                                for (int k = t.length()-1; k >=0; k--)
                                    temp.add(t.charAt(k));
                            }
                            verifyString(temp, test);
                        }
                    } else {
                        if(ch == test.charAt(0)) {
                            test = test.substring(1);
                            verifyString(A, test);
                        }
                    } 
                }
            }
        }
    }   
}