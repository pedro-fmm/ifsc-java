import java.awt.EventQueue;
import java.lang.Math;
import java.sql.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Canvas;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.Panel;
import java.awt.Dimension;
import net.miginfocom.swing.MigLayout;
import java.awt.Component;

public class Main extends JFrame {

	private JPanel contentPane;
	private JTextField txtLado1;
	private JTextField txtLado2;
	private JTextField txtLado3;
	private static JLabel lblResultado;
	private static JLabel imagem;

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
	 * @throws IOException 
	 */
	public Main() throws IOException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setMinimumSize(new Dimension(300, 1000));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(2, 1, 0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel);
		panel.setLayout(new GridLayout(4, 0, 0, 0));
		
		JPanel panel_2 = new JPanel();
		panel.add(panel_2);
		
		JLabel lblLado1 = new JLabel("Lado 1: ");
		panel_2.add(lblLado1);
		
		txtLado1 = new JTextField();
		panel_2.add(txtLado1);
		txtLado1.setColumns(10);
		
		JPanel panel_3 = new JPanel();
		panel.add(panel_3);
		
		JLabel lblLado2 = new JLabel("Lado 2: ");
		panel_3.add(lblLado2);
		
		txtLado2 = new JTextField();
		txtLado2.setColumns(10);
		panel_3.add(txtLado2);
		
		JPanel panel_4 = new JPanel();
		panel.add(panel_4);
		
		JLabel lblLado3 = new JLabel("Lado 3: ");
		panel_4.add(lblLado3);
		
		txtLado3 = new JTextField();
		txtLado3.setColumns(10);
		panel_4.add(txtLado3);
		
		JPanel panel_5 = new JPanel();
		panel.add(panel_5);
		
		JButton btnExaminar = new JButton("Examinar");
		
		
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtLado1.setText("");
				txtLado2.setText("");
				txtLado3.setText("");
			}
		});
		panel_5.add(btnLimpar);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1);
		ImageIcon icon = new ImageIcon("./img/tri.jpg");
		panel_1.setLayout(new MigLayout("", "[424px]", "[20px][62px]"));
		
		Panel panel_6 = new Panel();
		panel_1.add(panel_6, "cell 0 0,growx,aligny top");
		
		lblResultado = new JLabel("");
		lblResultado.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel_6.add(lblResultado);
		
		Panel panel_7 = new Panel();
		panel_1.add(panel_7, "cell 0 1,grow");
		
		imagem = new JLabel(icon);
		imagem.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel_7.add(imagem);
		
		
		btnExaminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double lado1 = Math.abs(Double.valueOf(txtLado1.getText()));
				double lado2 = Math.abs(Double.valueOf(txtLado2.getText()));
				double lado3 = Math.abs(Double.valueOf(txtLado3.getText()));
				
				if((lado1 + lado2 < lado3) || (lado1 + lado3 < lado2) || (lado2 + lado3 < lado1)) {
					ImageIcon icon = new ImageIcon("./img/erro.png");
					panel_7.remove(imagem);
					imagem = new JLabel(icon);
					panel_7.add(imagem);
					lblResultado.setText("Triângulo inválido!");
					salvarDados(lado1, lado2, lado3, false, false, false, false);
				} else {
					if((lado1 != lado2) && (lado1 != lado3) && (lado2 != lado3)) {
						panel_7.remove(imagem);
						ImageIcon iconn = new ImageIcon("./img/tri-escaleno.jpg");
						imagem = new JLabel(iconn);
						panel_7.add(imagem);
						lblResultado.setText("Triângulo escaleno!");
						
						salvarDados(lado1, lado2, lado3, true, false, false, true);
					} else if((lado1 == lado2) && (lado1 == lado3) && (lado2 == lado3)) {
						panel_7.remove(imagem);
						ImageIcon icon = new ImageIcon("./img/tri-equilatero.jpg");
						imagem = new JLabel(icon);
						panel_7.add(imagem);
						lblResultado.setText("Triângulo equilátero!");
						salvarDados(lado1, lado2, lado3, true, true, false, false);
					} else {
						panel_7.remove(imagem);
						ImageIcon icon = new ImageIcon("./img/tri-isoceles.jpg");
						imagem = new JLabel(icon);
						panel_7.add(imagem);
						lblResultado.setText("Triângulo isósceles!");
						salvarDados(lado1, lado2, lado3, true, false, true, false);
					}
				}
				
			}

			private void salvarDados(double lado1, double lado2, double lado3, boolean isTriangulo, boolean isEquilatero, boolean isIsosceles, boolean isEscaleno) {
				try {
					Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/prova", "root", "aluno");
					
					PreparedStatement ps = conn.prepareStatement("insert into triangulo values(id,?,?,?,?,?,?,?)");

					ps.setDouble(1, lado1);
					ps.setDouble(2, lado2);
					ps.setDouble(3, lado3);
					ps.setBoolean(4, isTriangulo);
					ps.setBoolean(5, isEquilatero);
					ps.setBoolean(6, isIsosceles);
					ps.setBoolean(7, isEscaleno);

					ps.executeUpdate();
					
					System.out.println("Dados inseridos com sucesso.");
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});
		panel_5.add(btnExaminar);
	}

}
