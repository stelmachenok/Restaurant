package Controller;

import Model.Dish;
import Model.Table;
import View.DishOrderPanel;
import View.Window;

import javax.swing.*;
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
//        List<Table> tables = window.getController().getTablesFromTab(window.getCurrentTableArea());
//        tables.forEach((t)->tableToDishesMap.put(t, new ArrayList<>()));
    }

    public void addTableToMap(Table table){
        tableToDishesMap.put(table, new ArrayList<>());
    }

    public void refreshSelectedTableLabel(){
        JLabel selectedTableNameLabel = dishOrderPanel.getSelectedTableNameLabel();
        Table table = window.getCurrentTableArea().getLastSelectedTable();
        selectedTableNameLabel.setText("<html><u>Стол: " + table.getTableName()+ "</u></html>");
    }

    public void addDishToTable(Table table, Dish dish){
        tableToDishesMap.get(table).add(dish);
    }

    public List<Dish> getDishesFromTable(Table table){
        return tableToDishesMap.get(table);
    }

    public void removeDishes(Table table){
        tableToDishesMap.remove(table);
        tableToDishesMap.put(table, new ArrayList<>());
    }
}
