/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espol.proyectoed1.classes;

/**
 *
 * @author USER
 */
public class Vehiculo {

    protected double precio;
    protected String marca;
    protected String modelo;
    protected int año;
    protected double kilometraje;
    protected Motor motor;
    protected Transmision transmision;
    protected Ubicacion ubicacion;
    protected 
    
    protected String color;
    protected String tipoCombustible;
    
    protected Vendedor dueño;


    public Vehiculo(String placa, String marca, String modelo, String tipomotor, int año, double recorrido, String color, String tipoCombustible, double precio) {
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
        this.tipomotor = tipomotor;
        this.año = año;
        this.recorrido = recorrido;
        this.color = color;
        this.tipoCombustible = tipoCombustible;
        this.precio = precio;
    }
    
    public Vehiculo(String placa, String marca, String modelo, String tipomotor, int año, double recorrido, String color, String tipoCombustible, double precio, Vendedor dueño){
        this( placa, marca,modelo,tipomotor,año,recorrido,color,tipoCombustible,precio);
        this.dueño=dueño;
    }


    public String getPlaca() {
        return this.placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getMarca() {
        return this.marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return this.modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getTipomotor() {
        return tipomotor;
    }

    public void setTipomotor(String tipomotor) {
        this.tipomotor = tipomotor;
    }
    public int getAño() {
        return this.año;
    }

    public void setAño(int año) {
        this.año = año;
    }

    public double getRecorrido() {
        return this.recorrido;
    }

    public void setRecorrido(double recorrido) {
        this.recorrido = recorrido;
    }

    public String getColor() {
        return this.color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getTipoCombustible() {
        return this.tipoCombustible;
    }

    public void setTipoCombustible(String tipoCombustible) {
        this.tipoCombustible = tipoCombustible;
    }

    public double getPrecio() {
        return this.precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
}
