package com.farmacia.form;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.CardLayout;
import javax.swing.JTabbedPane;
import javax.swing.JLayeredPane;
import javax.swing.JDesktopPane;

import java.awt.FlowLayout;
import javax.swing.JButton;
import java.awt.Dimension;

import javax.swing.JList;
import javax.swing.AbstractListModel;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import java.awt.Dimension;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import java.awt.Cursor;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.border.MatteBorder;
import javax.swing.border.SoftBevelBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;


public class Main extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application. 
	 * author : 
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Main() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 997, 441);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnUsuario = new JMenu("Usuario");
		menuBar.add(mnUsuario);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Cerrar sesión");
		mntmNewMenuItem.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mntmNewMenuItem.setIcon(new ImageIcon(Main.class.getResource("/com/farmacia/icon/logout_icon_32.png")));
		mnUsuario.add(mntmNewMenuItem);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);

		contentPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(200, 400));
		panel.setMinimumSize(new Dimension(200, 400));
		contentPane.add(panel);
		panel.setLayout(new FlowLayout(FlowLayout.LEADING, 5, 5));
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1);

		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel middlePanel = new JPanel();
		middlePanel.setOpaque(false);
		middlePanel.setBorder(new EmptyBorder(0, 0, 0, 0));
		middlePanel.setLayout(new FlowLayout(4,4,4));
		
		JPanel gridPanel=new JPanel();
		gridPanel.setBorder(new EmptyBorder(40, 0, 0, 0));
		gridPanel.setLayout(new GridLayout(5,1,5,5));
		
		middlePanel.add(gridPanel);
		
		JButton btnProducto = new JButton("Producto");
		btnProducto.setIcon(new ImageIcon(Main.class.getResource("/com/farmacia/icon/icon-producto.png")));
		btnProducto.setBorder(new LineBorder(new Color(238, 238, 238)));
		btnProducto.setBackground(new Color(233, 233, 233));
		btnProducto.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnProducto.setBackground(new Color(174,214, 241));
			}
			
			public void mouseExited(MouseEvent e) {
				btnProducto.setBackground(new Color(240, 240, 240));
			}
		});
		btnProducto.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		gridPanel.add(btnProducto);
		
		JButton btnUsuario = new JButton("Usuarios");
		btnUsuario.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnUsuario.setSelectedIcon(new ImageIcon(Main.class.getResource("/com/farmacia/icon/users_icon-32.png")));
		btnUsuario.setIcon(new ImageIcon(Main.class.getResource("/com/farmacia/icon/users_icon-48.png")));
		btnUsuario.setBorder(new LineBorder(new Color(238, 238, 238)));
		btnUsuario.setBackground(new Color(233, 233, 233));
		btnUsuario.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnUsuario.setBackground(new Color(174,214, 241));
			}
			
			public void mouseExited(MouseEvent e) {
				btnUsuario.setBackground(new Color(240, 240, 240));
			}
		});
		gridPanel.add(btnUsuario);
		
		JButton btnFactura = new JButton("Facturas");
		btnFactura.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnFactura.setSelectedIcon(new ImageIcon(Main.class.getResource("/com/farmacia/icon/payment_icon_32.png")));
		btnFactura.setIcon(new ImageIcon(Main.class.getResource("/com/farmacia/icon/payment_icon_48.png")));
		btnFactura.setBorder(new LineBorder(new Color(238, 238, 238)));
		btnFactura.setBackground(new Color(233, 233, 233));
		btnFactura.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnFactura.setBackground(new Color(174,214, 241));
			}
			
			public void mouseExited(MouseEvent e) {
				btnFactura.setBackground(new Color(240, 240, 240));
			}
		});
		gridPanel.add(btnFactura);
		
		JButton btnCaja = new JButton("Caja");
		btnCaja.setSelectedIcon(new ImageIcon(Main.class.getResource("/com/farmacia/icon/cashier_icon-32.png")));
		btnCaja.setIcon(new ImageIcon(Main.class.getResource("/com/farmacia/icon/cashier_icon-48.png")));
		btnCaja.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnCaja.setBorder(new LineBorder(new Color(238, 238, 238)));
		btnCaja.setBackground(new Color(233, 233, 233));
		btnCaja.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnCaja.setBackground(new Color(174,214, 241));
			}
			
			public void mouseExited(MouseEvent e) {
				btnCaja.setBackground(new Color(240, 240, 240));
			}
		});
		gridPanel.add(btnCaja);
		
		JButton btnVentas = new JButton("Ventas");
		btnVentas.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnVentas.setSelectedIcon(new ImageIcon(Main.class.getResource("/com/farmacia/icon/compra-icon-34.png")));
		btnVentas.setIcon(new ImageIcon(Main.class.getResource("/com/farmacia/icon/compra-icon-48.png")));
		btnVentas.setBorder(new LineBorder(new Color(238, 238, 238)));
		btnVentas.setBackground(new Color(233, 233, 233));
		btnVentas.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnVentas.setBackground(new Color(174,214, 241));
			}
			
			public void mouseExited(MouseEvent e) {
				btnVentas.setBackground(new Color(240, 240, 240));
			}
		});
		gridPanel.add(btnVentas);
		contentPane.add(middlePanel, BorderLayout.WEST);
		
		JPanel panel1 = new JPanel();
		contentPane.add(panel1, BorderLayout.CENTER);
		panel1.setLayout(new GridLayout(0, 1, 0, 0));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		panel1.add(tabbedPane);
		
		JPanel pnl_producto = new JPanel();
		tabbedPane.addTab("Producto", null, pnl_producto, null);
		
		JPanel pnl_categoria = new JPanel();
		tabbedPane.addTab("Categoria", null, pnl_categoria, null);
		
		JPanel pnl_vendedores = new JPanel();
		tabbedPane.addTab("Vendedores", null, pnl_vendedores, null);
		
		JPanel pnl_proveedor = new JPanel();
		tabbedPane.addTab("Proveedor", null, pnl_proveedor, null);
		
		JPanel pnl_factura = new JPanel();
		tabbedPane.addTab("Factura", null, pnl_factura, null);
		
		JPanel pnl_consultas = new JPanel();
		tabbedPane.addTab("Consultas", null, pnl_consultas, null);
		
		JPanel pnl_ventas = new JPanel();
		tabbedPane.addTab("Ventas", null, pnl_ventas, null);
		
		JPanel pnl_caja = new JPanel();
		tabbedPane.addTab("Caja", null, pnl_caja, null);
		
		

	}
}
