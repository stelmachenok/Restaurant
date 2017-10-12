package View;

import Controller.DishEditController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import static View.Window.*;

/**
 * Created by y50-70 on 12.10.2017.
 */
public class DishEditPanel extends AbstractEditPanel {
    private JTextField typeTextField;
    private JTextField subTypeTextField;
    private JTextField dishTextField;
    private JTabbedPane dishTabbedPane;
    private DishEditController controller;

    void setDishTabbedPane(JTabbedPane dishTabbedPane) {
        this.dishTabbedPane = dishTabbedPane;
    }

    JTextField getTypeTextField() {
        return typeTextField;
    }

    JTextField getSubTypeTextField() {
        return subTypeTextField;
    }

    public JTextField getDishTextField() {
        return dishTextField;
    }

    DishEditPanel() {
        super(1, 11);
        JLabel label = new JLabel("<html><u>Раздел</u></html>");
        label.setForeground(new Color(225, 0, 25));
        label.setFont(TAB_AND_BUTTON_FONT);
        add(label, constraints);
        constraints.gridy++;

        JButton button;
        button = new JButton("Добавить");
        button.addActionListener(e -> {
        });
        button.setFont(TAB_AND_BUTTON_FONT);
        add(button, constraints);
        constraints.gridy++;

        typeTextField = new JTextField(10);
        typeTextField.addKeyListener(new KeyAdapter() {

            @Override
            public void keyReleased(KeyEvent e) {
                if (dishTabbedPane.getTabCount() > 0) {
                    String newTitle = typeTextField.getText();
                    dishTabbedPane.setTitleAt(dishTabbedPane.getSelectedIndex(), newTitle);
                }
            }
        });
        typeTextField.setFont(TAB_AND_BUTTON_FONT);
        add(typeTextField, constraints);
        constraints.gridy++;

        button = new JButton("Удалить");
        button.addActionListener(e -> {
            if (dishTabbedPane.getTabCount() > 0) {
                dishTabbedPane.removeTabAt(dishTabbedPane.getSelectedIndex());
            }
        });
        button.setFont(TAB_AND_BUTTON_FONT);
        add(button, constraints);
        constraints.gridy++;

        label = new JLabel("<html><u>Подраздел</u></html>");
        label.setForeground(new Color(225, 0, 25));
        label.setFont(TAB_AND_BUTTON_FONT);
        add(label, constraints);
        constraints.gridy++;

        button = new JButton("Добавить");
        button.addActionListener(e -> {
        });
        button.setFont(TAB_AND_BUTTON_FONT);
        add(button, constraints);
        constraints.gridy++;

        subTypeTextField = new JTextField(10);
        subTypeTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (dishTabbedPane.getTabCount() > 0) {
                    String tabHeader = dishTabbedPane.getTitleAt(dishTabbedPane.getSelectedIndex());
                    typeTextField.setText(tabHeader);
                    JTabbedPane tab = (JTabbedPane) dishTabbedPane.getComponentAt(dishTabbedPane.getSelectedIndex());
                    if (tab != null) {
                        tab.setTitleAt(tab.getSelectedIndex(), subTypeTextField.getText());
                    }
                }
            }
        });
        subTypeTextField.setFont(TAB_AND_BUTTON_FONT);
        add(subTypeTextField, constraints);
        constraints.gridy++;

        button = new JButton("Удалить");
        button.addActionListener(e -> {
//            String title = dishTabbedPane.getTitleAt(dishTabbedPane.getSelectedIndex());
//            JTabbedPane tab = subTypeDishMap.get(title);
//            if (tab.getTabCount() > 0) {
//                tab.removeTabAt(tab.getSelectedIndex());
//            }
        });
        button.setFont(TAB_AND_BUTTON_FONT);
        add(button, constraints);
        constraints.gridy++;

        label = new JLabel("<html><u>Блюдо</u></html>");
        label.setForeground(new Color(225, 0, 25));
        label.setFont(TAB_AND_BUTTON_FONT);
        add(label, constraints);
        constraints.gridy++;

        button = new JButton("Добавить");
        button.addActionListener(e -> {
        });
        button.setFont(TAB_AND_BUTTON_FONT);
        add(button, constraints);
        constraints.gridy++;

        dishTextField = new JTextField(10);
        dishTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
        dishTextField.setFont(TAB_AND_BUTTON_FONT);
        add(dishTextField, constraints);
        constraints.gridy++;

        button = new JButton("Удалить");
        button.addActionListener(e -> {
        });
        button.setFont(TAB_AND_BUTTON_FONT);
        add(button, constraints);
    }
}
