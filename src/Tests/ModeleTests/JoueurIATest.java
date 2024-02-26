package Tests.ModeleTests;

//Étape 3 : Joueurs

import Modele.JoueurIA;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertTrue;

    public class JoueurIATest {
        @Test
        public void testProchainCoup() {
            // Créer une grille vide
            char[][] grille = new char[6][7];
            for (char[] chars : grille) {
                Arrays.fill(chars, ' ');
            }

            // Créer une instance de JoueurIA avec la grille vide
            JoueurIA joueurIA = new JoueurIA(grille);

            // Effectuer le test plusieurs fois pour vérifier que le coup est valide
            for (int i = 0; i < 10; i++) {
                int coup = joueurIA.prochainCoup();
                assertTrue("Le coup doit être dans la plage de colonnes de la grille", coup >= 0 && coup < grille[0].length);
                assertTrue("La colonne choisie doit être libre", colonneLibre(grille, coup));
            }
        }

        // Méthode pour vérifier si une colonne est libre dans la grille
        private boolean colonneLibre(char[][] grille, int colonne) {
            return grille[0][colonne] == ' ';
        }
    }
