 package sdg_cinemas_loja;

import java.util.Scanner;

public class SDG_CineMas_Loja {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        CineMas_GestionPeliculas gestionPeliculas = new CineMas_GestionPeliculas();
        CineMas_ControlButacas controlButacas = new CineMas_ControlButacas(20);
        CineMas_GestionTickets gestionTickets = new CineMas_GestionTickets();

      
        gestionPeliculas.cargarPeliculasDesdeArchivo("peliculas.txt");

        boolean salir = false;

        while (!salir) {
            System.out.println("\n===== Gestion CineMas loja =======");
            System.out.println("1) Nuevo ticket para una pelicula");
            System.out.println("2) Mostrar tickets");
            System.out.println("3) Salir");
            System.out.println("4) Resumen de tickets vendidos");
            System.out.print("Elegir opcion (1-4): ");

            String opcion = sc.nextLine();

            switch (opcion) {
                case "1":
                    // Hay o no hay peliculas
                    if (gestionPeliculas.getPeliculas().isEmpty()) {
                        System.out.println("No se encuenta peliculas en peliculas.txt.");
                        continue;
                    }   System.out.println("Peliculas disponibles:");
                    for (int i = 0; i < gestionPeliculas.getPeliculas().size(); i++) {
                        CineMas_Peliculas p = gestionPeliculas.getPeliculas().get(i);
                        System.out.println((i + 1) + ") " + p.getPelicula() + " (" + formatoDuracion(p.getDuracion()) + ")");
                    }   int opcionPelicula = 0;
                    while (true) {
                        System.out.print("Seleccione el numero de la pelicula que desea ver: ");
                        String entrada = sc.nextLine();
                        try {
                            opcionPelicula = Integer.parseInt(entrada);
                            if (opcionPelicula >= 1 && opcionPelicula <= gestionPeliculas.getPeliculas().size()) {
                                break;
                            } else {
                                System.out.println("En ese numero no hay ninguna pelicula.");
                            }
                        } catch (Exception e) {
                            System.out.println("Formato incorrecto,Vuelva a ingresar.");
                        }
                    }   CineMas_Peliculas pelicula = gestionPeliculas.getPeliculas().get(opcionPelicula - 1);
                    // Sala
                    String n_sala = "";
                    while (true) {
                        System.out.print("En que sala va a ver la pelicula ? (Sala 1 a Sala 4): ");
                        n_sala = sc.nextLine().trim();
                        if (n_sala.equalsIgnoreCase("Sala 1") || n_sala.equalsIgnoreCase("Sala 2") ||
                                n_sala.equalsIgnoreCase("Sala 3") || n_sala.equalsIgnoreCase("Sala 4")) {
                            break;
                        }
                        System.out.println("Esta Sala no existe. Intente de nuevo.");
                    }   // Horario
                    String horario = "";
                    while (true) {
                        System.out.print("En que horario va a verla ? (entre 13:00 y 23:00): ");
                        horario = sc.nextLine();
                        
                        if ((horario.length() == 4 && horario.charAt(1) == ':') ||
                                (horario.length() == 5 && horario.charAt(2) == ':')) {
                            
                            String parteHora = horario.length() == 4 ? horario.substring(0, 1) : horario.substring(0, 2);
                            String parteMinuto = horario.length() == 4 ? horario.substring(2, 4) : horario.substring(3, 5);
                            
                            try {
                                int h = Integer.parseInt(parteHora);
                                int m = Integer.parseInt(parteMinuto);
                                
                                if (m < 0 || m > 59) {
                                    System.out.println("Minutos incorrectos.");
                                    continue;
                                }
                                if (h < 13 || h > 23) {
                                    System.out.println("CineMas no atiende en este horario.");
                                    continue;
                                }
                                break;
                            } catch (Exception e) {
                                System.out.println("Formato incorrecto, intente de nuevo.");
                            }
                        } else {
                            System.out.println("Formato inválido, intente de nuevo.");
                        }
                    }   // Butaca
                    String butacaElegida = "";
                    while (true) {
                        controlButacas.mostrarButacasDisponibles(n_sala, horario);
                        System.out.print("Ingrese el numero de Butaca que desee (A1 a D20): ");
                        butacaElegida = sc.nextLine().trim();
                        
                        if (!controlButacas.validarButaca(butacaElegida)) {
                            System.out.println("Esta Butaca no existe, intente con otra");
                            continue;
                        }
                        if (controlButacas.ReservadaSiNo(n_sala, horario, butacaElegida)) {
                            System.out.println("Esta Butaca ya esta reservada, elija otra.");
                            continue;
                        }
                        break;
                    }   controlButacas.reservarButaca(n_sala, horario, butacaElegida);
                    controlButacas.verBreservadas(n_sala, horario);
                    CineMas_Butacas butaca = new CineMas_Butacas(butacaElegida);
                    double costo = controlButacas.obtenerPrecio(butacaElegida);
                    CineMas_VentaTickets ticket = new CineMas_VentaTickets(pelicula, horario, n_sala, butaca, costo);
                    gestionTickets.registrarTicket(ticket);
                    System.out.println("Ticket generado correctamente.");
                    break;
                case "2":
                    gestionTickets.mostrarTickets();
                    break;
                case "3":
                    salir = true;
                    System.out.println("A salido del sistema Gestion CineMas-Loja.");
                    break;
                case "4":
                    gestionTickets.resumenVentas();
                    break;
                default:
                    System.out.println("Opción incorrecta, intente otra vez.");
                    break;
            }
        }

        sc.close();
    }

    private static String formatoDuracion(int minutosTotales) {
        int horas = minutosTotales / 60;
        int minutos = minutosTotales % 60;
        if (horas > 0 && minutos > 0) {
            return horas + "h " + minutos + "m";
        } else if (horas > 0) {
            return horas + "h";
        } else {
            return minutos + "m";
        }
    }
}
