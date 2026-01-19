package symbols;

import turtle.Turtle;

public class Constante {

    private char symbole;

    // Constructeur
    public Constante(char symbole) {
        this.symbole = symbole;
    }

    // Getter pour le symbole
    public char getSymbole() {
        return symbole;
    }

    // Action par défaut : ne fait rien
    public void action(Turtle turtle) {
        // Les constantes spécifiques vont redéfinir cette méthode
    }
}
