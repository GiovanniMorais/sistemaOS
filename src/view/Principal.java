package view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.DAO;

public class Principal extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	DAO dao = new DAO();
	private Connection con;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal frame = new Principal();
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
	public Principal() {
		setTitle("MEKANIX ELEVADORES");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Principal.class.getResource("/img/note.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 745, 427);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		JButton btnusuarios = new JButton("");
		btnusuarios.setBackground(Color.LIGHT_GRAY);
		btnusuarios.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnusuarios.setIcon(new ImageIcon(Principal.class.getResource("/img/users.png")));
		btnusuarios.setToolTipText("Usuarios");
		btnusuarios.setBounds(10, 197, 120, 120);
		btnusuarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//abrir tela de usuarios
				Usuarios usuarios = new Usuarios ();
				usuarios.setVisible(true);
			}
		});
		contentPane.setLayout(null);
		contentPane.add(btnusuarios);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		btnNewButton.setContentAreaFilled(false);
		btnNewButton.setIcon(new ImageIcon(Principal.class.getResource("/img/logo.png")));
		btnNewButton.setBounds(0, 0, 729, 124);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.setToolTipText("Sobre");
		btnNewButton_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Sobre sobre = new Sobre();
				sobre.setVisible(true);
				
			}
		});
		btnNewButton_1.setIcon(new ImageIcon(Principal.class.getResource("/img/about.png")));
		btnNewButton_1.setBounds(684, 344, 45, 44);
		contentPane.add(btnNewButton_1);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.DARK_GRAY);
		panel.setForeground(new Color(0, 120, 215));
		panel.setBounds(0, 0, 729, 124);
		contentPane.add(panel);
		
		JButton btnNewButton_2 = new JButton("");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Cliente cliente = new Cliente();
				cliente.setVisible(true);
			}
		});
		btnNewButton_2.setToolTipText("Clientes");
		btnNewButton_2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton_2.setIcon(new ImageIcon(Principal.class.getResource("/img/usuario-verificado.png")));
		btnNewButton_2.setBounds(140, 197, 120, 120);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("New button");
		btnNewButton_3.setToolTipText("Relatorios");
		btnNewButton_3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton_3.setIcon(new ImageIcon(Principal.class.getResource("/img/relatorio-de-lucro.png")));
		btnNewButton_3.setBounds(270, 197, 120, 120);
		contentPane.add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("");
		btnNewButton_4.setToolTipText("Serviços");
		btnNewButton_4.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton_4.setIcon(new ImageIcon(Principal.class.getResource("/img/elevador.png")));
		btnNewButton_4.setBounds(400, 197, 120, 120);
		contentPane.add(btnNewButton_4);
		
		JButton btnNewButton_5 = new JButton("");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				view.produtos produtos = new produtos();
				produtos.setVisible(true);
			}
		});
		btnNewButton_5.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton_5.setToolTipText("Produtos");
		btnNewButton_5.setIcon(new ImageIcon(Principal.class.getResource("/img/codigo-de-barras.png")));
		btnNewButton_5.setBounds(530, 197, 95, 120);
		contentPane.add(btnNewButton_5);
		
		JButton btnNewButton_6 = new JButton("");
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fornecedores fornecedores = new fornecedores();
				fornecedores.setVisible(true);
			}
		});
		btnNewButton_6.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton_6.setToolTipText("Relatorios");
		btnNewButton_6.setIcon(new ImageIcon(Principal.class.getResource("/img/note.png")));
		btnNewButton_6.setBounds(629, 197, 95, 120);
		contentPane.add(btnNewButton_6);
	}
}
