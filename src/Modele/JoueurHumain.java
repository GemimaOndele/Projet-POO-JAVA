package Modele;

//Etape 3 : Joueurs

// JoueurHumain.java
public class JoueurHumain implements Joueur {
    private int colonneChoisie = -1;

    // Méthode appelée lorsqu'un coup doit être joué par le joueur humain
    @Override
    public int prochainCoup() {
        // Attente jusqu'à ce que le joueur choisisse une colonne
        while (colonneChoisie == -1) {
            try {
                Thread.sleep(100); // Pause pour éviter la surcharge du processeur
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        int colonne = colonneChoisie;
        colonneChoisie = -1; // Réinitialisation de la colonne choisie pour le prochain tour
        return colonne;
    }

    // Méthode pour définir la colonne choisie par le joueur
    public void setColonneChoisie(int colonne) {
        this.colonneChoisie = colonne;
    }
}
