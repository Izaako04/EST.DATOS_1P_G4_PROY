/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espol.proyectoed1.classes;

import java.util.Comparator;
import java.util.List;

/**
 *
 * @author USER
 */
public interface Filtrable {
    
    void filtrarPorX(Comparator cmp);
    
    void filtrarPorY(List<Object> objetos); //donde los objetos pueden ser partes del vehiculo
    
}
