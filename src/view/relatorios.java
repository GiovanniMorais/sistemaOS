package view;

import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;

import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import model.DAO;

public class relatorios extends JDialog {

	DAO dao = new DAO();
	private Connection con;
	private PreparedStatement pst;
	private ResultSet rs;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton btnClientes;
	private JButton btnServicos;
	private JButton btnNewButton_3;
	private JButton btnNewButton_2;
	private JButton btnNewButton_1;
	private JButton btnNewButton;
	private JButton btnNewButton_4;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					relatorios dialog = new relatorios();
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
	public relatorios() {
		setTitle("Relatórios");
		setResizable(false);
		setModal(true);
		setBounds(100, 100, 800, 600);
		getContentPane().setLayout(null);

		btnServicos = new JButton("");
		btnServicos.setIcon(new ImageIcon(relatorios.class.getResource("/img/elevador.png")));
		btnServicos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				relatorioServicos();

			}
		});
		btnServicos.setContentAreaFilled(false);
		btnServicos.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnServicos.setBorder(null);
		btnServicos.setToolTipText("Serviços");
		btnServicos.setBounds(337, 109, 119, 103);
		getContentPane().add(btnServicos);

		btnClientes = new JButton("");
		btnClientes.setIcon(new ImageIcon(relatorios.class.getResource("/img/usuario-verificado.png")));
		btnClientes.setContentAreaFilled(false);
		btnClientes.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnClientes.setBorder(null);
		btnClientes.setToolTipText("Clientes");
		btnClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				relatorioClientes();
			}
		});
		btnClientes.setBounds(98, 109, 112, 103);
		getContentPane().add(btnClientes);

		btnNewButton = new JButton("");
		btnNewButton.setContentAreaFilled(false);
		btnNewButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VendaPatrimonio();
			}
		});
		btnNewButton.setToolTipText("Relatorio");
		btnNewButton.setBorder(null);
		btnNewButton.setIcon(new ImageIcon(relatorios.class.getResource("/img/relatorio.png")));
		btnNewButton.setBounds(580, 109, 112, 106);
		getContentPane().add(btnNewButton);

		btnNewButton_1 = new JButton("");
		btnNewButton_1.setContentAreaFilled(false);
		btnNewButton_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CustoPatrimonio();
			}
		});
		btnNewButton_1.setToolTipText("Patrimonio");
		btnNewButton_1.setBorder(null);
		btnNewButton_1.setIcon(new ImageIcon(relatorios.class.getResource("/img/taxas-de-habitacao.png")));
		btnNewButton_1.setBounds(98, 284, 112, 106);
		getContentPane().add(btnNewButton_1);

		btnNewButton_2 = new JButton("");
		btnNewButton_2.setContentAreaFilled(false);
		btnNewButton_2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Reposicao();
			}
		});
		btnNewButton_2.setToolTipText("reposiçao");
		btnNewButton_2.setBorder(null);
		btnNewButton_2.setIcon(new ImageIcon(relatorios.class.getResource("/img/pecas-de-reposicao.png")));
		btnNewButton_2.setBounds(344, 284, 112, 106);
		getContentPane().add(btnNewButton_2);

		btnNewButton_3 = new JButton("");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Fornecedores();

			}
		});
		btnNewButton_3.setToolTipText("Fornecedor");
		btnNewButton_3.setBorder(null);
		btnNewButton_3.setContentAreaFilled(false);
		btnNewButton_3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton_3
				.setIcon(new ImageIcon(relatorios.class.getResource("/img/gestao-da-cadeia-de-abastecimento.png")));
		btnNewButton_3.setBounds(580, 284, 112, 106);
		getContentPane().add(btnNewButton_3);

		btnNewButton_4 = new JButton("");
		btnNewButton_4.setToolTipText("Validade");
		btnNewButton_4.setContentAreaFilled(false);
		btnNewButton_4.setBorder(null);
		btnNewButton_4.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton_4.setIcon(new ImageIcon(relatorios.class.getResource("/img/expirado.png")));
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				relatorioValidade();

			}
		});
		btnNewButton_4.setBounds(344, 436, 112, 103);
		getContentPane().add(btnNewButton_4);

	}// fim do construtor

	private void relatorioClientes() {
		// instanciar um objeto para construir a página pdf
		Document document = new Document();
		// configurar como A4 e modo paisagem
		// document.setPageSize(PageSize.A4.rotate());
		// gerar o documento pdf
		try {
			// criar um documento em branco (pdf) de nome clientes.pdf
			PdfWriter.getInstance(document, new FileOutputStream("clientes.pdf"));
			// abrir o documento (formatar e inserir o conteúdo)
			document.open();
			// adicionar a data atual
			Date dataRelatorio = new Date();
			DateFormat formatador = DateFormat.getDateInstance(DateFormat.FULL);
			document.add(new Paragraph(formatador.format(dataRelatorio)));
			// adicionar um páragrafo
			document.add(new Paragraph("Clientes:"));
			document.add(new Paragraph(" ")); // pular uma linha
			// ----------------------------------------------------------
			// query (instrução sql para gerar o relatório de clientes)
			String readClientes = "select nome,fone from clientes order by nome";
			try {
				// abrir a conexão com o banco
				con = dao.conectar();
				// preparar a query (executar a instrução sql)
				pst = con.prepareStatement(readClientes);
				// obter o resultado (trazer do banco de dados)
				rs = pst.executeQuery();
				// atenção uso do while para trazer todos os clientes
				// Criar uma tabela de duas colunas usando o framework(itextPDF)
				PdfPTable tabela = new PdfPTable(2); // (2) número de colunas
				// Criar o cabeçalho da tabela
				PdfPCell col1 = new PdfPCell(new Paragraph("Cliente"));
				PdfPCell col2 = new PdfPCell(new Paragraph("Fone"));
				tabela.addCell(col1);
				tabela.addCell(col2);
				while (rs.next()) {
					// popular a tabela
					tabela.addCell(rs.getString(1));
					tabela.addCell(rs.getString(2));
				} // adicionar a tabela ao documento pdf
				document.add(tabela);
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
			Desktop.getDesktop().open(new File("clientes.pdf"));
		} catch (Exception e) {
			System.out.println(e);
		}

	}

	private void relatorioServicos() {
		// instanciar um objeto para construir a página pdf
		Document document = new Document();
		// configurar como A4 e modo paisagem
		document.setPageSize(PageSize.A4.rotate());
		// gerar o documento pdf
		try {
			// criar um documento em branco (pdf) de nome clientes.pdf
			PdfWriter.getInstance(document, new FileOutputStream("servicos.pdf"));
			// abrir o documento (formatar e inserir o conteúdo)
			document.open();
			// adicionar a data atual
			Date dataRelatorio = new Date();
			DateFormat formatador = DateFormat.getDateInstance(DateFormat.FULL);
			document.add(new Paragraph(formatador.format(dataRelatorio)));
			// adicionar um páragrafo
			document.add(new Paragraph("Servicos:"));
			document.add(new Paragraph(" ")); // pular uma linha
			// ----------------------------------------------------------
			// query (instrução sql para gerar o relatório de clientes)
			String readServicos = "select servicos.os,servicos.dataOS,servicos.equipamento,servicos.defeito,servicos.valor,clientes.nome from servicos inner join clientes on servicos.idcli = clientes.idcli order by os;";
			try {
				// abrir a conexão com o banco
				con = dao.conectar();
				// preparar a query (executar a instrução sql)
				pst = con.prepareStatement(readServicos);
				// obter o resultado (trazer do banco de dados)
				rs = pst.executeQuery();
				// atenção uso do while para trazer todos os clientes
				// Criar uma tabela de duas colunas usando o framework(itextPDF)
				PdfPTable tabela = new PdfPTable(5); // (2) número de colunas
				// Criar o cabeçalho da tabela
				PdfPCell col1 = new PdfPCell(new Paragraph("dataOS"));
				PdfPCell col2 = new PdfPCell(new Paragraph("Equipamento"));
				PdfPCell col3 = new PdfPCell(new Paragraph("Problema"));
				PdfPCell col4 = new PdfPCell(new Paragraph("Valor"));
				PdfPCell col5 = new PdfPCell(new Paragraph("Nome"));
				tabela.addCell(col1);
				tabela.addCell(col2);
				tabela.addCell(col3);
				tabela.addCell(col4);
				tabela.addCell(col5);
				while (rs.next()) {
					// popular a tabela
					tabela.addCell(rs.getString(2));
					tabela.addCell(rs.getString(3));
					tabela.addCell(rs.getString(4));
					tabela.addCell(rs.getString(5));
					tabela.addCell(rs.getString(6));

				}
				// adicionar a tabela ao documento pdf
				document.add(tabela);
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
			Desktop.getDesktop().open(new File("servicos.pdf"));
		} catch (Exception e) {
			System.out.println(e);
		}

	}

	private void Reposicao() {
		// instanciar um objeto para construir a página pdf
		Document document = new Document();
		// configurar como A4 e modo paisagem
		document.setPageSize(PageSize.A4.rotate());
		// gerar o documento pdf
		try {
			// criar um documento em branco (pdf) de nome clientes.pdf
			PdfWriter.getInstance(document, new FileOutputStream("produtos.pdf"));
			// abrir o documento (formatar e inserir o conteúdo)
			document.open();
			// adicionar a data atual
			Date dataRelatorio = new Date();
			DateFormat formatador = DateFormat.getDateInstance(DateFormat.FULL);
			document.add(new Paragraph(formatador.format(dataRelatorio)));
			// adicionar um páragrafo
			document.add(new Paragraph("Produtos:"));
			document.add(new Paragraph("")); // pular uma linha
			// ----------------------------------------------------------
			// query (instrução sql para gerar o relatório de clientes)
			String readProdutos = "select codigo as código,produto,date_format(dataval, '%d/%m/%Y') as validade,\n"
					+ "date_format(dataent, '%d/%m/%Y') as entrada,\n" + "estoque, estoquemin as estoque_mínimo \n"
					+ "from produtos where dataval < dataent";
			try {
				// abrir a conexão com o banco
				con = dao.conectar();
				// preparar a query (executar a instrução sql)
				pst = con.prepareStatement(readProdutos);
				// obter o resultado (trazer do banco de dados)
				rs = pst.executeQuery();
				// atenção uso do while para trazer todos os clientes
				// Criar uma tabela de duas colunas usando o framework(itextPDF)
				PdfPTable tabela = new PdfPTable(6); // (2) número de colunas
				// Criar o cabeçalho da tabela
				PdfPCell col1 = new PdfPCell(new Paragraph("OS"));
				PdfPCell col2 = new PdfPCell(new Paragraph("Produto"));
				PdfPCell col3 = new PdfPCell(new Paragraph("Validade"));
				PdfPCell col4 = new PdfPCell(new Paragraph("Entrada"));
				PdfPCell col5 = new PdfPCell(new Paragraph("Estoque"));
				PdfPCell col6 = new PdfPCell(new Paragraph("Estoque min"));

				tabela.addCell(col1);
				tabela.addCell(col2);
				tabela.addCell(col3);
				tabela.addCell(col4);
				tabela.addCell(col5);
				tabela.addCell(col6);

				while (rs.next()) {
					// popular a tabela
					tabela.addCell(rs.getString(1));
					tabela.addCell(rs.getString(2));
					tabela.addCell(rs.getString(3));
					tabela.addCell(rs.getString(4));
					tabela.addCell(rs.getString(5));
					tabela.addCell(rs.getString(6));
				}
				// adicionar a tabela ao documento pdf
				document.add(tabela);
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
			Desktop.getDesktop().open(new File("produtos.pdf"));
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	private void CustoPatrimonio() {
		// instanciar um objeto para construir a página pdf
		Document document = new Document();
		// configurar como A4 e modo paisagem
		document.setPageSize(PageSize.A4.rotate());
		// gerar o documento pdf
		try {
			// criar um documento em branco (pdf) de nome clientes.pdf
			PdfWriter.getInstance(document, new FileOutputStream("patrimonio.pdf"));
			// abrir o documento (formatar e inserir o conteúdo)
			document.open();
			// adicionar a data atual
			Date dataRelatorio = new Date();
			DateFormat formatador = DateFormat.getDateInstance(DateFormat.FULL);
			document.add(new Paragraph(formatador.format(dataRelatorio)));
			// adicionar um páragrafo
			document.add(new Paragraph("Patrímônio:"));
			document.add(new Paragraph("")); // pular uma linha
			// ----------------------------------------------------------
			// query (instrução sql para gerar o relatório de clientes)
			String readPatrimonio = "select sum(custo * estoque) as Total from produtos;";
			try {
				// abrir a conexão com o banco
				con = dao.conectar();
				// preparar a query (executar a instrução sql)
				pst = con.prepareStatement(readPatrimonio);
				// obter o resultado (trazer do banco de dados)
				rs = pst.executeQuery();
				// atenção uso do while para trazer todos os clientes
				// Criar uma tabela de duas colunas usando o framework(itextPDF)
				PdfPTable tabela = new PdfPTable(1); // (2) número de colunas
				// Criar o cabeçalho da tabela
				PdfPCell col1 = new PdfPCell(new Paragraph("Patrímônio"));

				tabela.addCell(col1);

				while (rs.next()) {
					// popular a tabela
					tabela.addCell(rs.getString(1));

				}
				// adicionar a tabela ao documento pdf
				document.add(tabela);
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
			Desktop.getDesktop().open(new File("patrimonio.pdf"));
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	private void VendaPatrimonio() {
		// instanciar um objeto para construir a página pdf
		Document document = new Document();
		// configurar como A4 e modo paisagem
		document.setPageSize(PageSize.A4.rotate());
		// gerar o documento pdf
		try {
			// criar um documento em branco (pdf) de nome clientes.pdf
			PdfWriter.getInstance(document, new FileOutputStream("patrimonio.pdf"));
			// abrir o documento (formatar e inserir o conteúdo)
			document.open();
			// adicionar a data atual
			Date dataRelatorio = new Date();
			DateFormat formatador = DateFormat.getDateInstance(DateFormat.FULL);
			document.add(new Paragraph(formatador.format(dataRelatorio)));
			// adicionar um páragrafo
			document.add(new Paragraph("Patrímônio:"));
			document.add(new Paragraph("")); // pular uma linha
			// ----------------------------------------------------------
			// query (instrução sql para gerar o relatório de clientes)
			String readPatrimonio = "select sum((custo +(custo * lucro)/100) * estoque) as total from produtos;";
			try {
				// abrir a conexão com o banco
				con = dao.conectar();
				// preparar a query (executar a instrução sql)
				pst = con.prepareStatement(readPatrimonio);
				// obter o resultado (trazer do banco de dados)
				rs = pst.executeQuery();
				// atenção uso do while para trazer todos os clientes
				// Criar uma tabela de duas colunas usando o framework(itextPDF)
				PdfPTable tabela = new PdfPTable(1); // (2) número de colunas
				// Criar o cabeçalho da tabela
				PdfPCell col1 = new PdfPCell(new Paragraph("Patrímônio"));

				tabela.addCell(col1);

				while (rs.next()) {
					// popular a tabela
					tabela.addCell(rs.getString(1));

				}
				// adicionar a tabela ao documento pdf
				document.add(tabela);
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
			Desktop.getDesktop().open(new File("patrimonio.pdf"));
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	private void Fornecedores() {

		// instanciar um objeto para construir a página pdf

		Document document = new Document();

		// configurar como A4 e modo paisagem

		// document.setPageSize(PageSize.A4.rotate());

		// gerar o documento pdf

		try {

			// criar um documento em branco (pdf) de nome clientes.pdf

			PdfWriter.getInstance(document, new FileOutputStream("fornecedores.pdf"));

			// abrir o documento (formatar e inserir o conteúdo)

			document.open();

			// adicionar a data atual

			Date dataRelatorio = new Date();

			DateFormat formatador = DateFormat.getDateInstance(DateFormat.FULL);

			document.add(new Paragraph(formatador.format(dataRelatorio)));

			// adicionar um páragrafo

			document.add(new Paragraph("Fornecedores:"));

			document.add(new Paragraph(" ")); // pular uma linha

			// ----------------------------------------------------------

			// query (instrução sql para gerar o relatório de clientes)

			String readFornecedores = "select razao,fone, cnpj from fornecedores order by razao";

			try {

				// abrir a conexão com o banco

				con = dao.conectar();

				// preparar a query (executar a instrução sql)

				pst = con.prepareStatement(readFornecedores);

				// obter o resultado (trazer do banco de dados)

				rs = pst.executeQuery();

				// atenção uso do while para trazer todos os clientes

				// Criar uma tabela de duas colunas usando o framework(itextPDF)

				PdfPTable tabela = new PdfPTable(3); // (2) número de colunas

				// Criar o cabeçalho da tabela

				PdfPCell col1 = new PdfPCell(new Paragraph("Razão Social"));

				PdfPCell col2 = new PdfPCell(new Paragraph("Fone"));

				PdfPCell col3 = new PdfPCell(new Paragraph("CNPJ"));

				tabela.addCell(col1);

				tabela.addCell(col2);

				tabela.addCell(col3);

				while (rs.next()) {

					// popular a tabela

					tabela.addCell(rs.getString(1));

					tabela.addCell(rs.getString(3));

					tabela.addCell(rs.getString(7));
				}
				// adicionar a tabela ao documento pdf
				document.add(tabela);
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
			Desktop.getDesktop().open(new File("fornecedores.pdf"));
		} catch (Exception e) {
			System.out.println(e);

		}

	}

	private void relatorioValidade() {
		Document document = new Document();
		document.setPageSize(PageSize.A4.rotate());
		try {
			PdfWriter.getInstance(document, new FileOutputStream("validade.pdf"));
			document.open();
			Date dataRelatorio = new Date();
			DateFormat formatador = DateFormat.getDateInstance(DateFormat.FULL);
			document.add(new Paragraph(formatador.format(dataRelatorio)));
			document.add(new Paragraph("Validade:"));
			document.add(new Paragraph(" "));
			String readServicos = "select nome,dataent,dataval from produtos order by nome";
			try {
				con = dao.conectar();
				pst = con.prepareStatement(readServicos);
				rs = pst.executeQuery();
				PdfPTable tabela = new PdfPTable(3);
				PdfPCell col1 = new PdfPCell(new Paragraph("Produto"));
				PdfPCell col2 = new PdfPCell(new Paragraph("Data de entrada"));
				PdfPCell col3 = new PdfPCell(new Paragraph("Data de validade"));
				tabela.addCell(col1);
				tabela.addCell(col2);
				tabela.addCell(col3);
				while (rs.next()) {
					tabela.addCell(rs.getString(1));
					tabela.addCell(rs.getString(2));
					tabela.addCell(rs.getString(3));
				}
				document.add(tabela);
				con.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		document.close();
		try {
			Desktop.getDesktop().open(new File("validade.pdf"));
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}// fim do código
