/**
*La classe Codex est une classe "base de donnée" qui contient toutes les informations "modèles" du jeu. Elle contient chaque type de personnage, leur nom, leur description, de même pour les sorts et les plateaux. Un fan attentionné peut la modifier très facilement pour ajouter ses propres types et ses propres sorts, voire ses propres plateaux !
*/

public class Codex {
    
    /**
    * Chaque ligne correspond à un type de personnage et chaque colonne correspond à, dans l'ordre : PV, PM, Compétence 1, Compétence 2, Compétence 3, Force, Intelligence
    * @see #nomsTypes
    * @see #descTypes
    */
	public static int[][] types={
        {200,   3,  10, 1,  2,  3,  60, 30},
        {160,    7,  10, 4,  5,  6,  20, 70},
        {280,   5,  10, 7,  8,  9,  80, 10},
        {240,   9,  10, 10, 11, 12, 10, 80}
    };
    
    /**
    * Chaque case correspond respectivement au nom d'un type de personnage (= ligne du tableau types)
    * @see #types
    * @see #descTypes
    */
    public static String[] nomsTypes = {
        "Estelle",
        "Nikita",
        "Achille",
        "Aina"
    };
    /**
    * Chaque case correspond respectivement à la description d'un type de personnage (= ligne du tableau types)
    * @see #types
    * @see #nomsTypes
    */
    public static String[] descTypes = {
        "Elle est l'archère d'élite, atteignant ses cibles à très longue distance, non pas avec ses yeux revolvers (dédicace à Marc Lavoine) mais avec ses flèches bien acerées. Elle dispose de deux types de flèches : une flèche classique qui diminue les PV de l'adversaire, et une flèche spéciale qui diminue ses PM diminuant ainsi son rayon d'action.",
        "Le magicien épique qui vous réduit en cendres tout adversaire malvenu : bien sûr, sa boule de feu saura faire taire les bavards en infligeant des dégâts magiques importants, mais il peut aussi enlever des PA à l'adversaire et encore mieux : voler de la vie avec son vampirique.",
        "Achine de son petit nom. Sa masse musculaire importante est son atout. En plus de sa force impressionnante, son sort attirance approchera l'adversaire (qu'il le veuille ou non) d'Achine, et s'il n'est pas encore assez proche, il peut lui lancer une hache. Cerise sur le gâteau : sa compétence passive Châtiment augmente sa force en fonction des PV qu'il perd.",
        "Il n'a pas de temps à perdre avec la force et la magie. La perfidie vient à bout de tous, même des plus coriaces. Son sort peur attire l'adversaire vers le piège le plus proche, et son sort Contrôle lui limite ses mouvements. Et comme ici l'hôpital ne se fout pas de la charité, lui même est insensible aux pièges et aux trous de la carte. Malin."
    };
    
    /**
    * Chaque ligne correspond à une compétence et chaque colonne correspond à, dans l'ordre : diff. PV Physique cible, diff. PV Magique cible, diff. PA cible, diff. PM cible, diff. PV lanceur, diff. PA lanceur, diff. PM lanceur, Special, Portee
    * @see #nomsSorts
    */
	public static int[][] sorts={
		{-10,	0,		0,		0,		0,		-1,		0,		0,	1},
		{-20,	0,		0,		0,		0,		-3,		0,		0,	10},
		{-5,	0,		0,		-2,		0,		-5,		0,		0,	10},
		{0,		0,		0,		0,		0,		-8,		-3,		1,	40},
		{0,		-30,	0,		0,		0,		-4,		0,		0,	6},
		{0,		-10,	0,		0,		10,		-5,		0,		0,	3},
		{0,		-10,	-5,		0,		0,		-3,		0,		0,	3},
		{-30,	0,		0,		0,		0,		-3,		0,		0,	3},
		{0,		0,		0,		0,		0,		-1,		-4,		2,	40},
		{0,		0,		0,		0,		0,		0,		0,		3,	40},
		{-10,	0,		0,		0,		0,		-4,		0,		4,	40},
		{0,		0,		0,		-3,		0,		-4,		0,		0,	8},
		{0	,	0,		0,		0,		0,		0,		0,		5,	40}
	};

