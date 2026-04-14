package lindenmeyer.ui.components;

import java.awt.Font;
import javax.swing.JButton;

public class Button extends JButton {

    public static Font FONT = new Font("Segoe UI", Font.PLAIN, 14);

    public Button() {
        super();
        
        setFont(FONT);
        
    }
}
