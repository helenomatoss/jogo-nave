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

public class DeathFish extends Nave implements ActionListener {

	private Image Imagem;
	private int x, y;
	private int a;
	private int largura, altura;
	private boolean isVisivel, movimento, tiro;
	private List<Explosao> explosoes;
	private List<EnemyTiro> tiroInimigo;
	private int vida;

	private Timer timer;

	private static final int LARGURA = 1024;
	private static int VELOCIDADE = 1;

	public DeathFish(int x, int y) {
		this.x = x;
		this.y = y;
		isVisivel = true;
		vida = 1;
		a = 0;
		tiro = true;
		explosoes = new ArrayList<Explosao>();
		tiroInimigo = new ArrayList<EnemyTiro>();

		timer = new Timer(1000, this);
		timer.start();

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		a = 1;
		if (movimento == true) {
			a = 0;
		}

		timer = new Timer(500, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tiroInimigo();
				timer.setDelay(600000);

			}

		});

		timer.start();

	}

	public void explosoes() {
		this.explosoes.add(new Explosao(x + largura, y + altura / 2));

	}

	public void load() {
		if (vida < 3) {
			ImageIcon referencia = new ImageIcon("res\\enemy4.png");
			Imagem = referencia.getImage();

		}

		if (vida == 3 && vida < 4) {
			ImageIcon referencia = new ImageIcon("res\\enemy4.png");
			Imagem = referencia.getImage();
		}

		if (vida > 4) {
			ImageIcon referencia = new ImageIcon("res\\enemy4.png");
			Imagem = referencia.getImage();
		}

		this.altura = Imagem.getHeight(null);
		this.largura = Imagem.getWidth(null);
	}

	public void update() {
		if (this.x < 0) {
			this.x = -100;
		} else {
			this.x -= VELOCIDADE;
			if (a == 0) {
				this.y -= 1;
				movimento = false;
			}
			if (a == 1) {
				this.y += 1;
				movimento = true;

			}

		}

	}

	public void tiroInimigo() {
		this.tiroInimigo.add(new EnemyTiro(x + largura, y + altura / 2));

	}

	public List<EnemyTiro> getTiroInimigo() {
		return tiroInimigo;
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

}
