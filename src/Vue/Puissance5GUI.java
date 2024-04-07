package Vue;

import Modele.HistoriquePartie;

import javax.swing.*;

public class Puissance5GUI {
    public void afficherHistorique() {
        String historique = HistoriquePartie.lireHistorique();

        JFrame fenetre = new JFrame("Historique des parties");
        fenetre.setSize(400, 600);
        fenetre.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JTextArea textArea = new JTextArea(10, 30);
        textArea.setText(historique);
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        fenetre.add(scrollPane);

        fenetre.setVisible(true);
    }
}
