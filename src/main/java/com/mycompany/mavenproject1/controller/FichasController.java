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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Jorge
 */
public class FichasController implements Initializable {

    @FXML
    private TableView<Ficha> table;

    @FXML
    private TableColumn<Ficha, Integer> cfcolumn;

    @FXML
    private TableColumn<Ficha, String> nombrecolumn;

    @FXML
    private TableColumn<Ficha, String> razacolumn;

    @FXML
    private TableColumn<Ficha, String> clasecolumn;

    @FXML
    private TableColumn<Ficha, Integer> fuerzacolumn;

    @FXML
    private TableColumn<Ficha, Integer> agilidadcolumn;

    @FXML
    private TableColumn<Ficha, Integer> magiacolumn;
    
    @FXML
    private TextField buscaNombre;

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

    public ObservableList<Ficha> data;
    public ObservableList<Ficha> data2;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.data = FXCollections.observableArrayList();
        this.data2 = FXCollections.observableArrayList();

        List<Ficha> misFichas = FichaDAO.selectAll();
        data.addAll(misFichas);

        this.cfcolumn.setCellValueFactory(eachRowData -> {
            return new SimpleObjectProperty<>(eachRowData.getValue().getCF());
        });

        this.nombrecolumn.setCellValueFactory(eachRowData -> {
            return new SimpleObjectProperty<>(eachRowData.getValue().getNombre());
        });

        this.razacolumn.setCellValueFactory(eachRowData -> {
            return new SimpleObjectProperty<>(eachRowData.getValue().getRaza());
        });

        this.clasecolumn.setCellValueFactory(eachRowData -> {
            return new SimpleObjectProperty<>(eachRowData.getValue().getClase());
        });

        this.fuerzacolumn.setCellValueFactory(eachRowData -> {
            return new SimpleObjectProperty<>(eachRowData.getValue().getFUE());
        });

        this.agilidadcolumn.setCellValueFactory(eachRowData -> {
            return new SimpleObjectProperty<>(eachRowData.getValue().getAGI());
        });

        this.magiacolumn.setCellValueFactory(eachRowData -> {
            return new SimpleObjectProperty<>(eachRowData.getValue().getMAG());
        });

        nombrecolumn.setCellFactory(TextFieldTableCell.forTableColumn());
        nombrecolumn.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Ficha, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Ficha, String> t) {

                Ficha selected = (Ficha) t.getTableView().getItems().get(
                        t.getTablePosition().getRow());

                selected.setNombre(t.getNewValue());  //<<- update lista en vista

                FichaDAO dao = new FichaDAO(selected); //update en mysql
                dao.save();
            }
        }
        );

        razacolumn.setCellFactory(TextFieldTableCell.forTableColumn());
        razacolumn.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Ficha, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Ficha, String> t) {

                Ficha selected = (Ficha) t.getTableView().getItems().get(
                        t.getTablePosition().getRow());

                selected.setRaza(t.getNewValue());  //<<- update lista en vista

                FichaDAO dao = new FichaDAO(selected); //update en mysql
                dao.save();
            }
        }
        );

        clasecolumn.setCellFactory(TextFieldTableCell.forTableColumn());
        clasecolumn.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Ficha, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Ficha, String> t) {

                Ficha selected = (Ficha) t.getTableView().getItems().get(
                        t.getTablePosition().getRow());

                selected.setClase(t.getNewValue());  //<<- update lista en vista

                FichaDAO dao = new FichaDAO(selected); //update en mysql
                dao.save();
            }
        }
        );

        table.setEditable(true);
        table.setItems(data);

    }

    @FXML
    public void borrarficha() {
        Ficha selected = table.getSelectionModel().getSelectedItem();
        if (selected != null) {
            if (!showConfirm(selected.getNombre())) {
                return;
            }
            data.remove(selected);
            FichaDAO dao = new FichaDAO(selected);
            dao.remove(selected);
        } else {
            showWarning("¡Advertencia!", "Ningúna ficha a borrar", "Seleccione una Ficha antes de pulsar BORRAR");
        }
    }

    public void showWarning(String title, String header, String description) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(description);
        alert.showAndWait();
    }

    public boolean showConfirm(String nombre) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmación");
        alert.setHeaderText("BORRAR");
        alert.setContentText("Desea borrar la ficha del personaje " + nombre);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            return true;
        } else {
            return false;
        }
    }

    public void openCrearFicha() {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("CrearFicha.fxml"));
        Parent modal;
        try {
            modal = fxmlLoader.load();

            Stage modalStage = new Stage();
            modalStage.setTitle("Nueva Ficha");
            modalStage.initModality(Modality.APPLICATION_MODAL);
            modalStage.initOwner(App.rootstage);

            Scene modalScene = new Scene(modal);
            modalStage.setScene(modalScene);

            CrearFichaController modalController = fxmlLoader.getController();
            if (modalController != null) {
                if (modalController != null) {
                    modalController.setStage(modalStage);
                    modalController.setParent(this);
                }
            }

            modalStage.showAndWait();

        } catch (IOException ex) {
            Logger.getLogger(FichasController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void cancelar() {
        if (this.myStage != null) {
            this.myStage.close();
        }
    }
    
    @FXML
    private void buscarNombre(KeyEvent event) {
        String bnombre = this.buscaNombre.getText();
        if (bnombre.isEmpty()) {
            this.table.setItems(data);
        } else {
            this.data2.clear();
            for (Ficha j : this.data) {
                if (j.getNombre().toLowerCase().contains(bnombre.toLowerCase())) {
                    this.data2.add(j);
                }
            }
            this.table.setItems(data2);
        }
    }
}
