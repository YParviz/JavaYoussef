package com.example.demo;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class VoitureTest {
    @Test
    public void testConstructeur() {
        Voiture voiture = new Voiture("Clio", 1000);
        assertEquals("Clio", voiture.getNom());
        assertEquals(1000, voiture.getPrix(), 0.001);
    }

    @Test
    public void testGettersEtSetters() {
        Voiture voiture = new Voiture("Clio", 1000);

        voiture.setNom("BMW");
        assertEquals("BMW", voiture.getNom());

        voiture.setPrix(2000);
        assertEquals(2000, voiture.getPrix(),0.001);

    }
}
