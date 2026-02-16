package test.rules;

import java.util.ArrayList;

// import lindenmeyer.rules.ContextRule;
import lindenmeyer.rules.GenericRule;
// import lindenmeyer.rules.SimpleRule;
import lindenmeyer.rules.RuleFactory;
import lindenmeyer.rules.RuleSet;

// import lindenmeyer.symbols.Symbol;
import lindenmeyer.symbols.SymbolFactory;
// import lindenmeyer.symbols.SymbolList;

/**
 * Une classe qui fournit les tests pour une `{@link RuleFactory}`
 */
public class RuleFactoryTest
{
    public final static String sampleSucc = "sdfg";
    public final static String samplePred = "g";

    

    public final static char ruleSep = '=';
    public final static char partSep = ';';

    public final static SymbolFactory sf = SymbolFactory.fromString(sampleSucc + samplePred);

    public final static String strRuleSet = "A>B+B,B>A";

    public static void main (String[] args)
    {
        boolean res = true;

        res &= RuleFactory_constructDefault_Test();
        res &= RuleFactory_constructParam_Test();
        res &= RuleFactory_getRuleSeparator_Test();
        res &= RuleFactory_getPartSeparator_Test();
        res &= RuleFactory_getFactory_Test();
        res &= RuleFactory_parseString_Test();

        System.out.println(res);
    }

    public static boolean RuleFactory_constructDefault_Test()
    {
        boolean res = true;
        RuleFactory rf = new RuleFactory(sf);

        res &= rf instanceof RuleFactory;
        res &= rf.getPartSeparator() == '>';
        res &= rf.getRuleSeparator() == ',';
        res &= rf.getFactory().equals(sf);

        return res;
    }

    public static boolean RuleFactory_constructParam_Test()
    {
        boolean res = true;
        RuleFactory rf = new RuleFactory(ruleSep, partSep, sf);

        res &= rf instanceof RuleFactory;
        res &= rf.getPartSeparator() == ruleSep;
        res &= rf.getRuleSeparator() == partSep;
        res &= rf.getFactory().equals(sf);

        return res;
    }

    public static boolean RuleFactory_getRuleSeparator_Test()
    {
        RuleFactory rf = new RuleFactory(ruleSep, partSep, sf);
        return rf.getRuleSeparator() == ruleSep;
    }

    public static boolean RuleFactory_getPartSeparator_Test()
    {
        RuleFactory rf = new RuleFactory(ruleSep, partSep, sf);
        return rf.getPartSeparator() == partSep;
    }

    public static boolean RuleFactory_getFactory_Test()
    {
        RuleFactory rf = new RuleFactory(ruleSep, partSep, sf);
        return rf.getFactory().equals(sf);
    }

    public static boolean RuleFactory_parseString_Test()
    {
        RuleFactory rf = new RuleFactory(ruleSep, partSep, sf);
        RuleSet rs = rf.parseString(strRuleSet);
        // strRuleSet = A>B+B,B>A;
        ArrayList<String> rs_Strings = new ArrayList<>();

        rs_Strings.add("A->B+B");
        rs_Strings.add("B->A");

        for (GenericRule rule : rs.getRules()) 
        {
            rs_Strings.remove(rule.toString());
        }
        return rs_Strings.isEmpty();
    }
}