package es.cheste.handlers;

import es.cheste.CommonMethod;
import es.cheste.classes.Party;
import es.cheste.classes.Character;
import es.cheste.exceptions.DAOException;
import es.cheste.implementations.ImpPartyDAO;
import es.cheste.implementations.ImpCharacterDAO;

import java.util.List;
import java.util.Scanner;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

/**
 * Clase que maneja las operaciones relacionadas con los grupos de aventureros.
 */
public class PartyHandler {
    private final ImpPartyDAO dao = new ImpPartyDAO();
    private final ImpCharacterDAO charImpMethod = new ImpCharacterDAO();
    private final CommonMethod cm = new CommonMethod();
    private final Scanner scanner = new Scanner(System.in);
    private static final Logger LOGGER = LogManager.getLogger(PartyHandler.class.getName());

    /**
     * Función que inicia el sistema de gestión de grupos de aventureros.
     */
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

            choice = cm.getValidInteger();

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
    }

    /**
     * Función para insertar un nuevo grupo de aventureros.
     */
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

    /**
     * Función para encontrar un grupo por su nombre.
     */
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

    /**
     * Función para actualizar un grupo existente.
     */
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

    /**
     * Función para eliminar un grupo por su nombre.
     */
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

    /**
     * Función para listar todos los grupos.
     */
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

    /**
     * Función para encontrar el grupo más fuerte.
     */
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

    /**
     * Función para encontrar los grupos que contengan un miembro específico.
     */
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

    /**
     * Función para obtener los miembros de un grupo.
     */
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

    /**
     * Función para obtener un personaje.
     *
     * @param role El rol del personaje en el grupo.
     * @return El personaje seleccionado.
     */
    private Character getCharacter(String role) {
        Character chara = null;
        System.out.print("Select character for role" + role + ":\n");

        try {
            List<Character> characters = charImpMethod.obtainAll();

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
}