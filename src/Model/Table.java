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


//    private int xCoord;
//    private int yCoord;
//    private boolean isSelect = false;
//    private boolean isOrderDone = false;

//    private DishBase dishes;

//    public Table(){
//        dishes = new DishBase();
//    }

//    public Table(int xCoord, int yCoord){
//        this.xCoord = xCoord;
//        this.yCoord = yCoord;
//    }
//
//    public Table(Table table){
//        dishes = table.getDishes();
//    }

//    public int getxCoord() {
//        return xCoord;
//    }

//    public int getyCoord() {
//        return yCoord;
//    }


//    public boolean isSelected(){
//        return isSelect;
//    }

//    public void setSelected(boolean state){
//        isSelect = state;
//    }

//    public boolean isOrderDone() {
//        return isOrderDone;
//    }
//
//    public void setOrderDone(boolean orderDone) {
//        isOrderDone = orderDone;
//    }

//    public void setxCoord(int xCoord) {
//        this.xCoord = xCoord;
//    }
//
//    public void setyCoord(int yCoord) {
//        this.yCoord = yCoord;
//    }


//    public DishBase getDishes() {
//        return dishes;
//    }
