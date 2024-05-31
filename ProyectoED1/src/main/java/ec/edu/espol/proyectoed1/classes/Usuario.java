/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espol.proyectoed1.classes;

import static ec.edu.espol.proyectoed1.classes.Utilitaria.leerArchivo;
import java.util.Comparator;
import java.util.List;
import ec.edu.espol.proyectoed1.TDAs.ArrayListG4;

/**
 *
 * @author USER
 */
public class Usuario extends Persona{
    
    // nombre archivo >>>>>> usuarios
    private String correo;
    private String contrasenia;
    private List<Vehiculo> vehiculosPropios;
    private List<Vehiculo> vehiculosAgregadosAFavoritos;

    public Usuario(String nombre, String cedula) {
        super(nombre, cedula);
    }

    public Usuario(String correo, String contrasenia, String nombre, String cedula) {
        super(nombre, cedula);
        this.correo = correo;
        this.contrasenia = contrasenia;
        this.vehiculosPropios = new ArrayListG4<Vehiculo>();
        this.vehiculosAgregadosAFavoritos = new ArrayListG4<Vehiculo>();
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

    public List<Vehiculo> getVehiculosPropios() {
        return vehiculosPropios;
    }

    public void setVehiculosPropios(List<Vehiculo> vehiculosPropios) {
        this.vehiculosPropios = vehiculosPropios;
    }

    public List<Vehiculo> getVehiculosAgregadosAFavoritos() {
        return vehiculosAgregadosAFavoritos;
    }

    public void setVehiculosAgregadosAFavoritos(List<Vehiculo> vehiculosAgregadosAFavoritos) {
        this.vehiculosAgregadosAFavoritos = vehiculosAgregadosAFavoritos;
    }
    
    


    @Override
    public void registrarVehiculo(Vehiculo v) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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
