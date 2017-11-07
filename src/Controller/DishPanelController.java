package Controller;

import Model.Dish;
import Model.Table;
import View.DishOrderPanel;
import View.DishPanel;
import View.Window;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by y50-70 on 13.10.2017.
 */
public class DishPanelController {
    private DishPanel dishPanel;
    private Window window;
    private List<Dish> dishes;
    private GridBagConstraints constraints;

    public DishPanelController(DishPanel dishPanel, Window window) {
        this.dishPanel = dishPanel;
        this.window = window;
    }

    public void backToSimpleMode() {
        JPanel panel = window.getPanel();
        DishPanel dishPanel = window.getDishPanel();
        window.getFrame().remove(dishPanel);
        window.getFrame().add(panel);
        panel.updateUI();
    }


    //todo перенети во view, сделать заполнение через xml
    public void fillingDishList() {
        JTabbedPane dishTabbedPane = dishPanel.getTabbedPane();

        dishes = new ArrayList<>();
        Dish dish = new Dish("Espresso", 15);
        Dish dish1 = new Dish("Capuccino", 15);
        Dish dish2 = new Dish("Macaccino", 15);
        Dish dish3 = new Dish("Italiano", 15);
        dishes.add(dish);
        dish.setVisible(true);
        dishes.add(dish1);
        dish1.setVisible(true);
        dishes.add(dish2);
        dish2.setVisible(true);
        dishes.add(dish3);
        dish3.setVisible(true);

        JPanel hotPanel = new JPanel(new GridBagLayout());
        hotPanel.setVisible(true);
        hotPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                DishEditController dishEditController = window.getDishEditPanel().getController();
                dishEditController.setSelectedDish(null);
                dishEditController.refreshEditPaneFields();

            }
        });

        createGridBagConstraints();

        dishTabbedPane.add("Горячие Напитки", hotPanel);

        for (Dish currentDish : dishes) {
            hotPanel.add(currentDish, constraints);
            currentDish.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    addDishListener(currentDish);
                }
            });
        }

        dishTabbedPane.add("Еда", new JPanel(new GridBagLayout()));
    }

    void addDish() {
        Dish dish = new Dish("Новое блюдо", 25);
        dishes.add(dish);
        dish.setVisible(true);
        JTabbedPane tabbedPane = dishPanel.getTabbedPane();
        if (tabbedPane.getTabCount() > 0) {
            JTabbedPane typeTabbedPane = (JTabbedPane) tabbedPane.getComponentAt(tabbedPane.getSelectedIndex());
            if (typeTabbedPane.getTabCount() > 0) {
                JPanel dishTypePanel = (JPanel) typeTabbedPane.getComponentAt(typeTabbedPane.getSelectedIndex());
                dishTypePanel.add(dish, constraints);
                dish.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        DishEditController dishEditController = window.getDishEditPanel().getController();
                        dishEditController.setSelectedDish(dish);
                        dishEditController.refreshEditPaneFields();
                    }
                });
                dishPanel.updateUI();
            }
        }
    }

    private void createGridBagConstraints() {
        constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.NORTH;
        constraints.fill = GridBagConstraints.PAGE_START;
        constraints.gridheight = 1;
        constraints.gridwidth = 1;
        constraints.gridx = GridBagConstraints.RELATIVE;
        constraints.gridy = 0;
        constraints.insets = new Insets(10, 5, 0, 5);
        constraints.ipadx = 1;
        constraints.ipady = 1;
        constraints.weightx = 100;
        constraints.weighty = 1;
    }

    private void addDishListener(Dish dish) {
        DishEditController dishEditController = window.getDishEditPanel().getController();
        dishEditController.setSelectedDish(dish);
        dishEditController.refreshEditPaneFields();
        Table lastSelectedTable = window.getController().getLastSelectedTable();
        DishOrderPanel dishOrderPanel = window.getDishOrderPanel();
        dishOrderPanel.getController().addDishToTable(lastSelectedTable, dish);
        List<Dish> dishes = dishOrderPanel.getController().getDishesFromTable(lastSelectedTable);
        window.getDishOrderPanel().getOrderDishesPanel().removeAll();
        dishes.forEach((d) -> {
            window.getDishOrderPanel().getController().addDishToDisplay(lastSelectedTable, d);
        });

        dishPanel.updateUI();
    }
}
