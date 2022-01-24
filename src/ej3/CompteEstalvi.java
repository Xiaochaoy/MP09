package ej3;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class CompteEstalvi {
    private int saldo;

    public CompteEstalvi() {
        saldo = 0;
    }

    public synchronized int getSaldo() {
        return saldo;
    }

    private synchronized void setSaldo(int saldo) {
        this.saldo = saldo;
    }

    public synchronized void ingres(int diners) {
        int aux;
        aux=getSaldo();
        aux=aux+diners;
        setSaldo(aux);
        System.out.print("Ingresso "+ diners + '\n');
    }

    public synchronized void treu(int diners) {
        int aux;
        aux=getSaldo();
        aux=aux-diners;
        setSaldo(aux);
        System.out.print("trec "+diners+ '\n');
    }

    public static void main(String[] args) throws InterruptedException {
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newCachedThreadPool();
        final CompteEstalvi b = new CompteEstalvi();
        List<Thread> llistaT = new ArrayList<>();

        //        Thread threadIngresa = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                b.ingres(100);
//            }
//        });
//
//        Thread threadTreu = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                b.treu(50);
//            }
//        });
//        for(int x=0;x<1000;x++) {
//            threadIngresa.start();
//            threadTreu.start();
//        }
//        threadIngresa.join();
//        threadTreu.join();

        for (int i = 0; i < 1000; i++) {
            Thread threadIngres = new Thread(() -> b.ingres(100));
            Thread threadTreu = new Thread(() -> b.treu(50));

            llistaT.add(threadIngres);
            llistaT.add(threadTreu);

            threadIngres.start();
            threadTreu.start();
        }
//        llistaT.forEach(thread -> {
//            try {
//                thread.join();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        });

        for (Thread th: llistaT) {
            th.join();
        }

        System.out.println("Saldo final: "+ b.getSaldo());

    }
}


