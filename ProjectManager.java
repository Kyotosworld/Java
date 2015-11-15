import Outils.TermInput;
import IE.Janvier2014;
import TD1.Ex6;
import TD2.Ex1;
import TD2.Ex2;

public class ProjectManager {

    public static String[] projectsArr = {"IE/Janvier2014",
                                          "TD1/Ex6",
                                          "TD2/Ex1",
                                          "TD2/Ex2",
                                          "TD2/Ex3"};

    public static void main(String[] args) {
        System.out.println("Quel projet voulez-vous exécuter ?");
        String projet = TermInput.getString();

        if (projet.equals(projectsArr[0]))
            IE.Janvier2014.main(projectsArr);
        else if (projet.equals(projectsArr[1]))
//            TD1.Ex6.main(projectsArr);
            System.out.println("TD1/Ex6 ne peut s'exécuter de cette manière");
        else if (projet.equals(projectsArr[2]))
            TD2.Ex1.main(projectsArr);
        else if (projet.equals(projectsArr[3]))
            TD2.Ex2.main(projectsArr);
        else if (projet.equals(projectsArr[4]))
            TD2.Ex3.main(projectsArr);
        else
            System.out.println(projet + "ne correspond à aucun projet existant.");
    }
}


/*
/* *****************************************
 * Invoke a method from another Java class *
 *******************************************

//Method method = WhateverYourClassIs.class.getDeclaredMethod("Method" + MyVar);
//method.invoke();


 *****************************
 * Load an entire Java class *
 *****************************

// Create a File object on the root of the directory containing the class file
File file = new File("c:\\myclasses\\");

try {
    // Convert File to a URL
    URL url = file.toURL();          // file:/c:/myclasses/
    URL[] urls = new URL[]{url};

    // Create a new class loader with the directory
    ClassLoader cl = new URLClassLoader(urls);

    // Load in the class; MyClass.class should be located in
    // the directory file:/c:/myclasses/com/mycompany
    Class cls = cl.loadClass("com.mycompany.MyClass");
} catch (MalformedURLException e) {
} catch (ClassNotFoundException e) {
}
*/
