package UF3.TCPejemplo;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.List;

public class TCPClientObjLlista extends Thread {
    private String hostname;
    private int port;
    private boolean acabat = false;
    private InputStream is;
    private OutputStream os;
    private ObjectOutputStream output;
    private ObjectInputStream input;

    public TCPClientObjLlista(String hostname, int port) {
        this.hostname = hostname;
        this.port = port;
    }

    @Override
    public void run() {
        Socket socket;
        try {
            socket = new Socket(InetAddress.getByName(hostname), port);
            os = socket.getOutputStream();
            output = new ObjectOutputStream(os);
            is = socket.getInputStream();
            input = new ObjectInputStream(is);

            while (!acabat) {
                Jugador jugador = new Jugador("joel", 100);
                output.writeObject(jugador);
                output.flush();

                jugador = (Jugador) input.readObject();
                printLlista(jugador);
                acabat = true;
            }
        }catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            output.close();
            input.close();
            is.close();
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    private void printLlista(Jugador jugador) {
        System.out.println(jugador.apodo + " " + jugador.puntuacion);
    }

    public static void main(String[] args) {
        TCPClientObjLlista clientObjLlista  = new TCPClientObjLlista("localhost",5557);
        clientObjLlista.start();
    }
}
