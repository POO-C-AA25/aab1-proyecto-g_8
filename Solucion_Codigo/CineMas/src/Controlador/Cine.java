
package controlador;

import modelo.*;
import vista.EjecutorCineMas;
import java.util.ArrayList;

public class Cine {
    
    private ArrayList<Venta> ventasRegistradas;
    private EjecutorCineMas vista;

    private static Pelicula[] peliculasDisponibles = {
        new Pelicula("Avatar 2", "18:00", 1, 8.0),
        new Pelicula("Spiderman", "20:00", 2, 7.5),
        new Pelicula("Matrix", "21:30", 3, 6.0)
    };

    private static Snack[] snacksDisponibles = {
        new Snack("Palomitas", 3.5),
        new Snack("Refresco", 2.0),
        new Snack("Chocolate", 1.5)
    };


    public Cine() {
        ventasRegistradas = new ArrayList<>();
    }

    public void setVista(EjecutorCineMas vista) {
        this.vista = vista;
    }

    public void iniciarPrograma() {
        int opcion;
        do {
            opcion = vista.mostrarMenu();
            switch (opcion) {
                case 1:
                    mostrarCartelera();
                    break;
                case 2:
                    procesarCompraBoletos();
                    break;
                case 3:
                    procesarCompraSnacks();
                    break;
                case 4:
                    mostrarVentas();
                    break;
                case 5:
                    vista.imprimirTexto("Gracias por usar CineMas. Hasta luego.");
                    break;
                default:
                    vista.imprimirTexto("Opcion invalida, intente de nuevo.");
            }
        } while (opcion != 5);
    }

    private void mostrarCartelera() {
        String texto = "Cartelera disponible:\n";
        for (int i = 0; i < peliculasDisponibles.length; i++) {
            texto += (i + 1) + ". " + peliculasDisponibles[i].toString() + "\n";
        }
        vista.imprimirTexto(texto);
    }

    private void procesarCompraBoletos() {
        mostrarCartelera();
        int seleccion = vista.leerNumero("Seleccione numero de pelicula: ") - 1;
        if (seleccion < 0 || seleccion >= peliculasDisponibles.length) {
            vista.imprimirTexto("Seleccion invalida.");
            return;
        }
        Pelicula peliculaElegida = peliculasDisponibles[seleccion];
        int cantidadBoletos = vista.leerNumero("Cantidad de boletos: ");
        Venta nuevaVenta = new Venta(peliculaElegida, cantidadBoletos);
        nuevaVenta.calcularTotal();
        ventasRegistradas.add(nuevaVenta);
        vista.imprimirTexto("Compra boletos exitosa.\n" + nuevaVenta.toString());
    }

    private void procesarCompraSnacks() {
        if (ventasRegistradas.isEmpty()) {
            vista.imprimirTexto("Primero debe comprar boletos.");
            return;
        }
        Venta ultimaVenta = ventasRegistradas.get(ventasRegistradas.size() - 1);
        String texto = "Snacks disponibles:\n";
        for (int i = 0; i < snacksDisponibles.length; i++) {
            texto += (i + 1) + ". " + snacksDisponibles[i].toString() + "\n";
        }
        vista.imprimirTexto(texto);

        boolean agregar = true;
        while (agregar) {
            int opcionSnack = vista.leerNumero("Seleccione snack (0 para terminar): ");
            if (opcionSnack == 0) {
                agregar = false;
            } else if (opcionSnack > 0 && opcionSnack <= snacksDisponibles.length) {
                Snack snackElegido = snacksDisponibles[opcionSnack - 1];
                int cantidad = vista.leerNumero("Cantidad de " + snackElegido.getNombre() + ": ");
                ultimaVenta.agregarSnack(snackElegido, cantidad);
            } else {
                vista.imprimirTexto("Seleccion invalida.");
            }
        }
        ultimaVenta.calcularTotal();
        vista.imprimirTexto("Compra snacks actualizada.\n" + ultimaVenta.toString());
    }

    private void mostrarVentas() {
        if (ventasRegistradas.isEmpty()) {
            vista.imprimirTexto("No hay ventas registradas.");
            return;
        }
        for (Venta venta : ventasRegistradas) {
            vista.imprimirTexto(venta.toString());
            vista.imprimirTexto("--------------------------");
        }
    }
}


