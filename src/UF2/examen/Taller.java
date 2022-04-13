package UF2.examen;
public class Taller {

    public static void main(String[] args) {
        Cistell cistell_manigues = new Cistell(8);
        Cistell cistell_cossos = new Cistell(5);
        Cosidor cosidor_manigues = new Cosidor(cistell_manigues, "Maniga");
        Cosidor cosidor_cossos = new Cosidor(cistell_cossos, "Cos");
        Ensamblador ensamblador = new Ensamblador(cistell_manigues, cistell_cossos, "Peça");

        cosidor_manigues.start();
        cosidor_cossos.start();
        ensamblador.start();
    }
}
