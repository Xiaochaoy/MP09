package UF2.ej1;

import java.util.concurrent.Callable;

public class AlumneP implements Callable<Integer> {

    private String Nom;

    public AlumneP(String nom) {
        Nom = nom;
    }

    public int Examinar() {
        //Temps que triga a fer l'UF2.examen
        try {
            Thread.sleep((long) (Math.random()*2000)+1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //Nota de l'UF2.examen
        int nota = (int) (Math.random()*10);
        return nota;
    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String nom) {
        Nom = nom;
    }

    @Override
    public Integer call() throws Exception {
        return Examinar();
    }
}
