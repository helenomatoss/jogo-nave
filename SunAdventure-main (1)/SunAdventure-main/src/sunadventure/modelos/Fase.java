package sunadventure.modelos;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.ImageIcon;

import javax.swing.JPanel;
import javax.swing.Timer;

import sunadventure.songs.EfeitosSonoros;

public class Fase extends JPanel implements ActionListener {

	private Image fundo;
	private Player player;
	private Timer timer, timer2;
	private boolean emJogo, emExplosao;
	private List<RedUfo> redufo;
	private List<DeathFish> deathFish;
	private List<BigBang> bigBang;
	private List<GreenFire> greenfire;
	private List<Stars> stars;
	private List<PowerUps> powerUpsVida, powerUpsTurbo, powerUpsMissel;
	private List<Explosao> explosoes;
	private EfeitosSonoros musica;
	private Chefao chefao;
	private int vida2 = 0, powerUpVida, powerUpTurbo, powerUpMissel;

	// Construtor
	public Fase() {
		setFocusable(true);
		setDoubleBuffered(true);

		// Teclado
		addKeyListener(new TecladoAdapter());

		// Fundo da fase
		ImageIcon referencia = new ImageIcon("res\\Blackground.png");
		fundo = referencia.getImage();

		// Player
		player = new Player();
		player.load();

		// Inimigos e PowerUps
		powerUpVida = 0;
		powerUpTurbo = 0;
		powerUpsTurbo = new ArrayList<PowerUps>();
		powerUpsVida = new ArrayList<PowerUps>();
		powerUpsMissel = new ArrayList<PowerUps>();

		inicializaInimigos();
		inicializaStars();
		inicializaExplosoes();

		// Timers
		waitForSeconds();
		timer = new Timer(5, this);
		timer.start();

		emJogo = true;
	}

	public void inicializaExplosoes() {
		explosoes = new ArrayList<Explosao>();

	}

	public void inicializaInimigos() {
		int cordenadas[] = new int[40];
		redufo = new ArrayList<RedUfo>();
		for (int i = 0; i < cordenadas.length; i++) {
			int x = (int) (Math.random() * 8000 + 1024);
			int y = (int) (Math.random() * 650 + 30);
			redufo.add(new RedUfo(x, y));
		}
		greenfire = new ArrayList<GreenFire>();
		for (int u = 0; u < cordenadas.length; u++) {
			int x = (int) (Math.random() * 12000 + 1024);
			int y = (int) (Math.random() * 650 + 30);
			greenfire.add(new GreenFire(x, y));

		}

		deathFish = new ArrayList<DeathFish>();
		for (int o = 0; o < cordenadas.length; o++) {
			int x = (int) (Math.random() * 12000 + 8000);
			int y = (int) (Math.random() * 650 + 10);

			deathFish.add(new DeathFish(x, y));

		}

		bigBang = new ArrayList<BigBang>();
		for (int o = 0; o < cordenadas.length; o++) {
			int x = (int) (Math.random() * 12000 + 6000);
			int y = (int) (Math.random() * 650 + 10);

			bigBang.add(new BigBang(x, y));
		}

		chefao = new Chefao(1024, 100);
		chefao.setVisivel(false);

	}

