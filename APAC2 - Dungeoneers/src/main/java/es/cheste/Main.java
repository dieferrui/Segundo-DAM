package es.cheste;

import es.cheste.handlers.InventoryHandler;
import es.cheste.handlers.ItemHandler;
import es.cheste.handlers.QuestResultHandler;
import es.cheste.handlers.PartyHandler;
import es.cheste.handlers.CharacterHandler;
import es.cheste.handlers.DungeonHandler;

import java.util.Scanner;

public class Main {
    private final Setup setup = new Setup();
    private final CommonMethod cm = new CommonMethod();
    private final CharacterHandler characterHandler = new CharacterHandler();
    private final DungeonHandler dungeonHandler = new DungeonHandler();
    private final ItemHandler itemHandler = new ItemHandler();
    private final PartyHandler partyHandler = new PartyHandler();
    private final InventoryHandler inventoryHandler = new InventoryHandler();
    private final QuestResultHandler questResultHandler = new QuestResultHandler();

    private final Scanner scanner = new Scanner(System.in);

    /**
     * Función principal para iniciar la aplicación.
     *
     * @param args Argumentos de la línea de comandos.
     */
    public static void main(String[] args) {
        new Main().start();
    }

    /**
     * Función para iniciar la aplicación y mostrar el menú principal.
     */
    private void start() {
        setup.createTables();
        int choice;

        do {
            System.out.println("\nDungeoneers");
            System.out.println("1. Character Management");
            System.out.println("2. Item Management");
            System.out.println("3. Dungeon Management");
            System.out.println("4. Party Management");
            System.out.println("5. Inventory Management");
            System.out.println("6. Quest Result Management");
            System.out.println("0. Exit");

            System.out.print("Select an option: ");

            choice = cm.getValidInteger();

            switch (choice) {
                case 1 -> characterHandler.start();
                case 2 -> itemHandler.start();
                case 3 -> dungeonHandler.start();
                case 4 -> partyHandler.start();
                case 5 -> inventoryHandler.start();
                case 6 -> questResultHandler.start();
                case 0 -> System.out.println("Exiting the application...");
                default -> System.out.println("Invalid option, try again.");
            }

        } while (choice != 0);

        scanner.close();
    }
}