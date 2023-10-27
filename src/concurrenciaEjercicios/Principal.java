package concurrenciaEjercicios;


public class Principal {

	public static void main(String[] args) {
		Bar3 b = new Bar3();
		for (int i = 0; i < 10; i++) {
			boolean z;
			int numero = (int)(Math.random()*2+1);
			if(numero == 1) {
				z = true;
			}else {
				z = false;				
			}
			Cliente c = (new Cliente(i, b,z));
			c.start();
		}

	}

}
