/**
 * Java program to validate string acceptance from a determined grammar via top down parsing.
 * @author Carlos Maldonado A01339011
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
     * @param args
     * @throws IOException
     * Main class that validates if the string is accepted or not.
     */
    public static void main(String[] args) throws IOException {
        File file;
        Scanner in;
        try {
            file = new File("/Users/emilianoroldan/Documents/GitHub/Top-down Parsing/test3.txt");
            in = new Scanner(file);
        } catch (FileNotFoundException e1)
            throw e1;

        String s = "";
        if(in.hasNext()) {
            System.out.println("Set of non-terminal symbols: " + in.next());
            System.out.println("Set of terminal symbols: " + in.next());
            s = in.next();
        }
        Character begin = s.charAt(0);
        while (in.hasNext()) {
            String productions = in.next();
            Character nonTerm = productions.charAt(0);
            String prod = productions.substring(3);
            if(!trans.containsKey(nonTerm))
                trans.put(nonTerm, new ArrayList<String>());
            if(prod.equals("lmd")) 
                lambda.put(nonTerm, true);
            else {
                if(!lambda.containsKey(nonTerm))
                    lambda.put(nonTerm, false);
            }
            trans.get(nonTerm).add(prod);
        } in.close();

        Stack<Character> stack = new Stack<>();
        stack.add(begin);
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the string to test");
        String test = scanner.nextLine();
        scanner.close();
        System.out.println("string to test: " + test);
        verifyString(stack, test);
        if(accepted)
            System.out.println("String is accepted");
        else 
            System.out.println("String is not accepted");
    }
    /**
     * @param stack
     * @param test
     * Function that will check if the given String is produced by the grammar.
     */
    private static void verifyString(Stack<Character> stack, String test){
        if(!accepted) {
            if(stack.size() == 1 && test.equals("")) {
                if(lambda.containsKey(stack.peek()) && lambda.get(stack.peek()))
                    accepted = true;
            }
            if (test.equals("") && !stack.empty()) return;
            if (!test.equals("") && stack.empty()) return;
            if (stack.empty() && test.equals(""))
                accepted = true;
            if(!stack.empty()) {
                char ch = stack.pop();
                if (trans.containsKey(ch)) {
                    int count = 0;
                    if(lambda.containsKey(ch) && lambda.get(ch))
                        count++;
                    StringBuilder sBuilder = new StringBuilder();
                    while (!stack.empty()) {
                        if(lambda.containsKey(stack.peek()) && lambda.get(stack.peek()))
                            count++;
                        sBuilder.append(stack.pop());
                    }
    
                    if(sBuilder.length() + 1 > test.length() + count)
                        return;

                    for (int i = 0; i < trans.get(ch).size(); i++) {
                        Stack<Character> tempStack = new Stack<>();
                        for (int j = sBuilder.length() - 1; j >= 0; j--)
                            tempStack.add(sBuilder.charAt(j));
    
                        String s = trans.get(ch).get(i);
    
                        if(!s.equals("lmd")){
                            for (int k = s.length() - 1; k >= 0; k--)
                                tempStack.add(s.charAt(k));
                        }
                        verifyString(tempStack, test); 
                    }
                } else {
                    if (ch == test.charAt(0)) {
                        test = test.substring(1);
                        verifyString(stack, test);
                    }
                }
            }
        }
    }
}