package Modele;

//Etape 3 : Joueurs
// JoueurIA.java
import java.util.Random;

public class JoueurIA implements Joueur {
    private final char[][] grille;

    public JoueurIA(char[][] grille) {
        this.grille = grille;
    }

    // Méthode pour obtenir le prochain coup de l'IA
    @Override
    public int prochainCoup() {
        Random random = new Random();
        int colonne;
        do {
            colonne = random.nextInt(grille[0].length);
        } while (!colonneLibre(colonne));
        return colonne;
    }

    // Méthode pour vérifier si une colonne est libre
    private boolean colonneLibre(int colonne) {
        return grille[0][colonne] == ' ';
    }
}

