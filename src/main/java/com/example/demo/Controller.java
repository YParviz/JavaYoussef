package com.example.demo;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class Controller {
    @FXML
    private TableView<Voiture> voitureTable;
    @FXML
    private TableView<Moto> motoTable;


    @FXML
    private TableColumn<Voiture, String> nameColumn;

    @FXML
    private TableColumn<Voiture, Double> priceColumn;
    @FXML
    private TableColumn<Moto, String> nameMotoColumn;

    @FXML
    private TableColumn<Moto, Double> priceMotoColumn;

    @FXML
    private TextField nameTextField;

    @FXML
    private TextField priceTextField;


    @FXML
    private Button addButton;

    @FXML
    private Button clearButton;

    @FXML
    private Button deleteButton;
    @FXML
    private VoitureDAO voitureDAO;
    @FXML
    private MotoDAO motoDAO;
    @FXML
    private void updateTable() {
        voitureTable.getItems().clear();
        List<Voiture> voitures = voitureDAO.getVoitures();
        voitureTable.getItems().addAll(voitures);
        motoTable.getItems().clear();
        List<Moto> motos = MotoDAO.getMotos();
        motoTable.getItems().addAll(motos);
    }

    @FXML
    public void initialize() {
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("prix"));
        nameMotoColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
        priceMotoColumn.setCellValueFactory(new PropertyValueFactory<>("prix"));

        // Initialize
        voitureDAO = new VoitureDAO();
        motoDAO = new MotoDAO();


        updateTable();
    }


    @FXML
    private void AjouterButton() {
        String name = nameTextField.getText();
        String priceText = priceTextField.getText();
        double price;

        if (!priceText.contains(".")) {

            priceText += ".0";
        }
        try {
            price = Double.parseDouble(priceText);
        } catch (NumberFormatException e) {

            System.err.println("Erreur: Entrée invalide pour le prix.");

            return;
        }


        Voiture voiture = new Voiture(name, price);
        voitureDAO.ajouterVoiture(voiture);

        voitureTable.getItems().add(voiture);

        updateTable();

        nameTextField.clear();
        priceTextField.clear();
    }
    @FXML
    private void AjouterButtonMoto() {
        String name = nameTextField.getText();
        String priceText = priceTextField.getText();
        double price;

        if (!priceText.contains(".")) {

            priceText += ".0";
        }
        try {
            price = Double.parseDouble(priceText);
        } catch (NumberFormatException e) {

            System.err.println("Erreur: Entrée invalide pour le prix.");

            return;
        }


        Moto moto = new Moto(name, price);
        motoDAO.ajouterMoto(moto);

        motoTable.getItems().add(moto);

        updateTable();

        nameTextField.clear();
        priceTextField.clear();
    }

    @FXML
    private void ViderButton() {
        VoitureDAO.supprimerToutesVoitures();
        updateTable();
    }

    @FXML
    private void ViderButtonMoto() {
        motoDAO.supprimerToutesMoto();
        updateTable();
    }

}
