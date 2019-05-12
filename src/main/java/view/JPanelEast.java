package view;

import models.Process;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;

public class JPanelEast extends JPanel {

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
        jTVideoList.setForeground(Color.WHITE);
        jTVideoList.setBackground(new Color(24, 24, 24));
        jTVideoList.setFont(new Font("Tahoma", Font.PLAIN, 12));
        JTableHeader header = jTVideoList.getTableHeader();
        header.setFont(new Font("Tahoma", Font.PLAIN, 16));
        header.setBackground(new Color(24, 24, 24));
        header.setForeground(new Color(93, 93, 93));
        header.setBorder(BorderFactory.createLineBorder(new Color(24, 24, 24)));
        jScrollPaneVideoList = new JScrollPane(jTVideoList);
        jScrollPaneVideoList.getVerticalScrollBar().setBackground(new Color(24, 24, 24));
        jScrollPaneVideoList.setBackground(new Color(24, 24, 24));
        jScrollPaneVideoList.getViewport().setBackground(Color.WHITE);
        add(jScrollPaneVideoList, BorderLayout.CENTER);
        setVisible(true);
    }

    public void addProcess(Process process){
        model.addRow(process.toObject());
    }

    public void cleanTable(){
        model.setRowCount(0);
    }
}
