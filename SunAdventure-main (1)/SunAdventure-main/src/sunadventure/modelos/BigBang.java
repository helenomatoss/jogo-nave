package sunadventure.modelos;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.Timer;

public class BigBang extends Nave implements ActionListener{

	private Image Imagem;
	private int x, y;
	private int largura, altura;
	private boolean isVisivel, tiro;
	private List<Explosao> explosoes;
	private int vida;
	private static final int LARGURA = 1024;
	private static int VELOCIDADE = 1;

	// Construtor
	public BigBang(int x, int y) {
		this.x = x;
		this.y = y;
		isVisivel = true;
		vida = 1;

		tiro = true;
		explosoes = new ArrayList<Explosao>();

	}

	// imagem
	public void load() {
		if (vida < 2) {
			ImageIcon referencia = new ImageIcon("res\\enemy3.png");
			Imagem = referencia.getImage();

		}

		if (vida == 2) {
			ImageIcon referencia = new ImageIcon("res\\enemy3Hitmed.png");
			Imagem = referencia.getImage();
		}

		if (vida == 3) {
			ImageIcon referencia = new ImageIcon("res\\enemy3Hit.png");
			Imagem = referencia.getImage();
		}

		this.altura = Imagem.getHeight(null);
		this.largura = Imagem.getWidth(null);
	}

	// Movimento
	public void update() {
		if (this.x < 0) {
			this.x = -100;
		} else {
			this.x -= VELOCIDADE;
			if (x < 550) {
				this.x -= VELOCIDADE + 3;
				this.y += 3;

			}

		}

	}

	// Explosoes
	public void explosoes() {
		this.explosoes.add(new Explosao(x + largura, y + altura / 2));
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

	public static void setVELOCIDADE(int vELOCIDADE) {
		VELOCIDADE = vELOCIDADE;
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

	public void setImagem(Image imagem) {
		Imagem = imagem;
	}

	public List<Explosao> getExplosoes() {
		return explosoes;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
