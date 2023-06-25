package com.farmacia.form;

import java.awt.Component;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class RenderTabla extends DefaultTableCellRenderer {

	
	

	@Override
	public Component getTableCellRendererComponent(JTable table, Object objeto_renderizable, boolean isSelected, boolean hasFocus,
			int row, int column) {
		if (objeto_renderizable instanceof JButton) {
			return (JButton)objeto_renderizable;
		}
		// TODO Auto-generated method stub
		return super.getTableCellRendererComponent(table, objeto_renderizable, isSelected, hasFocus, row, column);
	}
	
	
}
