package lindenmeyer.symbols;

<<<<<<< HEAD
import turtle.Turtle;

public class Constante extends Symbol {

    private char symbole;

    // Constructeur
=======
import lindenmeyer.turtle.Turtle;

public abstract class Constante extends Symbol {

>>>>>>> 0af722f (Creation d'une classe Demopour tout le projet,corrrection de toutes les erreurs)
    public Constante(char symbole) {
        super(symbole);
    }

<<<<<<< HEAD
    // Action par défaut : ne fait rien
    public void action(Turtle turtle) {
        // Les constantes spécifiques vont redéfinir cette méthode
=======
    /**
     * Action associée à la constante.
     * Par défaut, ne fait rien.
     * Les sous-classes (ex: +, -, [, ]) redéfinissent cette méthode.
     */
    public void action(Turtle turtle) {
        // aucune action par défaut
>>>>>>> 0af722f (Creation d'une classe Demopour tout le projet,corrrection de toutes les erreurs)
    }
}
