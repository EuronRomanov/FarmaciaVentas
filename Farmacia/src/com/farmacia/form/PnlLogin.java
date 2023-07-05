package com.farmacia.form;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class PnlLogin extends JPanel {
	private Image imagen;
  private String nombre;
  
 
	/**
	 * Create the panel.
	 */
	public PnlLogin() {
         
	}


	
	@Override
	public void paint(Graphics g) {
		imagen=new ImageIcon(getClass().getResource("/com/farmacia/icon/background_login.jpg")).getImage();
		g.drawImage(imagen,0,0,getWidth(),getHeight(),this);
		// TODO Auto-generated method stub
		setOpaque(false);
		super.paint(g);
	}
}
