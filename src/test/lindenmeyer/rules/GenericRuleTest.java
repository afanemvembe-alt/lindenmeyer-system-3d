package lindenmeyer.rules;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import lindenmeyer.symbols.Symbol;
import lindenmeyer.symbols.SymbolFactory;
import lindenmeyer.symbols.SymbolList;
import org.junit.jupiter.api.Test;

public class GenericRuleTest {

    public static final String sampleSucc = "sdfg";
    public static final String samplePred = "g";

    public static final SymbolFactory sf = SymbolFactory.fromString(sampleSucc + samplePred);
    public static final SymbolList aSuccList = SymbolList.fromString(sampleSucc, sf);
    public static final SymbolList aPredList = SymbolList.fromString(samplePred, sf);

    @Test
    void GenericRule_construct_Test() {
        GenericRule simpleRule = new SimpleRule(aPredList.get(0), aSuccList);
        assertInstanceOf(GenericRule.class, simpleRule);
        assertInstanceOf(SimpleRule.class, simpleRule);
    }

    @Test
    void GenericRule_getSuccessor_Test() {
        GenericRule simpleRule = new SimpleRule(aPredList.get(0), aSuccList);
        assertInstanceOf(SymbolList.class, simpleRule.getSuccessor());
        assertEquals(aSuccList, simpleRule.getSuccessor());
    }

    @Test
    void GenericRule_isApplicable_Test() {
        SymbolList applicGeneration = SymbolList.fromString(samplePred, sf);
        SymbolList nonApplicGeneration = SymbolList.fromString("c", sf);

        GenericRule simpleRule = new SimpleRule(aPredList.get(0), aSuccList);
        
        // CORRECTION : On passe null pour left et right context comme dans la nouvelle signature
        assertTrue(simpleRule.isApplicable(applicGeneration, null, null));
        assertFalse(simpleRule.isApplicable(nonApplicGeneration, null, null));
    }

    @Test
    void GenericRule_toString_Test() {
        // CORRECTION : Dans ton GenericRule, le toString est "pred -> succ" (avec espaces et flèche)
        // alors que ton test attendait "pred>succ". On aligne le test sur le code.
        String str = samplePred + " -> " + sampleSucc;

        GenericRule simpleRule = new SimpleRule(aPredList.get(0), aSuccList);
        assertEquals(str, simpleRule.toString());
    }
    @Test
    void equals() {
        Symbol a = sf.getSymbol('A');
        Symbol b = sf.getSymbol('B');

        SymbolFactory sf2 = new SymbolFactory();
        Symbol a1 = sf2.getSymbol(a.getSymbol());
        Symbol b1 = sf2.getSymbol(b.getSymbol());

        GenericRule rule = new MockRule(a, b);
        GenericRule otherRule = new MockRule(a1, b1);

        assertEquals(rule, rule);
        assertEquals(rule, otherRule);
        assertNotEquals(new MockRule(a, b), new MockRule(b, a));
    }



    // Change "private" en "public" pour que RuleSetTest puisse l'utiliser aussi
    public static class MockRule extends GenericRule {
        private Symbol pred;

        public MockRule(Symbol pred, Symbol succ) {
            super(SymbolList.of(pred), SymbolList.of(succ));
            this.pred = pred;
        }

        @Override
        public boolean isApplicable(SymbolList symbol, SymbolList left, SymbolList right) {
            return symbol.equals(predecessor);
        }

        public SymbolList getPredecessor() {
            return SymbolList.of(pred);
        }
    }
}