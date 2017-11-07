package Controller;

import Model.Dish;
import View.DishEditPanel;
import View.DishPanel;
import View.Window;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

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

    public void removeType() {
        JTabbedPane dishTabbedPane = window.getDishPanel().getTabbedPane();
        if (dishTabbedPane.getTabCount() > 0) {
            dishTabbedPane.removeTabAt(dishTabbedPane.getSelectedIndex());
        }
        refreshEditPaneFields();
    }

    public void changeTypeName() {
        JTabbedPane dishTabbedPane = window.getDishPanel().getTabbedPane();
        if (dishTabbedPane.getTabCount() > 0) {
            String newTitle = dishEditPanel.getTypeTextField().getText();
            dishTabbedPane.setTitleAt(dishTabbedPane.getSelectedIndex(), newTitle);
        }
    }

    public void changeDishName() {
        if (selectedDish != null) {
            selectedDish.setDishName(dishEditPanel.getDishNameTextField().getText());
        }
    }

    public void changeDishPrice() {
        if (selectedDish != null) {
            try{
            selectedDish.setPrice(Integer.valueOf(dishEditPanel.getDishPriceTextField().getText()));
            }
            catch (NumberFormatException ignored){
            }
        }
    }

    public void addType() {
        JTabbedPane dishTabbedPane = window.getDishPanel().getTabbedPane();
        dishTabbedPane.addTab("Новый тип", new JPanel(new GridBagLayout()));
        refreshEditPaneFields();
    }

    public void addDish(){
        DishPanel dishPanel = window.getDishPanel();
        JTabbedPane dishTabbedPane = window.getDishPanel().getTabbedPane();
        if (dishTabbedPane.getTabCount() > 0){
            JTabbedPane typeTabbedPane = (JTabbedPane)dishTabbedPane.getComponentAt(dishTabbedPane.getSelectedIndex());
            if (typeTabbedPane.getTabCount() > 0){
                DishPanelController dishPanelController = dishPanel.getController();
                dishPanelController.addDish();
            }
        }
    }

    public void refreshEditPaneFields(){
        JTabbedPane dishTabbedPane = window.getDishPanel().getTabbedPane();
        if (dishTabbedPane.getTabCount() > 0) {
            dishEditPanel.getTypeTextField().setText(dishTabbedPane.getTitleAt(dishTabbedPane.getSelectedIndex()));
        }
        else{
            dishEditPanel.getTypeTextField().setText("");
        }

        if (selectedDish != null){
            dishEditPanel.getDishNameTextField().setText(selectedDish.getDishName());
            dishEditPanel.getDishPriceTextField().setText(String.valueOf(selectedDish.getPrice()));
        }
        else{
            dishEditPanel.getDishNameTextField().setText("");
            dishEditPanel.getDishPriceTextField().setText("");
        }

    }

    public void setSelectedDish(Dish dish){
        selectedDish = dish;
    }
}

//    public void removeSubType(){
//        JTabbedPane dishTabbedPane = window.getDishPanel().getTabbedPane();
//        if (dishTabbedPane.getTabCount() > 0) {
//            JTabbedPane typeTabbedPane = (JTabbedPane)dishTabbedPane.getComponentAt(dishTabbedPane.getSelectedIndex());
//            if (typeTabbedPane.getTabCount() > 0){
//                typeTabbedPane.removeTabAt(typeTabbedPane.getSelectedIndex());
//            }
//        }
//        refreshEditPaneFields();
//    }


//    public void addSubType() {
//        if (dishTabbedPane.getTabCount() > 0) {
//            JTabbedPane typeTabbedPane = (JTabbedPane)dishTabbedPane.getComponentAt(dishTabbedPane.getSelectedIndex());
//            typeTabbedPane.addTab("Новый подтип",  new JPanel(new BorderLayout()));
//            refreshEditPaneFields();
//        }
//    }

//    public void changeSubTypeName() {
//        if (dishTabbedPane.getTabCount() > 0) {
//            JTabbedPane typeTabbedPane = (JTabbedPane) dishTabbedPane.getComponentAt(dishTabbedPane.getSelectedIndex());
//            if (typeTabbedPane.getTabCount() > 0){
//                typeTabbedPane.setTitleAt(typeTabbedPane.getSelectedIndex(), dishEditPanel.getSubTypeTextField().getText());
//            }
//        }
//    }