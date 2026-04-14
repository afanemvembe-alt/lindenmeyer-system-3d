public static void Demo(String[] args) {
    SymbolFactory symbolFactory = new SymbolFactory();

    // 1. Créer les symboles
    Symbol a = symbolFactory.getSymbol('A');
    Symbol b = symbolFactory.getSymbol('B');
    Symbol c = symbolFactory.getSymbol('C');
    Symbol d = symbolFactory.getSymbol('D');

    // 2. Test d'une règle contextuelle : A < B > C -> D
    SymbolList succD = new SymbolList(symbolFactory);
    succD.add(d);

    SymbolList contextL = SymbolList.of(a);
    SymbolList contextR = SymbolList.of(c);

    ContextRule contextRule = new ContextRule(b, succD, contextL, contextR);

    System.out.println("--- Test Règle Contextuelle ---");
    System.out.println("Règle créée : " + contextRule);

    // Simulation de voisinage
    SymbolList current = SymbolList.of(b);
    SymbolList leftOk = SymbolList.of(a);
    SymbolList rightOk = SymbolList.of(c);
    SymbolList rightBad = SymbolList.of(a);

    System.out.println("Applicable avec A < B > C ? "
        + contextRule.isApplicable(current, leftOk, rightOk)); // true

    System.out.println("Applicable avec A < B > A ? "
        + contextRule.isApplicable(current, leftOk, rightBad)); // false

    // 3. Test du RuleSet
    System.out.println("\n--- Test RuleSet ---");

    RuleSet ruleSet = new RuleSet();
    ruleSet.add(contextRule);

    SymbolList result = ruleSet.successorOf(current, leftOk, rightOk);
    System.out.println("Successeur trouvé : " + result);

    // 4. Test du parsing (IMPORTANT pour ton projet)
    System.out.println("\n--- Test Parsing ---");

    RuleSetFactory factory = new RuleSetFactory(symbolFactory);
    RuleSet parsedRules = factory.parseString("A<B>C>D");

    SymbolList parsedResult = parsedRules.successorOf(current, leftOk, rightOk);
    System.out.println("Successeur via parsing : " + parsedResult);
}