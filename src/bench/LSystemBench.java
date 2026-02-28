package bench;

import lindenmeyer.lsystem.*;

public abstract class LSystemBench implements Runnable {
    protected Integer generations;
    protected LSystem lSystem;

    public LSystemBench(int generations, LSystem lSystem) {
        this.generations = generations;
        this.lSystem = lSystem;
    }

    @Override
    public void run() {
        lSystem.step(generations);
        // for (int i = 0; i < generations; i++) {
        //     System.err.println(lSystem.getCurrentGeneration().size());
        //     lSystem.step();
        // }
    }
}
