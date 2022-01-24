package ej1;

public class pLlegirPuntsJugadors implements Runnable {
    Jugador jug;

    public pLlegirPuntsJugadors(Jugador j) {
        jug = j;
    }

    @Override
    public void run() {
        System.out.println("El jugador " + jug.getNom() + " porta " + jug.getPunts());
    }

}
