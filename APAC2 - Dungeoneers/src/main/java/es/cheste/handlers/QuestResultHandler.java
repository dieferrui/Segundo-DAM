package es.cheste.handlers;

import es.cheste.classes.Party;
import es.cheste.classes.Dungeon;
import es.cheste.classes.QuestResult;
import es.cheste.exceptions.DAOException;
import es.cheste.implementations.ImpQuestResultDAO;
import es.cheste.implementations.ImpPartyDAO;
import es.cheste.implementations.ImpDungeonDAO;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import java.util.List;
import java.util.Scanner;

public class QuestResultHandler {
    private final ImpQuestResultDAO dao = new ImpQuestResultDAO();
    private final ImpPartyDAO partyDAO = new ImpPartyDAO();
    private final ImpDungeonDAO dungeonDAO = new ImpDungeonDAO();
    private final Scanner scanner = new Scanner(System.in);
    private static final Logger LOGGER = LogManager.getLogger(QuestResultHandler.class.getName());

    public void start() {
        int choice;

        do {
            System.out.println("\nQuest Result Management System");
            System.out.println("1. Resolve Quest Result (automatically generates success and basic report)");
            System.out.println("2. Find Quest Result by ID");
            System.out.println("3. List All Quest Results");
            System.out.println("4. List Quest Results by Party");
            System.out.println("5. List Quest Results by Dungeon");
            System.out.println("6. List Cleared Quests");
            System.out.println("7. List Failed Quests");
            System.out.println("8. Update Quest Report");
            System.out.println("9. Delete Quest Result");
            System.out.println("0. Return to main menu");
            System.out.print("Select an option: ");

            choice = getValidInteger();

            switch (choice) {
                case 1 -> tryQuestResult();
                case 2 -> findQuestResultById();
                case 3 -> listAllQuestResults();
                case 4 -> listQuestResultsByParty();
                case 5 -> listQuestResultsByDungeon();
                case 6 -> listClearedQuests();
                case 7 -> listFailedQuests();
                case 8 -> updateQuestResult();
                case 9 -> deleteQuestResult();
                case 0 -> System.out.println("Returning to main menu...");
                default -> System.out.println("Invalid option, try again.");
            }

        } while (choice != 0);
    }

    private void tryQuestResult() {
        try {
            Party party = getParty();
            Dungeon dungeon = getDungeon();

            QuestResult questResult = new QuestResult(party, dungeon);
            dao.insert(questResult);
            System.out.println("Quest result added successfully.");

            LOGGER.info("Quest result added for party " + party.getPartyName() + " in dungeon " + dungeon.getName());

        } catch (DAOException e) {
            System.out.println("Error inserting quest result.");
            LOGGER.error("Error inserting quest result: " + e.getMessage());
        }
    }

    private void findQuestResultById() {
        System.out.print("Enter quest ID: ");
        int questId = getValidInteger();

        try {
            QuestResult questResult = dao.obtainById(questId);

            if (questResult != null) {
                System.out.println("Quest Result for ID " + questId + ": " + questResult.describe());

            } else {
                System.out.println("No quest result found with that ID.");

            }

        } catch (DAOException e) {
            System.out.println("Error finding quest result.");
            LOGGER.error("Error finding quest result: " + e.getMessage());

        }
    }

    private void listAllQuestResults() {
        try {
            List<QuestResult> results = dao.obtainAll();

            if (results.isEmpty()) {
                System.out.println("No quest results found.");
                return;

            }

            System.out.println("All Quest Results:");
            results.forEach(result -> System.out.println("- " + result.getQuestId()));

        } catch (DAOException e) {
            System.out.println("Error listing quest results.");
            LOGGER.error("Error listing quest results: " + e.getMessage());

        }
    }

    private void listQuestResultsByParty() {
        Party party = getParty();

        try {
            List<QuestResult> results = dao.obtainAllByParty(party.getPartyName());

            if (results.isEmpty()) {
                System.out.println("No quest results found for party: " + party.getPartyName());
                return;
            }

            System.out.println("Quest Results for party " + party.getPartyName() + ":");
            results.forEach(result -> System.out.println("- " + result.getQuestId() + " in " + result.getDungeonId()));

        } catch (DAOException e) {
            System.out.println("Error listing quest results by party.");
            LOGGER.error("Error listing quest results by party: " + e.getMessage());

        }
    }

