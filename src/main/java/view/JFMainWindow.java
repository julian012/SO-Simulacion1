package view;

import javax.imageio.ImageIO;
import javax.swing.*;

import controller.Controller;
import models.Partition;
import models.Process;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class JFMainWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanelWest jPanelWest;
	private JPanelEast jPanelEast;
	private JPanelSouth jPanelSouth;
	private Controller controller;
	private BufferedImage image;

	public JFMainWindow(Controller controller) {
		initImages();
		this.controller = controller;
		getContentPane().setBackground(Color.decode("#E6E6FA"));
		setUIManager();
		setIconImage(image);
		setTitle(Constraints.TITLE);
		this.setExtendedState(MAXIMIZED_BOTH);
		setSize((int)(getToolkit().getScreenSize().getWidth()),(int)(getToolkit().getScreenSize().getHeight()));
		setLocationRelativeTo(null);
		setResizable(false);
		setUndecorated(true);
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		setLayout(new BorderLayout());
		initComponents(controller);
		addWindowsListenerOption();
		cleanPartitionForm();
		cleanProcessForm();
	}
	
	public void initImages() {
		try {
			image = ImageIO.read(getClass().getResource("/Img/Icon.png"));
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	public void initComponents(Controller controller){
		jPanelWest = new JPanelWest(controller);
		jPanelSouth = new JPanelSouth();
		jPanelEast = new JPanelEast();
		this.add(jPanelWest, BorderLayout.WEST);
		this.add(jPanelSouth, BorderLayout.SOUTH);
		this.add(jPanelEast, BorderLayout.CENTER);
	}

	private void addWindowsListenerOption(){
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				close();
			}
		});
	}

	public void close(){
		if (JOptionPane.showConfirmDialog(this, "¿Desea realmente salir del sistema?",
				"Salir del sistema", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
			System.exit(0);
	}
	
	public void minimize() {
		setExtendedState(ICONIFIED);
	}

	public void setUIManager() {
		UIManager.put("Label.foreground", Color.black);
		UIManager.put("Label.font", new Font("Helvetiva", Font.PLAIN,20));
		UIManager.put("TextField.font", new Font("Helvetiva", Font.PLAIN,20));
		UIManager.put("TextField.border", BorderFactory.createEmptyBorder());
		UIManager.put("Button.font", new Font("Helvetiva", Font.PLAIN,16));
		//UIManager.put("Button.foreground", Color.WHITE);
		//UIManager.put("Button.border", BorderFactory.createEmptyBorder());
	}

	public void cleanProcessForm(){
		jPanelWest.cleanProcessForm();
	}
	
	public void cleanPartitionForm() {
		jPanelWest.cleanPartitionForm();
	}

	public String getNameProcess(){
		return jPanelWest.getNameProcess();
	}

	public int getTimeProcess(){
		return jPanelWest.getTimeProcess();
	}

	public int getSizeProcess(){
		return jPanelWest.getSizeProcess();
	}
	

    public String getPartitionName() {
    	return jPanelWest.getPartitionName();
    }
    
    public int getPartitionSize() {
    	return jPanelWest.getPartitionSize();
    }
    
	public void addProcessInTable(Process process){
		jPanelEast.addProcess(process,controller);
	}
	
	public void addPartitionInTable(Partition partition) {
		jPanelWest.loadPartitions(partition.getPartitionName());
		jPanelEast.addPartition(partition, controller);
		jPanelWest.validatePartitionList();
	}
	
	public void validateComboBoxPartition() {
		jPanelWest.validatePartitionList();
	}

	public void clearTableProcess(){
		jPanelEast.cleanTableProcess();
	}
	
	public void clearTablePartition(){
		jPanelEast.cleanTablePartition();
	}
	
	public String getSelectedPartition() {
		return jPanelWest.getSelectedPartition();
	}
	
	
}
