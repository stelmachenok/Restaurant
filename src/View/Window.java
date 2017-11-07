package View;

import Controller.Controller;

import javax.swing.*;
import java.awt.*;

/**
 * Created by y50-70 on 14.09.2017.
 */
public class Window {
    private JFrame frame;
    private JPanel panel;
    private DishPanel dishPanel;
    private DishEditPanel dishEditPanel;
    private DishOrderPanel dishOrderPanel;
    private JToolBar toolBar;
    private TableEditPanel tableEditPanel;
    private Controller controller;
    private JTabbedPane tabbedPane;
    private JButton editButton;
    private JButton exitButton;
    private JButton readyButton;
    private boolean isEditModeOn;

    static final Dimension DIMENSION = new Dimension(1920, 1030);
    static final Font TAB_AND_BUTTON_FONT = new Font("Console", Font.BOLD, 20);

    private Window(){
        createFrame();
        createController();
        createTabbedPane();
        createEditPanel();
        createToolBar();
        createDishEditPanel();
        createDishOrderPanel();
        createDishPanel();
        panel.updateUI();
    }

    private void createFrame(){
        frame = new JFrame("Restaurant");
        panel = new JPanel(new BorderLayout());
        frame.add(panel);
        frame.setSize(DIMENSION);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private void createController() {
        controller = new Controller(this);
    }

    private void createTabbedPane(){
        tabbedPane = new JTabbedPane();
        tabbedPane.setFont(TAB_AND_BUTTON_FONT);
        tabbedPane.addTab("New Hall", new TableArea(this));
        tabbedPane.addChangeListener(e -> {
            controller.changeTab();
        });
        panel.add(tabbedPane, BorderLayout.CENTER);
    }

    private void createEditPanel(){
        tableEditPanel = new TableEditPanel(this);
        panel.add(tableEditPanel, BorderLayout.EAST);
    }

    private void createToolBar(){
        toolBar = new JToolBar("ToolBar", JToolBar.HORIZONTAL);
        createEditButton();
        createExitButton();
        createReadyButton();
        panel.add(toolBar, BorderLayout.NORTH);

    }

    private void createDishEditPanel(){
        dishEditPanel = new DishEditPanel(this);
    }

    private void createDishOrderPanel(){
        dishOrderPanel = new DishOrderPanel(this);
    }

    private void createDishPanel(){
        dishPanel = new DishPanel(this);
    }

    private void createEditButton(){
        editButton = new JButton("Редактировать");
        editButton.setFont(TAB_AND_BUTTON_FONT);
        editButton.addActionListener(e -> {
            toolBar.removeAll();
            toolBar.add(readyButton);
            tableEditPanel.setVisible(true);
            String tabHeader;
            if (tabbedPane.getTabCount() != 0) {
                tabHeader = tabbedPane.getTitleAt(tabbedPane.getSelectedIndex());
                tableEditPanel.getHallNameTextField().setText(tabHeader);
            }
            isEditModeOn = true;
        });
        toolBar.add(editButton);
    }

    private void createReadyButton(){
        readyButton = new JButton("Готово");
        readyButton.setFont(TAB_AND_BUTTON_FONT);
        readyButton.addActionListener(e -> {
            toolBar.removeAll();
            toolBar.add(editButton);
            toolBar.add(exitButton);
            tableEditPanel.setVisible(false);
            isEditModeOn = false;
        });

    }

    private void createExitButton(){
        exitButton = new JButton("Выйти");
        exitButton.setFont(TAB_AND_BUTTON_FONT);
        exitButton.addActionListener(e -> {

        });
        toolBar.add(exitButton);
    }

    public Controller getController() {
        return controller;
    }

    public JPanel getPanel() {
        return panel;
    }

    public JFrame getFrame() {
        return frame;
    }

    public DishPanel getDishPanel() {
        return dishPanel;
    }

    public TableArea getCurrentTableArea() {
        if (tabbedPane.getTabCount() > 0) {
            return (TableArea) tabbedPane.getComponentAt(tabbedPane.getSelectedIndex());
        }
        else{
            return null;
        }
    }

    boolean isEditModeOn() {
        return isEditModeOn;
    }

    public DishEditPanel getDishEditPanel() {
        return dishEditPanel;
    }

    public DishOrderPanel getDishOrderPanel() {
        return dishOrderPanel;
    }

    public TableEditPanel getTableEditPanel() {
        return tableEditPanel;
    }

    public JTabbedPane getTabbedPane() {
        return tabbedPane;
    }

    public static void main(String[] args) {
        new Window();
    }
}
