package lindenmeyer.ui.components;

import java.awt.Font;
import javax.swing.JLabel;

public class Label extends JLabel {
	
	public Label(String text){
		super(text);
		this.setFont(new Font("Segoe UI", Font.PLAIN, 14));
	}
}
