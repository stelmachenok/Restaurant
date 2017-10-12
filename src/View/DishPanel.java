package View;

import Controller.Controller;

import javax.swing.*;
import java.awt.*;

import static View.Window.DIMENSION;
import static View.Window.TAB_AND_BUTTON_FONT;

/**
 * Created by y50-70 on 29.09.2017.
 */
public class DishPanel extends JPanel{
    private Window window;
    private Controller controller;
    private JToolBar toolBar;
    private JTabbedPane tabbedPane;
    private DishEditPanel editDishPanel;
    private boolean isEditModeOn;
    private JButton editButton;
    private JButton closeButton;
    private JButton readyButton;

    DishPanel(Window window){
        super(new BorderLayout());
        this.window = window;
        controller = this.window.getController();
        createPanel();
        createToolBar();
        createTabbedPane();
        createSubTypeTabbedPane();
        createEditPanel();
    }

    private void createEditPanel(){

        editDishPanel = new DishEditPanel();
        editDishPanel.setDishTabbedPane(tabbedPane);

        add(editDishPanel, BorderLayout.EAST);
    }

    private void createSubTypeTabbedPane() {
        JTabbedPane tabbedPane = new JTabbedPane();

        tabbedPane.addTab("Алкогольные", new JPanel());
        tabbedPane.addTab("Горячие", new JPanel());
        this.tabbedPane.add("Напитки", tabbedPane);
        tabbedPane.addTab("Еда", new JPanel(new BorderLayout()));
    }

    private void createTabbedPane() {
        tabbedPane = new JTabbedPane();
        tabbedPane.setFont(TAB_AND_BUTTON_FONT);
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
                    String subTypeHeader = tab.getTitleAt(tab.getSelectedIndex());
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
}
