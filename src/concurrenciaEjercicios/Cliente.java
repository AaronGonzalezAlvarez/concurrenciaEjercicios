package concurrenciaEjercicios;


public class Cliente extends Thread {

	int i;
	Bar3 b;
	boolean alien;

	public Cliente(int i, Bar3 b, boolean alien) {
		this.b = b;
		this.i = i;
		this.alien = alien;
	}

	@Override
	public void run() {
		boolean ewok;
		try {					
			b.entrar(i,alien);
			Thread.sleep(5*1000);
			b.salir(i,alien);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
