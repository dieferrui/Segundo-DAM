package es.cheste;

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
            insertTestData();

        } catch (CreateTableException e) {
            System.out.println(e.getMessage());

        } finally {
            c.desconectar();

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
                    FOREIGN KEY (itemName) REFERENCES Item(name) ON DELETE RESTRICT
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

    private void insertTestData() {
        try {
            if (isTableEmpty("Charactera")) insertTestCharacters();
            if (isTableEmpty("Party")) insertTestParties();
            if (isTableEmpty("Dungeon")) insertTestDungeons();
            if (isTableEmpty("Item")) insertTestItems();
            if (isTableEmpty("QuestResult")) insertTestQuestResults();

        } catch (SQLException e) {
            System.out.println("Error inserting test data: " + e.getMessage());

        }
    }
    
    private boolean isTableEmpty(String tableName) throws SQLException {
        String sql = "SELECT COUNT(*) FROM " + tableName;

        try (PreparedStatement ps = c.getConnection().prepareStatement(sql)) {
            var rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) == 0;

            }
        }

        return false;
    }

    private void insertTestCharacters() throws SQLException {
        String sql = """
                INSERT INTO Charactera (name, class, ancestry, dexMod, strMod, conMod, intMod, wisMod, chaMod) 
                VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)
                ON DUPLICATE KEY UPDATE name = name;
                """;
        
        try (PreparedStatement ps = c.getConnection().prepareStatement(sql)) {
            ps.setString(1, "Alastor, the Dark Sun");
            ps.setString(2, "Wizard");
            ps.setString(3, "Human");
            ps.setInt(4, 0);
            ps.setInt(5, 3);
            ps.setInt(6, 3);
            ps.setInt(7, 4);
            ps.setInt(8, 2);
            ps.setInt(9, 2);
            ps.executeUpdate();

            ps.setString(1, "Elyra, the Swift Wind");
            ps.setString(2, "Monk");
            ps.setString(3, "Elf");
            ps.setInt(4, 3);
            ps.setInt(5, 4);
            ps.setInt(6, 3);
            ps.setInt(7, 1);
            ps.setInt(8, 3);
            ps.setInt(9, 1);
            ps.executeUpdate();

            ps.setString(1, "Drem, the Flashing Blade");
            ps.setString(2, "Rogue");
            ps.setString(3, "Human");
            ps.setInt(4, 4);
            ps.setInt(5, 1);
            ps.setInt(6, 2);
            ps.setInt(7, 3);
            ps.setInt(8, 1);
            ps.setInt(9, 3);
            ps.executeUpdate();

            ps.setString(1, "Kalaf, the Radiant Shield");
            ps.setString(2, "Cleric");
            ps.setString(3, "Dwarf");
            ps.setInt(4, 0);
            ps.setInt(5, 3);
            ps.setInt(6, 3);
            ps.setInt(7, 1);
            ps.setInt(8, 4);
            ps.setInt(9, 2);
            ps.executeUpdate();
            
        }
    }

    private void insertTestParties() throws SQLException {
        String sql = """
                INSERT INTO Party (name, ptLeader, ptStriker, ptTank, ptHealer, ptPower) 
                VALUES (?, ?, ?, ?, ?, ?)
                ON DUPLICATE KEY UPDATE name = name;
                """;
        
        try (PreparedStatement ps = c.getConnection().prepareStatement(sql)) {
            ps.setString(1, "Fellers of the Runelord");
            ps.setString(2, "Drem, the Flashing Blade");
            ps.setString(3, "Alastor, the Dark Sun");
            ps.setString(4, "Elyra, the Swift Wind");
            ps.setString(5, "Kalaf, the Radiant Shield");
            ps.setInt(6, 27);
            ps.executeUpdate();

        }
    }

    private void insertTestDungeons() throws SQLException {
        String sql = """
                INSERT INTO Dungeon (name, biome, difficulty, floors, hasBoss, pointsToBeat) 
                VALUES (?, ?, ?, ?, ?, ?)
                ON DUPLICATE KEY UPDATE name = name;
                """;
        
        try (PreparedStatement ps = c.getConnection().prepareStatement(sql)) {
            ps.setString(1, "Throne of Karzoug, the RuneLord of Greed");
            ps.setString(2, "City");
            ps.setString(3, "S+");
            ps.setInt(4, 4);
            ps.setBoolean(5, true);
            ps.setInt(6, 26);
            ps.executeUpdate();

        }
    }

    private void insertTestItems() throws SQLException {
        String sql = """
                INSERT INTO Item (name, type, description, rarity, value, consumable) 
                VALUES (?, ?, ?, ?, ?, ?)
                ON DUPLICATE KEY UPDATE name = name;
                """;
        
        try (PreparedStatement ps = c.getConnection().prepareStatement(sql)) {
            ps.setString(1, "Health Potion");
            ps.setString(2, "Potion");
            ps.setString(3, "Restores health.");
            ps.setString(4, "Common");
            ps.setInt(5, 50);
            ps.setBoolean(6, true);
            ps.executeUpdate();

            ps.setString(1, "Sword of electricity, Lightning");
            ps.setString(2, "Weapon");
            ps.setString(3, "Beautifully engraved with electric runes, this sword is riddled with distinctive use marks.");
            ps.setString(4, "Epic");
            ps.setInt(5, 5000);
            ps.setBoolean(6, true);
            ps.executeUpdate();

        }
    }

    private void insertTestQuestResults() throws SQLException {
        String sql = """
                INSERT INTO QuestResult (questId, PartyId, DungeonId, report, success) 
                VALUES (?, ?, ?, ?, ?)
                ON DUPLICATE KEY UPDATE questId = questId;
                """;
        
        try (PreparedStatement ps = c.getConnection().prepareStatement(sql)) {
            ps.setInt(1, 1);
            ps.setString(2, "Fellers of the Runelord");
            ps.setString(3, "Throne of Karzoug, the RuneLord of Greed");
            ps.setString(4, "The party defeated Karzoug in his extraplanar pocket situated in the city of Xin-Shalast, and prevented his resurrection.");
            ps.setBoolean(5, true);
            ps.executeUpdate();

        }
    }
}
