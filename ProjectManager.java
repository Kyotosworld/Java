import Outils.TermInput;

public class ProjectManager {

	public static String[] projectsArr = {"IE/IEJanvier2014",
										  "TD 1/Ex6",
										  "TD 2/Ex1",
										  "TD 2/Ex2"};

    public static void main(String[] args) {
    	String projet = TermInput.getString();
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
