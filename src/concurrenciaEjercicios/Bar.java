package concurrenciaEjercicios;

public class Bar {

	public int aforo = 2;
	public int hayActualmente = 0;
	
	public Object o1 = new Object();
	public Object o2 = new Object();

	public void entrar(int i) throws InterruptedException {
		
		synchronized(o1) {
			while (hayActualmente == aforo) {
				System.out.println("El cliente " + i + " espera para entrar");
				o1.wait();
			}
			hayActualmente++;
			System.out.println("Ha conseguido entrar en el bar el cliente " + i);
		}
		
	}

	public void salir(int i) {

		synchronized(o2) {
			hayActualmente--;			
			System.out.println("Ha salido del bar el cliente " + i);	
			synchronized(o1) {
				o1.notify();
			}
		}
		
	}

}
