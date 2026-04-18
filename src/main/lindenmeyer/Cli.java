package lindenmeyer;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import lindenmeyer.lsystem.LSystem;
import lindenmeyer.lsystem.axiom.Axiom;
import lindenmeyer.lsystem.rules.RuleSet;
import lindenmeyer.lsystem.rules.RuleSetFactory;
import lindenmeyer.lsystem.symbols.SymbolFactory;

public class Cli {

    public static void main(String[] args) throws Exception {
        Options options = new Options(args);

        if (options.isDemo()) {
            // faire tourner une demo avec des options par defaut
            System.out.println("Mode demo actif !");
        } else {
            // sinon, on demande des infos a l'utilisateur:
            System.out.println(
                    "Bienvenue dans notre simulateur de L-Systemes !\n");
            System.out.println(
                    "Veuillez entrer quelques info sur votre simulation...\n");

            String rulesString = prompt(
                    "Quelles regles voulez-vous utiliser? Entrer des regles sous forme A->B-B, separees par une virgule (,): ");
            String axiomString = prompt("Quel sera le point de départ du LSystème? ");
            String toRun = prompt("Combien de generations ? ");

            System.out.println(rulesString + axiomString);

            SymbolFactory symbolFactory = new SymbolFactory();
            RuleSetFactory ruleFactory = new RuleSetFactory(',', '>', symbolFactory);

            RuleSet rules = ruleFactory.parseString(rulesString);

            LSystem ls = new LSystem(new Axiom(axiomString), rules, symbolFactory);

            System.out.println(ls);

            System.out.println(ls.generer(Integer.valueOf(toRun)));
        }
    }

    private static String prompt(String message) throws Exception {
        System.out.println(message);
        BufferedReader stdin = new BufferedReader(
                new InputStreamReader(System.in));
        return stdin.readLine();
    }

    private static class Options {

        private boolean demo;

        /**
         * Instancie les options avec les valeur par defaut.
         */
        public Options() {
            this.demo = false;
        }

        /**
         * Definit les options a partir de chaines de caracteres fournies.
         * 
         * @param args arguments a examiner
         */
        public Options(String[] args) {
            this();
            for (String arg : args) {
                if (arg.equals("--demo") || arg.equals("-d")) {
                    this.demo = true;
                }
            }
        }

        public boolean isDemo() {
            return this.demo;
        }

        @Override
        public String toString() {
            return "Options: demo = " + isDemo();
        }
    }
}
