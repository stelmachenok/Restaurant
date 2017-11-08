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
public class DishEditPanel extends JPanel {
    private JTextField typeTextField;
    private JTextField dishNameTextField;
    private JTextField dishPriceTextField;
    private DishEditController controller;
    private Window window;
    private GridBagConstraints constraints;

    DishEditPanel(Window window) {
        super(new GridBagLayout());
        this.window = window;
        controller = new DishEditController(window, this);
        setVisible(true);
        initGridBagConstraints();
        fillContent();
    }

    private void fillContent(){
        addTypeContent();
        addDishContent();
    }

    private void addTypeContent(){
        JLabel label;
        JButton button;

        label = new JLabel("<html><u>Раздел</u></html>");
        label.setForeground(new Color(225, 0, 25));
        label.setFont(TAB_AND_BUTTON_FONT);
        add(label, constraints);
        constraints.gridy++;

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
    }

    private void addDishContent(){
        JLabel label;
        JButton button;

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
            }
        });
        dishPriceTextField.setFont(TAB_AND_BUTTON_FONT);
        add(dishPriceTextField, constraints);
        constraints.gridy++;


        button = new JButton("Удалить");
        button.addActionListener(e -> {
            controller.removeDish();
        });
        button.setFont(TAB_AND_BUTTON_FONT);
        add(button, constraints);
    }

    private void initGridBagConstraints() {
        constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.fill = GridBagConstraints.CENTER;
        constraints.gridheight = 1;
        constraints.gridwidth = 1;
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.insets = new Insets(10, 0, 0, 0);
        constraints.ipadx = 1;
        constraints.ipady = 1;
        constraints.weightx = 0.0;
        constraints.weighty = 0.0;
    }

    public DishEditController getController() {
        return controller;
    }

    public JTextField getTypeTextField() {
        return typeTextField;
    }

    public JTextField getDishNameTextField() {
        return dishNameTextField;
    }

    public JTextField getDishPriceTextField() {
        return dishPriceTextField;
    }
}
