package Controller;

import Model.Point;
import Model.Table;
import View.TableArea;
import View.Window;

import javax.swing.*;
import java.util.List;

/**
 * Created by y50-70 on 12.10.2017.
 */
public class TableEditController {
    private Window window;
    private JTabbedPane tableTabbedPane;

    public TableEditController(Window window, JTabbedPane tableTabbedPane) {
        this.tableTabbedPane = tableTabbedPane;
        this.window = window;
    }

    public void addTable(int xCoord, int yCoord) {
        Table table = new Table();
        Point point = new Point(xCoord, yCoord);
        TableArea tableArea = window.getCurrentTableArea();
        window.getController().addTableToMap(tableArea, table);
        window.getController().setTableCoord(table, point);
        window.getDishOrderPanel().getController().addTableToMap(table);
        window.getController().setOrderDone(table, false);
        int listTablesSize = window.getController().getTablesFromTab(tableArea).size();
        table.setTableName("Стол " + listTablesSize);
        tableArea.repaint();
    }

    public void addTableArea() {
        JTabbedPane tabbedPane = window.getTabbedPane();
        TableArea tableArea = new TableArea(window);
        tabbedPane.addTab("New Hall", tableArea);
    }

    public void removeTable() {
        TableArea tableArea = window.getCurrentTableArea();

        Table table = window.getController().getSelectedTable();
        if (table != null) {
            window.getController().removeFromMap(tableArea, table);
        }
        tableArea.repaint();
    }
}
