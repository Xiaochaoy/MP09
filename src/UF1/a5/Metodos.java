package UF1.a5;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.xml.crypto.KeySelector;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;
import java.security.KeyPair;
import java.security.KeyStore;
import java.security.PublicKey;
import java.security.Signature;
import java.security.cert.Certificate;
import java.util.Enumeration;
import java.util.Scanner;

public class Metodos {
    public static Scanner scanner = new Scanner(System.in);
    public static void uno_punto_uno(){
        System.out.println("1.1");
        KeyPair keyPair = Xifrar.randomGenerate(1024);
        String texto = scanner.nextLine();
        byte[] bytes = texto.getBytes(StandardCharsets.UTF_8);
        byte[] encriptado = UF1.a5.Xifrar.encryptData(keyPair.getPublic(), bytes);
        byte[] desencriptado = UF1.a5.Xifrar.decryptData(keyPair.getPrivate(), encriptado);

        String msg = new String(desencriptado, 0, desencriptado.length);
        System.out.println("Texto original: " + texto);
        System.out.println("Texto en byte: " + bytes);
        System.out.println("Byte encriptado: " + encriptado);
        System.out.println("Byte desencriptado: " + desencriptado);
        System.out.println("------------------------------------------------------");
        System.out.println("KeyPair Public: " + keyPair.getPublic());
        System.out.println("KeyPair Public Algoritmo: " + keyPair.getPublic().getAlgorithm());
        System.out.println("KeyPair Public Format: " + keyPair.getPublic().getFormat());
        System.out.println("KeyPair Public Encoded: " + keyPair.getPublic().getEncoded());
        System.out.println("------------------------------------------------------");
        System.out.println("KeyPair Private: " + keyPair.getPrivate());
        System.out.println("KeyPair Private Algoritmo: " + keyPair.getPrivate().getAlgorithm());
        System.out.println("KeyPair Private Format: " + keyPair.getPrivate().getFormat());
        System.out.println("KeyPair Private Encoded: " + keyPair.getPrivate().getEncoded());
        System.out.println("------------------------------------------------------");
        System.out.println("Texto final: " + msg);
    }

    public static void uno_punto_dos_punto_uno(){
        System.out.println("1.2.1");
        try {
            KeyStore keyStore = Xifrar.loadKeyStore("/home/usuario/keystore_xiao.ks","123456");

            System.out.println("Tipo de KeyStore: " + keyStore.getType());
            System.out.println("Tamaño de KeyStore: " + keyStore.size());
            System.out.println("Alies: ");
            Enumeration<String> alies = keyStore.aliases();
            while (alies.hasMoreElements()) {
                System.out.print("  " + alies.nextElement() + "");
                System.out.println();
            }
            Certificate certificate = keyStore.getCertificate(keyStore.aliases().nextElement());
            System.out.println("Certificado de \"lamevaclaum9\": " + certificate);
            System.out.println("Algoritmo de la clau \"lamevaclaum9\": " + certificate.getPublicKey().getAlgorithm());

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void uno_punto_dos_punto_dos(){
        System.out.println("1.2.2");
        SecretKey secretKey = Xifrar.SecretKey(192);
        try {
            KeyStore.ProtectionParameter protectionParameter = new KeyStore.PasswordProtection("123456".toCharArray());
            KeyStore keyStore = Xifrar.loadKeyStore("/home/usuario/keystore_xiao.ks","123456");
            KeyStore.SecretKeyEntry secretKeyEntry = new KeyStore.SecretKeyEntry(secretKey);
            keyStore.setEntry("mykey2",secretKeyEntry,protectionParameter);
            FileOutputStream fos = new FileOutputStream("/home/usuario/keystore_xiao.ks");
            keyStore.store(fos,"123456".toCharArray());
            fos.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public static void uno_punto_tres(){
        System.out.println("1.3");
        try {
            PublicKey publicKey = Xifrar.getPublicKey("/home/usuario/archivo");
            System.out.println(publicKey);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public static void uno_punto_quatro(){
        System.out.println("1.4");
        try {
            KeyStore keyStore = Xifrar.loadKeyStore("/home/usuario/keystore_xiao.ks","123456");
            PublicKey publicKey = Xifrar.getPublicKey(keyStore,"lamevaclaum9","123456");
            System.out.println(publicKey);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void uno_punto_cinco_y_seis(){
        System.out.println("1.5");
        KeyPair keyPair = Xifrar.randomGenerate(1024);
        String texto = "hola noob";
        byte[] bytes = texto.getBytes(StandardCharsets.UTF_8);
        byte[] signatura = Xifrar.signData(bytes, keyPair.getPrivate());
        System.out.println(signatura);

        System.out.println("1.6");
        System.out.println(Xifrar.validateSignature(bytes,signatura,keyPair.getPublic()));
    }

    public static void dos_punto_dos(){
        System.out.println("2.2");
        KeyPair keyPair = Xifrar.randomGenerate(1024);
        String texto = "hola pro";
        byte[] bytes = texto.getBytes(StandardCharsets.UTF_8);
        byte[][] encriptado = Xifrar.encryptWrappedData(bytes,keyPair.getPublic());
        byte[] desencriptado = Xifrar.decryptWrappedData(encriptado[0], keyPair.getPrivate(),encriptado[1]);

        String msg = new String(desencriptado);
        System.out.println("Texto origen: " + texto);
        System.out.println("Texto en byte: " + bytes);
        System.out.println("Texto encriptado: " + encriptado[0]);
        System.out.println("Clave encriptado: " + encriptado[1]);
        System.out.println("Texto decriptado: " + desencriptado);
        System.out.println("Texto final: " + msg);
    }
}
