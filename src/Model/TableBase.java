package Model;

import javafx.scene.control.Tab;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by y50-70 on 15.09.2017.
 */
public class TableBase {
    private List<Table> tables;

    public TableBase(){
        tables = new ArrayList<Table>();
    }

    public int size(){
        return tables.size();
    }

    public Table getTable(int index){
        return tables.get(index);
    }

    public void addTable(Table table){
        tables.add(table);
    }

    public void removeTable(Table table){
        tables.remove(table);
    }
}
