package lindenmeyer.lsystem;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class FenetreLSystem extends JFrame {

    public FenetreLSystem() {
        super("L-System GUI");

        this.setSize(500, 300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();

        JLabel labelAxiome = new JLabel("Axiome :");
        JTextField axiomeField = new JTextField("F+F--F+F", 20);

        JButton generateButton = new JButton("Générer");

        JLabel resultLabel = new JLabel("Résultat : ");

        panel.add(labelAxiome);
        panel.add(axiomeField);
        panel.add(generateButton);
        panel.add(resultLabel);

        this.getContentPane().add(panel);

        generateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String axiome = axiomeField.getText();

                LSystem lsys = new LSystem(axiome);
                lsys.ajouterRegle('F', "F--F+F");

                String resultat = lsys.generer(4);

                resultLabel.setText("Résultat : " + resultat);
            }
        });

        this.setVisible(true);
    }

    public static void main(String[] args) {
        new FenetreLSystem();
    }
}