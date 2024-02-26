package Tests.ModeleTests;//Étape 3 : Joueurs

import Modele.JoueurHumain;
import org.junit.Assert;
import org.junit.Test;

public class JoueurHumainTest {
    @Test
    public void testProchainCoup() {
        JoueurHumain joueur = new JoueurHumain();

        // Simuler un clic sur une colonne
        joueur.setColonneChoisie(3);

        // Vérifier que le coup retourné est correct
        Assert.assertEquals(3, joueur.prochainCoup());
    }
}
