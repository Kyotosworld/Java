import java.io.*;

class MethodesStatiques {

    public static void main(String[] args) {
        System.out.println("Test de performance en lecture+ecriture d'un fichier avec un flux de caracteres");
        final String input = "BIG.mp3";
        final String output = "BIG_OUTPUT";
        long t0 = System.currentTimeMillis();

        copieDepreciee(input, output);
        System.out.println("Copie depreciee sans buffer:                        "+(System.currentTimeMillis()-t0)+" ms");
        t0 = System.currentTimeMillis();

        copie(input, output);
        System.out.println("Copie sans buffer:                                  "+(System.currentTimeMillis()-t0)+" ms");
        t0 = System.currentTimeMillis();

        copieBufferManuel(input, output, 8);
        System.out.println("Copie avec buffer manuel de 8 B:                    "+(System.currentTimeMillis()-t0)+" ms");
        t0 = System.currentTimeMillis();

        copieBufferManuel(input, output, 8192);
        System.out.println("Copie avec buffer manuel de 8 KB:                   "+(System.currentTimeMillis()-t0)+" ms");
        t0 = System.currentTimeMillis();

        copieBufferManuel(input, output, 16000);
        System.out.println("Copie avec buffer manuel de 16 KB:                  "+(System.currentTimeMillis()-t0)+" ms");
        t0 = System.currentTimeMillis();

        copieBufferAutomatique(input, output, 8);
        System.out.println("Copie avec buffer automatique de 8 B:               "+(System.currentTimeMillis()-t0)+" ms");
        t0 = System.currentTimeMillis();

        copieBufferAutomatique(input, output, 8192);
        System.out.println("Copie avec buffer automatique de 8 KB:              "+(System.currentTimeMillis()-t0)+" ms");
        t0 = System.currentTimeMillis();

        copieBufferAutomatique(input, output, 16000);
        System.out.println("Copie avec buffer automatique de 16 KB:             "+(System.currentTimeMillis()-t0)+" ms");
        t0 = System.currentTimeMillis();

        copieBufferAutomatique(input, output);
        System.out.println("Copie avec buffer automatique de taille predefinie: "+(System.currentTimeMillis()-t0)+" ms");
        t0 = System.currentTimeMillis();

        /********************************************************************************************************/
        System.out.println("");
        System.out.println("Test de performance en lecture+ecriture d'un fichier avec un flux d'octets");
        t0 = System.currentTimeMillis();

        flux(input, output);
        System.out.println("Copie sans buffer:                                  "+(System.currentTimeMillis()-t0)+" ms");
        t0 = System.currentTimeMillis();

        fluxBufferManuel(input, output, 8);
        System.out.println("Copie avec buffer manuel de 8 B:                    "+(System.currentTimeMillis()-t0)+" ms");
        t0 = System.currentTimeMillis();

        fluxBufferManuel(input, output, 8192);
        System.out.println("Copie avec buffer manuel de 8 KB:                   "+(System.currentTimeMillis()-t0)+" ms");
        t0 = System.currentTimeMillis();

        fluxBufferManuel(input, output, 16000);
        System.out.println("Copie avec buffer manuel de 16 KB:                  "+(System.currentTimeMillis()-t0)+" ms");
        t0 = System.currentTimeMillis();

        fluxBufferAutomatique(input, output, 8);
        System.out.println("Copie avec buffer automatique de 8 B:               "+(System.currentTimeMillis()-t0)+" ms");
        t0 = System.currentTimeMillis();

        fluxBufferAutomatique(input, output, 8192);
        System.out.println("Copie avec buffer automatique de 8 KB:              "+(System.currentTimeMillis()-t0)+" ms");
        t0 = System.currentTimeMillis();

        fluxBufferAutomatique(input, output, 16000);
        System.out.println("Copie avec buffer automatique de 16 KB:             "+(System.currentTimeMillis()-t0)+" ms");
        t0 = System.currentTimeMillis();

        fluxBufferAutomatique2(input, output, 8);
        System.out.println("Copie 2 avec buffer automatique de 8 B:             "+(System.currentTimeMillis()-t0)+" ms");
        t0 = System.currentTimeMillis();

        fluxBufferAutomatique2(input, output, 8192);
        System.out.println("Copie 2 avec buffer automatique de 8 KB:            "+(System.currentTimeMillis()-t0)+" ms");
        t0 = System.currentTimeMillis();

        fluxBufferAutomatique2(input, output, 16000);
        System.out.println("Copie 2 avec buffer automatique de 16 KB:           "+(System.currentTimeMillis()-t0)+" ms");
        t0 = System.currentTimeMillis();
    }

