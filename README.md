Java-Work
====

Dépôt de code pour les projets concernés par le cours d'informatique à l'INSA 
  de Lyon. Chaque projet a son propre répertoire:
* TD**X**: regroupe les exercices effectués dans le TD n°**X**
* IE: regroupe les interrogations écrites d'informatiques traités
* DS: regroupe les devoirs surveillés d'informatique traités
* Outils: regroupe les outils pouvant être réutilisés dans d'autres projets du
  répertoire
* untitled: regroupe les éléments de code qui furent des exercices personnels 
  à part entière, ou des éléments de compréhension de Java

## Utilisation:
Chaque dossier regroupe en fait des classes Java dans un package, pour que tous les exercices soient accessibles depuis le dossier racine. Pour l'utilisation de classes n'utilisant aucun objet d'un autre package, cela ne pose pas de problèmes:
```
# depuis la racine du projet : /
$ javac TD2/Ex1.java                               # compile l'exercice n°1 du TD n°2
$ java TD2/Ex1
```
Mais si un objet utilise les ressources d'un autre package, (par exemple l'exercice 3 du TD 2 qui utilise un objet du package Outils) il faut alors le compiler différemment. En effet cette classe contient l'instruction `import Outils.TermInput;` et il est nécessaire de spécifier la racine du projet pour que Java trouve le package Outils. Depuis la racine du projet, il faut donc naturellement spécifier le dossier actuel:
```
# depuis la racine du projet : /
$ javac -classpath . TD2/Ex3.java                 # TD2/Ex3 ne peut compiler sans l'option classpath
$ java TD2/Ex3
```
Enfin, la saisie de données se fait avec une classe particulière, la classe IO du package Outils, par exemple:
```
Outils.IO.getInt(0, "None", "Entrez la borne supérieure: ");
```
Cette classe ne fait que vérifier les données entrées pour ne pas accepter une saisie non-conforme, mais ne récupère pas elle-même les données à partir du flux d'entrée. On peut donc très bien s'en passer et remplacer ces lignes par:
```
Scanner sc = new Scanner(System.in);
int entier = sc.nextInt();
```