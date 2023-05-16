package sunadventure.modelos;

import java.awt.Image;
import java.awt.Rectangle;
import java.util.Random;

import javax.swing.ImageIcon;

public class Stars {

	private Image Imagem, imagem2;
	private int x, y;
	private int largura, altura;
	private boolean isVisivel;

	private static final int LARGURA = 1024;
	private static int VELOCIDADE = 1;

	public Stars(int x, int y) {
		this.x = x;
		this.y = y;
		isVisivel = true;
	}

	public void load() {
		ImageIcon referencia = new ImageIcon("res\\star1.png");
		Imagem = referencia.getImage();
		ImageIcon referencia2 = new ImageIcon("res\\star22.png");
		imagem2 = referencia2.getImage();

		this.altura = Imagem.getHeight(null);
		this.largura = Imagem.getWidth(null);
	}

	public void update() {
		if (this.x < 0) {
			this.x = LARGURA;
			Random a = new Random();
			int m = a.nextInt(500);
			this.x = m + 1024;
			Random r = new Random();
			int n = r.nextInt(768);
			this.y = n;
		} else {
			this.x -= VELOCIDADE;
		}
	}

	public static void setVELOCIDADE(int vELOCIDADE) {
		VELOCIDADE = vELOCIDADE;
	}

	public Image getImagem2() {
		return imagem2;
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

}
