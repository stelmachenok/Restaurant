package Controller;

import Model.Dish;
import Model.Table;
import View.DishOrderPanel;
import View.DishPanel;
import View.TableArea;
import View.Window;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.*;
import java.util.List;

import Model.Point;

/**
 * Created by y50-70 on 15.09.2017.
 */
public class Controller {
    private Window window;
    private Table lastSelectedTable;
    private Table selectedTable;
    private Map<TableArea, List<Table>> tabToTablesMap;
    private Map<Table, Point> tableCord;
    private Map<Table, Boolean> isOrderDone;

    public Controller(Window window) {
        this.window = window;
        tabToTablesMap = new HashMap<>();
        tableCord = new HashMap<>();
        isOrderDone = new HashMap<>();
    }

    public void addTableWithMouse(MouseEvent e) {
        Point point = new Point(e.getX(), e.getY());
        Table table = new Table();
        tableCord.put(table, point);
        window.getDishOrderPanel().getController().addTableToMap(table);
        isOrderDone.put(table, false);
        TableArea tableArea = window.getCurrentTableArea();
        List<Table> tables = tabToTablesMap.get(tableArea);
        if (tables == null) {
            tables = new ArrayList<>();
            tables.add(table);
        } else {
            tables.add(table);
        }
        if (tabToTablesMap.isEmpty()) {
            table.setTableName("Стол 1");
        } else {
            table.setTableName("Стол " + (tabToTablesMap.get(tableArea).size()));
        }
        tableArea.repaint();
    }

    public Table getSelectedTable() {
        return selectedTable;
    }

    private Table getSelectedTable(int xCoord, int yCoord) {
        TableArea tableArea = window.getCurrentTableArea();
        List<Table> tables = tabToTablesMap.get(tableArea);
        if (tables == null) {
            selectedTable = null;
            return null;
        }
        selectedTable = null;
        if (!tableCord.isEmpty() && !tables.isEmpty()) {
            int firstX = tableCord.get(tables.get(0)).getX();
            int firstY = tableCord.get(tables.get(0)).getY();

            double minDistance = Math.hypot(firstX - xCoord, firstY - yCoord);
            if (minDistance < Table.WIDTH) {
                selectedTable = tables.get(0);
            }

            for (Table table : tables) {
                int x = tableCord.get(table).getX();
                int y = tableCord.get(table).getY();
                double distance = Math.hypot(x - xCoord, y - yCoord);
                if (distance < minDistance && distance < Table.WIDTH) {
                    minDistance = distance;
                    selectedTable = table;
                }
            }
        }
        return selectedTable;
    }

    public void selectTableOnEditMode(MouseEvent e) {
        int xCoord = e.getX();
        int yCoord = e.getY();
        TableArea tableArea = window.getCurrentTableArea();
        selectedTable = getSelectedTable(xCoord, yCoord);
        JTextField tableNameTextField = window.getTableEditPanel().getTableNameTextField();
        if (selectedTable != null) {
            tableNameTextField.setText(selectedTable.getTableName());
        } else {
            tableNameTextField.setText("");
        }
        tableArea.repaint();
    }

    public void selectTableOnSimpleMode(MouseEvent e) {
        int xCoord = e.getX();
        int yCoord = e.getY();
        Table selectedTable = getSelectedTable(xCoord, yCoord);
        this.selectedTable = null;
        if (selectedTable != null) {
            lastSelectedTable = selectedTable;
            DishPanel dishPanel = window.getDishPanel();
            DishOrderPanel dishOrderPanel = window.getDishOrderPanel();
            JPanel panel = window.getPanel();
            window.getFrame().remove(panel);
            window.getFrame().add(dishPanel);
            dishPanel.add(dishOrderPanel, BorderLayout.EAST);
            window.getDishOrderPanel().getController().refreshSelectedTableLabel();
            JPanel orderDishesPanel = window.getDishOrderPanel().getOrderDishesPanel();
            GridBagConstraints constraints = window.getDishOrderPanel().getOrderСonstraints();
            constraints.gridy = 0;
            orderDishesPanel.removeAll();
            List<Dish> dishes = window.getDishOrderPanel().getController().getDishesFromTable(lastSelectedTable);
            dishes.forEach((d) -> {
                window.getDishOrderPanel().getController().addDishToDisplay(lastSelectedTable, d);
            });
            dishPanel.updateUI();
        }
    }

    public void toDishMode() {

    }

    public void changeTab() {
        JTabbedPane tabbedPane = window.getTabbedPane();
        JTextField hallNameTextField = window.getTableEditPanel().getHallNameTextField();
        if (tabbedPane.getTabCount() > 0) {
            hallNameTextField.setText(tabbedPane.getTitleAt(tabbedPane.getSelectedIndex()));
        } else {
            hallNameTextField.setText("");
        }
    }

    public Table getLastSelectedTable() {
        return lastSelectedTable;
    }

    public void moveTable(MouseEvent e) {
        int xCoord = e.getX();
        int yCoord = e.getY();
        TableArea tableArea = window.getCurrentTableArea();
        tableCord.remove(selectedTable);
        Point point = new Point(xCoord, yCoord);
        tableCord.put(selectedTable, point);
        tableArea.repaint();
    }

    public void addTableToMap(TableArea tab, Table table) {
        tabToTablesMap.get(tab).add(table);
    }

    public void addTableAreaToMap(TableArea tableArea) {
        tabToTablesMap.put(tableArea, new ArrayList<>());
    }

    public void removeFromMap(TableArea tab, Table table) {
        List<Table> tables = tabToTablesMap.get(tab);
        tables.remove(table);

    }

    public List<Table> getTablesFromTab(TableArea tab) {
        return tabToTablesMap.get(tab);
    }

    public Point getTableCoord(Table table) {
        return tableCord.get(table);
    }

    public void setTableCoord(Table table, Point point) {
        tableCord.remove(table);
        tableCord.put(table, point);
    }

    public void setOrderDone(Table table, boolean isDone) {
        isOrderDone.put(table, isDone);
    }

    public boolean isOrderDone(Table table) {
        return isOrderDone.get(table);
    }
}
