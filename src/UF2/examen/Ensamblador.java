package UF2.examen;

public class Ensamblador {
    Cistell mano;
    Cistell cuerpo;
    String nombre;
    int camiseta;

    public Ensamblador(Cistell mano, Cistell cuerpo, String nombre) {
        this.mano = mano;
        this.cuerpo = cuerpo;
        this.nombre = nombre;
    }

    public void start() {
        for (;;) {
            hacer();
            try{
                Thread.sleep((long) Math.random()*50);
            }catch (InterruptedException e){
                e.printStackTrace();
            }

            System.out.println("Tengo " + camiseta + " camisetas");
        }
    }

    private synchronized void hacer() {
        while (mano.cantidad - 2 <2 && cuerpo.cantidad == 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        mano.cantidad -= 2;
        cuerpo.cantidad -= 1;
        camiseta++;
        notifyAll();
    }
}
