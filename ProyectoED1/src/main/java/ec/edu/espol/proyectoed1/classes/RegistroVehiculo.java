/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espol.proyectoed1.classes;

import java.util.List;

/**
 *
 * @author USER
 */
public class RegistroVehiculo {
    
    private String placa;
    private Persona duenio;
    private int año;
    protected String marca;
    protected String modelo;
    private List<Reparacion> reparaciones;
    private List<Accidente> accidentes;

    public RegistroVehiculo(String placa, Persona duenio, int año, String marca, String modelo, List<Reparacion> reparaciones, List<Accidente> accidentes) {
        this.placa = placa;
        this.duenio = duenio;
        this.año = año;
        this.marca = marca;
        this.modelo = modelo;
        this.reparaciones = reparaciones;
        this.accidentes = accidentes;
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

    public List<Reparacion> getReparaciones() {
        return reparaciones;
    }

    public void setReparaciones(List<Reparacion> reparaciones) {
        this.reparaciones = reparaciones;
    }

    public List<Accidente> getAccidentes() {
        return accidentes;
    }

    public void setAccidentes(List<Accidente> accidentes) {
        this.accidentes = accidentes;
    }
    
    
}