    static void copieDepreciee(String input, String output) {
        FileReader fr = null;   // flux d'entrée
        FileWriter fw = null;   // flux de sortie
        int c;                  // "buffer" d'un "caractère" = un byte

        // structure try-catch depréciée :
        // si deux exceptions sont levées, une dans le try{} et une dans le finally{},
        // la première sera supprimée
        try {
            fr = new FileReader(new File(input));
            fw = new FileWriter(new File(output));

            // c est un byte (entre 0 et 255)
            // mais déclaré comme un int pour pouvoir contenir -1 si la
            // fin du flux est atteinte
            while ((c = fr.read()) != -1) {
                // on écrit le byte lu
                fw.write(c);
            }
        }
        catch (FileNotFoundException err) {
            System.out.println("Fichier "+ input +" non trouve !");
            err.printStackTrace();
        }
        catch (IOException err) {
            err.printStackTrace();
        }
        finally {
            try {
                if (fr != null)
                    fr.close();
                if (fw != null)
                    fw.close();
            } catch (IOException err) {
                err.printStackTrace();
            }        
        }

        /* Structure try-catch encore pire:
        SI DES ERREURS SONT LEVEES, LES FLUX NE SERONT JAMAIS FERMES
        try {
            fr = new FileReader(new File(input));
            fw = new FileWriter(new File(output));
            while ((c = fr.read()) != -1) {
                fw.write(c);
            }

            fr.close()
            fw.close()
        }
        catch (FileNotFoundException err) {
            err.printStackTrace();
        }
        catch (IOException err) {
            err.printStackTrace();
        }
        */
    }

    static void copie(String input, String output) {
        int c;
        // structure try-with-resources
        // TOUTES les variables déclarées à l'intérieur doivent implémenter l'interface AutoCloseable
        // et doivent être DECLAREES, et non instanciées
        //
        // si deux exceptions sont levées, une dans le try{} et une dans le finally{},
        // c'est cette fois la DEUXIEME qui sera supprimée
        try (
            FileReader fr = new FileReader(input);
            FileWriter fw = new FileWriter(output);
        ) {
            while ((c = fr.read()) != -1) {
                fw.write(c);
            }
        }
        catch (FileNotFoundException err) {
            System.out.println("Fichier "+ input +" non trouve !");
            err.printStackTrace();
        }
        catch (IOException err) {
            err.printStackTrace();
        }
    }

    static void copieBufferManuel(String input, String output, int bufferSize) {
        // On utilise ici Reader/Writer : on manipule des flux de caractères donc le buffer
        // doit être de type char
        char[] buffer = new char[bufferSize];
        try (
            FileReader fr = new FileReader(input);
            FileWriter fw = new FileWriter(output);
        ) {
            while (fr.read(buffer) != -1) {
                fw.write(buffer);
            }
        }
        catch (FileNotFoundException err) {
            System.out.println("Fichier "+ input +" non trouve !");
            err.printStackTrace();
        }
        catch (IOException err) {
            err.printStackTrace();
        }
    }

    static void copieBufferAutomatique(String input, String output) {
        String str;
        try (
            BufferedReader bfr = new BufferedReader(new FileReader(input));
            BufferedWriter bfw = new BufferedWriter(new FileWriter(output));
        ) {
            while ((str = bfr.readLine()) != null) {
                bfw.write(str);
            }
        }
        catch (FileNotFoundException err) {
            System.out.println("Fichier "+ input +" non trouve !");
            err.printStackTrace();
        }
        catch (IOException err) {
            err.printStackTrace();
        }
    }

