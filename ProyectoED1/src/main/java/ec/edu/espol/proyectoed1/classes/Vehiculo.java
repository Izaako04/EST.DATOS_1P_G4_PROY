/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espol.proyectoed1.classes;

import ec.edu.espol.proyectoed1.TDAs.ArrayListG4;
import ec.edu.espol.proyectoed1.TDAs.CDLinkedList;
import static ec.edu.espol.proyectoed1.classes.Utilitaria.escribirArchivo;
import static ec.edu.espol.proyectoed1.classes.Utilitaria.leerArchivo;
import java.io.File;
import java.util.List;
import java.io.Serializable;
import java.util.Comparator;


/**
 *
 * @author USER
 */

public class Vehiculo implements Serializable {
    
    // nombre archivo >>>> vehiculos
    private static final long serialVersionUID = 1L;
    
    protected RegistroVehiculo registro;
    protected Motor motor;
    protected Transmision transmision;
    protected double precio;
    protected double kilometraje;
    protected Ubicacion ubicacion;
    protected CDLinkedList<File> cdLLImagenes; // se agrega CDLL de Files (va a contener las imágenes del vehículo)

    public Vehiculo(RegistroVehiculo registro, Motor motor, Transmision transmision, double precio, double kilometraje, Ubicacion ubicacion, CDLinkedList<File> cdLLImagenes) {
        this.registro = registro;
        this.motor = motor;
        this.transmision = transmision;
        this.precio = precio;
        this.kilometraje = kilometraje;
        this.ubicacion = ubicacion;
        this.cdLLImagenes = cdLLImagenes;
    }

    public RegistroVehiculo getRegistro() {
        return registro;
    }

    public void setRegistro(RegistroVehiculo registro) {
        this.registro = registro;
    }

    public Motor getMotor() {
        return motor;
    }

    public void setMotor(Motor motor) {
        this.motor = motor;
    }

    public Transmision getTransmision() {
        return transmision;
    }

    public void setTransmision(Transmision transmision) {
        this.transmision = transmision;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public double getKilometraje() {
        return kilometraje;
    }

    public void setKilometraje(double kilometraje) {
        this.kilometraje = kilometraje;
    }

    public Ubicacion getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(Ubicacion ubicacion) {
        this.ubicacion = ubicacion;
    }
    
    public void agregarVehiculo_ARCHIVO(){

    if (leerArchivo("vehiculos")== null){
          List<Vehiculo> vehiculos= new ArrayListG4<>();
          vehiculos.add(this);
          escribirArchivo(vehiculos ,"vehiculos" );
      }
    else{
          List<Vehiculo> vehiculos= leerArchivo("vehiculos");
          vehiculos.add(this);
          escribirArchivo(vehiculos, "vehiculos");
      }
        
    }
    
    public void eliminarVehiculo_ARCHIVO(Comparator cmp){
        if (leerArchivo("vehiculos")== null){}
        else {
            if(verificarExistenciaVehiculo_ARCHIVO()){
                List<Vehiculo> vehiculos= Utilitaria.leerArchivo("vehiculos");
                vehiculos.remove(this);
            }
        }
        
    }
    
    public boolean verificarExistenciaVehiculo_ARCHIVO(){
        
        if(Utilitaria.leerArchivo("vehiculos")==null){ 
            return false;
            }
        else {
            try {
                if(obtenerDelArchivo()!= null) return true;
            } catch (excepcionDatoNoExistente ex) {
                ex.printStackTrace();
            }
        }
        return false;
        
    }
    
    private Vehiculo obtenerDelArchivo() throws excepcionDatoNoExistente{
        try{
            List<Vehiculo> vehiculos= Utilitaria.leerArchivo("vehiculos");
            for(Vehiculo v:vehiculos){
                if(this.getRegistro().getPlaca().equals(v.getRegistro().getPlaca())) return this;
            }
        } catch(Exception e){
            throw new excepcionDatoNoExistente("no existe el vehiculo en el archivo");
        }
        return null;
    }

    // getter de la CDLL de imgs
    public CDLinkedList<File> getCdLLImagenes() {
        return cdLLImagenes;
    }     
            
}