package sunadventure.modelos;

import java.awt.Image;
import java.awt.Rectangle;
import java.util.Random;

import javax.swing.ImageIcon;

public class PowerUps {

	private Image vida, turbo, missel;
	private int x, y;
	private int largura, altura;
	private boolean isVisivel;

	private static final int LARGURA = 1024;
	private static int VELOCIDADE = 2;

	public PowerUps(int x, int y) {
		this.x = x;
		this.y = y;
		isVisivel = true;
	}

	public void load_Vida() {
		ImageIcon referencia = new ImageIcon("res\\Vida.png");
		vida = referencia.getImage();
		this.altura = vida.getHeight(null);
		this.largura = vida.getWidth(null);
	}

	public void load_Turbo() {
		ImageIcon referencia2 = new ImageIcon("res\\TurboPowerup.png");
		turbo = referencia2.getImage();
		this.altura = turbo.getHeight(null);
		this.largura = turbo.getWidth(null);
	}

	public void load_Missel() {
		ImageIcon referencia3 = new ImageIcon("res\\Misselico.png");
		missel = referencia3.getImage();

		this.altura = missel.getHeight(null);
		this.largura = missel.getWidth(null);
	}

	public void update() {
		if (this.x < 0) {
			x = -100;

		} else {
			this.x -= VELOCIDADE;
		}
	}

	public Image getMissel() {
		return missel;
	}

	public void setMissel(Image missel) {
		this.missel = missel;
	}

	public Image getVida() {
		return vida;
	}

	public Image getTurbo() {
		return turbo;
	}

	public static void setVELOCIDADE(int vELOCIDADE) {
		VELOCIDADE = vELOCIDADE;
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

	public int getY() {
		return y;
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, largura, altura);
	}

}
