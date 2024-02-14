PROGRAMMATION ORIENTEE OBJET 
EN JAVA. 

-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------
 Projet Puissance 4 avec Interface Graphique et ODBC
 
 Objectif : 
 
 Réaliser un programme de Puissance 4 jouable sur une interface graphique en Java tout en intégrant la connectivité à une base de données via ODBC.


 Présentation du jeu et des règles du jeu
   Le jeu Puissance 4 est un jeu de stratégie pour deux joueurs qui se joue sur une 
   grille verticale de 6 lignes et horizontale de 7 colonnes. L'objectif du jeu est d'aligner 
   quatre de ses propres pions de manière consécutive, que ce soit horizontalement, 
   verticalement ou en diagonale.


-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------
 
 
 Principe du Jeu :
 
 Grille de Jeu : 
   
  La grille est initialement vide. Les joueurs choisissent à tour de rôle une colonne 
  dans laquelle placer leur pion. Le pion tombe ensuite dans la position la plus basse 
  libre de cette colonne.
  
   Le premier joueur à aligner quatre de ses pions consécutifs dans n'importe quelle 
  direction (horizontale, verticale ou diagonale) remporte la partie.
   Règles du Jeu :
   Tour de Jeu : Les joueurs jouent tour à tour. Le joueur 1 commence 
  généralement la partie.
  
   Placement du Pion : Lors de son tour, un joueur choisit une colonne où placer 
  son pion. Le pion tombe dans la case vide la plus basse de cette colonne.
   Alternative des Joueurs : Les joueurs alternent les tours après chaque 
  placement de pion.


-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------
 Fin de Partie : La partie se termine dès qu'un joueur a réussi à aligner quatre de 
ses pions ou lorsque la grille est complètement remplie sans qu'aucun joueur n'ait 
réussi à aligner quatre pions.

Égalité : Si la grille est remplie sans qu'aucun joueur n'ait aligné quatre pions, la 
partie est déclarée nulle.

 Début de Nouvelle Manche : Une nouvelle manche démarre après chaque 
partie, avec les joueurs alternant celui qui commence.


-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------

 Étape 1: Interface Graphique
 
 Au lancement du programme, afficher un menu interactif pour permettre aux 
joueurs de choisir entre un joueur humain et une IA. Chaque joueur doit fournir un 
nom.



-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------
 Exemple de démarrage de partie :
 
 Joueur 1 
humain Joe


 Joueur 2 
IA Dark Vador


 Par défaut, le premier joueur utilise les pions « X » et le second utilise les pions « O ».
 
 Étape 2: Gestionnaire de Partie
   
   Implémenter un gestionnaire de partie qui mémorise l'état de la grille, gère les 
  tours des joueurs, détermine la fin de la partie et enregistre le score. En cas d'égalité, 
  aucune des deux parties ne gagnera de point. Le joueur qui commence la partie 
  alternera entre les manches.

-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------
 Étape 3: Joueurs
   
   Humain : Interagit avec la grille en utilisant l'interface graphique.
   IA : Effectue des mouvements automatiques, choisissant une colonne libre au hasard dans un premier temps.


-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------
 Étape 4: Historique de Partie
 
   Maintenir un historique de partie dans un fichier log.txt à la racine du dossier, 
  contenant toutes les informations relatives à la partie sous un format spécifié.


----------------------------------------------------------------------------------------------------------------------------------------------------------------------------
 Étape 5: Gestion des Erreurs

   
  Implémenter la détection et la gestion d'erreurs lors des saisies utilisateur ou des 
  mouvements dans le jeu. Afficher des messages d'erreur à l'utilisateur et les 
  enregistrer dans les logs.



----------------------------------------------------------------------------------------------------------------------------------------------------------------------------
 Étape 6: Fonctionnalité Additionnelle - Blocage Temporaire de Colonnes.

  
  Ajouter une fonctionnalité permettant aux joueurs de bloquer temporairement 
 une colonne, empêchant l'adversaire d'y placer un pion pendant un certain nombre 
 de tours. Cette fonctionnalité ajoute une dimension stratégique au jeu.

 
  Exemple d'utilisation :
  Joueur 1 :  bloquer 3 (bloque la colonne 3 pour les 3 prochains tours)
  Joueur 2 :  4 (peut jouer normalement dans les colonnes non bloquées)
  Joueur 1 :  debloquer 3 (libère la colonne 3, prête à être utilisée à nouveau)
  
  L'intégration de cette fonctionnalité nécessitera des ajustements dans l'interface 
 graphique, le gestionnaire de partie et les règles du jeu. Une documentation devra 
 être fournie dans le fichier README pour expliquer son fonctionnement.
 
[PROJET_POO.pdf](https://github.com/GemimaOndele/Projet-POO-JAVA/files/14280382/PROJET_POO.pdf)
