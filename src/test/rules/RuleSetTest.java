package test.rules;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

// import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

// import lindenmeyer.rules.ContextRule;
import lindenmeyer.rules.GenericRule;
// import lindenmeyer.rules.RuleFactory;
import lindenmeyer.rules.RuleSet;
import lindenmeyer.rules.SimpleRule;
import lindenmeyer.symbols.Symbol;
import lindenmeyer.symbols.SymbolFactory;
import lindenmeyer.symbols.SymbolList;
import test.rules.GenericRuleTest.MockRule;

/**
 * Une classe qui fournit les tests pour une `{@link RuleSet}`
 */
public class RuleSetTest {
    public static SymbolFactory symbolFactory = new SymbolFactory();

    // créer les symboles A, B, +
    public static Symbol a = symbolFactory.getSymbol('A');
    public static Symbol b = symbolFactory.getSymbol('B');
    public static Symbol plus = symbolFactory.getSymbol('+');

    public final static String strRuleSet = "A>B+B,B>A";

    // public static void main (String[] args)
    // {
    // boolean res = true;

    // // res &= RuleSet_constructEmpty_Test();
    // // res &= RuleSet_constructParam_Test();
    // // res &= RuleSet_add_Test();
    // // res &= RuleSet_successorOf_Test();

    // System.out.println(res);
    // }

    @Test
    void RuleSet_constructEmpty_Test() {
        // boolean res = true;
        RuleSet rs = new RuleSet();

        // res &= rs instanceof RuleSet;
        // res &= rs.getRules().isEmpty();

        assertInstanceOf(RuleSet.class, rs);
        assertTrue(rs.getRules().isEmpty());

        // return res;
    }

    @Test
    void RuleSet_constructParam_Test() {
        Set<GenericRule> rules = new HashSet<>();

        // rule: (A->B+B)
        SymbolList a_predList = new SymbolList();
        SymbolList a_succList = new SymbolList();

        a_predList.add(a);
        a_succList.add(b);
        a_succList.add(plus);
        a_succList.add(b);

        GenericRule rule_a_to_b = new SimpleRule(a, a_succList);

        // rule: (B>A)
        SymbolList b_predList = new SymbolList();
        SymbolList b_succList = new SymbolList();

        b_predList.add(b);
        b_succList.add(a);

        GenericRule rule_b_to_a = new SimpleRule(b, b_succList);

        rules.add(rule_a_to_b);
        rules.add(rule_b_to_a);

        RuleSet rs = new RuleSet(rules);

        assertTrue(rs.getRules().containsAll(rules));

        // return rs.getRules().containsAll(rules);
    }

    @Test
    void RuleSet_add_Test() {
        RuleSet rs = new RuleSet();

        // rule: (A->B+B)
        SymbolList a_predList = new SymbolList();
        SymbolList a_succList = new SymbolList();

        a_predList.add(a);
        a_succList.add(b);
        a_succList.add(plus);
        a_succList.add(b);

        GenericRule rule_a_to_b = new SimpleRule(a, a_succList);

        rs.add(rule_a_to_b);

        assertTrue(rs.getRules().contains(rule_a_to_b));

        // return rs.getRules().contains(rule_a_to_b);
    }

    @Test
    void RuleSet_successorOf_Test() {
        SymbolList a_predList = new SymbolList();
        SymbolList a_succList = new SymbolList();

        a_predList.add(a);
        a_succList.add(b);
        a_succList.add(plus);
        a_succList.add(b);

        GenericRule rule_a_to_b = new SimpleRule(a, a_succList);

        RuleSet rs = new RuleSet();
        rs.add(rule_a_to_b);

        SymbolList res_SuccList = rs.successorOf(a_predList);
        // return res_SuccList.containsAll(a_succList);

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