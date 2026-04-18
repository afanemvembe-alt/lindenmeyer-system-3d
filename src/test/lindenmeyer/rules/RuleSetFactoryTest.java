package lindenmeyer.rules;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertTrue;

import lindenmeyer.symbols.SymbolFactory;
import lindenmeyer.symbols.SymbolList;
import org.junit.jupiter.api.Test;

public class RuleSetFactoryTest {

    public static final String sampleSucc = "sdfg";
    public static final String samplePred = "g";
    public static final char ruleSep = ';';
    public static final char partSep = '=';

    public static final SymbolFactory sf = SymbolFactory.fromString(sampleSucc + samplePred);

    public static final String strRuleSet = String.format("A%1$sB+B%2$sB%1$sA", partSep, ruleSep);

    @Test
    void RuleFactory_constructDefault_Test() {
        RuleSetFactory rf = new RuleSetFactory(sf);
        assertInstanceOf(RuleSetFactory.class, rf);
        assertEquals('>', rf.getPartSeparator());
        assertEquals(',', rf.getRuleSeparator());
        assertEquals(sf, rf.getFactory());
    }

    @Test
    void RuleFactory_constructParam_Test() {
        RuleSetFactory rf = new RuleSetFactory(ruleSep, partSep, sf);
        assertInstanceOf(RuleSetFactory.class, rf);
        assertEquals(ruleSep, rf.getRuleSeparator());
        assertEquals(partSep, rf.getPartSeparator());
        assertEquals(sf, rf.getFactory());
    }

    @Test
    void RuleFactory_parseString_Test() {
        RuleSetFactory rf = new RuleSetFactory(ruleSep, partSep, sf);
        RuleSet rs = rf.parseString(strRuleSet);

        // On crée manuellement les règles attendues pour comparer
        SimpleRule abb = new SimpleRule(
            sf.getSymbol('A'),
            SymbolList.fromString("B+B", sf)
        );
        SimpleRule ba = new SimpleRule(
            sf.getSymbol('B'),
            SymbolList.fromString("A", sf)
        );

        // Ces assertions ne passeront QUE SI GenericRule a une méthode equals()
        assertTrue(rs.getRules().contains(abb), "La règle A=B+B devrait être présente");
        assertTrue(rs.getRules().contains(ba), "La règle B=A devrait être présente");
    }
}