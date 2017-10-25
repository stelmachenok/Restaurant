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
    private JToolBar toolBar;
    private TableEditPanel tableEditPanel;
    private Controller controller;
    private JTabbedPane tabbedPane;
//    private JTextField hallNameTextField;
//    private JTextField tableNameTextField;
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
        createDishPanel();
        panel.updateUI();
    }

    private void createDishPanel(){
        dishPanel = new DishPanel(this);
    }

    private void createController() {
        controller = new Controller(this);
    }

    private void createFrame(){
        frame = new JFrame("Restaurant");
        panel = new JPanel(new BorderLayout());
        frame.add(panel);
        frame.setSize(DIMENSION);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private void createToolBar(){
        toolBar = new JToolBar("ToolBar", JToolBar.HORIZONTAL);
        createEditButton();
        createExitButton();
        createReadyButton();
        panel.add(toolBar, BorderLayout.NORTH);

    }

    public JTextField getTableNameTextField() {
        return tableEditPanel.getTableNameTextField();
    }

    private void createEditPanel(){
        tableEditPanel = new TableEditPanel(this, tabbedPane);
        panel.add(tableEditPanel, BorderLayout.EAST);
    }

    private void createTabbedPane(){
        tabbedPane = new JTabbedPane();
        tabbedPane.setFont(TAB_AND_BUTTON_FONT);
        tabbedPane.addTab("New Hall", new TableArea(this));
        tabbedPane.addChangeListener(e -> {
            if (tabbedPane.getTabCount() > 0) {
                tableEditPanel.getHallNameTextField().setText(tabbedPane.getTitleAt(tabbedPane.getSelectedIndex()));
            }
            else{
                tableEditPanel.getHallNameTextField().setText("");
            }
        });
        panel.add(tabbedPane, BorderLayout.CENTER);
    }

    Controller getController() {
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
        return (TableArea)tabbedPane.getComponentAt(tabbedPane.getSelectedIndex());
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

    boolean isEditModeOn() {
        return isEditModeOn;
    }

    private void createExitButton(){
        exitButton = new JButton("Выйти");
        exitButton.setFont(TAB_AND_BUTTON_FONT);
        exitButton.addActionListener(e -> {

        });
        toolBar.add(exitButton);
    }

    public JTabbedPane getTabbedPane() {
        return tabbedPane;
    }

    public static void main(String[] args) {
        new Window();
    }
}
