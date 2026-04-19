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
    private RuleSet regles;
    private SymbolList currentGeneration;
    private SymbolFactory symbolFactory;

    public LSystem(
        Axiom axiome,
        RuleSet regles,
        SymbolList currentGeneration,
        SymbolFactory symbolFactory
    ) {
        this.axiome = axiome;
        this.regles = regles;
        this.currentGeneration = currentGeneration;
        this.symbolFactory = symbolFactory;
    }

    public LSystem(Axiom axiome, RuleSet regles, SymbolFactory symbolFactory) {
        this.axiome = axiome;
        this.regles = regles;
        this.symbolFactory = symbolFactory;
        resetGeneration();
    }

    public LSystem(Axiom axiome) {
        this(axiome, new RuleSet(), new SymbolFactory());
    }

    public LSystem(JSONObject obj) {
        this(
            new Axiom(obj.getString("axiom")),
            new RuleSet(),
            new SymbolFactory()
        );
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

    private void resetGeneration() {
        this.currentGeneration = new SymbolList(this.symbolFactory);
        for (int i = 0; i < axiome.getContent().length(); i++) {
            currentGeneration.add(
                symbolFactory.getSymbol(axiome.getContent().charAt(i))
            );
        }
    }

    public void ajouterRegle(char symbole, String remplacement) {
        Symbol pred = symbolFactory.getSymbol(symbole);
        SymbolList succ = new SymbolList(symbolFactory);
        for (int i = 0; i < remplacement.length(); i++) {
            succ.add(symbolFactory.getSymbol(remplacement.charAt(i)));
        }
        regles.add(new SimpleRule(pred, succ));
        this.lsystemChange();
    }

    public void ajouterRegle(GenericRule regle) {
        regles.add(regle);
        this.lsystemChange();
    }

    public void step() {
        step(1);
    }

    public void step(int n) {
        for (int i = 0; i < n; i++) {
            SymbolList nextGen = new SymbolList(symbolFactory);

            for (int j = 0; j < currentGeneration.size(); j++) {
                Symbol current = currentGeneration.get(j);

                // Gestion du contexte pour les règles contextuelles
                SymbolList left = new SymbolList(symbolFactory);
                if (j > 0) left.add(currentGeneration.get(j - 1));

                SymbolList right = new SymbolList(symbolFactory);
                if (j < currentGeneration.size() - 1) right.add(
                    currentGeneration.get(j + 1)
                );

                // APPEL CORRIGÉ : On passe le symbole ET ses voisins
                SymbolList successor = regles.successorOf(
                    SymbolList.of(current),
                    left,
                    right
                );

                if (successor != null) {
                    nextGen.addAll(successor);
                } else {
                    nextGen.add(current);
                }
            }

            currentGeneration = nextGen;
        }
        lsystemChange();
    }

    public String generer(int n) {
        resetGeneration(); // On repart de l'axiome pour une nouvelle génération
        step(n);
        return currentGeneration.toString();
    }

    public void setAxiome(Axiom axiome) {
        this.axiome = axiome;
        resetGeneration(); // TRÈS IMPORTANT : vide la génération actuelle
        this.lsystemChange();
    }

    // Getters standards
    public SymbolList getCurrentGeneration() {
        return currentGeneration;
    }

    public Axiom getAxiome() {
        return axiome;
    }

    public RuleSet getRegles() {
        return regles;
    }

    public SymbolFactory getSymbolFactory() {
        return symbolFactory;
    }

    public void setCurrentGeneration(SymbolList symbols) {
        this.currentGeneration = symbols;
        lsystemChange();
    }

    @Override
    public String toString() {
        return axiome + " " + regles + " " + currentGeneration;
    }

    public void setRegles(RuleSet regles) {
        this.regles = regles;
        // On notifie les observeurs (l'interface) que le modèle a changé
        this.lsystemChange();
    }
}
