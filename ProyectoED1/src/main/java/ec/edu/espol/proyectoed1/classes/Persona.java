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
public abstract class Persona implements Serializable {
    private String nombre;
    private String cedula;
    
    public abstract void registrarVehiculo(Vehiculo v);
    
    public abstract void crearUsuario_Archivo(Usuario usuario);

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

    public Persona(String nombre, String cedula) {
        this.nombre = nombre;
        this.cedula = cedula;
    }
    

    

 
    
    


}
