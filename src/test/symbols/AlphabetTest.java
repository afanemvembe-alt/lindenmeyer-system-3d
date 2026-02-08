package test.symbols;

import lindenmeyer.symbols.*;

public class AlphabetTest{
	
	public boolean addSymbolTest(){
		Symbol s1= new Symbol('A');
		Symbol s2= new Symbol('B');
		Symbol s3= new Symbol('C');
		
		Alphabet alphabet = new Alphabet();
		alphabet.addSymbol(s1);
		alphabet.addSymbol(s2);
		alphabet.addSymbol(s3);
		
		boolean ok = alphabet.isInAlphabet(s1) && alphabet.isInAlphabet(s2) && alphabet.isInAlphabet(s3);		
		if (!ok){ System.out.println("Erreur : addSymbol(Symbol symbol) incorrect pour Alphabet");}
		return ok;
	}
	
	public boolean isInAlphabetTest(){
		Alphabet alphabet = new Alphabet();
		Symbol s1 = new Symbol('F');
        Symbol s2 = new Symbol('Z');
		
		boolean ok = alphabet.isInAlphabet(s1) && !alphabet.isInAlphabet(s2);		
		if (!ok){ System.out.println("Erreur : isInAlphabet(Symbol symbol) incorrect pour Alphabet");}
		return ok;
	}
	
}
	
