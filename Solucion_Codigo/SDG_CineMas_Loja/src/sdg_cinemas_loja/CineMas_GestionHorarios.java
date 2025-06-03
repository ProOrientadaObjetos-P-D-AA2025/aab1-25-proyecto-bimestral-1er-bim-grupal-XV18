 package sdg_cinemas_loja;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class CineMas_GestionHorarios {

    public static class Horario {
        private String hora;
        private CineMas_Peliculas peli;
        private String sala;

        public Horario(CineMas_Peliculas peli, String sala, String hora) {
            this.peli = peli;
            this.sala = sala;
            this.hora = hora;
        }

        public String getSala() {
            return sala;
        }

        public CineMas_Peliculas getPelicula() {
            return peli;
        }

        public String getHora() {
            return hora;
        }

        public String toString() {
            return peli.getPelicula() + " | " + sala + " | " + hora;
        }
    }

        private ArrayList<Horario> listaHorarios;

        public CineMas_GestionHorarios() {
            listaHorarios = new ArrayList<>();
     }

        public boolean existeHorario(String sala, String hora) {
            for (Horario h : listaHorarios) {
              if (h.getSala().equalsIgnoreCase(sala) && h.getHora().equals(hora)) {
                  return true;
             }
           }
            return false;
     }

        public void añadirHorario(CineMas_Peliculas peli, String sala, String hora) {
         if (existeHorario(sala, hora)) {
             System.out.println("Horario ya reservado para la " + sala + " a las " + hora);
                return;
          }
            Horario nuevoHorario = new Horario(peli, sala, hora);
         listaHorarios.add(nuevoHorario);
         System.out.println("Horario agregado: " + nuevoHorario);
        }

        public ArrayList<Horario> listarHorarios() {
         return listaHorarios;
      }
        
         public void cargarHorariosDesdeArchivo(String archivo, CineMas_GestionPeliculas gestionPeliculas) {
             listaHorarios.clear();
            try {
                 BufferedReader br = new BufferedReader(new FileReader(archivo));
                 String linea;

            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(";");

                if (partes.length == 3) {
                    String nombrePeli = partes[0].trim();
                    String sala = partes[1].trim();
                    String hora = partes[2].trim();

                    CineMas_Peliculas peliEncontrada = null;
                    for (CineMas_Peliculas p : gestionPeliculas.getPeliculas()) {
                        if (p.getPelicula().equalsIgnoreCase(nombrePeli)) {
                            peliEncontrada = p;
                            break;
                        }
                    }

                    if (peliEncontrada != null) {
                        añadirHorario(peliEncontrada, sala, hora);
                    } else {
                        System.out.println("No se encontro la pelicula " + nombrePeli + " para el horario.");
                    }
                }
            }
            br.close();
            System.out.println("Horarios cargados del archivo.");
        } catch (IOException e) {
            System.out.println("Error al leer el archivo de horarios. " + e.getMessage());
        }
    }
}
