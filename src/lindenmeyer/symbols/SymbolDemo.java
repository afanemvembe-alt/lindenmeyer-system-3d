package lindenmeyer.symbols;

public class SymbolDemo {
    public static void main(String[] args) {
        System.out.println("=== TEST DU PACKAGE SYMBOLS ===\n");

        // 1. Initialisation
        SymbolFactory factory = new SymbolFactory();
        Alphabet alphabet = new Alphabet();

        // 2. Test du Design Pattern Flyweight
        System.out.println("--- 1. Test de la Factory ---");
        Symbol s1 = factory.getSymbol('F');
        Symbol s2 = factory.getSymbol('F');
        
        System.out.println("Instance 1 ('F') ID: " + System.identityHashCode(s1));
        System.out.println("Instance 2 ('F') ID: " + System.identityHashCode(s2));
        
        if (s1 == s2) {
            System.out.println(" SUCCES : C'est le meme objet en memoire.");
        }

        // 3. Test de SymbolList 
        System.out.println("\n--- 2. Test de SymbolList ---");
        String texte = "F+X-F";
        SymbolList maListe = SymbolList.fromString(texte, factory);
        
        // Note : On passe par getSymbols() car c'est le nom dans ton fichier
        int taille = maListe.getSymbols().size(); 
        System.out.println("Texte: " + texte);
        System.out.println("Nombre de symboles : " + taille);
        
        // Vérification de l'unicité dans la liste
        if (maListe.getSymbols().get(0) == maListe.getSymbols().get(4)) {
            System.out.println("SUCCES : Le premier et le dernier 'F' sont le meme objet.");
        }

        // 4. Test de l'Alphabet
        System.out.println("\n--- 3. Test de l'Alphabet ---");
        Symbol symboleValide = factory.getSymbol('F');
        Symbol symboleZ = new Symbol('Z'); 
        
        System.out.println("Alphabet : " + alphabet.toString());
        System.out.println("Est-ce que 'F' est valide ? " + alphabet.isInAlphabet(symboleValide));
        System.out.println("Est-ce que 'Z' est valide ? " + alphabet.isInAlphabet(symboleZ));

        System.out.println("\n=== FIN DU TEST ===");
    }
}