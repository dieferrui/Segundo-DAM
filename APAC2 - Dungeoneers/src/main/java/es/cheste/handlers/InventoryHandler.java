package es.cheste.handlers;

import es.cheste.CommonMethod;
import es.cheste.classes.Inventory;
import es.cheste.classes.Character;
import es.cheste.classes.Item;
import es.cheste.exceptions.DAOException;
import es.cheste.implementations.ImpInventoryDAO;
import es.cheste.implementations.ImpItemDAO;
import es.cheste.implementations.ImpCharacterDAO;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import java.util.List;
import java.util.Scanner;

public class InventoryHandler {
    private final ImpInventoryDAO dao = new ImpInventoryDAO();
    private final ImpCharacterDAO characterDAO = new ImpCharacterDAO();
    private final ImpItemDAO itemDAO = new ImpItemDAO();
    private final CommonMethod cm = new CommonMethod();
    private final Scanner scanner = new Scanner(System.in);
    private static final Logger LOGGER = LogManager.getLogger(InventoryHandler.class.getName());

    public void start() {
        int choice;

        do {
            System.out.println("\nInventory Management System");
            System.out.println("1. Add Inventory Item");
            System.out.println("2. Find Inventory by Slot Number");
            System.out.println("3. List Inventory for Character");
            System.out.println("4. List Equipment for Character");
            System.out.println("5. List Consumables for Character");
            System.out.println("6. Update Inventory Item");
            System.out.println("7. Delete Inventory Item");
            System.out.println("0. Return to main menu");
            System.out.print("Select an option: ");

            choice = cm.getValidInteger();

            switch (choice) {
                case 1 -> insertInventory();
                case 2 -> findInventoryBySlot();
                case 3 -> listCharacterInventory();
                case 4 -> listCharacterEquipment();
                case 5 -> listCharacterConsumables();
                case 6 -> updateInventory();
                case 7 -> deleteInventory();
                case 0 -> System.out.println("Returning to main menu...");
                default -> System.out.println("Invalid option, try again.");
            }

        } while (choice != 0);
    }

    private void insertInventory() {
        try {
            Character chara = getCharacter();
            Item item = getItem();

            System.out.print("Quantity: ");
            int quantity = cm.getValidInteger();

            Inventory inventory = new Inventory(chara, item, quantity);
            dao.insert(inventory);
            System.out.println("Inventory item added successfully.");
            LOGGER.info("Inventory item " + item.getName() + " added for character " + chara.getName());

        } catch (DAOException e) {
            System.out.println("Error inserting inventory item.");
            LOGGER.error("Error inserting inventory item: " + e.getMessage());

        }
    }

    private void findInventoryBySlot() {
        System.out.print("Enter slot number: ");
        int slotNumber = cm.getValidInteger();

        try {
            Inventory inventory = dao.obtainBySlotNumber(slotNumber);

            if (inventory != null) {
                System.out.println("Inventory entry for slot " + slotNumber + ": " + inventory.describe());

            } else {
                System.out.println("No inventory item found in that slot.");

            }

        } catch (DAOException e) {
            System.out.println("Error finding inventory.");
            LOGGER.error("Error finding inventory: " + e.getMessage());

        }
    }

    private void listCharacterInventory() {
        System.out.print("Enter character name: ");
        String characterName = scanner.nextLine();

        try {
            List<Inventory> inventories = dao.obtainCharacterInventory(characterName);

            if (inventories.isEmpty()) {
                System.out.println("No inventory entries found for character: " + characterName);
                return;

            }

            System.out.println("Inventory items for character " + characterName + ":");

            for (Inventory inventory : inventories) {
                System.out.println("- " + inventory.getSlotNumber() + ": " + inventory.getItemName() +
                        " (Qty: " + inventory.getQuantity() + ")");

            }

        } catch (DAOException e) {
            System.out.println("Error listing inventory.");
            LOGGER.error("Error listing inventory: " + e.getMessage());

        }
    }

