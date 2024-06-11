package ec.edu.espol.proyectoed1;

import ec.edu.espol.proyectoed1.classes.Vehiculo;
import java.util.function.Predicate;

public class FiltrosDinamicos {
    public static Predicate<Vehiculo> filtrarPorModelo (String modelo) {
        return v -> v.getRegistro().getModelo().equalsIgnoreCase(modelo);
    }
    
    public static Predicate<Vehiculo> filtrarPorTipo (String tipo) {
        return v -> v.getRegistro().getTipo().equalsIgnoreCase(tipo);
    }

    public static Predicate<Vehiculo> filtrarPorMarca (String marca) {
        return v -> v.getRegistro().getMarca().equalsIgnoreCase(marca);
    }

    public static Predicate<Vehiculo> filtrarPorPrecioDesde (double precioDesde) {
        return v -> v.getPrecio() >= precioDesde;
    }

    public static Predicate<Vehiculo> filtrarPorPrecioHasta (double precioHasta) {
        return v -> v.getPrecio() <= precioHasta;
    }

    public static Predicate<Vehiculo> filtrarPorYearDesde (int yearHasta) {
        return v -> v.getRegistro().getAño() >= yearHasta;
    }
    
    public static Predicate<Vehiculo> filtrarPorYearHasta (int yearHasta) {
        return v -> v.getRegistro().getAño() <= yearHasta;
    }
    
    public static Predicate<Vehiculo> filtrarPorKmDesde (int kmDesde) {
        return v -> v.getKilometraje() >= kmDesde;
    }
    
    public static Predicate<Vehiculo> filtrarPorKmHasta (int kmHasta) {
        return v -> v.getKilometraje() <= kmHasta;
    }
}