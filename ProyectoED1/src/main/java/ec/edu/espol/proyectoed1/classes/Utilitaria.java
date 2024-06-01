/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espol.proyectoed1.classes;
import ec.edu.espol.proyectoed1.TDAs.ArrayListG4;
import ec.edu.espol.proyectoed1.TDAs.CDLinkedList;
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
    
    public static <T> ArrayListG4 <T> leerArchivo(String filename){
        ArrayListG4 <T> returnList = new ArrayListG4 <T> ();
        
        try {
            FileInputStream archivo = new FileInputStream (filename + ".ser");
            ObjectInputStream lector = new ObjectInputStream(archivo);
            returnList = (ArrayListG4 <T>) lector.readObject();
            return returnList;
            
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static <T> CDLinkedList <T> leerArchivoVehiculos (String filename){
        CDLinkedList <T> returnList = new CDLinkedList <T> ();
        
        try {
            FileInputStream archivo = new FileInputStream (filename + ".ser");
            ObjectInputStream lector = new ObjectInputStream(archivo);
            returnList = (CDLinkedList <T>) lector.readObject();
            return returnList;
            
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
