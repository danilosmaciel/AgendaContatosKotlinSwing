package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public abstract class Base extends JFrame implements ActionListener {

    public void iniConfig(final JPanel panel){
        configInit(panel);
    }

    private void configInit(final JPanel panel) {
        setContentPane(panel);
        setSize(500, 250);
        setVisible(true);
        setOnCenterScreen();
        setListerners();
    }

    private void setOnCenterScreen(){
        final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        final int onX = dim.width / 2 - getSize().width / 2;
        final int onY = dim.height / 2 - getSize().height / 2;
        setLocation(onX,onY);
    }

    void setExitOnClose(){
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    void setDisposedOnClose(){
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }

    protected abstract void setListerners();
}
