package lindenmeyer.symbols;

import lindenmeyer.turtle.AbstractTurtle3D;

// import turtle.Turtle;

/**
 * Ensemble de constantes prédéfinies permettant des mouvements particuliers d'une {@link AbstractTurtle3D}.
 */
public class Constante extends Symbol {

    // private char symbole;

    // Constructeur
    public Constante(char symbole) {
        super(symbole);
    }

    // Action par défaut : ne fait rien
    // public void action(Turtle turtle) {
    // Les constantes spécifiques vont redéfinir cette methode
    // }

    @Override
    public void interpret(AbstractTurtle3D turtle) {
        switch (getSymbol()) {
            case '[':
                turtle.savePosition();
                break;
            case ']':
                turtle.restorePosition();
                break;
            case '+':
                turtle.changeAngleX(true);
                break;
            case '-':
                turtle.changeAngleX(false);
                break;
            case '*':
                turtle.changeAngleZ(true);
                break;
            case '/':
                turtle.changeAngleZ(false);
                break;
        }
    }
}
