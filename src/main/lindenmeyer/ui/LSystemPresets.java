package lindenmeyer.ui;

import java.util.ArrayList;
import lindenmeyer.axiom.Axiom;
import lindenmeyer.lsystem.LSystem;

public class LSystemPresets {

    private ArrayList<LSystem> presets;
    private ArrayList<ConfigLsystem> configs;

    public LSystemPresets() {
        this.presets = new ArrayList<>();
        this.configs = new ArrayList<>();
        loadPresets();
    }

    public ArrayList<LSystem> getPresets() {
        return this.presets;
    }

    public ArrayList<ConfigLsystem> getConfigs() {
        return this.configs;
    }

    private void loadPresets() {
        // Koch Flake
        LSystem koch = new LSystem(new Axiom("F--F--F"));
        koch.ajouterRegle('F', "F+F--F+F");
        this.presets.add(koch);
        this.configs.add(
            new ConfigLsystem(
                60,
                10,
                300,
                400,
                "Koch Flake - Max steps 6\nDescription: Flocon de Koch classique\nAngle: 60\nLongueur: 10"
            )
        );

        // Tree
        LSystem tree = new LSystem(new Axiom("X"));
        tree.ajouterRegle('X', "F-[[X]+X]+F[+FX]-X");
        tree.ajouterRegle('F', "FF");
        this.presets.add(tree);
        this.configs.add(
            new ConfigLsystem(
                25,
                5,
                350,
                500,
                "Simple Tree - Max steps 5\nDescription: Arbre fractal\nAngle: 25\nLongueur: 5"
            )
        );

        // Dragon Curve
        LSystem dragon = new LSystem(new Axiom("FX"));
        dragon.ajouterRegle('X', "X+YF+");
        dragon.ajouterRegle('Y', "-FX-Y");
        this.presets.add(dragon);
        this.configs.add(
            new ConfigLsystem(
                90,
                5,
                400,
                400,
                "Dragon Curve - Max steps 5\nDescription: Courbe du dragon\nAngle: 90\nLongueur: 5"
            )
        );

        // Sierpinski Triangle
        LSystem sierpinski = new LSystem(new Axiom("A"));
        sierpinski.ajouterRegle('A', "B-A-B");
        sierpinski.ajouterRegle('B', "A+B+A");
        this.presets.add(sierpinski);
        this.configs.add(
            new ConfigLsystem(
                60,
                5,
                300,
                300,
                "Sierpinski Triangle - Max steps 5\nDescription: Triangle de Sierpinski\nAngle: 60\nLongueur: 5"
            )
        );

        // Hilbert Curve
        LSystem hilbert = new LSystem(new Axiom("A"));
        hilbert.ajouterRegle('A', "-BF+AFA+FB-");
        hilbert.ajouterRegle('B', "+AF-BFB-FA+");
        this.presets.add(hilbert);
        this.configs.add(
            new ConfigLsystem(
                90,
                5,
                400,
                400,
                "Hilbert Curve - Max steps 5\nDescription: Courbe de Hilbert\nAngle: 90\nLongueur: 5"
            )
        );

        // Peano Curve
        LSystem peano = new LSystem(new Axiom("X"));
        peano.ajouterRegle('X', "XFYFX+F+YFXFY-F-XFYFX");
        peano.ajouterRegle('Y', "YFXFY-F-XFYFX+F+YFXFY");
        this.presets.add(peano);
        this.configs.add(
            new ConfigLsystem(
                90,
                3,
                350,
                350,
                "Peano Curve - Max steps 3\nDescription: Courbe de Peano\nAngle: 90\nLongueur: 3"
            )
        );

        // Levy C Curve
        LSystem levy = new LSystem(new Axiom("F"));
        levy.ajouterRegle('F', "+F--F+");
        this.presets.add(levy);
        this.configs.add(
            new ConfigLsystem(
                45,
                8,
                400,
                400,
                "Levy C Curve - Max steps 8\nDescription: Courbe de Levy C\nAngle: 45\nLongueur: 8"
            )
        );

        // Quadratic Koch Island
        LSystem kochIsland = new LSystem(new Axiom("F+F+F+F"));
        kochIsland.ajouterRegle('F', "F+F-F-FF+F+F-F");
        this.presets.add(kochIsland);
        this.configs.add(
            new ConfigLsystem(
                90,
                4,
                300,
                300,
                "Quadratic Koch Island - Max steps 4\nDescription: Flocon quadratique\nAngle: 90\nLongueur: 4"
            )
        );

        // Plant-like fractal
        LSystem plant = new LSystem(new Axiom("X"));
        plant.ajouterRegle('X', "F-[[X]+X]+F[+FX]-X");
        plant.ajouterRegle('F', "FF");
        this.presets.add(plant);
        this.configs.add(
            new ConfigLsystem(
                25,
                5,
                350,
                500,
                "Plant-like fractal - Max steps 5\nDescription: Plante fractale\nAngle: 25\nLongueur: 5"
            )
        );

        // Tree variant 2
        LSystem tree2 = new LSystem(new Axiom("F"));
        tree2.ajouterRegle('F', "F[+F]F[-F]F");
        this.presets.add(tree2);
        this.configs.add(
            new ConfigLsystem(
                25,
                5,
                350,
                500,
                "Tree variant 2 - Max steps 5\nDescription: Variante d'arbre\nAngle: 25\nLongueur: 5"
            )
        );
    }
}
