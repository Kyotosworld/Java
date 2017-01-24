import java.net.*;
import java.io.*;

final class Serveur implements Runnable {

    private final int numeroPort, connexionsMax;
    private final String nomFichier;

    Serveur(int i, int i2, String s) {
        numeroPort = i;
        connexionsMax = i2;
        nomFichier = s;
        System.out.format("%s::Serveur():: Cr\u00e9ation d'un Serveur pour le port %d acceptant %d connexions\n", Thread.currentThread().getName(), i, i2);
    }

    public void run() {
        byte[] buffer = new byte[8192];
        String nomFichier2;
        try (
            ServerSocket socketServeur = new ServerSocket(numeroPort, connexionsMax);
        ) {
            for (int i=0; i<5; i++) {
                nomFichier2 = nomFichier + String.valueOf(i);
                try (
                    // Initialisation du flux d'entrée <- client
                    //System.out.format("%s::Serveur():: Récupération du socket client...", Thread.currentThread().getName());
                    Socket socketClient = socketServeur.accept();
                    BufferedInputStream bis = new BufferedInputStream(socketClient.getInputStream());
                    //System.out.format("%s::Serveur():: Récupération du flux d'entrée" Thread.currentThread().getName());

                    // Initialisation du flux de sortie -> fichier
                    BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(nomFichier2));
                    //System.out.format("%s::Serveur():: Initialisation du flux de sortie" Thread.currentThread().getName());
                ) {
                    System.out.format("%s::Serveur():: D\u00e9but du transfert de %s\n", Thread.currentThread().getName(), nomFichier);
                    long t0 = System.nanoTime(), tempsEcoule = 0, taille = 0;
                    double vitesse = 0;

                    while(bis.read(buffer) != -1) {
                        bos.write(buffer);
                        taille += buffer.length;
                        tempsEcoule = System.nanoTime() - t0;
                        vitesse = taille * 1000000 / tempsEcoule;
                        System.out.format("%s::Serveur():: Transfert de %s : %f KB à %f KB/s\n", Thread.currentThread().getName(), nomFichier, (double)(taille/1000), vitesse);
                    }
                    System.out.format("%s::Serveur():: Transfert n°%d r\u00e9ussi en %f s\n", Thread.currentThread().getName(), i, (double)(tempsEcoule/1000000000));
                }
                catch (IOException e) {
                    System.out.println("SERVEUR: Probl\u00e8me de lecture/ecriture\n");
                    e.printStackTrace();
                }
            }
            System.out.format("%s::Serveur():: Tous transferts r\u00e9ussis\n", Thread.currentThread().getName());
            System.out.format("%s::Serveur():: Terminaison\n", Thread.currentThread().getName());
        } catch (IOException e) {
            System.out.println("SERVEUR Erreur fatale: impossible d'initialiser le serveur");
            e.printStackTrace();
            System.exit(1);
        }
    }

    public static void main(String[] args) {
        String nomFichierSortie = "OUTPUT";
        int portLocal = 2000;
        int nbConnexions = 1;

        try {
            portLocal = Integer.parseInt(args[0]);
            nbConnexions = Integer.parseInt(args[1]);
            nomFichierSortie = args[2];
        }
        catch (Exception e) {
            System.out.println("SYNOPSIS: java Serveur portLocal nbConnexionsMax nomFichierSortie");
            System.exit(1);
        }

        Serveur serveur = new Serveur(portLocal, nbConnexions, nomFichierSortie);
        new Thread(serveur).start();
    }

}