/**
 * Java program to validate string acceptance from a determined grammar via top down parsing.
 * @author Carlos Maldonado A01339011
 * @author Emiliano Roldan A01650141
 */
import java.util.*;
import java.io.*;
public class Main {
    public static HashMap<Character, ArrayList<String>> trans = new HashMap<>();
    public static HashMap<Character, Boolean> lambda = new HashMap<>();
    /**
     * @param args
     * @throws IOException Exception thrown if the file is not found.
     * Main class that validates if the string belongs to the grammar or not.
     */
    public static void main(String[] args) throws IOException {
        File file;
        Scanner in;
        
        try {
            file = new File("/Users/emilianoroldan/Documents/GitHub/Top-down Parsing/test1.txt");
            in = new Scanner(file);
        } catch (FileNotFoundException e1) {
            throw e1;
        }
        String s = "";
        if(in.hasNext()) {
            System.out.println("Non-terminal symbols: " + in.next());
            System.out.println("Terminal symbols: " + in.next());
            s = in.next();
        }
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
        System.out.println("Grammar productions: " + trans);
        System.out.println("Grammar productions with lambda: " + lambda);
        Stack<Character> stack = new Stack<>();
        Character begin = s.charAt(0);
        stack.add(begin);
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the string to test");
        String test = scanner.nextLine();
        scanner.close();
        System.out.println("string to test: " + test);
        topDownParsing(stack, test);
        if(belongs)
            System.out.println("String belongs to the grammar");
        else 
            System.out.println("String does not belongs to the grammar");
    }
    /**
     * @param stack This stack is used to add all the productions of the grammar.
     * @param test The string that will be checked if is belongs or not.
     * Function that will check if the given String is produced by the grammar.
     */
    public static boolean belongs = false;
    private static void topDownParsing(Stack<Character> stack, String test) {
        if(!belongs) {
            if(stack.size() == 1 && test.equals("")) {
                if(lambda.containsKey(stack.peek()) && lambda.get(stack.peek()))
                    belongs = true;
            }
            if (test.equals("") && !stack.empty()) return;if (!test.equals("") && stack.empty()) return;
            if (stack.empty() && test.equals(""))
                belongs = true;
            if(!stack.empty()) {
                char ch = stack.pop();
                if (trans.containsKey(ch)) {
                    int count = 0;
                    if(lambda.containsKey(ch) && lambda.get(ch))
                        count++;
                    StringBuilder attacher = new StringBuilder();
                    while (!stack.empty()) {
                        if(lambda.containsKey(stack.peek()) && lambda.get(stack.peek()))
                            count++;
                        attacher.append(stack.pop());
                    }
                    if(attacher.length() + 1 > test.length() + count)
                        return;

                    for (int i = 0; i < trans.get(ch).size(); i++) {
                        Stack<Character> tempStack = new Stack<>();
                        for (int j = attacher.length() - 1; j >= 0; j--)
                            tempStack.add(attacher.charAt(j));
    
                        String s = trans.get(ch).get(i);
                        if(!s.equals("lmd")){
                            for (int k = s.length() - 1; k >= 0; k--)
                                tempStack.add(s.charAt(k));
                        }
                        topDownParsing(tempStack, test); 
                    }
                } else {
                    if (ch == test.charAt(0)) {
                        test = test.substring(1);
                        topDownParsing(stack, test);
                    }
                }
            }
        }
    }
}