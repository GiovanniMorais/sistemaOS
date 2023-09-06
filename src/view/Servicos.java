package view;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileOutputStream;
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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import model.DAO;

public class Servicos extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	DAO dao = new DAO();
	private Connection con;
	private PreparedStatement pst;
	private ResultSet rs;
	private JTextField txtOS;
	private JTextField txtData;
	private JTextField txtEquipamento;
	private JTextField txtDefeito;
	private JTextField txtValor;
	private JTextField txtID;
	private JButton btnBuscar;
	private JList listaNome;
	private JScrollPane scrollPaneNome;
	private JTextField txtCliente;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Servicos dialog = new Servicos();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Servicos() {
		setBounds(100, 100, 773, 383);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Cliente", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(355, 70, 211, 117);
		contentPanel.add(panel);
		panel.setLayout(null);

		scrollPaneNome = new JScrollPane();
		scrollPaneNome.setVisible(false);
		scrollPaneNome.setBounds(9, 30, 191, 47);
		panel.add(scrollPaneNome);
		scrollPaneNome.setBorder(null);

		listaNome = new JList();
		scrollPaneNome.setViewportView(listaNome);
		listaNome.setBorder(null);
		listaNome.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				BuscarNomeCliente();
			}
		});

		JLabel lblNewLabel_4 = new JLabel("ID");
		lblNewLabel_4.setBounds(23, 92, 32, 14);
		panel.add(lblNewLabel_4);

		txtID = new JTextField();
		txtID.setEnabled(false);
		txtID.setBounds(61, 89, 140, 20);
		panel.add(txtID);
		txtID.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				ListarCliente();
			}
		});
		txtID.setColumns(10);

		txtCliente = new JTextField();
		txtCliente.addKeyListener(new KeyAdapter() {

			@Override
			public void keyReleased(KeyEvent e) {
				ListarCliente();
			}
		});
		txtCliente.setBounds(10, 11, 191, 20);
		panel.add(txtCliente);
		txtCliente.setColumns(10);

		JLabel lblNewLabel = new JLabel("OS:");
		lblNewLabel.setBounds(23, 22, 46, 14);
		contentPanel.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Data:");
		lblNewLabel_1.setBounds(23, 70, 46, 14);
		contentPanel.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Equipamento:");
		lblNewLabel_2.setBounds(23, 127, 79, 14);
		contentPanel.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Defeito:");
		lblNewLabel_3.setBounds(41, 187, 79, 14);
		contentPanel.add(lblNewLabel_3);

		JLabel lblNewLabel_5 = new JLabel("Valor:");
		lblNewLabel_5.setBounds(23, 245, 46, 14);
		contentPanel.add(lblNewLabel_5);

		JButton btnAdd = new JButton("");
		btnAdd.setContentAreaFilled(false);
		btnAdd.setBorder(null);
		btnAdd.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAdd.setToolTipText("Adicionar");
		btnAdd.setIcon(new ImageIcon(Servicos.class.getResource("/img/adicionar.png")));
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Adicionar();
			}
		});
		btnAdd.setBounds(23, 288, 89, 45);
		contentPanel.add(btnAdd);

		JButton btnEditar = new JButton("");
		btnEditar.setContentAreaFilled(false);
		btnEditar.setBorder(null);
		btnEditar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnEditar.setToolTipText("Editar");
		btnEditar.setIcon(new ImageIcon(Servicos.class.getResource("/img/editar.png")));
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Editar();
			}
		});
		btnEditar.setBounds(177, 288, 89, 45);
		contentPanel.add(btnEditar);

		JButton btnExcluir = new JButton("");
		btnExcluir.setContentAreaFilled(false);
		btnExcluir.setBorder(null);
		btnExcluir.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnExcluir.setToolTipText("Excluir");
		btnExcluir.setIcon(new ImageIcon(Servicos.class.getResource("/img/lixeira-de-reciclagem.png")));
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Excluir();
			}
		});
		btnExcluir.setBounds(305, 288, 89, 45);
		contentPanel.add(btnExcluir);

		txtOS = new JTextField();
		txtOS.setEnabled(false);
		txtOS.setBounds(52, 19, 89, 20);
		contentPanel.add(txtOS);
		txtOS.setColumns(10);

		txtData = new JTextField();
		txtData.setEnabled(false);
		txtData.setBounds(62, 67, 119, 20);
		contentPanel.add(txtData);
		txtData.setColumns(10);

		txtEquipamento = new JTextField();
		txtEquipamento.setBounds(108, 124, 134, 20);
		contentPanel.add(txtEquipamento);
		txtEquipamento.setColumns(10);

		txtDefeito = new JTextField();
		txtDefeito.setBounds(95, 184, 185, 20);
		contentPanel.add(txtDefeito);
		txtDefeito.setColumns(10);

		txtValor = new JTextField();
		txtValor.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				String caracteres = "0123456789.";

				if (!caracteres.contains(e.getKeyChar() + "")) {

					e.consume();
				}
			}
		});
		txtValor.setBounds(79, 242, 86, 20);
		contentPanel.add(txtValor);
		txtValor.setColumns(10);

		btnBuscar = new JButton("");
		btnBuscar.setContentAreaFilled(false);
		btnBuscar.setBorder(null);
		btnBuscar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnBuscar.setToolTipText("Buscar");
		btnBuscar.setIcon(new ImageIcon(Servicos.class.getResource("/img/pesquisar.png")));
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buscar();
			}
		});
		btnBuscar.setBounds(177, 18, 89, 38);
		contentPanel.add(btnBuscar);

		JButton btnLimpar = new JButton("");
		btnLimpar.setContentAreaFilled(false);
		btnLimpar.setBorder(null);
		btnLimpar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnLimpar.setToolTipText("Limpar");
		btnLimpar.setIcon(new ImageIcon(Servicos.class.getResource("/img/eraser.png")));
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Limpar();
			}
		});
		btnLimpar.setBounds(439, 288, 89, 45);
		contentPanel.add(btnLimpar);
	}

	private void buscar() {
		String numOS = JOptionPane.showInputDialog("Número da OS");

		String read = "select * from servicos where os = ?";
		try {
			con = dao.conectar();
			pst = con.prepareStatement(read);
			pst.setString(1, txtOS.getText());
			rs = pst.executeQuery();
			if (rs.next()) {
				txtID.setText(rs.getString(1));
				txtData.setText(rs.getString(2));
				txtEquipamento.setText(rs.getString(3));
				txtDefeito.setText(rs.getString(4));
				txtValor.setText(rs.getString(5));
				txtOS.setText(rs.getString(6));
			} else {
				JOptionPane.showMessageDialog(null, "Serviço Inexistente");
			}
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	private void Limpar() {
		txtID.setText(null);
		txtOS.setText(null);
		txtData.setText(null);
		txtEquipamento.setText(null);
		txtDefeito.setText(null);
		txtValor.setText(null);

	}

	private void Excluir() {
		int confirma = JOptionPane.showConfirmDialog(null, "Confirma a exclusão deste Clienet?", "Atenção!",
				JOptionPane.YES_NO_OPTION);
		if (confirma == JOptionPane.YES_OPTION) {
			// CRUD - Delete
			String delete = "delete from clientes where idcli=?";

			try {
				con = dao.conectar();
				pst = con.prepareStatement(delete);
				pst.setString(1, txtID.getText());
				pst.executeUpdate();
				Limpar();
				JOptionPane.showMessageDialog(null, "Cliente Excluído");
				con.close();
			} catch (Exception e) {
				System.out.println(e);
			}

		}
	}

	private void Editar() {

		if (txtOS.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Digite o OS");
			txtOS.requestFocus();
		} else if (txtData.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Digite a Data");
			txtData.requestFocus();
		} else if (txtEquipamento.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Digite o Equipamento");
			txtEquipamento.requestFocus();
		} else if (txtDefeito.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Digite o Defeito");
			txtDefeito.requestFocus();
		} else if (txtValor.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Digite o Valor");
			txtValor.requestFocus();

		} else {
			String update = "update clientes set OS=?, Data=?,Equipamento=?,Defeito=?,Valor=? where idcli=?";
			try {
				con = dao.conectar();
				pst = con.prepareStatement(update);
				pst.setString(1, txtOS.getText());
				pst.setString(2, txtData.getText());
				pst.setString(3, txtEquipamento.getText());
				pst.setString(4, txtDefeito.getText());
				pst.setString(5, txtValor.getText());
				pst.setString(6, txtID.getText());
				pst.executeUpdate();
				JOptionPane.showInternalMessageDialog(null, "Dados do Serviço editados com Sucesso");
				Limpar();
				con.close();

			} catch (Exception e) {
				System.out.println(e);

			}
		}
	}

	private void Adicionar() {
		if (txtEquipamento.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o Equipamento");
			txtEquipamento.requestFocus();
		} else if (txtDefeito.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o Defeito");
			txtDefeito.requestFocus();
		} else if (txtValor.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o Valor");
			txtValor.requestFocus();
		} else if (txtValor.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o Id do Cliente");
			txtValor.requestFocus();
		} else {
			String create = "insert into clientes(Equipamento,Defeito,Valor,ID) values (?,?,?,?)";

			try {
				con = dao.conectar();
				pst = con.prepareStatement(create);
				pst.setString(1, txtEquipamento.getText());
				pst.setString(2, txtDefeito.getText());
				pst.setString(3, txtValor.getText());
				pst.setString(4, txtID.getText());
				pst.executeUpdate();
				JOptionPane.showMessageDialog(null, "OS Cadastrado");
				Limpar();
				con.close();
			} catch (Exception e) {
				System.out.println(e);

			}

		}

	}

	private void ListarCliente() {
		DefaultListModel<String> modelo = new DefaultListModel<>();
		listaNome.setModel(modelo);

		String readlistaCliente = "select * from clientes where nome like '" + txtID.getText() + "%'" + "order by nome";
		try {
			con = dao.conectar();
			pst = con.prepareStatement(readlistaCliente);
			rs = pst.executeQuery();
			while (rs.next()) {
//				System.out.println("Conexão");
				scrollPaneNome.setVisible(true);
				modelo.addElement(rs.getString(2));
				if (txtCliente.getText().isEmpty()) {
//					System.out.println("Vazio");
					scrollPaneNome.setVisible(false);
				}
			}
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}

	}

	private void BuscarNomeCliente() {
		int linha = listaNome.getSelectedIndex();
		if (linha >= 0) {
			String readlistaCliente = "select * from clientes where nome like '" + txtID.getText() + "%'"
					+ "order by nome limit " + (linha) + " , 1";
			try {
				con = dao.conectar();
				pst = con.prepareStatement(readlistaCliente);
				rs = pst.executeQuery();
				if (rs.next()) {
					scrollPaneNome.setVisible(false);
					txtID.setText(rs.getString(1));
					txtCliente.setText(rs.getString(2));
				} else {
					JOptionPane.showMessageDialog(null, "Cliente Inexistente");
				}
				con.close();
			} catch (Exception e) {
				System.out.println(e);
			}

		} else {
			scrollPaneNome.setVisible(false);
		}
	}

	/**
	 * 
	 * Impressão da OS
	 * 
	 */

	private void imprimirOS() {

		// instanciar objeto para usar os métodos da biblioteca

		Document document = new Document();

		// documento pdf

		try {

			// criar um documento em branco (pdf) de nome clientes.pdf

			PdfWriter.getInstance(document, new FileOutputStream("os.pdf"));

			// abrir o documento (formatar e inserir o conteúdo)

			document.open();

			String readOS = "select * from servicos where os = ?";

			// conexão com o banco

			try {

				// abrir a conexão
				con = dao.conectar();
				// preparar a execução da query (instrução sql)
				pst = con.prepareStatement(readOS);
				pst.setString(1, txtOS.getText());
				// executar a query
				rs = pst.executeQuery();
				// se existir a OS
				if (rs.next()) {

					// document.add(new Paragraph("OS: " + rs.getString(1)));

					Paragraph os = new Paragraph("OS: " + rs.getString(1));
					os.setAlignment(Element.ALIGN_RIGHT);
					document.add(os);
					Paragraph modelo = new Paragraph("Equipamento: " + rs.getString(3));
					modelo.setAlignment(Element.ALIGN_LEFT);
					document.add(modelo);
					Paragraph peca = new Paragraph("Defeito: " + rs.getString(4));
					peca.setAlignment(Element.ALIGN_LEFT);
					document.add(peca);
					Paragraph valor = new Paragraph("Valor: " + rs.getString(5));
					valor.setAlignment(Element.ALIGN_LEFT);
					document.add(valor);
					Paragraph data = new Paragraph("Data: " + rs.getString(2));
					data.setAlignment(Element.ALIGN_LEFT);
					document.add(data);

					// imprimir imagens
					Image imagem = Image.getInstance(Servicos.class.getResource("/img/plantabmw.jpg"));
					// resolução de imagem

					imagem.scaleToFit(500, 350);
					imagem.setAbsolutePosition(10, 330);
					document.add(imagem);

				}

				// fechar a conexão com o banco

				con.close();

			} catch (Exception e) {

				System.out.println(e);

			}

		} catch (Exception e) {

			System.out.println(e);

		}

		// fechar o documento (pronto para "impressão" (exibir o pdf))

		document.close();

		// Abrir o desktop do sistema operacional e usar o leitor padrão

		// de pdf para exibir o documento

		try {

			Desktop.getDesktop().open(new File("os.pdf"));

		} catch (Exception e) {

			System.out.println(e);

		}

	}
}
