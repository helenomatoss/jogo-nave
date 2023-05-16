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

public class Chefao implements ActionListener {

	private Image Imagem;
	private int x, y;
	private int a;
	private int largura, altura;
	private boolean isVisivel, movimento, tiro, hit, tiroBossTemp;
	private List<BossTiro> tiroBoss;
	private int vida;
	private int yPlayer;
	private Timer timer, timer2;

	private static final int LARGURA = 1024;
	private static int VELOCIDADE = 0;
	private int VELOCIDADE2 = 1;

	public Chefao(int x, int y) {
		this.x = x;
		this.y = 100;
		isVisivel = true;
		vida = 0;
		a = 0;
		tiro = true;
		tiroBoss = new ArrayList<BossTiro>();

		hit = false;

		timer = new Timer(1000, this);
		timer.start();

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (isVisivel == true) {
			tiroBoss();
		} else {

		}
	}

	public void load() {
		if (hit == false) {
			ImageIcon referencia = new ImageIcon("res\\Chefao.png");
			Imagem = referencia.getImage();

		}

		if (hit == true) {
			ImageIcon referencia = new ImageIcon("res\\ChefaoHit.png");
			Imagem = referencia.getImage();

		}

		this.altura = Imagem.getHeight(null);
		this.largura = Imagem.getWidth(null);
	}

	public void update() {
		if (this.x < 0) {
			this.x = -100;

		} else {
			this.x -= VELOCIDADE2;

			this.y -= VELOCIDADE;

		}

	}

	public int getVELOCIDADE2() {
		return VELOCIDADE2;
	}

	public void setVELOCIDADE2(int vELOCIDADE2) {
		VELOCIDADE2 = vELOCIDADE2;
	}

	public static int getVELOCIDADE() {
		return VELOCIDADE;
	}

	public void setyPlayer(int yPlayer) {
		this.yPlayer = yPlayer;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void tiroBoss() {
		this.tiroBoss.add(new BossTiro((x + largura / 2) - 200, (y + altura / 2) - 150));

	}

	public List<BossTiro> getTiroBoss() {
		return tiroBoss;
	}

	public boolean isHit() {
		return hit;
	}

	public void setHit(boolean hit) {
		this.hit = hit;
	}

	public int getLargura() {
		return largura;
	}

	public int getAltura() {
		return altura;
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

}
