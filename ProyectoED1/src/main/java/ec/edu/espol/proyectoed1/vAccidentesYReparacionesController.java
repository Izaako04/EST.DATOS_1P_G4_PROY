package ec.edu.espol.proyectoed1;

import ec.edu.espol.proyectoed1.TDAs.ArrayListG4;
import ec.edu.espol.proyectoed1.TDAs.CDLinkedList;
import ec.edu.espol.proyectoed1.classes.Accidente;
import ec.edu.espol.proyectoed1.classes.Reparacion;
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

public class vAccidentesYReparacionesController {
    
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
    private CDLinkedList<Vehiculo> cdlVehiculos;
    private String ubicacionAccidente;
    private String descripcionAccidente;
    private String descripcionReparacion;
    private LocalDate fechaReparacion;
    private LocalDate fechaAccidente;
    
    private CDLinkedList <Vehiculo> vPropios;
    @FXML
    private TextArea taAccidentes;
    @FXML
    private TextArea taReparaciones;
    @FXML
    private Button btnAGuardar;
    @FXML
    private Button btnREditar;
    @FXML
    private Button btnRGuardar;
    @FXML
    private Button btnAEditar;
    @FXML
    private DatePicker dpFechaAccidente;
    @FXML
    private ComboBox<String> cmbUbicacionAccidente;
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
    
private boolean alertar(String tittle, String Header, String cuerpo) {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle(tittle);
        alert.setHeaderText(Header);
        alert.setContentText(cuerpo);

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
        this.cdlVehiculos = vehiculos;
        
        configurarComboBoxUbicacion ();
        llenarDatosVehiculo ();
        taAccidentes.setEditable(false);
        btnAGuardar.setDisable(true);
        dpFechaAccidente.setDisable(true);
        cmbUbicacionAccidente.setDisable(true);
       
        taReparaciones.setEditable(false);
        btnRGuardar.setDisable(true);
        dpFechaReparacion.setDisable(true);
        
        btRegresar.setOnAction(event -> {
            try {        
                regresar(event);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        
         btnAEditar.setOnMouseClicked (event -> {btnAEditar.setDisable(true); permitirEditarA();});
         btnREditar.setOnMouseClicked (event -> {btnREditar.setDisable(true); permitirEditarR();});
         
          btnAGuardar.setOnAction(event -> {
            Boolean alertado = alertar("Alerta!","Estas apunto de Actualizar este Vehiculo","Revisa que todos los campos esten bien llenados \n antes de hacerlo!");
            Boolean estado = checkAccidenteCamposVacios();
            if(alertado == true && estado!=true){ 
                try { 
                    editarAutoAccidente(event);
                    muestraAlerta ("Datos Actualizados", "Los datos de Accidente(s) han sido actualizados!");
                    btnAEditar.setDisable(false);
                    btnAGuardar.setDisable(true);
                } catch (IOException ex) {
                    ex.printStackTrace();
                    }
                }
          });
           btnRGuardar.setOnAction(event -> {
            Boolean alertado = alertar("Alerta!","Estas apunto de Actualizar este Vehiculo","Revisa que todos los campos esten bien llenados \n antes de hacerlo!");
            Boolean estado = checkReparacionCamposVacios();
            if(alertado == true && estado!=true){ 
                try { 
                    editarAutoReparacion(event);
                     muestraAlerta ("Datos Actualizados", "Los datos de Reparaciones(s) han sido actualizados!");
                     btnREditar.setDisable(false);
                     btnRGuardar.setDisable(true);
                } catch (IOException ex) {
                    ex.printStackTrace();
                    }
                }
          });
    }
    
    private void llenarDatosVehiculo() {
        taAccidentes.setFont(Font.font("verdana", 20));
        taReparaciones.setFont(Font.font("verdana", 20));
        if(this.vehiculo.getRegistro().getAccidentes() == null){
             muestraAlerta ("Alerta Accidentes", "Este Vehiculo no cuenta con Accidentes!");
             taAccidentes.setPromptText("Este vehiculo no cuenta con Accidentes. \n  \n Agrega la información de los accidentes de este vehiculo.");
             dpFechaAccidente.setPromptText("Fecha:");
             cmbUbicacionAccidente.setPromptText("Ubicacion");
        }else{ 
        taAccidentes.setText(this.vehiculo.getRegistro().getAccidentes().getDescripcion());
        LocalDate fechaAccionesD = LocalDate.parse(this.vehiculo.getRegistro().getAccidentes().getFecha());
        cmbUbicacionAccidente.setValue(this.vehiculo.getRegistro().getAccidentes().getUbicacion());
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
         
    private void permitirEditarA(){
        taAccidentes.setEditable(true);
        btnAGuardar.setDisable(false);
        dpFechaAccidente.setDisable(false);
        cmbUbicacionAccidente.setDisable(false);
    }
    
     private void permitirEditarR(){
        taReparaciones.setEditable(true);
        btnRGuardar.setDisable(false);
        dpFechaReparacion.setDisable(false);

    }
    
    private boolean checkAccidenteCamposVacios () {
        boolean camposVacios = false;
        descripcionAccidente = taAccidentes.getText();
        fechaAccidente = dpFechaAccidente.getValue();
        ubicacionAccidente = cmbUbicacionAccidente.getValue();
        return camposVacios = (cmbUbicacionAccidente.getValue() == null || descripcionAccidente.isBlank() || fechaAccidente == null);
    }
    
    private boolean checkReparacionCamposVacios () {
        boolean camposVacios = false;
        descripcionReparacion = taReparaciones.getText();
        fechaReparacion = dpFechaReparacion.getValue();
        camposVacios = (descripcionReparacion.isBlank() || fechaReparacion == null);
        return camposVacios;
    }
     
     
    private void editarAutoAccidente(Event event)  throws IOException{  
        descripcionAccidente = taAccidentes.getText();
        fechaAccidente = dpFechaAccidente.getValue();
        ubicacionAccidente = cmbUbicacionAccidente.getValue();
        
        Accidente accidentes= new Accidente (fechaAccidente.toString(), descripcionAccidente, ubicacionAccidente);
        this.vehiculo.getRegistro().setAccidentes(accidentes);
        Sistema.agregarVehiculo_Archivo(this.vehiculo);
        CDLinkedList <Vehiculo> listaVehiculosUsuarios = usuario.getVehiculosPropios();
        listaVehiculosUsuarios.add(this.vehiculo);
        usuario.setVehiculosPropios(listaVehiculosUsuarios);
        Sistema.actualizarUsuario_Archivo(usuario);
        Vehiculo vehiculoRemover =  cdlVehiculos.getPrev(this.vehiculo);       
        cdlVehiculos.remove(vehiculoRemover);
        vehiculoRemover.eliminarVehiculo_ARCHIVO(Vehiculo.cmpXmarca);
    }
    private void editarAutoReparacion(Event event)  throws IOException{
        descripcionReparacion = taReparaciones.getText();
        fechaReparacion = dpFechaReparacion.getValue();
        
        Reparacion reparaciones= new Reparacion (fechaReparacion.toString(), descripcionReparacion);
        this.vehiculo.getRegistro().setReparaciones(reparaciones);
        Sistema.agregarVehiculo_Archivo(this.vehiculo);
        CDLinkedList <Vehiculo> listaVehiculosUsuarios = usuario.getVehiculosPropios();
        listaVehiculosUsuarios.add(this.vehiculo);
        usuario.setVehiculosPropios(listaVehiculosUsuarios);
        Sistema.actualizarUsuario_Archivo(usuario);
        Vehiculo vehiculoRemover =  cdlVehiculos.getPrev(vehiculo);       
        cdlVehiculos.remove(vehiculoRemover);
        vehiculoRemover.eliminarVehiculo_ARCHIVO(Vehiculo.cmpXmarca);
    }
    
    private void configurarComboBoxUbicacion () {
        ArrayListG4 <String> ciudades = new ArrayListG4<String>();
        ciudades.add("Guayaquil");
        ciudades.add("Manta");
        ciudades.add("Quito");
        ciudades.add("Cuenca");
        
        ObservableList<String> ciudadesEcuador = FXCollections.observableArrayList(ciudades);
        cmbUbicacionAccidente.setItems(ciudadesEcuador);
    }
    

    private void ventanaIniciarSesion(MouseEvent event) {
        try{
            App.setRoot("vInicioSesion");
        } catch(IOException e){
        }
    }
    
    
    public void regresar(Event event) throws IOException{        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("vVisualizacionMisVehiculos.fxml"));
        root = loader.load();
            
        vVisualizacionMisVehiculosController vVisualizacionMisVehiculosController = loader.getController();
        vVisualizacionMisVehiculosController.home(this.vehiculo, usuario);
            
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root, 1280, 720);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();    
    
    }
    
}