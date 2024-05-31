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
import java.util.List;

/**
 *
 * @author Isaías
 */

public class Sistema {
    
    public void agregarUsuario_Archivo(Usuario u){
    
        if (leerArchivo("usuarios")== null){
          List<Usuario> usuarios= new ArrayListG4<>();
          usuarios.add(u);
          escribirArchivo(usuarios,"usuarios" );
      }
    else{
          List<Usuario> usuarios= leerArchivo("usuarios");
          usuarios.add(u);
          escribirArchivo(usuarios, "usuarios");
      }
        
    }
    
    public static boolean verificarContraseia_ARCHIVO(String correo, String contrasenia){
        List<Usuario> usuarios = leerArchivo("usuario");
        if (usuarios != null) {
            for (Usuario user : usuarios) {
                if (!user.getCorreo().equals(correo) && !user.getContrasenia().equals(contrasenia)) {
                    return false;
                }  
            }
            return true;
        }
        return false;
    }
    
    public static boolean verificarExistenciaUsuario_ARCHIVO(String correo, String contrasenia){
        if(Utilitaria.leerArchivo("usuarios")==null){ 
            return false;
            }
        else {
            List<Usuario> usuarios= Utilitaria.leerArchivo("usuarios");
            for(Usuario user:usuarios){
                if(user.getCorreo().equals(correo) && user.getContrasenia().equals(contrasenia)) 
                    return true;
            }
        }
        return false;
    }
}
