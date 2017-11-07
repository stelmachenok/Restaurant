package View;

import Controller.*;

import javax.swing.*;
import java.awt.*;

import static View.Window.DIMENSION;
import static View.Window.TAB_AND_BUTTON_FONT;

/**
 * Created by y50-70 on 29.09.2017.
 */
public class DishPanel extends JPanel {
    private Window window;
    private DishPanelController controller;
    private JToolBar toolBar;
    private JTabbedPane tabbedPane;
    private boolean isEditModeOn;
    private JButton editButton;
    private JButton closeButton;
    private JButton readyButton;

    public boolean isEditModeOn() {
        return isEditModeOn;
    }

    public Window getWindow() {
        return window;
    }

    public DishPanelController getController() {
        return controller;
    }

    public JTabbedPane getTabbedPane() {
        return tabbedPane;
    }

    DishPanel(Window window) {
        super(new BorderLayout());
        this.window = window;
        createPanel();
        createController();
        createToolBar();
        createTabbedPane();
    }

    private void createController() {
        controller = new DishPanelController(this, window);
    }

    private void createTabbedPane() {
        tabbedPane = new JTabbedPane();
        tabbedPane.setFont(TAB_AND_BUTTON_FONT);
        controller.fillingDishList();
        add(tabbedPane, BorderLayout.CENTER);
    }

    private void createPanel() {
        setSize(DIMENSION);
        setVisible(true);
    }

    private void createToolBar() {
        toolBar = new JToolBar("DishToolBar", JToolBar.HORIZONTAL);
        createEditButton();
        createCloseButton();
        createReadyButton();
        add(toolBar, BorderLayout.NORTH);
    }

    private void createEditButton() {
        DishOrderPanel dishOrderPanel = window.getDishOrderPanel();
        DishEditPanel dishEditPanel = window.getDishEditPanel();

        editButton = new JButton("Изменить");
        editButton.setFont(TAB_AND_BUTTON_FONT);
        editButton.addActionListener(e -> {
            toolBar.removeAll();
            toolBar.add(readyButton);
            remove(dishOrderPanel);
            add(dishEditPanel, BorderLayout.EAST);
            if (tabbedPane.getTabCount() > 0) {
                String tabHeader = tabbedPane.getTitleAt(tabbedPane.getSelectedIndex());
                dishEditPanel.getTypeTextField().setText(tabHeader);
            } else {
                dishEditPanel.getTypeTextField().setText("");
            }
            isEditModeOn = true;
            updateUI();
        });
        toolBar.add(editButton);
    }

    private void createCloseButton() {
        closeButton = new JButton("Закрыть");
        closeButton.setFont(TAB_AND_BUTTON_FONT);
        closeButton.addActionListener(e ->
                controller.backToSimpleMode());
        toolBar.add(closeButton);
    }

    public void addChangeListener(){
        tabbedPane.addChangeListener(e -> {
            controller.refreshEditPaneFields();
        });
    }

    private void createReadyButton() {
        DishEditPanel dishEditPanel = window.getDishEditPanel();
        DishOrderPanel dishOrderPanel = window.getDishOrderPanel();
        readyButton = new JButton("Готово");
        readyButton.setFont(TAB_AND_BUTTON_FONT);
        readyButton.addActionListener(e -> {
            toolBar.removeAll();
            toolBar.add(editButton);
            toolBar.add(closeButton);
            remove(dishEditPanel);
            add(dishOrderPanel, BorderLayout.EAST);
            isEditModeOn = false;
            updateUI();
        });
    }
}


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


//    JTabbedPane tab = (JTabbedPane) tabbedPane.getComponentAt(tabbedPane.getSelectedIndex());
////                JTabbedPane tab = tabbedPane.getComponentAt(tabbedPane.getSelectedIndex()).get(tabHeader);
//                if (tab != null) {
//                        String subTypeHeader = tab.getTitleAt(tab.getSelectedIndex()); //todo validation codname "bread"
//                        dishEditPanel.getSubTypeTextField().setText(subTypeHeader);
//                        }
//                        else {
//                        dishEditPanel.getSubTypeTextField().setText("");
//                        }


//    public void addPanelListener(DishPanelListener dishPanelListener) {
//        this.listeners.add(dishPanelListener);
//    }