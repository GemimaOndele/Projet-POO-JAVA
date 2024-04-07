package Modele;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HistoriquePartie {
    private static final Logger LOGGER = Logger.getLogger(HistoriquePartie.class.getName());
    private static final String CHEMIN_FICHIER = "log.txt"; // Assurez-vous que ce chemin est correct

    // Méthode pour lire l'historique des parties depuis le fichier log.txt
    public static String lireHistorique() {
        StringBuilder contenu = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(CHEMIN_FICHIER))) {
            String ligne;
            while ((ligne = br.readLine()) != null) {
                contenu.append(ligne).append("\n");
            }
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Un problème est survenu lors de la lecture du fichier d'historique", e);
            // En production, envisagez d'utiliser un logger plus robuste
        }
        return contenu.toString();
    }

    // Méthode pour enregistrer une nouvelle partie dans le fichier log.txt
    public static void enregistrerPartie(String detailsPartie) {
        try (FileWriter fw = new FileWriter(CHEMIN_FICHIER, true); // L'option 'true' est pour écrire en mode 'append'
             PrintWriter pw = new PrintWriter(fw)) {
            pw.println(detailsPartie);
            LOGGER.info("Partie enregistrée dans le fichier d'historique."); // Confirmation de l'enregistrement réussi
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Impossible d'enregistrer la partie dans le fichier d'historique", e);
            // En production, envisagez d'utiliser un logger plus robuste
        }
    }
}
