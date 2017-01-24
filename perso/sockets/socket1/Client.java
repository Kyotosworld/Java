import java.net.*;
import java.io.*;

final class Client implements Runnable {

    private Socket socket;
    private final int numeroPort;
    private final InetAddress adresseDistante;
    private BufferedReader in;
    private PrintWriter out;

    Client(InetAddress ia, int i) {
        adresseDistante = ia;
        numeroPort = i;
    }

    public void run() {
        try {
            socket = new Socket(adresseDistante, numeroPort);
            System.out.println("Connexion reussie");
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            System.out.println(in.readLine());
        }
        catch (UnknownHostException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                socket.close();
            } catch (IOException e) {
                System.out.println("Fatal error from Client: could not close sockets");
            }
        }
    }
}