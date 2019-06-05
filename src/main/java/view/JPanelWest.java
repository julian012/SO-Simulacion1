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
	
    private JLabel jLName;
    private JTextField jTFName;
    
    private JLabel jLTime;
    private JTextField jSTime;
    
    private JLabel jLSize;
    private JTextField jTFSize;
    
    private JLabel jLSelectPartition;
    private JComboBox<String> jCBPartition;
    
    private JButton jBSaveProcess;    
    private JButton jBCleanProcessForm;
    
    private JLabel jLTitlePartition;
    
    private JLabel jLPartitionName;
    private JTextField jTFPartitionName;
    
    private JLabel jLPartitionSize;
    private JTextField jTFPartitionSize;
    
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
        
        jLTime = new JLabel("Tiempo del proceso: ");
        jLTime.setForeground(Color.decode("#2E8B57"));
        constraints.insets = new Insets(0,0,0,0);
        constraints.gridx = 1;
        constraints.gridy = 4;
        constraints.gridwidth = 1;
        //constraints.weightx = 0.04;
        constraints.weighty = 0.1;
        this.add(jLTime, constraints);

        jSTime = new JTextField();
        jSTime.setPreferredSize(new Dimension(200,60));
        ((AbstractDocument) jSTime.getDocument()).setDocumentFilter(new CustomDocumentFilter());
        constraints.insets = new Insets(5,5,5,5);
        constraints.gridx = 2;
        constraints.gridy = 4;
        constraints.gridwidth = 1;
        //constraints.weightx = 0.04;
        constraints.weighty = 0.1;
        this.add(jSTime, constraints);
        
        jLSize = new JLabel("Tamaño");
        jLSize.setForeground(Color.decode("#2E8B57"));
        constraints.insets = new Insets(0,0,0,0);
        constraints.gridx = 1;
        constraints.gridy = 5;
        constraints.gridwidth = 1;
        //constraints.weightx = 0.04;
        constraints.weighty = 0.1;
        this.add(jLSize, constraints);
        
        jTFSize = new JTextField();
        jTFSize.setPreferredSize(new Dimension(200,60));
        ((AbstractDocument) jTFSize.getDocument()).setDocumentFilter(new CustomDocumentFilter());
        constraints.insets = new Insets(5,5,5,5);
        constraints.gridx = 2;
        constraints.gridy = 5;
        constraints.gridwidth = 1;
        //constraints.weightx = 0.04;
        constraints.weighty = 1;
        this.add(jTFSize, constraints);
        
        jLSelectPartition = new JLabel("Seleccionar Partición");
        jLSelectPartition.setForeground(Color.decode("#2E8B57"));
        constraints.gridx = 1;
        constraints.gridy = 6;
        constraints.gridwidth = 1;
        //constraints.weightx = 0.04;
        constraints.weighty = 1;
        this.add(jLSelectPartition, constraints);
        
        jCBPartition = new JComboBox<String>();
        constraints.gridx = 2;
        constraints.gridy = 6;
        constraints.gridwidth = 1;
        //constraints.weightx = 0.04;
        constraints.weighty = 1;
        this.add(jCBPartition, constraints);
        
        jBSaveProcess = new JButton("Agregar Proceso");
        jBSaveProcess.addActionListener(actionListener);
        jBSaveProcess.setActionCommand(Events.ADD_PROCESS.toString());
        constraints.insets = new Insets(0,0,0,0);
        constraints.gridx = 1;
        constraints.gridy = 7;
        constraints.gridwidth = 1;
        //constraints.weightx = 0.04;
        constraints.weighty = 0.1;
        this.add(jBSaveProcess, constraints);
        
        jBCleanProcessForm = new JButton("Limpiar Formulario");
        jBCleanProcessForm.addActionListener(this);
        jBCleanProcessForm.setActionCommand(Events.CLEAR_PROCESS_FORM.toString());
        constraints.insets = new Insets(0,0,0,0);
        constraints.gridx = 2;
        constraints.gridy = 7;
        constraints.gridwidth = 1;
        //constraints.weightx = 0.04;
        constraints.weighty = 0.1;
        this.add(jBCleanProcessForm, constraints);
        
        /*
         * Aca iria el de agregar a que particion corresponde
         * */
        
        jLTitlePartition = new JLabel("Agregar Partición", JLabel.CENTER);
        jLTitlePartition.setFont(new Font("Helvetiva", Font.PLAIN,30));
        jLTitlePartition.setForeground(Color.decode("#407DA7"));
        jLTitlePartition.setPreferredSize(new Dimension(450,100));
        constraints.gridx = 1;
        constraints.gridy = 8;
        constraints.gridwidth = 2;
        constraints.weighty = 0.3;
        this.add(jLTitlePartition, constraints);
        
        jLPartitionName = new JLabel("Nombre de la Partición");
        jLPartitionName.setForeground(Color.decode("#407DA7"));
        constraints.insets = new Insets(0,0,0,0);
        constraints.gridx = 1;
        constraints.gridy = 9;
        constraints.gridwidth = 1;
        //constraints.weightx = 0.04;
        constraints.weighty = 0.1;
        this.add(jLPartitionName, constraints);
        
        jTFPartitionName = new JTextField();
        jTFPartitionName.setPreferredSize(new Dimension(200,60));
        //constraints.insets = new Insets(5,5,5,5);
        constraints.gridx = 2;
        constraints.gridy = 9;
        constraints.gridwidth = 1;
        //constraints.weightx = 0.04;
        constraints.weighty = 0.01;
        this.add(jTFPartitionName, constraints);

        jLPartitionSize = new JLabel("Tamaño de la partición");
        jLPartitionSize.setForeground(Color.decode("#407DA7"));
        constraints.insets = new Insets(0,0,0,0);
        constraints.gridx = 1;
        constraints.gridy = 10;
        constraints.gridwidth = 1;
        //constraints.weightx = 0.04;
        constraints.weighty = 1;
        this.add(jLPartitionSize, constraints);

        jTFPartitionSize = new JTextField();
        jTFPartitionSize.setPreferredSize(new Dimension(200,60));
        ((AbstractDocument) jTFPartitionSize.getDocument()).setDocumentFilter(new CustomDocumentFilter());
        //constraints.insets = new Insets(5,5,5,5);
        constraints.gridx = 2;
        constraints.gridy = 10;
        constraints.gridwidth = 1;
        //constraints.weightx = 0.04;
        constraints.weighty = 1;
        this.add(jTFPartitionSize, constraints);
        
        constraints.gridx = 1;
        constraints.gridy = 11;
        constraints.gridwidth = 1;
        //constraints.weightx = 0.04;
        constraints.weighty = 1;
        
        constraints.gridx = 2;
        constraints.gridy = 11;
        constraints.gridwidth = 1;
        //constraints.weightx = 0.04;
        constraints.weighty = 1;
        
        constraints.gridx = 1;
        constraints.gridy = 12;
        constraints.gridwidth = 1;
        constraints.gridheight = 2;
        //constraints.weightx = 0.04;
        constraints.weighty = 1;
        
    	constraints.gridx = 2;
        constraints.gridy = 12;
        constraints.gridwidth = 1;
        //constraints.weightx = 0.04;
        constraints.weighty = 1;
        constraints.gridheight = 1;
        
        constraints.gridx = 2;
        constraints.gridy = 13;
        constraints.gridwidth = 1;
        //constraints.weightx = 0.04;
        constraints.weighty = 1;
        
        jBAdd = new JButton("Agregar Partición");
        jBAdd.addActionListener(actionListener);
        jBAdd.setActionCommand(Events.ADD_PARTITION.toString());
        constraints.gridx = 1;
        constraints.gridy = 14;
        constraints.gridwidth = 1;
        //constraints.weightx = 0.04;
        constraints.weighty=1;
        constraints.weighty = 1;
       // constraints.insets = new Insets(5,5,0,5);
        this.add(jBAdd, constraints);
        
        jBCancel = new JButton("Limpiar Formulario");
        jBCancel.addActionListener(this);
        jBCancel.setActionCommand(Events.CLEAR_PARTITION_FORM.toString());
        //constraints.insets = new Insets(10,80,200,80);
        constraints.gridx = 2;
        constraints.gridy = 14;
        constraints.gridwidth = 1;
        //constraints.weightx = 0.04;
        constraints.weighty = 1;
        this.add(jBCancel, constraints);

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

    public void cleanProcessForm(){
    	jTFName.setText("");
    	
    	((AbstractDocument) jSTime.getDocument()).setDocumentFilter(null);
    	jSTime.setText("");
    	((AbstractDocument) jSTime.getDocument()).setDocumentFilter(new CustomDocumentFilter());
    	
    	((AbstractDocument) jTFSize.getDocument()).setDocumentFilter(null);
    	jTFSize.setText("");
    	((AbstractDocument) jTFSize.getDocument()).setDocumentFilter(new CustomDocumentFilter());
    	validatePartitionList();
    }
    
    public void cleanPartitionForm(){
    	jTFPartitionName.setText("");
    	
    	((AbstractDocument) jTFPartitionSize.getDocument()).setDocumentFilter(null);
    	jTFPartitionSize.setText("");
    	((AbstractDocument) jTFPartitionSize.getDocument()).setDocumentFilter(new CustomDocumentFilter());
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

    public int getSizeProcess(){
    	if(jTFSize.getText().equals("")) {
    		return 0;
    	}
    	int value  = Integer.parseInt(jTFSize.getText());
    	((AbstractDocument) jTFSize.getDocument()).setDocumentFilter(null);
        return  value;
    }
    
    public String getPartitionName() {
    	return jTFPartitionName.getText();
    }
    
    public int getPartitionSize() {
    	if(jTFPartitionSize.getText().equals("")) {
    		return 0;
    	}
    	int value  = Integer.parseInt(jTFPartitionSize.getText());
    	((AbstractDocument) jTFPartitionSize.getDocument()).setDocumentFilter(null);
        return  value;
    }
    
    public JButton createButton() {
    	JButton button = new JButton();
    	button.setForeground(Color.decode("#333333"));
    	button.setBackground(Color.decode("#333333"));
    	button.setEnabled(false);
    	button.setBorder(BorderFactory.createEmptyBorder());
    	return button;
    }
    
    public void loadPartitions(String value) {
    	jCBPartition.addItem(value);
    }
    
    public void validatePartitionList() {
    	if (jCBPartition.getItemCount() > 0) {
			jBSaveProcess.setEnabled(true);
		}else {
			jBSaveProcess.setEnabled(false);
		}
    }
    
    public String getSelectedPartition() {
    	return jCBPartition.getItemAt(jCBPartition.getSelectedIndex());
    }
    
    
   /* public void changeStatusNewPriority() {
    	if(jCBNewPriority.isSelected()) {
    		jTFNewPriority.setEnabled(true);
    		jTFNewPriority.setBackground(Color.white);
    	}else {
    		jTFNewPriority.setEnabled(false);
    		jTFNewPriority.setBackground(Color.LIGHT_GRAY);
    	}
    }*/

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (Events.valueOf(e.getActionCommand())) {
		case CLEAR_PROCESS_FORM:
			cleanProcessForm();
			break;
		case CLEAR_PARTITION_FORM:
			cleanPartitionForm();
			break;
		default:
			break;
		}
		
	}
}
