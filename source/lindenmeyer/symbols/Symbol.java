package lindenmeyer.symbols;

<<<<<<< HEAD
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
=======
public class Symbol {
    private final char symbol;

    public Symbol(char symbol) {
        this.symbol = symbol;
    }

    public char getSymbol() {
        return symbol;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Symbol)) return false;
>>>>>>> 0af722f (Creation d'une classe Demopour tout le projet,corrrection de toutes les erreurs)
        Symbol other = (Symbol) obj;
        return this.symbol == other.symbol;
    }

    @Override
    public int hashCode() {
<<<<<<< HEAD
        return Character.hashCode(this.symbol);
    }
}
=======
        return Character.hashCode(symbol);
    }

    @Override
    public String toString() {
        return String.valueOf(symbol);
    }
}
>>>>>>> 0af722f (Creation d'une classe Demopour tout le projet,corrrection de toutes les erreurs)
