package Model;

/**
 * Created by y50-70 on 02.09.2017.
 */
public class Dish {
    String title;
    int price;

    public Dish(){
    }

    public Dish(String title, int price){
        this.title = title;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public int getPrice() {
        return price;
    }
}
