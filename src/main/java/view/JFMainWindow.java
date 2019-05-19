package view;

import javax.imageio.ImageIO;
import javax.swing.*;

import controller.Controller;
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
		cleanForm();
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
	

    public int getPriority() {
    	return jPanelWest.getPriority();
    }
    
    public int getNewPriority() {
    	return jPanelWest.getNewPriority();
    }
    
    public boolean isDestroy() {
    	return jPanelWest.isDestroy();
    }
    
    public boolean isLayoff() {
    	return jPanelWest.isLayoff();
    }
    
    public String isConnect() {
    	return jPanelWest.isConnect();
    }

	public void addProcessInTable(Process process){
		jPanelEast.addProcess(process,controller);
	}

	public void clearTable(){
		jPanelEast.cleanTable();
	}
	
	
}
