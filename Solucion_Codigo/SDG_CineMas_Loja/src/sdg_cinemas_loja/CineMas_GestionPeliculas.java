 package sdg_cinemas_loja;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class CineMas_GestionPeliculas {

    private final ArrayList<CineMas_Peliculas> listaPeliculas;

    public CineMas_GestionPeliculas() {
        listaPeliculas = new ArrayList<>();
    }

    public void guardarPelicula(CineMas_Peliculas peli) {
        listaPeliculas.add(peli);
    }

    public ArrayList<CineMas_Peliculas> getPeliculas() {
        return listaPeliculas;
    }

    public void cargarPeliculasDesdeArchivo(String archivo) {
        try {
            BufferedReader lector = new BufferedReader(new FileReader(archivo));
            String linea;

            while ((linea = lector.readLine()) != null) {
                String[] partes = linea.split(";");

                if (partes.length == 2) {
                    String nombre = partes[0].trim();
                    String duracionTexto = partes[1].trim();

                    int duracion = convertirADuracion(duracionTexto);

                    if (duracion > 0) {
                        CineMas_Peliculas nuevaPeli = new CineMas_Peliculas(nombre, duracion);
                        guardarPelicula(nuevaPeli);
                    }
                }
            }

            lector.close();
        } catch (IOException e) {
            System.out.println("ERROR al cargar el archivo " + e.getMessage());
        }
    }

    private int convertirADuracion(String texto) {
        texto = texto.toLowerCase().trim();
        int totalMinutos = 0;

        try {
            if (texto.contains("h")) {
                String[] partes = texto.split("h");
                totalMinutos += Integer.parseInt(partes[0].trim()) * 60;

                if (partes.length > 1 && partes[1].contains("m")) {
                    String minutos = partes[1].replace("m", "").trim();
                    if (!minutos.equals("")) {
                        totalMinutos += Integer.parseInt(minutos);
                    }
                }
            } else if (texto.contains("m")) {
                totalMinutos += Integer.parseInt(texto.replace("m", "").trim());
            } else {
                totalMinutos += Integer.parseInt(texto);
            }
        } catch (Exception e) {
            return 0;
        }

        return totalMinutos;
    }
}
