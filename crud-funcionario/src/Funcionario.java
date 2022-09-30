
public class Funcionario {

	private long id;
	private String nome;
	private String usuario;
	private String senha;
	
	public Funcionario() {}
	
	public Funcionario (long id, String nome, String usuario, String senha) {
		this.id = id;
		this.nome = nome;
		this.usuario = usuario;
		this.senha = senha;
	}
	
	public long getID() {
		return id;
	}
	
	public void setID(long ID) {
		this.id= ID;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getUsuario() {
		return usuario;
	}
	
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	
	public String getSenha() {
		return senha;
	}
	
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public void mostrarDados() {
		System.out.println("Dados funcionário: \n");
		System.out.println("ID: "+ id);
		System.out.println("Nome: "+ nome);
		System.out.println("Usuario: "+ usuario);
		System.out.println("Senha: "+ senha);
		System.out.println("\n");
	}
}