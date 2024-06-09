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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextFormatter;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;

public class vVisualizacionMisVehiculosController {
    
    private Parent root;
    private Stage stage;
    private Scene scene;
    
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
    @FXML
    private Button btnImgMovIzq;
    private File imgActual;
    private CDLinkedList<Vehiculo> cdlVehiculos;
    @FXML
    private Text tIndexImg;
    
    private int nImages;
    
    private CDLinkedList <Vehiculo> vFavoritos;
    @FXML
    private Button btnEditar;
    @FXML
    private Button btnGuardar;
    @FXML
    private TextField tfMarca;
    @FXML
    private TextField tfModelo;
    @FXML
    private TextField tfAnio;
    @FXML
    private TextField tfKm;
    @FXML
    private TextField tfPotencia;
    @FXML
    private TextField tfPrecio;
    @FXML
    private ComboBox<String> cmbTransmision;
    @FXML
    private ComboBox<String> cmbCombustible;
    @FXML
    private ComboBox<String> cmbUbicacion;
    @FXML
    private ComboBox<String> cmbTipo;
    @FXML
    private Button btnRemover;
    
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
    
    
    
    
    public void home(Vehiculo v, Usuario user){   
        usuario = user;
        vehiculo = v;
        imgsVehiculos = vehiculo.getCdLLImagenes();
        cdlVehiculos = user.getVehiculosPropios();
        
        
        configurarComboBoxTransmision ();
        configurarComboBoxTipo(); 
        configurarComboBoxCombustible ();
         configurarComboBoxUbicacion ();
        
        tfMarca.setEditable(false);
        tfModelo.setEditable(false);
        tfAnio.setEditable(false);
        tfKm.setEditable(false);
        tfPotencia.setEditable(false);
        cmbTransmision.setDisable(true);
        cmbCombustible.setDisable(true);
        cmbUbicacion.setDisable(true);
        cmbTipo.setDisable(true);
        tfPrecio.setEditable(false);
        
        btnEditar.setOnMouseClicked (event -> permitirEditar());
        
        imgActual = imgsVehiculos.get(0);
        nImages = imgsVehiculos.size();
        cambiarImg (imgActual);
        
        btnImgMovIzq.setOnAction (event -> avanzarImgIzq ());
        btnImgMovDer.setOnAction (event -> avanzarImgDer ());
        btOutMoverIzquierda.setOnAction (event -> avanzarVehiculoIzq ());
        btOutMoverDerecha.setOnAction (event -> avanzarVehiculoDer ());
               
        
        btRegresar.setOnAction(event -> {
            try {
                regresar(event);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        
        // Configurar el TextFormatter para aceptar solo valores numéricos
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
        
//        tfPotencia.setTextFormatter(textFormatterPotencia);
        
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
        
        tfPrecio.setTextFormatter(textFormatterPrecio);
        tfKm.setTextFormatter(textFormatterKilometraje);
        llenarDatosVehiculo();
    }
    
    private void llenarDatosVehiculo () {
        tfMarca.setText(vehiculo.getRegistro().getMarca());
        tfModelo.setText(vehiculo.getRegistro().getModelo());
        tfAnio.setText(String.valueOf(vehiculo.getRegistro().getAño()));
        tfKm.setText(String.valueOf(vehiculo.getKilometraje()));
        tfPotencia.setText(String.valueOf(vehiculo.getMotor().getPotencia()));
        cmbTransmision.setPromptText(vehiculo.getTransmision().getTipo());
        cmbTipo.setPromptText(vehiculo.getRegistro().getTipo());
        cmbCombustible.setPromptText(vehiculo.getMotor().getTipo());
        cmbUbicacion.setPromptText(vehiculo.getUbicacion());
        tfPrecio.setText(String.valueOf(vehiculo.getPrecio()));
    }
         
    private void permitirEditar(){
        tfMarca.setEditable(true);
        tfModelo.setEditable(true);
        tfAnio.setEditable(true);
        tfKm.setEditable(true);
        tfPotencia.setEditable(true);
        cmbTransmision.setDisable(false);
        cmbCombustible.setDisable(false);
        cmbUbicacion.setDisable(false);
        cmbTipo.setDisable(false);
        tfPrecio.setEditable(true);
    
    }
    
    private void avanzarVehiculoIzq () {
        vehiculo = cdlVehiculos.getPrev(vehiculo);
        imgsVehiculos = vehiculo.getCdLLImagenes();
        imgActual = imgsVehiculos.get(0);
        nImages = imgsVehiculos.size();
        llenarDatosVehiculo();
        cambiarImg(imgActual);
    }        

    private void avanzarVehiculoDer () {
        vehiculo = cdlVehiculos.getNext(vehiculo);
        imgsVehiculos = vehiculo.getCdLLImagenes();
        nImages = imgsVehiculos.size();
        imgActual = imgsVehiculos.get(0);
        llenarDatosVehiculo();
        cambiarImg(imgActual);
    } 
    
    private void avanzarImgIzq () {   
        imgActual = imgsVehiculos.getPrev(imgActual);
        cambiarImg (imgActual);
    }
    
    private void avanzarImgDer () {
        imgActual = imgsVehiculos.getNext(imgActual);
        cambiarImg (imgActual);
    }
    
    private void cambiarImg (File img) {
        visualizadorImgs.setX(0);
        visualizadorImgs.setY(60);
        
        File imageFile = img;
        Image image = new Image(imageFile.toURI().toString());
        visualizadorImgs.setImage(image);
        
        int posImg = imgsVehiculos.indexOf(img) + 1;
        tIndexImg.setText(posImg + "/" + nImages);
    }
    
    private void ventanaIniciarSesion(MouseEvent event) {
        try{
            App.setRoot("vInicioSesion");
        } catch(IOException e){
        }
    }
    
    
    private void configurarComboBoxTransmision () {
        ArrayListG4 <String> transmisionTipo = new ArrayListG4<String>();
        transmisionTipo.add("Automática");
        transmisionTipo.add("Manual");
        transmisionTipo.add("Semi-Automática");
        
        ObservableList<String> transmisiones = FXCollections.observableArrayList(transmisionTipo);
        cmbTransmision.setItems(transmisiones);
    }
    
    private void configurarComboBoxCombustible () {
        ArrayListG4 <String> CombustibleTipo = new ArrayListG4<String>();
        CombustibleTipo.add("Gasolina");
        CombustibleTipo.add("Diesel");
        CombustibleTipo.add("Electricidad");
        CombustibleTipo.add("Gas");
        
        ObservableList<String> combustibles = FXCollections.observableArrayList(CombustibleTipo);
        cmbCombustible.setItems(combustibles);
    }
    
     private void configurarComboBoxTipo () {
        ArrayListG4 <String> TipoVehiculos = new ArrayListG4<String>();
        TipoVehiculos.add("Limusina");
        TipoVehiculos.add("SUV");
        TipoVehiculos.add("Coupé");
        TipoVehiculos.add("HatchBack");
        TipoVehiculos.add("Camioneta");
        TipoVehiculos.add("Sedán");
        TipoVehiculos.add("4x4");
        TipoVehiculos.add("Electricos");
        
        ObservableList<String> tipos = FXCollections.observableArrayList(TipoVehiculos);
        cmbTipo.setItems(tipos);
    }
     
       private void configurarComboBoxUbicacion () {
        ArrayListG4 <String> ciudades = new ArrayListG4<String>();
        ciudades.add("Guayaquil");
        ciudades.add("Manta");
        ciudades.add("Quito");
        ciudades.add("Cuenca");
        
        ObservableList<String> ciudadesEcuador = FXCollections.observableArrayList(ciudades);
        cmbUbicacion.setItems(ciudadesEcuador);
    }
    
    
    public void regresar(Event event) throws IOException{        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("vPaginaPrincipal.fxml"));
        root = loader.load();
            
        vPaginaPrincipalController vPaginaPrincipalController = loader.getController();
        // vPaginaPrincipalController.actualizarVehiculo(); alguna función para 'recargar' los vehiculos
        vPaginaPrincipalController.home(usuario, cdlVehiculos);
            
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root, 1280, 720);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();    
    
    }
}