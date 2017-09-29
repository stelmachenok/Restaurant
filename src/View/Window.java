package View;

import Controller.Controller;
import Model.Table;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Created by y50-70 on 14.09.2017.
 */
public class Window {
    private JFrame frame;
    private JPanel panel;
    private DishPanel dishPanel;
    private JToolBar toolBar;
    private JPanel editToolBar;
    private Controller controller;
    private JTabbedPane tabbedPane;
    private JTextField hallNameTextField;
    private JTextField tableNameTextField;
    private JButton editButton;
    private JButton exitButton;
    private JButton readyButton;
    private boolean isEditModeOn;

    static final Dimension DIMENSION = new Dimension(1920, 1030);
    static final Font TAB_AND_BUTTON_FONT = new Font("Console", Font.BOLD, 20);

    private Window(){
        createFrame();
        createController();
        createEditToolBar();
        createTabbedPane();
        createToolBar();
        createDishPanel();
        panel.updateUI();
    }

    private void createDishPanel(){
        dishPanel = new DishPanel();
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
        return tableNameTextField;
    }

    private void createEditToolBar(){
        editToolBar = new JPanel(new GridBagLayout());
        editToolBar.setVisible(false);
        GridBagConstraints c = initGridBagConstraints();

        JLabel label = new JLabel("<html><u>Зал</u></html>");
        label.setForeground(new Color(225,0, 25));
        label.setFont(TAB_AND_BUTTON_FONT);
        editToolBar.add(label, c);

        JButton button;
        button = new JButton("Добавить");
        button.addActionListener(e -> {
            controller.addTableArea();
        });
        button.setFont(TAB_AND_BUTTON_FONT);
        c.gridy++;
        editToolBar.add(button,c);

        hallNameTextField = new JTextField(10);
        hallNameTextField.setFont(TAB_AND_BUTTON_FONT);
        hallNameTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                tabbedPane.setTitleAt(tabbedPane.getSelectedIndex(), hallNameTextField.getText());
            }
        });
        c.gridy++;
        editToolBar.add(hallNameTextField, c);

        button = new JButton("Удалить");
        button.setFont(TAB_AND_BUTTON_FONT);
        button.addActionListener(e -> {
            tabbedPane.removeTabAt(tabbedPane.getSelectedIndex());
        });
        c.gridy++;
        editToolBar.add(button,c);

        label = new JLabel("<html><u>Стол</u></html>");
        label.setForeground(new Color(225,0, 25));
        label.setFont(TAB_AND_BUTTON_FONT);
        c.gridy++;
        editToolBar.add(label, c);

        button = new JButton("Добавить");
        button.setFont(TAB_AND_BUTTON_FONT);
        button.addActionListener(e -> {
            controller.addTable((int)(DIMENSION.getWidth()/2), (int)(DIMENSION.getHeight()/2));
        });
        c.gridy++;
        editToolBar.add(button,c);

        tableNameTextField = new JTextField(10);
        tableNameTextField.setFont(TAB_AND_BUTTON_FONT);
        tableNameTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
            Table table = getCurrentTableArea().getSelectedTable();
            if (table != null){
                table.setTableName(tableNameTextField.getText());
            }
            tabbedPane.repaint();
            }
        });
        c.gridy++;
        editToolBar.add(tableNameTextField, c);

        button = new JButton("Удалить");
        button.setFont(TAB_AND_BUTTON_FONT);
        button.addActionListener(e -> {
            controller.removeTable();
        });
        c.gridy++;
        editToolBar.add(button,c);

        panel.add(editToolBar, BorderLayout.EAST);
    }

    private void createTabbedPane(){
        tabbedPane = new JTabbedPane();
        tabbedPane.setFont(TAB_AND_BUTTON_FONT);
        tabbedPane.addTab("New Hall", new TableArea(this));
        tabbedPane.addChangeListener(e ->
        {
            hallNameTextField.setText(tabbedPane.getTitleAt(tabbedPane.getSelectedIndex()));
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
            editToolBar.setVisible(true);
            String tabHeader = tabbedPane.getTitleAt(tabbedPane.getSelectedIndex());
            hallNameTextField.setText(tabHeader);
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
            editToolBar.setVisible(false);
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

    private GridBagConstraints initGridBagConstraints(){
        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.CENTER;
        c.fill   = GridBagConstraints.CENTER;
        c.gridheight = 1;
        c.gridwidth  = 8;
        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(10, 0, 0, 0);
        c.ipadx = 1;
        c.ipady = 1;
        c.weightx = 0.0;
        c.weighty = 0.0;
        return c;
    }

    public JTabbedPane getTabbedPane() {
        return tabbedPane;
    }

    public static void main(String[] args) {
        new Window();
    }
}
