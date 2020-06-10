package com.mycompany.mavenproject1.controller;

import com.mycompany.mavenproject1.model.Sesion;
import com.mycompany.mavenproject1.model.SesionDAO;
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
public class CrearSesionController implements Initializable {

    @FXML
    private TextField nombre;

    @FXML
    private TextField duracion;

    @FXML
    private TextField cd;
    
    @FXML
    private TextField cf;
    
    @FXML
    private TextField cd2;
    
    @FXML
    private TextField cf2;

    private SesionesController parent;

    private Object params;

    private Stage myStage;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void setStage(Stage myStage) {
        this.myStage = myStage;
    }

    public void setParent(SesionesController p) {
        this.parent = p;
    }

    public void setParams(Object p) {
        params = p;
    }

    @FXML
    private void anadir() {
        String Nombre = this.nombre.getText();
        String Duracion = this.duracion.getText();
        String CD = this.cd.getText();
        String CF = this.cf.getText();
        String CD2 = this.cd2.getText();
        String CF2 = this.cf2.getText();

        if (Nombre.trim().length() > 0 && Duracion.trim().length() > 0 && CD.trim().length() > 0 && CF.trim().length() > 0 && CD2.trim().length() > 0 && CF2.trim().length() > 0) {
            Sesion newSesion = new Sesion();
            newSesion.setNombre(Nombre);
            int DDuracion = Integer.parseInt(Duracion);
            newSesion.setDuracion(DDuracion);
            int CDD = Integer.parseInt(CD);
            newSesion.setCD(CDD);
            int CFF = Integer.parseInt(CF);
            newSesion.setCF(CFF);
            int CDD2 = Integer.parseInt(CD2);
            newSesion.setCD2(CDD2);
            int CFF2 = Integer.parseInt(CF2);
            newSesion.setCF2(CFF2);

            SesionDAO J = new SesionDAO(newSesion);
            int CS = J.save();

            newSesion.setCS(CS);
            parent.data.add(newSesion);
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
