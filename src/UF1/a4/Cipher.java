package UF1.a4;

import UF1.a4.utils.Xifrar;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;

public class Cipher {
    public static void main(String[] args) {
        Metodos.unoPuntoCinco();
        Metodos.unoPuntoSeis();
        Metodos.unoPuntoSiete();
        Metodos.unoPuntoOcho();
        try{
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Metodos.puntoDos();
    }
}
