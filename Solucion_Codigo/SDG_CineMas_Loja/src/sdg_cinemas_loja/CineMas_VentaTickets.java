 package sdg_cinemas_loja;

public class CineMas_VentaTickets {
    
    private CineMas_Peliculas pelicula;
    private String horario;
    private String sala;
    private CineMas_Butacas butaca;
    private double costo;
 
    public CineMas_VentaTickets(CineMas_Peliculas pelicula, String horario, String sala, CineMas_Butacas butaca, double costo) {
        this.pelicula = pelicula;
        this.horario = horario;
        this.sala = sala;
        this.butaca = butaca;
        this.costo = costo;
    }
 
    public CineMas_Peliculas getPelicula() {
        return pelicula;
    }

    public String getHorario() {
        return horario;
    }

    public String getSala() {
        return sala;
    }

    public CineMas_Butacas getButaca() {
        return butaca;
    }

    public double getCosto() {
        return costo;
    }
}
