import java.util.Objects;
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
            System.out.println(menor);

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
                case 0, 1, 2, 3, 4: suspensos.add(nota); break;
                case 5, 6: aprobados.add(nota); break;
                case 7, 8: notables.add(nota); break;
                case 9: sobresalientes.add(nota); break;
                case 10: matriculas.add(nota); break;
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

        datos[0] = String.format("%.3f", diametro);
        datos[1] = String.format("%.3f", areaSeccion);
        datos[2] = String.format("%.3f", volumen);

        return datos;
    }

    private int encontrarNumeroSuerte(int dia, int mes, int anio) {

        int suma = dia + mes + anio;
        int numSuerte = 0;

        while (suma > 0) {
            numSuerte += suma % 10;
            suma /= 10;

        }

        return numSuerte;
    }

    private boolean contraseniaValida(String contrasenia) {
        boolean tieneMayuscula = false;
        boolean tieneNumero = false;
        boolean tiene5Caracteres = contrasenia.length() >= 5;

        for (int i = 0; i < contrasenia.length(); i++) {
            char caracter = contrasenia.charAt(i);

            if (Character.isUpperCase(caracter)) {
                tieneMayuscula = true;
            } else if (Character.isDigit(caracter)) {
                tieneNumero = true;
            }

        }

        int comprobar = contrasenia.compareTo(SC.nextLine());

        return tieneMayuscula && tiene5Caracteres && tieneNumero && (comprobar == 0);
    }

    private void mostrarASCII() {
        for (int i = 0; i < 256; i++) {
            System.out.println(i + " - " + (char) i);
        }
    }

    private void guardarCincoNombres() {
        String[] nombres = new String[5];

        for (int i = 0; i < 5; i++) {
            nombres[i] = SC.nextLine();
        }

        for (int i = 0; i < 5; i++) {
            System.out.println(nombres[i]);
        }
    }

    private void guardarNombresHastaCero() {
        List<String> nombres = new ArrayList<>();
        String nombre = SC.nextLine();

        while (!nombre.equals("0")) {
            nombres.add(nombre);
            nombre = SC.nextLine();
        }

        int num = 1;
        for (String n : nombres) {
            System.out.println(num + " " + n);
            num++;
        }
    }

    private void comparaNumeroAleatorio(int numero) {
        int numeroAleatorio = (int) (Math.random() * 10) + 1;
        boolean premio = false;

        if (numero == numeroAleatorio) {
            premio = true;
        }

        if (premio) {
            System.out.println("Elige un premio: 1. T-34, 2. M4, 3. Panzer IV");

            int premioElegido = SC.nextInt();
            System.out.println("Has elegido el premio " + premioElegido);

        } else {
            System.out.println("No has ganado nada. El número era " + numeroAleatorio);
        }
    }

    private void compararNumerosAleatorios(int[] numeros) {
        int numeroAleatorio = (int) (Math.random() * 10) + 1;
        boolean premio = false;

        for (int i = 0; i < numeros.length; i++) {
            if (numeros[i] == numeroAleatorio) {
                premio = true;
                break;
            }
        }

        if (premio) {
            System.out.println("Elige un premio: 1. IS-2, 2. M26, 3. Tiger II");

            int premioElegido = SC.nextInt();
            System.out.println("Has elegido el premio " + premioElegido);

        } else {
            System.out.println("No has ganado nada. El número era " + numeroAleatorio);
        }
    }

    private void generarTanques(int numTanques) {
        List<Tanque> tanques = new ArrayList<>();

        for (int i = 0; i < numTanques; i++) {
            String nombre = SC.nextLine();
            int peso = SC.nextInt();
            TipoTanque tipo = TipoTanque.valueOf(SC.nextLine());

            tanques.add(new Tanque(nombre, peso, tipo));
        }

        for (Tanque t : tanques) {
            System.out.println(t.toString());
        }
    }
}