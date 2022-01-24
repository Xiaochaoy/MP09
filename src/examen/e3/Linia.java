package examen.e3;

public class Linia implements Runnable {
    @Override
    public void run() {
        String[] line = {"hola","adios","feo","guapo","listo","tonto"};
        int i = (int) (Math.random()*line.length);
        System.out.println(line[i]);
    }
}
