package com.mycompany.mavenproject1.controller;

import com.mycompany.mavenproject1.model.Ficha;
import com.mycompany.mavenproject1.model.FichaDAO;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Jorge
 */
public class CrearFichaController implements Initializable {

    @FXML
    private TextField nombre;

    @FXML
    private TextField raza;

    @FXML
    private TextField clase;

    @FXML
    private TextField fuerza;

    @FXML
    private TextField agilidad;

    @FXML
    private TextField magia;

    private FichasController parent;

    private Object params;

    private Stage myStage;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void setStage(Stage myStage) {
        this.myStage = myStage;
    }

    public void setParent(FichasController p) {
        this.parent = p;
    }

    public void setParams(Object p) {
        params = p;
    }

    @FXML
    private void anadir() {
        String Nombre = this.nombre.getText();
        String Raza = this.raza.getText();
        String Clase = this.clase.getText();
        String Fuerza = this.fuerza.getText();
        String Agilidad = this.agilidad.getText();
        String Magia = this.magia.getText();

        if (Nombre.trim().length() > 0 && Raza.trim().length() > 0 && Clase.trim().length() > 0 && Fuerza.trim().length() > 0 && Agilidad.trim().length() > 0 && Magia.trim().length() > 0) {
            Ficha newFicha = new Ficha();
            newFicha.setNombre(Nombre);
            newFicha.setRaza(Raza);
            newFicha.setClase(Clase);
            int Fuerrza = Integer.parseInt(Fuerza);
            newFicha.setFUE(Fuerrza);
            int Aggilidad = Integer.parseInt(Agilidad);
            newFicha.setAGI(Aggilidad);
            int Maggia = Integer.parseInt(Magia);
            newFicha.setMAG(Maggia);

            FichaDAO J = new FichaDAO(newFicha);
            int CF = J.save();

            newFicha.setCF(CF);
            parent.data.add(newFicha);
            if (this.myStage != null) {
                this.myStage.close();
            }
        } else {
            if (parent != null) {
                parent.showWarning("Error de validación", "Corrija los errores", "Debe contener información");
            }
        }

    }

    @FXML
    private void cancelar() {
        if (this.myStage != null) {
            this.myStage.close();
        }
    }
}
