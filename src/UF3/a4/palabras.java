package UF3.a4;

public class palabras {

    /* Classe que gestiona una dada de velocitat (int) aleatòria, per implementar l'exemple de Multicast:
     * ClientVelocimetre1.java
     * ClientVelocimetre2.java
     * SrvVelocitats.java
     */
    String[] letras = {"hola", "adios", "calvo", "rata"};
    String letra;
    public palabras() {
    }

    public String[] getLetras() {
        return letras;
    }

//    public void setVel(int vel) {
//        this.vel = vel;
//    }

    public String agafapalabra() {
        this.letra = letras[(int)(Math.random()* letras.length)];
        return letra;
    }

}
