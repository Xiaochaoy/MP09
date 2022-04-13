package UF2.ej3.banyMixt;

public class Persona extends Thread {
    private Bany bany;
    private short genere;

    public Persona(Bany b) {
        bany = b;
    }

    public Persona(Bany b, String name, short g) {
        super(name);
        genere = g;
        bany = b;
    }

    public Persona(ThreadGroup group, String name) {
        super(group, name);
    }

    @Override
    public void run() {
        for(;;) {
            bany.entrar(genere);
            UtilitzarBany();
            bany.sortir();
            Treballar();
        }
    }

    private void Treballar() {
        try {
            Thread.sleep((long) Math.random()*8000+2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void UtilitzarBany() {
        System.out.println(getName() + " esta en banyo...");
        try {
            Thread.sleep((long) Math.random()*2000+1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}