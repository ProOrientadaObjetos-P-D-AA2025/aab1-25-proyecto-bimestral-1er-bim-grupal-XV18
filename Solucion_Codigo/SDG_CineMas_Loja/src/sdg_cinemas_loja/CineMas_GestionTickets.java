 package sdg_cinemas_loja;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class CineMas_GestionTickets {

    private ArrayList<CineMas_VentaTickets> tickets;
    private int contadorTickets = 0;   
    public CineMas_GestionTickets() {
        tickets = new ArrayList<>();
    }
    
    private int obtenerNumeroTicket() {
        return ++contadorTickets;
    }

    public void registrarTicket(CineMas_VentaTickets ticket) {
        tickets.add(ticket);
        guardarTicketEnArchivo(ticket, obtenerNumeroTicket());
    }
    
        public void resumenVentas() {
        if (tickets.isEmpty()) {
        System.out.println("No hay tickets vendidos aún.");
        return;
        }

        double total = 0;
     System.out.println("\nResumen de tickets vendidos:");
        for (CineMas_VentaTickets t : tickets) {
        System.out.println("- " + t.getPelicula().getPelicula() + " | " + t.getSala() + 
                           " | " + t.getHorario() + " | " + t.getButaca().getNumeroButaca() + 
                           " | $" + t.getCosto());
         total += t.getCosto();
        }
     System.out.println("Total registrado: $" + total);
    }

    private void guardarTicketEnArchivo(CineMas_VentaTickets ticket, int numeroTicket) {
        try {
            FileWriter fw = new FileWriter("tickets_vendidos.txt", true);
            PrintWriter pw = new PrintWriter(fw);

            pw.println("Ticket #" + numeroTicket);
            pw.println("Pelicula: " + ticket.getPelicula().getPelicula());
            pw.println("Sala: " + ticket.getSala());
            pw.println("Horario: " + ticket.getHorario());
            pw.println("Butaca: " + ticket.getButaca().getNumeroButaca());
            pw.printf("Precio: $%.2f%n", ticket.getCosto());
            pw.println("---------------------------");

            pw.close();
            fw.close();
 
        } catch (IOException e) {
            System.out.println("No se pudo guardar el ticket. " + e.getMessage());
        }
    }

    public void mostrarTickets() {
        if (tickets.isEmpty()) {
            System.out.println("No hay tickets guardados en el sistema.");
        } else {
            int contador = 1;
            for (CineMas_VentaTickets t : tickets) {
                System.out.println("Ticket #" + contador);
                System.out.println("Pelicula: " + t.getPelicula().getPelicula());
                System.out.println("Sala: " + t.getSala());
                System.out.println("Horario: " + t.getHorario());
                System.out.println("Butaca: " + t.getButaca().getNumeroButaca());
                System.out.printf("Precio: $%.2f%n", t.getCosto());
                System.out.println("-----------------------------------");
                contador++;
            }
        }
    }
}
