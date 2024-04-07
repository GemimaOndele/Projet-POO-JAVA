package Modele;

import java.util.logging.*;
import java.io.IOException;


public class GestionErreur {

    public static class Main {
    
    private static final Logger logger = Logger.getLogger(Main.class.getName());
    
    public void main(String[] args) {
        LogManager.getLogManager().reset();
        
        // Création du fichier de logs
        try {
            FileHandler fileHandler = new FileHandler("errors.log");
            fileHandler.setLevel(Level.SEVERE);
            logger.addHandler(fileHandler);
            SimpleFormatter formatter = new SimpleFormatter();
            fileHandler.setFormatter(formatter);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        // Fonction pour détecter les erreurs
        checkErrors();
    }
    
    public void checkErrors() {
        try {
            int input_value = 0; // Remplacer par la valeur d'entrée de l'utilisateur
            
            if (input_value < 0) {
                throw new IllegalArgumentException("La valeur saisie ne peut pas être négative.");
            }
            
        } catch (NumberFormatException e) {
            System.out.println("Veuillez saisir un nombre entier valide.");
            
        } catch (IllegalArgumentException e) {
            System.out.println("Erreur : " + e.getMessage());
            logger.log(Level.SEVERE, "Erreur détectée : " + e.getMessage());
            
        } catch (Exception e) {
            System.out.println("Une erreur inattendue s'est produite : " + e.getMessage());
            logger.log(Level.SEVERE, "Erreur inattendue détectée : " + e.getMessage());
        }
    }
} 

}