/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espol.proyectoed1.classes;
import java.io.Serializable;
/**
 *
 * @author USER
 */
public class Accidente implements Serializable {
    private String fecha;
    private String descripcion;
    private String ubicacion;

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public Accidente(String fecha, String descripcion, String ubicacion) {
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.ubicacion = ubicacion;
    }
    
    
    

    
    
}
