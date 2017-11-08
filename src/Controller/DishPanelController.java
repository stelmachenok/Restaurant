package Controller;

import Model.Dish;
import Model.Table;
import View.DishEditPanel;
import View.DishOrderPanel;
import View.DishPanel;
import View.Window;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by y50-70 on 13.10.2017.
 */
public class DishPanelController {
    private DishPanel dishPanel;
    private Window window;
    private List<Dish> dishes;
    private GridBagConstraints constraints;
    private boolean isEditModeOn;

    public DishPanelController(DishPanel dishPanel, Window window) {
        this.dishPanel = dishPanel;
        this.window = window;
    }

    public void backToTablePanel() {
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
            JPanel dishesPanel = (JPanel) tabbedPane.getComponentAt(tabbedPane.getSelectedIndex());
            dishesPanel.add(dish, constraints);
            dish.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    DishEditController dishEditController = window.getDishEditPanel().getController();
                    dishEditController.setSelectedDish(dish);
                    dishEditController.refreshEditPaneFields();
                    addDishListener(dish);
                }
            });
            this.dishPanel.updateUI();
        }
    }

    void removeDish(Dish dish) {
        JTabbedPane tabbedPane = dishPanel.getTabbedPane();
        if (tabbedPane.getTabCount() > 0) {
            JPanel dishesPanel = (JPanel) tabbedPane.getComponentAt(tabbedPane.getSelectedIndex());
            List<Component> components = Arrays.asList(dishesPanel.getComponents());
            components.forEach((c)->{
                if (dish.equals(c)){
                    dishesPanel.remove(c);
                }
            });
        }
        dishes.remove(dish);
        dishPanel.updateUI();
    }

    public void setEditMode() {
        DishPanel dishPanel = window.getDishPanel();
        JTabbedPane tabbedPane = dishPanel.getTabbedPane();
        DishEditPanel dishEditPanel = window.getDishEditPanel();
        JToolBar toolBar = dishPanel.getToolBar();
        JButton readyButton = dishPanel.getReadyButton();
        DishOrderPanel dishOrderPanel = window.getDishOrderPanel();

        toolBar.removeAll();
        toolBar.add(readyButton);
        dishPanel.remove(dishOrderPanel);
        dishPanel.add(dishEditPanel, BorderLayout.EAST);
        if (tabbedPane.getTabCount() > 0) {
            String tabHeader = tabbedPane.getTitleAt(tabbedPane.getSelectedIndex());
            dishEditPanel.getTypeTextField().setText(tabHeader);
        } else {
            dishEditPanel.getTypeTextField().setText("");
        }
        setEditModeOn(true);
        dishPanel.updateUI();
    }

    public void setSimpleMode() {
        DishPanel dishPanel = window.getDishPanel();
        DishEditPanel dishEditPanel = window.getDishEditPanel();
        JToolBar toolBar = dishPanel.getToolBar();
        DishOrderPanel dishOrderPanel = window.getDishOrderPanel();
        JButton closeButton = dishPanel.getCloseButton();
        JButton editButton = dishPanel.getEditButton();

        toolBar.removeAll();
        toolBar.add(editButton);
        toolBar.add(closeButton);
        dishPanel.remove(dishEditPanel);
        dishPanel.add(dishOrderPanel, BorderLayout.EAST);
        setEditModeOn(false);
        dishPanel.updateUI();
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
        if (!isEditModeOn()) {
            Table lastSelectedTable = window.getController().getLastSelectedTable();
            DishOrderPanel dishOrderPanel = window.getDishOrderPanel();
            dishOrderPanel.getController().addDishToTable(lastSelectedTable, dish);

            DishOrderController dishOrderController = window.getDishOrderPanel().getController();
            dishOrderController.refreshOrderNames();
        }

        dishPanel.updateUI();
    }

    private boolean isEditModeOn() {
        return isEditModeOn;
    }

    private void setEditModeOn(boolean editModeOn) {
        isEditModeOn = editModeOn;
    }
}
