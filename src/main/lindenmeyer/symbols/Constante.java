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
            // --- pile ---
            case '[': turtle.savePosition();    break;
            case ']': turtle.restorePosition(); break;

            // --- yaw (RotateU) : tourne dans le plan horizontal ---
            case '+': turtle.rotateU(true);     break;
            case '-': turtle.rotateU(false);    break;

            // --- pitch (RotateL) : tourne dans le plan vertical ---
            case '&': turtle.rotateL(true);     break;
            case '^': turtle.rotateL(false);    break;

            // --- roll (RotateH) : roule autour de l'axe de marche ---
            case '\\': turtle.rotateH(true);    break;
            case '/':  turtle.rotateH(false);   break;

            // --  utiliser comme rotation Z -> on le mappe sur pitch ---
            case '*': turtle.changeAngleZ(true); break;

            // --- demi-tour : inverse heading et left ---
            case '|': turtle.rotateU(true);
                      turtle.rotateU(true); break; // deux fois l'angle si angle=90°
        }
    }
}