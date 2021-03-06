package UF3.a3;

import UF3.a2.DatagramSocketClient;
import java.io.IOException;
import java.net.*;
import java.nio.ByteBuffer;
import java.util.Scanner;

public class SecretNumClient {
    InetAddress serverIP;
    int serverPort;
    DatagramSocket socket;
    Scanner sc;
    String nom;
    int cont = 0;
    SecretNum sn;

    public SecretNumClient() {
        sc = new Scanner(System.in);
    }

    public void init(String host, int port) throws SocketException, UnknownHostException {
        serverIP = InetAddress.getByName(host);
        serverPort = port;
        socket = new DatagramSocket();
    }

    public void runClient() throws IOException {
        byte [] receivedData = new byte[1024];
        byte [] sendingData;

        sendingData = getFirstRequest();
        while (mustContinue(sendingData)) {
            DatagramPacket packet = new DatagramPacket(sendingData,sendingData.length,serverIP,serverPort);
            socket.send(packet);
            packet = new DatagramPacket(receivedData,1024);
            socket.receive(packet);
            sendingData = getDataToRequest(packet.getData(), packet.getLength());
        }

    }

    //Resta de conversa que se li envia al server
    private byte[] getDataToRequest(byte[] data, int length) {
        cont++;
        int nombre = ByteBuffer.wrap(data).getInt();
        //Imprimeix el nom del client + el que es reb del server i demana més dades
        System.out.println("Intento " + cont);
        if (nombre == 0){
            System.out.println("Correcto");
            try{
                Thread.sleep(2000);
            }catch (Exception e){
                e.printStackTrace();
            }
            cont = 0;
            System.out.println("Acabo de generar otro numero, vuelve a intentarlo.");
        }else if (nombre == 1){
            System.out.println("Més petit");
        }else{
            System.out.println("Més gran");
        }
        byte[] resposta = ByteBuffer.allocate(4).putInt(sc.nextInt()).array();
        return resposta;
    }

    //primer missatge que se li envia al server
    private byte[] getFirstRequest() {
        System.out.println("Tengo un numero random, adivina cual es: ");
        nom = sc.nextLine();
        byte[] resposta = ByteBuffer.allocate(4).putInt(Integer.parseInt(nom)).array();
        return resposta;
    }

    //Si se li diu adeu al server el client es desconnecta
    private boolean mustContinue(byte [] data) {
        String msg = new String(data).toLowerCase();
        return !msg.equals("exit");
    }

    public static void main(String[] args) {
        SecretNumClient client = new SecretNumClient();
        try {
            client.init("localhost",5555);
            client.runClient();
        } catch (IOException e) {
            e.getStackTrace();
        }
    }
}
