package Model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by y50-70 on 02.09.2017.
 */
public class DishBase {
    private List<Dish> dishes;

    public DishBase() {
        dishes = new ArrayList<Dish>();
    }

    public void addDish(Dish dish) {
        dishes.add(dish);
    }

    public void removeDish(String dishTitle) {
//        for (int i = dishes.size() - 1; i>=0; i--){
//            if(dishes.get(i).getDishName().equals(dishTitle)){
//                dishes.remove(i);
//            }
//        }

        Iterator<Dish> dishIterator = dishes.iterator();
        while (dishIterator.hasNext()) {
            if (dishIterator.next().getDishName().equals(dishTitle)) {
                dishIterator.remove();
            }
        }
    }
}
