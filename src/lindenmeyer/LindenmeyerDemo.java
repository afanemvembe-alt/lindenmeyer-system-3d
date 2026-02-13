package lindenmeyer;

import java.util.*;
import lindenmeyer.axiom.*;
import lindenmeyer.symbols.*;
import lindenmeyer.rules.*;

public class LindenmeyerDemo{
	
	public static void main(String[] args) {
		
		Axiom axiom = new Axiom("F");
		Alphabet alphabet= new Alphabet();
		System.out.println(axiom.isInAlphabet(alphabet));
		
		SymbolFactory factory= new SymbolFactory();
		Symbol pred= factory.getSymbol('F');
		SymbolList succ= SymbolList.fromString("F+F+F", factory);
		SymbolList liste = SymbolList.fromString(axiom.getContent(), factory);
		
		RuleSetFactory rulefactory= new RuleSetFactory(',', '>', factory);
		RuleSet ruleset= rulefactory.parseString("F>F+F+F");
		
		for (GenericRule rule : ruleset.getRules()) {
            System.out.println(rule);
        }
        
		for(int i=0; i<5; i++){
			System.out.println(liste);
			SymbolList neww= new SymbolList();
			for(Symbol s: liste){
				SymbolList l= SymbolList.fromString(""+s.getSymbol(), factory);
				SymbolList list= ruleset.successorOf(l);
				for(Symbol li: list){
					neww.add(li);
				}
			}
			liste= neww;
		}
			
	}
}
		
		
