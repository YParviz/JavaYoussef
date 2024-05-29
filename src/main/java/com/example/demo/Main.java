package com.example.demo;

import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        VoitureDAO voitureDAO = new VoitureDAO();


        Voiture newVoiture = new Voiture("Peugeot", 1000);
        voitureDAO.ajouterVoiture(newVoiture);


        List<Voiture> voitures = VoitureDAO.getVoitures();
        System.out.println("Liste des voitures :");
        for (Voiture voiture : voitures) {
            System.out.println(voiture.getNom() + " - " + voiture.getPrix());
        }


    }
}