package view;

import java.awt.Cursor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import model.DAO;
import utils.Validador;

public class Usuarios extends JDialog {

	/**
	 * 
	 */
	DAO dao = new DAO();
	private Connection con;
	private PreparedStatement pst;
	private ResultSet rs;

	private static final long serialVersionUID = 1L;
	private JTextField txtid;
	private JTextField txtnome;
	private JTextField txtlogin;
	private JPasswordField txtsenha;
	private JButton btnAdicionar;
	private JButton btnPesquisar;
	private JButton btnExcluir;
	private JButton btnEditar;
	private JList ListaUsuario;
	private JScrollPane scrollPaneUsuario;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Usuarios dialog = new Usuarios();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Usuarios() {
		setTitle("Tela de Login");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Usuarios.class.getResource("/img/note.png")));
		setBounds(100, 100, 537, 369);
		getContentPane().setLayout(null);

		scrollPaneUsuario = new JScrollPane();
		scrollPaneUsuario.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				BuscarUsuarioLista();
			}
		});
		scrollPaneUsuario.setBounds(56, 77, 211, 77);
		getContentPane().add(scrollPaneUsuario);

		ListaUsuario = new JList();
		scrollPaneUsuario.setViewportView(ListaUsuario);
		{
			JLabel lblNewLabel = new JLabel("ID");
			lblNewLabel.setBounds(10, 10, 24, 25);
			getContentPane().add(lblNewLabel);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("Nome");
			lblNewLabel_1.setBounds(10, 61, 46, 14);
			getContentPane().add(lblNewLabel_1);
		}
		{
			JLabel lblNewLabel_2 = new JLabel("Senha");
			lblNewLabel_2.setBounds(0, 113, 46, 14);
			getContentPane().add(lblNewLabel_2);
		}
		{
			JLabel lblNewLabel_3 = new JLabel("Login");
			lblNewLabel_3.setBounds(197, 15, 46, 14);
			getContentPane().add(lblNewLabel_3);
		}

		txtid = new JTextField();
		txtid.setBounds(44, 12, 86, 20);
		getContentPane().add(txtid);
		txtid.setColumns(10);
		txtid.setDocument(new Validador(5));

		txtnome = new JTextField();
		txtnome.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				ListarUsuarios();

			}
		});
		txtnome.setBounds(56, 58, 211, 20);
		getContentPane().add(txtnome);
		txtnome.setColumns(10);
		txtnome.setDocument(new Validador(30));

		txtlogin = new JTextField();
		txtlogin.setBounds(253, 12, 157, 20);
		getContentPane().add(txtlogin);
		txtlogin.setColumns(10);
		txtlogin.setDocument(new Validador(20));

		btnPesquisar = new JButton("");
		btnPesquisar.setBounds(277, 46, 41, 41);
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buscar();
			}
		});
		btnPesquisar.setContentAreaFilled(false);
		btnPesquisar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnPesquisar.setIcon(new ImageIcon(Usuarios.class.getResource("/img/pesquisar.png")));
		getContentPane().add(btnPesquisar);

		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.setBounds(10, 165, 63, 59);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limparCampos();
			}
		});
		btnNewButton_1.setIcon(new ImageIcon(Usuarios.class.getResource("/img/eraser.png")));
		btnNewButton_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton_1.setContentAreaFilled(false);
		getContentPane().add(btnNewButton_1);

		txtsenha = new JPasswordField();
		txtsenha.setBounds(56, 110, 157, 20);
		getContentPane().add(txtsenha);
		txtsenha.setDocument(new Validador(15));
		// substituir o click pela tecla <ENTER> em um botao
		getRootPane().setDefaultButton(btnPesquisar);

		btnAdicionar = new JButton("");
		btnAdicionar.setBounds(217, 165, 65, 59);
		btnAdicionar.setEnabled(false);
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adicionar();
			}
		});
		btnAdicionar.setContentAreaFilled(false);
		btnAdicionar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAdicionar.setIcon(new ImageIcon(Usuarios.class.getResource("/img/adicionar.png")));
		getContentPane().add(btnAdicionar);

		btnEditar = new JButton("");
		btnEditar.setBounds(112, 165, 72, 59);
		btnEditar.setEnabled(false);
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editarContato();
			}
		});
		btnEditar.setContentAreaFilled(false);
		btnEditar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnEditar.setHideActionText(true);
		btnEditar.setIcon(new ImageIcon(Usuarios.class.getResource("/img/editar.png")));
		getContentPane().add(btnEditar);

		btnExcluir = new JButton("");
		btnExcluir.setBounds(306, 165, 78, 67);
		btnExcluir.setEnabled(false);
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				excluirContato();
			}
		});
		btnExcluir.setContentAreaFilled(false);
		btnExcluir.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnExcluir.setIcon(new ImageIcon(Usuarios.class.getResource("/img/lixeira-de-reciclagem.png")));
		getContentPane().add(btnExcluir);
	}

	private void limparCampos() {
		txtid.setText(null);
		txtnome.setText(null);
		txtlogin.setText(null);
		txtsenha.setText(null);
		btnEditar.setEnabled(false);
		btnExcluir.setEnabled(false);
		btnPesquisar.setEnabled(true);
		btnAdicionar.setEnabled(false);
	}

	/**
	 * Método para buscar um contato pelo nome
	 */
	private void buscar() {
		// dica - testar o evento primeiro
		// System.out.println("teste do botão buscar");

		// Criar uma variável com a query (instrução do banco)
		String read = "select * from usuarios where login = ?";
		// tratamento de exceções
		try {
			// abrir a conexão
			con = dao.conectar();
			// preparar a execução da query(instrução sql - CRUD Read)
			// O parâmetro 1 substitui a ? pelo conteúdo da caixa de texto
			pst = con.prepareStatement(read);
			pst.setString(1, txtlogin.getText());
			// executar a query e buscar o resultado
			rs = pst.executeQuery();
			// uso da estrura if else para verificar se existe o contato
			// rs.next() -> se existir um contato no banco
			if (rs.next()) {
				// preencher as caixas de texto com as informações
				txtid.setText(rs.getString(1)); // 1º campo da tabela ID
				txtnome.setText(rs.getString(2)); // 3º campo (Fone)
				txtlogin.setText(rs.getString(3)); // 4º campo (Email)
				txtsenha.setText(rs.getString(4));
				btnExcluir.setEnabled(true);
				btnEditar.setEnabled(true);
				btnPesquisar.setEnabled(true);

			} else {
				// se não existir um contato no banco
				JOptionPane.showMessageDialog(null, "Usuario inexistente");
				btnAdicionar.setEnabled(true);
				btnPesquisar.setEnabled(false);
			}
			// fechar a conexão (IMPORTANTE!)
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/**
	 *
	 */
	private void adicionar() {
		// System.out.println("teste");
		// validação de campos obrigatórios
		if (txtnome.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o nome do usuario");
			txtnome.requestFocus();
		} else if (txtlogin.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o login");
			txtnome.requestFocus();
		} else if (txtsenha.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha a senha do usuario");
			txtsenha.requestFocus();
		} else {
			// logica principal
			// CRUD Create
			String create = "insert into usuarios(nome,login,senha) values (?,?,?)";
			// tratamento de exceções
			try {
				// abrir a conexão
				con = dao.conectar();
				// preparar a execução da query(instrução sql - CRUD Create)
				pst = con.prepareStatement(create);
				pst.setString(1, txtnome.getText());
				pst.setString(2, txtlogin.getText());
				pst.setString(3, txtsenha.getText());
				// executa a query(instrução sql (CRUD - Create))
				pst.executeUpdate();
				// confirmar
				JOptionPane.showMessageDialog(null, "usuario adicionado");
				// Limpar os Campos
				limparCampos();
				btnPesquisar.setEnabled(true);
				btnAdicionar.setEnabled(false);
				// fechar a conexão
				con.close();
			} catch (Exception e) {
				System.out.println(e);

			}
		}
	}

	private void editarContato() {
		if (txtnome.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Digite o nome");
			txtnome.requestFocus();
		} else if (txtlogin.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Digite o login");
			txtlogin.requestFocus();
		} else if (txtsenha.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha a senha do usuario");
			txtsenha.requestFocus();
		} else {
			String update = "update usuarios set nome=?,login=?,senha=? where id=?";
			try {
				con = dao.conectar();
				pst = con.prepareStatement(update);
				pst.setString(1, txtnome.getText());
				pst.setString(2, txtlogin.getText());
				pst.setString(3, txtsenha.getText());
				pst.setString(4, txtid.getText());

				pst.executeUpdate();
				JOptionPane.showMessageDialog(null, "Dados do usuarios editados com sucesso.");
				limparCampos();
				con.close();

			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}

	private void excluirContato() {
		int confirmar = JOptionPane.showConfirmDialog(null, "confirmar a exclusão desse contato?", "Atenção",
				JOptionPane.YES_NO_OPTION);
		if (confirmar == JOptionPane.YES_OPTION) {
			String delete = "delete from usuarios where id=?";

			try {
				con = dao.conectar();
				pst = con.prepareStatement(delete);
				pst.setString(1, txtid.getText());
				pst.executeUpdate();
				limparCampos();
				JOptionPane.showMessageDialog(null, "Usuario Excluido");
				con.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}

	private void BuscarUsuarioLista() {

		int linha = ListaUsuario.getSelectedIndex();
		if (linha > 0) {
			String readlistaUsuario = "select * from usuarios where nome like '" + txtnome.getText() + "%'"
					+ "order by nome limit " + (linha) + ", 1";
			try {

				con = dao.conectar();
				pst = con.prepareStatement(readlistaUsuario);
				rs = pst.executeQuery();
				if (rs.next()) {

					scrollPaneUsuario.setVisible(false);
					txtid.setText(rs.getString(1));
					txtnome.setText(rs.getString(2));
					txtlogin.setText(rs.getString(3));
					txtsenha.setText(rs.getString(4));
				} else {
					JOptionPane.showMessageDialog(null, "Usuário Inexistente");
				}
				con.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		} else {
			scrollPaneUsuario.setVisible(false);

		}

	}

	private void ListarUsuarios() {

		// System.out.println("Teste");
		// a linha abaixo criar um objeto usando como referência um vetor dinâmico, este
		// objeto irá temporariamente armazenar os nomes
		DefaultListModel<String> modelo = new DefaultListModel<>();
		// setar o modelo (vetor na lista)
		ListaUsuario.setModel(modelo);
		// Query (instrução sql)
		String readlista = "select * from usuarios where nome like '" + txtnome.getText() + "%'" + "order by nome";
		try {
			// abrir conexão
			con = dao.conectar();
			// preparar a query (instrução sql)
			pst = con.prepareStatement(readlista);
			// executar a query e trazer o resultado para a lista
			rs = pst.executeQuery();
			// uso do while para trazer os usuarios enquanto existir
			while (rs.next()) {
				// mostrar a barra de rolagem
				scrollPaneUsuario.setVisible(true);
				// adicionar os usuarios no vetor -> lista
				modelo.addElement(rs.getString(2));
				// esconder a lista se nenhuma leta for digitada
				if (txtnome.getText().isEmpty()) {
					scrollPaneUsuario.setVisible(false);
				}

			}
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}

	}

}
