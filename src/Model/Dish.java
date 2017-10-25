package Model;

import javax.swing.*;
import java.awt.*;

/**
 * Created by y50-70 on 02.09.2017.
 */
public class Dish extends JPanel {
    private JLabel dishName;
    private JLabel price;
//    private boolean isSelected;


    static final Font TAB_AND_BUTTON_FONT = new Font("Console", Font.BOLD, 20);

    public Dish(String dishName) {
        this.dishName = new JLabel(dishName);
        setLayout(new BorderLayout());
        setVisible(true);
        setSize(640, 280);
    }

    public String getDishName() {
        return dishName.getText();
    }

    public Dish(String dishName, int price) {
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

//    public boolean isSelected() {
//        return isSelected;
//    }
//
//    public void setSelected(boolean selected) {
//        isSelected = selected;
//    }

    public void setPrice(int price) {
        try{
        this.price.setText(String.valueOf(price));
            System.out.println("Успешный конверт");
        }
        catch (NumberFormatException ignored){
            System.out.println("Ощибки конверта");
        }
    }


    public void setDishName(String dishName) {
        this.dishName.setText(dishName);
    }

    public int getPrice() {
        return Integer.valueOf(price.getText());
    }
}
