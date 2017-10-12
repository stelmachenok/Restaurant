package Controller;

import Model.Table;
import Model.TableBase;
import View.TableArea;
import View.Window;

import javax.swing.*;

/**
 * Created by y50-70 on 12.10.2017.
 */
public class TableEditController {
    private Window window;
    private JTabbedPane tableTabbedPane;

    public TableEditController(Window window, JTabbedPane tableTabbedPane){
        this.tableTabbedPane = tableTabbedPane;
        this.window = window;
    }

    public void addTable(int xCoord, int yCoord){
        Table table = new Table(xCoord, yCoord);
        TableArea tableArea = window.getCurrentTableArea();
        TableBase tableBase = tableArea.getTables();
        table.setTableName("Стол " + (tableBase.size() + 1));
        tableBase.addTable(table);
        tableArea.repaint();
    }

    public void addTableArea(){
        JTabbedPane tabbedPane = window.getTabbedPane();
        TableArea tableArea = new TableArea(window);
        tabbedPane.addTab("New Hall", tableArea);
    }

    public void removeTable(){
        TableArea tableArea = window.getCurrentTableArea();
        Table table  = tableArea.getSelectedTable();
        if (table != null){
            tableArea.getTables().removeTable(table);
        }
        tableArea.repaint();
    }
}
