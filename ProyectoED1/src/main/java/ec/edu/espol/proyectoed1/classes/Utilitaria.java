/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espol.proyectoed1.classes;
import ec.edu.espol.proyectoed1.TDAs.ArrayListG4;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author USER
 */
public class Utilitaria {
    
    public static void crearUsuario_ARCHIVO(String correo, String contrasenia){
        
        
        
    }
    
    public static void verificarExistenciaUsuario_ARCHIVO(String correo, String contrasenia){
        
    }    
    
    public static void verificarContrasenia_ARCHIVO(String correo, String contrasenia){
        
    }
    
    public static <T> List<T> leerArchivo(String filename){
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename+".ser"))) {
            return (List<T>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static <T> void escribirArchivo(List<T> conjunto, String filename){
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename + ".ser"))) {
            out.writeObject(conjunto);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
            
}
