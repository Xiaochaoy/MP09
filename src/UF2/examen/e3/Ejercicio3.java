package UF2.examen.e3;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Ejercicio3{
    public static void main(String[] args){
        Linia linia = new Linia();

        ScheduledExecutorService schExService = Executors.newScheduledThreadPool(1);
        schExService.scheduleWithFixedDelay(linia, 0, 5, TimeUnit.SECONDS);

        try {
            schExService.awaitTermination(1, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        schExService.shutdownNow();
    }
}
