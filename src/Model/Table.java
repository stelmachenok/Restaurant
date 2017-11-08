package Model;

import java.util.UUID;

/**
 * Created by y50-70 on 02.09.2017.
 */
public class Table {
    private String tableName;
    private UUID id;
    public static final int WIDTH = 50;
    public static final int LENGTH = 50;

    public Table() {
        id = UUID.randomUUID();
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Table)) return false;

        Table table = (Table) o;

        return id.equals(table.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

}
