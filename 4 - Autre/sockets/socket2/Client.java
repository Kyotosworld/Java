import java.net.*;
import java.io.*;

final class Client implements Runnable {

    private final String nomFichier;
    private final int numeroPort;
    private final InetAddress adresseDistante;

    Client(InetAddress ia, int i, String s) {
        adresseDistante = ia;
        numeroPort = i;
        nomFichier = s;
    }

    public void run() {
        System.out.format("%s::Client():: Lancement", Thread.currentThread().getName());
        byte[] buffer = new byte[8192];
        try (
            // Initialisation du flux d'entrse <- fichier
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(nomFichier));

            // Initialisation du flux de sortie -> serveur
            Socket socket = new Socket(adresseDistante, numeroPort);
            BufferedOutputStream bos = new BufferedOutputStream(socket.getOutputStream());
        ) {
            System.out.format("%s::Client():: D\u00e9but du transfert\n", Thread.currentThread().getName());
            long t0 = System.nanoTime(), tempsEcoule = 0, taille = 0;
            double vitesse = 0;

            while(bis.read(buffer) != -1) {
                bos.write(buffer);
                taille += buffer.length;
                tempsEcoule = System.nanoTime() - t0;
                vitesse = taille * 1000000 / tempsEcoule;
                System.out.format("%s::Client():: Transfert de %s : %f KB à %f KB/s\n", Thread.currentThread().getName(), nomFichier, (double)(taille/1000), vitesse);
            }
            System.out.format("%s::Client():: Transfert r\u00e9ussi en %f s", Thread.currentThread().getName(), (double)(tempsEcoule/1000000000));
        }
        catch (FileNotFoundException e) {
            System.out.println("CLIENT "+Thread.currentThread().getName()+": Probl\u00e8me de r\u00e9cup\u00e9ration du fichier");
            e.printStackTrace();
        }
        catch (UnknownHostException e) {
            System.out.println("CLIENT "+Thread.currentThread().getName()+": Probl\u00e8me de connexion");
            e.printStackTrace();
        }
        catch (IOException e) {
            System.out.println("CLIENT "+Thread.currentThread().getName()+": Probl\u00e8me de lecture/\u00e9criture");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        int portDistant = 2000;
        int nbConnexions = 1;
        String nomFichierEntree = "INPUT";
        InetAddress adresseDistante;

        try {
            adresseDistante = InetAddress.getByName(args[0]);
            System.out.format("%s::MainClient():: Adresse %s trouv\u00e9e\n", Thread.currentThread().getName(), args[1]);
            portDistant = Integer.parseInt(args[1]);
            nbConnexions = Integer.parseInt(args[2]);
            nomFichierEntree = args[3];

            for (int i=0; i<nbConnexions; i++) {
                System.out.format("%s::MainClient():: Cr\u00e9ation du Client[%d] vers %s : %d\n", Thread.currentThread().getName(), i, adresseDistante, portDistant);
                new Thread(new Client(adresseDistante, portDistant, nomFichierEntree)).start();
            }
        }
        catch (UnknownHostException e) {
            System.out.format("%s::MainClient():: ERREUR : Adresse inconnue %s\n", Thread.currentThread().getName(), args[1]);
            e.printStackTrace();
        }
        catch (Exception e) {
            System.out.println("SYNOPSIS: java Client adresseDistante portDistant nbClients nomFichierEntree");
            System.exit(1);
        }

    }
}