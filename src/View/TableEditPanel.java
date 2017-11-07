package View;

import Controller.TableEditController;
import Model.Table;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import static View.Window.DIMENSION;
import static View.Window.TAB_AND_BUTTON_FONT;

/**
 * Created by y50-70 on 12.10.2017.
 */
public class TableEditPanel extends JPanel {
    private JTextField hallNameTextField;
    private JTextField tableNameTextField;
    private JTabbedPane tableTabbedPane;
    private Window window;
    private TableEditController controller;
    private GridBagConstraints constraints;

    public void setTableTabbedPane(JTabbedPane tableTabbedPane) {
        this.tableTabbedPane = tableTabbedPane;
    }

    public void setWindow(Window window) {
        this.window = window;
    }

    public JTextField getHallNameTextField() {
        return hallNameTextField;
    }

    public JTextField getTableNameTextField() {
        return tableNameTextField;
    }

    TableEditPanel(Window window, JTabbedPane tableTabbedPane) {
        super(new GridBagLayout());
        setVisible(false);
        initGridBagConstraints();

        this.window = window;
        this.tableTabbedPane = tableTabbedPane;
        controller = new TableEditController(window, tableTabbedPane);
        JLabel label = new JLabel("<html><u>Зал</u></html>");
        label.setForeground(new Color(225, 0, 25));
        label.setFont(TAB_AND_BUTTON_FONT);
        add(label, constraints);

        JButton button;
        button = new JButton("Добавить");
        button.addActionListener(e -> {
            controller.addTableArea();
        });
        button.setFont(TAB_AND_BUTTON_FONT);
        constraints.gridy++;
        add(button, constraints);

        hallNameTextField = new JTextField(10);
        hallNameTextField.setFont(TAB_AND_BUTTON_FONT);
        hallNameTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (tableTabbedPane.getTabCount() > 0) {
                    tableTabbedPane.setTitleAt(tableTabbedPane.getSelectedIndex(), hallNameTextField.getText());
                }
            }
        });
        constraints.gridy++;
        add(hallNameTextField, constraints);

        button = new JButton("Удалить");
        button.setFont(TAB_AND_BUTTON_FONT);
        button.addActionListener(e -> {
            if (tableTabbedPane.getTabCount() > 0)
                tableTabbedPane.removeTabAt(tableTabbedPane.getSelectedIndex());
        });
        constraints.gridy++;
        add(button, constraints);

        label = new JLabel("<html><u>Стол</u></html>");
        label.setForeground(new Color(225, 0, 25));
        label.setFont(TAB_AND_BUTTON_FONT);
        constraints.gridy++;
        add(label, constraints);

        button = new JButton("Добавить");
        button.setFont(TAB_AND_BUTTON_FONT);
        button.addActionListener(e -> {
            controller.addTable((int) (DIMENSION.getWidth() / 2), (int) (DIMENSION.getHeight() / 2));
        });
        constraints.gridy++;
        add(button, constraints);

        tableNameTextField = new JTextField(10);
        tableNameTextField.setFont(TAB_AND_BUTTON_FONT);
        tableNameTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                TableArea tableArea = window.getCurrentTableArea();
                if (tableArea != null) {
                    Table table = tableArea.getSelectedTable();
                    if (table != null) {
                        table.setTableName(tableNameTextField.getText());
                    }
                    tableTabbedPane.repaint();
                }
            }
        });
        constraints.gridy++;
        add(tableNameTextField, constraints);

        button = new JButton("Удалить");
        button.setFont(TAB_AND_BUTTON_FONT);
        button.addActionListener(e -> {
            controller.removeTable();
        });
        constraints.gridy++;
        add(button, constraints);
    }

    private void initGridBagConstraints() {
        constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.fill = GridBagConstraints.CENTER;
        constraints.gridheight = 1;
        constraints.gridwidth = 8;
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.insets = new Insets(10, 0, 0, 0);
        constraints.ipadx = 1;
        constraints.ipady = 1;
        constraints.weightx = 0.0;
        constraints.weighty = 0.0;
    }
}


//        window.addDishPanelListener(new DishPanelListener() {
//            @Override
//            public JTextField getTableNameTextField() {
//                return tableNameTextField;
//            }
//
//            @Override
//            public JTextField getHallNameTextField() {
//                return hallNameTextField;
//            }
//        });
