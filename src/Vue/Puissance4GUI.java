// Étape 1 : Interface Graphique
//Puissance4GUI.java
package Vue;

import javax.swing.*;
import java.awt.*;

public class Puissance4GUI {

    public static void main(String[] args) {
        // Configuration initiale du jeu via JOptionPane
        String[] options = {"Joueur Humain", "IA"};
        int choixJoueur1 = JOptionPane.showOptionDialog(
                null,
                "Choisissez le type de Joueur 1:",
                "Puissance 4 - Configuration",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]);

        String joueur1Nom = JOptionPane.showInputDialog("Nom du Joueur 1:");

        int choixJoueur2 = JOptionPane.showOptionDialog(
                null,
                "Choisissez le type de Joueur 2:",
                "Puissance 4 - Configuration",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]);

        String joueur2Nom = JOptionPane.showInputDialog("Nom du Joueur 2:");

        JOptionPane.showMessageDialog(null,
                "Joueur 1 (" + (choixJoueur1 == 0 ? "Humain" : "IA") + "): " + joueur1Nom + " - Pion: X" + "\n" +
                        "Joueur 2 (" + (choixJoueur2 == 0 ? "Humain" : "IA") + "): " + joueur2Nom + " - Pion: O",
                "Configuration de la Partie",
                JOptionPane.INFORMATION_MESSAGE);

        // Création de la fenêtre principale du jeu
        JFrame fenetrePrincipale = new JFrame("Puissance 4");
        fenetrePrincipale.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fenetrePrincipale.setSize(800, 600);
        fenetrePrincipale.setLayout(new FlowLayout()); // Utilisez un gestionnaire de mise en page approprié

        // Bouton pour afficher l'historique
        JButton btnHistorique = new JButton("Afficher l'historique");
        btnHistorique.addActionListener(e -> {
            // Modifier ici pour appeler directement afficherHistorique
            new Vue.Puissance5GUI().afficherHistorique();
        });

        fenetrePrincipale.add(btnHistorique);

        // Afficher la fenêtre principale
        fenetrePrincipale.setVisible(true);

        // Créer et démarrer une instance du jeu
        // Remarque : Assurez-vous que votre GestionnairePartie enregistre correctement les parties dans le fichier d'historique
        Modele.GestionnairePartie gestionnaire = new Modele.GestionnairePartie(joueur1Nom, joueur2Nom, "X", "O");
        gestionnaire.commencerPartie();
    }
}
