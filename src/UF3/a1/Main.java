package UF3.a1;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Main {
    public static void main(String[] args) {
        InetAddress[] addresses = new InetAddress[2];

        addresses[0] = InetAddress.getLoopbackAddress();
        try {
            addresses[1] = InetAddress.getByName("ioc.xtec.cat");
            for (InetAddress adress : addresses) {
                if (adress.isLoopbackAddress()) {
                    System.out.println(adress.getHostName() + " té una adreça loopback");
                } else {
                    System.out.println(adress.getHostName() + " no té una adreça loopback");
                }
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}
