package sunadventure.modelos;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Missel extends Tiro {

	private Image Imagem;
	private int x, y;
	private int largura, altura;
	private boolean isVisivel;

	private static final int LARGURA = 930;
	private static int VELOCIDADE = 0;

	public Missel(int x, int y) {
		super(x, y);
		this.x = x;
		this.y = y;
		isVisivel = true;
	}

	public void load() {
		if (x < 900) {
			ImageIcon referencia = new ImageIcon("res\\missil.png");
			Imagem = referencia.getImage();
		}
		if (x > 900) {
			ImageIcon referencia = new ImageIcon("res\\explosion1.gif");
			Imagem = referencia.getImage();
		}

		this.altura = Imagem.getHeight(null);
		this.largura = Imagem.getWidth(null);

	}

	public void update() {
		this.x += VELOCIDADE;
		if (this.x > LARGURA) {
			isVisivel = false;
		}

	}

	public boolean isVisivel() {
		return isVisivel;
	}

	public void setVisivel(boolean isVisivel) {
		this.isVisivel = isVisivel;
	}

	public Image getImagem() {
		return Imagem;
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

	public static void setVELOCIDADE(int vELOCIDADE) {
		VELOCIDADE = vELOCIDADE;
	}

}
