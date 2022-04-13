package UF2.examen.e4;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

public class MainMagicNumber {

    public static void main(String[] args) throws InterruptedException {
        List<MagicN> llista_dates = new ArrayList<>();
        List<Integer> llista_magics = new ArrayList<>();

        //casos de prova
        llista_dates.add(new MagicN(LocalDate.of(2019, 1, 1)));
        llista_dates.add(new MagicN(LocalDate.of(2020, 1, 2)));
        llista_dates.add(new MagicN(LocalDate.of(2021, 2, 1)));
        llista_dates.add(new MagicN(LocalDate.of(2018, 10, 10)));
        llista_dates.add(new MagicN(LocalDate.of(2016, 12, 12)));
        llista_dates.add(new MagicN(LocalDate.of(2017, 7, 8)));
        llista_dates.add(new MagicN(LocalDate.of(1998, 10, 2)));
        llista_dates.add(new MagicN(LocalDate.of(2000, 12, 20)));
        llista_dates.add(new MagicN(LocalDate.of(2001, 7, 16)));
        llista_dates.add(new MagicN(LocalDate.of(1974, 7, 16)));


        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(3);

        List<Future<Integer>> llistaResultado;
        llistaResultado = executor.invokeAll(llista_dates);
        executor.shutdown();


        for (int i = 0; i < llistaResultado.size(); i++) {
            Future<Integer> resultat = llistaResultado.get(i);
            try {
                System.out.println(resultat.get());
            } catch (InterruptedException | ExecutionException e) {
                e.getStackTrace();
            }
        }
    }
}
