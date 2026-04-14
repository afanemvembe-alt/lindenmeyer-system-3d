package lindenmeyer.lsystem;

import lindenmeyer.axiom.Axiom;
import lindenmeyer.rules.GenericRule;
import lindenmeyer.rules.RuleSet;
import lindenmeyer.rules.SimpleRule;
import lindenmeyer.symbols.Symbol;
import lindenmeyer.symbols.SymbolFactory;
import lindenmeyer.symbols.SymbolList;

import org.json.JSONObject;

public class LSystem extends AbstractLsystemListenable {

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

    public LSystem(JSONObject obj)
    {
        this(new Axiom(obj.getString("axiom")), new RuleSet(), new SymbolFactory());

        JSONObject rulesObject = obj.getJSONObject("rules");
        for (String key : rulesObject.keySet()) {
            if (key.length() != 1) {
                throw new IllegalArgumentException(
                    "Rule key must be a single character: " + key
                );
            }

            char symbol = key.charAt(0);
            String replacement = rulesObject.getString(key);
            this.ajouterRegle(symbol, replacement);
        }
    }

    public void ajouterRegle(char symbole, String remplacement) {
        Symbol pred = symbolFactory.getSymbol(symbole);
        SymbolList succ = new SymbolList(symbolFactory);
        for (int i = 0; i < remplacement.length(); i++) {
            succ.add(remplacement.charAt(i));
        }

        regles.add(new SimpleRule(pred, succ));
        this.lsystemChange();
    }

    public void ajouterRegle(GenericRule regle) {
        regles.add(regle);
        this.lsystemChange();
    }

    public void step() {
        SymbolList res = new SymbolList(symbolFactory);

        for (Symbol s : currentGeneration) {
            res.addAll(regles.successorOf(SymbolList.of(s)));
        }

        currentGeneration = res;
        this.lsystemChange();
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
        this.lsystemChange();
    }

    public String toString() {
        return axiome + " " + regles + " " + currentGeneration;
    }

    public RuleSet getRegles() {
        return regles;
    }

    public Axiom getAxiom() {
        return axiome;
    }

    public SymbolFactory getSymbolFactory() {
        return symbolFactory;
    }
}
