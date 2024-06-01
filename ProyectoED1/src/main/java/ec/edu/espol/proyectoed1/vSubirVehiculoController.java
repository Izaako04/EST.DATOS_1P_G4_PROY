/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.proyectoed1;

import ec.edu.espol.proyectoed1.TDAs.ArrayListG4;
import ec.edu.espol.proyectoed1.TDAs.CDLinkedList;
import ec.edu.espol.proyectoed1.classes.Accidente;
import ec.edu.espol.proyectoed1.classes.Motor;
import ec.edu.espol.proyectoed1.classes.RegistroVehiculo;
import ec.edu.espol.proyectoed1.classes.Reparacion;
import ec.edu.espol.proyectoed1.classes.Transmision;
import ec.edu.espol.proyectoed1.classes.Usuario;
import ec.edu.espol.proyectoed1.classes.Vehiculo;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
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
    private CheckBox cbTieneAccidente;
    @FXML
    private DatePicker dpFechaAccidente;
    private TextField fcUbicacionAccidente;
    @FXML
    private TextArea taDescripcionAccidente;
    @FXML
    private CheckBox cbTieneReparacion;
    @FXML
    private DatePicker dpFechaReparacion;
    @FXML
    private TextArea taDescripcionReparacion;
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
    private CDLinkedList <File> imagenes = new CDLinkedList <File> ();
    
    private String ubicacionAccidente;
    private String descripcionAccidente;
    private String descripcionReparacion;
    private LocalDate fechaReparacion;
    private LocalDate fechaAccidente;

    @FXML
    private AnchorPane anchorPaneImgPortada;
    @FXML
    private ComboBox<String> cmbUbicacionVehiculo;
    @FXML
    private ComboBox<String> cmbUbicacionAccidente;
    @FXML
    private TextField fcCombustible;
    @FXML
    private TextField fcPotencia;
    
    
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
        
        TextFormatter<Integer> textFormatterPotencia = new TextFormatter<Integer> (
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
        fcPotencia.setTextFormatter(textFormatterPotencia);
        
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

        TextFormatter<Double> textFormatterKilometraje = new TextFormatter<Double> (
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
        fcKilometraje.setTextFormatter(textFormatterKilometraje);
        
        configurarComboBoxTransmision ();
        configurarComboBoxUbicacion ();
        
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
        
        configBtnImagenes ();
      
        Map <String, ArrayListG4 <String> > marcaYModelo = generaMapa();
        Set<String> keys = marcaYModelo.keySet();
        ObservableList<String> keyList = FXCollections.observableArrayList(keys);
        cmbMarca.setItems(keyList);
        cmbMarca.setOnAction(event -> configuraComboBoxMarcaModelo (marcaYModelo));
               
        
        // resolver bug: Cuando el usuario seleeciona una fecha para el accidente y luego decide borrarla por el teclado
        // se le permite subir su vehículo sin haber registrado una fecha para el accidente
    }
    
    private void configBtnImagenes () {
        btnExteriorFrontal.setOnAction(event -> {
            try {
                seleccionarImg(0);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        
        btnExteriorLateral.setOnAction(event -> {
            try {
                seleccionarImg(1);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        
        btnExteriorTrasero.setOnAction(event -> {
            try {
                seleccionarImg(1);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        
        btnInterior.setOnAction(event -> {
            try {
                seleccionarImg(1);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
    }
   
    
    private void subirVehiculo (Usuario user, Event event) {
        // comprobación campos vacíos
        boolean camposVacios = (fcKilometraje.getText().equals("") || fcPrecio.getText().equals("") || fcYear.getText().equals("")  || fcPotencia.getText().equals("") || fcCombustible.getText() == null || fcPlaca.getText() == null|| cmbUbicacionVehiculo.getValue() == null || cmbMarca.getValue() == null || cmbModelo.getValue() == null || cmbTransmision.getValue() == null);

        if (camposVacios) { // campos vacíos
            muestraAlerta ("Error al cargar tu vehículo", "Por favor asegúrate de haber llenado todos los campos obligatorios*");
        }

        // campos obligatorios
        Double precio = Double.parseDouble(this.fcPrecio.getText()); ;
        Double kilometraje = Double.parseDouble(this.fcKilometraje.getText());
        Integer year = Integer.parseInt(this.fcYear.getText());
        String combustible = this.fcCombustible.getText();
        Integer potencia = Integer.parseInt(this.fcPotencia.getText());
        String placa = this.fcPlaca.getText();
        String ubicacion = this.cmbUbicacionVehiculo.getValue();
        String marca = this.cmbMarca.getValue();
        String modelo = this.cmbModelo.getValue();
        String transmision = this.cmbTransmision.getValue();
        
        // campos opcionales
        boolean checkAccidente = checkAccidenteCamposVacios(); // devuelve true si el checkbox está activo y no ha llenado los campos
        boolean checkReparacion = checkReparacionCamposVacios();
        boolean imgPortadaEmpty = imagenes.get(0) == null; 

        if (!camposVacios && ( checkAccidente || checkReparacion) ) { // campos vacíos
            muestraAlerta ("Error al cargar tu vehículo", "Por favor asegúrate de haber llenado todos los campos obligatorios*");
        } else if (imgPortadaEmpty) { // no hay portada
            muestraAlerta ("Error al cargar tu vehículo", "Por favor asegúrate de haber cargado al menos la imágen 'Exterior Frontal'");
        } else { // crear vehiculo!
            Accidente accidente = null;
            Reparacion reparacion = null;
            
            // Caso 1: Hay accidente
            if (cbTieneAccidente.isSelected()) {
                String ubicacionAccidente = this.cmbUbicacionAccidente.getValue();
                accidente = new Accidente (fechaAccidente.toString(), descripcionAccidente, ubicacionAccidente);
            }
            
            // Caso 2: Hay reparación
            if (cbTieneReparacion.isSelected()) {
                reparacion = new Reparacion (fechaAccidente.toString(), descripcionReparacion);
            }
            
            // Se crea objeto registro
            RegistroVehiculo nuevoRegistro = new RegistroVehiculo (placa, user, year, marca, modelo, reparacion, accidente);
            
            // Se crea objeto Transmision
            Transmision transmisionObj = new Transmision (transmision);
            
            // Se crea objeto Motor
            Motor motorObj = new Motor (combustible, potencia);
            
            Vehiculo nuevoVehiculo = new Vehiculo (nuevoRegistro, motorObj, transmisionObj, precio, kilometraje, ubicacion, imagenes);
        }
    }
    
    public void seleccionarImg (int tipo) throws IOException {
        FileChooser fileChooser = new FileChooser();

        // filtro imágenes
        FileChooser.ExtensionFilter imageFilter = new FileChooser.ExtensionFilter("Archivos de Imagen", "*.png", "*.jpg", "*.jpeg", "*.gif");
        fileChooser.getExtensionFilters().add(imageFilter);

        File selectedFile = fileChooser.showOpenDialog(new Stage());

        if (selectedFile != null) {
            // Copiar la imagen seleccionada a carpeta de imágenes
            File targetFolder = new File("imgsVechiculos");
            if (!targetFolder.exists()) {
                targetFolder.mkdirs(); // Crea la carpeta si no existe
            }

            File targetFile = new File(targetFolder, selectedFile.getName());
            Files.copy(selectedFile.toPath(), targetFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

            if (tipo == 0) { // 0 -> portada/imagen principal
                imagenes.add(0, targetFile);
                Image image = new Image(targetFile.toURI().toString());
                
                portadaVehiculo.setLayoutX(8 );
                portadaVehiculo.setLayoutY(170);
                
                portadaVehiculo.setImage(image);

            } else {
                imagenes.add(targetFile);

            }
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
            descripcionAccidente = taDescripcionAccidente.getText();
            fechaAccidente = dpFechaAccidente.getValue();
            ubicacionAccidente = cmbUbicacionAccidente.getValue();
            
            camposVacios = (cmbUbicacionAccidente.getValue() == null || descripcionAccidente.isBlank() || fechaAccidente == null);
            
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
    
    private void configurarComboBoxUbicacion () {
        ArrayListG4 <String> ciudades = new ArrayListG4<String>();
        ciudades.add("Guayaquil");
        ciudades.add("Manta");
        ciudades.add("Quito");
        ciudades.add("Cuenca");
        
        ObservableList<String> ciudadesEcuador = FXCollections.observableArrayList(ciudades);
        cmbUbicacionAccidente.setItems(ciudadesEcuador);
        cmbUbicacionVehiculo.setItems(ciudadesEcuador);
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
