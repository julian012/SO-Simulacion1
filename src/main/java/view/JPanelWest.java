package view;

import controller.Events;
import utilities.CustomDocumentFilter;

import javax.swing.*;
import javax.swing.text.AbstractDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JPanelWest extends JPanel implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JLabel jLTitle;
//	private String connectProcess;
	private JLabel jLPriority;
	private JTextField jTFPriority;
	

	private JLabel jLNewPriority;
	private JCheckBox jCBNewPriority;
	private JTextField jTFNewPriority;
	
    private JLabel jLName;
    private JTextField jTFName;
    
    private JLabel jLTime;
    private JTextField jSTime;
    
    private JLabel jLExcecute;
    private JCheckBox jCBExcecute;
    
    private JLabel jLBlock;
    private JCheckBox jCBlock;
    
    private JLabel jLDestroy;
    private JCheckBox jCDestroy;
    
    private JLabel jLLayoff;
    private JCheckBox jCBLayoff;
    
    private JLabel jLConnectProcess;
	private JCheckBox jCBConnectProcess;
	private JTextField jTFConnectProcess;
    
    private JButton jBClose;
    private JButton jBMinimize;
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
    
    public void createTimeProcess() {
    	 jLPriority = new JLabel("Establecer prioridad");
         jLPriority.setForeground(Color.decode("#2E8B57"));
         constraints.gridx = 1;
         constraints.gridy = 4;
         constraints.gridwidth = 1;
         constraints.weightx = 0.5;
         constraints.weighty = 0.01;
         this.add(jLPriority, constraints);
         
         jTFPriority = new JTextField();
     	jTFPriority.setPreferredSize(new Dimension(200,60));
     	 ((AbstractDocument) jTFPriority.getDocument()).setDocumentFilter(new CustomDocumentFilter());
         constraints.insets = new Insets(5,5,5,5);
         constraints.gridx = 2;
         constraints.gridy = 4;
         constraints.gridwidth = 1;
         //constraints.weightx = 0.04;
         constraints.weighty = 0.01;
         this.add(jTFPriority, constraints);
    }

    public void initComponents(){
    	
    	jBClose = new JButton("Cerrar Ventana");
    	jBClose.addActionListener(actionListener);
    	jBClose.setActionCommand(Events.CLOSE.toString());
    	jBClose.setPreferredSize(new Dimension(50,15));
        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        constraints.weighty = 0.3;
        this.add(jBClose, constraints);
        
        jBMinimize = new JButton("Minimizar");
        jBMinimize.addActionListener(actionListener);
        jBMinimize.setActionCommand(Events.MINIMIZE.toString());
    	constraints.gridx = 2;
        constraints.gridy = 1;
        constraints.gridwidth = 2;
        constraints.weighty = 0.3;
        this.add(jBMinimize, constraints);
        
        
        jLTitle = new JLabel("Agregar proceso", JLabel.CENTER);
        jLTitle.setFont(new Font("Helvetiva", Font.PLAIN,30));
        jLTitle.setForeground(Color.decode("#008080"));
        jLTitle.setPreferredSize(new Dimension(450,100));
        constraints.gridx = 1;
        constraints.gridy = 2;
        constraints.gridwidth = 2;
        constraints.weighty = 0.3;
        this.add(jLTitle, constraints);
        
        jLName = new JLabel("Nombre del proceso: ");
        jLName.setForeground(Color.decode("#2E8B57"));
        constraints.gridx = 1;
        constraints.gridy = 3;
        constraints.gridwidth = 1;
        constraints.weightx = 0.5;
        constraints.weighty = 0.01;
        this.add(jLName, constraints);

        jTFName = new JTextField();
        jTFName.setPreferredSize(new Dimension(200,60));
        constraints.insets = new Insets(5,5,5,5);
        constraints.gridx = 2;
        constraints.gridy = 3;
        constraints.gridwidth = 1;
        //constraints.weightx = 0.04;
        constraints.weighty = 0.01;
        this.add(jTFName, constraints);
        
        createTimeProcess();
        
    	jLNewPriority = new JLabel("¿Nueva prioridad?");
    	jLNewPriority.setForeground(Color.decode("#2E8B57"));
        constraints.gridx = 1;
        constraints.gridy = 5;
        constraints.gridwidth = 1;
        constraints.gridheight = 2;
        constraints.weightx = 0.5;
        constraints.weighty = 0.01;
        this.add(jLNewPriority, constraints);
        
        jCBNewPriority = new JCheckBox();
        jCBNewPriority.setOpaque(false);
        jCBNewPriority.addActionListener(this);
        jCBNewPriority.setActionCommand(Events.NEW_PRIORITY.toString());
        constraints.gridx = 2;
        constraints.gridy = 5;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        constraints.weightx = 0.5;
        constraints.weighty = 0.01;
        this.add(jCBNewPriority, constraints);
        
        jTFNewPriority = new JTextField();
        jTFNewPriority.setEnabled(false);
        jTFNewPriority.setBackground(Color.LIGHT_GRAY);
        jTFNewPriority.setPreferredSize(new Dimension(200,60));
        ((AbstractDocument) jTFNewPriority.getDocument()).setDocumentFilter(new CustomDocumentFilter());
        constraints.insets = new Insets(5,5,5,5);
        constraints.gridx = 2;
        constraints.gridy = 6;
        constraints.gridwidth = 1;
        //constraints.weightx = 0.04;
        constraints.weighty = 0.01;
        this.add(jTFNewPriority, constraints);

        jLTime = new JLabel("Tiempo del proceso: ");
        jLTime.setForeground(Color.decode("#2E8B57"));
        constraints.insets = new Insets(0,0,0,0);
        constraints.gridx = 1;
        constraints.gridy = 7;
        constraints.gridwidth = 1;
        //constraints.weightx = 0.04;
        constraints.weighty = 0.1;
        this.add(jLTime, constraints);

        jSTime = new JTextField();
        jSTime.setPreferredSize(new Dimension(200,60));
        ((AbstractDocument) jSTime.getDocument()).setDocumentFilter(new CustomDocumentFilter());
        constraints.insets = new Insets(5,5,5,5);
        constraints.gridx = 2;
        constraints.gridy = 7;
        constraints.gridwidth = 1;
        //constraints.weightx = 0.04;
        constraints.weighty = 0.1;
        this.add(jSTime, constraints);
        
        jLExcecute = new JLabel("¿Se ejecuta?");
        jLExcecute.setForeground(Color.decode("#2E8B57"));
        constraints.insets = new Insets(0,0,0,0);
        constraints.gridx = 1;
        constraints.gridy = 8;
        constraints.gridwidth = 1;
        //constraints.weightx = 0.04;
        constraints.weighty = 0.1;
        this.add(jLExcecute, constraints);
        
        jCBExcecute = new JCheckBox();
        jCBExcecute.setOpaque(false);
        constraints.gridx = 2;
        constraints.gridy = 8;
        constraints.gridwidth = 1;
        //constraints.weightx = 0.04;
        constraints.weighty = 1;
        this.add(jCBExcecute, constraints);

        jLBlock = new JLabel("¿Proceso con bloqueo?: ");
        jLBlock.setForeground(Color.decode("#2E8B57"));
        constraints.insets = new Insets(0,0,0,0);
        constraints.gridx = 1;
        constraints.gridy = 9;
        constraints.gridwidth = 1;
        //constraints.weightx = 0.04;
        constraints.weighty = 1;
        this.add(jLBlock, constraints);

        jCBlock = new JCheckBox();
        jCBlock.setOpaque(false);
        constraints.gridx = 2;
        constraints.gridy = 9;
        constraints.gridwidth = 1;
        //constraints.weightx = 0.04;
        constraints.weighty = 1;
        this.add(jCBlock, constraints);
        
        
        jLDestroy = new JLabel("¿Destruir proceso?");
        jLDestroy.setForeground(Color.decode("#2E8B57"));
        constraints.gridx = 1;
        constraints.gridy = 10;
        constraints.gridwidth = 1;
        //constraints.weightx = 0.04;
        constraints.weighty = 1;
        this.add(jLDestroy, constraints);
        
        jCDestroy = new JCheckBox();
        jCDestroy.setOpaque(false);
        constraints.gridx = 2;
        constraints.gridy = 10;
        constraints.gridwidth = 1;
        //constraints.weightx = 0.04;
        constraints.weighty = 1;
        this.add(jCDestroy, constraints);
        
        jLLayoff = new JLabel("¿Suspender?");
        jLLayoff.setForeground(Color.decode("#2E8B57"));
        constraints.gridx = 1;
        constraints.gridy = 11;
        constraints.gridwidth = 1;
        //constraints.weightx = 0.04;
        constraints.weighty = 1;
        this.add(jLLayoff, constraints);
        
        jCBLayoff = new JCheckBox();
        jCBLayoff.setOpaque(false);
        constraints.gridx = 2;
        constraints.gridy = 11;
        constraints.gridwidth = 1;
        //constraints.weightx = 0.04;
        constraints.weighty = 1;
        this.add(jCBLayoff, constraints);
        
        jLConnectProcess = new JLabel("¿Se conecta el proceso?");
        jLConnectProcess.setForeground(Color.decode("#2E8B57"));
        constraints.gridx = 1;
        constraints.gridy = 12;
        constraints.gridwidth = 1;
        constraints.gridheight = 2;
        //constraints.weightx = 0.04;
        constraints.weighty = 1;
        this.add(jLConnectProcess, constraints);
        
    	jCBConnectProcess = new JCheckBox();
    	jCBConnectProcess.setOpaque(false);
    	jCBConnectProcess.setSelected(false);
    	jCBConnectProcess.setActionCommand(Events.CONNECT_PROCESS.toString());
    	jCBConnectProcess.addActionListener(this);
    	constraints.gridx = 2;
        constraints.gridy = 12;
        constraints.gridwidth = 1;
        //constraints.weightx = 0.04;
        constraints.weighty = 1;
        constraints.gridheight = 1;
        this.add(jCBConnectProcess, constraints);
        
        jTFConnectProcess = new JTextField();
        jTFConnectProcess.setEnabled(false);
        jTFConnectProcess.setBackground(Color.LIGHT_GRAY);
        constraints.gridx = 2;
        constraints.gridy = 13;
        constraints.gridwidth = 1;
        //constraints.weightx = 0.04;
        constraints.weighty = 1;
        this.add(jTFConnectProcess, constraints);
        
        jBCancel = new JButton("Limpiar");
        jBCancel.addActionListener(actionListener);
        jBCancel.setActionCommand(Events.CLEAR.toString());
        constraints.gridx = 1;
        constraints.gridy = 14;
        constraints.gridwidth = 1;
        //constraints.weightx = 0.04;
        constraints.weighty=1;
        constraints.weighty = 1;
        constraints.insets = new Insets(5,5,0,5);
        this.add(jBCancel, constraints);
        
        jBAdd = new JButton("Agregar");
        jBAdd.addActionListener(actionListener);
        jBAdd.setActionCommand(Events.ADD.toString());
        //constraints.insets = new Insets(10,80,200,80);
        constraints.gridx = 2;
        constraints.gridy = 14;
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
        constraints.gridy = 15;
        constraints.gridwidth = 2;
        //constraints.weightx = 0.04;
        constraints.weighty = 1;
        this.add(jBStart, constraints);
    }

    public void cleanAll(){
    	jCBConnectProcess.setSelected(false);
    	jCBExcecute.setSelected(false);
    	jCBLayoff.setSelected(false);
    	jCBlock.setSelected(false);
    	jCBNewPriority.setSelected(false);
    	jCDestroy.setSelected(false);
    	jTFName.setText("");
    	jSTime.setText("");
    	((AbstractDocument) jSTime.getDocument()).setDocumentFilter(new CustomDocumentFilter());
        jTFConnectProcess.setText("");
        jTFNewPriority.setText("");
        ((AbstractDocument) jTFNewPriority.getDocument()).setDocumentFilter(new CustomDocumentFilter());
        jTFPriority.setText("");
        ((AbstractDocument) jTFPriority.getDocument()).setDocumentFilter(new CustomDocumentFilter());
        changeStatusConnectProcess();
        changeStatusNewPriority();
    }

    public String getNameProcess(){
        return jTFName.getText();
    }

    public int getTimeProcess(){
    	if(jSTime.getText().equals("")) {
    		return 0;
    	}
    	int value  = Integer.parseInt(jSTime.getText());
    	((AbstractDocument) jSTime.getDocument()).setDocumentFilter(null);
        return  value;
    }

    public boolean isBlocked(){
        return jCBlock.isSelected();
    }
    
    public int getPriority() {
    	if(jTFPriority.getText().equals("")) {
    		return 0;
    	}
    	((AbstractDocument) jTFPriority.getDocument()).setDocumentFilter(null);
    	return Integer.parseInt(jTFPriority.getText());
    }
    
    public int getNewPriority() {
    	((AbstractDocument) jTFNewPriority.getDocument()).setDocumentFilter(null);
    	if(!jCBNewPriority.isSelected()) {
    		
    		return getPriority();
    	}else {
    		if(jTFNewPriority.getText().equals("")) {
        		return Integer.parseInt(jTFPriority.getText());
        	}
    		return Integer.parseInt(jTFNewPriority.getText());
    	}
    }
    
    public boolean isDestroy() {
    	return jCDestroy.isSelected();
    }
    
    public boolean isLayoff() {
    	return jCBLayoff.isSelected();
    }
    
    public String isConnect() {
    	if(!jCBConnectProcess.isSelected()) {
    		return "No";
    	}else {
    		return jTFConnectProcess.getText();
    	}
    }
    
    public boolean isExecute() {
    	if(jCBExcecute.isSelected()) {
    		return true;
    	}else {
    		return false;
    	}
    }
    
    public JButton createButton() {
    	JButton button = new JButton();
    	button.setForeground(Color.decode("#333333"));
    	button.setBackground(Color.decode("#333333"));
    	button.setEnabled(false);
    	button.setBorder(BorderFactory.createEmptyBorder());
    	return button;
    }
    
    public void changeStatusConnectProcess() {
    	if(jCBConnectProcess.isSelected()) {
    		jTFConnectProcess.setEnabled(true);
    		jTFConnectProcess.setBackground(Color.white);
    	}else {
    		jTFConnectProcess.setEnabled(false);
    		jTFConnectProcess.setBackground(Color.LIGHT_GRAY);
    	}
    }
    
    public void changeStatusNewPriority() {
    	if(jCBNewPriority.isSelected()) {
    		jTFNewPriority.setEnabled(true);
    		jTFNewPriority.setBackground(Color.white);
    	}else {
    		jTFNewPriority.setEnabled(false);
    		jTFNewPriority.setBackground(Color.LIGHT_GRAY);
    	}
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (Events.valueOf(e.getActionCommand())) {
		case CONNECT_PROCESS:
			changeStatusConnectProcess();
			break;
		case NEW_PRIORITY:
			changeStatusNewPriority();
			break;
		default:
			break;
		}
		
	}
}
