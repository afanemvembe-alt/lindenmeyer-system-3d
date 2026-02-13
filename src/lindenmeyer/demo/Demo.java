package lindenmeyer.demo;

import lindenmeyer.symbols.Symbol;
import lindenmeyer.symbols.SymbolFactory;
import lindenmeyer.symbols.Alphabet;
import lindenmeyer.axiom.Axiom;
import lindenmeyer.lsystem.LSystem;
import lindenmeyer.rules.RuleSet;
import lindenmeyer.rules.SimpleRule;
import lindenmeyer.symbols.SymbolList;
import java.util.List;

public class Demo {
    public static void main(String[] args){
        Alphabet alphabet = new Alphabet();
        System.out.println("Alphabet : F + - [ ] X Y");
        String axiome = "F+F--F+F";
        System.out.println("Axiome : " + axiome);

        LSystem lsys = new LSystem(new Axiom(axiome), new RuleSet(), new SymbolFactory());
        lsys.ajouterRegle(new SimpleRule(
            new Symbol('F'),
            new SymbolList(List.of(new Symbol('F'), new Symbol('+'), new Symbol('F')), null)
        ));

        System.out.println("Résultat après 2 itérations : " + lsys.generer(2));
    }
}
