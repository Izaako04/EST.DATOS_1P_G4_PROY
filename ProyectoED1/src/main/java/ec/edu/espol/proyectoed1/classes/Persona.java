/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espol.proyectoed1.classes;

import java.util.List;
import java.io.Serializable;
/**
 *
 * @author USER
 */
public class Persona implements Serializable {
    private String nombre;
    private String cedula;
    private String correo;
    private String contrasenia;
    private List<Vehiculo> vehiculosPropios;
    private List<Vehiculo> vehiculosAgregadosAFavoritos;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
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

    public Persona(String nombre, String cedula, String correo, String contrasenia, List<Vehiculo> vehiculosPropios, List<Vehiculo> vehiculosAgregadosAFavoritos) {
        this.nombre = nombre;
        this.cedula = cedula;
        this.correo = correo;
        this.contrasenia = contrasenia;
        this.vehiculosPropios = vehiculosPropios;
        this.vehiculosAgregadosAFavoritos = vehiculosAgregadosAFavoritos;
    }
    
    
}