    static void copieBufferAutomatique(String input, String output, int bufferSize) {
        String str;
        try (
            BufferedReader bfr = new BufferedReader(new FileReader(input), bufferSize);
            BufferedWriter bfw = new BufferedWriter(new FileWriter(output), bufferSize);
        ) {
            while ((str = bfr.readLine()) != null) {
                bfw.write(str);
            }
        }
        catch (FileNotFoundException err) {
            System.out.println("Fichier "+ input +" non trouve !");
            err.printStackTrace();
        }
        catch (IOException err) {
            err.printStackTrace();
        }
    }

    static void flux(String input, String output) {
        int c;
        try(
            FileInputStream fis = new FileInputStream(input);
            FileOutputStream fos = new FileOutputStream(output);
        ) {
            while((c = fis.read()) != -1) {
                fos.write(c);
            }
        }
        catch (FileNotFoundException err) {
            System.out.println("Fichier "+ input +" non trouve !");
            err.printStackTrace();
        }
        catch (IOException err) {
            err.printStackTrace();
        }
    }

    static void fluxBufferManuel(String input, String output, int bufferSize) {
        byte[] buffer = new byte[bufferSize];
        try(
            FileInputStream fis = new FileInputStream(input);
            FileOutputStream fos = new FileOutputStream(output);
        ) {
            while(fis.read(buffer) != -1) {
                fos.write(buffer);
            }
        }
        catch (FileNotFoundException err) {
            System.out.println("Fichier "+ input +" non trouve !");
            err.printStackTrace();
        }
        catch (IOException err) {
            err.printStackTrace();
        }
    }

    static void fluxBufferAutomatique(String input, String output, int bufferSize) {
        byte[] buffer = new byte[bufferSize];
        try(
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(input), bufferSize);
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(output), bufferSize);
        ) {
            while(bis.read(buffer) != -1) {
                bos.write(buffer);
            }
        }
        catch (FileNotFoundException err) {
            System.out.println("Fichier "+ input +" non trouve !");
            err.printStackTrace();
        }
        catch (IOException err) {
            err.printStackTrace();
        }
    }

    static void fluxBufferAutomatique2(String input, String output, int bufferSize) {
        byte[] buffer = new byte[bufferSize];
        try(
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(input));
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(output));
        ) {
            while(bis.read(buffer) != -1) {
                bos.write(buffer);
            }
        }
        catch (FileNotFoundException err) {
            System.out.println("Fichier "+ input +" non trouve !");
            err.printStackTrace();
        }
        catch (IOException err) {
            err.printStackTrace();
        }
    }

    /******************************************************************************************************************/
    static void copieAvecBuffer2(String input, String output) {
        FileInputStream fis = null;
        FileOutputStream fos = null;
        byte[] buf = new byte[8];
        int n = 0;

        try {
            fis = new FileInputStream(new File(input));
            fos = new FileOutputStream(new File(output));

            while ((n = fis.read(buf)) != -1) {
/*                for (int i=0; i<buf.length; i++) {
                    buf[i]++;
                }*/
                fos.write(buf);
            }
        }
        catch (FileNotFoundException err) {
            System.out.println("Fichier "+ input +" non trouvé !");
            err.printStackTrace();
        }
        catch (IOException err) {
            err.printStackTrace();
        }
        finally {
            try {
                if (fis != null)
                    fis.close();
                if (fos != null)
                    fos.close();
            } catch (IOException err) {
                err.printStackTrace();
            }        
        }
    }

    static void listerFichiers(File racine) {
        if (racine.exists() && racine.isDirectory()) {
            for (File f: racine.listFiles()) {
                if (f.isDirectory()) {
                    for (File f2: f.listFiles())
                        System.out.println(((f2.isDirectory())?f2.getName()+"/": f2.getName()));
                } else {
                    System.out.println(f.getName());
                }
            }
        }
        else {
            System.out.println("Aucun fichier spécifié !");
        }
    }
}
