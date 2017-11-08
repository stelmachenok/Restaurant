package Controller;

import Model.Dish;
import View.DishEditPanel;
import View.DishPanel;
import View.Window;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by y50-70 on 12.10.2017.
 */
public class DishEditController {
    private DishEditPanel dishEditPanel;
    private Window window;
    private Dish selectedDish;

    public DishEditController(Window window, DishEditPanel dishEditPanel) {
        this.window = window;
        this.dishEditPanel = dishEditPanel;
    }

    public void addType() {
        JTabbedPane dishTabbedPane = window.getDishPanel().getTabbedPane();
        JPanel dishPanel = new JPanel(new GridBagLayout());
        dishPanel.setVisible(true);
        dishPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                setSelectedDish(null);
                refreshEditPaneFields();
            }
        });
        dishTabbedPane.addTab("Новый тип", dishPanel);
        refreshEditPaneFields();
    }

    public void changeTypeName() {
        JTabbedPane dishTabbedPane = window.getDishPanel().getTabbedPane();
        if (dishTabbedPane.getTabCount() > 0) {
            String newTitle = dishEditPanel.getTypeTextField().getText();
            dishTabbedPane.setTitleAt(dishTabbedPane.getSelectedIndex(), newTitle);
        }
    }

    public void removeType() {
        JTabbedPane dishTabbedPane = window.getDishPanel().getTabbedPane();
        if (dishTabbedPane.getTabCount() > 0) {
            dishTabbedPane.removeTabAt(dishTabbedPane.getSelectedIndex());
        }
        refreshEditPaneFields();
    }

    public void addDish() {
        DishPanel dishPanel = window.getDishPanel();
        JTabbedPane dishTabbedPane = dishPanel.getTabbedPane();
        if (dishTabbedPane.getTabCount() > 0) {
            DishPanelController dishPanelController = dishPanel.getController();
            dishPanelController.addDish();
        }
    }

    public void changeDishName() {
        if (selectedDish != null) {
            selectedDish.setDishName(dishEditPanel.getDishNameTextField().getText());
            window.getDishOrderPanel().getController().refreshOrderNames();
        }
    }

    public void changeDishPrice() {
        if (selectedDish != null) {
            try {
                selectedDish.setPrice(Integer.valueOf(dishEditPanel.getDishPriceTextField().getText()));
                window.getDishOrderPanel().getController().refreshOrderNames();
            } catch (NumberFormatException ignored) {
            }
        }
    }

    public void removeDish() {
        DishPanel dishPanel = window.getDishPanel();
        if (selectedDish != null) {
            dishPanel.getController().removeDish(selectedDish);
        }
    }

    public void refreshEditPaneFields() {
        JTabbedPane dishTabbedPane = window.getDishPanel().getTabbedPane();
        if (dishTabbedPane.getTabCount() > 0) {
            dishEditPanel.getTypeTextField().setText(dishTabbedPane.getTitleAt(dishTabbedPane.getSelectedIndex()));
        } else {
            dishEditPanel.getTypeTextField().setText("");
        }

        if (selectedDish != null) {
            dishEditPanel.getDishNameTextField().setText(selectedDish.getDishName());
            dishEditPanel.getDishPriceTextField().setText(String.valueOf(selectedDish.getPrice()));
        } else {
            dishEditPanel.getDishNameTextField().setText("");
            dishEditPanel.getDishPriceTextField().setText("");
        }

    }

    public void setSelectedDish(Dish dish) {
        selectedDish = dish;
    }
}