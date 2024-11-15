package es.cheste.handlers;

import es.cheste.classes.Character;
import es.cheste.enums.Ancestry;
import es.cheste.enums.CharaClass;
import es.cheste.exceptions.DAOException;
import es.cheste.implementations.ImpCharacterDAO;

import java.util.List;
import java.util.Scanner;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class CharacterHandler {
    private final ImpCharacterDAO dao = new ImpCharacterDAO();
    private final Scanner scanner = new Scanner(System.in);
    private static final Logger LOGGER = LogManager.getLogger(CharacterHandler.class.getName());

    public void start() {
        int choice;

        do {
            System.out.println("\nCharacter Management System");
            System.out.println("1. Add Character");
            System.out.println("2. Find Character by Name");
            System.out.println("3. Update Character");
            System.out.println("4. Delete Character");
            System.out.println("5. List All Characters");
            System.out.println("6. Find Strongest Character");
            System.out.println("7. Find Characters by Class");
            System.out.println("8. Find Characters by Ancestry");
            System.out.println("0. Return to main menu");
            System.out.print("Select an option: ");
            
            choice = getValidInteger();

            switch (choice) {
                case 1 -> insertCharacter();
                case 2 -> findCharacterByName();
                case 3 -> updateCharacter();
                case 4 -> deleteCharacter();
                case 5 -> listAllCharacters();
                case 6 -> findStrongestCharacter();
                case 7 -> findCharactersByClass();
                case 8 -> findCharactersByAncestry();
                case 0 -> System.out.println("Returning to main menu...");
                default -> System.out.println("Invalid option, try again.");
            }

        } while (choice != 0);
    }

    public void insertCharacter() {
        try {
            System.out.println("Insert Character Data...");

            System.out.print("Name: ");
            String name = scanner.nextLine();

            CharaClass chClass = selectClass();
            Ancestry ancestry = selectAncestry();

            System.out.println("Character stats must be in range -1 to 4.");
            int dexMod = getStat("Dexterity Modifier: ");
            int strMod = getStat("Strength Modifier: ");
            int conMod = getStat("Constitution Modifier: ");
            int intMod = getStat("Intelligence Modifier: ");
            int wisMod = getStat("Wisdom Modifier: ");
            int chaMod = getStat("Charisma Modifier: ");

            Character character = new Character(name, chClass, ancestry, dexMod, strMod, conMod, intMod, wisMod, chaMod);
            dao.insert(character);
            System.out.println("Character data:");
            character.describe();
            System.out.println("Character added successfully.");

            LOGGER.info("Character added successfully.");

        } catch (DAOException e) {
            System.out.println("Error inserting character.");
            LOGGER.error("Error inserting character: " + e.getMessage());

        }
    }

    private void findCharacterByName() {
        System.out.print("Enter character name: ");
        String name = scanner.nextLine();

        try {
            Character character = dao.obtainByName(name);

            if (character != null) {
                System.out.println("Character found: " + character);
                character.describe();

            } else {
                System.out.println("Character not found.");

            }

        } catch (DAOException e) {
            System.out.println("Error finding character.");
            LOGGER.error("Error finding character: " + e.getMessage());

        }
    }

    private void updateCharacter() {
        System.out.print("Enter the name of the character to update: ");
        String oldName = scanner.nextLine();

        try {
            Character oldCharacter = dao.obtainByName(oldName);

            if (oldCharacter != null) {
                System.out.println("Updating character: " + oldCharacter);
                
                System.out.print("New Name: ");
                String newName = scanner.nextLine();
                CharaClass chClass = selectClass();
                Ancestry ancestry = selectAncestry();
                int dexMod = getStat("New Dexterity Modifier: ");
                int strMod = getStat("New Strength Modifier: ");
                int conMod = getStat("New Constitution Modifier: ");
                int intMod = getStat("New Intelligence Modifier: ");
                int wisMod = getStat("New Wisdom Modifier: ");
                int chaMod = getStat("New Charisma Modifier: ");

                Character newCharacter = new Character(newName, chClass, ancestry, dexMod, strMod, conMod, intMod, wisMod, chaMod);
                dao.update(newCharacter, oldName);
                System.out.println("Character updated successfully.");
                LOGGER.info("Character updated successfully.");

            } else {
                System.out.println("Character not found.");

            }

        } catch (DAOException e) {
            System.out.println("Error updating character.");
            LOGGER.error("Error updating character: " + e.getMessage());

        }
    }

    private void deleteCharacter() {
        System.out.print("Enter character name to delete: ");
        String name = scanner.nextLine();

        try {
            dao.delete(name);
            System.out.println("Character deleted successfully.");
            LOGGER.info("Character deleted: " + name);

        } catch (DAOException e) {
            System.out.println("Error deleting character.");
            LOGGER.error("Error deleting character: " + e.getMessage());

        }
    }

    private void listAllCharacters() {
        try {
            List<Character> characters = dao.obtainAll();
    
            if (characters.isEmpty()) {
                System.out.println("No characters found.");
                return;

            }
    
            System.out.println("Listing all characters:\n");
            System.out.printf("%-30s %-15s %-15s %-5s %-5s %-5s %-5s %-5s %-5s%n",
                              "Name", "Class", "Ancestry", "DEX", "STR", "CON", "INT", "WIS", "CHA");
            System.out.println("-----------------------------------------------------------------------------------------------");
    
            for (Character character : characters) {
                System.out.printf("%-30s %-15s %-15s %-5d %-5d %-5d %-5d %-5d %-5d%n",
                                  character.getName(),
                                  character.getChClass().getClassName(),
                                  character.getAncestry().getAncestryName(),
                                  character.getDexMod(),
                                  character.getStrMod(),
                                  character.getConMod(),
                                  character.getIntMod(),
                                  character.getWisMod(),
                                  character.getChaMod());

            }

        } catch (DAOException e) {
            System.out.println("Error listing characters.");
            LOGGER.error("Error listing characters: " + e.getMessage());

        }
    }
    
    private void findStrongestCharacter() {
        try {
            Character character = dao.obtainStrongest();

            if (character != null) {
                System.out.println("Strongest Character: " + character.describe());

            } else {
                System.out.println("No characters found.");

            }

        } catch (DAOException e) {
            System.out.println("Error finding strongest character.");
            LOGGER.error("Error finding strongest character: " + e.getMessage());

        }
    }

    private void findCharactersByClass() {
        CharaClass chClass = selectClass();
    
        try {
            List<Character> characters = dao.obtainAllByClass(chClass);
    
            if (characters.isEmpty()) {
                System.out.println("No characters found for class: " + chClass.getClassName());
                return;
            }
    
            System.out.println("Characters of class " + chClass.getClassName() + ":\n");
    
            for (Character character : characters) {
                System.out.println("- " + character.getName());
            }
    
        } catch (DAOException e) {
            System.out.println("Error finding characters by class.");
            LOGGER.error("Error finding characters by class: " + e.getMessage());
        }
    }
    
    private void findCharactersByAncestry() {
        Ancestry ancestry = selectAncestry();
    
        try {
            List<Character> characters = dao.obtainAllByAncestry(ancestry);
    
            if (characters.isEmpty()) {
                System.out.println("No characters found for ancestry: " + ancestry.getAncestryName());
                return;
            }
    
            System.out.println("Characters of ancestry " + ancestry.getAncestryName() + ":\n");
    
            for (Character character : characters) {
                System.out.println("- " + character.getName());
            }
    
        } catch (DAOException e) {
            System.out.println("Error finding characters by ancestry.");
            LOGGER.error("Error finding characters by ancestry: " + e.getMessage());
        }
    }
    
    private CharaClass selectClass() {
        System.out.println("Select Character Class:");
        CharaClass[] classes = CharaClass.values();

        for (int i = 0; i < classes.length; i++) {
            System.out.println((i + 1) + ". " + classes[i].getClassName());
        }

        return classes[getValidIndex(classes.length) - 1];
    }

    private Ancestry selectAncestry() {
        System.out.println("Select Character Ancestry:");
        Ancestry[] ancestries = Ancestry.values();

        for (int i = 0; i < ancestries.length; i++) {
            System.out.println((i + 1) + ". " + ancestries[i].getAncestryName());
        }

        return ancestries[getValidIndex(ancestries.length) - 1];
    }

    private int getStat(String prompt) {
        int stat;
        do {
            System.out.print(prompt);
            stat = getValidInteger();

            if (stat < -1 || stat > 4) {
                System.out.println("Please enter a value between -1 and 4 for character stats.");
            }

        } while (stat < -1 || stat > 4);

        return stat;
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
                System.out.println("Please enter an integer value (1, 2, 3...)");

            }
        }
    }
    
}
