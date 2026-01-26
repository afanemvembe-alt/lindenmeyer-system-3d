package lindenmeyer.symbols;

public class Symbol{
	
	private char symbol;
	
	public Symbol(char symbol){
		this.symbol= symbol;
	}
	
	public char getSymbol(){
		return this.symbol;
	}
	
	public void setSymbol(char symbole){
		this.symbol= symbole;
	}
	
	 @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Symbol)){
			return false;
		}
        Symbol other = (Symbol) obj;
        return this.symbol == other.symbol;
    }

    @Override
    public int hashCode() {
        return Character.hashCode(this.symbol);
    }
}