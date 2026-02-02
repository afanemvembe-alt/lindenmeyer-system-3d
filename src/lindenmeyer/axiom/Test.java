package lindenmeyer.axiom;

import test.axiom.*;

public class Test {
	public static void main(String[] args) {
		boolean ok = true;
		AxiomTests test= new AxiomTests();
		ok = ok && test.getContentTest();
		ok = ok && test.setContentTest();
		ok = ok && test.isInAlphabetTest();
		System.out.println(ok ? "All tests passed" : "At least one test failed");
	}
}
