package concurrenciaEjercicios;

import java.util.concurrent.Semaphore;

public class Bar2 {

    private int aforo = 8;
    private int hayActualmente = 0;
    private Semaphore semaforo = new Semaphore(aforo);

    public void entrar(int i) throws InterruptedException {        
        while (hayActualmente == aforo) {
			System.out.println("El cliente " + i + " espera para entrar");
			semaforo.acquire();
		}
		hayActualmente++;
		System.out.println("Ha conseguido entrar en el bar el cliente " + i);
    }

    public void salir(int i) {
    	hayActualmente--;
        semaforo.release();
        System.out.println("Ha salido del bar el cliente " + i);
    }
}
