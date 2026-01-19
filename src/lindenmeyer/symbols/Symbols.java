package lindenmeyer.symbols;

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
	
}
