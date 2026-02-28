package test.rules;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import lindenmeyer.rules.GenericRule;
import lindenmeyer.rules.SimpleRule;
// import lindenmeyer.symbols.Symbol;
import lindenmeyer.symbols.SymbolFactory;
import lindenmeyer.symbols.SymbolList;

/**
 * Une classe qui fournit les tests pour une `{@link GenericRule}`
 */
public class GenericRuleTest {
    /** Un tableau des caratères pour les tests */
    public final static String sampleSucc = "sdfg";
    public final static String samplePred = "g";

    // need a method to add symbols to the factory from a string
    public final static SymbolFactory sf = SymbolFactory.fromString(sampleSucc + samplePred);
    public final static SymbolList aSuccList = SymbolList.fromString(sampleSucc, sf);
    public final static SymbolList aPredList = SymbolList.fromString(samplePred, sf);

    // maybe factor out simpleRule and contextRule vars later.

    // public static void main(String[] args)
    // {
    // boolean res = true;

    // // res &= GenericRule_construct_Test();
    // // res &= GenericRule_getSuccessor_Test();
    // // res &= GenericRule_isApplicable_Test();
    // // res &= GenericRule_toString_Test();

    // System.out.println(res);
    // }

    @Test
    void GenericRule_construct_Test() {
        // boolean res = true;
        GenericRule simpleRule = new SimpleRule(aPredList.get(0), aSuccList);
        // GenericRule contextRule = new ContextRule(aSuccList, aPredList);

        // res &= simpleRule instanceof GenericRule;
        assertInstanceOf(GenericRule.class, simpleRule);
        // res &= contextRule instanceof GenericRule;

        // res &= simpleRule instanceof SimpleRule;
        assertInstanceOf(SimpleRule.class, simpleRule);
        // res &= contextRule instanceof ContextRule;
        // should getPredecessor return a Symbol instead of SymbolList?
        // res &= rule.getPredecessor() instanceof SymbolList;
        // return res;
    }

    @Test
    void GenericRule_getSuccessor_Test() {
        // boolean res = true;
        GenericRule simpleRule = new SimpleRule(aPredList.get(0), aSuccList);
        // GenericRule contextRule = new ContextRule(aPredList, aSuccList);

        // res &= simpleRule.getSuccessor() instanceof SymbolList;
        assertInstanceOf(SymbolList.class, simpleRule.getSuccessor());
        // res &= simpleRule.getSuccessor().equals(aSuccList);
        assertEquals(aSuccList, simpleRule.getSuccessor());

        // res &= contextRule.getSuccessor() instanceof SymbolList;
        // res &= contextRule.getSuccessor().equals(aSuccList);

        // return res;
    }

    // rule is applicable to SymbolList s when s is
    // equal to predecessor of rule
    @Test
    void GenericRule_isApplicable_Test() {
        // boolean res = true;
        SymbolList applicGeneration = SymbolList.fromString(samplePred, sf);
        SymbolList nonApplicGeneration = SymbolList.fromString("c", sf);

        GenericRule simpleRule = new SimpleRule(aPredList.get(0), aSuccList);
        // res &= simpleRule.isApplicable(applicGeneration);
        assertTrue(simpleRule.isApplicable(applicGeneration));
        // res &= !simpleRule.isApplicable(nonApplicGeneration);
        assertFalse(simpleRule.isApplicable(nonApplicGeneration));

        // return res;
    }

    @Test
    void GenericRule_toString_Test() {
        String str = samplePred + "->" + sampleSucc;

        GenericRule simpleRule = new SimpleRule(aPredList.get(0), aSuccList);

        // return str.equals(simpleRule.toString());
        assertEquals(str, simpleRule.toString());
    }
}