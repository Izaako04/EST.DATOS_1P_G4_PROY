package ec.edu.espol.proyectoed1;

import ec.edu.espol.proyectoed1.TDAs.ArrayListG4;
import ec.edu.espol.proyectoed1.TDAs.CDLinkedList;
import ec.edu.espol.proyectoed1.classes.Accidente;
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
import ec.edu.espol.proyectoed1.classes.Reparacion;
import ec.edu.espol.proyectoed1.classes.Usuario;
import ec.edu.espol.proyectoed1.classes.Utilitaria;
import ec.edu.espol.proyectoed1.classes.Vehiculo;
import java.io.File;
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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextFormatter;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;

public class vAccidentesYReparacionesOUTController {
    
    private Parent root;
    private Stage stage;
    private Scene scene;
    
    private Button btOutMoverIzquierda;
    private Button btOutMoverDerecha;
    @FXML
    private Button btRegresar; 
    private ImageView visualizadorImgs;
    
    private Usuario usuario;
    private Vehiculo vehiculo;
    CDLinkedList <File> imgsVehiculos;
    private Button btnImgMovDer;
    private Text tMarca;
    private Text tModelo;
    private Text tYear;
    private Text tKilometraje;
    private Text tPrecio;
    private Text tUbicacion;
    private Text tCombustible;
    private Text tTransmicion;
    private Text tPotencia;
    private Text tMotor;
    private Button btnImgMovIzq;
    private File imgActual;
    private CDLinkedList<Vehiculo> cdlVehiculos;
    private Text tIndexImg;
    
    private int nImages;
    
    private CDLinkedList <Vehiculo> vPropios;
    private Button btnEditar;
    private Button btnGuardar;
    private TextField tfMarca;
    private TextField tfModelo;
    private TextField tfAnio;
    private TextField tfKm;
    private TextField tfPotencia;
    private TextField tfPrecio;
    private ComboBox<String> cmbTransmision;
    private ComboBox<String> cmbCombustible;
    private ComboBox<String> cmbUbicacion;
    private ComboBox<String> cmbTipo;
    private Button btnRemover;
    private ComboBox<String> cmbMarca;
    private ComboBox<String> cmbModelo;
    @FXML
    private Button btnAcMovDer;
    @FXML
    private Button btnAcMovIzq;
    @FXML
    private Button btnRpMovDer;
    @FXML
    private Button btnRpMovIzq;
    @FXML
    private Text descripcionAccidente;
    @FXML
    private Text fechaAccidente;
    @FXML
    private Text ubicacionAccidente;
    @FXML
    private Text descripcionReparacion;
    @FXML
    private Text fechaReparacion;
    
    private CDLinkedList<Accidente> accidentes;
    
    private CDLinkedList<Reparacion> reparaciones;
    
    private Accidente accidenteActual ;
    
    private Reparacion reparacionActual ;
    
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
    
    
    public void home(Vehiculo v, Usuario user, CDLinkedList<Accidente> accidentes, CDLinkedList<Reparacion> reparaciones){ 
        this.vehiculo = v;
        this.usuario = user;
        this.accidentes = accidentes;
        this.reparaciones = reparaciones;

        if(accidentes!= null && v.getRegistro().getAccidentes().get(0) != null ){
            accidenteActual =  v.getRegistro().getAccidentes().get(0);
            actualizarAccidente();
        }
        else descripcionAccidente.setText("el usuario no ha registrado accidente todavía");

        
        if(reparaciones!=null && v.getRegistro().getReparaciones().get(0) != null ){
            reparacionActual = v.getRegistro().getReparaciones().get(0);
            actualizarReparacion();
        }
        else descripcionReparacion.setText("el usuario no ha registrado reparacion todavía");

        btRegresar.setOnAction(event -> {
            try {        
                regresar(event);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        
        btnAcMovDer.setOnAction(event ->{btnAcMovDerMethod(accidenteActual);} );
        
        btnAcMovIzq.setOnAction(event -> {btnAcMovIzqMethod(accidenteActual);} );
        
        btnRpMovDer.setOnAction(event ->{btnRpMovDerMethod(reparacionActual);} );
        
        btnRpMovIzq.setOnAction(event ->{btnRpMovIzqMethod(reparacionActual);} );

        
        
    }
    
    private void llenarDatosVehiculo () {
    }
         
    private void permitirEditar(){
    }

    private void ventanaIniciarSesion(MouseEvent event) {
        try{
            App.setRoot("vInicioSesion");
        } catch(IOException e){
        }
    }
    
    
    private boolean estaEnPropios () {
        for (Vehiculo v : cdlVehiculos) {
            System.out.println("Vehiculo propio: " + v);
        }
        
        System.out.println("Vehiculo a verificar: " + vehiculo);
        return cdlVehiculos.contains(vehiculo);
    }
    
    
    public void regresar(Event event) throws IOException{        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("vVisualizacion.fxml"));
        root = loader.load();
        
        vVisualizacionController vVisualizacionController = loader.getController();
        CDLinkedList<Vehiculo> vs = Utilitaria.leerArchivoVehiculos("vehiculos");
        vVisualizacionController.home(this.vehiculo, usuario,vs);
            
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root, 1280, 720);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();    
    
    }
//    public void agregarImgs(Event event)
    
    private void btnAcMovIzqMethod(Accidente accidente){
        accidenteActual = accidentes.getNext(accidente);
        actualizarAccidente();
        System.out.println("god");

    }
    
    private void btnAcMovDerMethod(Accidente accidente){
        accidenteActual = accidentes.getPrev(accidente);
        actualizarAccidente();

    }
    
    private void btnRpMovIzqMethod(Reparacion reparacion){
        reparacionActual = reparaciones.getNext(reparacion);
        actualizarAccidente();
        System.out.println("god");

    }
    
    private void btnRpMovDerMethod(Reparacion reparacion){
        reparacionActual = reparaciones.getPrev(reparacion);
        actualizarAccidente();

    }
    
    private void actualizarAccidente(){
        String feAccidente=accidenteActual.getFecha();
        String ubAccidente= accidenteActual.getUbicacion();
        String desAccidente= accidenteActual.getDescripcion();
        
        fechaAccidente.setText(feAccidente);
        ubicacionAccidente.setText(ubAccidente);
        descripcionAccidente.setText(desAccidente);
    }
    
    private void actualizarReparacion(){
        
        String feReparacion=reparacionActual.getFecha();
        String desReparacion=reparacionActual.getDescripcion();
        
        fechaReparacion.setText(feReparacion);
        descripcionReparacion.setText(desReparacion);
        
    }
}