package es.cheste.handlers;

import es.cheste.classes.Item;
import es.cheste.enums.ItemType;
import es.cheste.enums.Rarity;
import es.cheste.exceptions.DAOException;
import es.cheste.implementations.ImpItemDAO;

import java.util.List;
import java.util.Scanner;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class ItemHandler {
    private final ImpItemDAO dao = new ImpItemDAO();
    private final Scanner scanner = new Scanner(System.in);
    private static final Logger LOGGER = LogManager.getLogger(ItemHandler.class.getName());

    public void start() {
        int choice;
        do {
            System.out.println("\nItem Management System");
            System.out.println("1. Add Item");
            System.out.println("2. Find Item by Name");
            System.out.println("3. Find Most Expensive Item");
            System.out.println("4. Update Item");
            System.out.println("5. Delete Item");
            System.out.println("6. List All Items");
            System.out.println("7. List Items by Type");
            System.out.println("8. List Items by Rarity");
            System.out.println("0. Return to main menu");
            System.out.print("Select an option: ");

            choice = getValidInteger();

            switch (choice) {
                case 1 -> insertItem();
                case 2 -> findItemByName();
                case 3 -> findMostExpensiveItem();
                case 4 -> updateItem();
                case 5 -> deleteItem();
                case 6 -> listAllItems();
                case 7 -> listItemsByType();
                case 8 -> listItemsByRarity();
                case 0 -> System.out.println("Returning to main menu...");
                default -> System.out.println("Invalid option, try again.");
            }

        } while (choice != 0);
    }

    private void insertItem() {
        try {
            System.out.print("Item Name: ");
            String name = scanner.nextLine();

            ItemType type = selectType();
            Rarity rarity = selectRarity();

            System.out.print("Description: ");
            String description = scanner.nextLine();

            System.out.print("Value: ");
            int value = getValidInteger();

            boolean consumable = getValidBoolean();

            Item item = new Item(name, type, description, rarity, value, consumable);
            dao.insert(item);
            System.out.println("Item added successfully.");
            LOGGER.info("Item added: " + name);

        } catch (DAOException | IllegalArgumentException e) {
            System.out.println("Error inserting item.");
            LOGGER.error("Error inserting item: " + e.getMessage());

        }
    }

    private void findItemByName() {
        System.out.print("Enter item name: ");
        String name = scanner.nextLine();

        try {
            Item item = dao.obtainByName(name);

            if (item != null) {
                System.out.println("Item found: " + item.getName() + "\n" + item.describe());

            } else {
                System.out.println("Item not found.");

            }
        } catch (DAOException e) {
            System.out.println("Error finding item.");
            LOGGER.error("Error finding item: " + e.getMessage());

        }
    }

    private void findMostExpensiveItem() {
        try {
            Item item = dao.obtainMostExpensive();

            if (item != null) {
                System.out.println("Most Expensive Item: " + item.getName() + " - Value: " + item.getValue());

            } else {
                System.out.println("No items found.");

            }
        } catch (DAOException e) {
            System.out.println("Error finding most expensive item.");
            LOGGER.error("Error finding most expensive item: " + e.getMessage());

        }
    }

    private void updateItem() {
        System.out.print("Enter the name of the item to update: ");
        String oldName = scanner.nextLine();

        try {
            Item oldItem = dao.obtainByName(oldName);

            if (oldItem != null) {
                System.out.print("New Name: ");
                String name = scanner.nextLine();

                ItemType type = selectType();
                Rarity rarity = selectRarity();

                System.out.print("New Description: ");
                String description = scanner.nextLine();

                System.out.print("New Value: ");
                int value = getValidInteger();

                boolean consumable = getValidBoolean();

                Item newItem = new Item(name, type, description, rarity, value, consumable);
                dao.update(newItem, oldName);
                System.out.println("Item updated successfully.");
                LOGGER.info("Item updated: " + name);

            } else {
                System.out.println("Item not found.");

            }

        } catch (DAOException | IllegalArgumentException e) {
            System.out.println("Error updating item.");
            LOGGER.error("Error updating item: " + e.getMessage());

        }
    }

    private void deleteItem() {
        System.out.print("Enter item name to delete: ");
        String name = scanner.nextLine();

        try {
            dao.delete(name);
            System.out.println("Item deleted successfully.");
            LOGGER.info("Item deleted: " + name);

        } catch (DAOException e) {
            System.out.println("Error deleting item.");
            LOGGER.error("Error deleting item: " + e.getMessage());

        }
    }

    private void listAllItems() {
        try {
            List<Item> items = dao.obtainAll();

            if (items.isEmpty()) {
                System.out.println("No items found.");
                return;

            }

            System.out.println("Listing all items:");

            for (Item item : items) {
                System.out.println("- " + item.getName());

            }

        } catch (DAOException e) {
            System.out.println("Error listing items.");
            LOGGER.error("Error listing items: " + e.getMessage());

        }
    }

    private void listItemsByType() {
        ItemType type = selectType();

        try {
            List<Item> items = dao.obtainAllByType(type);

            if (items.isEmpty()) {
                System.out.println("No items found with type: " + type);
                return;

            }

            System.out.println("Items with type " + type + ":");

            for (Item item : items) {
                System.out.println("- " + item.getName());

            }

        } catch (DAOException | IllegalArgumentException e) {
            System.out.println("Error listing items by type.");
            LOGGER.error("Error listing items by type: " + e.getMessage());

        }
    }

    private void listItemsByRarity() {
        Rarity rarity = selectRarity();

        try {
            List<Item> items = dao.obtainAllByRarity(rarity);

            if (items.isEmpty()) {
                System.out.println("No items found with rarity: " + rarity);
                return;

            }

            System.out.println("Items with rarity " + rarity + ":");

            for (Item item : items) {
                System.out.println("- " + item.getName());

            }

        } catch (DAOException | IllegalArgumentException e) {
            System.out.println("Error listing items by rarity.");
            LOGGER.error("Error listing items by rarity: " + e.getMessage());

        }
    }

    private ItemType selectType() {
        System.out.println("Select Item type:");
        ItemType[] types = ItemType.values();

        for (int i = 0; i < types.length; i++) {
            System.out.println((i + 1) + ". " + types[i].name());

        }

        return types[getValidIndex(types.length) - 1];
    }

    private Rarity selectRarity() {
        System.out.println("Select Item rarity:");
        Rarity[] rarities = Rarity.values();

        for (int i = 0; i < rarities.length; i++) {
            System.out.println((i + 1) + ". " + rarities[i].name());

        }

        return rarities[getValidIndex(rarities.length) - 1];
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

    private boolean getValidBoolean() {
        boolean validChoice = false;
        boolean consumable = false;

        while (!validChoice) {
            System.out.print("Is Consumable (true/false): ");
            String input = scanner.nextLine().trim().toLowerCase();
    
            if (input.equals("true")) {
                validChoice = true;
                consumable = true;

            } else if (input.equals("false")) {
                validChoice = true;
                consumable = false;

            } else {
                System.out.println("Invalid input. Please enter 'true' or 'false'.");

            }
        }

        return consumable;
    }
    
}
