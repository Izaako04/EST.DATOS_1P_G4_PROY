package ec.edu.espol.proyectoed1;

import ec.edu.espol.proyectoed1.classes.RegistroVehiculo;
import ec.edu.espol.proyectoed1.classes.Vehiculo;
import ec.edu.espol.proyectoed1.classes.Utilitaria;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


/**
 * JavaFX App
 */

public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("vInicioSesion"), 800, 600);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("*nombre programa*");
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
        
        RegistroVehiculo rv = new RegistroVehiculo("placa2");
        
        Vehiculo v = new Vehiculo(rv);
        
        v.agregarVehiculo_ARCHIVO();
        
       
        
        System.out.println("done");
        
    }

}