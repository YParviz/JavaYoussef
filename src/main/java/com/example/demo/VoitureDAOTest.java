package com.example.demo;

import org.testng.annotations.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static org.testng.Assert.assertEquals;

public class VoitureDAOTest {
    private static final String DB_URL = "jdbc:mysql://devbdd.iutmetz.univ-lorraine.fr:3306/parviz1u_exam";
    private static final String USERNAME = "parviz1u_appli";
    private static final String PASSWORD = "32216049";

    private VoitureDAO voitureDAO;

    public VoitureDAOTest() throws SQLException {
        voitureDAO = new VoitureDAO();
    }

    private void NetoieBaseDonnées() throws SQLException {

        try (Connection con = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
             Statement st = con.createStatement()) {
            st.executeUpdate("DELETE FROM tvoiture");
        }
    }

    @Test
    public void testAjouterVoiture() throws SQLException {
        NetoieBaseDonnées();

        List<Voiture> voituresAvant = voitureDAO.getVoitures();
        int nombreAvant = voituresAvant.size();

        Voiture nouvelleVoiture = new Voiture("Clio", 1000);
        voitureDAO.ajouterVoiture(nouvelleVoiture);

        List<Voiture> voituresApres = voitureDAO.getVoitures();
        int nombreApres = voituresApres.size();

        assertEquals(nombreAvant + 1, nombreApres, "Le nombre de voitures après l'ajout doit être incrémenté de 1");
    }

    @Test
    public void testSupprimerToutesVoitures() throws SQLException {
        NetoieBaseDonnées();

        Voiture voiture1 = new Voiture("Peugeot", 1000);
        Voiture voiture2 = new Voiture("Renault", 2000);
        Voiture voiture3 = new Voiture("BMW", 3000);
        voitureDAO.ajouterVoiture(voiture1);
        voitureDAO.ajouterVoiture(voiture2);
        voitureDAO.ajouterVoiture(voiture3);

        List<Voiture> voituresAvant = voitureDAO.getVoitures();
        assertEquals(3, voituresAvant.size(), "Il doit y avoir 3 voitures avant la suppression");

        voitureDAO.supprimerToutesVoitures();

        List<Voiture> voituresApres = voitureDAO.getVoitures();
        assertEquals(0, voituresApres.size(), "Il ne doit plus y avoir de voiture après la suppression");
    }
}