    /**
    * Chaque case correspond respectivement au nom d'une compétence (= ligne du tableau sorts)
    * @see #sorts
    */
    public static String[] nomsSorts = {
        "Corps à corps",
        "Flèche (PV)",
        "Flèche (PM)",
        "Téléportation",
        "Boule de feu ++",
        "Vampirique",
        "EnlèvePA",
        "Lancer de hache",
        "Attirance",
        "Passif: Châtiment",
        "Sort peur",
        "Contrôle",
        "Passif: Anti-piège",
    };
    
    
	// -1 et -2: pour les spawn
	//1 : mur
	//2 : trou
	//3 : piège
	
	/** un tableau constante à 3 dimensions, il s'agit d'un tableau 1D comprenant des tableaux 2D, ces tableaux étant les terrains de jeux sélectionnables par les joueurs au début d'une partie.
     * @see #nomsPlateaux
	 */
	 
	public static int[][][] plateaux = {
		{ //map mur
			{0,0,0,0,0,2,2,2,2,0,0,0,0,0,0},
			{0,1,0,0,0,0,0,0,0,0,0,0,0,1,0},
			{0,1,0,0,0,0,0,3,0,0,0,0,0,1,0},
			{-1,0,0,2,0,0,3,0,0,0,2,0,0,0,-2},
			{0,1,0,0,0,0,1,1,0,0,0,0,0,1,0},
			{0,1,0,0,0,0,1,1,0,0,0,0,0,1,0},
			{-1,0,0,2,0,3,3,3,3,0,2,0,0,0,-2},
			{0,1,0,0,0,0,1,1,0,0,0,0,0,1,0},
			{0,0,0,0,0,3,3,3,3,0,0,0,0,0,0},
			{0,0,0,0,0,2,2,2,2,0,0,0,0,0,0}
		},
		{ // map trou
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,1,0,0,0,1,0,0,0,0,2,0},
			{-1,0,0,3,1,0,2,2,1,0,3,0,0,2,-2},
			{0,0,0,3,0,0,2,2,0,0,3,0,0,0,0},
			{0,0,2,2,0,0,2,2,0,0,2,2,0,0,0},
			{0,0,2,2,0,0,2,2,0,0,2,2,0,0,0},
			{-1,0,0,0,0,0,2,2,0,0,0,0,0,0,-2},
			{0,0,0,0,3,0,2,2,0,0,3,0,0,0,0},
			{0,0,2,0,3,1,2,2,1,0,3,2,0,2,0},
			{0,0,2,0,0,1,0,0,1,0,0,2,0,2,0},
			{0,0,2,0,0,0,0,0,0,0,0,2,0,0,0}
		},
		{ // map labyrinthe
			{0,0,0,3,1,0,0,0,0,0,1,3,0,0,0},
			{0,0,0,3,2,0,3,3,0,0,2,3,0,0,0},
			{0,0,1,1,1,0,3,3,0,0,1,1,1,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{-1,0,0,0,2,2,2,2,2,2,2,0,0,0,-2},
			{-1,0,0,0,2,2,2,2,2,2,0,0,0,0,-2},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,1,1,1,0,0,0,0,0,1,1,0,0,0},
			{0,0,0,3,2,0,3,3,0,0,2,3,0,0,0},
			{0,0,0,3,1,0,3,3,0,0,1,3,0,0,0}
		}
	};
    
    /**
    * Chaque case correspond respectivement au nom d'une map (= ligne du tableau plateaux)
    * @see #plateaux
    */
    public static String[] nomsPlateaux={"Map murs","Map trous","Map labyrinthe"};

}
