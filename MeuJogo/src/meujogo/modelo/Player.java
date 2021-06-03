package meujogo.modelo;

import java.awt.Image;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

public class Player {
	
	private int x,y;
	private int dx,dy;
	private Image imagem;
	private int altura, largura;
	private List <Tiro> tiros;
	
	
	public Player() {
		this.x = 100;
		this.y = 100;
		
		tiros = new ArrayList<Tiro>(); 
	}
	
	public void load() {
		ImageIcon referencia = new ImageIcon("res\\player.png");
		imagem = referencia.getImage();
		
		altura = imagem.getHeight(null);
		largura = imagem.getWidth(null);
	}
	
	
	public void update() {
		x += dx;
		y += dy;
	}
	
	public void tirosimples() {
		this.tiros.add(new Tiro(x+largura, y + (altura/2)));
	}
	
	public void keyPressed(KeyEvent tecla) {
		int codigo = tecla.getKeyCode();
		
		if(codigo == KeyEvent.VK_A) {
			tirosimples();
		}
		
		if(codigo == KeyEvent.VK_UP) {
			dy=-7;
		}
		
		if(codigo == KeyEvent.VK_DOWN) {
			dy=7;
		}
		
		if(codigo == KeyEvent.VK_LEFT) {
			dx=-7;
		}
		
		if(codigo == KeyEvent.VK_RIGHT) {
			dx=7;
		}
	}
	
	public void keyRelease(KeyEvent tecla) {
		int codigo = tecla.getKeyCode();
		
		if(codigo == KeyEvent.VK_UP) {
			dy=0;
		}
		
		if(codigo == KeyEvent.VK_DOWN) {
			dy=0;
		}
		
		if(codigo == KeyEvent.VK_LEFT) {
			dx=0;
		}
		
		if(codigo == KeyEvent.VK_RIGHT) {
			dx=0;
		}
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public Image getImagem() {
		return imagem;
	}

	public List<Tiro> getTiros() {
		return tiros;
	}

	
}
