package View;

import Model.Table;
import Model.TableBase;
import Controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Created by y50-70 on 15.09.2017.
 */
public class TableArea extends JComponent {
    private Window window;
    private Controller controller;
    private TableBase tables;

    //static final private Color CLOSE_BUTTON_COLOR = new Color(228, 96, 96);
    static final private Color CHAIRS_COLOR = Color.WHITE;
    static final private Color SHADOW_COLOR = Color.GRAY;
    static final private Color TABLES_COLOR = new Color(0x37B776);
    static final private Color SELECTED_COLOR = new Color(0x00FF28);

    public TableArea(Window window){
        this.window = window;
        this.controller = this.window.getController();
        tables = new TableBase();
        addTableAreaListeners();
    }

    @Override
    protected void paintComponent(Graphics g){
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(0,0,2000,2000);
        int size = tables.size();
        for(int i = 0; i < size; i++){
            Table table = tables.getTable(i);
            drawChairs(g, table);
            drawTable(g,table);
        }
    }

    private void drawChairs(Graphics g, Table table){
        int xCoord = table.getxCoord();
        int yCoord = table.getyCoord();
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
        int xCoord = table.getxCoord();
        int yCoord = table.getyCoord();
        g.setColor(SHADOW_COLOR);
        g.fillRoundRect(xCoord - Table.WIDTH - 4, yCoord - Table.LENGTH - 4, Table.WIDTH * 2, Table.LENGTH*2, 15, 15);
        if (table.isSelected()) {
            g.setColor(SELECTED_COLOR);
        }
        else{
            g.setColor(TABLES_COLOR);
        }
        g.fillRoundRect(xCoord - Table.WIDTH, yCoord - Table.LENGTH, Table.WIDTH * 2, Table.LENGTH*2, 15, 15);
        g.setColor(Color.BLACK);
        g.drawString(table.getTableName(), xCoord - Table.WIDTH, yCoord - Table.LENGTH + 20);
    }

    public Table getSelectedTable(){
        Table selectedTable = null;
        int size = tables.size();
        for(int i = 0; i < size; i++) {
            Table table = tables.getTable(i);
            if (table.isSelected()) {
                selectedTable = tables.getTable(i);
            }
        }
        return selectedTable;
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

    public TableBase getTables() {
        return tables;
    }

}


