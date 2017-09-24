package View;

import Controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

/**
 * Created by y50-70 on 14.09.2017.
 */
public class Window {
    private JPanel panel;
    private JToolBar toolBar;
    private JPanel editToolBar;
    private List<TableArea> tableAreaList;
    private TableArea currentTableArea;
    private Controller controller;
    private JTabbedPane tabbedPane;
    private JTextField hallNameTextField;
    private JTextField tableNameTextField;
    private JButton editButton;
    private JButton exitButton;
    private JButton readyButton;

    static final private Font TAB_AND_BUTTON_FONT = new Font("Console", Font.BOLD, 20);

    private Window(){
        createFrame();
        createController();
        createTabbedPane();
        createToolBar();
        createEditToolBar();
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
        createEditButton();
        createExitButton();
        createReadyButton();
        panel.add(toolBar, BorderLayout.NORTH);

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
        c.gridy++;
        editToolBar.add(hallNameTextField, c);

        button = new JButton("Удалить");
        button.setFont(TAB_AND_BUTTON_FONT);
        c.gridy++;
        editToolBar.add(button,c);

        label = new JLabel("<html><u>Стол</u></html>");
        label.setForeground(new Color(225,0, 25));
        label.setFont(TAB_AND_BUTTON_FONT);
        c.gridy++;
        editToolBar.add(label, c);

        button = new JButton("Добавить");
        button.setFont(TAB_AND_BUTTON_FONT);
        c.gridy++;
        editToolBar.add(button,c);

        tableNameTextField = new JTextField(10);
        tableNameTextField.setFont(TAB_AND_BUTTON_FONT);
        c.gridy++;
        editToolBar.add(tableNameTextField, c);

        button = new JButton("Удалить");
        button.setFont(TAB_AND_BUTTON_FONT);
        c.gridy++;
        editToolBar.add(button,c);

        panel.add(editToolBar, BorderLayout.EAST);
        panel.updateUI();
    }

    private void createTabbedPane(){
        tabbedPane = new JTabbedPane();
        tabbedPane.setFont(TAB_AND_BUTTON_FONT);
        tabbedPane.addTab("New Hall", new TableArea(this));
        panel.add(tabbedPane, BorderLayout.CENTER);
        /*tableAreaList = new ArrayList<>();
        currentTableArea = new TableArea(this);
        tableAreaList.add(currentTableArea);
        panel.add(currentTableArea, BorderLayout.CENTER);*/
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
        return (TableArea)tabbedPane.getComponentAt(tabbedPane.getSelectedIndex());
    }

    public JPanel getPanel() {
        return panel;
    }

    /*public void setCurrentTableArea(TableArea tableArea) {
        panel.remove(currentTableArea);
        currentTableArea = tableArea;
        panel.add(currentTableArea, BorderLayout.CENTER);
    }*/

    /*public void createButtonPlus(){
        JButton button = new JButton("+");
        button.setFont(TAB_AND_BUTTON_FONT);
        button.setSize(500,500);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.addTableArea();
            }
        });
        toolBar.add(button);
    }*/

    private void createEditButton(){
        editButton = new JButton("Редактировать");
        editButton.setFont(TAB_AND_BUTTON_FONT);
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                toolBar.removeAll();
                toolBar.add(readyButton);
                editToolBar.setVisible(true);
                String tabHeader = tabbedPane.getTitleAt(tabbedPane.getSelectedIndex());
                hallNameTextField.setText(tabHeader);
            }
        });
        toolBar.add(editButton);
    }

    private void createReadyButton(){
        readyButton = new JButton("Готово");
        readyButton.setFont(TAB_AND_BUTTON_FONT);
        readyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                toolBar.removeAll();
                toolBar.add(editButton);
                toolBar.add(exitButton);
                editToolBar.setVisible(false);
            }
        });
    }

    private void createExitButton(){
        exitButton = new JButton("Выйти");
        exitButton.setFont(TAB_AND_BUTTON_FONT);
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
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
