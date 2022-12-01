import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.*;
import java.awt.event.ActionEvent;

public class Janela extends JFrame {

	private JPanel contentPane;
	private JTextField numero;
	private Integer contador = 0;
	private JLabel resultado;
	Connection conexao;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Janela frame = new Janela();
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
	public Janela() {
		setTitle("Eh Primo?");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 825, 259);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(4, 1, 0, 0));
		
		JLabel lblNewLabel = new JLabel("Digite um número:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblNewLabel);
		
		JPanel panel = new JPanel();
		contentPane.add(panel);
		
		numero = new JTextField();
		panel.add(numero);
		numero.setColumns(10);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1);
		
		JButton btnVerificar = new JButton("Verificar");
		btnVerificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Integer num = Integer.parseInt(numero.getText());
				for (Integer i = 1; i <= num; i++) {
					if (num % i == 0) {
						contador++;
					}
					if (contador > 2) {
						break;
					}
				}
				
				if (contador == 1 || contador > 2) {
					resultado.setText("O número " + num + " não é primo");
					cadastraNumero(num, false);
				}else {
					resultado.setText("O número " + num + " é primo");
					cadastraNumero(num, true);
				}
				
				contador = 0;
			}
		});
		panel_1.add(btnVerificar);
		
		resultado = new JLabel("");
		resultado.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(resultado);
	}

	public void cadastraNumero(Integer numero, boolean isprimo) {
		try {
			conexao = DriverManager.getConnection("jdbc:mysql://localhost:3306/ex_1_1", "root", "aluno");
			System.out.println("Conectado na base de dados com sucesso.");
			PreparedStatement ps = conexao.prepareStatement("INSERT INTO primo VALUES(id, ?,?)");
			ps.setInt(1, numero);
			ps.setBoolean(2, isprimo);
			ps.executeUpdate();
		} catch(SQLException e) {
			System.out.println("Erro ao conectar na base de dados. Erro: " + e);
		}
	}
	
}
