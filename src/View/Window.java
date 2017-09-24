package View;

import Controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by y50-70 on 14.09.2017.
 */
public class Window {
    private JPanel panel;
    private JToolBar toolBar;
    private List<TableArea> tableAreaList;
    private TableArea currentTableArea;
    private Controller controller;

    private Window(){
        createFrame();
        createController();
        createTableAreaList();
        createToolBar();
    }

    private void createController() {
        controller = new Controller(this);
    }

    private void createFrame(){
        JFrame frame = new JFrame("Restaurant");
        panel = new JPanel(new BorderLayout());
        frame.add(panel);
        frame.setSize(1920, 1030);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private void createToolBar(){
        toolBar = new JToolBar("ToolBar", JToolBar.HORIZONTAL);
        for (TableArea tableArea: tableAreaList) {
            JButton tab = tableArea.getTab();
            JButton closeButton = tableArea.getCloseButton();
            toolBar.add(tab);
            toolBar.add(closeButton);
        }
        createButtonPlus();
        panel.add(toolBar, BorderLayout.NORTH);
    }

    private void createTableAreaList(){
        tableAreaList = new ArrayList<>();
        currentTableArea = new TableArea(this);
        tableAreaList.add(currentTableArea);
        /*JButton tab = currentTableArea.getTab();
        toolBar.add(tab);*/
        panel.add(currentTableArea, BorderLayout.CENTER);
    }

    public JToolBar getToolBar() {
        return toolBar;
    }

    public List<TableArea> getTableAreaList() {
        return tableAreaList;
    }

    Controller getController() {
        return controller;
    }

    public TableArea getCurrentTableArea() {
        return currentTableArea;
    }

    public JPanel getPanel() {
        return panel;
    }

    public void setCurrentTableArea(TableArea tableArea) {
        panel.remove(currentTableArea);
        currentTableArea = tableArea;
        panel.add(currentTableArea, BorderLayout.CENTER);
    }

    public void createButtonPlus(){
        JButton button = new JButton("+");
        button.setFont(TableArea.TAB_HEADER_FONT);
        button.setSize(500,500);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.addTableArea();
            }
        });
        toolBar.add(button);
    }



    public static void main(String[] args) {
        new Window();
    }
}
