import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        final Scanner SC = new Scanner(System.in);

        int posicion = 0;
        int profundidad = 0;
        int localizacion = 0;

        System.out.println("Introduce las órdenes: ");

        while (SC.hasNextLine()) {

            String linea = SC.nextLine();

            String[] partes = linea.split(" ");
            String direccion = partes[0];
            int valor = Integer.parseInt(partes[1]);

            switch (direccion) {
                case "forward":
                    posicion += valor;
                    break;
                case "down":
                    profundidad += valor;
                    break;
                default:
                    profundidad -= valor;
                    break;
            }
        }

        localizacion = posicion * profundidad;

        System.out.println(localizacion);
    }

}