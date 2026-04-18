package lindenmeyer.symbols;

import java.util.List;
import java.util.ArrayList;

/**
 * Une liste ordonnée de symboles, auquel il est possible de rajouter des
 * symboles. Aucune déduplication n'est faite sur les opération prenant en argument
 * un type {@link Symbol}.
 */
public class SymbolList extends ArrayList<Symbol> {
    private SymbolFactory factory;
    // private List<Symbol> symbols;

    /**
     * Construit une nouvelle liste de symboles vide, contenant sa propre source de symboles.
     */
    public SymbolList() {
        this(new SymbolFactory());
    }

    /**
     * Construit une liste de symboles vide avec la source de symboles donnée.
     * @param factory une source de symboles
     */
    public SymbolList(SymbolFactory factory) {
        this(new ArrayList<>(), factory);
    }

    /**
     * Initialise une liste de symboles à partir d'une autre en la copiant, avec la
     * source de symboles donnée.
     * Cette operation ne fait pas de déduplication de symboles.
     * 
     * @param s une liste de symboles
     * @param f une source de symboles
     */
    public SymbolList(List<Symbol> s, SymbolFactory f) {
        super(s);
        this.factory = f;
        // this.symbols = new ArrayList<>(s);
    }

    // /**
    //  * Initialise une liste de symboles à partir d'une autre en la copiant, avec la
    //  * source de symboles donnée.
    //  * Cette operation ne fait pas de déduplication de symboles.
    //  * 
    //  * @param s une liste de symboles
    //  * @param f une source de symboles
    //  */
    // public SymbolList(SymbolList s, SymbolFactory f) {
    //     this(s.getSymbols(), f);
    // }

    /**
     * Crée une liste de symboles avec le symbole donné.
     * @param s un symbole
     * @return une liste de symboles contenant uniquement le symbole donné
     */
    public static SymbolList of(Symbol s) {
        SymbolList res = new SymbolList();
        res.add(s);
        return res;
    }

    // /**
    //  * Ajoute un symbole à la liste de symboles.
    //  * @param symbol symbole à ajouter
    //  */
    // public void add(Symbol symbol) {
    //     this.symbols.add(symbol);
    // }

    // /**
    //  * Ajoute un symbole à la liste de symboles à l'indexe donné.
    //  * @param index l'index auquel insérer l'élément
    //  * @param symbol le symbole à ajouter
    //  */
    // public void add(int index, Symbol symbol) {
    //     this.symbols.add(index, symbol);
    // }

    /**
     * Ajoute le caractère donné à la fin de la liste.
     * @param c le caractère à ajouter
     */
    public void add(char c) {
        this.add(factory.getSymbol(c));
    }
    
    /**
     * Ajoute le caractère donné à la position donnée à la liste.
     * @param index l'index auquel insérer l'élément
     * @param c le caractère à ajouter
     */
    public void add(int index, char c) {
        this.add(index, factory.getSymbol(c));
    }

    /**
     * Construit une nouvelle liste de symboles à partir de la chaîne de caractères donnée.
     * @param s la chaîne à examiner
     * @param f une source de symboles
     * @return une liste de symboles
     */
    public static SymbolList fromString(String s, SymbolFactory f) {
        SymbolList res = new SymbolList(f);

        for (int i = 0; i < s.length(); i++) {
            res.add(s.charAt(i));
        }

        return res;
    }

    // /**
    //  * Retourne la liste de symboles contenue par la liste.
    //  * @return une liste de symboles
    //  */
    // public List<Symbol> getSymbols() {
    //     return this.symbols;
    // }

    @Override
    public boolean equals(Object obj) {
        return this.hashCode() == obj.hashCode() && obj instanceof SymbolList;
    }

    // @Override
    // public int hashCode() {
    //     return symbols.hashCode();
    // }

    @Override
    public String toString() {
        String res = "";

        for (Symbol s : this) {
            res += s.getSymbol();
        }

        return res;
    }
}
