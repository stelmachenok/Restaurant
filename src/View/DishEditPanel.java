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
    private JTextField dishNameTextField;
    private JTextField dishPriceTextField;
//    private JTabbedPane dishTabbedPane;
    private DishEditController controller;
    private DishPanel dishPanel;

    public DishEditController getController() {
        return controller;
    }

    public JTextField getTypeTextField() {
        return typeTextField;
    }

    public JTextField getSubTypeTextField() {
        return subTypeTextField;
    }

    public JTextField getDishNameTextField() {
        return dishNameTextField;
    }

    public JTextField getDishPriceTextField() {
        return dishPriceTextField;
    }

//    public JTextField getDishTextField() {
//        return dishTextField;
//    }

    DishEditPanel(DishPanel dishPanel) {
        super(1, 15);
        this.dishPanel = dishPanel;
//        this.dishTabbedPane = dishTabbedPane;
        controller = new DishEditController(dishPanel, this);
        this.dishPanel.addListener(new DishPanelListener() {
            @Override
            public void tableChanged() {

                controller.refreshEditPaneFields();
            }
        });

        JLabel label = new JLabel("<html><u>Раздел</u></html>");
        label.setForeground(new Color(225, 0, 25));
        label.setFont(TAB_AND_BUTTON_FONT);
        add(label, constraints);
        constraints.gridy++;

        JButton button;
        button = new JButton("Добавить");
        button.addActionListener(e -> {
            controller.addType();
        });
        button.setFont(TAB_AND_BUTTON_FONT);
        add(button, constraints);
        constraints.gridy++;

        typeTextField = new JTextField(10);
        typeTextField.addKeyListener(new KeyAdapter() {

            @Override
            public void keyReleased(KeyEvent e) {
                controller.changeTypeName();
            }
        });
        typeTextField.setFont(TAB_AND_BUTTON_FONT);
        add(typeTextField, constraints);
        constraints.gridy++;

        button = new JButton("Удалить");
        button.addActionListener(e -> {
            controller.removeType();
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
            controller.addSubType();
        });
        button.setFont(TAB_AND_BUTTON_FONT);
        add(button, constraints);
        constraints.gridy++;

        subTypeTextField = new JTextField(10);
        subTypeTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                controller.changeSubTypeName();
            }
        });
        subTypeTextField.setFont(TAB_AND_BUTTON_FONT);
        add(subTypeTextField, constraints);
        constraints.gridy++;

        button = new JButton("Удалить");
        button.addActionListener(e -> {
            controller.removeSubType();
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
            controller.addDish();
        });

        button.setFont(TAB_AND_BUTTON_FONT);
        add(button, constraints);
        constraints.gridy++;

        label = new JLabel("<html><u>Название</u></html>");
        label.setForeground(new Color(0, 0, 0));
        label.setFont(TAB_AND_BUTTON_FONT);
        add(label, constraints);
        constraints.gridy++;

        dishNameTextField = new JTextField(10);
        dishNameTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                controller.changeDishName();
            }
        });
        dishNameTextField.setFont(TAB_AND_BUTTON_FONT);
        add(dishNameTextField, constraints);
        constraints.gridy++;

        label = new JLabel("<html><u>Цена</u></html>");
        label.setForeground(new Color(0, 0, 0));
        label.setFont(TAB_AND_BUTTON_FONT);
        add(label, constraints);
        constraints.gridy++;

        dishPriceTextField = new JTextField(10);
        dishPriceTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                controller.changeDishPrice();
                //dishTabbedPane.updateUI();
            }
        });
        dishPriceTextField.setFont(TAB_AND_BUTTON_FONT);
        add(dishPriceTextField, constraints);
        constraints.gridy++;



        button = new JButton("Удалить");
        button.addActionListener(e -> {
        });
        button.setFont(TAB_AND_BUTTON_FONT);
        add(button, constraints);
    }
}
