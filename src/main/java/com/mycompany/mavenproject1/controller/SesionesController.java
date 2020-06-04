package com.mycompany.mavenproject1.controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.mycompany.mavenproject1.App;
import com.mycompany.mavenproject1.model.Ficha;
import com.mycompany.mavenproject1.model.FichaDAO;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Jorge
 */
public class SesionesController implements Initializable {

    @FXML
    private TableView<Ficha> table;

    @FXML
    private TableColumn<Ficha, Integer> cscolumn;

    @FXML
    private TableColumn<Ficha, Integer> cdcolumn;

    @FXML
    private TableColumn<Ficha, Integer> cfcolumn;

    @FXML
    private TableColumn<Ficha, String> nombrecolumn;

    @FXML
    private TableColumn<Ficha, Integer> duracioncolumn;

    private PrimaryController parent;

    private Object params;

    private Stage SesionStage;

    public void setStage(Stage SesionStage) {
        this.SesionStage = SesionStage;
    }

    public void setParent(PrimaryController p) {
        params = p;
    }

    public void setParams(Object p) {
        this.SesionStage = SesionStage;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }

}
