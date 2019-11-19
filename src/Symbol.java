import java.util.*;
public class Symbol {
    private char name;
    private int index;
    private boolean initialSymbol;
    public ArrayList<String> states;

    public Symbol (int index, char name){
        this.name = name;
        if (!this.isTerminal())
            states = new ArrayList<>();
    }

    public void setIndex(){
        this.index = index;
    }
    public int getIndex(){
        return index;
    }

    public void setName(){
        this.name=name;
    }
    public char getName(){
        return name;
    }

    public void setInitialSymbol(boolean initialSymbol){
        this.initialSymbol = initialSymbol;
    }

    public boolean isInitial(){
        return initialSymbol;
    }

    public boolean isTerminal(){
        if (Character.isLowerCase(this.getName())){
            return true;
        }
        else if (Character.isUpperCase(this.getName())){
            return false;
        }
        return false;
    }

    public ArrayList<String> getStates(){
        return states;
    }
    @Override
    public String toString() {
        String characterToString = Character.toString(name);
        return characterToString;
    }
}
