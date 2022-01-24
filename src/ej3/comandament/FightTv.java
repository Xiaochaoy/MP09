package ej3.comandament;

public class FightTv {
    public static void main(String[] args) {
        Comandament c = new Comandament();
        MembreFamilia fill = new MembreFamilia("Pedro",c);
        MembreFamilia filla = new MembreFamilia("Petra",c);
        MembreFamilia iaia = new MembreFamilia("Petrusca",c);

        fill.start();
        filla.start();
        iaia.start();
    }
}
