package View;

import Controller.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static View.Window.DIMENSION;
import static View.Window.TAB_AND_BUTTON_FONT;

/**
 * Created by y50-70 on 29.09.2017.
 */
public class DishPanel extends JPanel{
    private Window window;
    private DishPanelController controller;
    private JToolBar toolBar;
    private JTabbedPane tabbedPane;
    private DishEditPanel editDishPanel;
    private boolean isEditModeOn;
    private JButton editButton;
    private JButton closeButton;
    private JButton readyButton;
    private List<DishPanelListener> listeners;

    public DishPanelController getController() {
        return controller;
    }

    public JTabbedPane getTabbedPane() {
        return tabbedPane;
    }

    public DishEditPanel getEditDishPanel() {
        return editDishPanel;
    }

    DishPanel(Window window){
        super(new BorderLayout());
        this.window = window;
        this.listeners = new ArrayList<>();
        createPanel();
        createController();
        createToolBar();
        createTabbedPane();
        createEditPanel();
        createSubTypeTabbedPane();
    }

    private void createController(){
        controller = new DishPanelController(this, window);
    }

    private void createEditPanel(){
        editDishPanel = new DishEditPanel(this);
        add(editDishPanel, BorderLayout.EAST);
    }

    private void createSubTypeTabbedPane() {
        controller.fillingDishList();
//        JTabbedPane tabbedPane = new JTabbedPane();
//        tabbedPane.addChangeListener(e -> {
//            controller.refreshEditPaneFields();
//        });
//        tabbedPane.addTab("Алкогольные", new JPanel(new GridBagLayout()));
//
//        List<Dish> dishes = new ArrayList<>();
//        Dish dish = new Dish("Espresso", 15);
//        Dish dish1 = new Dish("Capuccino", 15);
//        Dish dish2 = new Dish("Macaccino", 15);
//        Dish dish3 = new Dish("Italiano", 15);
//        dishes.add(dish);
//        dish.setVisible(true);
//        dishes.add(dish1);
//        dish1.setVisible(true);
//        dishes.add(dish2);
//        dish2.setVisible(true);
//        dishes.add(dish3);
//        dish3.setVisible(true);
//
//        JPanel hotPanel = new JPanel(new GridBagLayout());
//        hotPanel.setVisible(true);
//
//        GridBagConstraints constraints;
//
//        constraints = new GridBagConstraints();
//        constraints.anchor = GridBagConstraints.NORTH;
//        constraints.fill = GridBagConstraints.PAGE_START;
//        constraints.gridheight = 1;
//        constraints.gridwidth = 5;
//        constraints.gridx = GridBagConstraints.RELATIVE;
//        constraints.gridy = 0;
//        constraints.insets = new Insets(10, 5, 0, 5);
//        constraints.ipadx = 1;
//        constraints.ipady = 1;
//        constraints.weightx = 100;
//        constraints.weighty = 1;
//
//
//        tabbedPane.addTab("Горячие", hotPanel);
//        this.tabbedPane.add("Напитки", tabbedPane);
//
//        for (Dish currentDish:dishes) {
//            hotPanel.add(currentDish, constraints);
////            constraints.weightx += 100;
//            currentDish.addMouseListener(new MouseAdapter() {
//                @Override
//                public void mouseClicked(MouseEvent e) {
//                    controller.
//                    editDishPanel.getController().
//                }
//            });
//        }
//
//        JTabbedPane foodTabbedPane = new JTabbedPane();
//        foodTabbedPane.addChangeListener(e -> {
//            controller.refreshEditPaneFields();
//        });
//        this.tabbedPane.add("Еда", foodTabbedPane);

    }

    private void createTabbedPane() {
        tabbedPane = new JTabbedPane();
        tabbedPane.setFont(TAB_AND_BUTTON_FONT);
        tabbedPane.addChangeListener(e -> {
            controller.refreshEditPaneFields();
            for(DishPanelListener l: listeners){
                l.tableChanged();
            }
        });
        add(tabbedPane, BorderLayout.CENTER);
    }

    private void createPanel(){
        setSize(DIMENSION);
        setVisible(true);
    }

    private void createToolBar(){
        toolBar = new JToolBar("DishToolBar", JToolBar.HORIZONTAL);
        createEditButton();
        createCloseButton();
        createReadyButton();
        add(toolBar, BorderLayout.NORTH);
    }

    private void createEditButton(){
        editButton = new JButton("Изменить");
        editButton.setFont(TAB_AND_BUTTON_FONT);
        editButton.addActionListener(e -> {
            toolBar.removeAll();
            toolBar.add(readyButton);
            editDishPanel.setVisible(true);
            if (tabbedPane.getTabCount() > 0) {
                String tabHeader = tabbedPane.getTitleAt(tabbedPane.getSelectedIndex());
                editDishPanel.getTypeTextField().setText(tabHeader);
                JTabbedPane tab = (JTabbedPane) tabbedPane.getComponentAt(tabbedPane.getSelectedIndex());
//                JTabbedPane tab = tabbedPane.getComponentAt(tabbedPane.getSelectedIndex()).get(tabHeader);
                if (tab != null) {
                    String subTypeHeader = tab.getTitleAt(tab.getSelectedIndex()); //todo validation codname "bread"
                    editDishPanel.getSubTypeTextField().setText(subTypeHeader);
                }
                else {
                    editDishPanel.getSubTypeTextField().setText("");
                }
            }
            else{
                editDishPanel.getTypeTextField().setText("");
            }
            isEditModeOn = true;
        });
        toolBar.add(editButton);
    }

    private void createCloseButton(){
        closeButton = new JButton("Закрыть");
        closeButton.setFont(TAB_AND_BUTTON_FONT);
        closeButton.addActionListener(e ->
                controller.backToSimpleMode());
        toolBar.add(closeButton);
    }

    private void createReadyButton(){
        readyButton = new JButton("Готово");
        readyButton.setFont(TAB_AND_BUTTON_FONT);
        readyButton.addActionListener(e -> {
            toolBar.removeAll();
            toolBar.add(editButton);
            toolBar.add(closeButton);
            isEditModeOn = false;
            editDishPanel.setVisible(false);
        });
    }

    public void addListener(DishPanelListener dishPanelListener) {
        this.listeners.add(dishPanelListener);
    }
}
