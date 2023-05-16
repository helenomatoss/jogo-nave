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

public class GreenFire extends Nave implements ActionListener {

	private Image Imagem;
	private int x, y;
	private int largura, altura;
	private boolean isVisivel, tiro;
	private List<EnemyTiro> tiroInimigo;
	private List<Explosao> explosoes;

	private Timer timer;

	private static final int LARGURA = 1024;
	private static int VELOCIDADE = 2;

	public GreenFire(int x, int y) {
		this.x = x;
		this.y = y;
		isVisivel = true;
		tiro = true;

		explosoes = new ArrayList<Explosao>();
		tiroInimigo = new ArrayList<EnemyTiro>();

		timer = new Timer(1500, this);
		timer.start();

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (x < LARGURA) {
			if (tiro == true) {
				tiroInimigo();
			}
		}

	}

	public void explosoes() {
		this.explosoes.add(new Explosao(x + largura, y + altura / 2));

	}

	public void load() {
		ImageIcon referencia = new ImageIcon("res\\enemy2.png");
		Imagem = referencia.getImage();

		this.altura = Imagem.getHeight(null);
		this.largura = Imagem.getWidth(null);
	}

	public void update() {
		if (this.x < 0) {
			this.x = (int) (Math.random() * 8000 + 1024);
			this.y = (int) (Math.random() * 694 + 68);

		} else {
			this.x -= VELOCIDADE;
		}

	}

	public void tiroInimigo() {
		this.tiroInimigo.add(new EnemyTiro(x + largura / 2, y + altura / 2));

	}

	public List<EnemyTiro> getTiroInimigo() {
		return tiroInimigo;
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