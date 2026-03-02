package bench;

import java.time.*;

/**
 * Classe permettant de faire des tests de rapidité sur des classes.
 */
public class Bencher {
    /**
     * Execute le test avec la classe spécifiée.
     * 
     * @param toRun objet doté d'une implémentation de Runnable contenant la méthode
     *              à évaluer
     * @return les résultats du test
     */
    public static BenchResult bench(Runnable toRun) {
        Instant now = Instant.now();
        toRun.run();
        return new BenchResult(now.until(Instant.now()), toRun);
    }
}
