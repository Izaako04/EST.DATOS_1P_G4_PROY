/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espol.proyectoed1.classes;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 *
 * @author USER
 */
public class Utilitaria {
    
    public static void crearUsuario_ARCHIVO(String correo, String contrasenia){
        
        
    }
    
    public static void verificarExistenciaUsuario_ARCHIVO(String correo, String contrasenia){
        
    }
    
    public static void verificarExistenciaVehiculo_ARCHIVO(Vehiculo v){
        
    }
    
    
    public static void verificarContrasenia_ARCHIVO(String correo, String contrasenia){
        
    }
    
    public static void iniciarSesion_ARCHIVO(String correo, String contrasenia){
        
        
    }
    
    public static void agregarVehiculo_ARCHIVO(Vehiculo v){
        
        try (FileOutputStream fileOut = new FileOutputStream("vehiculo.ser");
             ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            out.writeObject(v);
            System.out.println("Vehiculo serializado y guardado en vehiculo.ser");
        } catch (IOException i) {
            i.printStackTrace();
        }
    }
            
}
