import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.awt.event.ActionEvent;
import java.sql.*;

public class Janela extends JFrame {

	private JPanel contentPane;
	private JTextField numero;
	private JTextField numero2;
	private JLabel resultado;

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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 448, 483);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(3, 1, 0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblNewLabel = new JLabel("Digite dois números:");
		panel.add(lblNewLabel);
		
		numero = new JTextField();
		panel.add(numero);
		numero.setColumns(10);
		
		numero2 = new JTextField();
		panel.add(numero2);
		numero2.setColumns(10);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1);
		
		JButton somar = new JButton("Somar");
		somar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String numero_str = numero.getText();
				String numero2_str = numero2.getText();
				try {
					Double numero_val = Double.parseDouble(numero_str);
					Double numero2_val = Double.parseDouble(numero2_str);
					String resultado_str = Double.toString(numero_val + numero2_val);
					resultado.setText("Resultado: " + numero_str + " + " + numero2_str + " = " + resultado_str);
				}catch (Exception e1){
					resultado.setText("Preencha todos os campos");
				}
			}
		});
		panel_1.add(somar);
		
		JButton subtrair = new JButton("Subtrair");
		subtrair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String numero_str = numero.getText();
				String numero2_str = numero2.getText();
				try {
					Double numero_val = Double.parseDouble(numero_str);
					Double numero2_val = Double.parseDouble(numero2_str);
					String resultado_str = Double.toString(numero_val - numero2_val);
					resultado.setText("Resultado: " + numero_str + " - " + numero2_str + " = " + resultado_str);
				}catch (Exception e1){
					resultado.setText("Preencha todos os campos");
				}
			}
		});
		panel_1.add(subtrair);
		
		JButton multiplicar = new JButton("Multiplicar");
		multiplicar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String numero_str = numero.getText();
				String numero2_str = numero2.getText();
				try {
					Double numero_val = Double.parseDouble(numero_str);
					Double numero2_val = Double.parseDouble(numero2_str);
					String resultado_str = Double.toString(numero_val * numero2_val);
					resultado.setText("Resultado: " + numero_str + " * " + numero2_str + " = " + resultado_str);
				}catch (Exception e1){
					resultado.setText("Preencha todos os campos");
				}
			}
		});
		panel_1.add(multiplicar);
		
		JButton dividir = new JButton("Dividir");
		dividir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String numero_str = numero.getText();
				String numero2_str = numero2.getText();
				try {
					Double numero_val = Double.parseDouble(numero_str);
					Double numero2_val = Double.parseDouble(numero2_str);
					if (numero2_val == 0) {
						resultado.setText("Impossível dividir por zero");
					}else {
						String resultado_str = Double.toString(numero_val / numero2_val);
						resultado.setText("Resultado: " + numero_str + " / " + numero2_str + " = " + resultado_str);
					}
				}catch (Exception e1){
					resultado.setText("Preencha todos os campos");
				}
			}
		});
		panel_1.add(dividir);
		
		resultado = new JLabel("Resultado:");
		resultado.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(resultado);
	}
	
	public void registraOperacao(Double num, Double num2, Double resultado_ope, String operacao) {
		try {
			Connection conexao = DriverManager.getConnection("jdbc:mysql://localhost:3306/calculadora", "root", "aluno");
			PreparedStatement ps = conexao.prepareStatement("INSERT INTO operacoes VALUES(id, ?, ? ,?, ?)");
			ps.setDouble(1, num);
			ps.setDouble(2, num2);
			ps.setDouble(3, resultado_ope);
			ps.setString(4, operacao);
			ps.executeUpdate();
		}catch (Exception e2) {
			System.out.println("Erro ao conectar na base de dados. Erro: " + e2);
		}
	}

}

//CREATE TABLE operacoes (
//	id INT PRIMARY KEY AUTO_INCREMENT,
//	num DOUBLE NOT NULL,
//	num2 DOUBLE NOT NULL,
//	resultado DOUBLE NOT NULL,
//	operacao VARCHAR(30)
//);

