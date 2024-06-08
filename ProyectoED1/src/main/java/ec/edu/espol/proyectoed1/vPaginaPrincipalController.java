package ec.edu.espol.proyectoed1;

import ec.edu.espol.proyectoed1.TDAs.ArrayListG4;
import ec.edu.espol.proyectoed1.TDAs.CDLinkedList;
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
import ec.edu.espol.proyectoed1.classes.RegistroVehiculo;
import ec.edu.espol.proyectoed1.classes.Utilitaria;
import ec.edu.espol.proyectoed1.classes.Vehiculo;
import java.io.File;
import java.net.URL;
import java.util.Comparator;
import java.util.List;
import static java.util.Locale.filter;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Cursor;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;


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
    private ComboBox<String> cmbTipoVehiculo;
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
    private TextField tfKmDesde;
    @FXML
    private Button btnBuscar;
    @FXML
    private TextField tfYearDesde;
    @FXML
    private TextField tfYearHasta;
    @FXML
    private ComboBox<String> cmbOrdenar;
    @FXML
    private Button btnDefault;
    @FXML
    private Text tVerFavoritos;
    
    private Parent root;
    private Stage stage;
    private Scene scene;

    
    private int widthSpane = 604;
    private int heightSpane = 522;
    
    private int widthImg = 182;
    private int heightImg = 128;
   
    private int widthBtn = 128;
    private int heightBtn = 34;
    
    private Usuario usuario;
    
    @FXML
    private ScrollPane scrollPane;

    private String estilo2 = "-fx-background-color: transparent; -fx-text-fill: transparent;";
    
    private CDLinkedList <Vehiculo> CDLLVehiculos;
    
    private int yearDesde, yearHasta, kilometrajeDesde, kilometrajeHasta;
    private double precioDesde, precioHasta;
    private String modeloActual, marcaActual, tipoAutoActual;
    private ArrayListG4 <String> filtros = new ArrayListG4 <>();
    
    
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
        cmbOrdenar.getItems().addAll("Precio (Mayor-Menor) ", "Precio (Menor-Mayor) ", "Kilometraje (Mayor-Menor) ", "Kilometraje (Menor-Mayor) ");
        
    }    
    

    public void home (Usuario user, CDLinkedList <Vehiculo> listaVehiculos) {
        btnBuscar.setOnAction (event -> aplicarFiltro ());
        usuario = user;
        textoSaludoUsuario.setText("Bienvenido, " + usuario.getNombre());
        
        btnDefault.setOnAction(event -> setDefault ());
        
        btnVenderVehiculo.setOnAction(event -> {
            try {
                mostrarSubirVehiculo(event, user); // deberia pasar user
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        
        tVerFavoritos.setOnMouseClicked (event -> {
            try {
                favoritos (event, user);
            } catch (IOException ex) {
                ex.printStackTrace();
            }

        });
        
        vCerrarSesión.setOnAction(event -> {
            try {
                regresar(user,event); // deberia pasar user
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
      
        configurarTextField ();
        
        Map <String, ArrayListG4 <String> > marcaYModelo = generaMapa();
        Set<String> keys = marcaYModelo.keySet();
        ObservableList<String> keyList = FXCollections.observableArrayList(keys);
        cmbMarca.setItems(keyList);
        
        cmbMarca.setOnAction(event -> configuraComboBox (marcaYModelo));
        
        configuraComboBoxOrdenarPor();
        
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        
        CDLLVehiculos = listaVehiculos;
        if (CDLLVehiculos != null) {
            cargarVehiculos (CDLLVehiculos);
        }
        
        cmbOrdenar.setOnAction(event -> {
            String selectedOption = cmbOrdenar.getSelectionModel().getSelectedItem();
            applyMethod(selectedOption);
        });
        
    }
    
    private void setDefault () {
        CDLLVehiculos = Utilitaria.leerArchivoVehiculos("vehiculos");
        contenedorHbox.getChildren().clear();
        cargarVehiculos (CDLLVehiculos);
        
        configuraComboBoxOrdenarPor();
        configuraComboBoxMarcaModelo();

        tfPrecioDesde.setText("");
        tfPrecioHasta.setText("");
        tfYearDesde.setText( "");
        tfYearHasta.setText("");
        tfKmDesde.setText("");
        tfKmHasta.setText("");
    }
    
    private void configurarTextField () {
        tfYearDesde.setTextFormatter(createIntegerTextFormatter());
        tfYearHasta.setTextFormatter(createIntegerTextFormatter());
        tfPrecioDesde.setTextFormatter(createDoubleTextFormatter());
        tfPrecioHasta.setTextFormatter(createDoubleTextFormatter());
        tfKmDesde.setTextFormatter(createIntegerTextFormatter());
        tfKmHasta.setTextFormatter(createIntegerTextFormatter());

        tfPrecioDesde.setText(TextFieldValues.getPrecioDesdeValue() != null ? 
        String.valueOf(TextFieldValues.getPrecioDesdeValue()) : "");
        tfPrecioHasta.setText(TextFieldValues.getPrecioHastaValue() != null ? 
        String.valueOf(TextFieldValues.getPrecioHastaValue()) : "");
        tfYearDesde.setText(TextFieldValues.getYearDesdeValue() != null ? 
        String.valueOf(TextFieldValues.getYearDesdeValue()) : "");
        tfYearHasta.setText(TextFieldValues.getYearHastaValue() != null ? 
        String.valueOf(TextFieldValues.getYearHastaValue()) : "");
        tfKmDesde.setText(TextFieldValues.getKmDesdeValue() != null ? 
        String.valueOf(TextFieldValues.getKmDesdeValue()) : "");
        tfKmHasta.setText(TextFieldValues.getKmHastaValue() != null ? 
        String.valueOf(TextFieldValues.getKmHastaValue()) : "");
    }
    
    private TextFormatter<Double> createDoubleTextFormatter() {
        return new TextFormatter<>(
            new DoubleStringConverter(),
            null, // Valor inicial
            change -> {
                if (change.isAdded() && !change.getText().matches("\\d*\\.?\\d*")) {
                    return null;
                }
                return change;
            }
        );
    }
    
    private TextFormatter<Integer> createIntegerTextFormatter() {
        return new TextFormatter<>(
            new IntegerStringConverter(),
            null, // Valor inicial
            change -> {
                if (change.isAdded() && !change.getText().matches("\\d*")) {
                    return null;
                }
                return change;
            }
        );
    }
    
    private void cargarVehiculos(CDLinkedList <Vehiculo> CDLLVehiculos) {
        int cantidadVehiculos = CDLLVehiculos.size();
        int j = 0;
        
        if(cantidadVehiculos != 0){
            int cantFilas = (int) Math.ceil(cantidadVehiculos/3.0);
            int tope = 3;

            for(int i = 0; i < cantFilas; i++){
                HBox hbox = new HBox();
                
                while(j < cantidadVehiculos & j < tope){
                    Vehiculo vehiculo = CDLLVehiculos.get(j);
                    
                    StackPane spane = new StackPane();
                    spane.setAlignment(Pos.CENTER);
                    spane.setPrefSize(widthSpane, heightSpane);

                    ImageView visualizadorImg = new ImageView();
                    visualizadorImg.setFitHeight(heightImg);
                    visualizadorImg.setFitWidth(widthImg);

                    File imageFile = vehiculo.getCdLLImagenes().get(0);
                    Image image = new Image(imageFile.toURI().toString());
                    visualizadorImg.setImage(image);

                    Button btnSeleccionar = new Button("Seleccionar");
                    btnSeleccionar.setPrefSize(widthBtn, heightBtn);
                    btnSeleccionar.setStyle(estilo2);
                    btnSeleccionar.setCursor(Cursor.HAND);
                      
                    spane.getChildren().addAll(visualizadorImg, btnSeleccionar);

                    hbox.getChildren().addAll(spane);
                    
                    // Manejar el evento cuando el cursor entra del botón
                    visualizadorImg.setOnMouseEntered(event -> estiloHover(btnSeleccionar, visualizadorImg));
                    btnSeleccionar.setOnMouseEntered(event -> estiloHover(btnSeleccionar, visualizadorImg));

                    // Manejar el evento cuando el cursor sale del botón
                    visualizadorImg.setOnMouseExited(event -> estiloNotHover(btnSeleccionar, visualizadorImg));
                    btnSeleccionar.setOnMouseExited(event -> estiloNotHover(btnSeleccionar, visualizadorImg));
                    
                    btnSeleccionar.setOnAction(event -> {
                        try {
                            seleccionarVehiculo (vehiculo, event, CDLLVehiculos);
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    });
                    
                    j++;
                }
                
                hbox.setSpacing(0.5);
                tope += 3;
                contenedorHbox.getChildren().addAll(hbox);   
            
            }
            
        }
        contenedorHbox.setSpacing(20);
    }
    
    private void muestraAlerta (String titulo, String mssg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mssg);
        alert.showAndWait();
    }
    
    
    // por ahora solo marca
    private void aplicarFiltro () {
        Map <String, Object> m = leerFiltros ();
        
        if (filtros.isEmpty()) {
            muestraAlerta ("Error al aplicar filtros", "Por favor asegúrate de haber aplicado al menos 1 filtro");
            return;
        }
        
        CDLLVehiculos = Utilitaria.leerArchivoVehiculos("vehiculos");
        CDLinkedList <Vehiculo> vFiltrados = new CDLinkedList <Vehiculo>();
        
        Vehiculo v = CDLLVehiculos.get(0);
        
        List <Vehiculo> vehiculosFiltradosTemp = FiltradoVehiculos.filtrarVehiculos(CDLLVehiculos, filtros, m);
        
        for (Vehiculo vvv: vehiculosFiltradosTemp) {
            vFiltrados.add(vvv);
        }
          
        contenedorHbox.getChildren().clear();
        CDLLVehiculos = vFiltrados;
        configuraComboBoxOrdenarPor();
        cargarVehiculos (vFiltrados);
    }
    
    private Map <String, Object> leerFiltros () {
        
        Map<String, Object> valoresFiltros = new HashMap <> ();
        
        filtros.clear();
        
        if (!tfKmDesde.getText().equals("")) {
            kilometrajeDesde = Integer.parseInt(tfKmDesde.getText());
            filtros.add ("kmDesde");
            
            valoresFiltros.put("kmDesde", kilometrajeDesde);
        }
        
        if (!tfKmHasta.getText().equals("")) {
            kilometrajeHasta = Integer.parseInt(tfKmHasta.getText());
            filtros.add ("kmHasta");
            valoresFiltros.put("kmHasta", kilometrajeHasta);
        }
        
        if (!tfYearDesde.getText().equals("")) {
            yearDesde = Integer.parseInt(tfYearDesde.getText());
            filtros.add ("yearDesde");
            valoresFiltros.put("yearDesde", yearDesde);
        }
        
        if (!tfYearHasta.getText().equals("")) {
            yearHasta = Integer.parseInt(tfYearHasta.getText());
            filtros.add ("yearHasta");
            valoresFiltros.put("yearHasta", yearHasta);
        }
        
        if (!tfPrecioDesde.getText().equals("")) {
            precioDesde = Double.parseDouble(tfPrecioDesde.getText());
            filtros.add ("precioDesde");
            valoresFiltros.put("precioDesde", precioDesde);
        }
        
        if (!tfPrecioHasta.getText().equals("")) {
            precioHasta = Double.parseDouble(tfPrecioHasta.getText());
            filtros.add ("precioHasta");
            valoresFiltros.put("precioHasta", precioHasta);
        }
        
        if (cmbTipoVehiculo.getValue() != null) {
            filtros.add("tipoVehiculo");
            valoresFiltros.put("tipoVehiculo", cmbTipoVehiculo.getValue());
        }
        
        if (cmbMarca.getValue() != null) {
            filtros.add("marca");
            valoresFiltros.put("marca", cmbMarca.getValue());
        }
        
        if (cmbModelo.getValue() != null) {
            filtros.add("modelo");
            valoresFiltros.put("modelo", cmbModelo.getValue());
        }
        
        return valoresFiltros;
    }
    
    private void configuraComboBoxOrdenarPor () {
        cmbOrdenar.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {
            @Override
            public ListCell<String> call(ListView<String> param) {
                return new ListCell<String>() {
                    @Override
                    protected void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty || item == null) {
                            setText(null);
                        } else {
                            setText(item);
                        }
                    }
                };
            }
        });

        cmbOrdenar.setButtonCell(new ListCell<String>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText("Ordenar por");
                } else {
                    setText(item);
                }
            }
        });
        
        cmbOrdenar.getSelectionModel().select("Ordenar por");
    }
    
    private void configuraComboBoxMarcaModelo () {
        cmbMarca.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {
            @Override
            public ListCell<String> call(ListView<String> param) {
                return new ListCell<String>() {
                    @Override
                    protected void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty || item == null) {
                            setText(null);
                        } else {
                            setText(item);
                        }
                    }
                };
            }
        });

        cmbMarca.setButtonCell(new ListCell<String>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText("Marca");
                } else {
                    setText(item);
                }
            }
        });
        
        
        cmbModelo.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {
            @Override
            public ListCell<String> call(ListView<String> param) {
                return new ListCell<String>() {
                    @Override
                    protected void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty || item == null) {
                            setText(null);
                        } else {
                            setText(item);
                        }
                    }
                };
            }
        });

        cmbModelo.setButtonCell(new ListCell<String>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText("Modelo");
                } else {
                    setText(item);
                }
            }
        });
        
        cmbMarca.getSelectionModel().select("Marca");
        cmbModelo.getSelectionModel().select("Modelo");
        cmbModelo.setItems(null);
    }
    
    private void seleccionarVehiculo (Vehiculo vehiculo, Event event, CDLinkedList<Vehiculo> CDLLVehiculos) throws IOException {
        if (!tfPrecioDesde.getText().equals("")) TextFieldValues.setPrecioDesdeValue(Double.valueOf(tfPrecioDesde.getText()));
        if (!tfPrecioHasta.getText().equals("")) TextFieldValues.setPrecioHastaValue(Double.valueOf(tfPrecioHasta.getText()));
        if (!tfYearDesde.getText().equals("")) TextFieldValues.setYearDesdeValue(Integer.valueOf(tfYearDesde.getText()));
        if (!tfYearHasta.getText().equals("")) TextFieldValues.setYearHastaValue(Integer.valueOf(tfYearHasta.getText()));
        if (!tfKmDesde.getText().equals("")) TextFieldValues.setKmDesdeValue(Integer.valueOf(tfKmDesde.getText()));
        if (!tfKmHasta.getText().equals("")) TextFieldValues.setKmHastaValue(Integer.valueOf(tfKmHasta.getText()));

        FXMLLoader loader = new FXMLLoader(getClass().getResource("vVisualizacion.fxml"));
        root = loader.load();
            
        vVisualizacionController vVisualizadorController = loader.getController();
        vVisualizadorController.home(vehiculo, usuario, CDLLVehiculos);
            
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root, 1280, 720);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();  
    }
    
    private void estiloHover(Button btnAnadirCod, ImageView img){
        btnAnadirCod.setStyle("-fx-background-color: #980B20; -fx-background-radius: 10; -fx-text-fill: white; cursor: hand;");
        img.setOpacity(0.5);
        btnAnadirCod.setFont(new Font("Verdana", 14));
    }
    
    private void estiloNotHover(Button btnAnadirCod, ImageView img){
        btnAnadirCod.setStyle("-fx-background-color: transparent; -fx-text-fill: transparent;");
        img.setOpacity(1.0);
    }
    
    private void configuraComboBox (Map <String, ArrayListG4 <String> > marcaYModelo){
        String selectedKey = cmbMarca.getValue();
        
        if (selectedKey != null) {
            ArrayListG4 <String> values = marcaYModelo.get(selectedKey);
            cmbModelo.setItems(FXCollections.observableArrayList(values));
            cmbModelo.getSelectionModel().clearSelection();
            cmbModelo.setPromptText("Modelo");
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

    public void regresar(Usuario user, Event event) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("vInicioSesion.fxml"));
        root = loader.load();
            
        vInicioSesionController vInicioSesionController = loader.getController();
        vInicioSesionController.home();
            
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root, 800, 600);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
        
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX((primaryScreenBounds.getWidth() - stage.getWidth()) / 2);
        stage.setY((primaryScreenBounds.getHeight() - stage.getHeight()) / 2);
        stage.show();
    }
    
    public void favoritos (Event event, Usuario user) throws IOException {
        Vehiculo primerVehiculo = user.getVehiculosAgregadosAFavoritos().get(0);
        
        if (primerVehiculo == null) {
            muestraAlerta("Error al cargar favoritos", "Al parecer no tienes ningún vehículo agregado a favoritos.\nAgrega uno para poder acceder a esta pestaña.");
            return;
        }
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("vFavoritos.fxml"));
        root = loader.load();

        vFavoritosController vFavoritosController = loader.getController();
        vFavoritosController.home(primerVehiculo, user); // pasar argumentos (user, this.controller) idk

        stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root, 1280, 720);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show(); 
    }

    public void filtrarPorX(Comparator cmp) {
        CDLLVehiculos = Vehiculo.getSortedList(CDLLVehiculos, cmp);
        System.out.println("bine");
        contenedorHbox.getChildren().clear();
        System.out.println("bine0");
        cargarVehiculos(CDLLVehiculos);
        System.out.println("bie");
    }

    @Override
    public void filtrarPorY(List<Object> objetos) {
        
        // los objetos los obtendre mediante lo que el usuario haya elegido
          
    }

    private void applyMethod(String selectedOption) {
         switch (selectedOption) {
            case "Precio (Mayor-Menor) ":
                System.out.println("bien precio");
                filtrarPorX(Vehiculo.getCmpXprecioMayMen());
                System.out.println("bien precio 222");
                break;
            case "Precio (Menor-Mayor) ":
                filtrarPorX(Vehiculo.getCmpXprecioMenMay());
                break;
            case "Kilometraje (Mayor-Menor) ":
                filtrarPorX(Vehiculo.getCmpXkilometrajeMayMen());
                break;
            case "Kilometraje (Menor-Mayor) ":
                filtrarPorX(Vehiculo.getCmpXkilometrajeMenMay());
                break;
            default:
                // Acción para cualquier otra opción (si la hay)
                System.out.println("Se seleccionó una opción desconocida");
                break;
        }
    }
}