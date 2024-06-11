/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espol.proyectoed1.classes;

import ec.edu.espol.proyectoed1.TDAs.ArrayListG4;
import ec.edu.espol.proyectoed1.TDAs.CDLinkedList;
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
    private CDLinkedList<Reparacion> reparaciones; // antes ArrayListG4
    private CDLinkedList<Accidente> accidentes;
    private String tipo;
    private Reparacion reparaciones2; // antes ArrayListG4
    private Accidente accidentes2;
    

    public RegistroVehiculo(String placa, Persona duenio, int año, String marca, String modelo, CDLinkedList<Reparacion> reparaciones, CDLinkedList<Accidente> accidentes, String tipo) {
        this.placa = placa;
        this.duenio = duenio;
        this.año = año;
        this.marca = marca;
        this.modelo = modelo;
        this.reparaciones = reparaciones;
        this.accidentes = accidentes;
        this.tipo = tipo;
    }

    
    
    public RegistroVehiculo (String marca) {
        this.marca = marca;
    }
    
    public RegistroVehiculo (String placa, Boolean lol) {
        this.placa = placa;
        System.out.println(lol);
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

    public CDLinkedList<Reparacion> getReparaciones() {
        return reparaciones;
    }

    public void setReparaciones(CDLinkedList<Reparacion> reparaciones) {
        this.reparaciones = reparaciones;
    }

    public CDLinkedList<Accidente> getAccidentes() {
        return accidentes;
    }

    public void setAccidentes(CDLinkedList<Accidente> accidentes) {
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
    
    public String getTipo(){
        return tipo;
    }
    
    public void setTipo(String tipo){
        this.tipo = tipo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Reparacion getReparaciones2() {
        return reparaciones2;
    }

    public void setReparaciones2(Reparacion reparaciones2) {
        this.reparaciones2 = reparaciones2;
    }

    public Accidente getAccidentes2() {
        return accidentes2;
    }

    public void setAccidentes2(Accidente accidentes2) {
        this.accidentes2 = accidentes2;
    }
    
    
}
