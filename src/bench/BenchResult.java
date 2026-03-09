package bench;

import java.time.Duration;

/**
 * Résultats d'un test de rapidité. Stocke la durée du test, et l'objet qui a
 * été évalué, et permet d'afficher ceux-ci.
 */
public class BenchResult {
    private Duration duration;
    private Object source;

    /**
     * Construit une instance de classe avec les paramètres donnés.
     * 
     * @param duration longueur d'un test
     * @param source   objet évalué
     */
    public BenchResult(Duration duration, Object source) {
        this.duration = duration;
        this.source = source;
    }

    @Override
    public String toString() {
        return String.format("Bench of %s completed in %s", source.toString(), duration.toString());
    }
}
