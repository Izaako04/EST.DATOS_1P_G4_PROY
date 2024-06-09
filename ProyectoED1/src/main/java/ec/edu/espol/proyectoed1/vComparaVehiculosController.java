/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.proyectoed1;

import ec.edu.espol.proyectoed1.classes.Vehiculo;
import ec.edu.espol.proyectoed1.TDAs.ArrayListG4;
import ec.edu.espol.proyectoed1.TDAs.CDLinkedList;
import ec.edu.espol.proyectoed1.classes.Motor;
import ec.edu.espol.proyectoed1.classes.RegistroVehiculo;
import ec.edu.espol.proyectoed1.classes.Transmision;
import ec.edu.espol.proyectoed1.classes.Usuario;
import ec.edu.espol.proyectoed1.classes.Utilitaria;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Isaías
 */

public class vComparaVehiculosController implements Initializable {

    @FXML
    private Text tMarca2;
    @FXML
    private Text tPrecio2;
    @FXML
    private Text tTransmision2;
    @FXML
    private Text tCombustible2;
    @FXML
    private Text tUbicacion2;
    @FXML
    private Text tAccidentes2;
    @FXML
    private Text tReparaciones2;
    @FXML
    private Text tMotor2;
    @FXML
    private ImageView img2;
    @FXML
    private Button btnRegresar;
    @FXML
    private ImageView img1;
    @FXML
    private Text tMarca1;
    @FXML
    private Text tKilometraje1;
    @FXML
    private Text tPrecio1;
    @FXML
    private Text tPotencia1;
    @FXML
    private Text tTransmision1;
    @FXML
    private Text tCombustible1;
    @FXML
    private Text tUbicacion1;
    @FXML
    private Text tAccidentes1;
    @FXML
    private Text tReparaciones1;
    @FXML
    private Text tMotor1;

    private Vehiculo vehiculo1;
    private Vehiculo vehiculo2;
    @FXML
    private Text tKilometraje2;
    @FXML
    private Text tYear2;
    @FXML
    private Text tYear1;
    @FXML
    private Text tPotencia2;
    
    private Parent root;
    private Stage stage;
    private Scene scene;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void home (ArrayListG4 <Vehiculo> vehiculos, Usuario usuario) {
        btnRegresar.setOnAction(event -> {
            try {
                regresar (usuario, event);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        
        vehiculo1 = vehiculos.get(0);
        vehiculo2 = vehiculos.get(1);
        
        cargarInformacion (vehiculo1, vehiculo2);
    }
    
    private void cargarInformacion (Vehiculo v1, Vehiculo v2) {
        tKilometraje1.setText(String.valueOf(v1.getKilometraje()));
        tKilometraje2.setText(String.valueOf(v2.getKilometraje()));
        
        tPrecio1.setText(String.valueOf(v1.getPrecio()));
        tPrecio2.setText(String.valueOf(v2.getPrecio()));
        
        tUbicacion1.setText(String.valueOf(v1.getUbicacion()));
        tUbicacion2.setText(String.valueOf(v2.getUbicacion()));
        
        Transmision t1 = v1.getTransmision();
        Transmision t2 = v2.getTransmision();
        
        tTransmision1.setText(t1.getTipo());
        tTransmision2.setText(t2.getTipo());
        
        RegistroVehiculo r1 = v1.getRegistro();
        RegistroVehiculo r2 = v2.getRegistro();
        
        tYear1.setText(String.valueOf(r1.getAño()));
        tYear2.setText(String.valueOf(r2.getAño()));
        
        tMarca1.setText(r1.getMarca() + " - " + r1.getModelo());
        tMarca2.setText(r2.getMarca() + " - " + r2.getModelo());
        
        Motor m1 = v1.getMotor();
        Motor m2 = v2.getMotor();
        
        
        tMotor1.setText("Torque" + m1.getTorque());
        tMotor2.setText("Torque: " + m2.getTorque());
        
        tCombustible1.setText(m1.getTipo());
        tCombustible2.setText(m2.getTipo());
        
        tPotencia1.setText(String.valueOf(m1.getPotencia()));
        tPotencia2.setText(String.valueOf(m2.getPotencia()));
        
        if (r1.getAccidentes() == null) {
            tAccidentes1.setText("No");
        } else {
            tAccidentes1.setText("Sí");
        }
        
        if (r2.getAccidentes() == null) {
            tAccidentes2.setText("No");
        } else {
            tAccidentes2.setText("Sí");
        }
        
        if (r1.getReparaciones() == null) {
            tReparaciones1.setText("No");
        } else {
            tReparaciones1.setText("Sí");
        }
        
        if (r2.getReparaciones() == null) {
            tReparaciones2.setText("No");
        } else {
            tReparaciones2.setText("Sí");
        }
        
        File f1 = v1.getCdLLImagenes().get(0);
        Image image1 = new Image(f1.toURI().toString());
        img1.setImage(image1);
        
        
        File f2 = v2.getCdLLImagenes().get(0);
        Image image2 = new Image(f2.toURI().toString());
        img2.setImage(image2);
    }
    
    public void regresar(Usuario user, Event event) throws IOException{
        CDLinkedList listaVehiculos = Utilitaria.leerArchivoVehiculos("vehiculos");

        FXMLLoader loader = new FXMLLoader(getClass().getResource("vPaginaPrincipal.fxml"));
        root = loader.load();

        vPaginaPrincipalController vPaginaPrincipalController = loader.getController();
        vPaginaPrincipalController.home(user, listaVehiculos);

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root, 1280, 720);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();     
    }
}
