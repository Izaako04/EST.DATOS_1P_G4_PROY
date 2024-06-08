package ec.edu.espol.proyectoed1;

import ec.edu.espol.proyectoed1.classes.Vehiculo;
import ec.edu.espol.proyectoed1.TDAs.ArrayListG4;
import ec.edu.espol.proyectoed1.TDAs.CDLinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class FiltradoVehiculos {
    public static List <Vehiculo> filtrarVehiculos(CDLinkedList <Vehiculo> vehiculos, List<String> filtros, Map<String, Object> valoresFiltros) {
        ArrayListG4<Predicate<Vehiculo>> predicados = new ArrayListG4<>();

        for (String filtro : filtros) {
            Object valorFiltro = valoresFiltros.get(filtro);
            if (valorFiltro != null) {
                switch (filtro) {
                    case "modelo":
                        String modelo = (String) valorFiltro;
                        predicados.add(FiltrosDinamicos.filtrarPorModelo(modelo));
                        break;
                    case "marca":
                        String marca = (String) valorFiltro;
                        predicados.add(FiltrosDinamicos.filtrarPorMarca(marca));
                        break;
                    case "precioDesde":
                        double precioDesde = (double) valorFiltro;
                        predicados.add(FiltrosDinamicos.filtrarPorPrecioDesde(precioDesde));
                        break;
                    case "precioHasta":
                        double precioHasta = (double) valorFiltro;
                        predicados.add(FiltrosDinamicos.filtrarPorPrecioHasta(precioHasta));
                        break;
                    case "yearDesde":
                        int yearDesde = (int) valorFiltro;
                        predicados.add(FiltrosDinamicos.filtrarPorYearDesde(yearDesde));
                        break;
                    case "yearHasta":
                        int yearHasta = (int) valorFiltro;
                        predicados.add(FiltrosDinamicos.filtrarPorYearHasta(yearHasta));
                        break;
                    case "kmDesde":
                        int kmDesde = (int) valorFiltro;
                        predicados.add(FiltrosDinamicos.filtrarPorKmDesde(kmDesde));
                        break;
                    case "kmHasta":
                        int kmHasta = (int) valorFiltro;
                        predicados.add(FiltrosDinamicos.filtrarPorKmHasta(kmHasta));
                        break;
                }
            }
        }

        Predicate<Vehiculo> filtroCombinado = predicados.stream().reduce(x -> true, Predicate::and);
        return vehiculos.stream().filter(filtroCombinado).collect(Collectors.toList());
    }
}
