import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.mysql.cj.conf.ConnectionUrl;

import net.miginfocom.swing.MigLayout;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.awt.event.ActionEvent;

import java.sql.*;

public class Main extends JFrame {

	private JPanel contentPane;
	private JTextField txtNumero;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
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
	public Main() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(4, 1, 0, 0));
		
		JPanel panel1 = new JPanel();
		contentPane.add(panel1);
		
		JLabel lblTexto = new JLabel("Digite um número: ");
		panel1.add(lblTexto);
		
		JPanel panel2 = new JPanel();
		contentPane.add(panel2);
		
		txtNumero = new JTextField();
		panel2.add(txtNumero);
		txtNumero.setColumns(10);
		
		JPanel panel3 = new JPanel();
		contentPane.add(panel3);

		JPanel panel4 = new JPanel();
		contentPane.add(panel4);
		
		JLabel lblResultado = new JLabel("");
		panel4.add(lblResultado);
		
		JButton btnCalcular = new JButton("Calcular");
		panel3.add(btnCalcular);
		
		btnCalcular.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblResultado.setText("lol");
				int num = -1;
				
				try {
					num = Integer.valueOf(txtNumero.getText());
					
					if (num < 1) {
						lblResultado.setText("Valor inválido, insira um número natural.");
					} else if (num == 1) {
						lblResultado.setText("1 não é um número primo");
					}
					else {
						int divisores = 1;
						
						for (int i = 1; i < num; i++) {
							if (divisores > 2) {
								lblResultado.setText(num + " não é um número primo");
								registrarNumero(num, false);
								break;
							} else if (num % i == 0) {
								divisores++;
								lblResultado.setText(num+ "");
							}
						}
						
						if (divisores == 2) {
							lblResultado.setText(num + " é primo.");
							registrarNumero(num, true);
						}
					}
				} catch (Exception e2) {
					lblResultado.setText("Valor inválido, insira um número inteiro.");
				}
			}
		});
		
	}

	private void registrarNumero(int numero, boolean bool) {
		try {
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/aula", "root", "aluno");
			
			PreparedStatement ps = conn.prepareStatement("insert into primo values(id,?,?)");

			ps.setInt(1, numero);
			ps.setBoolean(2, bool);
			
			ps.executeUpdate();
			
			System.out.println("Dados inseridos com sucesso.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
