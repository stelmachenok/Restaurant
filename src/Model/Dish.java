package Model;

import javax.swing.*;
import java.awt.*;
import java.util.UUID;

/**
 * Created by y50-70 on 02.09.2017.
 */
public class Dish extends JPanel {
    private JLabel dishName;
    private JLabel price;
    private UUID id;


    static final Font TAB_AND_BUTTON_FONT = new Font("Console", Font.BOLD, 20);

    public Dish(String dishName, int price) {
        id = UUID.randomUUID();
        this.dishName = new JLabel(dishName);
        this.price = new JLabel(String.valueOf(price));
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createLineBorder(Color.black));

        add(this.dishName, BorderLayout.CENTER);
        add(this.price, BorderLayout.WEST);

        setVisible(true);
        Dimension dimension = new Dimension(200, 50);
        this.dishName.setFont(TAB_AND_BUTTON_FONT);
        setPreferredSize(dimension);
    }

    public String getDishName() {
        return dishName.getText();
    }

    public int getPrice() {
        return Integer.valueOf(price.getText());
    }

    public void setDishName(String dishName) {
        this.dishName.setText(dishName);
    }

    public void setPrice(int price) {
        try{
            this.price.setText(String.valueOf(price));
            System.out.println("Успешный конверт");
        }
        catch (NumberFormatException ignored){
            System.out.println("Ощибки конверта");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Dish)) return false;

        Dish dish = (Dish) o;

        return id.equals(dish.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
