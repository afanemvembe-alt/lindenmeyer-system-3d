package lindenmeyer.symbols;

import java.util.*;
import test.symbols.*;

public class Tests{
	public static void main(String[] args){
		boolean ok= true;
		AlphabetTest test1= new AlphabetTest();
		ok = ok && test1.addSymbolTest();
		ok = ok && test1.isInAlphabetTest();
		SymbolTest test2= new SymbolTest();
		ok = ok && test2.getSymbolTest();
		ok = ok && test2.setSymbolTest();
		ok = ok && test2.equalsTest();
		SymbolFactoryTest test3= new SymbolFactoryTest();
		ok = ok && test3.getSymbolTest();
		ok = ok && test3.sizeTest();
		System.out.println(ok ? "All tests passed" : "At least one test failed");
	}
}
