package com.mycompany.mavenproject1.controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.mycompany.mavenproject1.App;
import com.mycompany.mavenproject1.model.Sesion;
import com.mycompany.mavenproject1.model.SesionDAO;
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
public class SesionesController implements Initializable {

    @FXML
    private TableView<Sesion> table;

    @FXML
    private TableColumn<Sesion, Integer> cscolumn;

    @FXML
    private TableColumn<Sesion, Integer> cdcolumn;

    @FXML
    private TableColumn<Sesion, Integer> cfcolumn;

    @FXML
    private TableColumn<Sesion, Integer> cd2column;

    @FXML
    private TableColumn<Sesion, Integer> cf2column;

    @FXML
    private TableColumn<Sesion, String> nombrecolumn;

    @FXML
    private TableColumn<Sesion, Integer> duracioncolumn;
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

    public ObservableList<Sesion> data;
    public ObservableList<Sesion> data2;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.data = FXCollections.observableArrayList();
        this.data2 = FXCollections.observableArrayList();

        List<Sesion> misJugadores = SesionDAO.selectAll();
        data.addAll(misJugadores);

        this.cscolumn.setCellValueFactory(eachRowData -> {
            return new SimpleObjectProperty<>(eachRowData.getValue().getCS());
        });

        this.cdcolumn.setCellValueFactory(eachRowData -> {
            return new SimpleObjectProperty<>(eachRowData.getValue().getCD());
        });

        this.cfcolumn.setCellValueFactory(eachRowData -> {
            return new SimpleObjectProperty<>(eachRowData.getValue().getCF());
        });

        this.cd2column.setCellValueFactory(eachRowData -> {
            return new SimpleObjectProperty<>(eachRowData.getValue().getCD2());
        });

        this.cf2column.setCellValueFactory(eachRowData -> {
            return new SimpleObjectProperty<>(eachRowData.getValue().getCF2());
        });

        this.nombrecolumn.setCellValueFactory(eachRowData -> {
            return new SimpleObjectProperty<>(eachRowData.getValue().getNombre());
        });

        this.duracioncolumn.setCellValueFactory(eachRowData -> {
            return new SimpleObjectProperty<>(eachRowData.getValue().getDuracion());
        });

        nombrecolumn.setCellFactory(TextFieldTableCell.forTableColumn());
        nombrecolumn.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Sesion, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Sesion, String> t) {

                Sesion selected = (Sesion) t.getTableView().getItems().get(
                        t.getTablePosition().getRow());

                selected.setNombre(t.getNewValue());  //<<- update lista en vista

                SesionDAO dao = new SesionDAO(selected); //update en mysql
                dao.save();
            }
        }
        );

        table.setEditable(true);
        table.setItems(data);

    }

    @FXML
    public void borrarsesion() {
        Sesion selected = table.getSelectionModel().getSelectedItem();
        if (selected != null) {
            if (!showConfirm(selected.getNombre())) {
                return;
            }
            data.remove(selected);
            SesionDAO dao = new SesionDAO(selected);
            dao.remove(selected);
        } else {
            showWarning("¡Advertencia!", "Ninguna sesión a borrar", "Seleccione una sesión antes de pulsar BORRAR");
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
        alert.setContentText("Desea borrar la Sesión " + nombre);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            return true;
        } else {
            return false;
        }
    }

    public void openCrearSesion() {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("CrearSesion.fxml"));
        Parent modal;
        try {
            modal = fxmlLoader.load();

            Stage modalStage = new Stage();
            modalStage.setTitle("Nueva Sesión");
            modalStage.initModality(Modality.APPLICATION_MODAL);
            modalStage.initOwner(App.rootstage);

            Scene modalScene = new Scene(modal);
            modalStage.setScene(modalScene);

            CrearSesionController modalController = fxmlLoader.getController();
            if (modalController != null) {
                if (modalController != null) {
                    modalController.setStage(modalStage);
                    modalController.setParent(this);
                }
            }

            modalStage.showAndWait();

        } catch (IOException ex) {
            Logger.getLogger(SesionesController.class.getName()).log(Level.SEVERE, null, ex);
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
            for (Sesion j : this.data) {
                if (j.getNombre().toLowerCase().contains(bnombre.toLowerCase())) {
                    this.data2.add(j);
                }
            }
            this.table.setItems(data2);
        }
    }

}
