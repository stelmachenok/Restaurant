package View;

import Model.Table;
import Model.Point;
import Controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;

/**
 * Created by y50-70 on 15.09.2017.
 */
public class TableArea extends JComponent {
    private Window window;
    private Controller controller;
    private UUID id;

    //static final private Color CLOSE_BUTTON_COLOR = new Color(228, 96, 96);
    private static final Color CHAIRS_COLOR = Color.WHITE;
    private static final Color ORDER_DONE_COLOR = new Color(0xFF464A);
    private static final Color SHADOW_COLOR = Color.GRAY;
    private static final Color TABLES_COLOR = new Color(0x37B776);
    private static final Color SELECTED_COLOR = new Color(0x00FF28);

    public TableArea(Window window){
        id = UUID.randomUUID();
        this.window = window;
        this.controller = this.window.getController();
        controller.addTableAreaToMap(this);
        addTableAreaListeners();
    }

    @Override
    protected void paintComponent(Graphics g){
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(0,0,2000,2000);
        List<Table> tables = controller.getTablesFromTab(this);
        tables.forEach((t)->{
            drawChairs(g, t);
            drawTable(g,t);
        });
    }

    private void drawChairs(Graphics g, Table table){
        Point point = controller.getTableCoord(table);
        int xCoord = point.getX();
        int yCoord = point.getY();
        g.setColor(SHADOW_COLOR);
        g.fillOval(xCoord - Table.WIDTH * 2, yCoord - Table.LENGTH * 2, Table.WIDTH, Table.LENGTH);
        g.fillOval(xCoord + Table.WIDTH, yCoord - Table.LENGTH * 2, Table.WIDTH, Table.LENGTH);
        g.fillOval(xCoord + Table.WIDTH, yCoord + Table.LENGTH, Table.WIDTH, Table.LENGTH);
        g.fillOval(xCoord - Table.WIDTH * 2, yCoord + Table.LENGTH, Table.WIDTH, Table.LENGTH);
        g.setColor(CHAIRS_COLOR);
        g.fillOval(xCoord - Table.WIDTH * 2 + 5, yCoord - Table.LENGTH * 2 + 5, Table.WIDTH, Table.LENGTH);
        g.fillOval(xCoord + Table.WIDTH + 5, yCoord - Table.LENGTH * 2 + 5, Table.WIDTH, Table.LENGTH);
        g.fillOval(xCoord + Table.WIDTH + 5, yCoord + Table.LENGTH + 5, Table.WIDTH, Table.LENGTH);
        g.fillOval(xCoord - Table.WIDTH * 2 + 5, yCoord + Table.LENGTH + 5, Table.WIDTH, Table.LENGTH);
    }

    private void drawTable(Graphics g, Table table){
        Point point = controller.getTableCoord(table);
        int xCoord = point.getX();
        int yCoord = point.getY();
        g.setColor(SHADOW_COLOR);
        g.fillRoundRect(xCoord - Table.WIDTH - 4, yCoord - Table.LENGTH - 4, Table.WIDTH * 2, Table.LENGTH*2, 15, 15);
        if (table.equals(controller.getSelectedTable())) {
            g.setColor(SELECTED_COLOR);
        }
        else{
            if (controller.isOrderDone(table)) {
                g.setColor(ORDER_DONE_COLOR);
            }
            else{
                g.setColor(TABLES_COLOR);
            }
        }
        g.fillRoundRect(xCoord - Table.WIDTH, yCoord - Table.LENGTH, Table.WIDTH * 2, Table.LENGTH*2, 15, 15);
        g.setColor(Color.BLACK);
        g.drawString(table.getTableName(), xCoord - Table.WIDTH, yCoord - Table.LENGTH + 20);
    }

    public Table getSelectedTable(){
        return controller.getSelectedTable();
    }

    private void addTableAreaListeners(){
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            if (window.isEditModeOn()) {
                if (e.getClickCount() == 2) {
                    controller.addTableWithMouse(e);
                } else {
                    controller.selectTableOnEditMode(e);
                }
            }
            else{
                controller.selectTableOnSimpleMode(e);
            }
            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (window.isEditModeOn()) {
                    controller.moveTable(e);
                }
            }
        });
    }

    public Table getLastSelectedTable(){
        return controller.getLastSelectedTable();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TableArea)) return false;

        TableArea tableArea = (TableArea) o;

        return id.equals(tableArea.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}


//    public Table getSelectedTable(){
//        Table selectedTable = null;
//        int size = tables.size();
//        for(int i = 0; i < size; i++) {
//            Table table = tables.getTable(i);
//            if (table.equals(controller.getSelectedTable())) {
//                selectedTable = tables.getTable(i);
//            }
//        }
//        return selectedTable;
//    }