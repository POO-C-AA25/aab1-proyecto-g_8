package modelo;

public class Promocion {
    private String dia;
    private String horario;
    private double descuento;

    public Promocion() {
    }

    public Promocion(String dia, String horario, double descuento) {
        this.dia = dia;
        this.horario = horario;
        this.descuento = descuento;
    }

    public String getDia() {
        return dia;
    }

    public String getHorario() {
        return horario;
    }

    public double getDescuento() {
        return descuento;
    }

    @Override
    public String toString() {
        return "Promocion: Dia " + dia + ", Horario " + horario + ", Descuento: " + (descuento * 100) + "%";
    }
}



