package lindenmeyer.axiom;

import java.util.*;
import lindenmeyer.symbols.*;

public class AxiomDemo {

    public static void main(String[] args) {
        Axiom axiom = new Axiom("F+F--F+F");
		Alphabet alphabet = new Alphabet();
		System.out.println(alphabet);
        System.out.println(axiom.getContent());
        if (axiom.isInAlphabet(alphabet)) {
            System.out.println("Axiome valide");
        } else {
            System.out.println("Axiome invalide");
        }
    }
}
