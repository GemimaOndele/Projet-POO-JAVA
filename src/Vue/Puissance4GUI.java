package Vue;

// Étape 1 : Interface Graphique
//Puissance4GUI.java

import Modele.GestionnairePartie;

import javax.swing.*;
import java.awt.*;

public class Puissance4GUI {
    private final ImageIcon joueur1Icon;
    private final ImageIcon joueur2Icon;

    public Puissance4GUI(ImageIcon joueur1Icon, ImageIcon joueur2Icon) {
        this.joueur1Icon = joueur1Icon;
        this.joueur2Icon = joueur2Icon;
    }

    public void lancerConfigurationPartie() {
        // Afficher le menu interactif pour choisir le type de joueur
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

        // Récupérer le nom du Joueur 1
        String joueur1Nom = JOptionPane.showInputDialog("Nom du Joueur 1:");

        // Afficher le menu interactif pour choisir le type de joueur 2
        int choixJoueur2 = JOptionPane.showOptionDialog(
                null,
                "Choisissez le type de Joueur 2:",
                "Puissance 4 - Configuration",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]);

        // Récupérer le nom du Joueur 2
        String joueur2Nom = JOptionPane.showInputDialog("Nom du Joueur 2:");

        // Attribuer les pions par défaut "X" et "O"
        String joueur1Pion = "X";
        String joueur2Pion = "O";

        // Afficher les informations des joueurs dans une boîte de dialogue
        JOptionPane.showMessageDialog(null,
                "Joueur 1 (" + (choixJoueur1 == 0 ? "Humain" : "IA") + "): " + joueur1Nom + " - Pion: " + joueur1Pion + "\n" +
                        "Joueur 2 (" + (choixJoueur2 == 0 ? "Humain" : "IA") + "): " + joueur2Nom + " - Pion: " + joueur2Pion,
                "Configuration de la Partie",
                JOptionPane.INFORMATION_MESSAGE);

        // Ici, on crée une instance de la classe GestionnairePartie et commencer la partie
        // Créer une instance de la classe GestionnairePartie
        GestionnairePartie gestionnaire = new GestionnairePartie(joueur1Nom, joueur2Nom, joueur1Pion, joueur2Pion);

        // Commencer la partie
        gestionnaire.commencerPartie();

        // Ajouter la grille à la fenêtre principale
        JFrame frame = new JFrame("Puissance 4");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        GrillePanel grillePanel = new GrillePanel(joueur1Icon, joueur2Icon); // Créer la grille avec les icônes
        frame.add(grillePanel, BorderLayout.CENTER);

        frame.pack();
        frame.setVisible(true);
    }
}
