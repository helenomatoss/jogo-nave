package sunadventure.container;
	import javax.swing.*;
	import java.awt.*;
	import java.awt.event.*;
	import java.util.Arrays;

	import javax.swing.border.Border;
	import javax.swing.border.TitledBorder;


	public class Cadastro_SunAdventure extends JFrame {
		/*
		 
		 by: Cauan de Paula, Gustavo Candido e Heleno Matos - 4SIT
		
		 */
		Cadastro_SunAdventure frame;
		private static Container ctela;
		private JLabel lbnome, sizefundo;
		private static JTextField tfnome;
		private static JButton btenviar;
		private JPanel panel1;
		ImageIcon bt, fundo;

		public Cadastro_SunAdventure() {
			inicializarComponentes();
		}
		
		public void inicializarComponentes() {
			ctela = getContentPane();
			
			panel1 = new JPanel((null));

			Border lineBorder = BorderFactory.createLineBorder(Color.white);
			panel1.setBorder(lineBorder);
			
			lbnome = new JLabel("Apelido:");
			tfnome = new JTextField();	
			bt = new ImageIcon("res\\bt.png");
			btenviar = new JButton(bt);
			btenviar.setBackground(Color.white);
			btenviar.setToolTipText("Cadastrar");
			
			fundo = new ImageIcon("res\\Space.png");
			sizefundo = new JLabel(fundo);
			
			sizefundo.setBounds(0,0,400,200);
			lbnome.setBounds(73, 30, 120, 20);
			tfnome.setBounds(154, 31, 150, 20);
			btenviar.setBounds(142, 65, 91, 25);
			
			lbnome.setFont(new Font("AlBayan", Font.BOLD, 16));
			lbnome.setForeground(Color.white);
			
			add(lbnome);
			add(tfnome);
			panel1.add(btenviar);
			add(sizefundo);
			add(panel1);
		}
		public static String nome(String palavras){
			palavras = tfnome.getText();
			return palavras;
		}
		public static void main(String args[]) {
			Cadastro_SunAdventure frame = new Cadastro_SunAdventure();
			frame.setResizable(false);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setBounds(400,300,400,150);
			frame.setVisible(true);
			frame.setTitle("Sun Adventure");
			Image Icone = Toolkit.getDefaultToolkit().getImage("res\\logoo.png"); 
			frame.setIconImage(Icone);
			
			 btenviar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(tfnome.getText().trim().isEmpty()) {
						UIManager.getDefaults().put("OptionPane.background",new Color(65, 130, 180));
						UIManager.put ("Panel.background", new Color(65,130,180));
						UIManager.put("OptionPane.okButtonText", "Fechar");
						JOptionPane.showMessageDialog(null, "Respostas incompletas. Por favor, preencha o campo de texto e tente novamente.", "Erro",2);
					}else {
					new Container_SunAdventure();
					}
					}
				});
		}
	}

