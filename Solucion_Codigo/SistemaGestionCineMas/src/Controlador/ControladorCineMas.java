
package Controlador;

import Modelo.*;
import Vista.VistaCineMas;
import java.util.ArrayList;

public class ControladorCineMas {
    private VistaCineMas vista = new VistaCineMas();

    private Pelicula[] peliculas;
    private String[] titulos;
    private double[] precios;
    private ArrayList<ArrayList<String>> horarios;
    private ArrayList<ArrayList<String>> salas;

    private ArrayList<Snack> snacksDisponibles;

    private ArrayList<Venta> registroVentas;

    public ControladorCineMas() {
        registroVentas = new ArrayList<>();

        // Definimos las peliculas con sus títulos y precios
        titulos = new String[]{"The Last Adventure", "La Casa Misteriosa", "Viaje al Espacio"};
        precios = new double[]{7.5, 6.0, 8.0};

        horarios = new ArrayList<>();
        salas = new ArrayList<>();

        // Creamos horarios y salas para pelicula 1
        ArrayList<String> h1 = new ArrayList<>();
        h1.add("14:00");
        h1.add("19:00");
        h1.add("21:00");
        horarios.add(h1);

        ArrayList<String> s1 = new ArrayList<>();
        s1.add("Sala 1");
        s1.add("Sala 1");
        s1.add("Sala 2");
        salas.add(s1);

        // Creamos horarios y salas para pelicula 2
        ArrayList<String> h2 = new ArrayList<>();
        h2.add("13:00");
        h2.add("18:00");
        horarios.add(h2);

        ArrayList<String> s2 = new ArrayList<>();
        s2.add("Sala 3");
        s2.add("Sala 3");
        salas.add(s2);

        // Creamos horarios y salas para pelicula 3
        ArrayList<String> h3 = new ArrayList<>();
        h3.add("15:00");
        h3.add("20:00");
        h3.add("22:00");
        horarios.add(h3);

        ArrayList<String> s3 = new ArrayList<>();
        s3.add("Sala 4");
        s3.add("Sala 4");
        s3.add("Sala 5");
        salas.add(s3);

        // Crearmos objetos de tipo Pelicula con datos iniciales
        peliculas = new Pelicula[titulos.length];
        for(int i = 0; i < titulos.length; i++){
            peliculas[i] = new Pelicula(titulos[i], horarios.get(i), salas.get(i), precios[i]);
        }

        // Inicializamos los snacks disponibles con una cantidad inicial de 0
        snacksDisponibles = new ArrayList<>();
        snacksDisponibles.add(new Snack("Popcorn", 3.5, 0));
        snacksDisponibles.add(new Snack("Soda", 2.0, 0));
        snacksDisponibles.add(new Snack("Chocolate", 2.5, 0));
    }

    public void ejecutarPrograma() {
        vista.mostrarTitulo();

        // Metodo para mostrar la cartelera completa con horarios, salas y precios
        vista.mostrarCartelera(titulos, horarios, salas, precios);

        // Metodo para Seleccionar la pelicula
        int seleccionPelicula = vista.seleccionarPelicula();
        Pelicula peliculaElegida = peliculas[seleccionPelicula];

        // Metodo para Seleccionar el horario y sala
        int seleccionHorario = vista.seleccionarHorario(peliculaElegida.getHorarios().size());
        String horarioElegido = peliculaElegida.getHorarios().get(seleccionHorario);
        String salaElegida = peliculaElegida.getSalas().get(seleccionHorario);

        // Metodo para ingresar la Cantidad de boletos
        int cantidadBoletos = vista.ingresarCantidadBoletos();
        Boleto boleto = new Boleto(cantidadBoletos, peliculaElegida.getPrecio());

        // Metodo para selccionar los snacks
        ArrayList<Snack> snacksComprados = new ArrayList<>();
        while(true){
            vista.mostrarSnacksDisponibles(snacksDisponibles);
            int opcionSnack = vista.seleccionarSnack(snacksDisponibles.size() + 1);
            if(opcionSnack == snacksDisponibles.size()){ // Opcion "Ninguno"
                break;
            }
            int cantidadSnack = vista.ingresarCantidadSnack();
            Snack snackBase = snacksDisponibles.get(opcionSnack);
            snacksComprados.add(new Snack(snackBase.getNombre(), snackBase.getPrecio(), cantidadSnack));
        }

        // Crearmos venta con descuento inicial 0 y descripcion "Ninguno"
        Venta venta = new Venta(peliculaElegida, horarioElegido, salaElegida, boleto, snacksComprados, 0, "Ninguno");

        // Metodo para Aplicar descuentos automáticos que modifican la venta
        aplicarDescuentos(venta);

        // Metodo para Mostrar factura final
        vista.mostrarFactura(venta);

        // Metodo para Registrar venta para historial
        registroVentas.add(venta);
    }

    // Metodo para Aplicar descuentos según reglas definidas
    private void aplicarDescuentos(Venta venta){
        double descuento = 0;
        String descripcion = "Ninguno";

        // Metodo que se encarga de dar un Descuento del 10% si compra 5 o más boletos
        if(venta.getBoleto().getCantidad() >= 5){
            descuento = venta.getBoleto().calcularTotal() * 0.10;
            descripcion = "Descuento por compra de 5 o mas boletos";
        }

        // Metodo que se encarga de dar un Descuento extra por combo snacks y boletos
        if(!venta.getSnacks().isEmpty() && venta.getBoleto().getCantidad() >= 3){
            descuento += 2.0;
            descripcion += " + Descuento combo snacks y boletos";
        }

        venta.setDescuentoAplicado(descuento);
        venta.setDescripcionDescuento(descripcion);
    }
}
