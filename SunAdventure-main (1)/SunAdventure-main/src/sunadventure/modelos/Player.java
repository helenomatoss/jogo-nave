package sunadventure.modelos;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.Timer;

import sunadventure.songs.EfeitosSonoros;

public class Player extends Nave implements ActionListener {

	private int x, y;
	private int dx, dy;
	private Image imagem;
	private List<Tiro> tiros;
	private List<Missel> misseis;
	private int altura, largura;
	private Timer timer;
	private boolean turbo, isVisivel;
	private int vida, turboLiberar, misselLiberar;
	private double tempo = 0;

	
	public Player() {
		this.x = 100;
		this.y = 100;

		vida = 6;
		turboLiberar = 3;
		misselLiberar = 10;
		timer = new Timer(7000, this);
		timer.start();

		tiros = new ArrayList<Tiro>();
		misseis = new ArrayList<Missel>();
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (turbo == true) {
			ImageIcon referencia = new ImageIcon("res\\spaceship2.png");
			imagem = referencia.getImage();
			turbo = false;

		}
	}

	
	public void load() {
		ImageIcon referencia = new ImageIcon("res\\spaceship2.png");
		imagem = referencia.getImage();

		altura = imagem.getHeight(null);
		largura = imagem.getWidth(null);

	}

	
	public void update() {

		x += dx;
		y += dy;

		if (this.x < 6) {
			x = 6;
		}

		if (this.x > 938) {
			x = 938;
		}

		if (this.y < 65) {
			y = 65;
		}
		if (this.y > 694) {
			y = 694;
		}

		setImagemvida();

	}


	public void tirosSimples() {
		this.tiros.add(new Tiro(x + largura, y + altura / 2));

	}

	public void Misseis() {
		if (misselLiberar > 0) {
			this.misseis.add(new Missel(x + largura, y + altura / 2));
		}

	}

	
	public void turbo() {
		if (turboLiberar > 0) {
			turbo = true;
			ImageIcon referencia = new ImageIcon("res\\naveturbo.png");
			imagem = referencia.getImage();
			EfeitosSonoros a = new EfeitosSonoros();
			a.tocarSomTurbo();
			turboLiberar--;

		}
	}


	public void setImagemvida() {
		if (vida == 1) {
			ImageIcon referencia = new ImageIcon("res\\spaceship2.png");
			imagem = referencia.getImage();

		}

		if (vida == 0) {
			ImageIcon referencia = new ImageIcon("res\\spaceship2.png");
			imagem = referencia.getImage();
		}

	}

	

	public boolean isTurbo() {
		return turbo;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getMisselLiberar() {
		return misselLiberar;
	}

	public void setMisselLiberar(int misselLiberar) {
		this.misselLiberar = misselLiberar;
	}

	public int getTurboLiberar() {
		return turboLiberar;
	}

	public void setTurboLiberar(int turboLiberar) {
		this.turboLiberar = turboLiberar;
	}

	public int getVida() {
		return vida;
	}

	public void setVida(int vida) {
		this.vida = vida;
	}

	public boolean isVisivel() {
		return isVisivel;
	}

	public void setVisivel(boolean isVisivel) {
		this.isVisivel = isVisivel;
	}

	public int getX() {
		return x;
	}

	public List<Missel> getMisseis() {
		return misseis;
	}

	public List<Tiro> getTiros() {
		return tiros;
	}

	public int getY() {
		return y;
	}

	public Image getImagem() {
		return imagem;
	}



	public Rectangle getBounds() {
		return new Rectangle(x, y, largura, altura);
	}

	
	public void keyPressed(KeyEvent tecla) {

		int codigo = tecla.getKeyCode();

		if (codigo == KeyEvent.VK_SPACE) {
			if (turbo == false) {
				turbo();
			}

		}

		if (codigo == KeyEvent.VK_A) {
			if (turbo == false) {
				tirosSimples();
			}

		}

		if (codigo == KeyEvent.VK_S) {
			if (turbo == false) {
				if (misselLiberar > 0) {
					Misseis();
					misselLiberar--;
				}

			}

		}

		if (codigo == KeyEvent.VK_UP) {
			dy = -3;
		}

		if (codigo == KeyEvent.VK_DOWN) {
			dy = 3;
		}

		if (codigo == KeyEvent.VK_LEFT) {
			dx = -3;
		}

		if (codigo == KeyEvent.VK_RIGHT) {
			dx = 3;
		}

	}

	public void keyReleased(KeyEvent tecla) {
		int codigo = tecla.getKeyCode();

		if (codigo == KeyEvent.VK_UP) {
			dy = 0;
		}

		if (codigo == KeyEvent.VK_DOWN) {
			dy = 0;
		}

		if (codigo == KeyEvent.VK_LEFT) {
			dx = 0;
		}

		if (codigo == KeyEvent.VK_RIGHT) {
			dx = 0;
		}

	}

}
