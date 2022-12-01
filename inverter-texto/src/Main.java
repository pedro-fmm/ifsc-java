import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;

public class Main extends JFrame {

	private JPanel contentPane;
	private JTextField txtTexto;

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
		contentPane.setLayout(new GridLayout(4, 0, 0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel);
		
		JLabel lblTexto = new JLabel("Digite o texto:");
		panel.add(lblTexto);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1);
		
		txtTexto = new JTextField();
		panel_1.add(txtTexto);
		txtTexto.setColumns(10);
		
		JPanel panel_2 = new JPanel();
		contentPane.add(panel_2);
		
		JLabel lblResultado = new JLabel("");
		panel_2.add(lblResultado);
		
		JPanel panel_3 = new JPanel();
		contentPane.add(panel_3);
		
		JButton btnInverter = new JButton("Inverter");
		btnInverter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				char vetorTexto[];
				String textoInvertido = "";
				
				String texto = String.valueOf(txtTexto.getText());
				vetorTexto = texto.toCharArray();
				
				for (int i = vetorTexto.length - 1; i >= 0; i--) {
					textoInvertido += vetorTexto[i];
				}
				
				lblResultado.setText("" + textoInvertido);
				
				salvarDados(texto, textoInvertido);
			}

			private void salvarDados(String texto, String textoInvertido) {
				try {
					Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ex_1_4", "root", "aluno");
					
					PreparedStatement ps = conn.prepareStatement("insert into texto values(id,?,?)");

					ps.setString(1, texto);
					ps.setString(2, textoInvertido);

					ps.executeUpdate();
					
					System.out.println("Dados inseridos com sucesso.");
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});
		panel_3.add(btnInverter);
	}

}
