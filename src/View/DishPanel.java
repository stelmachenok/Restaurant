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

    DishPanel(Window window) {
        super(new BorderLayout());
        this.window = window;
        createPanel();
        createController();
        createToolBar();
        createTabbedPane();
        addChangeListener();
    }

    private void createPanel() {
        setSize(DIMENSION);
        setVisible(true);
    }

    private void createController() {
        controller = new DishPanelController(this, window);
    }

    private void createToolBar() {
        toolBar = new JToolBar("DishToolBar", JToolBar.HORIZONTAL);
        createEditButton();
        createCloseButton();
        createReadyButton();
        add(toolBar, BorderLayout.NORTH);
    }

    private void createTabbedPane() {
        tabbedPane = new JTabbedPane();
        tabbedPane.setFont(TAB_AND_BUTTON_FONT);
        controller.fillingDishList();
        add(tabbedPane, BorderLayout.CENTER);
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

    private void addChangeListener() {
        tabbedPane.addChangeListener(e -> {
            window.getDishEditPanel().getController().refreshEditPaneFields();
        });
    }

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
}