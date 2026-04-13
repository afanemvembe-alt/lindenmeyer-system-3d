package lindenmeyer.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import lindenmeyer.axiom.Axiom;
import lindenmeyer.lsystem.LSystem;

public class FenetreLSystem extends JFrame {

    public FenetreLSystem() {
        super("Petit-Test du LSystem");

        this.setSize(500, 300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel Monpanel = new JPanel();

        JLabel Axiomee = new JLabel("Axiome :");
        JTextField ChampDeLaxiome = new JTextField("F+F--F+F", 30);

        JButton BouttonDeGeneration = new JButton("Générer");
        JButton BouttonPourEffacerLeChampAxiome = new JButton("Supprimer");

        JLabel ProduitAttendu  = new JLabel("ProduitAttendu : ");

        Monpanel.add(Axiomee);
        Monpanel.add(ChampDeLaxiome);
        Monpanel.add(BouttonDeGeneration);
        Monpanel.add(ProduitAttendu );

        this.getContentPane().add(Monpanel);

        BouttonDeGeneration.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String axiome = ChampDeLaxiome.getText();

                LSystem lsys = new LSystem(new Axiom(axiome));
                lsys.ajouterRegle('F', "F--F+F");

                String result = lsys.generer(1);

                ProduitAttendu .setText("Résultat : " + result);
            }
        });

        this.setVisible(true);
    }

    public static void main(String[] args) {
        new FenetreLSystem();
    }
}