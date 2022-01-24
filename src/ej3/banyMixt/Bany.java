package ej3.banyMixt;

public class Bany {

    private short estat; //0:LLIURE 1:HI HA DONES 2:HI HA HOMES 3:NENS 4:PLE
    private int num;
    private int MAXP;
    String s = "";

    public Bany(int maxp) {
        estat = 0;
        num = 0;
        MAXP = maxp;
    }

    public synchronized short getEstat() {
        return estat;
    }

    public synchronized void entrar(short genere) {
        try {

            while ((estat!=0 && genere!=estat) || estat==4) wait();
            //a dins
            //si estava lliure posem l'estat del bany coincident amb el gènere que ha entrat
            if(estat == 0) estat = genere;
            if(++num == MAXP) estat = 4;

            switch (estat) {
                case 0: s="buit";break;
                case 1: s="dones";break;
                case 2: s="homes";break;
                case 3: s="nen";break;
                case 4: s="ple";
            }
            System.out.println("Somos " + num + " " + s);
            notifyAll();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public synchronized void sortir() {
        num--;
        System.out.println("surt " + estat);
        //si queda lliure actualitzem l'estat a 0
        if(num == 0) estat = 0;
        notifyAll();
    }

    public static void main(String... args) {
        Bany banyMixte = new Bany(3);
        Persona dones[] = new Persona[50];
        Persona homes[] = new Persona[50];
        Persona nens[] = new Persona[50];

        for(int i=0;i<50;i++) {
            dones[i] = new Persona(banyMixte,"dona" + i, (short) 1);
            homes[i] = new Persona(banyMixte,"home" + i, (short) 2);
            nens[i] = new Persona(banyMixte,"nen" + i, (short) 3);
        }

        for(int i=0;i<50;i++) {
            dones[i].start();
            homes[i].start();
            nens[i].start();
        }


    }
}