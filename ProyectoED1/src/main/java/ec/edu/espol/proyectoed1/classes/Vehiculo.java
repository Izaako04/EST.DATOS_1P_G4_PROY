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
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;


/**
 *
 * @author USER
 */

public class Vehiculo implements Serializable {
    
    // nombre archivo >>>> vehiculos
    
    private RegistroVehiculo registro;
    protected Motor motor;
    protected Transmision transmision;
    protected double precio;
    protected double kilometraje;
    protected String ubicacion;

    protected CDLinkedList<File> cdLLImagenes; // se agrega CDLL de Files (va a contener las imágenes del vehículo)


    public Vehiculo(RegistroVehiculo registro, Motor motor, Transmision transmision, double precio, double kilometraje, String ubicacion, CDLinkedList<File> cdLLImagenes) {
        this.registro = registro;
        this.motor = motor;
        this.transmision = transmision;
        this.precio = precio;
        this.kilometraje = kilometraje;
        this.ubicacion = ubicacion;

        this.cdLLImagenes = cdLLImagenes;

    }

    public Vehiculo(RegistroVehiculo registro) {
        this.registro = registro;
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

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
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

    // metodo para sortear por comparador los vehiculos, me da una nueva lista con los elementos ya sorteados ready para presentar
    public static CDLinkedList<Vehiculo> getSortedList(CDLinkedList<Vehiculo> vehiculos, Comparator<Vehiculo> cmp){
        PriorityQueue<Vehiculo> sortVehiculos = new PriorityQueue(cmp);
        CDLinkedList<Vehiculo> ret = new CDLinkedList();
//        for(Vehiculo v: vehiculos){
//            sortVehiculos.add(v);
//            
//        }
//        for(Vehiculo v: sortVehiculos){
//            ret.add(v);
//            
//        }
//        
        for (int i = 0; i < vehiculos.size(); i++) {
            sortVehiculos.add(vehiculos.get(i));
        }

        for (int i = 0; i < sortVehiculos.size(); i++) {
            ret.add(vehiculos.get(i));
        }
        return ret;
    } 
//    Comparator cmpXprecio
    
            
    private static Comparator<Vehiculo> cmpXprecioMenMay = new Comparator<Vehiculo>() {
        @Override
        public int compare(Vehiculo v1, Vehiculo v2) {
            return Double.compare(v1.getPrecio(),v2.getPrecio());
        }
    };
    private static Comparator<Vehiculo> cmpXprecioMayMen = new Comparator<Vehiculo>() {
        @Override
        public int compare(Vehiculo v1, Vehiculo v2) {
            return Double.compare(v2.getPrecio(),v1.getPrecio());
        }
    };
    private static Comparator<Vehiculo> cmpXkilometrajeMenMay = new Comparator<Vehiculo>() {
        @Override
        public int compare(Vehiculo v1, Vehiculo v2) {
            return Double.compare(v1.getKilometraje(),v2.getKilometraje());
        }
    };
    
    private static Comparator<Vehiculo> cmpXkilometrajeMayMen = new Comparator<Vehiculo>() {
        @Override
        public int compare(Vehiculo v1, Vehiculo v2) {
            return Double.compare(v2.getKilometraje(),v1.getKilometraje());
        }
    };
    
    private static Comparator<Vehiculo> cmpXprecioYkm= new Comparator<Vehiculo>() {
        @Override
        public int compare(Vehiculo v1, Vehiculo v2) {
            if (Double.compare(v1.getPrecio(),v2.getPrecio())==0){
                return Double.compare(v1.getKilometraje(), v2.getKilometraje());
            }
            else return Double.compare(v1.getPrecio(),v2.getPrecio());
        }
    };
    
    
    public static Comparator<Vehiculo> cmpXmarca = new Comparator<Vehiculo>() {
        @Override
        public int compare(Vehiculo v1, Vehiculo v2) {
            return v1.getRegistro().getMarca().compareTo(v2.getRegistro().getMarca());
        }
    };
    
    private static Comparator<Vehiculo> cmpXmodelo = new Comparator<Vehiculo>() {
        @Override
        public int compare(Vehiculo v1, Vehiculo v2) {
            return v1.getRegistro().getModelo().compareTo(v2.getRegistro().getModelo());
        }
    };
    
    private static Comparator<Vehiculo> cmpXtransmision = new Comparator<Vehiculo>() {
        @Override
        public int compare(Vehiculo v1, Vehiculo v2) {
            return v1.getTransmision().getTipo().compareTo(v2.getTransmision().getTipo());
        }
    };
    
    private static Comparator<Vehiculo> cmpXmotor = new Comparator<Vehiculo>() {
        @Override
        public int compare(Vehiculo v1, Vehiculo v2) {
            return v1.getMotor().getTipo().compareTo(v2.getMotor().getTipo());
        }
    };

    public static Comparator<Vehiculo> getCmpXprecioMenMay() {
        return cmpXprecioMenMay;
    }

    public static Comparator<Vehiculo> getCmpXprecioMayMen() {
        return cmpXprecioMayMen;
    }

    public static Comparator<Vehiculo> getCmpXkilometrajeMenMay() {
        return cmpXkilometrajeMenMay;
    }

    public static Comparator<Vehiculo> getCmpXkilometrajeMayMen() {
        return cmpXkilometrajeMayMen;
    }

    public static Comparator<Vehiculo> getCmpXprecioYkm() {
        return cmpXprecioYkm;
    }

    public static Comparator<Vehiculo> getCmpXmarca() {
        return cmpXmarca;
    }

    public static Comparator<Vehiculo> getCmpXmodelo() {
        return cmpXmodelo;
    }

    public static Comparator<Vehiculo> getCmpXtransmision() {
        return cmpXtransmision;
    }

    public static Comparator<Vehiculo> getCmpXmotor() {
        return cmpXmotor;
    }
    
    

    // getter de la CDLL de imgs
    public CDLinkedList<File> getCdLLImagenes() {
        return cdLLImagenes;
    }     
            
}