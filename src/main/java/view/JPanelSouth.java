package view;

import javax.swing.*;
import java.awt.*;

public class JPanelSouth extends JPanel {

    private JLabel jLInfo;

    public JPanelSouth(){
        setBackground(Color.decode("#333333"));
        jLInfo = new JLabel("Desarrollado por: Julián Camilo Serna Vargas 2019", JLabel.CENTER);
        jLInfo.setForeground(Color.white);
        setLayout(new BorderLayout());
        this.add(jLInfo,BorderLayout.CENTER);
        this.setVisible(true);

    }
}
