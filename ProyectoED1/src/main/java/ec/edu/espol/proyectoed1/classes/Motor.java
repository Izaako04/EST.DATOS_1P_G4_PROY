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
public class Motor implements Serializable {
    private String tipo; //gasolina/diesel/electrico
    private double cilindrada;// en cc
    private int potencia; // en hp
    private int torque; // en Nm
    private int numeroCilindros;

    public Motor (String tipo, int potencia) {
        this.tipo = tipo;
        this.potencia = potencia;
    }
    
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getCilindrada() {
        return cilindrada;
    }

    public void setCilindrada(double cilindrada) {
        this.cilindrada = cilindrada;
    }

    public int getPotencia() {
        return potencia;
    }

    public void setPotencia(int potencia) {
        this.potencia = potencia;
    }

    public int getTorque() {
        return torque;
    }

    public void setTorque(int torque) {
        this.torque = torque;
    }

    public int getNumeroCilindros() {
        return numeroCilindros;
    }

    public void setNumeroCilindros(int numeroCilindros) {
        this.numeroCilindros = numeroCilindros;
    }

    public Motor(String tipo, double cilindrada, int potencia, int torque, int numeroCilindros) {
        this.tipo = tipo;
        this.cilindrada = cilindrada;
        this.potencia = potencia;
        this.torque = torque;
        this.numeroCilindros = numeroCilindros;
    }
    
    
}
