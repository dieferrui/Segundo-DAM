import java.util.Scanner;
import java.util.ArrayList;

public class Main {

    protected static final Scanner SC = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println("Elige el número de ciclos de crecimiento: ");
        int numCiclos = SC.nextInt();

        System.out.println("Introduce el estado incial los peces: ");
        String estado = SC.nextLine();

        String[] arrayPeces = estado.split(",");
        Integer[] arrayPecesInt = new Integer[arrayPeces.length];

        for (int i = 0; i < arrayPeces.length; i++) {

            arrayPecesInt[i] = Integer.parseInt(arrayPeces[i]);

        }

        ArrayList<Integer> bancoPeces = new ArrayList<>(Arrays.asList(arrayPecesInt));

        for (int i = 0; i < numCiclos; i++) {

            for (String valor : bancoPeces) {

                if (valor = 0) {

                    valor = 6;
                    bancoPeces.append(8);

                } else {

                    valor--;

                }
            }
        }

        System.out.println(bancoPeces.size());
    }
}
