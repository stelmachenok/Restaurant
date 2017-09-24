package Model;

import java.awt.geom.RoundRectangle2D;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by y50-70 on 02.09.2017.
 */
public class Table {
    DishBase dishes;
    int xCoord;
    int yCoord;
    boolean isSelect = false;
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

    public DishBase getDishes() {
        return dishes;
    }
}
