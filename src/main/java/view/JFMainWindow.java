package view;

import javax.swing.*;

import controller.Controller;
import models.Process;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class JFMainWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanelWest jPanelWest;
	private JPanelEast jPanelEast;
	private JPanelSouth jPanelSouth;
	private Controller controller;

	public JFMainWindow(Controller controller) {
		this.controller = controller;
		getContentPane().setBackground(Color.decode("#333333"));
		setUIManager();
		setTitle(Constraints.TITLE);
		setSize((int)(getToolkit().getScreenSize().getWidth()*0.8),(int)(getToolkit().getScreenSize().getHeight()*0.45));
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		setLayout(new BorderLayout());
		initComponents(controller);
		addWindowsListenerOption();
		cleanForm();
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

	public void setUIManager() {
		UIManager.put("Label.foreground", Color.black);
		UIManager.put("Label.font", new Font("Helvetiva", Font.PLAIN,20));
		UIManager.put("TextField.font", new Font("Helvetiva", Font.PLAIN,20));
		UIManager.put("TextField.border", BorderFactory.createEmptyBorder());
		UIManager.put("Button.font", new Font("Helvetiva", Font.PLAIN,16));
		//UIManager.put("Button.foreground", Color.WHITE);
		//UIManager.put("Button.border", BorderFactory.createEmptyBorder());
	}

	public void cleanForm(){
		jPanelWest.cleanAll();
	}

	public String getNameProcess(){
		return jPanelWest.getNameProcess();
	}

	public int getTimeProcess(){
		return jPanelWest.getTimeProcess();
	}

	public boolean isBlockedProcess(){
		return jPanelWest.isBlocked();
	}

	public void addProcessInTable(Process process){
		jPanelEast.addProcess(process,controller);
	}

	public void clearTable(){
		jPanelEast.cleanTable();
	}
	
	
}
