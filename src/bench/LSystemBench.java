package bench;

import lindenmeyer.lsystem.*;

/**
 * Implémentation générique de {@link Runnable} pour un {@link LSystem}.
 */
public abstract class LSystemBench implements Runnable {
    protected Integer generations;
    protected LSystem lSystem;

    /**
     * Crée une instance de classe avec les paramètres donnés.
     * 
     * @param generations le nombre de générations à simuler
     * @param lSystem     un L-Système à évaluer
     */
    public LSystemBench(int generations, LSystem lSystem) {
        this.generations = generations;
        this.lSystem = lSystem;
    }

    @Override
    public void run() {
        lSystem.step(generations);
        // for (int i = 0; i < generations; i++) {
        // System.err.println(lSystem.getCurrentGeneration().size());
        // lSystem.step();
        // }
    }
}
