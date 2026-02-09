package lindenmeyer.demo;

import lindenmeyer.lsystem.LSystem;
import lindenmeyer.symbols.Alphabet;

public class Demo {
    public static void main(String[] args){
        Alphabet alphabet = new Alphabet();
        System.out.println("Alphabet : F + - [ ] X Y");
        String axiome = "F+F--F+F";
        System.out.println("Axiome : " + axiome);

        LSystem lsys = new LSystem(axiome);
        /*lsys.ajouterRegle(new SimpleRule(
            new Symbol('F'),
            new SymbolList(List.of(new Symbol('F'), new Symbol('+'), new Symbol('F')), null)
        ));*/

        System.out.println("Résultat après 2 itérations : " + lsys.generer(2));
    }
}
