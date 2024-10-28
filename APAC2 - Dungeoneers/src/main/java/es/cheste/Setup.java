package es.cheste;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import es.cheste.classes.DBConnection;
import es.cheste.exceptions.CreateTableException;

public class Setup {

    DBConnection c = new DBConnection();

    public void createTables() {
        try {
            createCharacterTable();
            createPartyTable();
            createDungeonTable();
            createItemTable();
            createInventoryTable();
            createQuestResultTable();

        } catch (CreateTableException e) {
            System.out.println(e.getMessage());

        } catch (SQLException e) {
            System.out.println("Error creating tables.");
            e.printStackTrace();

        }
    }

    private void createCharacterTable() throws CreateTableException {
        String sql = """
                CREATE TABLE IF NOT EXISTS Charactera (
                    name VARCHAR(50) PRIMARY KEY,
                    class VARCHAR(12) NOT NULL,
                    ancestry VARCHAR(12) NOT NULL,
                    dexMod INT,
                    strMod INT,
                    conMod INT,
                    intMod INT,
                    wisMod INT,
                    chaMod INT
                );
                """;

        try (PreparedStatement ps = c.getConnection().prepareStatement(sql)) {
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new CreateTableException("Character table not created.", e);
        }
    }

    private void createPartyTable() throws CreateTableException {
        String sql = """
                CREATE TABLE IF NOT EXISTS Party (
                    name VARCHAR(50) PRIMARY KEY,
                    ptLeader VARCHAR(50),
                    ptStriker VARCHAR(50),
                    ptTank VARCHAR(50),
                    ptHealer VARCHAR(50),
                    ptPower INT,
                    FOREIGN KEY (ptLeader) REFERENCES Charactera(name) ON DELETE RESTRICT,
                    FOREIGN KEY (ptStriker) REFERENCES Charactera(name) ON DELETE RESTRICT,
                    FOREIGN KEY (ptTank) REFERENCES Charactera(name) ON DELETE RESTRICT,
                    FOREIGN KEY (ptHealer) REFERENCES Charactera(name) ON DELETE RESTRICT
                );
                """;

        try (PreparedStatement ps = c.getConnection().prepareStatement(sql)) {
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new CreateTableException("Party table not created.", e);
        }
    }

    private void createDungeonTable() throws CreateTableException {
        String sql = """
                CREATE TABLE IF NOT EXISTS Dungeon (
                    name VARCHAR(50) PRIMARY KEY,
                    biome VARCHAR(12) NOT NULL,
                    difficulty VARCHAR(5) NOT NULL,
                    floors INT,
                    hasBoss BOOLEAN,
                    pointsToBeat INT
                );
                """;

        try (PreparedStatement ps = c.getConnection().prepareStatement(sql)) {
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new CreateTableException("Dungeon table not created.", e);
        }
    }

    private void createItemTable() throws CreateTableException {
        String sql = """
                CREATE TABLE IF NOT EXISTS Item (
                    name VARCHAR(50) PRIMARY KEY,
                    type VARCHAR(20),
                    description VARCHAR(100),
                    rarity VARCHAR(12),
                    value INT,
                    consumable BOOLEAN
                );
                """;

        try (PreparedStatement ps = c.getConnection().prepareStatement(sql)) {
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new CreateTableException("Item table not created.", e);
        }
    }

    private void createInventoryTable() throws CreateTableException {
        String sql = """
                CREATE TABLE IF NOT EXISTS Inventory (
                    slotNumber INT AUTO_INCREMENT PRIMARY KEY,
                    characterName VARCHAR(50) NOT NULL,
                    itemName VARCHAR(50) NOT NULL,
                    quantity INT NOT NULL,
                    FOREIGN KEY (characterName) REFERENCES Charactera(name) ON DELETE CASCADE,
                    FOREIGN KEY (itemName) REFERENCES Item(name) ON DELETE CASCADE
                );
                """;

        try (PreparedStatement ps = c.getConnection().prepareStatement(sql)) {
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new CreateTableException("Inventory table not created.", e);
        }
    }

    private void createQuestResultTable() throws CreateTableException {
        String sql = """
                CREATE TABLE IF NOT EXISTS QuestResult (
                    questId INT PRIMARY KEY,
                    PartyId VARCHAR(50) NOT NULL,
                    DungeonId VARCHAR(50) NOT NULL,
                    report VARCHAR(100) NOT NULL,
                    success BOOLEAN NOT NULL,
                    FOREIGN KEY (PartyId) REFERENCES Party(name) ON DELETE CASCADE,
                    FOREIGN KEY (DungeonId) REFERENCES Dungeon(name) ON DELETE CASCADE
                );
                """;

        try (PreparedStatement ps = c.getConnection().prepareStatement(sql)) {
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new CreateTableException("Inventory table not created.", e);
        }
    }
}

