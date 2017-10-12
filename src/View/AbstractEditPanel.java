package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import static View.Window.TAB_AND_BUTTON_FONT;

/**
 * Created by y50-70 on 12.10.2017.
 */
abstract class AbstractEditPanel extends JPanel {
    GridBagConstraints constraints;

    AbstractEditPanel(int gridheight, int gridwidth) {
        super(new GridBagLayout());
        setVisible(false);
        initGridBagConstraints(gridheight, gridwidth);
    }

    private void initGridBagConstraints(int gridheight, int gridwidth) {
        constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.fill = GridBagConstraints.CENTER;
        constraints.gridheight = gridheight;
        constraints.gridwidth = gridwidth;
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.insets = new Insets(10, 0, 0, 0);
        constraints.ipadx = 1;
        constraints.ipady = 1;
        constraints.weightx = 0.0;
        constraints.weighty = 0.0;
    }
}
