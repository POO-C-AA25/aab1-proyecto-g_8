package modelo;

public class Cartelera {
    private Pelicula[] peliculas;
    private int totalPeliculas;

    public Cartelera() {
        peliculas = new Pelicula[10];
        totalPeliculas = 0;
        cargarPeliculas();
    }

    private void cargarPeliculas() {
        agregarPelicula(new Pelicula("Avatar 2", "18:00", 1, 8.0));
        agregarPelicula(new Pelicula("Spiderman", "20:00", 2, 7.5));
        agregarPelicula(new Pelicula("Matrix", "21:30", 3, 6.0));
    }

    public void agregarPelicula(Pelicula pelicula) {
        if (totalPeliculas < peliculas.length) {
            peliculas[totalPeliculas] = pelicula;
            totalPeliculas++;
        }
    }

    public Pelicula getPelicula(int indice) {
        if (indice >= 0 && indice < totalPeliculas) {
            return peliculas[indice];
        }
        return null;
    }

    @Override
    public String toString() {
        String resultado = "  +-----------+\n  | Cartelera |\n  +-----------+\n";
        for (int i = 0; i < totalPeliculas; i++) {
            resultado += (i + 1) + ". " + peliculas[i].toString() + "\n";
        }
        return resultado;
    }
}




