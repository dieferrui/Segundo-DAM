package es.cheste.handlers;

import es.cheste.classes.Party;
import es.cheste.classes.Character;
import es.cheste.exceptions.DAOException;
import es.cheste.implementations.ImpPartyDAO;
import es.cheste.implementations.ImpCharacterDAO;

import java.util.List;
import java.util.Scanner;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class PartyHandler {
    private final ImpPartyDAO dao = new ImpPartyDAO();
    private final ImpCharacterDAO charImpMethod = new ImpCharacterDAO();
    private final Scanner scanner = new Scanner(System.in);
    private static final Logger LOGGER = LogManager.getLogger(PartyHandler.class.getName());

    public void start() {
        int choice;
        do {
            System.out.println("\nParty Management System");
            System.out.println("1. Add Party");
            System.out.println("2. Find Party by Name");
            System.out.println("3. Update Party");
            System.out.println("4. Delete Party");
            System.out.println("5. List All Parties");
            System.out.println("6. Find Strongest Party");
            System.out.println("7. Find Parties by Member");
            System.out.println("8. Get Party Members");
            System.out.println("0. Return to main menu");
            System.out.print("Select an option: ");

            choice = getValidInteger();

            switch (choice) {
                case 1 -> insertParty();
                case 2 -> findPartyByName();
                case 3 -> updateParty();
                case 4 -> deleteParty();
                case 5 -> listAllParties();
                case 6 -> findStrongestParty();
                case 7 -> findPartiesByMember();
                case 8 -> getPartyMembers();
                case 0 -> System.out.println("Returning to main menu...");
                default -> System.out.println("Invalid option, try again.");
            }

        } while (choice != 0);

        scanner.close();
    }

    private void insertParty() {
        try {
            System.out.print("Party Name: ");
            String name = scanner.nextLine();

            Character leader = getCharacter("Party Leader");
            Character striker = getCharacter("Party Striker");
            Character tank = getCharacter("Party Tank");
            Character healer = getCharacter("Party Healer");

            Party party = new Party(name, leader, striker, tank, healer);
            dao.insert(party);
            System.out.println("Party added successfully.");
            LOGGER.info("Party added: " + name);

        } catch (DAOException e) {
            System.out.println("Error inserting party.");
            LOGGER.error("Error inserting party: " + e.getMessage());
        }
    }

    private void findPartyByName() {
        System.out.print("Enter party name: ");
        String name = scanner.nextLine();

        try {
            Party party = dao.obtainByName(name);

            if (party != null) {
                System.out.println("Party found: " + party.getPartyName() + "\n" + party.describe());
            } else {
                System.out.println("Party not found.");
            }

        } catch (DAOException e) {
            System.out.println("Error finding party.");
            LOGGER.error("Error finding party: " + e.getMessage());
        }
    }

    private void updateParty() {
        System.out.print("Enter the name of the party to update: ");
        String oldName = scanner.nextLine();

        try {
            Party oldParty = dao.obtainByName(oldName);

            if (oldParty != null) {
                System.out.println("Updating party: " + oldParty.getPartyName());

                System.out.print("New Name: ");
                String newName = scanner.nextLine();

                Character leader = getCharacter("Party Leader");
                Character striker = getCharacter("Party Striker");
                Character tank = getCharacter("Party Tank");
                Character healer = getCharacter("Party Healer");

                Party newParty = new Party(newName, leader, striker, tank, healer);
                dao.update(newParty, oldName);
                System.out.println("Party updated successfully.");
                LOGGER.info("Party updated: " + newName);

            } else {
                System.out.println("Party not found.");

            }

        } catch (DAOException e) {
            System.out.println("Error updating party.");
            LOGGER.error("Error updating party: " + e.getMessage());

        }
    }

    private void deleteParty() {
        System.out.print("Enter party name to delete: ");
        String name = scanner.nextLine();

        try {
            dao.delete(name);
            System.out.println("Party deleted successfully.");
            LOGGER.info("Party deleted: " + name);

        } catch (DAOException e) {
            System.out.println("Error deleting party.");
            LOGGER.error("Error deleting party: " + e.getMessage());

        }
    }

    private void listAllParties() {
        try {
            List<Party> parties = dao.obtainAll();

            if (parties.isEmpty()) {
                System.out.println("No parties found.");
                return;

            }

            System.out.println("Listing all parties:");

            for (Party party : parties) {
                System.out.println("- " + party.getPartyName());

            }

        } catch (DAOException e) {
            System.out.println("Error listing parties.");
            LOGGER.error("Error listing parties: " + e.getMessage());

        }
    }

    private void findStrongestParty() {
        try {
            Party party = dao.obtainStrongest();

            if (party != null) {
                System.out.println("Strongest Party: " + party.getPartyName() + "\n" + party.describe());
            } else {
                System.out.println("No parties found.");
            }

        } catch (DAOException e) {
            System.out.println("Error finding strongest party.");
            LOGGER.error("Error finding strongest party: " + e.getMessage());
        }
    }

    private void findPartiesByMember() {
        System.out.print("Enter character name to find parties with this member: ");
        String memberName = scanner.nextLine();

        try {
            List<Party> parties = dao.obtainAllThatContainMember(memberName);

            if (parties.isEmpty()) {
                System.out.println("No parties found with member: " + memberName);
                return;

            }

            System.out.println("Parties with member " + memberName + ":");

            for (Party party : parties) {
                System.out.println("- " + party.getPartyName());

            }

        } catch (DAOException e) {
            System.out.println("Error finding parties by member.");
            LOGGER.error("Error finding parties by member: " + e.getMessage());

        }
    }

    private void getPartyMembers() {
        System.out.print("Enter party name to get members: ");
        String partyName = scanner.nextLine();

        try {
            Character[] members = dao.getMembers(partyName);

            if (members.length == 0) {
                System.out.println("No members found for party: " + partyName);
                return;

            }

            System.out.println("Members of party " + partyName + ":");

            for (Character member : members) {
                System.out.println("- " + member.getName());

            }

        } catch (DAOException e) {
            System.out.println("Error getting party members.");
            LOGGER.error("Error getting party members: " + e.getMessage());

        }
    }

    private Character getCharacter(String role) {
        Character chara = null;
        System.out.print("Select character for role (input character name): " + role + "\n");
        
        try {
            List<Character> characters = charImpMethod.obtainAll();

            for (int i = 0; i < characters.size(); i++) {
                System.out.println(i + ". " + characters.get(i).getName());

            }

        } catch (DAOException e) {
            System.out.println("Error obtaining characters.");
            LOGGER.error("Error obtaining characters: " + e.getMessage());

        }

        String selection = scanner.nextLine();

        try {
            chara = charImpMethod.obtainByName(selection);

        } catch (DAOException e) {
            System.out.println("Error obtaining character.");
            LOGGER.error("Error obtaining character: " + e.getMessage());

        }

        return chara;
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