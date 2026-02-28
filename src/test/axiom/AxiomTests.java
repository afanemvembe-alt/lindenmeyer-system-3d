package test.axiom;

import lindenmeyer.axiom.*;
import lindenmeyer.symbols.*;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class AxiomTests {
	@Test
	void getContentTest() {
		String s1 = "F+F";
		String s2 = "F";
		String s3 = "F+F-F";

		Axiom axiom1 = new Axiom(s1);
		Axiom axiom2 = new Axiom(s2);
		Axiom axiom3 = new Axiom(s3);

		Set<String> axiomes = new HashSet<>();
		axiomes.add(axiom1.getContent());
		axiomes.add(axiom2.getContent());
		axiomes.add(axiom3.getContent());

		// boolean ok = axiomes.contains(s1) && axiomes.contains(s2) &&
		// axiomes.contains(s3) && axiomes.size() == 3;

		// if (!ok) {
		// System.out.println("Erreur : getContent() incorrect pour Axiome");
		// }
		// return ok;

		assertTrue(axiomes.contains(s1));
		assertTrue(axiomes.contains(s2));
		assertTrue(axiomes.contains(s3));
		assertEquals(3, axiomes.size());
	}

	@Test
	void setContentTest() {
		Axiom axiom1 = new Axiom("F+F");
		Axiom axiom2 = new Axiom("F");
		Axiom axiom3 = new Axiom("F+F-F");

		String s1 = "F+F-F";
		String s2 = "F+F";
		String s3 = "F";

		axiom1.setContent(s1);
		axiom2.setContent(s2);
		axiom3.setContent(s3);

		// boolean ok = axiom1.getContent() == s1 && axiom2.getContent() == s2 &&
		// axiom3.getContent() == s3;
		// if (!ok) {
		// System.out.println("Erreur : setContent() incorrect pour Axiome");
		// }
		// return ok;

		assertEquals(s1, axiom1.getContent());
		assertEquals(s2, axiom2.getContent());
		assertEquals(s3, axiom3.getContent());
	}

	@Test
	void isInAlphabetTest() {
		Alphabet alphabet = new Alphabet();
		Axiom axiom1 = new Axiom("F+F");
		Axiom axiom2 = new Axiom("F");

		// boolean ok = axiom1.isInAlphabet(alphabet) && axiom2.isInAlphabet(alphabet);
		// if (!ok) {
		// 	System.out.println("Erreur : isInAlphabet() incorrect pour Axiome");
		// }
		// return ok;

		assertTrue(axiom1.isInAlphabet(alphabet));
		assertTrue(axiom2.isInAlphabet(alphabet));
	}

}
