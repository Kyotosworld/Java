import java.net.*;
import java.io.*;

class Test {

    public static void main(String[] args) {
        final InetAddress ADDRESSE_LOCALE;
        final int PORT_LOCAL;
        Serveur serveur;
        Thread tServeur;
        Client[] clients;
        Thread[] tClients;

        try {
            ADDRESSE_LOCALE = InetAddress.getLocalHost();
            PORT_LOCAL = 2009;

            // Initialisation
            serveur = new Serveur(PORT_LOCAL, 1);
            tServeur = new Thread(serveur);
            clients = new Client[2];
            tClients = new Thread[clients.length];
            for (int i=0; i<clients.length; i++) {
                clients[i] = new Client(ADDRESSE_LOCALE, PORT_LOCAL);
                tClients[i] = new Thread(clients[i]); 
            }

            // Lancement
            tServeur.start();
            for (Thread t: tClients) {
                t.start();
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}