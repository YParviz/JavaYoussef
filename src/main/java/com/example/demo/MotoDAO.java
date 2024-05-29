package com.example.demo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MotoDAO {
    private static final String DB_URL = "jdbc:mysql://devbdd.iutmetz.univ-lorraine.fr:3306/parviz1u_exam";
    private static final String USERNAME = "parviz1u_appli";
    private static final String PASSWORD = "32216049";

    public MotoDAO() {
        // Test de connexion à la base de données
        try (Connection connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD)) {
            System.out.println("Connexion à la base de données réussie !");
        } catch (SQLException e) {
            System.err.println("Erreur lors de la connexion à la base de données : " + e.getMessage());
        }
    }


    // Pour récupérer la liste des motos
    public static List<Moto> getMotos() {
        List<Moto> motos = new ArrayList<>();
        try (Connection con = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM tmoto")) {

            while (rs.next()) {
                String nom = rs.getString("nom");
                double prix = rs.getDouble("prix");
                motos.add(new Moto(nom, prix));
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la récupération des motos : " + e.getMessage());
        }
        return motos;
    }

    // Pour ajouter une moto
    public void ajouterMoto(Moto moto) {
        try (Connection con = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
             PreparedStatement pst = con.prepareStatement("INSERT INTO tmoto (nom, prix) VALUES (?, ?)")) {

            pst.setString(1, moto.getNom());
            pst.setDouble(2, moto.getPrix());

            pst.executeUpdate();
            System.out.println("Moto ajoutée avec succès !");
        } catch (SQLException e) {
            System.err.println("Erreur lors de l'ajout de la Moto : " + e.getMessage());
        }
    }
    public void supprimerToutesMoto() {
        try (Connection con = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
             Statement st = con.createStatement()) {

            st.executeUpdate("DELETE FROM tmoto");
            System.out.println("Toutes les motos ont été supprimées avec succès !");
        } catch (SQLException e) {
            System.err.println("Erreur lors de la suppression des motos : " + e.getMessage());
        }
    }
}
