/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.proyectoed1;

import ec.edu.espol.proyectoed1.TDAs.ArrayListG4;
import ec.edu.espol.proyectoed1.classes.Usuario;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;

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
    private ComboBox<String> cmbTransmision;
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
    
    private String ubicacionAccidente;
    private String descripcionAccidente;
    private String descripcionReparacion;
    private LocalDate fechaReparacion;
    private LocalDate fechaAccidente;
    
    
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
        // Configurar el TextFormatter para aceptar solo valores numéricos
        TextFormatter<Integer> textFormatterYear = new TextFormatter<Integer> (
            new IntegerStringConverter(),
            null, // Valor inicial
            change -> {
                if (change.isAdded() && !change.getText().matches("\\d*\\.?\\d*")) {
                    return null;
                }
                return change;
            }
        );

        fcYear.setTextFormatter(textFormatterYear);
        
        TextFormatter<Double> textFormatterPrecio = new TextFormatter<Double> (
            new DoubleStringConverter(),
            null, // Valor inicial
            change -> {
                if (change.isAdded() && !change.getText().matches("\\d*\\.?\\d*")) {
                    return null;
                }
                return change;
            }
        );

        fcPrecio.setTextFormatter(textFormatterPrecio);
        
        configurarComboBoxTransmision ();
        
        btnRegresar.setOnAction(event -> {
            try {
                regresar(user, event);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        
        btnGuardar.setOnAction(event -> {
            subirVehiculo (user, event);
        });

        
        Map <String, ArrayListG4 <String> > marcaYModelo = generaMapa();
        Set<String> keys = marcaYModelo.keySet();
        ObservableList<String> keyList = FXCollections.observableArrayList(keys);
        cmbMarca.setItems(keyList);
        cmbMarca.setOnAction(event -> configuraComboBoxMarcaModelo (marcaYModelo));
               
        
        // resolver bug: Cuando el usuario seleeciona una fecha para el accidente y luego decide borrarla por el teclado
        // se le permite subir su vehículo sin haber registrado una fecha para el accidente
    }
 
    private void subirVehiculo (Usuario user, Event event) {
        // campos obligatorios
        String precio = this.fcPrecio.getText();
        String kilometraje = this.fcKilometraje.getText();
        String year = this.fcYear.getText();
        String motor = this.fcMotor.getText();
        String placa = this.fcPlaca.getText();
        String ubicacion = this.fcUbicacion.getText();
        String marca = this.cmbMarca.getValue();
        String modelo = this.cmbModelo.getValue();
        String transmision = this.cmbTransmision.getValue();
        
        // campos opcionales
        boolean checkAccidente = checkAccidenteCamposVacios(); // devuelve true si el checkbox está activo y no ha llenado los campos
        boolean checkReparacion = checkReparacionCamposVacios();
        
        // comprobación de campos vacíos
        boolean camposVacios = (precio.isBlank() || kilometraje.isBlank() || year.isBlank() || motor.isBlank() || placa.isBlank() || ubicacion.isBlank() || marca == null || modelo == null || transmision == null);
        
        if (camposVacios || checkAccidente || checkReparacion) {
            muestraAlerta ("Error al cargar tu vehículo", "Por favor asegúrate de haber llenado todos los campos obligatorios*");
        } else {
            
        }
    }
    
    private boolean checkReparacionCamposVacios () {
        boolean camposVacios = false;
        
        if (cbTieneReparacion.isSelected()) {
            descripcionReparacion = taDescripcionReparacion.getText();
            fechaReparacion = dpFechaReparacion.getValue();
            
            camposVacios = (descripcionReparacion.isBlank() || fechaReparacion == null);
            
        } else {
            camposVacios = false;
        }
        return camposVacios;
    }
    
    private boolean checkAccidenteCamposVacios () {
        boolean camposVacios = false;
        
        if (cbTieneAccidente.isSelected()) {
            ubicacionAccidente = fcUbicacionAccidente.getText();
            descripcionAccidente = taDescripcionAccidente.getText();
            fechaAccidente = dpFechaAccidente.getValue();
            
            camposVacios = (ubicacionAccidente.isBlank() || descripcionAccidente.isBlank() || fechaAccidente == null);
            
        } else {
            camposVacios = false;
        }
        return camposVacios;
    }
    
    private void muestraAlerta (String titulo, String mssg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mssg);
        alert.showAndWait();
    }
    
    private void configurarComboBoxTransmision () {
        ArrayListG4 <String> transmisionTipo = new ArrayListG4<String>();
        transmisionTipo.add("Automática");
        transmisionTipo.add("Manual");
        transmisionTipo.add("Semi-Automática");
        
        ObservableList<String> transmisiones = FXCollections.observableArrayList(transmisionTipo);
        cmbTransmision.setItems(transmisiones);
    }
    
    private void configuraComboBoxMarcaModelo (Map <String, ArrayListG4 <String> > marcaYModelo){
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
