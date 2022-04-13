package UF2.ej3;

public class Cargol extends Thread {
    private String name;

    public Cargol(String name) {
        super(name);
    }

    @Override
    public void run() {
        System.out.println("Hola sóc " + getName());
    }

    public static void main(String[] args) {
        Cargol cargol = new Cargol("treubanya");
        cargol.start();

    }
}