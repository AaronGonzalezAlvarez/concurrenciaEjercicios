package concurrenciaEjercicios;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Bar3 {

    private int aforo = 5;
    private int hayActualmente = 0;
    private int colaEwok =0;
    private int colaGorax =0;
    private ReentrantLock lock = new ReentrantLock();
    private Condition ewokEsperar = lock.newCondition();
    private Condition goraxEsperar = lock.newCondition();

    public void entrar(int i,boolean isEwok) throws InterruptedException {
        lock.lock();
        try {
            while ((hayActualmente == aforo)) {
            	if(isEwok) {
            		colaEwok++;
            		ewokEsperar.await();
            		System.out.println("un ewok a la espera de entrar " + i);
            		System.out.println("Cola ewok: "+ colaEwok);
            		System.out.println("Cola gorax: "+ colaGorax);
            	}else {
            		colaGorax++;
            		goraxEsperar.await();
            		System.out.println("un gorax a la espera de entrar " + i);
            		System.out.println("Cola ewok: "+ colaEwok);
            		System.out.println("Cola gorax: "+ colaGorax);
            	}
            }
            
            if(isEwok) {
            	if(colaEwok>0) {
            		colaEwok--;
            	}
        		System.out.println("Ha conseguido ewok en el bar el cliente blanco " + i);
        	}else if(!isEwok) {
        		if(colaGorax>0) {
        			colaGorax--;
            	}
        		System.out.println("Ha conseguido gorax en el bar el cliente blanco " + i);
        	}                          
            hayActualmente++;
            
        } finally {
            lock.unlock();
        }
    }

    public void salir(int i,boolean isEwok) {
        lock.lock();
        try {                     
            if(isEwok) {
            	hayActualmente--;            	
            	 System.out.println("Ha salido del bar un ewok " + i);            	 
        	}else {
        		hayActualmente--;
        		 System.out.println("Ha salido del bar un gorax " + i);
        	}
            
            if(colaEwok >0) {
            	ewokEsperar.signal();
            }else {
            	goraxEsperar.signal();
            }
            System.out.println("cola actual: - colaEwok: "+ colaEwok +" colaGorax: " + colaGorax);
        } finally {
            lock.unlock();
        }
    }
}