	public void inicializaStars() {
		int cordenadas[] = new int[300];
		stars = new ArrayList<Stars>();
		for (int i = 0; i < cordenadas.length; i++) {
			Random a = new Random();
			int x = a.nextInt(1024);
			Random r = new Random();
			int y = r.nextInt(768);
			stars.add(new Stars(x, y));

		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		// Player
		player.update();

		if (player.isTurbo() == true) {
			timer.setDelay(1);
		}

		if (player.isTurbo() == false) {
			timer.setDelay(5);
		}

		// Tiros Player
		List<Tiro> tiros = player.getTiros();
		for (int i = 0; i < tiros.size(); i++) {
			Tiro m = tiros.get(i);
			if (m.isVisivel()) {
				m.update();
				if (player.isTurbo() == true) {
					m.setVELOCIDADE(0);
					if (m.getX() == 0) {
						tiros.remove(i);
					}
				}
				if (player.isTurbo() == false) {
					m.setVELOCIDADE(6);
				}
			} else {
				tiros.remove(i);
			}
		}

		List<Missel> misseis = player.getMisseis();

		for (int i = 0; i < misseis.size(); i++) {
			Missel n = misseis.get(i);
			if (n.isVisivel()) {
				n.update();
				if (player.isTurbo() == true) {
					n.setVELOCIDADE(-1);
					if (n.getX() == 0) {
						misseis.remove(i);
					}
				}
				if (player.isTurbo() == false) {
					n.setVELOCIDADE(1);
				}
			} else {
				misseis.remove(i);
			}
		}

		// RedUfo
		for (int l = 0; l < redufo.size(); l++) {
			RedUfo in = redufo.get(l);
			if (in.isVisivel()) {
				in.update();
				if (player.isTurbo() == true) {
					in.setVELOCIDADE(-3);
				}
				if (player.isTurbo() == false) {
					in.setVELOCIDADE(3);
				}
			} else {
				int enemyX, enemyY;
				enemyX = redufo.get(l).getX();
				enemyY = redufo.get(l).getY();
				redufo.remove(l);
				powerUpVida++;
				if (powerUpVida == 5) {
					powerUpsVida.add(new PowerUps(enemyX, enemyY));
					powerUpVida = 0;
				}
				explosoes.add(new Explosao(enemyX, enemyY));
			}
		}
		for (int l = 0; l < bigBang.size(); l++) {
			BigBang in = bigBang.get(l);
			if (in.isVisivel()) {
				in.update();
				if (player.isTurbo() == true) {
					in.setVELOCIDADE(-4);
				}
				if (player.isTurbo() == false) {
					in.setVELOCIDADE(4);
				}
			} else {
				int enemyX, enemyY;
				enemyX = bigBang.get(l).getX();
				enemyY = bigBang.get(l).getY();
				bigBang.remove(l);
				powerUpVida++;
				if (powerUpVida == 5) {
					powerUpsVida.add(new PowerUps(enemyX, enemyY));
					powerUpVida = 0;
				}
				explosoes.add(new Explosao(enemyX, enemyY));
			}
		}
		
		
		// GreenFire
		for (int l1 = 0; l1 < greenfire.size(); l1++) {
			GreenFire in2 = greenfire.get(l1);
			if (in2.isVisivel()) {
				in2.update();
				if (player.isTurbo() == true) {
					in2.setVELOCIDADE(2);
				}
				if (player.isTurbo() == false) {
					in2.setVELOCIDADE(2);
				}
			} else {
				int enemyX, enemyY;
				enemyX = greenfire.get(l1).getX();
				enemyY = greenfire.get(l1).getY();
				greenfire.remove(l1);
				powerUpMissel++;
				if (powerUpMissel == 3) {
					powerUpsMissel.add(new PowerUps(enemyX, enemyY));
					powerUpMissel = 0;
				}
				explosoes.add(new Explosao(enemyX, enemyY));
			}
		}

		// DeathFish
		for (int l = 0; l < deathFish.size(); l++) {
			DeathFish tn = deathFish.get(l);
			if (tn.isVisivel()) {
				tn.update();
				if (player.isTurbo() == true) {
					tn.setVELOCIDADE(1);
				}
				if (player.isTurbo() == false) {
					tn.setVELOCIDADE(1);
				}
			} else {
				int enemyX, enemyY;
				enemyX = deathFish.get(l).getX();
				enemyY = deathFish.get(l).getY();
				deathFish.remove(l);
				powerUpsVida.add(new PowerUps(enemyX, enemyY));
				explosoes.add(new Explosao(enemyX, enemyY));
			}
		}

		// Boss
		if (chefao.isVisivel()) {
			chefao.update();
			int a = player.getY();
			int b = chefao.getY();
			player.setTurboLiberar(0);

			if (player.isTurbo() == false) {
				a -= 220;

				if (a < (b)) {
					chefao.setVELOCIDADE(1);
				}
				if (a == b) {
					chefao.setVELOCIDADE(0);
				}
				if (a > (b)) {
					chefao.setVELOCIDADE(-1);
				}
				chefao.setVELOCIDADE2(1);

				if (player.isTurbo() == true) {
					chefao.setVELOCIDADE2(-5);
				}
				if (chefao.isVisivel() == true) {
					int c = chefao.getX();
					if (c == 600) {
						chefao.setVELOCIDADE2(0);

					}
				}
			}
		} else {
			int enemyX, enemyY;
			enemyX = chefao.getX();
			enemyY = chefao.getY();
			chefao.setVisivel(false);
		}

		// Tiro GreenFIre
		for (int q = 0; q < greenfire.size(); q++) {
			List<EnemyTiro> tiroInimigos = greenfire.get(q).getTiroInimigo();
			for (int o = 0; o < tiroInimigos.size(); o++) {
				EnemyTiro m = (EnemyTiro) tiroInimigos.get(o);
				if (m.isVisivel()) {
					m.update();
				} else {
					tiroInimigos.remove(o);
				}

			}
		}

		// Tiro DeathFish
		for (int q = 0; q < deathFish.size(); q++) {
			List<EnemyTiro> tiroInimigos = deathFish.get(q).getTiroInimigo();
			for (int o = 0; o < tiroInimigos.size(); o++) {
				EnemyTiro m = (EnemyTiro) tiroInimigos.get(o);
				if (m.isVisivel()) {
					m.update();
				} else {
					tiroInimigos.remove(o);
				}

			}
		}

		// Tiro Chefao
		List<BossTiro> tiroBoss = chefao.getTiroBoss();
		for (int o = 0; o < tiroBoss.size(); o++) {
			BossTiro im = tiroBoss.get(o);
			if (im.isVisivel()) {
				im.update();
			} else {
				tiroBoss.remove(o);
			}

		}

		// Explosoes
		for (int q = 0; q < explosoes.size(); q++) {
			Explosao y = explosoes.get(q);
			if (y.isVisivel()) {
				y.update();
			} else {
				explosoes.remove(q);
			}

		}

		// Estrelas
		for (int p = 0; p < stars.size(); p++) {
			Stars on = stars.get(p);
			if (on.isVisivel()) {
				on.update();
				if (player.isTurbo() == true) {
					if (chefao.isVisivel() == true) {
						on.setVELOCIDADE(0);
					} else {
						on.setVELOCIDADE(3);
					}
				}

				if (player.isTurbo() == false) {
					if (chefao.isVisivel() == true) {
						on.setVELOCIDADE(0);
					} else {
						on.setVELOCIDADE(1);
					}
				}
			} else {
				stars.remove(p);
			}
		}

		// PowerUps

		for (int p = 0; p < powerUpsVida.size(); p++) {
			PowerUps on = powerUpsVida.get(p);
			if (on.isVisivel()) {
				on.update();
				if (player.isTurbo() == true) {
					on.setVELOCIDADE(3);
				}

				if (player.isTurbo() == false) {
					on.setVELOCIDADE(1);
				}
			} else {
				powerUpsVida.remove(p);
			}
		}

		for (int p = 0; p < powerUpsTurbo.size(); p++) {
			PowerUps on = powerUpsTurbo.get(p);
			if (on.isVisivel()) {
				on.update();
				if (player.isTurbo() == true) {
					on.setVELOCIDADE(3);
				}

				if (player.isTurbo() == false) {
					on.setVELOCIDADE(1);
				}
			} else {
				powerUpsTurbo.remove(p);
			}
		}

		for (int p = 0; p < powerUpsMissel.size(); p++) {
			PowerUps on = powerUpsMissel.get(p);
			if (on.isVisivel()) {
				on.update();
				if (player.isTurbo() == true) {
					on.setVELOCIDADE(3);
				}

				if (player.isTurbo() == false) {
					on.setVELOCIDADE(1);
				}
			} else {
				powerUpsMissel.remove(p);
			}
		}

		checarColisoes();
		repaint();

	}

	// Temporizador
	public void waitForSeconds() {
		Timer timer;
		Timer timer2;
		Timer timerChefao;

		timer = new Timer(2000, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < explosoes.size(); i++) {
					explosoes.get(i).setVisivel(false);
				}

			}

		});
		timer.start();

		timer2 = new Timer(200, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (chefao.isHit() == true) {
					chefao.setHit(false);

				}
			}

		});
		timer2.start();

		timerChefao = new Timer(100000, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chefao.setVisivel(true);

			}

		});
		timerChefao.start();

	}

	// Colisões

	public void checarColisoes() {
		Rectangle formaNave = player.getBounds();
		Rectangle formaRedUfo;
		Rectangle formaTiro;
		Rectangle formaMissel;
		Rectangle formaGreenFire;
		Rectangle formaBigBang;
		Rectangle formaDeathFish;
		Rectangle formaTiroInimigo;
		Rectangle formaPowerUpVida;
		Rectangle formaPowerUpTurbo;
		Rectangle formaPowerUpMissel;
		Rectangle formaChefao;
		Rectangle formaTiroBoss;

	
		for (int i = 0; i < redufo.size(); i++) {
			RedUfo tempRedUfo = redufo.get(i);
			formaRedUfo = tempRedUfo.getBounds();

			if (formaNave.intersects(formaRedUfo)) {
				if (player.isTurbo() == true) {
					tempRedUfo.setVisivel(false);
					ImageIcon referencia2 = new ImageIcon("res\\explosion1.gif");
					emExplosao = true;
				}
				if (player.isTurbo() == false) {
					int a = player.getVida();

					player.setVida(player.getVida() - 1);
					tempRedUfo.setVisivel(false);

					if (a < 0) {
						player.setVisivel(false);
						tempRedUfo.setVisivel(false);
						emJogo = false;
					}

				}

			}
		}

		for (int i = 0; i < greenfire.size(); i++) {
			GreenFire tempGreenfire = greenfire.get(i);
			formaGreenFire = tempGreenfire.getBounds();
			if (formaNave.intersects(formaGreenFire)) {
				if (player.isTurbo() == true) {
					tempGreenfire.setVisivel(false);
					ImageIcon referencia2 = new ImageIcon("res\\explosion1.gif");
					emExplosao = true;
				}
				if (player.isTurbo() == false) {
					int a = player.getVida();

					player.setVida(player.getVida() - 1);
					tempGreenfire.setVisivel(false);

					if (a < 0) {
						player.setVisivel(false);
						tempGreenfire.setVisivel(false);
						emJogo = false;
					}

				}

			}
		}

		for (int i = 0; i < deathFish.size(); i++) {
			DeathFish tempDeathFish = deathFish.get(i);
			formaDeathFish = tempDeathFish.getBounds();
			if (formaNave.intersects(formaDeathFish)) {
				if (player.isTurbo() == true) {
					tempDeathFish.setVisivel(false);
					ImageIcon referencia2 = new ImageIcon("res\\explosion1.gif");
					emExplosao = true;
				}
				if (player.isTurbo() == false) {
					int a = player.getVida();

					player.setVida(player.getVida() - 2);
					tempDeathFish.setVisivel(false);

					if (a < 0) {
						player.setVisivel(false);
						tempDeathFish.setVisivel(false);
						emJogo = false;
					}

				}

			}
		}

		
		for (int i = 0; i < bigBang.size(); i++) {
			BigBang tempbigBang = bigBang.get(i);
			formaBigBang = tempbigBang.getBounds();
			if (formaNave.intersects(formaBigBang)) {
				if (player.isTurbo() == true) {
					tempbigBang.setVisivel(false);
					ImageIcon referencia2 = new ImageIcon("res\\explosion1.gif");
					emExplosao = true;
				}
				if (player.isTurbo() == false) {
					int a = player.getVida();

					player.setVida(player.getVida() - 1);
					tempbigBang.setVisivel(false);

					if (a < 0) {
						player.setVisivel(false);
						tempbigBang.setVisivel(false);
						emJogo = false;
					}

				}

			}
		}

	
		Chefao tempchefao = chefao;
		formaChefao = tempchefao.getBounds();
		if (formaNave.intersects(formaChefao)) {
			player.setVida(player.getVida() - 1);
			if (player.getVida() < 0) {
				player.setVisivel(false);
				emJogo = false;
			}

		}

	
		List<Tiro> tiros = player.getTiros();

		
		for (int i = 0; i < tiros.size(); i++) {
			Tiro temptiro = tiros.get(i);
			formaTiro = temptiro.getBounds();

			for (int j = 0; j < redufo.size(); j++) {
				RedUfo tempRedUfo = redufo.get(j);
				formaRedUfo = tempRedUfo.getBounds();

				if (formaTiro.intersects(formaRedUfo)) {
					ImageIcon referencia2 = new ImageIcon("res\\explosion1.gif");
					
					emExplosao = true;
					tempRedUfo.setVisivel(false);
					temptiro.setVisivel(false);
				}

			}

			for (int j = 0; j < greenfire.size(); j++) {
				GreenFire tempGreenFire = greenfire.get(j);
				formaGreenFire = tempGreenFire.getBounds();

				if (formaTiro.intersects(formaGreenFire)) {
					ImageIcon referencia2 = new ImageIcon("res\\explosion1.gif");
					
					emExplosao = true;
					tempGreenFire.setVisivel(false);
					temptiro.setVisivel(false);
				}

			}

			for (int j = 0; j < deathFish.size(); j++) {
				DeathFish tempDeathFish = deathFish.get(j);
				formaDeathFish = tempDeathFish.getBounds();
				if (formaTiro.intersects(formaDeathFish)) {
					int a = tempDeathFish.getVida();

					if (a < 6) {
						temptiro.setVisivel(false);
						tempDeathFish.setVida(a + 1);

					}
					if (a == 5) {
						ImageIcon referencia2 = new ImageIcon("res\\explosion1.gif");
						
						emExplosao = true;
						tempDeathFish.setVisivel(false);
						temptiro.setVisivel(false);
					}
				}

			}

			for (int u = 0; u < bigBang.size(); u++) {
				BigBang tempBigBang = bigBang.get(u);
				formaBigBang = tempBigBang.getBounds();
				if (formaTiro.intersects(formaBigBang)) {
					int a = tempBigBang.getVida();

					if (a < 3) {
						temptiro.setVisivel(false);
						tempBigBang.setVida(a + 1);

					}
					if (a == 3) {
						ImageIcon referencia2 = new ImageIcon("res\\explosion1.gif");
						
						emExplosao = true;
						tempBigBang.setVisivel(false);
						temptiro.setVisivel(false);
					}
				}

			}

			Chefao tempChefao = chefao;
			formaChefao = tempChefao.getBounds();
			if (formaTiro.intersects(formaChefao)) {
				chefao.setHit(true);
				int a = tempchefao.getVida();
				if (a < 120) {

					temptiro.setVisivel(false);
					tempchefao.setVida(a + 1);

				}
				if (a > 119) {
					ImageIcon referencia2 = new ImageIcon("res\\explosion1.gif");
					tempchefao.setVisivel(false);
					emExplosao = true;
					temptiro.setVisivel(false);

				}
			}

		}

		
		List<Missel> misseis = player.getMisseis();
		for (int i = 0; i < misseis.size(); i++) {
			Missel tempmisseis = misseis.get(i);
			formaMissel = tempmisseis.getBounds();

			for (int j = 0; j < redufo.size(); j++) {
				RedUfo tempRedUfo = redufo.get(j);
				formaRedUfo = tempRedUfo.getBounds();

				if (formaMissel.intersects(formaRedUfo)) {

					ImageIcon referencia2 = new ImageIcon("res\\explosion1.gif");
					
					emExplosao = true;
					tempRedUfo.setVisivel(false);

				}

			}

			for (int j = 0; j < greenfire.size(); j++) {
				GreenFire tempGreenFire = greenfire.get(j);
				formaGreenFire = tempGreenFire.getBounds();

				if (formaMissel.intersects(formaGreenFire)) {
					ImageIcon referencia2 = new ImageIcon("res\\explosion1.gif");
					
					emExplosao = true;
					tempGreenFire.setVisivel(false);

				}

			}

			for (int j = 0; j < deathFish.size(); j++) {
				DeathFish tempDeathFish = deathFish.get(j);
				formaDeathFish = tempDeathFish.getBounds();

				if (formaMissel.intersects(formaDeathFish)) {
					ImageIcon referencia2 = new ImageIcon("res\\explosion1.gif");
					
					emExplosao = true;
					tempDeathFish.setVisivel(false);

				}

			}

			for (int j = 0; j < bigBang.size(); j++) {
				BigBang tempBigBang = bigBang.get(j);
				formaDeathFish = tempBigBang.getBounds();

				if (formaMissel.intersects(formaDeathFish)) {
					ImageIcon referencia2 = new ImageIcon("res\\explosion1.gif");
					
					emExplosao = true;
					tempBigBang.setVisivel(false);

				}

			}

			Chefao tempChefao = chefao;
			formaChefao = tempChefao.getBounds();
			if (formaMissel.intersects(formaChefao)) {
				chefao.setHit(true);
				int a = tempchefao.getVida();
				if (a < 120) {

					tempmisseis.setVisivel(false);
					tempchefao.setVida(a + 5);

				}
				if (a > 119) {
					ImageIcon referencia2 = new ImageIcon("res\\explosion.gif");
					
					emExplosao = true;
					chefao.setVisivel(false);
					tempchefao.setVisivel(false);

				}
			}

		}

	

		for (int q = 0; q < greenfire.size(); q++) {
			List<EnemyTiro> tiroInimigos = greenfire.get(q).getTiroInimigo();
			for (int i = 0; i < tiroInimigos.size(); i++) {
				EnemyTiro tempEnemyTiro = tiroInimigos.get(i);
				formaTiroInimigo = tempEnemyTiro.getBounds();
				if (formaNave.intersects(formaTiroInimigo)) {
					if (player.isTurbo() == true) {
						tempEnemyTiro.setVisivel(false);
					}
					if (player.isTurbo() == false) {
						int a = player.getVida();

						player.setVida(player.getVida() - 1);
						tempEnemyTiro.setVisivel(false);

						if (a == 0) {
							player.setVisivel(false);
							tempEnemyTiro.setVisivel(false);
							emJogo = false;

						}

					}
				}
			}

		}

		for (int q = 0; q < deathFish.size(); q++) {
			List<EnemyTiro> tiroInimigos = deathFish.get(q).getTiroInimigo();
			for (int i = 0; i < tiroInimigos.size(); i++) {
				EnemyTiro tempEnemyTiro = tiroInimigos.get(i);
				formaTiroInimigo = tempEnemyTiro.getBounds();
				if (formaNave.intersects(formaTiroInimigo)) {
					if (player.isTurbo() == true) {
						tempEnemyTiro.setVisivel(false);
					}

					if (player.isTurbo() == false) {
						int a = player.getVida();

						player.setVida(player.getVida() - 3);
						tempEnemyTiro.setVisivel(false);

						if (a == 0) {
							player.setVisivel(false);
							tempEnemyTiro.setVisivel(false);
							emJogo = false;
						}

					}

				}

			}
		}

		List<BossTiro> tiroBoss = chefao.getTiroBoss();
		for (int i = 0; i < tiroBoss.size(); i++) {
			BossTiro temptiroBoss = tiroBoss.get(i);
			formaTiroBoss = temptiroBoss.getBounds();
			if (formaTiroBoss.intersects(formaNave)) {
				int a = player.getVida();
				player.setVida(player.getVida() - 2);
				temptiroBoss.setVisivel(false);

				if (a < 0 || a == 0) {
					player.setVisivel(false);
					temptiroBoss.setVisivel(false);
					emJogo = false;

				}

			}
		}

		

		for (int i = 0; i < powerUpsVida.size(); i++) {
			PowerUps tempPowerUpVida = powerUpsVida.get(i);
			formaPowerUpVida = tempPowerUpVida.getBounds();

			if (formaNave.intersects(formaPowerUpVida)) {
				if (player.getVida() < 6) {
					player.setVida(player.getVida() + 1);
					powerUpsVida.remove(i);
				}
			}
		}

		for (int i = 0; i < powerUpsTurbo.size(); i++) {
			PowerUps tempPowerUpTurbo = powerUpsTurbo.get(i);
			formaPowerUpTurbo = tempPowerUpTurbo.getBounds();

			if (formaNave.intersects(formaPowerUpTurbo)) {
				if (player.isTurbo() == false) {
					if (player.getTurboLiberar() < 3) {
						player.setTurboLiberar(player.getTurboLiberar() + 1);
						powerUpsTurbo.remove(i);
					}
				}

			}
		}

		for (int i = 0; i < powerUpsMissel.size(); i++) {
			PowerUps tempPowerUpMissel = powerUpsMissel.get(i);
			formaPowerUpMissel = tempPowerUpMissel.getBounds();

			if (formaNave.intersects(formaPowerUpMissel)) {
				if (player.isTurbo() == false) {
					if (player.getMisselLiberar() < 30) {
						player.setMisselLiberar(player.getMisselLiberar() + 3);
						powerUpsMissel.remove(i);
					}
				}

			}
		}

	}

	public void paint(Graphics g) {
		Graphics2D graficos = (Graphics2D) g;
		if (emJogo) {
			
			graficos.drawImage(fundo, 0, 0, null);

			
			for (int k = 0; k < stars.size(); k++) {
				Stars on = stars.get(k);
				on.load();
				if (player.isTurbo() == false) {
					graficos.drawImage(on.getImagem(), on.getX(), on.getY(), null);
				}
				if (player.isTurbo() == true) {
					graficos.drawImage(on.getImagem2(), on.getX(), on.getY(), null);
				}

			}

			
			graficos.drawImage(player.getImagem(), player.getX(), player.getY(), this);

		
			List<Tiro> tiros = player.getTiros();

			for (int i = 0; i < tiros.size(); i++) {
				Tiro m = tiros.get(i);
				m.load();
				graficos.drawImage(m.getImagem(), m.getX(), m.getY(), this);
			}

			
			List<Missel> misseis = player.getMisseis();

			for (int i = 0; i < misseis.size(); i++) {
				Missel n = misseis.get(i);
				n.load();
				graficos.drawImage(n.getImagem(), n.getX(), n.getY(), this);
			}

		

			for (int o = 0; o < redufo.size(); o++) {
				RedUfo in = redufo.get(o);
				in.load();
				graficos.drawImage(in.getImagem(), in.getX(), in.getY(), this);
			}



			for (int o = 0; o < deathFish.size(); o++) {
				DeathFish tn = deathFish.get(o);
				tn.load();
				graficos.drawImage(tn.getImagem(), tn.getX(), tn.getY(), this);
			}

			for (int p = 0; p < greenfire.size(); p++) {
				GreenFire qn = greenfire.get(p);
				qn.load();
				graficos.drawImage(qn.getImagem(), qn.getX(), qn.getY(), this);
			}

		

			for (int l = 0; l < bigBang.size(); l++) {
				BigBang qn = bigBang.get(l);
				qn.load();
				graficos.drawImage(qn.getImagem(), qn.getX(), qn.getY(), this);

			}

		
			if (chefao.isVisivel()) {
				chefao.load();
				graficos.drawImage(chefao.getImagem(), chefao.getX(), chefao.getY(), this);

				if (chefao.getVida() > 0 && chefao.getVida() < 30) {
					ImageIcon vidaBoss = new ImageIcon("res\\VidaBoss.png");
					graficos.drawImage(vidaBoss.getImage(), 250, 650, null);
				}
				if (chefao.getVida() > 29 && chefao.getVida() < 50) {
					ImageIcon vidaBoss = new ImageIcon("res\\VidaBoss1Hit.png");
					graficos.drawImage(vidaBoss.getImage(), 250, 650, null);
				}
				if (chefao.getVida() > 49 && chefao.getVida() < 80) {
					ImageIcon vidaBoss = new ImageIcon("res\\VidaBoss2Hit.png");
					graficos.drawImage(vidaBoss.getImage(), 250, 650, null);
				}
				if (chefao.getVida() > 79 && chefao.getVida() < 100) {
					ImageIcon vidaBoss = new ImageIcon("res\\VidaBoss3Hit.png");
					graficos.drawImage(vidaBoss.getImage(), 250, 650, null);
				}

				if (chefao.getVida() > 99 && chefao.getVida() < 120) {
					ImageIcon vidaBoss = new ImageIcon("res\\VidaBoss4Hit.png");
					graficos.drawImage(vidaBoss.getImage(), 250, 650, null);
				}

				if (chefao.getVida() > 119) {
					ImageIcon vidaBoss = new ImageIcon("res\\VidaBoss5Hit.png");
					graficos.drawImage(vidaBoss.getImage(), 250, 650, null);
				}
			}
			if (chefao.isVisivel() == false) {
				ImageIcon explosaoChefe = new ImageIcon("res\\explosion.gif");
				graficos.drawImage(explosaoChefe.getImage(), chefao.getX(), chefao.getY(), this);

			}

		
			for (int q = 0; q < greenfire.size(); q++) {
				List<EnemyTiro> tiroInimigos = greenfire.get(q).getTiroInimigo();
				for (int o = 0; o < tiroInimigos.size(); o++) {
					EnemyTiro m = (EnemyTiro) tiroInimigos.get(o);
					m.load();
					graficos.drawImage(m.getImagem(), m.getX(), m.getY(), this);
				}
			}

			for (int s = 0; s < deathFish.size(); s++) {
				List<EnemyTiro> tiroInimigos = deathFish.get(s).getTiroInimigo();
				for (int o = 0; o < tiroInimigos.size(); o++) {
					EnemyTiro m = (EnemyTiro) tiroInimigos.get(o);
					m.load();
					graficos.drawImage(m.getImagem(), m.getX(), m.getY(), this);
				}
			}

		
			List<BossTiro> tiroBoss = chefao.getTiroBoss();
			for (int o = 0; o < tiroBoss.size(); o++) {
				BossTiro m = (BossTiro) tiroBoss.get(o);
				m.load();
				if (chefao.isVisivel()) {
					graficos.drawImage(m.getImagem(), m.getX(), m.getY(), this);
				}
			}

			

			for (int h = 0; h < explosoes.size(); h++) {
				Explosao ln = explosoes.get(h);
				ln.load();
				graficos.drawImage(ln.getImagem(), ln.getX(), ln.getY(), this);
			}

			
			for (int k = 0; k < powerUpsVida.size(); k++) {
				PowerUps on = powerUpsVida.get(k);
				on.load_Vida();
				if (player.isTurbo() == false) {
					graficos.drawImage(on.getVida(), on.getX(), on.getY(), null);
				}
				if (player.isTurbo() == true) {
					graficos.drawImage(on.getVida(), on.getX(), on.getY(), null);
				}

			}

			for (int w = 0; w < powerUpsTurbo.size(); w++) {
				PowerUps in = powerUpsTurbo.get(w);
				in.load_Turbo();
				if (player.isTurbo() == false) {
					graficos.drawImage(in.getTurbo(), in.getX(), in.getY(), null);
				}
				if (player.isTurbo() == true) {
					graficos.drawImage(in.getTurbo(), in.getX(), in.getY(), null);
				}

			}

			for (int p = 0; p < powerUpsMissel.size(); p++) {
				PowerUps pn = powerUpsMissel.get(p);
				pn.load_Missel();
				if (player.isTurbo() == false) {
					graficos.drawImage(pn.getMissel(), pn.getX(), pn.getY(), null);
				}
				if (player.isTurbo() == true) {
					graficos.drawImage(pn.getMissel(), pn.getX(), pn.getY(), null);
				}

			}

			

			ImageIcon tamplate1 = new ImageIcon("res\\tamplateIco.png");
			graficos.drawImage(tamplate1.getImage(), 5, 10, null);

			int a = 5;
			for (int j = 0; j < player.getVida(); j++) {
				ImageIcon vida = new ImageIcon("res\\Vida.png");
				graficos.drawImage(vida.getImage(), a, 20, null);
				a += 30;
			}

			ImageIcon tamplate2 = new ImageIcon("res\\TurboTamplate.png");
			graficos.drawImage(tamplate2.getImage(), 790, 20, null);

			int b = 900;
			for (int j = 0; j < player.getTurboLiberar(); j++) {
				ImageIcon turbo = new ImageIcon("res\\TurboPowerup.png");
				graficos.drawImage(turbo.getImage(), b, 40, null);
				b -= 50;
			}

			ImageIcon tamplate3 = new ImageIcon("res\\missilTamplate.png");
			graficos.drawImage(tamplate3.getImage(), 465, 10, null);

			ImageIcon missel = new ImageIcon("res\\Misselico.png");
			graficos.drawImage(missel.getImage(), 464, 20, null);
			graficos.setColor(Color.white);
			g.setFont(new Font("Arial", 1, 20));
			graficos.drawString(" " + player.getMisselLiberar(), 505, 52);

			graficos.drawString(" " + chefao.getVida(), 505, 100);

		}

		else {
			ImageIcon fimJogo = new ImageIcon("res\\fimdejogo.png");
			graficos.drawImage(fimJogo.getImage(), 0, 0, null);
		}

		g.dispose();
	}

	public void SomFase() {
		musica.tocarFase();

	}

	private class TecladoAdapter extends KeyAdapter {

		@Override
		public void keyPressed(KeyEvent e) {
			player.keyPressed(e);
		}

		@Override
		public void keyReleased(KeyEvent e) {
			player.keyReleased(e);
		}
	}

}
