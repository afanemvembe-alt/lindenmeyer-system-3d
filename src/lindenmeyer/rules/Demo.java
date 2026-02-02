package lindenmeyer.rules;

import lindenmeyer.symbols.Symbol;
import lindenmeyer.symbols.SymbolFactory;
import lindenmeyer.symbols.SymbolList;

/**
 * Demonstration de l'utilisation du package lindenmeyer.rules.
 */
public class Demo {
    /**
     * Execute la demo
     * @param args non utilisé
     */
    public static void main(String[] args) {
        SymbolFactory symbolFactory = new SymbolFactory();

        Symbol a = symbolFactory.getSymbol('A');
        Symbol b = symbolFactory.getSymbol('B');
        Symbol plus = symbolFactory.getSymbol('+');

        SymbolList a_predList = new SymbolList();
        SymbolList a_succList = new SymbolList();

        a_predList.add(a);
        a_succList.add(b);
        a_succList.add(plus);
        a_succList.add(b);

        GenericRule rule_a_to_b = new SimpleRule(a, a_succList);

        System.out.println(rule_a_to_b.getPredecessor().hashCode() + " " + a_predList.hashCode());
        System.out.println(rule_a_to_b.getPredecessor().equals(a_predList));
        System.out.println(a_predList.equals(a_predList));
        System.out.println(a_predList.getSymbols().equals(a_predList.getSymbols()));
        System.out.println(a.equals(a));

        System.out.println(rule_a_to_b);

        RuleFactory factory = new RuleFactory(',', '>', symbolFactory);
        RuleSet ruleSet = factory.parseString("A>B+B,B>A");

        for (GenericRule rule : ruleSet.getRules()) {
            System.out.println(rule);
        }

        System.out.println("Rule A>B is applicable to A : " + rule_a_to_b.isApplicable(a_predList));
        System.out.println("Rule A>B is applicable to B+B : " + rule_a_to_b.isApplicable(a_succList));
    }
}
