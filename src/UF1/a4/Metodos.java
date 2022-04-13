package UF1.a4;

import UF1.a4.utils.Xifrar;

import javax.crypto.BadPaddingException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Metodos {
    public static void unoPuntoCinco() {
        SecretKey secretKey = Xifrar.keygenKeyGeneration(128);
        String texto = "wñrklhntehgwefpirnwg";
        byte[] bytes = texto.getBytes(StandardCharsets.UTF_8);

        byte[] enciptado = Xifrar.encryptData(secretKey, bytes);
        byte[] desciptado = Xifrar.decryptData(secretKey, enciptado);

        String msg = new String(desciptado, 0, desciptado.length).toLowerCase();
        System.out.println("1.5");
        System.out.println("Texto original: " + texto);
        System.out.println("Texto en byte: " + bytes);
        System.out.println("Byte enciptado: " + enciptado);
        System.out.println("Byte desciptado: " + desciptado);
        System.out.println("Secreto Key: " + secretKey);
        System.out.println("Texto final: " + msg);

    }

    public static void unoPuntoSeis() {
        SecretKey secretKey = Xifrar.passwordKeyGeneration("Best Listo", 192);
        String texto = "wrisgknljerh rnyjñwqoeru";
        byte[] bytes = texto.getBytes(StandardCharsets.UTF_8);

        byte[] enciptado = Xifrar.encryptData(secretKey, bytes);
        byte[] desciptado = Xifrar.decryptData(secretKey, enciptado);

        String msg = new String(desciptado, 0, desciptado.length).toLowerCase();
        System.out.println("\n" + "1.6");
        System.out.println("Texto original: " + texto);
        System.out.println("Texto en byte: " + bytes);
        System.out.println("Byte enciptado: " + enciptado);
        System.out.println("Byte desciptado: " + desciptado);
        System.out.println("Secreto Key: " + secretKey);
        System.out.println("Texto final: " + msg);
    }

    public static void unoPuntoSiete() {
        SecretKey secretKey = Xifrar.keygenKeyGeneration(128);
        System.out.println("\n" + "1.7");
        System.out.println(secretKey.getEncoded());
        System.out.println(secretKey.getAlgorithm());
        System.out.println(secretKey.getFormat());
        System.out.println(secretKey.isDestroyed());
    }

    public static void unoPuntoOcho() {
        System.out.println("\n" + "1.8");
        SecretKey secretKey = Xifrar.passwordKeyGeneration("Best Listo", 192);
        SecretKey falsoKey = Xifrar.passwordKeyGeneration("Best Listos", 192);
        String texto = "wrisgknljerh rnyjñwqoeru";

        byte[] bytes = texto.getBytes(StandardCharsets.UTF_8);

        byte[] enciptado = Xifrar.encryptData(secretKey, bytes);
        byte[] desciptado = Xifrar.decryptData(falsoKey, enciptado);

    }

    public static void puntoDos() {
        System.out.println("\n" + "2");
        try {
            Path path = Paths.get("src/UF1/a4/cosita/textamagat");
            byte[] texto = Files.readAllBytes(path);

            File f = new File("src/UF1/a4/cosita/clausA4.txt");
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            String line = null;

            while ((line = br.readLine()) != null) {
                SecretKey secretKey = Xifrar.passwordKeyGeneration(line, 128);

                byte[] descriptado = Xifrar.decryptData(secretKey, texto);

                if (descriptado != null) {
                    String msg = new String(descriptado, "UTF8");
                    System.out.println(line);
                    System.out.println(msg);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
