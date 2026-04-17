package lindenmeyer.symbols;

import java.util.List;
import lindenmeyer.turtle.AbstractTurtle3D;
import lindenmeyer.turtle.Interpretable3D;

/**
 * Un symbole contenu dans un LSystème.
 */
public class Symbol implements Interpretable3D, Mot {

    private char symbol;

    /**
     * Crée un nouveau symbole contenant le caractère donné.
     * @param symbol un caractère
     */
    public Symbol(char symbol) {
        this.symbol = symbol;
    }

    /**
     * Retourne le caractère contenu par ce symbole.
     * @return un caractère
     */
    public char getSymbol() {
        return this.symbol;
    }

    /**
     * Modifie le caractère contenu par ce symbole.
     * @param symbole le nouveau caractère de ce symbole
     */
    public void setSymbol(char symbole) {
        this.symbol = symbole;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Symbol)) {
            return false;
        }
        Symbol other = (Symbol) obj;
        return this.symbol == other.symbol;
    }

    @Override
    public int hashCode() {
        return Character.hashCode(this.symbol);
    }

    @Override
    public String toString() {
        return "" + this.symbol;
    }

    @Override
    public void interpret(AbstractTurtle3D turtle) {
        turtle.setColorOf(this);
        turtle.forward();
    }

    @Override
    public List<Symbol> affiche() {
        return SymbolList.of(this);
    }
}
