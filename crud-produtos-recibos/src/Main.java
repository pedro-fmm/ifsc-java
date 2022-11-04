import java.util.ArrayList;
import java.util.Scanner;

import com.mysql.cj.protocol.a.LocalDateTimeValueEncoder;

import java.sql.*;
import java.text.DateFormat;
import java.time.LocalDate;

public class Main {

	public static void main(String[] args) {
		
		int opcao;
		Scanner input = new Scanner(System.in);
		
		ArrayList<Produto> listaProdutos = new ArrayList<>();
		
		listaProdutos.add(new Produto(1, "Coca-Cola Light", 3.50, 4));
		listaProdutos.add(new Produto(2, "Coca-Cola Normal", 3.20, 7));
		listaProdutos.add(new Produto(3, "Coca-Cola Zero", 3.65, 5));
		listaProdutos.add(new Produto(4, "Dolly Guaraná", 0.01, 3));
		listaProdutos.add(new Produto(5, "Fruki Churume", 2.10, 10));
		
		do {
			System.out.println("\nSistema de gestão de produtos\n");
			System.out.println("1 - Cadastrar um produto");
			System.out.println("2 - Consultar produto");
			System.out.println("3 - Listar produtos");
			System.out.println("4 - Vender um produto");
			System.out.println("5 - Listar Recibos");
			System.out.println("6 - Encerrar programa");
			
			opcao = Integer.valueOf(input.nextLine());
			
			switch (opcao) {
				case 1: // Criar produto 
					
					listaProdutos.add(criarProduto());
					
					break;
					
				case 2: // Consultar produto => ID
										
					System.out.println("\nConsultar produto: \n");
					long codigo_pesquisa = Long.valueOf(input.nextLine());
										
					Produto produtoPesquisado = pesquisarProduto(listaProdutos, codigo_pesquisa);
					
					if (produtoPesquisado == null) {
						System.out.println("\nNenhum produto cadastrado com esse código");
					} else {
						produtoPesquisado.mostrarDados();
					}
					
					break;
					
				case 3: // Listar produtos 
					
					System.out.println("\nListando produtos: \n");
					
					for (Produto produto : listaProdutos) {
						produto.mostrarDados();
					}
					
					break;
					
				case 4: // Vender produto => ID
					
					System.out.println("\nVender produto: \n");
					
					System.out.println("Código do produto: ");
					long codigoProdutoVenda = Long.valueOf(input.nextLine());
					
					System.out.println("Quantidade: ");
					int qtdeProduto = Integer.valueOf(input.nextLine());
					
					Produto produtoVenda = pesquisarProduto(listaProdutos, codigoProdutoVenda);
						
					if (produtoVenda != null) {						
						if(produtoVenda.getEstoque() <= qtdeProduto) {
							System.out.println("\nNão há estoque suficiente\n");
						} else {
							produtoVenda.setEstoque(produtoVenda.getEstoque() - qtdeProduto);
							System.out.println("\nVenda realizada\n");
							System.out.println("Valor: R$" + produtoVenda.getValor()*qtdeProduto);
							gerarRecibo(produtoVenda.getNome(), qtdeProduto, (float)produtoVenda.getValor());
							System.out.println("Recibo cadastrado no banco!");
						}
					} else {
						System.out.println("\nProduto inválido\n");
					}
					
					break;
	
				case 5:
					listarRecibos();
					break;
					
				default:
					break;
			}
			
		} while (opcao != 6);
		
		input.close();
		
	}

	public static Produto criarProduto() {
		
		Scanner input = new Scanner(System.in);
		
		System.out.println("\nCadastrando produto: \n");
		
		System.out.println("Código: ");
		long codigo = Long.valueOf(input.nextLine());
		
		System.out.println("Nome: ");
		String nome = input.nextLine();
		
		System.out.println("Valor: ");
		double valor = Double.valueOf(input.nextLine());
		
		System.out.println("Estoque: ");
		int estoque = Integer.valueOf(input.nextLine());
		
		Produto produto = new Produto(codigo, nome, valor, estoque);
				
		return produto;
	}
	
	public static Produto pesquisarProduto(ArrayList<Produto> listaProdutos, long codigo) {
		
		for (Produto produto : listaProdutos) {
			if (produto.getCodigo() == codigo) {
				return produto;
			}
		}
				
		return null;
	}
	
	public static void gerarRecibo(String nomeProduto, int qtdVenda, float precoUnitario) {
		try { 
			Connection conexao = DriverManager.getConnection("jdbc:mysql://localhost:3306/sistema", "root", "aluno");
			
			PreparedStatement ps = conexao.prepareStatement("INSERT INTO recibos VALUES(id,?,?,?,?,?)");
			LocalDate data = LocalDate.now();
			ps.setDate(1, Date.valueOf(data));
			ps.setString(2, nomeProduto);
			ps.setInt(3, qtdVenda);
			ps.setFloat(4, precoUnitario);
			ps.setFloat(5, Float.valueOf(qtdVenda * precoUnitario));
			
			ps.executeUpdate();
			
		}catch(SQLException e) {
			System.out.println("Erro ao conectar no base de dados. Erro: "+ e);
		}
		
	}
	
	public static void listarRecibos() {
		try { 
			Connection conexao = DriverManager.getConnection("jdbc:mysql://localhost:3306/sistema", "root", "aluno");
			
			PreparedStatement ps = conexao.prepareStatement("SELECT * FROM recibos");
			ResultSet rs = ps.executeQuery();
			
		 while( rs.next() )
	        {
	             int id = rs.getInt("ID");
	             Date data_venda = rs.getDate("DATA_VENDA");
	             String nome_produto = rs.getString("NOME_PRODUTO");
	             int qtd = rs.getInt("QTD");
	             float preco_unitario = rs.getFloat("PRECO_UNITARIO");
	             float valor_total = rs.getFloat("VALOR_TOTAL");
	             System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
	             System.out.println("ID: " + id);
	             System.out.println("Data: " + data_venda);
	             System.out.println("Nome do Produto: " + nome_produto);
	             System.out.println("Quantidade vendida: " + qtd);
	             System.out.println("Preco Unitario: " + preco_unitario);
	             System.out.println("Valor Total: " + valor_total);
	        }
			
		}catch(SQLException e) {
			System.out.println("Erro ao conectar no base de dados. Erro: "+ e);
		}
	}
	
}
