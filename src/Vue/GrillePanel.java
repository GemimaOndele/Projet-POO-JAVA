package Vue;
// Étape 1 : Interface Graphique
//GrillePanel.java

import javax.swing.*;

public class GrillePanel extends JPanel {
    private final ImageIcon joueur1Icon;
    private final ImageIcon joueur2Icon;

    // Constructeur prenant les icônes en paramètres
    public GrillePanel(ImageIcon joueur1Icon, ImageIcon joueur2Icon) {
        this.joueur1Icon = joueur1Icon;
        this.joueur2Icon = joueur2Icon;
        creerBoutonsGrille();
    }

    private void creerBoutonsGrille() {
        // Créer les boutons de la grille avec les icônes des joueurs
        JButton[][] boutonsGrille = new JButton[6][7];

        for (int i = 0; i < boutonsGrille.length; i++) {
            for (int j = 0; j < boutonsGrille[i].length; j++) {
                boutonsGrille[i][j] = new JButton();
                boutonsGrille[i][j].setIcon((j % 2 == 0) ? joueur1Icon : joueur2Icon);
                this.add(boutonsGrille[i][j]);
            }
        }
    }
}
