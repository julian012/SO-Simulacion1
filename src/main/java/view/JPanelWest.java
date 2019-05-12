package view;

import controller.Events;

import javax.swing.*;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.awt.event.ActionListener;

public class JPanelWest extends JPanel {

    private JLabel jLTitle;
    private JLabel jLName;
    private JTextField jTFName;
    private JLabel jLTime;
    private JSpinner jSTime;
    private JLabel jLBlock;
    private JCheckBox jCBlock;
    private JButton jBAdd;
    private JButton jBCancel;
    private JButton jBStart;
    private ActionListener actionListener;
    private GridBagConstraints constraints;

    public JPanelWest(ActionListener actionListener){
        setOpaque(false);
        this.actionListener = actionListener;
        setLayout(new GridBagLayout());
        constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.BOTH;
        initComponents();
        this.setVisible(true);
    }

    public void initComponents(){
        jLTitle = new JLabel("Agregar proceso", JLabel.CENTER);
        jLTitle.setFont(new Font("Helvetiva", Font.PLAIN,30));
        jLTitle.setForeground(Color.white);
        jLTitle.setPreferredSize(new Dimension(450,100));
        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.gridwidth = 2;
        constraints.weighty = 0.3;
        this.add(jLTitle, constraints);

        jLName = new JLabel("Nombre del proceso:");
        jLName.setForeground(Color.white);
        constraints.gridx = 1;
        constraints.gridy = 2;
        constraints.gridwidth = 1;
        constraints.weightx = 0.5;
        constraints.weighty = 0.01;
        this.add(jLName, constraints);

        jTFName = new JTextField();
        jTFName.setPreferredSize(new Dimension(200,30));
        constraints.gridx = 2;
        constraints.gridy = 2;
        constraints.gridwidth = 1;
        //constraints.weightx = 0.04;
        constraints.weighty = 0.01;
        this.add(jTFName, constraints);

        jLTime = new JLabel("Tiempo del Proceso: ");
        jLTime.setForeground(Color.white);
        constraints.gridx = 1;
        constraints.gridy = 3;
        constraints.gridwidth = 1;
        //constraints.weightx = 0.04;
        constraints.weighty = 0.1;
        this.add(jLTime, constraints);

        jSTime = new JSpinner();
        JFormattedTextField txt = ((JSpinner.NumberEditor) jSTime.getEditor()).getTextField();
        ((NumberFormatter) txt.getFormatter()).setAllowsInvalid(false);
        constraints.gridx = 2;
        constraints.gridy = 3;
        constraints.gridwidth = 1;
        //constraints.weightx = 0.04;
        constraints.weighty = 0.1;
        this.add(jSTime, constraints);

        jLBlock = new JLabel("¿Proceso con bloqueo?: ");
        jLBlock.setForeground(Color.white);
        constraints.gridx = 1;
        constraints.gridy = 4;
        constraints.gridwidth = 1;
        //constraints.weightx = 0.04;
        constraints.weighty = 1;
        this.add(jLBlock, constraints);

        jCBlock = new JCheckBox();
        jCBlock.setOpaque(false);
        constraints.gridx = 2;
        constraints.gridy = 4;
        constraints.gridwidth = 1;
        //constraints.weightx = 0.04;
        constraints.weighty = 1;
        this.add(jCBlock, constraints);

        jBCancel = new JButton("Limpiar");
        jBCancel.addActionListener(actionListener);
        jBCancel.setActionCommand(Events.CLEAR.toString());
        constraints.gridx = 1;
        constraints.gridy = 5;
        constraints.gridwidth = 1;
        //constraints.weightx = 0.04;
        constraints.weighty = 1;
        constraints.insets = new Insets(5,5,0,5);
        this.add(jBCancel, constraints);

        jBAdd = new JButton("Agregar");
        jBAdd.addActionListener(actionListener);
        jBAdd.setActionCommand(Events.ADD.toString());
        //constraints.insets = new Insets(10,80,200,80);
        constraints.gridx = 2;
        constraints.gridy = 5;
        constraints.gridwidth = 1;
        //constraints.weightx = 0.04;
        constraints.weighty = 1;
        this.add(jBAdd, constraints);

        jBStart = new JButton("Realizar Simulación");
        jBStart.setForeground(Color.WHITE);
        jBStart.setBackground(Color.decode("#088A08"));
        jBStart.addActionListener(actionListener);
        jBStart.setActionCommand(Events.START.toString());
        jBStart.setBorder(BorderFactory.createEmptyBorder());
        constraints.insets = new Insets(10,40,10,40);
        constraints.gridx = 1;
        constraints.gridy = 6;
        constraints.gridwidth = 2;
        //constraints.weightx = 0.04;
        constraints.weighty = 1;
        this.add(jBStart, constraints);

    }

    public void cleanAll(){
        jTFName.setText("");
        jSTime.setValue(5);
    }

    public String getNameProcess(){
        return jTFName.getText();
    }

    public int getTimeProcess(){
        return  Integer.valueOf(String.valueOf(jSTime.getValue()));
    }

    public boolean isBlocked(){
        return jCBlock.isSelected();
    }
}
