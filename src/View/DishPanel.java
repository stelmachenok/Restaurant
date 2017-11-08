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
        editButton = new JButton("Изменить");
        editButton.setFont(TAB_AND_BUTTON_FONT);
        editButton.addActionListener(e -> {
            controller.setEditMode();
        });
        toolBar.add(editButton);
    }

    private void createCloseButton() {
        closeButton = new JButton("Закрыть");
        closeButton.setFont(TAB_AND_BUTTON_FONT);
        closeButton.addActionListener(e ->
                controller.backToTablePanel());
        toolBar.add(closeButton);
    }

    private void createReadyButton() {
        readyButton = new JButton("Готово");
        readyButton.setFont(TAB_AND_BUTTON_FONT);
        readyButton.addActionListener(e -> {
            controller.setSimpleMode();
        });
    }

    private void addChangeListener() {
        tabbedPane.addChangeListener(e -> {
            window.getDishEditPanel().getController().refreshEditPaneFields();
        });
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

    public JToolBar getToolBar() {
        return toolBar;
    }

    public JButton getEditButton() {
        return editButton;
    }

    public JButton getCloseButton() {
        return closeButton;
    }

    public JButton getReadyButton() {
        return readyButton;
    }
}