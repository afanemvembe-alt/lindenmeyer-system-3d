package lindenmeyer.ui.components;

import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JTextField;

public class TextField extends JTextField {

    public TextField() {
        super();
        this.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        this.setPreferredSize(new Dimension(180, 30));
    }
}
