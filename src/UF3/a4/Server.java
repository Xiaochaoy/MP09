package UF3.a4;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.nio.charset.StandardCharsets;

public class Server {
    /* Servidor Multicast que ens comunica la velocitat que porta d'un objecte */

    MulticastSocket socket;
    InetAddress multicastIP;
    int port;
    boolean continueRunning = true;
    palabras simulator;

    public Server(int portValue, String strIp) throws IOException {
        socket = new MulticastSocket(portValue);
        multicastIP = InetAddress.getByName(strIp);
        port = portValue;
        simulator = new palabras();
    }

    public void runServer() throws IOException{
        DatagramPacket packet;
        byte [] sendingData;

        while(continueRunning){
            sendingData = simulator.agafapalabra().getBytes(StandardCharsets.UTF_8);
            packet = new DatagramPacket(sendingData, sendingData.length,multicastIP, port);
            socket.send(packet);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                ex.getMessage();
            }


        }
        socket.close();
    }

    public static void main(String[] args) throws IOException {
        //Canvieu la X.X per un número per formar un IP.
        //Que no sigui la mateixa que la d'un altre company
        Server srvVel = new Server(5557, "224.0.10.50");
        srvVel.runServer();
        System.out.println("Parat!");

    }
}
