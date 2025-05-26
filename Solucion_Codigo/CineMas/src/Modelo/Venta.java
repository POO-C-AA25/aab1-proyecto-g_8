package modelo;

import java.util.ArrayList;

public class Venta {
    private Pelicula peliculaComprada;
    private int boletosComprados;
    private ArrayList<Snack> snacksComprados;
    private ArrayList<Integer> cantidadesSnacks;
    private double totalPagar;

    public Venta() {
    }

    public Venta(Pelicula pelicula, int cantidadBoletos) {
        this.peliculaComprada = pelicula;
        this.boletosComprados = cantidadBoletos;
        this.snacksComprados = new ArrayList<>();
        this.cantidadesSnacks = new ArrayList<>();
        this.totalPagar = 0.0;
    }

    public void agregarSnack(Snack snack, int cantidad) {
        snacksComprados.add(snack);
        cantidadesSnacks.add(cantidad);
    }

    public void calcularTotal() {
        totalPagar = peliculaComprada.getPrecio() * boletosComprados;
        for (int i = 0; i < snacksComprados.size(); i++) {
            totalPagar += snacksComprados.get(i).getPrecio() * cantidadesSnacks.get(i);
        }
    }

    public double getTotalPagar() {
        return totalPagar;
    }

    @Override
    public String toString() {
        String texto = "Venta:\n";
        texto += peliculaComprada.toString() + "\n";
        texto += "Boletos: " + boletosComprados + "\n";
        if (!snacksComprados.isEmpty()) {
            texto += "Snacks:\n";
            for (int i = 0; i < snacksComprados.size(); i++) {
                texto += "- " + snacksComprados.get(i).getNombre() + " x" + cantidadesSnacks.get(i) + "\n";
            }
        }
        texto += "Total a pagar: $" + totalPagar;
        return texto;
    }
}




