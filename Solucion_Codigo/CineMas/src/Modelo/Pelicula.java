package modelo;

public class Pelicula {
    private String titulo;
    private String horario;
    private int sala;
    private double precio;

    public Pelicula() {
    }

    public Pelicula(String titulo, String horario, int sala, double precio) {
        this.titulo = titulo;
        this.horario = horario;
        this.sala = sala;
        this.precio = precio;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getHorario() {
        return horario;
    }

    public int getSala() {
        return sala;
    }

    public double getPrecio() {
        return precio;
    }

    @Override
    public String toString() {
        return "Pelicula: " + titulo + "\n  Horario: " + horario + "\n  Sala: " + sala + "\n  Precio: $" + precio;
    }
}




