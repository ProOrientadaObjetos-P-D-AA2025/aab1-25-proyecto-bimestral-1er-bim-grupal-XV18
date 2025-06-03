  package sdg_cinemas_loja;

public class CineMas_Butacas {
    
    private String numeroButaca;
    private boolean reservado;
    
    public CineMas_Butacas(String numeroButaca){
        this.numeroButaca = numeroButaca;
        this.reservado = false;
    }
    
    public String getNumeroButaca(){
        return this.numeroButaca;
    }
    
    public boolean isReservado(){
        return this.reservado;
    }
    
    public void reservar(){
        this.reservado = true;
    }
    
    public void desocupar(){
        this.reservado = false;
    }

    @Override
    public String toString() {
        return "Butaca " + numeroButaca + (reservado ? " (Reservada)" : " (Disponible)");
    }
}