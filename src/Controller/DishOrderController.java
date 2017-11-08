package Controller;

import Model.Dish;
import Model.Table;
import View.DishOrderPanel;
import View.DishPanel;
import View.Window;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by y50-70 on 26.10.2017.
 */
public class DishOrderController {
    private DishOrderPanel dishOrderPanel;
    private Window window;
    private Map<Table, List<Dish>> tableToDishesMap;

    public DishOrderController(DishOrderPanel dishOrderPanel, Window window){
        this.dishOrderPanel = dishOrderPanel;
        this.window = window;
        tableToDishesMap = new HashMap<>();
    }

    void addTableToMap(Table table){
        tableToDishesMap.put(table, new ArrayList<>());
    }

    void refreshSelectedTableLabel(){
        JLabel selectedTableNameLabel = dishOrderPanel.getSelectedTableNameLabel();
        Table table = window.getCurrentTableArea().getLastSelectedTable();
        selectedTableNameLabel.setText("<html><u>Стол: " + table.getTableName()+ "</u></html>");
    }

    void refreshOrderNames(){
        Table lastSelectedTable = window.getController().getLastSelectedTable();
        List<Dish> dishes = getDishesFromTable(lastSelectedTable);
        dishOrderPanel.getOrderDishesPanel().removeAll();
        dishes.forEach((d) -> {
            dishOrderPanel.getController().addDishToDisplay(lastSelectedTable, d);
        });
    }

    void addDishToTable(Table table, Dish dish){
        tableToDishesMap.get(table).add(dish);
    }

    public void addDishToDisplay(Table table, Dish dish){
        GridBagConstraints constraints = dishOrderPanel.getOrderСonstraints();
        JLabel dishName = new JLabel(dish.getDishName());
        JLabel dishPrice = new JLabel(String.valueOf(dish.getPrice()));
        JButton removeButton = new JButton("X");
        removeButton.addActionListener(e -> {
            window.getDishOrderPanel().getController().removeDish(table, dish);
            refreshOrderDishesPanel();
        });
        constraints.gridx = 0;
        dishOrderPanel.getOrderDishesPanel().add(dishName, constraints);
        constraints.gridx++;
        dishOrderPanel.getOrderDishesPanel().add(dishPrice, constraints);
        constraints.gridx++;
        dishOrderPanel.getOrderDishesPanel().add(removeButton, constraints);
        constraints.gridy++;
    }

    public void removeDishes(Table table){
        tableToDishesMap.remove(table);
        tableToDishesMap.put(table, new ArrayList<>());
    }

    private void removeDish(Table table, Dish dish){
        List<Dish> dishes = tableToDishesMap.get(table);
        dishes.remove(dish);
    }

    private void refreshOrderDishesPanel(){
        JPanel orderDishesPanel = window.getDishOrderPanel().getOrderDishesPanel();
        DishPanel dishPanel = window.getDishPanel();
        Table table = window.getCurrentTableArea().getLastSelectedTable();
        List<Dish> dishes = window.getDishOrderPanel().getController().getDishesFromTable(table);
        orderDishesPanel.removeAll();
        dishes.forEach((d) -> {
            window.getDishOrderPanel().getController().addDishToDisplay(table, d);
        });
        dishPanel.updateUI();
    }

    public List<Dish> getDishesFromTable(Table table){
        return tableToDishesMap.get(table);
    }
}
