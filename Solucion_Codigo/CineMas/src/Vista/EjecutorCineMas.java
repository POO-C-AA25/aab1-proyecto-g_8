package vista;

import java.util.Scanner;
import controlador.Cine;

public class EjecutorCineMas {

    private Scanner sc;
    private Cine controlador;

    public static void main(String[] args) {
        EjecutorCineMas app = new EjecutorCineMas();
        app.sc = new Scanner(System.in);
        app.controlador = new Cine();
        app.controlador.setVista(app);
        app.controlador.iniciarPrograma();
    }

    public int mostrarMenu() {
        System.out.println("\n      +---------+");
        System.out.println("+----+| CineMas |+----+");
        System.out.println("      +---------+");
        System.out.println("1. Mostrar cartelera");
        System.out.println("2. Comprar boletos");
        System.out.println("3. Comprar snacks");
        System.out.println("4. Mostrar ventas");
        System.out.println("5. Salir");
        System.out.print("Ingrese opcion: ");
        int opcion = sc.nextInt();
        sc.nextLine();
        System.out.println("");
        return opcion;
    }

    public void imprimirTexto(String texto) {
        System.out.println(texto);
    }

    public String leerTexto(String mensaje) {
        System.out.print(mensaje);
        return sc.nextLine();
    }

    public int leerNumero(String mensaje) {
        System.out.print(mensaje);
        int numero = sc.nextInt();
        sc.nextLine();
        return numero;
    }
}






