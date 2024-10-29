package es.cheste.handlers;

import es.cheste.classes.Dungeon;
import es.cheste.enums.Biome;
import es.cheste.enums.Difficulty;
import es.cheste.exceptions.DAOException;
import es.cheste.implementations.ImpDungeonDAO;

import java.util.List;
import java.util.Scanner;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class DungeonHandler {
    private final ImpDungeonDAO dao = new ImpDungeonDAO();
    private final Scanner scanner = new Scanner(System.in);
    private static final Logger LOGGER = LogManager.getLogger(DungeonHandler.class.getName());

    public void start() {
        int choice;
        do {
            System.out.println("\nDungeon Management System");
            System.out.println("1. Add Dungeon");
            System.out.println("2. Find Dungeon by Name");
            System.out.println("3. Update Dungeon");
            System.out.println("4. Delete Dungeon");
            System.out.println("5. List All Dungeons");
            System.out.println("6. Find Hardest Dungeon");
            System.out.println("7. Find Dungeons by Biome");
            System.out.println("8. Find Dungeons by Difficulty");
            System.out.println("0. Return to main menu");
            System.out.print("Select an option: ");
            
            choice = getValidInteger();

            switch (choice) {
                case 1 -> insertDungeon();
                case 2 -> findDungeonByName();
                case 3 -> updateDungeon();
                case 4 -> deleteDungeon();
                case 5 -> listAllDungeons();
                case 6 -> findHardestDungeon();
                case 7 -> findDungeonsByBiome();
                case 8 -> findDungeonsByDifficulty();
                case 0 -> System.out.println("Returning to main menu...");
                default -> System.out.println("Invalid option, try again.");

            }

        } while (choice != 0);
    }

    private void insertDungeon() {
        try {
            System.out.print("Dungeon Name: ");
            String name = scanner.nextLine();

            Biome biome = selectBiome();
            Difficulty difficulty = selectDifficulty();

            System.out.print("Number of Floors: ");
            int floors = getValidInteger();

            System.out.print("Has Boss (true/false): ");
            boolean hasBoss = Boolean.parseBoolean(scanner.nextLine());

            Dungeon dungeon = new Dungeon(name, biome, difficulty, floors, hasBoss);
            dao.insert(dungeon);
            System.out.println("Dungeon added successfully.");
            LOGGER.info("Dungeon added: " + dungeon.getName());

        } catch (DAOException e) {
            System.out.println("Error inserting dungeon.");
            LOGGER.error("Error inserting dungeon: " + e.getMessage());

        }
    }

    private void findDungeonByName() {
        System.out.print("Enter dungeon name: ");
        String name = scanner.nextLine();

        try {
            Dungeon dungeon = dao.obtainByName(name);

            if (dungeon != null) {
                System.out.println("Dungeon found: " + dungeon);

            } else {
                System.out.println("Dungeon not found.");

            }

        } catch (DAOException e) {
            System.out.println("Error finding dungeon.");
            LOGGER.error("Error finding dungeon: " + e.getMessage());

        }
    }

    private void updateDungeon() {
        System.out.print("Enter the name of the dungeon to update: ");
        String oldName = scanner.nextLine();

        try {
            Dungeon oldDungeon = dao.obtainByName(oldName);

            if (oldDungeon != null) {
                System.out.println("Updating dungeon: " + oldDungeon);

                System.out.print("New Name: ");
                String newName = scanner.nextLine();

                Biome biome = selectBiome();
                Difficulty difficulty = selectDifficulty();
                
                System.out.print("Number of Floors: ");
                int floors = getValidInteger();

                System.out.print("Has Boss (true/false): ");
                boolean hasBoss = Boolean.parseBoolean(scanner.nextLine());

                Dungeon newDungeon = new Dungeon(newName, biome, difficulty, floors, hasBoss);
                dao.update(newDungeon, oldName);
                System.out.println("Dungeon updated successfully.");
                LOGGER.info("Dungeon updated: " + newName);

            } else {
                System.out.println("Dungeon not found.");

            }

        } catch (DAOException e) {
            System.out.println("Error updating dungeon.");
            LOGGER.error("Error updating dungeon: " + e.getMessage());

        }
    }

    private void deleteDungeon() {
        System.out.print("Enter dungeon name to delete: ");
        String name = scanner.nextLine();

        try {
            dao.delete(name);
            System.out.println("Dungeon deleted successfully.");
            LOGGER.info("Dungeon deleted: " + name);

        } catch (DAOException e) {
            System.out.println("Error deleting dungeon.");
            LOGGER.error("Error deleting dungeon: " + e.getMessage());

        }
    }

    private void listAllDungeons() {
        try {
            List<Dungeon> dungeons = dao.obtainAll();

            if (dungeons.isEmpty()) {
                System.out.println("No dungeons found.");
                return;

            }

            System.out.println("Listing all dungeons:");

            for (Dungeon dungeon : dungeons) {
                System.out.println("- " + dungeon);

            }

        } catch (DAOException e) {
            System.out.println("Error listing dungeons.");
            LOGGER.error("Error listing dungeons: " + e.getMessage());

        }
    }

    private void findHardestDungeon() {
        try {
            Dungeon dungeon = dao.obtainHardest();

            if (dungeon != null) {
                System.out.println("Hardest Dungeon: " + dungeon);

            } else {
                System.out.println("No dungeons found.");

            }

        } catch (DAOException e) {
            System.out.println("Error finding hardest dungeon.");
            LOGGER.error("Error finding hardest dungeon: " + e.getMessage());

        }
    }

    private void findDungeonsByBiome() {
        Biome biome = selectBiome();

        try {
            List<Dungeon> dungeons = dao.obtainAllByBiome(biome);
            if (dungeons.isEmpty()) {
                System.out.println("No dungeons found for biome: " + biome);
                return;

            }

            System.out.println("Dungeons in biome " + biome + ":");

            for (Dungeon dungeon : dungeons) {
                System.out.println("- " + dungeon);

            }

        } catch (DAOException e) {
            System.out.println("Error finding dungeons by biome.");
            LOGGER.error("Error finding dungeons by biome: " + e.getMessage());

        }
    }

    private void findDungeonsByDifficulty() {
        Difficulty difficulty = selectDifficulty();

        try {
            List<Dungeon> dungeons = dao.obtainAllByDifficulty(difficulty);

            if (dungeons.isEmpty()) {
                System.out.println("No dungeons found for difficulty: " + difficulty);
                return;

            }

            System.out.println("Dungeons with difficulty " + difficulty + ":");

            for (Dungeon dungeon : dungeons) {
                System.out.println("- " + dungeon);

            }

        } catch (DAOException e) {
            System.out.println("Error finding dungeons by difficulty.");
            LOGGER.error("Error finding dungeons by difficulty: " + e.getMessage());

        }
    }

    private Biome selectBiome() {
        System.out.println("Select Dungeon Biome:");
        Biome[] biomes = Biome.values();

        for (int i = 0; i < biomes.length; i++) {
            System.out.println((i + 1) + ". " + biomes[i].name());

        }

        return biomes[getValidIndex(biomes.length) - 1];
    }

    private Difficulty selectDifficulty() {
        System.out.println("Select Dungeon Difficulty:");
        Difficulty[] difficulties = Difficulty.values();

        for (int i = 0; i < difficulties.length; i++) {
            System.out.println((i + 1) + ". " + difficulties[i].name());

        }

        return difficulties[getValidIndex(difficulties.length) - 1];
    }

    private int getValidIndex(int max) {
        int choice;

        do {
            System.out.print("Enter your choice: ");
            choice = getValidInteger();

            if (choice < 1 || choice > max) {
                System.out.println("Please select a number between 1 and " + max + ".");

            }

        } while (choice < 1 || choice > max);

        return choice;
    }

    private int getValidInteger() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine().trim());

            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid integer.");
                
            }
        }
    }
}
