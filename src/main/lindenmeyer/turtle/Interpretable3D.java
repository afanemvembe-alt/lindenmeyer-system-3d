package lindenmeyer.turtle;

/**
 * Interface permettant de créer un objet interprétable par une {@link AbstractTurtle3D}.
 */
public interface Interpretable3D {
    /**
     * Interpéter l'objet avec la tortue donnée.
     */
    public void interpret(AbstractTurtle3D turtle);
}
