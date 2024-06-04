/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espol.proyectoed1.classes;

import ec.edu.espol.proyectoed1.TDAs.ArrayListG4;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author USER
 */
public class RegistroVehiculo implements Serializable {
    
    private String placa;
    private Persona duenio;
    private int año;
    private String marca;
    private String modelo;
    private Reparacion reparaciones; // antes ArrayListG4
    private Accidente accidentes;

    public RegistroVehiculo(String placa, Persona duenio, int año, String marca, String modelo, Reparacion reparaciones, Accidente accidentes) {
        this.placa = placa;
        this.duenio = duenio;
        this.año = año;
        this.marca = marca;
        this.modelo = modelo;
        this.reparaciones = reparaciones;
        this.accidentes = accidentes;
    }
    
    public RegistroVehiculo (String marca) {
        this.marca = marca;
    }
    
    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public Persona getDuenio() {
        return duenio;
    }

    public void setDuenio(Persona duenio) {
        this.duenio = duenio;
    }

    public int getAño() {
        return año;
    }

    public void setAño(int año) {
        this.año = año;
    }

    public Reparacion getReparaciones() {
        return reparaciones;
    }

    public void setReparaciones(Reparacion reparaciones) {
        this.reparaciones = reparaciones;
    }

    public Accidente getAccidentes() {
        return accidentes;
    }

    public void setAccidentes(Accidente accidentes) {
        this.accidentes = accidentes;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }
    
    
}
