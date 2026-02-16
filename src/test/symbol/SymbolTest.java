package lindenmeyer.symbols;

public class SymbolTest {

    public static void main(String[] args) {
        

        testFactoryUnicite();
        testAlphabetValidation();
        testSymbolListIntegrity();

        System.out.println("\n----- TOUS LES TESTS SONT PASSÉS AVEC SUCCÈS -----");
    }

    /**
     * Vérifie que la Factory ne crée pas de doublons en mémoire.
     */
    public static void testFactoryUnicite() {
        System.out.print("[TEST] Unicité Factory : ");
        SymbolFactory factory = new SymbolFactory();
        
        Symbol f1 = factory.getSymbol('F');
        Symbol f2 = factory.getSymbol('F');
        Symbol x1 = factory.getSymbol('X');

        // Vérification par référence mémoire (==)
        if (f1 != f2) {
            throw new RuntimeException("ÉCHEC : La factory a créé deux objets différents pour 'F'");
        }
        if (f1 == x1) {
            throw new RuntimeException("ÉCHEC : La factory donne le même objet pour 'F' et 'X'");
        }
        System.out.println("OK");
    }

    /**
     * Vérifie que l'alphabet filtre correctement les symboles.
     */
    public static void testAlphabetValidation() {
        System.out.print("[TEST] Validation Alphabet : ");
        Alphabet alphabet = new Alphabet();
        SymbolFactory factory = new SymbolFactory();

        Symbol valide = factory.getSymbol('F');
        Symbol invalide = new Symbol('Z');     

        if (!alphabet.isInAlphabet(valide)) {
            throw new RuntimeException("ÉCHEC : 'F' devrait être valide");
        }
        if (alphabet.isInAlphabet(invalide)) {
            throw new RuntimeException("ÉCHEC : 'Z' ne devrait pas être valide");
        }
        System.out.println("OK");
    }

    /**
     * Vérifie que SymbolList utilise bien la factory et contient les bons objets.
     */
    public static void testSymbolListIntegrity() {
        System.out.print("[TEST] Intégrité SymbolList : ");
        SymbolFactory factory = new SymbolFactory();
        String input = "FXF";
        SymbolList list = SymbolList.fromString(input, factory);

        // 1. Vérifier la taille
        if (list.size() != 3) {
            throw new RuntimeException("ÉCHEC : La liste devrait avoir 3 symboles");
        }

        // 2. Vérifier que le 1er et le 3ème 'F' sont identiques (Flyweight au sein de la liste)
        Symbol firstF = list.get(0);
        Symbol lastF = list.get(2);
        
        if (firstF != lastF) {
            throw new RuntimeException("ÉCHEC : Les symboles identiques dans la liste ne partagent pas la même instance");
        }
        System.out.println("OK");
    }
}