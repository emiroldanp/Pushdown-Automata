import java.util.*;
public class Main{
    public static void main (String [] args){
        Scanner in = new Scanner (System.in);

        ArrayList<Symbol> grammar = new ArrayList<>();
        Queue <Symbol> queue = new LinkedList<>();
        String string = "abba";

        Symbol s = new Symbol(0,'S');
        s.states.add("AbB");s.states.add("bbS");
        s.setInitialSymbol(true);

        Symbol s2 = new Symbol(1,'A');
        s2.states.add("bAb");s2.states.add("a");

        Symbol s3 = new Symbol(2,'B');
        s3.states.add("AaA");s3.states.add("b");

        grammar.add(s);grammar.add(s2);grammar.add(s3);

        boolean result = topDown(grammar,string,queue);

    }

    public static boolean topDown (ArrayList<Symbol>grammar, String word, Queue<Symbol>queue){
        queue.add(grammar.get(0));
        Symbol q = queue.poll();
        for (int i = 0; i < q.states.size(); i++){
            System.out.println(q.states.get(i));
        }
        return false;
    }
}
