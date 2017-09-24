package Controller;

import Model.Table;
import Model.TableBase;
import View.TableArea;
import View.Window;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.util.List;

/**
 * Created by y50-70 on 15.09.2017.
 */
public class Controller {
    private Window window;


    public Controller(Window window){
        this.window = window;
    }

    public void addTableArea(){
        List<TableArea> tableAreaList = window.getTableAreaList();
        TableArea tableArea = new TableArea(window);
        tableAreaList.add(tableArea);
        JButton tab = tableArea.getTab();
        JToolBar toolBar = window.getToolBar();
        toolBar.add(tab);
        repaintToolBar();
    }

    private void repaintToolBar(){
        JToolBar toolBar = window.getToolBar();
        List<TableArea> tableAreaList = window.getTableAreaList();
        toolBar.removeAll();
        for (TableArea tableArea: tableAreaList) {
            toolBar.add(tableArea.getTab());
            toolBar.add(tableArea.getCloseButton());

        }
        window.createButtonPlus();
        toolBar.repaint();
    }

    public void addTable(MouseEvent e){
        int xCoord = e.getX();
        int yCoord = e.getY();
        Table table = new Table(xCoord, yCoord);
        TableArea tableArea = window.getCurrentTableArea();
        TableBase tableBase = tableArea.getTables();
        tableBase.addTable(table);
        repaintTableArea(tableArea);
    }

    public void selectTable(MouseEvent e){
        int xCoord = e.getX();
        int yCoord = e.getY();
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
        if (selectedTable != null){
            selectedTable.setSelected(true);
            tableBase.removeTable(selectedTable);
            tableBase.addTable(selectedTable);
        }
        repaintTableArea(tableArea);
    }

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
        repaintTableArea(tableArea);
    }

    public void switchTab(TableArea tableArea){
        window.setCurrentTableArea(tableArea);
        window.getPanel().updateUI();
    }

    public void closeTableArea(TableArea tableArea){
        List<TableArea> tableAreaList = window.getTableAreaList();
        TableArea currentTableArea = window.getCurrentTableArea();
        if(tableArea.equals(currentTableArea)){
            TableArea nextTableArea = null;
            for (TableArea tableAreaIterator:tableAreaList) {
                if (!tableAreaIterator.equals(currentTableArea)){
                    nextTableArea = tableAreaIterator;
                }
            }
            switchTab(nextTableArea);
        }
        tableAreaList.remove(tableArea);
        repaintToolBar();
    }

    private void repaintTableArea(TableArea tableArea){
        tableArea.repaint();
    }
}
