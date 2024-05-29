package com.example.demo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VoitureDAO {
    private static final String DB_URL = "jdbc:mysql://devbdd.iutmetz.univ-lorraine.fr:3306/parviz1u_exam";
    private static final String USERNAME = "parviz1u_appli";
    private static final String PASSWORD = "32216049";

    public VoitureDAO() {
        // Test de connexion à la base de données
        try (Connection connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD)) {
            System.out.println("Connexion à la base de données réussie !");
        } catch (SQLException e) {
            System.err.println("Erreur lors de la connexion à la base de données : " + e.getMessage());
        }
    }


    // Pour récupérer la liste des voitures
    public static List<Voiture> getVoitures() {
        List<Voiture> voitures = new ArrayList<>();
        try (Connection con = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM tvoiture")) {

            while (rs.next()) {
                String nom = rs.getString("nom");
                double prix = rs.getDouble("prix");
                voitures.add(new Voiture(nom, prix));
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la récupération des voitures : " + e.getMessage());
        }
        return voitures;
    }

    // Pour ajouter une voiture
    public void ajouterVoiture(Voiture voiture) {
        try (Connection con = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
             PreparedStatement pst = con.prepareStatement("INSERT INTO tvoiture (nom, prix) VALUES (?, ?)")) {

            pst.setString(1, voiture.getNom());
            pst.setDouble(2, voiture.getPrix());

            pst.executeUpdate();
            System.out.println("Voiture ajoutée avec succès !");
        } catch (SQLException e) {
            System.err.println("Erreur lors de l'ajout de la Voiture : " + e.getMessage());
        }
    }
    public static void supprimerToutesVoitures() {
        try (Connection con = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
             Statement st = con.createStatement()) {

            st.executeUpdate("DELETE FROM tvoiture");
            System.out.println("Toutes les voitures ont été supprimées avec succès !");
        } catch (SQLException e) {
            System.err.println("Erreur lors de la suppression des voitures : " + e.getMessage());
        }
    }
}
