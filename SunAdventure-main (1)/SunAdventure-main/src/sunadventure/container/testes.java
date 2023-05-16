package sunadventure.container;

public class testes {
	public static void main(String[] args) {
		Boolean game = true;
		while (game == true){
			int sort = (int) (Math.random() * 2);
			if(sort == 1) {
				System.out.println("deu 1");
			}else {
				System.out.println("deu 2");
			}
		}
	}
}
