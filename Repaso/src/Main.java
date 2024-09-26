import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Main {

    private static final Scanner SC = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println("Hello world!");
    }

    private int sumaNumeros(int x, int y) {

        return x + y;
    }

    private void saludar(String nombre) {

        System.out.println("Hola, " + nombre + "!");
    }

    private void comparar(double i, double j) {

        if (i == j) {
            System.out.println("Los números son iguales.");
        } else if (i > j) {
            System.out.println("Mayor: " + i + " Menor: " + j);
        } else {
            System.out.println("Mayor: " + j + " Menor: " + i);
        }
    }

    private void leerBucle(int n, int m) {

        int may = n > m ? n : m;
        int men = may == n ? m : n;

        for (int menor = men; menor < may; menor++) {

            System.out.println(men);
        }
    }

    private int sumaDeNumeros() {

        int sum = 0;

        for (int i = 0; i < 5; i++) {

            int concurrente = SC.nextInt();
            sum += concurrente;
        }

        return sum;
    }

    private int sumaDeNumeros2() {

        int sum = 0;
        int i = 0;

        while (i < 5) {

            int concurrente = SC.nextInt();
            sum += concurrente;
            i++;
        }

        return sum;
    }

    private String leerMes() {

        int mes = SC.nextInt();

        if (mes == 1) {
            return "Enero";
        } else if (mes == 2) {
            return "Febrero";
        } else if (mes == 3) {
            return "Marzo";
        } else if (mes == 4) {
            return "Abril";
        } else if (mes == 5) {
            return "Mayo";
        } else if (mes == 6) {
            return "Junio";
        } else if (mes == 7) {
            return "Julio";
        } else if (mes == 8) {
            return "Agosto";
        } else if (mes == 9) {
            return "Septiembre";
        } else if (mes == 10) {
            return "Octubre";
        } else if (mes == 11) {
            return "Noviembre";
        } else if (mes == 12) {
            return "Diciembre";
        } else {
            return "No es un mes, pazguato.";
        }
    }

    private void leerMes2() {

        int mes = SC.nextInt();
        String nombre;

        switch(mes) {
            case 1: nombre = "Enero";
            case 2: nombre = "Febrero";
            case 3: nombre = "Marzo";
            case 4: nombre = "Abril";
            case 5: nombre = "Mayo";
            case 6: nombre = "Junio";
            case 7: nombre = "Julio";
            case 8: nombre = "Agosto";
            case 9: nombre = "Septiembre";
            case 10: nombre = "Octubre";
            case 11: nombre = "Noviembre";
            case 12: nombre = "Diciembre";
            default: nombre = "No es un mes, impresentable.";
        }
    }

    private String devolverDNI(int sinLetra) {

        String[] letraDNI = {"T", "R", "W", "A", "G", "M", "Y", "F", "P", "D", "X", "B", "N", "J", "Z", "Q", "V", "H", "L", "C", "K", "E"};
        int resto = sinLetra % 23;

        return Integer.toString(sinLetra) + letraDNI[resto];
    }

    private void clasificarNotas() {

        List<Integer> suspensos = new ArrayList<>();
        List<Integer> aprobados = new ArrayList<>();
        List<Integer> notables = new ArrayList<>();
        List<Integer> sobresalientes = new ArrayList<>();
        List<Integer> matriculas = new ArrayList<>();

        for (int i = 0; i < 10; i++) {

            int nota = SC.nextInt();

            switch(nota) {
                case 0, 1, 2, 3, 4: suspensos.append(nota); break;
                case 5, 6: aprobados.append(nota); break;
                case 7, 8: notables.append(nota); break;
                case 9: sobresalientes.append(nota); break;
                case 10: matriculas.append(nota); break;
                default: System.out.println(nota + " no es un número calificable.");
            }

            System.out.println("Notas:\n");
            System.out.println(suspensos.toString());
            System.out.println(aprobados.toString());
            System.out.println(notables.toString());
            System.out.println(sobresalientes.toString());
            System.out.println(matriculas.toString());
        }
    }

    private double aFahrenheit(double celsius) {

        double fahrenheit = celsius * (9.0 / 5.0) + 32.0;
        return Double.parseDouble(String.format("%.1f", fahrenheit));
    }

    private String[] datosCirculoEsfera(double radio) {

        String[] datos = new String[3];

        double diametro = 2 * radio;
        double areaSeccion = Math.PI * Math.pow(radio, 2);
        double volumen = (4.0 / 3.0) * Math.PI * Math.pow(radio, 3);

        datos[0] = Double.parseDouble(String.format("%.3f", diametro));
        datos[1] = Double.parseDouble(String.format("%.3f", areaSeccion));
        datos[1] = Double.parseDouble(String.format("%.3f", volumen));

        return datos;
    }
}