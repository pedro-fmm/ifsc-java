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
					
					listaFuncionarios.add(funcionario);
					
					System.out.println("Funcionário cadastrado!");
										
					break;
				case 2: // Apagar -> ID
					
					System.out.println("Digite o ID do funcionário: ");
					id_func = Integer.valueOf(input.nextLine());
					
					Funcionario funcionario_apagar = searchFuncionarioPorID(listaFuncionarios, id_func);
					
					if(funcionario_apagar == null) {
						System.out.println("ID inválido. ");
					} else {						
						listaFuncionarios.remove(funcionario_apagar);
						System.out.println("Funcionário deletado!");
					}
										
					break;
				case 3: // Atualizar
					
					int opcao_alterar;
					
					System.out.println("Digite o ID do funcionário: ");
					id_func = Integer.valueOf(input.nextLine());
					Funcionario funcinonario_editado = searchFuncionarioPorID(listaFuncionarios, id_func);
					
					if (searchFuncionarioPorID(listaFuncionarios, id_func) == null) {
						System.out.println("ID inválido. ");
					} else {
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
						
						funcinonario_editado.setNome(nome);
						funcinonario_editado.setUsuario(usuario);
						funcinonario_editado.setSenha(senha);
										
						System.out.println("Funcionário alterado!");
					}
										
					break;
				case 4: // Consultar -> Nome
					
					System.out.println("Digite o nome do funcionário: ");
					String nome_consulta = String.valueOf(input.nextLine());
					
					Funcionario funcionario_listar = searchFuncionarioPorNome(listaFuncionarios, nome_consulta);
					
					if(funcionario_listar == null) {
						System.out.println("Nenhum funcionário cadastrado com esse nome. ");
					} else {						
						funcionario_listar.mostrarDados();
					}
					
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
	
	public static Funcionario searchFuncionarioPorID(ArrayList<Funcionario> listaFuncionarios, long id) {
		for (Funcionario funcionario : listaFuncionarios) {
			if (funcionario.getID() == id) {
				return funcionario;
			}
		}
		return null;
	}
	
	public static Funcionario searchFuncionarioPorNome(ArrayList<Funcionario> listaFuncionarios, String nome) {
		for (Funcionario funcionario : listaFuncionarios) {
			if (funcionario.getNome().equals(nome)) {
				return funcionario;
			}
		}
		return null;
	}

}
