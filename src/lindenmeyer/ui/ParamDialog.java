package lindenmeyer.ui;

import java.awt.*;
import javax.swing.*;

public class ParamDialog extends JDialog {

    private JTextField longueurField;
    private JTextField angleField;

    private int longueur = 10;
    private int angle = 60;
    private boolean confirmed = false;

    public ParamDialog(JFrame parent) {
        super(parent, "Paramètres", true);

        // Création des champs texte
        longueurField = new JTextField(String.valueOf(longueur), 10);
        angleField = new JTextField(String.valueOf(angle), 10);

        // Panel principal avec GridBagLayout pour alignement propre
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Labels et champs
        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(new JLabel("Longueur segment :"), gbc);
        gbc.gridx = 1;
        panel.add(longueurField, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(new JLabel("Angle :"), gbc);
        gbc.gridx = 1;
        panel.add(angleField, gbc);

        // Boutons OK et Cancel
        JButton okButton = new JButton("OK");
        JButton cancelButton = new JButton("Cancel");

        okButton.addActionListener(e -> {
            if (isInteger(longueurField.getText()) && isInteger(angleField.getText())) {
                longueur = Integer.parseInt(longueurField.getText());
                angle = Integer.parseInt(angleField.getText());
                confirmed = true;
                dispose();
            } else {
                // Message d'erreur simple et clair
                JOptionPane.showMessageDialog(this, 
                    "Veuillez entrer uniquement des nombres entiers.",
                    "Erreur de saisie",
                    JOptionPane.ERROR_MESSAGE);
            }
        });

        cancelButton.addActionListener(e -> {
            confirmed = false;
            dispose();
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(okButton);
        buttonPanel.add(cancelButton);

        // Ajout des panels à la fenêtre
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(panel, BorderLayout.CENTER);
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(parent);
    }

    // Méthode simple pour vérifier si une chaîne est un entier
    private boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch(NumberFormatException e) {
            return false;
        }
    }

    public int getLongueur() { return longueur; }
    public int getAngle() { return angle; }
    public boolean isConfirmed() { return confirmed; }

    // Test rapide
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        ParamDialog dialog = new ParamDialog(frame);
        dialog.setVisible(true);

        if (dialog.isConfirmed()) {
            System.out.println("Longueur : " + dialog.getLongueur());
            System.out.println("Angle : " + dialog.getAngle());
        } else {
            System.out.println("Annulé !");
        }
    }
}