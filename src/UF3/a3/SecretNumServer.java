package UF3.a3;

import UF3.a2.DatagramSocketServer;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class SecretNumServer {
    DatagramSocket socket;
    SecretNum sn;

    //Instàciar el socket
    public void init(int port) throws SocketException {
        socket = new DatagramSocket(port);
        sn = new SecretNum();
        System.out.println("Servidor iniciat per " + port);
    }

    public void runServer() throws IOException {
        byte [] receivingData = new byte[1024];
        byte [] sendingData;
        InetAddress clientIP;
        int clientPort;
        sn.pensa(100);

        while(true) {
            DatagramPacket packet = new DatagramPacket(receivingData,1024);
            socket.receive(packet);
            sendingData = processData(packet.getData(),packet.getLength());
            //Llegim el port i l'adreça del client per on se li ha d'enviar la resposta
            clientIP = packet.getAddress();
            clientPort = packet.getPort();
            packet = new DatagramPacket(sendingData,sendingData.length,clientIP,clientPort);
            socket.send(packet);
        }
    }

    //El server retorna al client el mateix missatge que li arriba però en majúscules
    private byte[] processData(byte[] data, int lenght) {
        String msg = new String(data,0,lenght);
        sn.secreto = sn.comprova(msg);
        if (sn.secreto.equals("Correcte")){
            sn.pensa(100);
        }
        System.out.println(msg);
        return sn.secreto.getBytes();
    }

    public static void main(String[] args) {
        SecretNumServer server = new SecretNumServer();
        try {
            server.init(5555);
            server.runServer();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
