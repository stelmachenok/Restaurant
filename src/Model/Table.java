package Model;

/**
 * Created by y50-70 on 02.09.2017.
 */
public class Table {
    private DishBase dishes;
    private String tableName;
    private int xCoord;
    private int yCoord;
    private boolean isSelect = false;
    public static final int WIDTH = 50;
    public static final int LENGTH = 50;

    public Table(){
        dishes = new DishBase();
    }

    public Table(int xCoord, int yCoord){
        this.xCoord = xCoord;
        this.yCoord = yCoord;
    }

    public Table(Table table){
        dishes = table.getDishes();
    }

    public int getxCoord() {
        return xCoord;
    }

    public int getyCoord() {
        return yCoord;
    }

    public String getTableName() {
        return tableName;
    }

    public boolean isSelected(){
        return isSelect;
    }

    public void setSelected(boolean state){
        isSelect = state;
    }

    public void setxCoord(int xCoord) {
        this.xCoord = xCoord;
    }

    public void setyCoord(int yCoord) {
        this.yCoord = yCoord;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public DishBase getDishes() {
        return dishes;
    }
}
