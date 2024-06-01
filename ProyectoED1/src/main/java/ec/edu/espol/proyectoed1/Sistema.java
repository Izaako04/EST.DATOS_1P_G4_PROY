/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espol.proyectoed1;

import ec.edu.espol.proyectoed1.TDAs.ArrayListG4;
import ec.edu.espol.proyectoed1.classes.Usuario;
import ec.edu.espol.proyectoed1.classes.Utilitaria;
import static ec.edu.espol.proyectoed1.classes.Utilitaria.escribirArchivo;
import static ec.edu.espol.proyectoed1.classes.Utilitaria.leerArchivo;
import ec.edu.espol.proyectoed1.classes.Vehiculo;
import ec.edu.espol.proyectoed1.classes.excepcionDatoNoExistente;

/**
 *
 * @author Isaías
 */

public class Sistema {
    
    public static void actualizarUsuario_Archivo (Usuario u) {
        ArrayListG4 <Usuario> lUsuarios = leerArchivo("usuarios");
        
        for (int i = 0; i < lUsuarios.size(); i++) {
            if (lUsuarios.get(i).getCedula().equals(u.getCedula())) {
                lUsuarios.set(i, u);
            }
        }
        
        escribirArchivo(lUsuarios,"usuarios");
    }
    
    public static void agregarUsuario_Archivo(Usuario u){
        ArrayListG4 <Usuario> lUsuarios = leerArchivo("usuarios");
        
        if (lUsuarios == null){
            lUsuarios = new ArrayListG4 <Usuario> ();
            lUsuarios.add(u);
            escribirArchivo(lUsuarios,"usuarios" );
            
        } else{
            lUsuarios.add(u);
            escribirArchivo(lUsuarios, "usuarios");
      }
    }
    
    // me devuelve el usuario que sí existe
    public static Usuario verificarContraseia_ARCHIVO(String correo, String contrasenia){
        ArrayListG4 <Usuario> usuarios = leerArchivo("usuarios");
        if (usuarios != null) {
            for (Usuario user : usuarios) {
                if (user.getCorreo().equals(correo) && user.getContrasenia().equals(contrasenia)) {
                    return user;
                }  
            }
        }
        return null;
    }
    
    public static boolean verificarExistenciaUsuario_ARCHIVO(String correo, String cedula){
        ArrayListG4 <Usuario> lUsuarios = leerArchivo("usuarios");
        if(lUsuarios == null){ 
            return false;
            
        } else {
            for(Usuario user : lUsuarios) {
                if(user.getCorreo().equals(correo)) {
                    return true;
                } else if (user.getCedula().equals(cedula)) {
                    return true;
                }
            }
        }
        return false;
    }
}
