package untitled.fichier;
import java.lang.ArrayIndexOutOfBoundsException;
import java.io.*;

class Test extends Object {

    public void fileStream(String[] args) {
        FileInputStream fis = null;
        FileOutputStream fos = null;
        byte[] buf = new byte[8];
        int n = 0;
   
        try {
            fis = new FileInputStream(new File(args[0]));
            fos = new FileOutputStream(new File(args[1]));
        }
        catch (ArrayIndexOutOfBoundsException err) {
            System.out.println("Vous devez spécifier les noms de deux fichiers: un à lire, un dans lequel écrire");
        }
        catch (FileNotFoundException err) {
            System.out.println("Fichier "+ args[0] +" non valide");
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

    public void listerFichiers(File racine) {
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
