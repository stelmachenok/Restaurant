package View;

import javax.swing.*;
import java.awt.*;

/**
 * Created by y50-70 on 14.10.2017.
 */
public class OrderPanel extends JPanel{
    GridBagConstraints constraints;
    public OrderPanel(){
        setLayout(new GridBagLayout());
        setVisible(false);
        initConstraints();
        fillingPanel();
    }

    private void initConstraints(){
        constraints = new GridBagConstraints();
        constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.fill = GridBagConstraints.CENTER;
        constraints.gridheight = GridBagConstraints.RELATIVE;
        constraints.gridwidth = 4;
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.insets = new Insets(10, 0, 0, 0);
        constraints.ipadx = 1;
        constraints.ipady = 1;
        constraints.weightx = 0.0;
        constraints.weighty = 0.0;
    }

    private void fillingPanel(){

    }
}
