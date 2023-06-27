package com.farmacia.controlador;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import java.text.DecimalFormat;

public class VentaDao {

	
	public void totalizar(JTable tblVentas,JLabel lblTotalpagar) {
		double t=0;
		double p=0;
		double c=0.0;
		if (tblVentas.getRowCount()>0) {
			for (int i = 0; i < tblVentas.getRowCount(); i++) {
				p=Double.parseDouble(tblVentas.getValueAt(i, 2).toString())*Double.parseDouble(tblVentas.getValueAt(i, 3).toString());
				t+=p;
				lblTotalpagar.setText(getTwoDecimals(String.valueOf(t)));
			}
		}else {
			lblTotalpagar.setText(getTwoDecimals(String.valueOf(c)));
		}
		
	}
	
	private  String getTwoDecimals(String valor){
	    DecimalFormat df = new DecimalFormat("0.00");
	    return df.format(Double.parseDouble(valor));
	}
}
