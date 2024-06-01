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
import javafx.stage.Stage;


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
    private ComboBox<?> cmbTipoVehiculo;
    @FXML
    private ComboBox<String> cmbMarca;
    @FXML
    private ComboBox<String> cmbModelo;
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
    private Parent root;
    private Stage stage;
    private Scene scene;

    
    private List<Vehiculo> vehiculos;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Map <String, ArrayListG4 <String> > marcaYModelo = generaMapa();
        Set<String> keys = marcaYModelo.keySet();
        ObservableList<String> keyList = FXCollections.observableArrayList(keys);
        cmbMarca.setItems(keyList);

        cmbMarca.setOnAction(event -> configuraComboBox (marcaYModelo));
    
        // El cuadro morado solo es para identificar el anchopane, cuando la parte del sistema
        // esté terminada y se puedan colocar los autos con sus fotos el fondo pasará a negro
    }    
    

    public void home (Usuario user) {
        textoSaludoUsuario.setText("Bienvenido " + user.getNombre());
        
        btnVenderVehiculo.setOnAction(event -> {
            try {
                mostrarSubirVehiculo(event, user); // deberia pasar user
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
    
    public void mostrarSubirVehiculo (Event event, Usuario user) throws IOException { // pasar argumentos !!        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("vSubirVehiculo.fxml"));
        root = loader.load();

        vSubirVehiculoController vSubirVehiculoController = loader.getController();
        vSubirVehiculoController.home(user); // pasar argumentos (user, this.controller) idk

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root, 1280, 720);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show(); 
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

