package lindenmeyer.symbols;

import java.util.Set;
import java.util.HashSet;

public class Symbols{
	
	private Set<Symbol> symbols;
	
	public Symbols(){
		this.symbols= new HashSet<Symbol>();
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
	
}
