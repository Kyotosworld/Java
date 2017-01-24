import java.net.*;
import java.io.*;

final class Serveur implements Runnable {
    private ServerSocket socketServeur; 
    private Socket socketClient;
    private BufferedReader in;
    private PrintWriter out;
    private final int numeroPort, connexionsMax;


    Serveur(int i, int i2) {
        numeroPort = i;
        connexionsMax = i2;
    }

    public void run() {
        try {
            socketServeur = new ServerSocket(numeroPort, connexionsMax);
            socketClient = socketServeur.accept();
            out = new PrintWriter(socketClient.getOutputStream());
            out.println("Message Serveur");
            out.flush();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (socketServeur != null) {
                    socketServeur.close();
                }
                if (socketClient != null) {
                    socketClient.close();
                }
            } catch (IOException e) {
                System.out.println("Fatal error from Serveur: could not close sockets");
            }
        }

    }

}