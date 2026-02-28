package lindenmeyer.lsystem;

import java.util.HashMap;
import java.util.Map;

import lindenmeyer.axiom.Axiom;
import lindenmeyer.rules.GenericRule;
import lindenmeyer.rules.RuleSet;
import lindenmeyer.rules.SimpleRule;
import lindenmeyer.symbols.Symbol;
import lindenmeyer.symbols.SymbolFactory;
import lindenmeyer.symbols.SymbolList;

public class LSystem {

    private Axiom axiome;
    // private Map<Character, String> regles;
    private RuleSet regles;
    private SymbolList currentGeneration;
    private SymbolFactory symbolFactory;

    // public LSystem(String axiome) {
    // this.axiome = axiome;
    // this.regles = new HashMap<>();
    // }

    public LSystem(Axiom axiome, RuleSet regles, SymbolList currentGeneration, SymbolFactory symbolFactory) {
        this.axiome = axiome;
        this.regles = regles;
        this.currentGeneration = currentGeneration;
        this.symbolFactory = symbolFactory;
    }

    public LSystem(Axiom axiome, RuleSet regles, SymbolFactory symbolFactory) {
        this.axiome = axiome;
        this.regles = regles;
        this.symbolFactory = symbolFactory;
        this.currentGeneration = new SymbolList(symbolFactory);

        for (int i = 0; i < axiome.getContent().length(); i++) {
            currentGeneration.add(axiome.getContent().charAt(i));
        }
    }

    public LSystem(Axiom axiome) {
        this(axiome, new RuleSet(), new SymbolFactory());
    }

    public void ajouterRegle(char symbole, String remplacement) {
        Symbol pred = symbolFactory.getSymbol(symbole);
        SymbolList succ = new SymbolList(symbolFactory);
        for (int i = 0; i < remplacement.length(); i++) {
            succ.add(remplacement.charAt(i));
        }

        regles.add(new SimpleRule(pred, succ));
    }

    public void ajouterRegle(GenericRule regle) {
        regles.add(regle);
    }

    public void step() {
        SymbolList res = new SymbolList(symbolFactory);

        for (Symbol s : currentGeneration) {
            res.addAll(regles.successorOf(SymbolList.of(s)));
        }

        currentGeneration = res;
    }

    public void step(int n) {
        for (int i = 0; i < n; i++) {
            step();
        }
    }

    public String generer(int n) {
        for (int i = 0; i < n; i++) {
            step();
        }

        return currentGeneration.toString();
    }

    public SymbolList getCurrentGeneration() {
        return currentGeneration;
    }

    // public String generer(int n) {
    // String resultat = axiome;

    // for (int i = 0; i < n; i++) {
    // String nouveau = "";

    // for (int j = 0; j < resultat.length(); j++) {
    // char c = resultat.charAt(j);

    // if (regles.containsKey(c)) {
    // nouveau = nouveau + regles.get(c);
    // } else {
    // nouveau = nouveau + c;
    // }
    // }

    // resultat = nouveau;
    // }

    // return resultat;
    // }

    public Axiom getAxiome() {
        return axiome;
    }

    public void setAxiome(Axiom axiome) {
        this.axiome = axiome;
    }

    @Override
    public String toString() {
        return axiome + " " + regles + " " + currentGeneration;
    }
}
