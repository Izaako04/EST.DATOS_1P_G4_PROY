package ec.edu.espol.proyectoed1;

import ec.edu.espol.proyectoed1.TDAs.ArrayListG4;
import ec.edu.espol.proyectoed1.TDAs.CDLinkedList;
import ec.edu.espol.proyectoed1.classes.Motor;
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
import ec.edu.espol.proyectoed1.classes.RegistroVehiculo;
import ec.edu.espol.proyectoed1.classes.Transmision;
import ec.edu.espol.proyectoed1.classes.Usuario;
import ec.edu.espol.proyectoed1.classes.Utilitaria;
import ec.edu.espol.proyectoed1.classes.Vehiculo;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.HashSet;
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
import javafx.scene.control.TextFormatter;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
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
    
    private CDLinkedList <Vehiculo> vPropios;
    @FXML
    private Button btnEditar;
    @FXML
    private Button btnGuardar;
    private TextField tfMarca;
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
    @FXML
    private ComboBox<String> cmbMarca;
    @FXML
    private ComboBox<String> cmbModelo;
    @FXML
    private Button btnAcsyReps;
    @FXML
    private Button btnSubirImg;
    @FXML
    private TextField tfPlaca;
    @FXML
    private AnchorPane anchorPane;
    
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
    
    
    public void home(Vehiculo v, Usuario user){   
        usuario = user;
        vehiculo = v;
        imgsVehiculos = vehiculo.getCdLLImagenes();
        cdlVehiculos = user.getVehiculosPropios();
        
        
        configurarComboBoxTransmision ();
        configurarComboBoxTipo(); 
        configurarComboBoxCombustible ();
         configurarComboBoxUbicacion ();
         
         Map <String, ArrayListG4 <String> > marcaYModelo = generaMapa();
        Set<String> keys = marcaYModelo.keySet();
        ObservableList<String> keyList = FXCollections.observableArrayList(keys);
        cmbMarca.setItems(keyList);
        cmbMarca.setOnAction(event -> configuraComboBoxMarcaModelo (marcaYModelo));
        
        cmbMarca.setDisable(true);
        cmbModelo.setDisable(true);
        tfAnio.setEditable(false);
        tfPlaca.setEditable(false);
        tfKm.setEditable(false);
        tfPotencia.setEditable(false);
        cmbTransmision.setDisable(true);
        cmbCombustible.setDisable(true);
        cmbUbicacion.setDisable(true);
        cmbTipo.setDisable(true);
        tfPrecio.setEditable(false);
        btnGuardar.setDisable(true);
        
        btnEditar.setOnMouseClicked (event -> {permitirEditar(); btnEditar.setDisable(true);});
        
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
        
        btnAcsyReps.setOnAction(event -> {
            try {
                verAccidentesyReparaciones(event, usuario);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        
        btnRemover.setOnAction(event -> {
            Boolean alertado =  alertar("Alerta!","Estas apunto de borrar tu Vehiculo","¿Estas seguro?");
            if(alertado == true) removerVehiculoPropio (event);
            //else muestraAlerta("Aviso","Carro no borrado");
        });
        
        btnGuardar.setOnAction(event -> {
            Boolean alertado = alertar("Alerta!","Estas apunto de Actualizar este Vehiculo","Revisa que todos los campos esten bien llenados \n antes de hacerlo!");
            if(alertado == true){ 
                try { 
                    editarAuto(event);
                    btnGuardar.setDisable(true);
                    btnEditar.setDisable(false);
                    cmbMarca.setDisable(true);
                    cmbModelo.setDisable(true);
                    tfPlaca.setEditable(false);
                    tfAnio.setEditable(false);
                    tfKm.setEditable(false);
                    tfPotencia.setEditable(false);
                    cmbTransmision.setDisable(true);
                    cmbCombustible.setDisable(true);
                    cmbUbicacion.setDisable(true);
                    cmbTipo.setDisable(true);
                    tfPrecio.setEditable(false);
                    btnGuardar.setDisable(true);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                imgsVehiculos = vehiculo.getCdLLImagenes();
                cdlVehiculos = user.getVehiculosPropios();
            }
            
        });
        
        btnSubirImg.setOnAction(event -> {
            try {
                seleccionarImg ();
                avanzarVehiculoIzq ();
                avanzarVehiculoDer ();
                muestraAlerta("Imagen agregada","La imagen ha sido agregada a tu vehiculo.");
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
        tfPotencia.setTextFormatter(textFormatterPotencia);
        llenarDatosVehiculo();
    }
    
    private void llenarDatosVehiculo () {
        cmbMarca.setValue(vehiculo.getRegistro().getMarca());
        cmbModelo.setValue(vehiculo.getRegistro().getModelo());
        tfPlaca.setText(String.valueOf(vehiculo.getRegistro().getPlaca()));
        tfAnio.setText(String.valueOf(vehiculo.getRegistro().getAño()));
        tfKm.setText(String.valueOf(vehiculo.getKilometraje()));
        tfPotencia.setText(String.valueOf(vehiculo.getMotor().getPotencia()));
        cmbTransmision.setValue(vehiculo.getTransmision().getTipo());
        cmbTipo.setValue(vehiculo.getRegistro().getTipo());
        cmbCombustible.setValue(vehiculo.getMotor().getTipo());
        cmbUbicacion.setValue(vehiculo.getUbicacion());
        tfPrecio.setText(String.valueOf(vehiculo.getPrecio()));
    }
         
    private void permitirEditar(){
        cmbMarca.setDisable(false);
        cmbModelo.setDisable(false);
        tfAnio.setEditable(true);
        tfKm.setEditable(true);
        tfPotencia.setEditable(true);
        cmbTransmision.setDisable(false);
        cmbCombustible.setDisable(false);
        cmbUbicacion.setDisable(false);
        cmbTipo.setDisable(false);
        tfPrecio.setEditable(true);
        btnGuardar.setDisable(false);
    
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
    
     public void verAccidentesyReparaciones (Event event, Usuario user) throws IOException {
                 
        FXMLLoader loader = new FXMLLoader(getClass().getResource("vAccidentesyReparaciones.fxml"));
        root = loader.load();

        vAccidentesYReparacionesController vAccidentesyReparacionesController = loader.getController();
        vAccidentesyReparacionesController.home(this.vehiculo, user, this.cdlVehiculos); // pasar argumentos (user, this.controller) idk

        stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root, 1280, 720);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show(); 
    }
    
    private void removerVehiculoPropio (Event event) {
        if (estaEnPropios()) {
            vehiculo =  cdlVehiculos.getNext(vehiculo);
            imgsVehiculos = vehiculo.getCdLLImagenes();
            nImages = imgsVehiculos.size();
            imgActual = imgsVehiculos.get(0);
            llenarDatosVehiculo();
            
            Vehiculo vehiculoRemover =  cdlVehiculos.getPrev(vehiculo);
             cdlVehiculos.remove(vehiculoRemover);
             vehiculoRemover.eliminarVehiculo_ARCHIVO(Vehiculo.cmpXmarca);
             
            cambiarImg(imgActual);
            
            if ( cdlVehiculos.isEmpty()) {
                muestraAlerta("Saliendo de Mis Vehiculos", "Ya no tienes ningún Vehiculo. Regresando a la página principal.");
                usuario.setVehiculosPropios( cdlVehiculos);
                Sistema.actualizarUsuario_Archivo(usuario);
                
                try {
                    regresar(event);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }

        } else {
             cdlVehiculos.add(vehiculo);
        }
        
        usuario.setVehiculosPropios( cdlVehiculos);
        Sistema.actualizarUsuario_Archivo(usuario);
    }
    
    private boolean estaEnPropios () {
        for (Vehiculo v : cdlVehiculos) {
            System.out.println("Vehiculo propio: " + v);
        }
        
        System.out.println("Vehiculo a verificar: " + vehiculo);
        return cdlVehiculos.contains(vehiculo);
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
       
        private void configuraComboBoxMarcaModelo (Map <String, ArrayListG4 <String> > marcaYModelo){
        String selectedKey = cmbMarca.getValue();
        if (selectedKey != null) {
            ArrayListG4 <String> values = marcaYModelo.get(selectedKey);
            cmbModelo.setItems(FXCollections.observableArrayList(values));
            cmbModelo.getSelectionModel().clearSelection();
            cmbModelo.setPromptText("Modelo*");
        }
    }
    
        public void seleccionarImg () throws IOException {
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
        this.vehiculo.setNuevaImagen(targetFile);
        Sistema.agregarVehiculo_Archivo(this.vehiculo);
        CDLinkedList <Vehiculo> listaVehiculosUsuarios = usuario.getVehiculosPropios();
        listaVehiculosUsuarios.add(this.vehiculo);
        usuario.setVehiculosPropios(listaVehiculosUsuarios);
        Sistema.actualizarUsuario_Archivo(usuario);
        Vehiculo vehiculoRemover =  cdlVehiculos.getPrev(vehiculo);       
        cdlVehiculos.remove(vehiculoRemover);
        vehiculoRemover.eliminarVehiculo_ARCHIVO(Vehiculo.cmpXmarca);
        
        }
    }
   public void editarAuto(Event event) throws IOException{
       boolean camposVacios = (tfKm.getText().equals("") || tfPrecio.getText().equals("") || tfAnio.getText().equals("")  || tfPotencia.getText().equals("") || cmbCombustible.getValue() == null || tfPlaca.getText() == null|| cmbUbicacion.getValue() == null || cmbMarca.getValue() == null || cmbModelo.getValue() == null || cmbTransmision.getValue() == null || cmbTipo.getValue() == null);
        if (camposVacios) { // campos vacíos
            muestraAlerta ("Error al cargar tu vehículo", "Por favor asegúrate de haber llenado todos los campos obligatorios*");
            return;
        } 
        
       Double precio = Double.parseDouble(this.tfPrecio.getText()); ;
        Double kilometraje = Double.parseDouble(this.tfKm.getText());
        Integer year = Integer.parseInt(this.tfAnio.getText());
        String combustible = this.cmbCombustible.getValue();
        Integer potencia = Integer.parseInt(this.tfPotencia.getText());;
        String ubicacion = this.cmbUbicacion.getValue();
        String placa = this.tfPlaca.getText();
        String marca = this.cmbMarca.getValue();
        String modelo = this.cmbModelo.getValue();
        String tipo = this.cmbTipo.getValue();
        String transmision = this.cmbTransmision.getValue();
        
        this.vehiculo.setKilometraje(kilometraje);
        this.vehiculo.setPrecio(precio);
        Transmision nTransmision = new Transmision(transmision);
        this.vehiculo.setTransmision(nTransmision);
        this.vehiculo.setUbicacion(ubicacion);
        this.vehiculo.getRegistro().setAño(year);
        this.vehiculo.getRegistro().setMarca(marca);
        this.vehiculo.getRegistro().setModelo(modelo);
        this.vehiculo.getRegistro().setPlaca(placa);
        this.vehiculo.getRegistro().setTipo(tipo);
        this.vehiculo.getMotor().setPotencia(potencia);
        this.vehiculo.getMotor().setTipo(combustible);
        

        
        Sistema.agregarVehiculo_Archivo(this.vehiculo);
        CDLinkedList <Vehiculo> listaVehiculosUsuarios = usuario.getVehiculosPropios();
        listaVehiculosUsuarios.add(this.vehiculo);
        usuario.setVehiculosPropios(listaVehiculosUsuarios);
        Sistema.actualizarUsuario_Archivo(usuario);
        muestraAlerta("Cambios Guardados", "¡Los datos de tu Vehiculo se han actualizado!");
        Vehiculo vehiculoRemover =  cdlVehiculos.getPrev(vehiculo);       
        cdlVehiculos.remove(vehiculoRemover);
        vehiculoRemover.eliminarVehiculo_ARCHIVO(Vehiculo.cmpXmarca);
   
   }
    
    public void regresar(Event event) throws IOException{        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("vPaginaPrincipal.fxml"));
        root = loader.load();
            
        vPaginaPrincipalController vPaginaPrincipalController = loader.getController();
        // vPaginaPrincipalController.actualizarVehiculo(); alguna función para 'recargar' los vehiculos
        CDLinkedList<Vehiculo> vehiculos = Utilitaria.leerArchivoVehiculos("vehiculos");
        vPaginaPrincipalController.home(usuario, vehiculos);
            
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root, 1280, 720);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();    
    
    }
}