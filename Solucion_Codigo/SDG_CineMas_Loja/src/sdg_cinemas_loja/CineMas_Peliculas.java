 package sdg_cinemas_loja;

public class CineMas_Peliculas {

    private String nombre;
    private int duracionMinutos;
    private String duracionFormateada;

    public CineMas_Peliculas(String nombre, int duracionMinutos) {
        this.nombre = nombre;
        this.duracionMinutos = duracionMinutos;
        this.duracionFormateada = formatearDuracion(duracionMinutos);
    }

    private String formatearDuracion(int minutos) {
        if (minutos <= 0) {
            return "0 min";
        }
        if (minutos < 60) {
            return minutos + "m";
        } else {
            int horas = minutos / 60;
            int mins = minutos % 60;
            if (mins == 0) {
                return horas + "h";
            } else {
                return horas + "h " + mins + "m";
            }
        }
    }

    public String getPelicula() {
        return nombre;
    }

    public int getDuracion() {
        return duracionMinutos;
    }

    public String getDuracionFormateada() {
        return duracionFormateada;
    }

    @Override
    public String toString() {
        return nombre + " - Duración: " + duracionFormateada;
    }
}
