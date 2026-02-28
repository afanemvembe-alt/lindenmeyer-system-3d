package test.rules;

import lindenmeyer.rules.GenericRule;
import lindenmeyer.rules.SimpleRule;
import lindenmeyer.symbols.SymbolFactory;
import lindenmeyer.symbols.SymbolList;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 * Une classe qui fournit les tests pour une `{@link SimpleRule}`
 */
public class SimpleRuleTest {
    /** Un tableau des caratères pour les tests */
    public final static String sampleSucc = "sdfg";
    public final static String samplePred = "g";

    // need a method to add symbols to the factory from a string
    public final static SymbolFactory sf = new SymbolFactory();
    public final static SymbolList aSuccList = SymbolList.fromString(sampleSucc, sf);
    public final static SymbolList aPredList = SymbolList.fromString(samplePred, sf);

    // public static void main(String[] args) {
    // boolean res = true;

    // // res &= SimpleRule_construct_Test();
    // // res &= SimpleRule_getPredecessor_Test();

    // System.out.println(res);
    // }

    @Test
    void SimpleRule_construct_Test() {
        // boolean res = true;
        GenericRule simpleRule = new SimpleRule(aPredList.get(0), aSuccList);

        // res &= simpleRule instanceof SimpleRule;

        assertInstanceOf(SimpleRule.class, simpleRule);
        // return res;
    }

    @Test
    void SimpleRule_getPredecessor_Test() {
        // boolean res = true;
        GenericRule simpleRule = new SimpleRule(aPredList.get(0), aSuccList);

        // res &= simpleRule.getPredecessor() instanceof SymbolList;
        // res &= simpleRule.getPredecessor().equals(aPredList);

        assertInstanceOf(SymbolList.class, simpleRule.getPredecessor());
        assertEquals(aPredList, simpleRule.getPredecessor());

        // return res;
    }
}
