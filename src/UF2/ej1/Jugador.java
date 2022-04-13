package UF2.ej1;

public class Jugador implements Comparable<Jugador> {
    private String Nom;
    private int Punts;

    public Jugador(String Nom) {
        this.Nom = Nom;
    }

    public Jugador(String Nom, int p) {
        this.Nom = Nom;
        Punts = p;
    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String nom) {
        Nom = nom;
    }

    public int getPunts() {
        return Punts;
    }

    public void setPunts(int punts) {
        Punts += punts;
    }

    @Override
    public int compareTo(Jugador o) {
        int byPunts = Integer.compare(this.Punts, o.getPunts());
        return byPunts;
    }
}

