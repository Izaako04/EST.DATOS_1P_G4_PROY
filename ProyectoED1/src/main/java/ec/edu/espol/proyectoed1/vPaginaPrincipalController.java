/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.proyectoed1;

import ec.edu.espol.proyectoed1.classes.Filtrable;
import ec.edu.espol.proyectoed1.classes.Vehiculo;
import java.net.URL;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author isaias
 */
public class vPaginaPrincipalController implements Initializable, Filtrable {

    @FXML
    private VBox contenedorHbox;
    @FXML
    private Button btnMisVehiculos;
    @FXML
    private Button btnVenderVehiculo;
    @FXML
    private Button vCerrarSesión;
    @FXML
    private Text textoSaludoUsuario;
    @FXML
    private ComboBox<String> cmbTipoVehiculo; // probando!!
    @FXML
    private ComboBox<?> cmbMarca;
    @FXML
    private ComboBox<?> cmbModelo;
    @FXML
    private TextField tfPrecioDesde;
    @FXML
    private TextField tfPrecioHasta;
    @FXML
    private TextField tfKmHasta;
    @FXML
    private Button btnBuscar;
    @FXML
    private TextField tfYearDesde;
    @FXML
    private TextField tfYearHasta;
    @FXML
    private ComboBox<?> cmbOrdenar;
    
    private List<Vehiculo> vehiculos;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        // solo para probar estilos del combo box
        ObservableList<String> options = FXCollections.observableArrayList(
                "Opción 1",
                "Opción 2",
                "Opción 3"
        );

        // Agregar las opciones al ComboBox
        cmbTipoVehiculo.setItems(options);
        
        // El cuadro morado solo es para identificar el anchopane, cuando la parte del sistema
        // esté terminada y se puedan colocar los autos con sus fotos el fondo pasará a negro
    }    
    
    public void home () {
    }

    @Override
    public void filtrarPorX(Comparator cmp) {
        vehiculos = (List) Vehiculo.getPriorityQueue(vehiculos, cmp); 
    }

    @Override
    public void filtrarPorY(List<Object> objetos) {
        
        // los objetos los obtendre mediante lo que el usuario haya elegido
        
      
    }

    
    

}
