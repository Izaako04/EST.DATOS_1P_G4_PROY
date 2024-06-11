package ec.edu.espol.proyectoed1;

import ec.edu.espol.proyectoed1.TDAs.ArrayListG4;
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
import ec.edu.espol.proyectoed1.classes.Utilitaria;
import ec.edu.espol.proyectoed1.classes.Vehiculo;
import java.io.File;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextFormatter;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;

public class vAccidentesYReparacionesOUTController {
    
    private Parent root;
    private Stage stage;
    private Scene scene;
    
    @FXML
    private Button btRegresar; 
    private CDLinkedList<Vehiculo > cdlVehiculos;
    private Usuario usuario;
    private Vehiculo vehiculo;
    @FXML
    private TextArea taAccidentes;
    @FXML
    private TextArea taReparaciones;
    @FXML
    private Text txUbicacionAccidente;
    @FXML
    private DatePicker dpFechaAccidente;
    @FXML
    private DatePicker dpFechaReparacion;
    
    
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
    
    private boolean alertar() {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Alerta!");
        alert.setHeaderText("Estas apunto de borrar tu Vehiculo");
        alert.setContentText("¿Estas seguro?");

        ButtonType btnContinue = new ButtonType("Continuar");
        ButtonType btnCancel = new ButtonType("Cancelar");
        alert.getButtonTypes().setAll(btnContinue, btnCancel);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == btnContinue) {
            return true;
        } else {
            return false;
        }
    }
    
    
    public void home(Vehiculo v, Usuario user,CDLinkedList<Vehiculo> vehiculos){ 
        this.vehiculo = v;
        this.usuario = user;
        cdlVehiculos = vehiculos;
        
        txUbicacionAccidente.setFont(Font.font("verdana", 20));
        dpFechaAccidente.setEditable(false);
        dpFechaReparacion.setEditable(false);
        taAccidentes.setEditable(false);
        taReparaciones.setEditable(false);
        llenarDatosVehiculo();
        btRegresar.setOnAction(event -> {
            try {        
                regresar(event);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
    }
    
    private void ventanaIniciarSesion(MouseEvent event) {
        try{
            App.setRoot("vInicioSesion");
        } catch(IOException e){
        }
    }
    
    private void llenarDatosVehiculo() {
        taAccidentes.setFont(Font.font("verdana", 20));
        taReparaciones.setFont(Font.font("verdana", 20));
        if(this.vehiculo.getRegistro().getAccidentes() == null){
             muestraAlerta ("Alerta Accidentes", "Este Vehiculo no cuenta con Accidentes!");
             taAccidentes.setPromptText("Este vehiculo no cuenta con Accidentes. \n  \n Agrega la información de los accidentes de este vehiculo.");
             dpFechaAccidente.setPromptText("Fecha:");
             txUbicacionAccidente.setText("Ubicacion");
        }else{ 
        taAccidentes.setText(this.vehiculo.getRegistro().getAccidentes().getDescripcion());
        LocalDate fechaAccionesD = LocalDate.parse(this.vehiculo.getRegistro().getAccidentes().getFecha());
        txUbicacionAccidente.setText(this.vehiculo.getRegistro().getAccidentes().getUbicacion());
        dpFechaAccidente.setValue(fechaAccionesD);
        }
        if(this.vehiculo.getRegistro().getReparaciones() == null){
            muestraAlerta ("Alerta Reparaciones", "Este Vehiculo no cuenta con Reparaciones!");
             taReparaciones.setPromptText("Este vehiculo no cuenta con Reparaciones.                                                     \n Agrega la información de las Reparaciones de este vehiculo.");
             dpFechaReparacion.setPromptText("Fecha:");
        }else{ 
        taReparaciones.setText(this.vehiculo.getRegistro().getReparaciones().getDescripcion());
        LocalDate fechaReparacionesD = LocalDate.parse(this.vehiculo.getRegistro().getReparaciones().getFecha());
        dpFechaReparacion.setValue(fechaReparacionesD);
        
        }
    }
    
    public void regresar(Event event) throws IOException{        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("vVisualizacion.fxml"));
        root = loader.load();
        
        vVisualizacionController vVisualizacionController = loader.getController();
        vVisualizacionController.home(this.vehiculo, usuario,cdlVehiculos);
            
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root, 1280, 720);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();    
    
    }
}