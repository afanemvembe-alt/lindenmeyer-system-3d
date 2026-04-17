package lindenmeyer.rules;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.Set;
import lindenmeyer.rules.GenericRule;
import lindenmeyer.rules.RuleSet;
import lindenmeyer.rules.SimpleRule;
import lindenmeyer.rules.GenericRuleTest.MockRule;
import lindenmeyer.symbols.Symbol;
import lindenmeyer.symbols.SymbolFactory;
import lindenmeyer.symbols.SymbolList;
import org.junit.jupiter.api.Test;

public class RuleSetTest {

    public static SymbolFactory symbolFactory = new SymbolFactory();

    public static Symbol a = symbolFactory.getSymbol('A');
    public static Symbol b = symbolFactory.getSymbol('B');
    public static Symbol plus = symbolFactory.getSymbol('+');

    @Test
    void RuleSet_constructEmpty_Test() {
        RuleSet rs = new RuleSet();
        assertInstanceOf(RuleSet.class, rs);
        assertTrue(rs.getRules().isEmpty());
    }

    @Test
    void RuleSet_constructParam_Test() {
        Set<GenericRule> rules = new HashSet<>();

        SymbolList a_succList = new SymbolList();
        a_succList.add(b);
        a_succList.add(plus);
        a_succList.add(b);

        // Correction du constructeur SimpleRule (Symbol, SymbolList)
        GenericRule rule_a_to_b = new SimpleRule(a, a_succList);

        SymbolList b_succList = new SymbolList();
        b_succList.add(a);

        GenericRule rule_b_to_a = new SimpleRule(b, b_succList);

        rules.add(rule_a_to_b);
        rules.add(rule_b_to_a);

        RuleSet rs = new RuleSet(rules);
        assertTrue(rs.getRules().containsAll(rules));
    }

    @Test
    void RuleSet_add_Test() {
        RuleSet rs = new RuleSet();
        SymbolList a_succList = new SymbolList();
        a_succList.add(b);
        a_succList.add(plus);
        a_succList.add(b);

        GenericRule rule_a_to_b = new SimpleRule(a, a_succList);
        rs.add(rule_a_to_b);

        assertTrue(rs.getRules().contains(rule_a_to_b));
    }

    @Test
    void RuleSet_successorOf_Test() {
        SymbolList a_predList = new SymbolList();
        a_predList.add(a);

        SymbolList a_succList = new SymbolList();
        a_succList.add(b);
        a_succList.add(plus);
        a_succList.add(b);

        GenericRule rule_a_to_b = new SimpleRule(a, a_succList);

        RuleSet rs = new RuleSet();
        rs.add(rule_a_to_b);

        // CORRECTION ICI : Ajout des arguments null pour left et right context
        SymbolList res_SuccList = rs.successorOf(a_predList, null, null);

        assertTrue(res_SuccList.containsAll(a_succList));
    }

    @Test
    void equals() {
        GenericRule ruleA = new MockRule(a, b);
        GenericRule ruleB = new MockRule(b, a);
        GenericRule ruleC = new MockRule(a, a);
        GenericRule ruleD = new MockRule(b, b);

        RuleSet ruleSet = new RuleSet();
        ruleSet.add(ruleA);
        ruleSet.add(ruleB);

        RuleSet extraRuleSet = new RuleSet();
        extraRuleSet.add(ruleA);
        extraRuleSet.add(ruleB);
        extraRuleSet.add(ruleC);

        RuleSet lessRuleSet = new RuleSet();
        lessRuleSet.add(ruleA);

        RuleSet dRuleSet = new RuleSet();
        dRuleSet.add(ruleD);

        RuleSet emptyRuleSet = new RuleSet();

        assertEquals(ruleSet, ruleSet);
        assertNotEquals(ruleSet, extraRuleSet);
        assertNotEquals(ruleSet, lessRuleSet);
        assertNotEquals(lessRuleSet, dRuleSet);
        assertEquals(emptyRuleSet, emptyRuleSet);
    }
}