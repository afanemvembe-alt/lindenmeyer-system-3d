package test.symbols;

import lindenmeyer.symbols.*;

public class SymbolTest{
	
	public boolean getSymbolTest(){
		char c1= 'A';
		char c2= 'B';
		char c3= 'C';
		Symbol s1= new Symbol(c1);
		Symbol s2= new Symbol(c2);
		Symbol s3= new Symbol(c3);
		
		boolean ok= s1.getSymbol()==c1 && s2.getSymbol()==c2 && s3.getSymbol()==c3;
		if (!ok){ System.out.println("Erreur : getSymbol() incorrect pour Symbol");}
		return ok;
	}
	
	public boolean setSymbolTest(){
		Symbol s1= new Symbol('A');
		Symbol s2= new Symbol('B');
		Symbol s3= new Symbol('C');
		char c1= 'A';
		char c2= 'B';
		char c3= 'C';
		s1.setSymbol(c3);
		s2.setSymbol(c1);
		s3.setSymbol(c2);
		
		boolean ok= s1.getSymbol()==c3 && s2.getSymbol()==c1 && s3.getSymbol()==c2;
		if (!ok){ System.out.println("Erreur : setSymbol() incorrect pour Symbol");}
		return ok;
	}
	
	public boolean equalsTest(){
		Symbol a = new Symbol('A');
		Symbol b = new Symbol('A');
		
		boolean ok= a.equals(b);
		if (!ok){ System.out.println("Erreur : equals() incorrect pour Symbol");}
		return ok;
	}
	
}
	