    private void listQuestResultsByDungeon() {
        Dungeon dungeon = getDungeon();

        try {
            List<QuestResult> results = dao.obtainAllByDungeon(dungeon.getName());

            if (results.isEmpty()) {
                System.out.println("No quest results found for dungeon: " + dungeon.getName());
                return;
            }

            System.out.println("Quest Results for dungeon " + dungeon.getName() + ":");
            results.forEach(result -> System.out.println("- " + result.getQuestId() + " by " + result.getPartyId()));

        } catch (DAOException e) {
            System.out.println("Error listing quest results by dungeon.");
            LOGGER.error("Error listing quest results by dungeon: " + e.getMessage());

        }
    }

    private void listClearedQuests() {
        try {
            List<QuestResult> results = dao.obtainAllCleared();

            if (results.isEmpty()) {
                System.out.println("No cleared quests found.");
                return;

            }

            System.out.println("Cleared Quests:");
            results.forEach(result -> System.out.println("- " + result.getQuestId() + " by " + result.getPartyId() + " in " + result.getDungeonId()));

        } catch (DAOException e) {
            System.out.println("Error listing cleared quests.");
            LOGGER.error("Error listing cleared quests: " + e.getMessage());

        }
    }

    private void listFailedQuests() {
        try {
            List<QuestResult> results = dao.obtainAllFailed();

            if (results.isEmpty()) {
                System.out.println("No failed quests found.");
                return;

            }

            System.out.println("Failed Quests:");
            results.forEach(result -> System.out.println("- " + result.getQuestId() + " by " + result.getPartyId() + " in " + result.getDungeonId()));

        } catch (DAOException e) {
            System.out.println("Error listing failed quests.");
            LOGGER.error("Error listing failed quests: " + e.getMessage());

        }
    }

    private void updateQuestResult() {
        System.out.print("Enter quest ID to update: ");
        int questId = getValidInteger();

        try {
            QuestResult existingResult = dao.obtainById(questId);

            if (existingResult != null) {
                System.out.print("Report: ");
                String report = scanner.nextLine();

                Party party = partyDAO.obtainByName(existingResult.getPartyId());
                Dungeon dungeon = dungeonDAO.obtainByName(existingResult.getDungeonId());

                QuestResult updatedResult = new QuestResult(party, dungeon, report);
                dao.update(updatedResult, questId);

                System.out.println("Quest result updated successfully.");
                LOGGER.info("Quest result updated for quest ID: " + updatedResult.getQuestId());

            } else {
                System.out.println("No quest result found with that ID.");

            }

        } catch (DAOException e) {
            System.out.println("Error updating quest result.");
            LOGGER.error("Error updating quest result: " + e.getMessage());

        }
    }

    private void deleteQuestResult() {
        System.out.print("Enter quest ID to delete: ");
        int questId = getValidInteger();

        try {
            dao.delete(questId);
            System.out.println("Quest result deleted successfully.");
            LOGGER.info("Quest result deleted for quest ID: " + questId);

        } catch (DAOException e) {
            System.out.println("Error deleting quest result.");
            LOGGER.error("Error deleting quest result: " + e.getMessage());

        }
    }

    private Party getParty() {
        Party party = null;
        System.out.print("Select party to embark on quest:\n");
        
        try {
            List<Party> parties = partyDAO.obtainAll();

            for (int i = 0; i < parties.size(); i++) {
                System.out.println(i + ". " + parties.get(i).getPartyName());

            }

        } catch (DAOException e) {
            System.out.println("Error obtaining parties.");
            LOGGER.error("Error obtaining parties: " + e.getMessage());

        }

        String selection = scanner.nextLine();

        try {
            party = partyDAO.obtainByName(selection);

        } catch (DAOException e) {
            System.out.println("Error obtaining party.");
            LOGGER.error("Error obtaining party: " + e.getMessage());

        }

        return party;
    }

    private Dungeon getDungeon() {
        Dungeon dungeon = null;
        System.out.print("Select dungeon to send party to:\n");
        
        try {
            List<Dungeon> dungeons = dungeonDAO.obtainAll();

            for (int i = 0; i < dungeons.size(); i++) {
                System.out.println(i + ". " + dungeons.get(i).getName());

            }

        } catch (DAOException e) {
            System.out.println("Error obtaining dungeons.");
            LOGGER.error("Error obtaining dungeons: " + e.getMessage());

        }

        String selection = scanner.nextLine();

        try {
            dungeon = dungeonDAO.obtainByName(selection);

        } catch (DAOException e) {
            System.out.println("Error obtaining dungeon.");
            LOGGER.error("Error obtaining dungeon: " + e.getMessage());

        }

        return dungeon;
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
