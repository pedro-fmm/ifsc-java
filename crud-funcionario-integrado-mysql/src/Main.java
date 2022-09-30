import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		boolean repetir = true;
		
		int opcao;
		long id = 0, id_func;
		
		Scanner input = new Scanner(System.in);
		
		ArrayList<Funcionario> listaFuncionarios = new ArrayList<>();
		
		do {
			System.out.println("1. Cadastrar funcionário");
			System.out.println("2. Apagar funcionário");
			System.out.println("3. Atualizar cadastro");
			System.out.println("4. Consultar funcionário");
			System.out.println("5. Sair");
			
			opcao = Integer.valueOf(input.nextLine());
			
			switch (opcao) {
				case 1: // Criando funcionario
					
					String nome, usuario, senha, confirma_senha;
					
					System.out.println("Cadastrando funcionário ... \n");
					
					System.out.println("Digite o nome: ");
					nome = input.nextLine();
					
					System.out.println("Digite o usuário: ");
					usuario = input.nextLine();
					
					do {
						System.out.println("Digite o senha: ");
						senha = input.nextLine();
						
						System.out.println("Confirme a senha: ");
						confirma_senha = input.nextLine();
					} while (!senha.equals(confirma_senha));
					
					Funcionario funcionario = new Funcionario(id++, nome, usuario, senha);
					
					System.out.println("Funcionário cadastrado!");
										
					break;
				case 2: // Apagar -> ID
					
					System.out.println("Digite o ID do funcionário: ");
					id_func = Integer.valueOf(input.nextLine());
																			
					break;
				case 3: // Atualizar
					
					int opcao_alterar;
					
					System.out.println("Digite o ID do funcionário: ");
					id_func = Integer.valueOf(input.nextLine());
												
					break;
				case 4: // Consultar -> Nome
					
					System.out.println("Digite o nome do funcionário: ");
					String nome_consulta = String.valueOf(input.nextLine());
					
					break;
				case 5:
					repetir = false;
					break;
					
				case 6:
					for (Funcionario ff : listaFuncionarios) {
						ff.mostrarDados();
					}
					break;
			default:
				throw new IllegalArgumentException("Unexpected value: " + opcao);
			}
		} while (repetir);
		
	}
	
}
