package ec.edu.espol.proyectoed1;

import ec.edu.espol.proyectoed1.TDAs.CDLinkedList;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import ec.edu.espol.proyectoed1.classes.Persona;
import ec.edu.espol.proyectoed1.classes.Usuario;
import ec.edu.espol.proyectoed1.classes.Vehiculo;
import java.io.File;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextFormatter;
import javafx.scene.image.Image;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;

public class vVisualizacionController {
    
    private Parent root;
    private Stage stage;
    private Scene scene;
    @FXML
    private Button bAccidentes;
    private TextField fcPrecio;
    @FXML
    private Text tGuardar;
    @FXML
    private Button btOutMoverIzquierda;
    @FXML
    private Button btOutMoverDerecha;
    @FXML
    private Button btRegresar; 
    @FXML
    private ImageView visualizadorImgs;

    private Usuario usuario;
    private Vehiculo vehiculo;
    CDLinkedList <File> imgsVehiculos;
    @FXML
    private Button btnImgMovDer;
    @FXML
    private Text tMarca;
    @FXML
    private Text tModelo;
    @FXML
    private Text tYear;
    @FXML
    private Text tKilometraje;
    @FXML
    private Text tPrecio;
    @FXML
    private Text tUbicacion;
    @FXML
    private Text tCombustible;
    @FXML
    private Text tTransmicion;
    @FXML
    private Text tPotencia;
    @FXML
    private Text tMotor;
    @FXML
    private Button btnImgMovIzq;
    private File imgActual;
    private CDLinkedList<Vehiculo> cdlVehiculos;
    
    private void initialize() {
    }
    
    private void switchToPrimary() throws IOException {
        App.setRoot("vPaginaPrincipal");
    }

    private void muestraAlerta (String titulo, String mssg) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mssg);
        alert.showAndWait();
    }
    
    
    public void home(Vehiculo v, Usuario user, CDLinkedList<Vehiculo> listaVehiculos){
        usuario = user;
        vehiculo = v;
        imgsVehiculos = vehiculo.getCdLLImagenes();
        cdlVehiculos = listaVehiculos;
        llenarDatosVehiculo();
        
        imgActual = imgsVehiculos.get(0);
        cambiarImg (imgActual);
        
        btnImgMovIzq.setOnAction (event -> avanzarImgIzq ());
        btnImgMovDer.setOnAction (event -> avanzarImgDer ());
        btOutMoverIzquierda.setOnAction (event -> avanzarVehiculoIzq ());
        btOutMoverDerecha.setOnAction (event -> avanzarVehiculoDer ());
                
        
        // Configurar el TextFormatter para aceptar solo valores numéricos
        TextFormatter<Integer> textFormatter = new TextFormatter<Integer> (
            new IntegerStringConverter(),
            null, // Valor inicial
            change -> {
                if (change.isAdded() && !change.getText().matches("\\d*\\.?\\d*")) {
                    return null;
                }
                return change;
            }
        );
        
    }
    
    private void llenarDatosVehiculo () {
        tMarca.setText(vehiculo.getRegistro().getMarca());
        tModelo.setText(vehiculo.getRegistro().getModelo());
        tYear.setText(String.valueOf(vehiculo.getRegistro().getAño()));
        tKilometraje.setText(String.valueOf(vehiculo.getKilometraje()));
        tMotor.setText(vehiculo.getMotor().getTipo());
        tPotencia.setText(String.valueOf(vehiculo.getMotor().getPotencia()));
        tTransmicion.setText(vehiculo.getTransmision().getTipo());
        tCombustible.setText(vehiculo.getMotor().getTipo());
        tUbicacion.setText(vehiculo.getUbicacion());
        tPrecio.setText(String.valueOf(vehiculo.getPrecio()));
    }
    
    private void avanzarVehiculoIzq () {
        int posActual = cdlVehiculos.indexOf(vehiculo);
        
        if (posActual == 0) {
            int size = cdlVehiculos.size();
            vehiculo = cdlVehiculos.get(size - 1);
        } else {
            posActual -= 1;
            vehiculo = cdlVehiculos.get(posActual);
        }
        
        imgActual = vehiculo.getCdLLImagenes().get(0);
        cambiarImg (imgActual);
        llenarDatosVehiculo();
    }        

    private void avanzarVehiculoDer () {
        int posActual = cdlVehiculos.indexOf(vehiculo);
        int size = cdlVehiculos.size();
        
        if (posActual == size - 1) {
            vehiculo = cdlVehiculos.get(0);
        } else {
            posActual += 1;
            vehiculo = cdlVehiculos.get(posActual);
        }
        
        imgActual = vehiculo.getCdLLImagenes().get(0);
        cambiarImg(imgActual);
        llenarDatosVehiculo();
    } 
    
    private void avanzarImgIzq () {        
        int posActual = imgsVehiculos.indexOf(imgActual);
        
        if (posActual == 0) {
            int size = imgsVehiculos.size();
            imgActual = imgsVehiculos.get(size - 1);
        } else {
            imgActual = imgsVehiculos.get(posActual--);
        }

        cambiarImg(imgActual);
    }
    
    private void avanzarImgDer () {
        int posActual = imgsVehiculos.indexOf(imgActual);
        int size = imgsVehiculos.size();
        
        if (posActual == size - 1) {
            imgActual = imgsVehiculos.get(0);
        } else {
            posActual += 1;
            imgActual = imgsVehiculos.get(posActual);
        }
        
        cambiarImg(imgActual);
    }
    
    private void cambiarImg (File img) {
        visualizadorImgs.setX(0);
        visualizadorImgs.setY(60);
        
        File imageFile = img;
        Image image = new Image(imageFile.toURI().toString());
        visualizadorImgs.setImage(image);
    }
    
    private void ventanaIniciarSesion(MouseEvent event) {
        try{
            App.setRoot("vInicioSesion");
        } catch(IOException e){
        }
    }
    
    @FXML
    private void verAccidentes(MouseEvent event) throws IOException {
    }
}