package com.example.demo;

public class Moto {
    private String nom;
    private double prix;

    public Moto(String nom, double prix) {
        this.nom = nom;
        this.prix = prix;

    }
    // Getters et Setters
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }
}