    private void listCharacterEquipment() {
        System.out.print("Enter character name: ");
        String characterName = scanner.nextLine();

        try {
            List<Inventory> equipment = dao.obtainCharacterEquipment(characterName);

            if (equipment.isEmpty()) {
                System.out.println("No equipment found for character: " + characterName);
                return;

            }

            System.out.println("Equipment for character " + characterName + ":");

            for (Inventory item : equipment) {
                System.out.println("- " + item.getSlotNumber() + ": " + item.getItemName());

            }

        } catch (DAOException e) {
            System.out.println("Error listing equipment.");
            LOGGER.error("Error listing equipment: " + e.getMessage());

        }
    }

    private void listCharacterConsumables() {
        System.out.print("Enter character name: ");
        String characterName = scanner.nextLine();

        try {
            List<Inventory> consumables = dao.obtainCharacterConsumables(characterName);

            if (consumables.isEmpty()) {
                System.out.println("No consumables found for character: " + characterName);
                return;

            }

            System.out.println("Consumables for character " + characterName + ":");

            for (Inventory item : consumables) {
                System.out.println("- " + item.getSlotNumber() + ": " + item.getItemName() +
                        " (Qty: " + item.getQuantity() + ")");

            }

        } catch (DAOException e) {
            System.out.println("Error listing consumables.");
            LOGGER.error("Error listing consumables: " + e.getMessage());

        }
    }

    private void updateInventory() {
        System.out.print("Enter slot number to update: ");
        int slotNumber = cm.getValidInteger();

        try {
            Inventory existingInventory = dao.obtainBySlotNumber(slotNumber);

            if (existingInventory != null) {
                Character chara = getCharacter();
                Item item = getItem();

                System.out.print("Quantity: ");
                int quantity = cm.getValidInteger();

                Inventory updatedInventory = new Inventory(slotNumber, chara.getName(), item.getName(), quantity);
                dao.update(slotNumber, updatedInventory);

                System.out.println("Inventory item updated successfully.");
                LOGGER.info("Inventory slot updated: " + slotNumber);

            } else {
                System.out.println("No inventory item found in that slot.");

            }

        } catch (DAOException e) {
            System.out.println("Error updating inventory item.");
            LOGGER.error("Error updating inventory item: " + e.getMessage());

        }
    }

    private void deleteInventory() {
        System.out.print("Enter slot number to delete: ");
        int slotNumber = cm.getValidInteger();

        try {
            dao.delete(slotNumber);
            System.out.println("Inventory item deleted successfully.");
            LOGGER.info("Inventory item deleted in slot: " + slotNumber);

        } catch (DAOException e) {
            System.out.println("Error deleting inventory item.");
            LOGGER.error("Error deleting inventory item: " + e.getMessage());

        }
    }

    private Character getCharacter() {
        Character chara = null;
        System.out.print("Select character to equip:\n");

        try {
            List<Character> characters = characterDAO.obtainAll();

            if (characters.isEmpty()) {
                System.out.println("No characters available.");
                return null;
            }

            for (int i = 0; i < characters.size(); i++) {
                System.out.println((i + 1) + ". " + characters.get(i).getName());
            }

            int choice = cm.getValidIndex(characters.size());
            chara = characters.get(choice - 1);

        } catch (DAOException e) {
            System.out.println("Error obtaining characters.");
            LOGGER.error("Error obtaining characters: " + e.getMessage());
        }

        return chara;
    }


    private Item getItem() {
        Item it = null;
        System.out.print("Select item to equip character:\n");
        
        try {
            List<Item> items = itemDAO.obtainAll();

            if (items.isEmpty()) {
                System.out.println("No items available.");
                return null;
            }

            for (int i = 0; i < items.size(); i++) {
                System.out.println((i + 1) + ". " + items.get(i).getName());
            }

            int choice = cm.getValidIndex(items.size());
            it = items.get(choice - 1);

        } catch (DAOException e) {
            System.out.println("Error obtaining items.");
            LOGGER.error("Error obtaining items: " + e.getMessage());

        }

        return it;
    }
}
