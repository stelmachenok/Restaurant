package View;

import javax.swing.*;
import java.awt.*;

/**
 * Created by y50-70 on 29.09.2017.
 */
public class DishPanel extends JPanel{
    private JToolBar toolBar;
    private JTabbedPane tabbedPane;

    DishPanel(){
        super(new BorderLayout());
        createPanel();
        createToolBar();
        createTabbedPane();
    }

    private void createTabbedPane() {
        tabbedPane = new JTabbedPane();
        tabbedPane.setFont(Window.TAB_AND_BUTTON_FONT);
        tabbedPane.addTab("Еда", new JPanel());
        tabbedPane.addTab("Напитки", new JPanel());
        add(tabbedPane, BorderLayout.CENTER);
    }

    private void createPanel(){
        setSize(Window.DIMENSION);
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
        JButton button = new JButton("Изменить");
        button.setFont(Window.TAB_AND_BUTTON_FONT);
        toolBar.add(button);
    }

    private void createCloseButton(){
        JButton button = new JButton("Закрыть");
        button.setFont(Window.TAB_AND_BUTTON_FONT);
        toolBar.add(button);
    }

    private void createReadyButton(){
        JButton button = new JButton("Готово");
        button.setFont(Window.TAB_AND_BUTTON_FONT);
    }
}
