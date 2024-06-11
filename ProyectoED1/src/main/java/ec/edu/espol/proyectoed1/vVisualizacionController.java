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
import ec.edu.espol.proyectoed1.classes.Utilitaria;
import ec.edu.espol.proyectoed1.classes.Vehiculo;
import java.io.File;
import javafx.event.Event;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextFormatter;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;

public class vVisualizacionController {
    
    private Parent root;
    private Stage stage;
    private Scene scene;
    
    @FXML
    private Button bAccidentes;
    @FXML
    private Button btOutMoverIzquierda;
    @FXML
    private Button btOutMoverDerecha;
    @FXML
    private Button btRegresar; 
    @FXML
    private ImageView visualizadorImgs;
    @FXML
    private ImageView imgCorazon;
    
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
    @FXML
    private Text tIndexImg;
    
    private int nImages;
    private Image corazonRojo = new Image("file:src/main/resources/ec/edu/espol/proyectoed1/heart-solid-red.png");
    private Image corazonNegro = new Image("file:src/main/resources/ec/edu/espol/proyectoed1/heart-solid.png");
    
    private CDLinkedList <Vehiculo> vFavoritos;
    @FXML
    private Text tTipo;
    
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
        vFavoritos = usuario.getVehiculosAgregadosAFavoritos();
        
        imgCorazon.setOnMouseClicked (event -> removerAnadirFav ());
        
        imgActual = imgsVehiculos.get(0);
        nImages = imgsVehiculos.size();
        cambiarImg (imgActual);
        llenarDatosVehiculo();
        
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
        
         bAccidentes.setOnAction(event -> {
            try {
                verAccidentesyReparaciones(event, usuario);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        
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
        tTipo.setText(vehiculo.getRegistro().getTipo());
        tMotor.setText(vehiculo.getMotor().getTipo());
        tPotencia.setText(String.valueOf(vehiculo.getMotor().getPotencia()));
        tTransmicion.setText(vehiculo.getTransmision().getTipo());
        tCombustible.setText(vehiculo.getMotor().getTipo());
        tUbicacion.setText(vehiculo.getUbicacion());
        tPrecio.setText(String.valueOf(vehiculo.getPrecio()));
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
        setCorazonFav();
        visualizadorImgs.setX(0);
        visualizadorImgs.setY(60);
        
        File imageFile = img;
        Image image = new Image(imageFile.toURI().toString());
        visualizadorImgs.setImage(image);
        
        int posImg = imgsVehiculos.indexOf(img) + 1;
        tIndexImg.setText(posImg + "/" + nImages);
    }
    
    private void setCorazonFav () {
        if (estaEnFav()) {
            imgCorazon.setImage(corazonRojo);
        } else {
            imgCorazon.setImage(corazonNegro);
        }
    }
    
    private void removerAnadirFav () {
        if (estaEnFav()) {
            boolean x = vFavoritos.remove(vehiculo);
            System.out.println(x);
            
        } else {
            vFavoritos.add(vehiculo);
        }
        
        usuario.setVehiculosAgregadosAFavoritos(vFavoritos);
        
        Sistema.actualizarUsuario_Archivo(usuario);
        setCorazonFav ();
    }
    
    private boolean estaEnFav () {
        for (Vehiculo v : vFavoritos) {
            System.out.println("Favorito: " + v.getRegistro().getPlaca());
        }
        
        System.out.println("Vehiculo a verificar: " + vehiculo.getRegistro().getPlaca());
        return vFavoritos.contains(vehiculo);
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
    
    public void verAccidentesyReparaciones (Event event, Usuario user) throws IOException {
                 
        FXMLLoader loader = new FXMLLoader(getClass().getResource("vAccidentesyReparacionesOUT.fxml"));
        root = loader.load();

        vAccidentesYReparacionesOUTController vAccidentesyReparacionesController = loader.getController();
        if(vehiculo.getRegistro().getAccidentes()== null) System.out.println("el nullll?????");
        vAccidentesyReparacionesController.home(this.vehiculo, user, vehiculo.getRegistro().getAccidentes(), vehiculo.getRegistro().getReparaciones()); // pasar argumentos (user, this.controller) idk

        stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root, 1280, 720);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show(); 
    }
}