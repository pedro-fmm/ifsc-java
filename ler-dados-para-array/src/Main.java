import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.*;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class Main extends JFrame {

	private JPanel contentPane;
	private JTextField txtNumero;
	private ArrayList<Double> listaNum = new ArrayList<Double>();

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
		contentPane.setLayout(new GridLayout(10, 0, 0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel);
		
		JLabel lblNewLabel = new JLabel("Digite os números: ");
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1);
		
		txtNumero = new JTextField();
		panel_1.add(txtNumero);
		txtNumero.setColumns(10);
		
		JPanel panel_2 = new JPanel();
		contentPane.add(panel_2);
		
		JButton btnAdicionar = new JButton("Adicionar");
		
		panel_2.add(btnAdicionar);
		
		JPanel panel_3 = new JPanel();
		contentPane.add(panel_3);
		
		JLabel lblMaior = new JLabel("Maior:");
		panel_3.add(lblMaior);
		
		JPanel panel_4 = new JPanel();
		contentPane.add(panel_4);
		
		JLabel lblResultadoMaior = new JLabel("-");
		panel_4.add(lblResultadoMaior);
		
		JPanel panel_5 = new JPanel();
		contentPane.add(panel_5);
		
		JLabel lblMenor = new JLabel("Menor:");
		panel_5.add(lblMenor);
		
		JPanel panel_6 = new JPanel();
		contentPane.add(panel_6);
		
		JLabel lblResultadoMenor = new JLabel("-");
		panel_6.add(lblResultadoMenor);
		
		JPanel panel_7 = new JPanel();
		contentPane.add(panel_7);
		
		JLabel lblMedia = new JLabel("Média:");
		panel_7.add(lblMedia);
		
		JPanel panel_8 = new JPanel();
		contentPane.add(panel_8);
		
		JLabel lblResultadoMedia = new JLabel("-");
		panel_8.add(lblResultadoMedia);
		
		JPanel panel_9 = new JPanel();
		contentPane.add(panel_9);
		
		JButton btnCalcular = new JButton("Calcular");
		btnCalcular.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double media = 0, maior = listaNum.get(0), menor = listaNum.get(0);
				
				for (Double num : listaNum) {
					media += num;
				}
				
				media = media/listaNum.size();
				
				for (Double num : listaNum) { 
					if (num < menor) {
						menor = num;
					};
				}
				
				for (Double num : listaNum) { 
					if (num > maior) {
						maior = num;
					};
				}
				
				lblResultadoMaior.setText("" + maior);
				lblResultadoMedia.setText("" + media);
				lblResultadoMenor.setText("" + menor);
				
				salvarDados(media, maior, menor);
				
				listaNum.clear();
			}

			private void salvarDados(double media, double maior, double menor) {
				try {
					Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ex_1_3", "root", "aluno");
					
					PreparedStatement ps = conn.prepareStatement("insert into numeros values(id,?,?,?)");

					ps.setDouble(1, media);
					ps.setDouble(2, maior);
					ps.setDouble(3, menor);

					ps.executeUpdate();
					
					System.out.println("Dados inseridos com sucesso.");
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});
		panel_9.add(btnCalcular);
		
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listaNum.add(Double.valueOf(txtNumero.getText()));
				}
		});
	}

}
