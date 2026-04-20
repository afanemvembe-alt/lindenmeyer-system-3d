package lindenmeyer.symbols;

import java.util.*;

/**
 * La classe representante l'ensemble de tous les symboles qui existent
 * dans un système {@link LSystem}
 */
public class Alphabet{
	
	private Set<Symbol> alphabet;
	
	public Alphabet(){
		this.alphabet= new HashSet<>();
		alphabet.add(new Symbol('F'));
        alphabet.add(new Symbol('f'));
        alphabet.add(new Symbol('+'));
        alphabet.add(new Symbol('-'));
        alphabet.add(new Symbol('['));
        alphabet.add(new Symbol(']'));
        alphabet.add(new Symbol('X'));
        alphabet.add(new Symbol('Y'));
	}
	
	public void addSymbol(Symbol symbol){
		this.alphabet.add(symbol);
	}
	
	public String toString(){
		String alphabet= "";
		for (Symbol s : this.alphabet) {
			alphabet+= s.getSymbol();
		}
		return alphabet;
	}
	
	public boolean isInAlphabet(Symbol symbol){
		if (this.alphabet.contains(symbol)){
			return true;
		}
		return false;
	}
	
}
