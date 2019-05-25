package view;

import models.Process;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import controller.Controller;

public class JPanelEast extends JPanel {

	private static final long serialVersionUID = 1L;
	private DefaultTableModel model;
    private JTable jTVideoList;
    private JScrollPane jScrollPaneVideoList;

    public JPanelEast(){
        setLayout(new BorderLayout());
        model = new DefaultTableModel();
        model.setColumnIdentifiers(Constraints.TABLE_HEADER);
        jTVideoList = new JTable(model);
        jTVideoList.setShowHorizontalLines(false);
        jTVideoList.setShowVerticalLines(false);
        jTVideoList.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        jTVideoList.getColumnModel().getColumn(0).setPreferredWidth((int)(100));
        jTVideoList.getColumnModel().getColumn(1).setPreferredWidth((int)(100));
        jTVideoList.getColumnModel().getColumn(2).setPreferredWidth((int)(100));
        jTVideoList.getColumnModel().getColumn(3).setPreferredWidth((int)(100));
        jTVideoList.setForeground(Color.decode("#333333"));
        jTVideoList.setBackground(Color.decode("#E6E6E6"));
        jTVideoList.setFont(new Font("Tahoma", Font.PLAIN, 18));
        JTableHeader header = jTVideoList.getTableHeader();
        header.setFont(new Font("Tahoma", Font.PLAIN, 16));
        header.setBackground(new Color(24, 24, 24));
        header.setForeground(Color.WHITE);
        header.setBorder(BorderFactory.createLineBorder(new Color(24, 24, 24)));
        jScrollPaneVideoList = new JScrollPane(jTVideoList);
        jScrollPaneVideoList.getVerticalScrollBar().setBackground(new Color(24, 24, 24));
        jScrollPaneVideoList.setBackground(new Color(24, 24, 24));
        jScrollPaneVideoList.getViewport().setBackground(Color.WHITE);
        add(jScrollPaneVideoList, BorderLayout.CENTER);
        setVisible(true);
    }

    public void addProcess(Process process, Controller controller){
    	Object[] obj = process.toObject();
    	Object[] set = new Object[]{obj[0],obj[1],obj[2], obj[3],obj[4],obj[5],obj[6], obj[7], obj[8]};
        model.addRow(set);
    }

    public void cleanTable(){
        model.setRowCount(0);
    }
    
}
