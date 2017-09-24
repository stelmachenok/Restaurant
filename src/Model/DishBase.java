package Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by y50-70 on 02.09.2017.
 */
public class DishBase {
    List<Dish> dishes;

    public DishBase(){
        dishes = new ArrayList<Dish>();
    }

    public void addDish(Dish dish){
        dishes.add(dish);
    }

    public void removeDish(String dishTitle){
        for (int i = dishes.size() - 1; i>=0; i--){
            if(dishes.get(i).getTitle().equals(dishTitle)){
                dishes.remove(i);
            }
        }
    }

}
