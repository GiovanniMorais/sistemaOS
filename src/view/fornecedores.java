package view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Iterator;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import model.DAO;
import utils.Validador;

public class fornecedores extends JDialog {

	private static final long serialVersionUID = 1L;
	DAO dao = new DAO();
	private Connection con;
	private PreparedStatement pst;
	private ResultSet rs;

	private JTextField txtID;
	private JTextField txtRazao;
	private JTextField txtFone;
	private JTextField txtCNPJ;
	private JTextField txtEndereco;
	private JTextField txtCEP;
	private JTextField txtNumero;
	private JTextField txtCidade;
	private JTextField txtComplemento;
	private JTextField txtBairro;
	private JComboBox cboUF;
	private JComboBox cboUF_1;
	private JButton btnLimpar;
	private JButton btnEditar;
	private JButton btnAdicionar;
	private JButton btnExcluir;
	private JScrollPane scrollPane00;
	private JList listarUsuarios;
	private JTextField txtFantasia;
	private JTextField txtVendedor;
	private JTextField txtIE;
	private JTextField txtEmail;
	private JTextField txtSite;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_10;
	private JLabel lblNewLabel_12;
	private JLabel lblNewLabel_13;
	private JLabel lblNewLabel_14;
	private JLabel lblNewLabel_11;
	private JLabel lblNewLabel_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					fornecedores dialog = new fornecedores();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the dialog.
	 */
	public fornecedores() {
		getContentPane().setBackground(new Color(255, 255, 255));
		setTitle("Fornecedores");
		setBounds(100, 100, 533, 493);
		getContentPane().setLayout(null);

		scrollPane00 = new JScrollPane();
		scrollPane00.setVisible(false);
		scrollPane00.setBounds(78, 90, 214, 33);
		getContentPane().add(scrollPane00);

		listarUsuarios = new JList();
		listarUsuarios.setDoubleBuffered(true);
		listarUsuarios.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				buscarUsuarioLista();
			}
		});
		scrollPane00.setViewportView(listarUsuarios);

		JLabel lblNewLabel = new JLabel("ID");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		lblNewLabel.setBounds(18, 32, 26, 14);
		getContentPane().add(lblNewLabel);

		txtID = new JTextField();
		txtID.setEditable(false);
		txtID.setBounds(54, 29, 53, 20);
		getContentPane().add(txtID);
		txtID.setColumns(10);

		txtRazao = new JTextField();
		txtRazao.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				listarUsuario();
			}
		});
		txtRazao.setBounds(78, 72, 214, 20);
		getContentPane().add(txtRazao);
		txtRazao.setColumns(10);
		txtRazao.setDocument(new Validador(40));

		txtFone = new JTextField();
		txtFone.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				// digitação...
				// definir caracteres permitidos
				String caracteres = "0123456789.";
				// se for digitado algum caractere não permitido
				if (!caracteres.contains(e.getKeyChar() + "")) {
					// ignorar
					e.consume();
				}

			}
		});
		txtFone.setBounds(348, 103, 144, 20);
		getContentPane().add(txtFone);
		txtFone.setColumns(10);
		txtFone.setDocument(new Validador(15));

		txtCNPJ = new JTextField();
		txtCNPJ.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				// digitação...
				// definir caracteres permitidos
				String caracteres = "0123456789.";
				// se for digitado algum caractere não permitido
				if (!caracteres.contains(e.getKeyChar() + "")) {
					// ignorar
					e.consume();
				}

			}
		});
		txtCNPJ.setBounds(89, 233, 144, 20);
		getContentPane().add(txtCNPJ);
		txtCNPJ.setColumns(10);
		txtCNPJ.setDocument(new Validador(15));

		lblNewLabel_1 = new JLabel("Razão Social");
		lblNewLabel_1.setFont(new Font("Segoe UI Semibold", Font.BOLD, 11));
		lblNewLabel_1.setBounds(10, 74, 104, 14);
		getContentPane().add(lblNewLabel_1);

		lblNewLabel_2 = new JLabel("Fone");
		lblNewLabel_2.setFont(new Font("Segoe UI Semibold", Font.BOLD, 11));
		lblNewLabel_2.setBounds(303, 105, 46, 14);
		getContentPane().add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("CNPJ");
		lblNewLabel_3.setFont(new Font("Segoe UI Semibold", Font.BOLD, 11));
		lblNewLabel_3.setBounds(20, 235, 36, 14);
		getContentPane().add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("Endereço");
		lblNewLabel_4.setFont(new Font("Segoe UI Semibold", Font.BOLD, 11));
		lblNewLabel_4.setBounds(10, 269, 61, 14);
		getContentPane().add(lblNewLabel_4);

		txtEndereco = new JTextField();
		txtEndereco.setBounds(89, 267, 214, 20);
		getContentPane().add(txtEndereco);
		txtEndereco.setColumns(10);

		txtCEP = new JTextField();
		txtCEP.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				// digitação...
				// definir caracteres permitidos
				String caracteres = "0123456789.";
				// se for digitado algum caractere não permitido
				if (!caracteres.contains(e.getKeyChar() + "")) {
					// ignorar
					e.consume();
				}

			}
		});
		txtCEP.setBounds(363, 267, 144, 20);
		getContentPane().add(txtCEP);
		txtCEP.setColumns(10);
		txtCEP.setDocument(new Validador(8));

		JLabel lblNewLabel_5 = new JLabel("CEP");
		lblNewLabel_5.setFont(new Font("Segoe UI Semibold", Font.BOLD, 11));
		lblNewLabel_5.setBounds(313, 269, 36, 14);
		getContentPane().add(lblNewLabel_5);

		JLabel lblNewLabel_6 = new JLabel("Número");
		lblNewLabel_6.setFont(new Font("Segoe UI Semibold", Font.BOLD, 11));
		lblNewLabel_6.setBounds(175, 311, 46, 14);
		getContentPane().add(lblNewLabel_6);

		txtNumero = new JTextField();
		txtNumero.addKeyListener(new KeyAdapter() {

		});
		txtNumero.setBounds(239, 309, 86, 20);
		getContentPane().add(txtNumero);
		txtNumero.setColumns(10);
		txtNumero.setDocument(new Validador(7));

		JLabel lblNewLabel_7 = new JLabel("Cidade");
		lblNewLabel_7.setFont(new Font("Segoe UI Semibold", Font.BOLD, 11));
		lblNewLabel_7.setBounds(346, 311, 46, 14);
		getContentPane().add(lblNewLabel_7);

		txtCidade = new JTextField();
		txtCidade.setBounds(402, 309, 105, 20);
		getContentPane().add(txtCidade);
		txtCidade.setColumns(10);
		txtCidade.setDocument(new Validador(30));

		txtComplemento = new JTextField();
		txtComplemento.setBounds(91, 352, 109, 20);
		getContentPane().add(txtComplemento);
		txtComplemento.setColumns(10);
		txtComplemento.setDocument(new Validador(25));

		JLabel lblNewLabel_8 = new JLabel("Complemento");
		lblNewLabel_8.setFont(new Font("Segoe UI Semibold", Font.BOLD, 11));
		lblNewLabel_8.setBounds(5, 354, 76, 14);
		getContentPane().add(lblNewLabel_8);

		cboUF_1 = new JComboBox();
		cboUF_1.setFont(new Font("Segoe UI Semibold", Font.BOLD, 11));
		cboUF_1.setModel(new DefaultComboBoxModel(
				new String[] { "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA", "PB",
						"PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO" }));
		cboUF_1.setBounds(239, 350, 54, 22);
		getContentPane().add(cboUF_1);

		JButton btnbuscarCep = new JButton("BuscarCEP");
		btnbuscarCep.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buscarCep();
			}
		});
		btnbuscarCep.setBounds(376, 233, 116, 23);
		getContentPane().add(btnbuscarCep);

		JLabel lblNewLabel_9 = new JLabel("Bairro");
		lblNewLabel_9.setFont(new Font("Segoe UI Semibold", Font.BOLD, 11));
		lblNewLabel_9.setBounds(10, 311, 46, 14);
		getContentPane().add(lblNewLabel_9);

		txtBairro = new JTextField();
		txtBairro.setBounds(60, 309, 105, 20);
		getContentPane().add(txtBairro);
		txtBairro.setColumns(10);
		txtBairro.setDocument(new Validador(40));

		btnLimpar = new JButton("");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limparCampos();
			}
		});
		btnLimpar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnLimpar.setContentAreaFilled(false);
		btnLimpar.setBorder(null);
		btnLimpar.setToolTipText("Limpar Campos");
		btnLimpar.setIcon(new ImageIcon(fornecedores.class.getResource("/img/eraser.png")));
		btnLimpar.setBounds(161, 383, 80, 60);
		getContentPane().add(btnLimpar);

		btnEditar = new JButton("");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editarContato();
			}
		});
		btnEditar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnEditar.setContentAreaFilled(false);
		btnEditar.setBorder(null);
		btnEditar.setToolTipText("Editar Fornecedor");
		btnEditar.setIcon(new ImageIcon(fornecedores.class.getResource("/img/editar.png")));
		btnEditar.setBounds(251, 383, 80, 60);
		getContentPane().add(btnEditar);

		btnAdicionar = new JButton("");
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adicionarUsuario();
			}
		});
		btnAdicionar.setContentAreaFilled(false);
		btnAdicionar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAdicionar.setBorder(null);
		btnAdicionar.setToolTipText("Adicionar Fornecedor");
		btnAdicionar
				.setIcon(new ImageIcon(fornecedores.class.getResource("/img/adicionar.png")));
		btnAdicionar.setBounds(348, 383, 80, 60);
		getContentPane().add(btnAdicionar);

		btnExcluir = new JButton("");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				excluirContato();
			}
		});
		btnExcluir.setToolTipText("Excluir Fornecedor");
		btnExcluir.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnExcluir.setContentAreaFilled(false);
		btnExcluir.setBorder(null);
		btnExcluir.setIcon(
				new ImageIcon(fornecedores.class.getResource("/img/excluir.png")));
		btnExcluir.setBounds(427, 383, 80, 60);
		getContentPane().add(btnExcluir);

		txtFantasia = new JTextField();
		txtFantasia.setBounds(66, 138, 202, 20);
		getContentPane().add(txtFantasia);
		txtFantasia.setColumns(10);

		lblNewLabel_10 = new JLabel("Fantasia");
		lblNewLabel_10.setFont(new Font("Segoe UI Semibold", Font.BOLD, 11));
		lblNewLabel_10.setBounds(10, 140, 46, 14);
		getContentPane().add(lblNewLabel_10);

		lblNewLabel_11 = new JLabel("Vendedor");
		lblNewLabel_11.setBounds(292, 141, 57, 14);
		getContentPane().add(lblNewLabel_11);

		txtVendedor = new JTextField();
		txtVendedor.setBounds(358, 138, 123, 20);
		getContentPane().add(txtVendedor);
		txtVendedor.setColumns(10);

		lblNewLabel_12 = new JLabel("IE (Inscrição Estadual)");
		lblNewLabel_12.setFont(new Font("Segoe UI Semibold", Font.BOLD, 11));
		lblNewLabel_12.setBounds(16, 169, 128, 14);
		getContentPane().add(lblNewLabel_12);

		txtIE = new JTextField();
		txtIE.setBounds(154, 167, 149, 20);
		getContentPane().add(txtIE);
		txtIE.setColumns(10);
		txtIE.setDocument(new Validador(15));

		lblNewLabel_13 = new JLabel("E-mail");
		lblNewLabel_13.setFont(new Font("Segoe UI Semibold", Font.BOLD, 11));
		lblNewLabel_13.setBounds(20, 200, 46, 14);
		getContentPane().add(lblNewLabel_13);

		txtEmail = new JTextField();
		txtEmail.setBounds(64, 198, 168, 20);
		getContentPane().add(txtEmail);
		txtEmail.setColumns(10);

		lblNewLabel_14 = new JLabel("Site");
		lblNewLabel_14.setFont(new Font("Segoe UI Semibold", Font.BOLD, 11));
		lblNewLabel_14.setBounds(257, 200, 46, 14);
		getContentPane().add(lblNewLabel_14);

		txtSite = new JTextField();
		txtSite.setBounds(288, 198, 165, 20);
		getContentPane().add(txtSite);
		txtSite.setColumns(10);

	}

	private void buscarCep() {
		String logradouro = "";
		String tipoLogradouro = "";
		String resultado = null;
		String cep = txtCEP.getText();
		try {
			URL url = new URL("http://cep.republicavirtual.com.br/web_cep.php?cep=" + cep + "&formato=xml");
			SAXReader xml = new SAXReader();
			Document documento = xml.read(url);
			Element root = documento.getRootElement();
			for (Iterator<Element> it = root.elementIterator(); it.hasNext();) {
				Element element = it.next();
				if (element.getQualifiedName().equals("cidade")) {
					txtCidade.setText(element.getText());
				}
				if (element.getQualifiedName().equals("bairro")) {
					txtBairro.setText(element.getText());
				}
				if (element.getQualifiedName().equals("uf")) {
					cboUF_1.setSelectedItem(element.getText());
				}
				if (element.getQualifiedName().equals("tipo_logradouro")) {
					tipoLogradouro = element.getText();
				}
				if (element.getQualifiedName().equals("logradouro")) {
					logradouro = element.getText();
				}
				if (element.getQualifiedName().equals("resultado")) {
					resultado = element.getText();
					if (resultado.equals("1")) {
						System.out.println("OK");
					} else {
						JOptionPane.showMessageDialog(null, "CEP não encontrado");
					}
				}
			}
			txtEndereco.setText(tipoLogradouro + " " + logradouro);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	private void excluirContato() {
		int confirma = JOptionPane.showConfirmDialog(null, "Confirma a exclusão deste fornecedor?", "Atenção!",
				JOptionPane.YES_NO_OPTION);
		if (confirma == JOptionPane.YES_OPTION) {
			String delete = "delete from fornecedores where idfornecedor=?";
			try {
				con = dao.conectar();
				pst = con.prepareStatement(delete);
				pst.setString(1, txtID.getText());
				pst.executeUpdate();
				limparCampos();

				JOptionPane.showMessageDialog(null, "Fornecedor excluído");
				con.close();
			} catch (java.sql.SQLIntegrityConstraintViolationException e1) {
				JOptionPane.showMessageDialog(null, "Não foi Possível Excluir o Fornecedor!\nHá um Serviço Pendente.");
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}

	private void editarContato() {
		if (txtRazao.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Digite o nome do fornecedor");
			txtRazao.requestFocus();
		} else {
			String update = "update fornecedores set razao=?, fantasia=?, vendedor=?, cnpj=?, ie=?, email=?, site=?, fone=?, cep=?, endereco=?, bairro=?, complemento=?, numero=?, cidade=?, uf=? WHERE idfornecedor=? ";
			try {
				con = dao.conectar();
				pst = con.prepareStatement(update);
				pst.setString(1, txtRazao.getText());
				pst.setString(2, txtFantasia.getText());
				pst.setString(3, txtVendedor.getText());
				pst.setString(4, txtCNPJ.getText());
				pst.setString(5, txtIE.getText());
				pst.setString(6, txtEmail.getText());
				pst.setString(7, txtSite.getText());
				pst.setString(8, txtFone.getText());
				pst.setString(9, txtCEP.getText());
				pst.setString(10, txtEndereco.getText());
				pst.setString(11, txtBairro.getText());
				pst.setString(12, txtComplemento.getText());
				pst.setString(13, txtNumero.getText());
				pst.setString(14, txtCidade.getText());
				pst.setString(15, cboUF_1.getSelectedItem().toString());
				pst.setString(16, txtID.getText());

				pst.executeUpdate();
				limparCampos();

				JOptionPane.showMessageDialog(null, "Dados do fornecedor editados com sucesso");
			} catch (java.sql.SQLIntegrityConstraintViolationException e) {
				JOptionPane.showMessageDialog(null, "CNPJ Existente");
				txtCNPJ.setText(null);
				txtCNPJ.requestFocus();
			} catch (Exception e1) {

				System.out.println(e1);

			}
		}
	}

	private void limparCampos() {
		txtID.setText(null);
		txtRazao.setText(null);
		txtVendedor.setText(null);
		txtEndereco.setText(null);
		txtCNPJ.setText(null);
		txtIE.setText(null);
		txtEmail.setText(null);
		txtSite.setText(null);
		txtFantasia.setText(null);
		txtCEP.setText(null);
		txtNumero.setText(null);
		txtComplemento.setText(null);
		txtBairro.setText(null);
		txtCidade.setText(null);
		txtFone.setText(null);
		cboUF_1.setSelectedItem(null);
	}

	private void adicionarUsuario() {
		// Criar uma variável/objeto para capturar a senha

		// System.out.println("teste");
		// validação de campos obrigatórios
		if (txtRazao.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o nome do fornecedor");
			txtRazao.requestFocus();
		} else if (txtFone.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o telefone do fornecedor");
			txtFone.requestFocus();
		} else if (txtCNPJ.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o CNPJ do fornecedor");
			txtCNPJ.requestFocus();
		} else if (txtCEP.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o CEP do fornecedor");
			txtCEP.requestFocus();
		} else if (txtCidade.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha a cidade do fornecedor");
			txtCidade.requestFocus();
		} else if (txtNumero.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o número do fornecedor");
			txtNumero.requestFocus();
		} else {
			// lógica principal
			// CRUD Create
			String create = "insert into fornecedores(razao,fantasia,vendedor,cnpj,ie,email,site,fone,cep,endereco,bairro,complemento,numero,cidade,uf) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			// tratamento de exceções
			try {
				// abrir a conexão
				con = dao.conectar();
				// preparar a execução da query(instrução sql - CRUD Create)
				pst = con.prepareStatement(create);
				pst.setString(1, txtRazao.getText());
				pst.setString(2, txtFantasia.getText());
				pst.setString(3, txtVendedor.getText());
				pst.setString(4, txtCNPJ.getText());
				pst.setString(5, txtIE.getText());
				pst.setString(6, txtEmail.getText());
				pst.setString(7, txtSite.getText());
				pst.setString(8, txtFone.getText());
				pst.setString(9, txtCEP.getText());
				pst.setString(10, txtEndereco.getText());
				pst.setString(11, txtBairro.getText());
				pst.setString(12, txtComplemento.getText());
				pst.setString(13, txtNumero.getText());
				pst.setString(14, txtCidade.getText());
				pst.setString(15, cboUF_1.getSelectedItem().toString());

				// executa a query(instrução sql (CRUD - Create))
				pst.executeUpdate();
				// confirmar
				JOptionPane.showMessageDialog(null, "Fornecedor adicionado");
				// limpar os campos
				limparCampos();
				// fechar a conexão
				con.close();
			} catch (java.sql.SQLIntegrityConstraintViolationException e) {
				JOptionPane.showMessageDialog(null, "CNPJ Existente");
				txtCNPJ.setText(null);
				txtCNPJ.requestFocus();
			} catch (Exception e1) {

				System.out.println(e1);

			}

		}
	}

	private void listarUsuario() {
		// System.out.println("teste");
		// a linha abaixo cria um objeto usando como referencia um vetor dinamico,este
		// objeto ira temporariamente armazenar os nomes.
		DefaultListModel<String> modelo = new DefaultListModel<>();
		// setar o modelo (vetor na lista)
		listarUsuarios.setModel(modelo);
		// Query (instrução sql)
		String readlista = "select * from fornecedores where razao like '" + txtRazao.getText() + "%'"
				+ "order by razao";
		try {
			// abrir conexão
			con = dao.conectar();
			// preparar a query (instrução sql)
			pst = con.prepareStatement(readlista);
			// executar a query e trazer o resultado para a lista
			rs = pst.executeQuery();
			// uso do while para trazer os usuarios enquanto existir
			while (rs.next()) {
				// mostrar a barra de rolagem (scrollpane)
				scrollPane00.setVisible(true);
				// adicionar os usuarios no vetor -> lista
				modelo.addElement(rs.getString(2));
				// esconder a lista se nenhuma letra for digitada
				if (txtRazao.getText().isEmpty()) {
					scrollPane00.setVisible(false);
				}

			}
			// fechar a conexão
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	private void buscarUsuarioLista() {
		// System.out.println("teste");
		// variavel que captura o indice da linha da lista
		int linha = listarUsuarios.getSelectedIndex();
		if (linha >= 0) {
			// Query (instrução sql)
			// limit (0,1) -> seleciona o indice 0 e 1 usuario da lista
			String readlistaUsuario = "select * from fornecedores where razao like '" + txtRazao.getText() + "%'"
					+ "order by razao limit " + (linha) + " , 1";
			try {
				// abrir a conexão
				con = dao.conectar();
				// preparar a query para a execução
				pst = con.prepareStatement(readlistaUsuario);
				// executar e obter o resultado
				rs = pst.executeQuery();
				if (rs.next()) {
					// esconder a linha
					scrollPane00.setVisible(false);
					// setar os campos
					txtID.setText(rs.getString(1));
					txtRazao.setText(rs.getString(2));
					txtFantasia.setText(rs.getString(3));
					txtCNPJ.setText(rs.getString(4));
					txtNumero.setText(rs.getString(5));
					txtComplemento.setText(rs.getString(6));
					txtCEP.setText(rs.getString(7));
					txtBairro.setText(rs.getString(8));
					txtCidade.setText(rs.getString(9));
					cboUF_1.setSelectedItem(rs.getString(10));
					txtEmail.setText(rs.getString(11));
					txtVendedor.setText(rs.getString(12));
					txtIE.setText(rs.getString(13));
					txtSite.setText(rs.getString(14));
					txtFone.setText(rs.getString(15));
					txtEndereco.setText(rs.getString(16));

				} else {
					JOptionPane.showMessageDialog(null, "Fornecedor inexistente");

				}
				// fechar a conexão
				con.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		} else {
			// se não existir no banco um usuario da lista
			scrollPane00.setVisible(false);

		}
	}
}
