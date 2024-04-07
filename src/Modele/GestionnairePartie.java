
//Étape 2 : Gestionnaire de Partie pour le jeu Puissance 4(Puissance4GUI)

/* Cela inclut la gestion de la grille, les tours des joueurs,
la détection de la fin de la partie, ainsi que l'affichage du résultat
et d'autres fonctionnalités liées à la gestion de la partie. */

package Modele;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class GestionnairePartie {
    private final String joueur1Nom;
    private final String joueur2Nom;
    private final String joueur1Pion;
    private final String joueur2Pion;
    private final char[][] grille;
    private boolean partieTerminee;
    private int tourActuel;

    private JButton[][] boutonsGrille;

    private JLabel scoreLabel;
    private JLabel chronoLabel;
    private JLabel tempsJeuLabel;
    private JLabel tempsJeuJoueur1Label;
    private JLabel tempsJeuJoueur2Label;

    private long tempsDebutPartie;
    private long tempsDebutTour;
    private long tempsTotalJoueur1;
    private long tempsTotalJoueur2;
    private Timer chronometreTour;
    private Timer chronoTempsJeu;

    public GestionnairePartie(String joueur1Nom, String joueur2Nom, String joueur1Pion, String joueur2Pion) {
        this.joueur1Nom = joueur1Nom;
        this.joueur2Nom = joueur2Nom;
        this.joueur1Pion = joueur1Pion;
        this.joueur2Pion = joueur2Pion;
        this.grille = new char[6][7];
        this.partieTerminee = true;
        this.tourActuel = 1;
        this.tempsDebutPartie = 0;
        this.tempsTotalJoueur1 = 0;
        this.tempsTotalJoueur2 = 0;
    }

    public void commencerPartie() {
        if (partieTerminee) {
            partieTerminee = false;
            initialiserGrille();
            tourActuel = 1;
            tempsDebutPartie = System.currentTimeMillis();

            SwingUtilities.invokeLater(() -> {
                JFrame frame = new JFrame("Puissance 4");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setLayout(new BorderLayout());

                JPanel grillePanel = new JPanel(new GridLayout(6, 7));
                creerBoutonsGrille(grillePanel);

                JPanel mainPanel = new JPanel(new BorderLayout());
                mainPanel.add(grillePanel, BorderLayout.CENTER);

                scoreLabel = new JLabel("", SwingConstants.CENTER);
                afficherScoreInitial();
                mainPanel.add(scoreLabel, BorderLayout.SOUTH);

                chronoLabel = new JLabel("", SwingConstants.CENTER);
                mainPanel.add(chronoLabel, BorderLayout.NORTH);

                frame.add(mainPanel);

                tempsJeuLabel = new JLabel("Temps de jeu : 0 sec", SwingConstants.CENTER);
                frame.add(tempsJeuLabel, BorderLayout.WEST);

                tempsJeuJoueur1Label = new JLabel(joueur1Nom + " : 0 sec", SwingConstants.CENTER);
                tempsJeuJoueur2Label = new JLabel(joueur2Nom + " : 0 sec", SwingConstants.CENTER);
                frame.add(tempsJeuJoueur1Label, BorderLayout.NORTH);
                frame.add(tempsJeuJoueur2Label, BorderLayout.EAST);

                frame.pack();
                frame.setVisible(true);
            });

            demarrerTourChronometre();
            demarrerChronoTempsJeu();
        } else {
            afficherMessage("La partie est déjà en cours!");
        }
    }

    private void afficherScoreInitial() {
        SwingUtilities.invokeLater(() -> scoreLabel.setText("Score initial : " + joueur1Nom + " = 0 points, " + joueur2Nom + " = 0 points"));
    }

    private void initialiserGrille() {
        for (char[] chars : grille) {
            Arrays.fill(chars, ' ');
        }
    }

    private void creerBoutonsGrille(JPanel grillePanel) {
        boutonsGrille = new JButton[6][7];

        for (int i = 0; i < grille.length; i++) {
            for (int j = 0; j < grille[i].length; j++) {
                boutonsGrille[i][j] = new JButton("");
                boutonsGrille[i][j].setPreferredSize(new Dimension(50, 50));
                boutonsGrille[i][j].addActionListener(new ColonneListener(j));
                grillePanel.add(boutonsGrille[i][j]);
            }
        }
    }

    private void demarrerTourChronometre() {
        chronometreTour = new Timer();
        tempsDebutTour = System.currentTimeMillis();
        chronometreTour.scheduleAtFixedRate(new ChronoTask(), 0, 1000);
    }

    private void stopperTourChronometre() {
        if (chronometreTour != null) {
            chronometreTour.cancel();
            chronometreTour.purge();
        }
    }

    private void demarrerChronoTempsJeu() {
        chronoTempsJeu = new Timer();
        chronoTempsJeu.scheduleAtFixedRate(new ChronoTempsJeuTask(), 0, 1000);
    }

    private void stopperChronoTempsJeu() {
        if (chronoTempsJeu != null) {
            chronoTempsJeu.cancel();
            chronoTempsJeu.purge();
        }
    }

    private class ChronoTask extends TimerTask {
        @Override
        public void run() {
            long tempsActuel = System.currentTimeMillis();
            long tempsEcoule = (tempsActuel - tempsDebutTour) / 1000;
            SwingUtilities.invokeLater(() -> {
                chronoLabel.setText("Temps restant : " + (5 - tempsEcoule) + " sec");
                if (tempsEcoule >= 5) {
                    stopperTourChronometre();
                    changerJoueur();
                    JOptionPane.showMessageDialog(null, "Temps écoulé ! La main passe au joueur suivant.", "Temps écoulé", JOptionPane.INFORMATION_MESSAGE);
                }
            });
        }
    }

    private class ColonneListener implements ActionListener {
        private final int colonne;

        public ColonneListener(int colonne) {
            this.colonne = colonne;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (!partieTerminee) {
                int ligne = placerPion(colonne);
                if (victoire(ligne, colonne)) {
                    partieTerminee = true;
                    finDePartie();
                } else if (grillePleine()) {
                    partieTerminee = true;
                    finDePartie();
                } else {
                    stopperTourChronometre();
                    changerJoueur();
                }
            }
        }
    }

    private int placerPion(int colonne) {
        int ligne;
        for (ligne = grille.length - 1; ligne >= 0; ligne--) {
            if (grille[ligne][colonne] == ' ') {
                grille[ligne][colonne] = pionActuel();
                mettreAJourBouton(ligne, colonne);
                return ligne;
            }
        }
        return -1;
    }

    private boolean victoire(int ligne, int colonne) {
        char pion = pionActuel();
        return (compterPions(ligne, colonne, 1, 0, pion) + compterPions(ligne, colonne, -1, 0, pion) >= 3) ||
                (compterPions(ligne, colonne, 0, 1, pion) + compterPions(ligne, colonne, 0, -1, pion) >= 3) ||
                (compterPions(ligne, colonne, 1, 1, pion) + compterPions(ligne, colonne, -1, -1, pion) >= 3) ||
                (compterPions(ligne, colonne, 1, -1, pion) + compterPions(ligne, colonne, -1, 1, pion) >= 3);
    }

    private int compterPions(int ligne, int colonne, int deltaLigne, int deltaColonne, char pion) {
        int compteur = 0;
        int i = ligne + deltaLigne;
        int j = colonne + deltaColonne;
        while (i >= 0 && i < grille.length && j >= 0 && j < grille[i].length && grille[i][j] == pion) {
            compteur++;
            i += deltaLigne;
            j += deltaColonne;
        }
        return compteur;
    }

    private boolean grillePleine() {
        for (char[] ligne : grille) {
            for (char c : ligne) {
                if (c == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    private void changerJoueur() {
        long tempsEcoule = System.currentTimeMillis() - tempsDebutTour;
        if (tourActuel % 2 == 1) {
            tempsTotalJoueur1 += tempsEcoule;
            SwingUtilities.invokeLater(() -> tempsJeuJoueur1Label.setText(joueur1Nom + " : " + (tempsTotalJoueur1 / 1000) + " sec"));
        } else {
            tempsTotalJoueur2 += tempsEcoule;
            SwingUtilities.invokeLater(() -> tempsJeuJoueur2Label.setText(joueur2Nom + " : " + (tempsTotalJoueur2 / 1000) + " sec"));
        }

        tourActuel++;
        demarrerTourChronometre();
    }

    private void mettreAJourBouton(int ligne, int colonne) {
        char pion = pionActuel();
        String couleurPion = pion == joueur1Pion.charAt(0) ? "blue" : "red";
        boutonsGrille[ligne][colonne].setText("<html><font color='" + couleurPion + "'>" + pion + "</font></html>");
        boutonsGrille[ligne][colonne].setBackground(couleurPion.equals("blue") ? Color.BLUE : Color.RED);
        boutonsGrille[ligne][colonne].setEnabled(false);
    }

    private char pionActuel() {
        return tourActuel % 2 == 1 ? joueur1Pion.charAt(0) : joueur2Pion.charAt(0);
    }

    private void finDePartie() {
        String vainqueur = determinerVainqueur();
        long tempsEcoule = System.currentTimeMillis() - tempsDebutPartie;
        tempsTotalJoueur1 += tempsEcoule;
        tempsTotalJoueur2 += tempsEcoule;

        SwingUtilities.invokeLater(() -> {
            int pionsJoueur1 = compterPionsPlaces(joueur1Pion.charAt(0));
            int pionsJoueur2 = compterPionsPlaces(joueur2Pion.charAt(0));
            String scoreText = "Score : " + joueur1Nom + " = " + pionsJoueur1 + " points, " + joueur2Nom + " = " + pionsJoueur2 + " points. ";
            if (vainqueur != null) {
                int scoreBonus = vainqueur.equals(joueur1Nom) ? pionsJoueur1 * 2 : pionsJoueur2 * 2;
                scoreText += vainqueur + " est le vainqueur avec un score bonus de " + scoreBonus + ". Félicitations ! 🎉";
                afficherMessage("Le vainqueur est : " + vainqueur + ". Félicitations ! 🎉\n" +
                        "Score : " + joueur1Nom + " = " + pionsJoueur1 + " points, " + joueur2Nom + " = " + pionsJoueur2 + " points.\n" +
                        "Score bonus (" + vainqueur + ") : " + scoreBonus);
            } else {
                scoreText += "La partie est nulle.";
                afficherMessage("La partie est nulle.");
            }
            scoreLabel.setText(scoreText);
            tempsJeuLabel.setText("Temps de jeu total : " + (tempsEcoule / 1000) + " sec");
        });

        terminerPartie();

        // Déterminer les détails de la partie pour l'enregistrement
        // Déterminer les détails de la partie pour l'enregistrement
        String detailsPartie = "Partie terminée le " + new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date()) +
                "\nVainqueur: " + determinerVainqueur() +
                "\nTemps de jeu total: " + ((System.currentTimeMillis() - tempsDebutPartie) / 1000) + " sec" +
                "\nPions Joueur 1 (" + joueur1Nom + "): " + compterPionsPlaces(joueur1Pion.charAt(0)) +
                "\nPions Joueur 2 (" + joueur2Nom + "): " + compterPionsPlaces(joueur2Pion.charAt(0)) + "\n";

        // Enregistrer les détails de la partie dans l'historique
        HistoriquePartie.enregistrerPartie(detailsPartie);

    }

    private int compterPionsPlaces(char pion) {
        int count = 0;
        for (char[] ligne : grille) {
            for (char c : ligne) {
                if (c == pion) {
                    count++;
                }
            }
        }
        return count;
    }

    private String determinerVainqueur() {
        int pionsJoueur1 = compterPionsPlaces(joueur1Pion.charAt(0));
        int pionsJoueur2 = compterPionsPlaces(joueur2Pion.charAt(0));

        String vainqueur = null;

        if (pionsJoueur1 > pionsJoueur2) {
            vainqueur = joueur1Nom;
        } else if (pionsJoueur1 < pionsJoueur2) {
            vainqueur = joueur2Nom;
        }

        return vainqueur;
    }

    private void afficherMessage(String message) {
        JOptionPane.showMessageDialog(null, message);
    }

    private class ChronoTempsJeuTask extends TimerTask {
        @Override
        public void run() {
            SwingUtilities.invokeLater(() -> tempsJeuLabel.setText("Temps de jeu : " + ((System.currentTimeMillis() - tempsDebutPartie) / 1000) + " sec"));
        }
    }

    private void terminerPartie() {
        stopperTourChronometre();
        stopperChronoTempsJeu();
        partieTerminee = true;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GestionnairePartie gestionnairePartie = new GestionnairePartie("Joueur 1", "Joueur 2", "X", "O");
            gestionnairePartie.commencerPartie();
        });
    }
    public static class GameManager {
        private boolean[] blockedColumns;
    
        public GameManager(int numColumns) {
            blockedColumns = new boolean[numColumns];
        }
    
        public void blockColumn(int column) {
            blockedColumns[column] = true;
        }
    
        public void unblockColumn(int column) {
            blockedColumns[column] = false;
        }
    
        public boolean isColumnBlocked(int column) {
            return blockedColumns[column];
        }
    }    
}
