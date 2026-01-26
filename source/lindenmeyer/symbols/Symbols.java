package lindenmeyer.symbols;

<<<<<<< HEAD
public class Symbols{
	
	private Set<Symbol> symbols;
	
	public Symbols(){
		this.symbols= new Set<Symbol>();
	}
	
	public void addSymbol(Symbol symbol){
		this.symbols.add(symbol);
	}
	
	public boolean isInSymbols(Symbol symbol){
		if (this.symbols.contains(symbol)){
			return true;
		}
		return false;
	}
	
=======
import java.util.HashSet;
import java.util.Set;

public class Symbols {

    private final Set<Symbol> symbols;

    public Symbols() {
        this.symbols = new HashSet<>();
    }

    public void addSymbol(Symbol symbol) {
        symbols.add(symbol);
    }

    public boolean contains(Symbol symbol) {
        return symbols.contains(symbol);
    }
>>>>>>> 0af722f (Creation d'une classe Demopour tout le projet,corrrection de toutes les erreurs)
}
