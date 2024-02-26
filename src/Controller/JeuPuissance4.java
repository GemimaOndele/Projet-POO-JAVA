//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
package Controller;

import Vue.Puissance4GUI;

import javax.swing.*;
import java.awt.image.ImageObserver;
import java.util.Objects;


public class JeuPuissance4 {
    public static void main(String[] args) {
        // Chargement des icônes des joueurs
        ImageIcon joueur1Icon = new ImageIcon(Objects.requireNonNull(JeuPuissance4.class.getResource("/Vue/icon1.jpeg")));
        ImageIcon joueur2Icon = new ImageIcon(Objects.requireNonNull(JeuPuissance4.class.getResource("/Vue/icon2.jpeg")));

        // Vérification du chargement des images
        if (joueur1Icon.getImageLoadStatus() != ImageObserver.ALLBITS) {
            System.out.println("Erreur lors du chargement de l'icône du joueur 1.");
        }

        if (joueur2Icon.getImageLoadStatus() != ImageObserver.ALLBITS) {
            System.out.println("Erreur lors du chargement de l'icône du joueur 2.");
        }

        // Lancer l'interface graphique
        SwingUtilities.invokeLater(() -> {
            Puissance4GUI puissance4GUI = new Puissance4GUI(joueur1Icon, joueur2Icon);
            puissance4GUI.lancerConfigurationPartie();
        });
    }
}
