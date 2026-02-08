package test.symbols;

import java.util.*;
import lindenmeyer.symbols.*;

public class SymbolFactoryTest{
	
	public boolean getSymbolTest(){
		SymbolFactory factory = new SymbolFactory();
		char c1= 'A';
		char c2= 'B';
		char c3= 'C';
		Symbol s1= new Symbol(c1);
		Symbol s2= new Symbol(c2);
		Symbol s3= new Symbol(c3);
		
		boolean ok= factory.getSymbol(c1).equals(s1) && factory.getSymbol(c2).equals(s2) && factory.getSymbol(c3).equals(s3);
		if (!ok){ System.out.println("Erreur : getSymbol() incorrect pour SymbolFactory");}
		return ok;
	}
	
	public boolean sizeTest(){
		SymbolFactory factory = new SymbolFactory();
		factory.getSymbol('A'); 
		factory.getSymbol('B'); 
		factory.getSymbol('C');
		
		boolean ok= factory.size()==3;
		if (!ok){ System.out.println("Erreur : size() incorrect pour SymbolFactory");}
		return ok;
	}
	
}
	

