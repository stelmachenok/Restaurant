package Controller;

import Model.Table;
import Model.TableBase;
import View.DishPanel;
import View.TableArea;
import View.Window;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * Created by y50-70 on 15.09.2017.
 */
public class Controller {
    private Window window;

    public Controller(Window window){
        this.window = window;
    }

//    public void addTableArea(){
//        JTabbedPane tabbedPane = window.getTabbedPane();
//        TableArea tableArea = new TableArea(window);
//        tabbedPane.addTab("New Hall", tableArea);
//    }

//    public void addTable(int xCoord, int yCoord){
//        Table table = new Table(xCoord, yCoord);
//        TableArea tableArea = window.getCurrentTableArea();
//        TableBase tableBase = tableArea.getTables();
//        table.setTableName("Стол " + (tableBase.size() + 1));
//        tableBase.addTable(table);
//        repaintTableArea(tableArea);
//    }

    public void addTableWithMouse(MouseEvent e){
        int xCoord = e.getX();
        int yCoord = e.getY();
        Table table = new Table(xCoord, yCoord);
        TableArea tableArea = window.getCurrentTableArea();
        TableBase tableBase = tableArea.getTables();
        table.setTableName("Стол " + (tableBase.size() + 1));
        tableBase.addTable(table);
        tableArea.repaint();
    }

//    public void removeTable(){
//        TableArea tableArea = window.getCurrentTableArea();
//        Table table  = tableArea.getSelectedTable();
//        if (table != null){
//            tableArea.getTables().removeTable(table);
//        }
//        repaintTableArea(tableArea);
//    }

    private Table getSelectedTable(int xCoord, int yCoord){
        TableArea tableArea = window.getCurrentTableArea();
        TableBase tableBase = tableArea.getTables();
        int tableBaseSize = tableBase.size();
        double minDistance = 2000;
        Table selectedTable = null;
        for (int i = 0; i<tableBaseSize; i++){
            Table currentTable = tableBase.getTable(i);
            double distance = Math.pow(Math.pow(currentTable.getxCoord() - xCoord, 2) + Math.pow(currentTable.getyCoord() - yCoord, 2),0.5);
            currentTable.setSelected(false);
            if (distance < minDistance && distance < Table.WIDTH){
                minDistance = distance;
                selectedTable = currentTable;
            }
        }
        return selectedTable;
    }

    public void selectTableOnEditMode(MouseEvent e){
        int xCoord = e.getX();
        int yCoord = e.getY();
        TableArea tableArea = window.getCurrentTableArea();
        TableBase tableBase = tableArea.getTables();
        Table selectedTable = getSelectedTable(xCoord, yCoord);
        if (selectedTable != null){
            selectedTable.setSelected(true);
            tableBase.removeTable(selectedTable);
            tableBase.addTable(selectedTable);
            window.getTableNameTextField().setText(selectedTable.getTableName());
        }
        else{
            window.getTableNameTextField().setText("");
        }
        tableArea.repaint();
    }

    public void selectTableOnSimpleMode(MouseEvent e){
        int xCoord = e.getX();
        int yCoord = e.getY();
        Table selectedTable = getSelectedTable(xCoord, yCoord);
        if (selectedTable != null){
            DishPanel dishPanel = window.getDishPanel();
            JPanel panel = window.getPanel();
            window.getFrame().remove(panel);
            window.getFrame().add(dishPanel);
            dishPanel.updateUI();
        }
    }

//    public GridBagConstraints initGridBagConstraints(int gridheight, int gridwidth){
//        GridBagConstraints c = new GridBagConstraints();
//        c.anchor = GridBagConstraints.CENTER;
//        c.fill   = GridBagConstraints.CENTER;
//        c.gridheight = gridheight;
//        c.gridwidth  = gridheight;
//        c.gridx = 0;
//        c.gridy = 0;
//        c.insets = new Insets(10, 0, 0, 0);
//        c.ipadx = 1;
//        c.ipady = 1;
//        c.weightx = 0.0;
//        c.weighty = 0.0;
//        return c;
//    }

//    public void backToSimpleMode(){
//        JPanel panel = window.getPanel();
//        DishPanel dishPanel = window.getDishPanel();
//        window.getFrame().remove(dishPanel);
//        window.getFrame().add(panel);
//        panel.updateUI();
//    }

    public void moveTable(MouseEvent e){
        int xCoord = e.getX();
        int yCoord = e.getY();
        TableArea tableArea = window.getCurrentTableArea();
        TableBase tableBase = tableArea.getTables();
        int tableBaseSize = tableBase.size();
        for (int i = 0; i<tableBaseSize; i++){
            Table currentTable = tableBase.getTable(i);
            if (currentTable.isSelected()){
                currentTable.setxCoord(xCoord);
                currentTable.setyCoord(yCoord);
            }
        }
        tableArea.repaint();
    }

//    private void repaintTableArea(TableArea tableArea){
//        tableArea.repaint();
//    }
}
