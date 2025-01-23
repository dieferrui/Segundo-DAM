package es.cheste.tank;

import es.cheste.tank.Tanque;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class ClienteObjeto {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        String host = "localhost";
        int numPuerto = 5000;

        System.out.println("CLIENTE >> Iniciando cliente...");
        Socket cliente = new Socket(host, numPuerto);
        ObjectInputStream ois = new ObjectInputStream(cliente.getInputStream());
        Tanque t = (Tanque) ois.readObject();

        System.err.println("CLIENTE >> Recibido tanque del servidor: " + t.getNickname());

        Scanner scanner = new Scanner(System.in);

        System.out.print("Introduce el nickname del tanque: ");
        String nickname = scanner.nextLine();
        t.setNickname(nickname);

        System.out.println("Selecciona la clase del tanque:");
        System.out.println("1. Light tank");
        System.out.println("2. Medium tank");
        System.out.println("3. Heavy tank");

        int opcionClase = getValidatedInt(scanner, 1, 3);
        String clase = switch (opcionClase) {
            case 1 -> "Light tank";
            case 2 -> "Medium tank";
            case 3 -> "Heavy tank";
            default -> throw new IllegalStateException("Valor inesperado: " + opcionClase);
        };
        t.setClase(clase);

        System.out.print("Introduce el número de serie del tanque (entero positivo): ");
        int numSerial = getValidatedInt(scanner, 1, Integer.MAX_VALUE);
        t.setNumSerial(numSerial);

        System.out.print("Introduce el peso del tanque en toneladas (15-100): ");
        int peso = getValidatedInt(scanner, 15, 100);
        t.setPeso(peso);

        System.out.print("Introduce el calibre del cañón en milímetros (20-183): ");
        int calibre = getValidatedInt(scanner, 20, 183);
        t.setCalibreCannon(calibre);

        ObjectOutputStream oos = new ObjectOutputStream(cliente.getOutputStream());
        oos.writeObject(t);

        System.out.println("CLIENTE >> Enviando tanque: " + t.getNickname());

        ois.close();
        oos.close();
        cliente.close();
    }

    private static int getValidatedInt(Scanner scanner, int min, int max) {
        int value;

        while (true) {
            try {
                value = Integer.parseInt(scanner.nextLine());

                if (value >= min && value <= max) {
                    return value;

                } else {
                    System.out.printf("El valor debe estar entre %d y %d. Inténtalo de nuevo: ", min, max);

                }

            } catch (NumberFormatException e) {
                System.out.print("Entrada inválida. Introduce un número entero: ");

            }
        }
    }
}
