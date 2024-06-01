/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.proyectoed1;

import ec.edu.espol.proyectoed1.TDAs.ArrayListG4;
import ec.edu.espol.proyectoed1.classes.Usuario;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Isaías
 */
public class vSubirVehiculoController implements Initializable {

    @FXML
    private TextField fcPrecio;
    @FXML
    private TextField fcKilometraje;
    @FXML
    private ComboBox<String> cmbMarca;
    @FXML
    private ComboBox<String> cmbModelo;
    @FXML
    private TextField fcYear;
    @FXML
    private TextField fcPlaca;
    @FXML
    private TextField fcMotor;
    @FXML
    private CheckBox cbTieneAccidente;
    @FXML
    private DatePicker dpFechaAccidente;
    @FXML
    private TextField fcUbicacionAccidente;
    @FXML
    private TextArea taDescripcionAccidente;
    @FXML
    private CheckBox cbTieneReparacion;
    @FXML
    private DatePicker dpFechaReparacion;
    @FXML
    private TextArea taDescripcionReparacion;
    @FXML
    private TextField fcUbicacion;
    @FXML
    private ComboBox<?> cmbTiransmision;
    @FXML
    private Button btnExteriorFrontal;
    @FXML
    private Button btnExteriorLateral;
    @FXML
    private Button btnExteriorTrasero;
    @FXML
    private Button btnInterior;
    @FXML
    private Button btnGuardar;
    @FXML
    private Button btnRegresar;
    
    private Parent root;
    private Stage stage;
    private Scene scene;
    @FXML
    private ImageView portadaVehiculo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
    private Map <String, ArrayListG4 <String> > generaMapa () {
        Map <String, ArrayListG4 <String> > marcaYModelo = new HashMap <> (); // prueba
        
        ArrayListG4 <String> nissan = new ArrayListG4 <String> ();
        nissan.add("Versa");
        nissan.add("Leaf");
        nissan.add("Kicks");
        
        ArrayListG4 <String> kia = new ArrayListG4 <String> ();
        kia.add("Picanto");
        kia.add("Sportage R");
        kia.add("Sportage Active");
        
        ArrayListG4 <String> toyota = new ArrayListG4 <String> ();
        toyota.add("Yaris FastBack");
        toyota.add("Fortuner");
        toyota.add("Corolla Sedan");
        
        ArrayListG4 <String> chevrolet = new ArrayListG4 <String> ();
        chevrolet.add("Aveo Family");
        chevrolet.add("Spark");
        chevrolet.add("Sail");
        
        marcaYModelo.put("Nissan", nissan);
        marcaYModelo.put("Kia", kia);
        marcaYModelo.put("Toyota", toyota);
        marcaYModelo.put("Chevrolet", chevrolet);
        
        return marcaYModelo;
    }
    
    public void home (Usuario user) {
        btnRegresar.setOnAction(event -> {
            try {
                regresar(user, event);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        
        Map <String, ArrayListG4 <String> > marcaYModelo = generaMapa();
        Set<String> keys = marcaYModelo.keySet();
        ObservableList<String> keyList = FXCollections.observableArrayList(keys);
        cmbMarca.setItems(keyList);
        
        cmbMarca.setOnAction(event -> configuraComboBox (marcaYModelo));
    }
    
    private void configuraComboBox (Map <String, ArrayListG4 <String> > marcaYModelo){
        String selectedKey = cmbMarca.getValue();
        if (selectedKey != null) {
            ArrayListG4 <String> values = marcaYModelo.get(selectedKey);
            cmbModelo.setItems(FXCollections.observableArrayList(values));
            cmbModelo.getSelectionModel().clearSelection();
            cmbModelo.setPromptText("Modelo*");
        }
    }
    
    public void regresar(Usuario user, Event event) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("vPaginaPrincipal.fxml"));
        root = loader.load();
            
        vPaginaPrincipalController vPaginaPrincipalController = loader.getController();
        // vPaginaPrincipalController.actualizarVehiculo(); alguna función para 'recargar' los vehiculos
        vPaginaPrincipalController.home(user);
            
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root, 1280, 720);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();    
    
    }
    
    
}
