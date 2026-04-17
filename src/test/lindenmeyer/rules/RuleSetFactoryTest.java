package lindenmeyer.rules;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
// import lindenmeyer.rules.ContextRule;
import lindenmeyer.rules.GenericRule;
import lindenmeyer.rules.RuleSet;
// import lindenmeyer.rules.SimpleRule;
import lindenmeyer.rules.RuleSetFactory;
import lindenmeyer.rules.SimpleRule;
// import lindenmeyer.symbols.Symbol;
import lindenmeyer.symbols.SymbolFactory;
// import lindenmeyer.symbols.SymbolList;
import lindenmeyer.symbols.SymbolList;
import org.junit.jupiter.api.Test;

/**
 * Une classe qui fournit les tests pour une `{@link RuleSetFactory}`
 */
public class RuleSetFactoryTest {

    public static final String sampleSucc = "sdfg";
    public static final String samplePred = "g";

    public static final char ruleSep = ';';
    public static final char partSep = '=';

    public static final SymbolFactory sf = SymbolFactory.fromString(
        sampleSucc + samplePred
    );

    // public final static String strRuleSet = "A>B+B,B>A";
    public static final String strRuleSet = String.format(
        "A%1$sB+B%2$sB%1$sA",
        partSep,
        ruleSep
    );

    // public static void main(String[] args) {
    // boolean res = true;

    // // res &= RuleFactory_constructDefault_Test();
    // // res &= RuleFactory_constructParam_Test();
    // // res &= RuleFactory_getRuleSeparator_Test();
    // // res &= RuleFactory_getPartSeparator_Test();
    // // res &= RuleFactory_getFactory_Test();
    // // res &= RuleFactory_parseString_Test();

    // System.out.println(res);
    // }

    @Test
    void RuleFactory_constructDefault_Test() {
        // boolean res = true;
        RuleSetFactory rf = new RuleSetFactory(sf);

        // res &= rf instanceof RuleSetFactory;
        // res &= rf.getPartSeparator() == '>';
        // res &= rf.getRuleSeparator() == ',';
        // res &= rf.getFactory().equals(sf);

        assertInstanceOf(RuleSetFactory.class, rf);
        assertEquals('>', rf.getPartSeparator());
        assertEquals(',', rf.getRuleSeparator());
        assertEquals(sf, rf.getFactory());

        // return res;
    }

    @Test
    void RuleFactory_constructParam_Test() {
        // boolean res = true;
        RuleSetFactory rf = new RuleSetFactory(ruleSep, partSep, sf);

        // res &= rf instanceof RuleSetFactory;
        // res &= rf.getPartSeparator() == ruleSep;
        // res &= rf.getRuleSeparator() == partSep;
        // res &= rf.getFactory().equals(sf);

        assertInstanceOf(RuleSetFactory.class, rf);
        assertEquals(ruleSep, rf.getRuleSeparator());
        assertEquals(partSep, rf.getPartSeparator());
        assertEquals(sf, rf.getFactory());

        // return res;
    }

    @Test
    void RuleFactory_getRuleSeparator_Test() {
        RuleSetFactory rf = new RuleSetFactory(ruleSep, partSep, sf);

        assertEquals(ruleSep, rf.getRuleSeparator());
        // return rf.getRuleSeparator() == ruleSep;
    }

    @Test
    void RuleFactory_getPartSeparator_Test() {
        RuleSetFactory rf = new RuleSetFactory(ruleSep, partSep, sf);

        assertEquals(partSep, rf.getPartSeparator());
        // return rf.getPartSeparator() == partSep;
    }

    @Test
    void RuleFactory_getFactory_Test() {
        RuleSetFactory rf = new RuleSetFactory(ruleSep, partSep, sf);

        assertEquals(sf, rf.getFactory());
        // return rf.getFactory().equals(sf);
    }

    @Test
    void RuleFactory_parseString_Test() {
        System.err.println(strRuleSet);
        RuleSetFactory rf = new RuleSetFactory(ruleSep, partSep, sf);
        RuleSet rs = rf.parseString(strRuleSet);
        // strRuleSet = A>B+B,B>A;
        ArrayList<String> rs_Strings = new ArrayList<>();

        // rs_Strings.add("A=B+B");
        rs_Strings.add(String.format("A%sB+B", partSep));
        SimpleRule abb = new SimpleRule(
            sf.getSymbol('A'),
            SymbolList.fromString("B+B", sf)
        );
        // rs_Strings.add("B=A");
        rs_Strings.add(String.format("B%sA", partSep));
        SimpleRule ba = new SimpleRule(
            sf.getSymbol('B'),
            SymbolList.fromString("A", sf)
        );

        for (GenericRule r : rs.getRules()) {
            System.err.println(r);
        }

        System.err.println(abb);
        System.err.println(ba);

        assertTrue(rs.getRules().contains(abb));
        assertTrue(rs.getRules().contains(ba));

        // for (GenericRule rule : rs.getRules()) {
        // rs_Strings.remove(rule.toString());
        // }

        // assertTrue(rs_Strings.isEmpty());
        // return rs_Strings.isEmpty();
    }
}
