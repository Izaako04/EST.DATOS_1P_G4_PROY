/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espol.proyectoed1.classes;

import static ec.edu.espol.proyectoed1.classes.Utilitaria.leerArchivo;
import java.util.Comparator;
import java.util.List;
import ec.edu.espol.proyectoed1.TDAs.ArrayListG4;
import ec.edu.espol.proyectoed1.TDAs.CDLinkedList;
import java.io.Serializable;

/**
 *
 * @author USER
 */
public class Usuario extends Persona implements Serializable {
    
    // nombre archivo >>>>>> usuarios
    private String correo;
    private String contrasenia;
    private CDLinkedList<Vehiculo> vehiculosPropios;
    private CDLinkedList <Vehiculo> vehiculosAgregadosAFavoritos;

    public Usuario(String nombre, String cedula) {
        super(nombre, cedula);
    }

    public Usuario(String correo, String contrasenia, String nombre, String cedula) {
        super(nombre, cedula);
        this.correo = correo;
        this.contrasenia = contrasenia;
        this.vehiculosPropios = new CDLinkedList<Vehiculo>();
        this.vehiculosAgregadosAFavoritos = new CDLinkedList<Vehiculo>();
    }
    
    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public CDLinkedList <Vehiculo> getVehiculosPropios() {
        return vehiculosPropios;
    }

    public void setVehiculosPropios(CDLinkedList<Vehiculo> vehiculosPropios) {
        this.vehiculosPropios = vehiculosPropios;
    }

    public CDLinkedList<Vehiculo> getVehiculosAgregadosAFavoritos() {
        return vehiculosAgregadosAFavoritos;
    }

    public void setVehiculosAgregadosAFavoritos(CDLinkedList<Vehiculo> vehiculosAgregadosAFavoritos) {
        this.vehiculosAgregadosAFavoritos = vehiculosAgregadosAFavoritos;
    }
    
    @Override
    public void registrarVehiculo(Vehiculo v) {
        vehiculosPropios.add(v);
    }
//
//    @Override
//    public void crearUsuario(Usuario usuario) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }
//    
//    public static boolean buscarUsuario_ARCHIVO( ) throws excepcionDatoNoExistente{
//        if(leerArchivo("usuarios")==null){
//            throw new excepcionDatoNoExistente("No existe el achivo solicitado");
//        }
//        else {
//            List<Usuario> usuarios = Utilitaria.leerArchivo("usuarios");
//            
//            for(Usuario u: usuarios){
//                if()
//            }
//
//        }     
//    }

    @Override
    public void crearUsuario_Archivo(Usuario usuario) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
        
    
}
