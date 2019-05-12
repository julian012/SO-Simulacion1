package view;

import models.Process;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.util.EventObject;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.CellEditorListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

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
        rendererTable();
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
    	Object[] set = new Object[]{obj[0],obj[1],obj[2], obj[3], createButton(controller, String.valueOf(process.getProcessId()))};
    
        model.addRow(set);
    }

    public void cleanTable(){
        model.setRowCount(0);
    }
    
    public JButton createButton(Controller controller, String action){
    	JButton btn = new JButton("Eliminar");
		btn.addActionListener(controller);
		btn.setActionCommand(action);
		btn.setBackground(Color.decode("#DF3A01"));
		btn.setForeground(Color.WHITE);
		btn.setHorizontalAlignment(JLabel.CENTER);
		return btn;
	}
    
    public void rendererTable() {
		jTVideoList.getColumn(Constraints.TABLE_HEADER[4]).setCellRenderer(new TableCellRenderer() {
			
			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
					int row, int column) {
				JButton result =  (JButton)value;
				result.setBorder(BorderFactory.createLineBorder(new Color(24, 24, 24)));
				return result;
			}
		});
		jTVideoList.getColumn(Constraints.TABLE_HEADER[4]).setCellEditor(new TableCellEditor() {
			
			@Override
			public boolean stopCellEditing() {
				return true;
			}
			
			@Override
			public boolean shouldSelectCell(EventObject arg0) {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public void removeCellEditorListener(CellEditorListener arg0) {
			}
			
			@Override
			public boolean isCellEditable(EventObject arg0) {
				// TODO Auto-generated method stub
				return true;
			}
			
			@Override
			public Object getCellEditorValue() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public void cancelCellEditing() {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void addCellEditorListener(CellEditorListener arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
				// TODO Auto-generated method stub
				return (JButton)value;
			}
		});
	}
}
