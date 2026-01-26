package lindenmeyer.symbols;

<<<<<<< HEAD
import java.util.*;

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
	
=======
import java.util.HashSet;
import java.util.Set;

public class Alphabet {

    private Set<Character> symbols;

    public Alphabet() {
        symbols = new HashSet<>();
        // Exemples : ajoute tous les symboles valides
        symbols.add('F');
        symbols.add('+');
        symbols.add('-');
        // ajoute tous les symboles nécessaires
    }

    // La méthode manquante
    public boolean isInAlphabet(Symbol s) {
        return symbols.contains(s.getSymbol());
    }
>>>>>>> 0af722f (Creation d'une classe Demopour tout le projet,corrrection de toutes les erreurs)
}
