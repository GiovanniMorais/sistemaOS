package view;

import java.awt.EventQueue;
import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import model.DAO;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class Login extends JFrame {

	DAO dao = new DAO();
	private Connection con;
	private PreparedStatement pst;
	private ResultSet rs;
	Principal principal = new Principal();
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtlogin;
	private JPasswordField txtsenha2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	public Login() {
		setTitle("MEKANIX - Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 416, 246);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Login:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel.setBounds(10, 25, 46, 14);
		contentPane.add(lblNewLabel);
		
		txtlogin = new JTextField();
		txtlogin.setBounds(66, 22, 199, 20);
		contentPane.add(txtlogin);
		txtlogin.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Senha:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1.setBounds(10, 76, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		JButton btnacessar = new JButton("Acessar");
		btnacessar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				logar();
			}
		});
		btnacessar.setBounds(291, 159, 89, 23);
		contentPane.add(btnacessar);
		
		txtsenha2 = new JPasswordField();
		txtsenha2.setBounds(61, 74, 204, 20);
		contentPane.add(txtsenha2);
	}
	private void logar() {
		String capturaSenha2 = new String(txtsenha2.getPassword());
		
		if (txtlogin.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o login");
			txtlogin.requestFocus();
		} else if (capturaSenha2.length() == 0) {
			JOptionPane.showMessageDialog(null, "Preencha a senha");
			txtsenha2.requestFocus();
		} else {
			String read = "select * from usuarios where login=? and senha=md5(?)";
			try {
				con = dao.conectar();
				pst = con.prepareStatement(read);
				pst.setString(1, txtlogin.getText());
				pst.setString(2, capturaSenha2);
				rs = pst.executeQuery();
				if (rs.next()) {
					
					principal.setVisible(true);
					this.dispose();
				} else {
					JOptionPane.showMessageDialog(null, "usuario e/ou senha esta invalido(s)");
				}
				con.close();
			} catch(Exception e) {
				System.out.println(e);
			}
			
		
		}
	}
}
