package lindenmeyer.ui.components;

import java.awt.Component;
import java.awt.LayoutManager;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class TitledPanel extends JPanel {

    public TitledPanel(String title, LayoutManager layout) {
        super(layout);
        this.setAlignmentX(Component.LEFT_ALIGNMENT);
        this.setBorder(BorderFactory.createTitledBorder(title));
    }
}
