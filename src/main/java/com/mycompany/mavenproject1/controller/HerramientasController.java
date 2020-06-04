package com.mycompany.mavenproject1.controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Jorge
 */
public class HerramientasController implements Initializable {

    @FXML
    private TextField d6;

    @FXML
    private TextField d8;

    @FXML
    private TextField d10;

    @FXML
    private TextField d12;

    @FXML
    private TextField d20;

    @FXML
    private TextField d100;

    @FXML
    private TextField evento;

    @FXML
    private TextField recompensa;

    private PrimaryController parent;

    private Object params;

    private Stage myStage;

    public void setStage(Stage myStage) {
        this.myStage = myStage;
    }

    public void setParent(PrimaryController p) {
        params = p;
    }

    public void setParams(Object p) {
        this.myStage = myStage;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void tirarDado6() {
        int dado = 6;
        int resultado;
        resultado = (int) (Math.random() * dado);
        // En valor 0 corresponderá al valor maximo del dado
        if (resultado == 0) {
            resultado = dado;
        }
        this.d6.setText(resultado + "");
    }
    
    @FXML
    private void tirarDado8() {
        int dado = 8;
        int resultado;
        resultado = (int) (Math.random() * dado);
        // En valor 0 corresponderá al valor maximo del dado
        if (resultado == 0) {
            resultado = dado;
        }
        this.d8.setText(resultado + "");
    }
    
    @FXML
    private void tirarDado10() {
        int dado = 10;
        int resultado;
        resultado = (int) (Math.random() * dado);
        // En valor 0 corresponderá al valor maximo del dado
        if (resultado == 0) {
            resultado = dado;
        }
        this.d10.setText(resultado + "");
    }
    
    @FXML
    private void tirarDado12() {
        int dado = 12;
        int resultado;
        resultado = (int) (Math.random() * dado);
        // En valor 0 corresponderá al valor maximo del dado
        if (resultado == 0) {
            resultado = dado;
        }
        this.d12.setText(resultado + "");
    }
    
    @FXML
    private void tirarDado20() {
        int dado = 20;
        int resultado;
        resultado = (int) (Math.random() * dado);
        // En valor 0 corresponderá al valor maximo del dado
        if (resultado == 0) {
            resultado = dado;
        }
        this.d20.setText(resultado + "");
    }
    
    @FXML
    private void tirarDado100() {
        int dado = 100;
        int resultado;
        resultado = (int) (Math.random() * dado);
        // En valor 0 corresponderá al valor maximo del dado
        if (resultado == 0) {
            resultado = dado;
        }
        this.d100.setText(resultado + "");
    }

    @FXML
    private void evento(ActionEvent event) {
        String evento = "";
        int aleatorio = (int) (Math.random() * 6);
        String[] eventos = {"Historia", "Misión", "Mazmorra", "Combate", "Aldea", "Viaje"};
        evento = eventos[aleatorio];
        this.evento.setText(evento);
    }

    @FXML
    private void recompensa(ActionEvent event) {
        String recompensa = "";
        int aleatorio = (int) (Math.random() * 8);
        String[] recompensas = {"Oro", "Objeto magico", "Arma", "Armadura", "Aliado", "Nada", "Maldición", "Mapa del tesoro"};
        recompensa = recompensas[aleatorio];
        this.recompensa.setText(recompensa);
    }

    @FXML
    private void cancelar() {
        if (this.myStage != null) {
            this.myStage.close();
        }
    }
}
