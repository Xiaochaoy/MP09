package UF2.ej3.comandament;

public class Comandament {
    private boolean ocupat;

    public Comandament(){
        this.ocupat = false;
    }

    public synchronized void agafar(){
        while (ocupat) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        ocupat = true;
        notifyAll();
    }
    public synchronized void deixar(){
        ocupat = false;
        notifyAll();
    }
}
