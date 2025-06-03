 package sdg_cinemas_loja;

import java.util.ArrayList;
import java.util.HashMap;

public class CineMas_ControlButacas {

    private HashMap<String, ArrayList<CineMas_Butacas>> verbutacas;
    private int ButMax;

    public CineMas_ControlButacas(int ButMax) {
        verbutacas = new HashMap<>();
        this.ButMax = ButMax;
    }

    private String letraxbutaca(String sala, String horario) {
        return sala.toLowerCase() + "-" + horario;
    }

    public boolean validarButaca(String butaca) {
        if (butaca.length() < 2 || butaca.length() > 3) {
            return false;
        }

        char letra = Character.toUpperCase(butaca.charAt(0));
        if (letra != 'A' && letra != 'B' && letra != 'C' && letra != 'D') {
            return false;
        }

        String numStr = butaca.substring(1);
        try {
            int num = Integer.parseInt(numStr);
            if (num < 1 || num > ButMax) {
                return false;
            }
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    public void reservarButaca(String sala, String horario, String n_butaca) {
        if (!validarButaca(n_butaca)) {
            System.out.println("El número de butaca está mal. Debe ser una letra"
                    + " entre A-D y un número entre 1 y " + ButMax + ".");
            return;
        }

        String clave = letraxbutaca(sala, horario);

        if (!verbutacas.containsKey(clave)) {
            verbutacas.put(clave, new ArrayList<CineMas_Butacas>());
        }

        ArrayList<CineMas_Butacas> lista = verbutacas.get(clave);

        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getNumeroButaca().equalsIgnoreCase(n_butaca)) {
                System.out.println("Esta butaca ya está reservada para esta sala y horario.");
                return;
            }
        }

        if (lista.size() >= ButMax) {
            System.out.println("Todas las butacas están reservadas para esta sala y horario.");
            return;
        }

        CineMas_Butacas nuevaButaca = new CineMas_Butacas(n_butaca);
        nuevaButaca.reservar();
        lista.add(nuevaButaca);

        System.out.println("Butaca " + n_butaca + " reservada correctamente para "
                + sala + " a las " + horario + ".");
    }

    public double obtenerPrecio(String n_butaca) {
        char fila = Character.toUpperCase(n_butaca.charAt(0));
        switch (fila) {
            case 'A': return 7.00;
            case 'B': return 6.00;
            case 'C': return 5.00;
            case 'D': return 4.00;
            default: return 5.00;  
        }
    }

    public boolean ReservadaSiNo(String sala, String horario, String n_butaca) {
        String clave = letraxbutaca(sala, horario);
        if (!verbutacas.containsKey(clave)) {
            return false;
        }

        ArrayList<CineMas_Butacas> lista = verbutacas.get(clave);

        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getNumeroButaca().equalsIgnoreCase(n_butaca)) {
                return true;
            }
        }
        return false;
    }

    public void mostrarButacasDisponibles(String sala, String horario) {
        String clave = letraxbutaca(sala, horario);
        ArrayList<CineMas_Butacas> reservadas = verbutacas.getOrDefault(clave, new ArrayList<>());

        System.out.println("\nOrganizacion de butacas para " + sala + " a las " + horario + "(precios incluidos):");
        for (char fila = 'A'; fila <= 'D'; fila++) {
            System.out.print(fila + " ");
            for (int i = 1; i <= ButMax; i++) {
                String codigo = fila + Integer.toString(i);
                boolean ocupada = false;
                for (CineMas_Butacas b : reservadas) {
                    if (b.getNumeroButaca().equalsIgnoreCase(codigo)) {
                        ocupada = true;
                        break;
                    }
                }
                if (ocupada) {
                    System.out.print("[X] ");
                } else {
                    System.out.print(ocupada ? "[X] " : "[$" + (int) obtenerPrecio(codigo) + "] ");
                }
            }
            System.out.println();
        }
    }

    public void verBreservadas(String sala, String horario) {
        String clave = letraxbutaca(sala, horario);

        if (!verbutacas.containsKey(clave)) {
            System.out.println("Todavía no hay ninguna butaca reservada.");
            return;
        }

        ArrayList<CineMas_Butacas> lista = verbutacas.get(clave);

        System.out.println("Butacas reservadas para " + sala + " a las " + horario + ":");
        if (lista.isEmpty()) {
            System.out.println("Todavía no hay ninguna butaca reservada.");
        } else {
            for (int i = 0; i < lista.size(); i++) {
                System.out.println("- " + lista.get(i).getNumeroButaca());
            }
        }
    }
}
