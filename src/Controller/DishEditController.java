package Controller;

import Model.Dish;
import View.DishEditPanel;
import View.DishPanel;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

/**
 * Created by y50-70 on 12.10.2017.
 */
public class DishEditController {
    private JTabbedPane dishTabbedPane;
    private DishEditPanel dishEditPanel;
    private DishPanel dishPanel;
    private Dish selectedDish;

    public DishEditController(DishPanel dishPanel, DishEditPanel dishEditPanel) {
        this.dishPanel = dishPanel;
        this.dishEditPanel = dishEditPanel;
        this.dishTabbedPane = this.dishPanel.getTabbedPane();
    }

    public void removeType() {
        if (dishTabbedPane.getTabCount() > 0) {
            dishTabbedPane.removeTabAt(dishTabbedPane.getSelectedIndex());
        }
        refreshEditPaneFields();
    }

    public void removeSubType(){
        if (dishTabbedPane.getTabCount() > 0) {
            JTabbedPane typeTabbedPane = (JTabbedPane)dishTabbedPane.getComponentAt(dishTabbedPane.getSelectedIndex());
            if (typeTabbedPane.getTabCount() > 0){
                typeTabbedPane.removeTabAt(typeTabbedPane.getSelectedIndex());
            }
        }
        refreshEditPaneFields();
    }

    public void changeTypeName() {
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

    public void changeSubTypeName() {
        if (dishTabbedPane.getTabCount() > 0) {
            JTabbedPane typeTabbedPane = (JTabbedPane) dishTabbedPane.getComponentAt(dishTabbedPane.getSelectedIndex());
            if (typeTabbedPane.getTabCount() > 0){
                typeTabbedPane.setTitleAt(typeTabbedPane.getSelectedIndex(), dishEditPanel.getSubTypeTextField().getText());
            }
        }
    }

    public void addType() {
        JTabbedPane subTypetabbedPane = new JTabbedPane();
        subTypetabbedPane.addChangeListener(e -> {
            refreshEditPaneFields();
        });
        dishTabbedPane.addTab("Новый тип", subTypetabbedPane);
        refreshEditPaneFields();
    }

    public void addSubType() {
        if (dishTabbedPane.getTabCount() > 0) {
            JTabbedPane typeTabbedPane = (JTabbedPane)dishTabbedPane.getComponentAt(dishTabbedPane.getSelectedIndex());
            typeTabbedPane.addTab("Новый подтип",  new JPanel(new BorderLayout()));
            refreshEditPaneFields();
        }
    }

    public void addDish(){
        if (dishTabbedPane.getTabCount() > 0){
            JTabbedPane typeTabbedPane = (JTabbedPane)dishTabbedPane.getComponentAt(dishTabbedPane.getSelectedIndex());
            if (typeTabbedPane.getTabCount() > 0){
                DishPanelController dishPanelController = dishPanel.getController();
                dishPanelController.addDish();
            }
        }
    }

    public void refreshEditPaneFields(){
        if (dishTabbedPane.getTabCount() > 0) {
            dishEditPanel.getTypeTextField().setText(dishTabbedPane.getTitleAt(dishTabbedPane.getSelectedIndex()));
            JTabbedPane typeTabbedPane = (JTabbedPane)dishTabbedPane.getComponentAt(dishTabbedPane.getSelectedIndex());
            if (typeTabbedPane.getTabCount() > 0){
                dishEditPanel.getSubTypeTextField().setText(typeTabbedPane.getTitleAt(typeTabbedPane.getSelectedIndex()));
            }
            else {
                dishEditPanel.getSubTypeTextField().setText("");
            }
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
