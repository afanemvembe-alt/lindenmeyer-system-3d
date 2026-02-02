package lindenmeyer;

import java.util.*;
import lindenmeyer.axiom.*;
import lindenmeyer.symbols.*;
import lindenmeyer.rules.*;

public class LindenmeyerDemo{
	
	public static void main(String[] args) {
		
		Axiom axiom = new Axiom("F");
		Alphabet alphabet= new Alphabet();
		
		SymbolFactory factory= new SymbolFactory();
		Symbol pred= factory.getSymbol('F');
		SymbolList succ= SymbolList.fromString("F+F+F", factory);
		SymbolList liste = SymbolList.fromString(axiom.getContent(), factory);
		
		GenericRule generic= new SimpleRule(pred, succ);
		RuleFactory rulefactory= new RuleFactory(',', '>', factory);
		RuleSet ruleset= rulefactory.parseString("F>F+F+F");
		
		for(int i=0; i<3; i++){
			String actu="";
			System.out.println("Iteration " + i + " : " + axiom);
			for(int j=0; j<axiom.getContent().length(); j++){
				char m= axiom.getContent().charAt(j);
				if(m=='F'){
					actu+= "F+F+F";
				}
				else {
					actu+=m;
				}
			}
			//SymbolList liste+(String)i= ruleset.successorOf(liste);
			axiom= new Axiom(actu);
		}
		
	}
}
		
		
