package sunadventure.container;

import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;

import sunadventure.modelos.Fase;

public class Container_SunAdventure extends JFrame {

	public Container_SunAdventure() {

		add(new Fase());
		setSize(1024, 768);
		setTitle("Sun Adventure");
		Image Icone = Toolkit.getDefaultToolkit().getImage("res\\logoo.png"); 
		setIconImage(Icone);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		this.setResizable(false);
		setVisible(true);

	}

	public static void main(String[] args) {
		new Container_SunAdventure();
		
	}

}
