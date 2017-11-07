package View;

import Controller.CheckPrinter;
import Controller.DishOrderController;
import Model.Dish;
import Model.Table;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

import static View.Window.*;

/**
 * Created by y50-70 on 26.10.2017.
 */
public class DishOrderPanel extends JPanel {
    private GridBagConstraints constraints;
    private GridBagConstraints orderСonstraints;
    private DishOrderController controller;
    private Window window;
    private JPanel orderDishesPanel;
    private JLabel selectedTableNameLabel;

    public GridBagConstraints getOrderСonstraints() {
        return orderСonstraints;
    }

    public JLabel getSelectedTableNameLabel() {
        return selectedTableNameLabel;
    }

    public DishOrderController getController() {
        return controller;
    }

    public JPanel getOrderDishesPanel() {
        return orderDishesPanel;
    }

    public DishOrderPanel(Window window) {
        super(new GridBagLayout());
        setVisible(true);
        constraints = initGridBagConstraints(1, 4);
        setPreferredSize(new Dimension(250, 480));
        this.window = window;
        controller = new DishOrderController(this, this.window);

        JButton button;
        JLabel label = new JLabel("<html><u>Заказ</u></html>");
        label.setForeground(new Color(225, 0, 25));
        label.setFont(TAB_AND_BUTTON_FONT);
        add(label, constraints);
        constraints.gridy++;

        orderDishesPanel = new JPanel(new GridBagLayout());
        orderСonstraints = initGridBagConstraints(1, 100);

        selectedTableNameLabel = new JLabel("<html><u>Стол</u></html>");
        Table table = window.getCurrentTableArea().getLastSelectedTable();
        if (table != null) {
            selectedTableNameLabel = new JLabel("<html><u>" + table.getTableName() + "</u></html>");
            List<Dish> dishes = window.getDishOrderPanel().getController().getDishesFromTable(table);
            orderDishesPanel.removeAll();
            dishes.forEach((d) -> orderDishesPanel.add(d));
        }
        selectedTableNameLabel.setForeground(new Color(0, 0, 0));
        selectedTableNameLabel.setFont(TAB_AND_BUTTON_FONT);
        add(selectedTableNameLabel, constraints);
        constraints.gridy++;


        add(orderDishesPanel, constraints);
        constraints.gridy++;

        constraints.gridy = 1000;
        button = new JButton("Оформить заказ");
        button.addActionListener(e -> {
            DishPanel dishPanel = this.window.getDishPanel();
            dishPanel.getController().backToSimpleMode();
            Table lastSelectedTable = dishPanel.getWindow().getCurrentTableArea().getLastSelectedTable();
            window.getController().setOrderDone(lastSelectedTable, true);
            orderDishesPanel.removeAll();

        });
        button.setFont(TAB_AND_BUTTON_FONT);
        add(button, constraints);
        constraints.gridy++;

        button = new JButton("Печать чека");
        button.addActionListener(e -> {
            DishPanel dishPanel = window.getDishPanel();
            Table lastSelectedTable = dishPanel.getWindow().getCurrentTableArea().getLastSelectedTable();
            String tableName = lastSelectedTable.getTableName();
            Component[] dishes = (Component[]) orderDishesPanel.getComponents();
            CheckPrinter.printCheck(tableName, dishes);
            window.getController().setOrderDone(lastSelectedTable, false);
            dishPanel.getController().backToSimpleMode();
            window.getDishOrderPanel().getController().removeDishes(lastSelectedTable);
            orderDishesPanel.removeAll();
        });
        button.setFont(TAB_AND_BUTTON_FONT);
        add(button, constraints);
    }

    private GridBagConstraints initGridBagConstraints(int gridheight, int gridwidth) {
        GridBagConstraints constraints = new GridBagConstraints();
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
        return constraints;
    }
}
