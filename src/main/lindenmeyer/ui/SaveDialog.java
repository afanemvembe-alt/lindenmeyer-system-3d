package lindenmeyer.ui;

import javax.swing.*;
import java.awt.*;

public class SaveDialog extends JDialog {

    private JTextField nameField;
    private JTextArea descriptionArea;
    private boolean confirmed = false;

    public SaveDialog(Frame parent) {
        super(parent, "New Preset", true); // modal

        nameField = new JTextField(20);
        descriptionArea = new JTextArea(5, 20);
        descriptionArea.setLineWrap(true);
        descriptionArea.setWrapStyleWord(true);

        JScrollPane scroll = new JScrollPane(descriptionArea);

        JButton okButton = new JButton("OK");
        JButton cancelButton = new JButton("Cancel");

        okButton.addActionListener(e -> {
            confirmed = true;
            dispose();
        });

        cancelButton.addActionListener(e -> dispose());

        JPanel panel = new JPanel(new BorderLayout(10, 10));

        JPanel inputPanel = new JPanel(new GridLayout(2, 2, 5, 5));
        inputPanel.add(new JLabel("Name:"));
        inputPanel.add(nameField);
        inputPanel.add(new JLabel("Description:"));
        inputPanel.add(scroll);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(okButton);
        buttonPanel.add(cancelButton);

        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.add(inputPanel, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        setContentPane(panel);
        pack();
        setLocationRelativeTo(parent);
    }

    public boolean isConfirmed() { return this.confirmed; }

    public String getName() { return this.nameField.getText(); }

    public String getDescription() { return this.descriptionArea.getText(); }
}