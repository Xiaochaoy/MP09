package examen;

public class Cosidor {
    Cistell cistell;
    String nombre;
    boolean lleno;
    Cosidor(Cistell cistell, String nombre){
        this.cistell = cistell;
        this.nombre = nombre;
    }

    public void start(){
        for (;;) {
            hacer();
            try{
                Thread.sleep((long) Math.random()*50);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            System.out.println("Tengo " + cistell.cantidad + " " + nombre);

            descanso();
            try{
                Thread.sleep((long) Math.random()*500);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    public synchronized void hacer(){
        lleno = false;
        cistell.cantidad++;

        if (lleno) notifyAll();


    }
    public synchronized void descanso(){
        while (cistell.cantidad == cistell.maximo) {
            try {
                lleno = true;
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
