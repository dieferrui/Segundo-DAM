package es.cheste;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import es.cheste.classes.*;
import es.cheste.classes.Character;
import es.cheste.exceptions.CreateTableException;
import es.cheste.enums.*;

public class Setup {

    private final Character alastor = new Character("Alastor, the Dark Sun", CharaClass.WIZARD, Ancestry.HUMAN, 0, 3, 3, 4, 2, 2);
    private final Character elyra = new Character("Elyra, the Swift Wind", CharaClass.MONK, Ancestry.ELF, 3, 4, 3, 1, 3, 1);
    private final Character drem = new Character("Drem, the Flashing Blade", CharaClass.ROGUE, Ancestry.HUMAN, 4, 1, 2, 3, 1, 3);
    private final Character kalaf = new Character("Kalaf, the Radiant Shield", CharaClass.CLERIC, Ancestry.DWARF, 0, 3, 3, 1, 4, 2);

    private final Party fellers = new Party("Fellers of the Runelord", drem, alastor, elyra, kalaf);

    private final Dungeon throne = new Dungeon("Throne of Karzoug, the Runelord of Greed", Biome.CITY, Difficulty.EXTREME, 4, true);

    private final Item potion = new Item("Health Potion", ItemType.POTION, "Restores health.", Rarity.COMMON, 50, true);
    private final Item lightningSword = new Item("Sword of electricity, Lightning", ItemType.WEAPON, "Beautifully engraved with electric runes, this sword is riddled with distinctive use marks.", Rarity.EPIC, 5000, false);

    private final QuestResult karzougDefeat = new QuestResult(fellers, throne, "The party defeated Karzoug in a planar pocket located in Xin-Shalast, preventing his resurrection.", 1);

    DBConnection c = new DBConnection();

    /**
     * Función para crear todas las tablas necesarias en la base de datos.
     */
    public void createTables() {
        try {
            createCharacterTable();
            createPartyTable();
            createDungeonTable();
            createItemTable();
            createInventoryTable();
            createQuestResultTable();
            insertTestData();
            createStoredProcedures();

        } catch (CreateTableException e) {
            System.out.println(e.getMessage());

        } finally {
            c.desconectar();

        }
    }

    /**
     * Función para crear la tabla de personajes en la base de datos.
     *
     * @throws CreateTableException si ocurre un error durante la creación de la tabla.
     */
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

    /**
     * Función para crear la tabla de grupos en la base de datos.
     *
     * @throws CreateTableException si ocurre un error durante la creación de la tabla.
     */
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

    /**
     * Función para crear la tabla de mazmorras en la base de datos.
     *
     * @throws CreateTableException si ocurre un error durante la creación de la tabla.
     */
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

    /**
     * Función para crear la tabla de ítems en la base de datos.
     *
     * @throws CreateTableException si ocurre un error durante la creación de la tabla.
     */
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

    /**
     * Función para crear la tabla de inventarios en la base de datos.
     *
     * @throws CreateTableException si ocurre un error durante la creación de la tabla.
     */
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

    /**
     * Función para crear la tabla de resultados de misiones en la base de datos.
     *
     * @throws CreateTableException si ocurre un error durante la creación de la tabla.
     */
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

    /**
     * Función para insertar datos de prueba en las tablas.
     */
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

    /**
     * Función para verificar si una tabla está vacía.
     *
     * @param tableName El nombre de la tabla.
     * @return true si la tabla está vacía, false en caso contrario.
     * @throws SQLException si ocurre un error durante la verificación.
     */
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

    /**
     * Función para insertar personajes de prueba en la tabla de personajes.
     *
     * @throws SQLException si ocurre un error durante la inserción.
     */
    private void insertTestCharacters() throws SQLException {
        String sql = """
                INSERT INTO Charactera (name, class, ancestry, dexMod, strMod, conMod, intMod, wisMod, chaMod)
                VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)
                ON DUPLICATE KEY UPDATE name = name;
                """;

        try (PreparedStatement ps = c.getConnection().prepareStatement(sql)) {
            ps.setString(1, alastor.getName());
            ps.setString(2, alastor.getChClass().getClassName());
            ps.setString(3, alastor.getAncestry().getAncestryName());
            ps.setInt(4, 0);
            ps.setInt(5, 3);
            ps.setInt(6, 3);
            ps.setInt(7, 4);
            ps.setInt(8, 2);
            ps.setInt(9, 2);
            ps.executeUpdate();

            ps.setString(1, elyra.getName());
            ps.setString(2, elyra.getChClass().getClassName());
            ps.setString(3, elyra.getAncestry().getAncestryName());
            ps.setInt(4, 3);
            ps.setInt(5, 4);
            ps.setInt(6, 3);
            ps.setInt(7, 1);
            ps.setInt(8, 3);
            ps.setInt(9, 1);
            ps.executeUpdate();

            ps.setString(1, drem.getName());
            ps.setString(2, drem.getChClass().getClassName());
            ps.setString(3, drem.getAncestry().getAncestryName());
            ps.setInt(4, 4);
            ps.setInt(5, 1);
            ps.setInt(6, 2);
            ps.setInt(7, 3);
            ps.setInt(8, 1);
            ps.setInt(9, 3);
            ps.executeUpdate();

            ps.setString(1, kalaf.getName());
            ps.setString(2, kalaf.getChClass().getClassName());
            ps.setString(3, kalaf.getAncestry().getAncestryName());
            ps.setInt(4, 0);
            ps.setInt(5, 3);
            ps.setInt(6, 3);
            ps.setInt(7, 1);
            ps.setInt(8, 4);
            ps.setInt(9, 2);
            ps.executeUpdate();

        }
    }

    /**
     * Función para insertar grupos de prueba en la tabla de grupos.
     *
     * @throws SQLException si ocurre un error durante la inserción.
     */
    private void insertTestParties() throws SQLException {
        String sql = """
                INSERT INTO Party (name, ptLeader, ptStriker, ptTank, ptHealer, ptPower)
                VALUES (?, ?, ?, ?, ?, ?)
                ON DUPLICATE KEY UPDATE name = name;
                """;

        try (PreparedStatement ps = c.getConnection().prepareStatement(sql)) {
            ps.setString(1, fellers.getPartyName());
            ps.setString(2, fellers.getPtLeader().getName());
            ps.setString(3, fellers.getPtStriker().getName());
            ps.setString(4, fellers.getPtTank().getName());
            ps.setString(5, fellers.getPtHealer().getName());
            ps.setInt(6, fellers.getPtPower());
            ps.executeUpdate();

        }
    }

    /**
     * Función para insertar mazmorras de prueba en la tabla de mazmorras.
     *
     * @throws SQLException si ocurre un error durante la inserción.
     */
    private void insertTestDungeons() throws SQLException {
        String sql = """
                INSERT INTO Dungeon (name, biome, difficulty, floors, hasBoss, pointsToBeat)
                VALUES (?, ?, ?, ?, ?, ?)
                ON DUPLICATE KEY UPDATE name = name;
                """;

        try (PreparedStatement ps = c.getConnection().prepareStatement(sql)) {
            ps.setString(1, throne.getName());
            ps.setString(2, throne.getBiome().getBiomeName());
            ps.setString(3, throne.getDifficulty().getDifficultyName());
            ps.setInt(4, throne.getFloors());
            ps.setBoolean(5, throne.isHasBoss());
            ps.setInt(6, throne.getPointsToBeat());
            ps.executeUpdate();

        }
    }

    /**
     * Función para insertar ítems de prueba en la tabla de ítems.
     *
     * @throws SQLException si ocurre un error durante la inserción.
     */
    private void insertTestItems() throws SQLException {
        String sql = """
                INSERT INTO Item (name, type, description, rarity, value, consumable)
                VALUES (?, ?, ?, ?, ?, ?)
                ON DUPLICATE KEY UPDATE name = name;
                """;

        try (PreparedStatement ps = c.getConnection().prepareStatement(sql)) {
            ps.setString(1, potion.getName());
            ps.setString(2, potion.getType().getType());
            ps.setString(3, potion.getDescription());
            ps.setString(4, potion.getRarity().getRarity());
            ps.setInt(5, potion.getValue());
            ps.setBoolean(6, potion.isConsumable());
            ps.executeUpdate();

            ps.setString(1, lightningSword.getName());
            ps.setString(2, lightningSword.getType().getType());
            ps.setString(3, lightningSword.getDescription());
            ps.setString(4, lightningSword.getRarity().getRarity());
            ps.setInt(5, lightningSword.getValue());
            ps.setBoolean(6, lightningSword.isConsumable());
            ps.executeUpdate();

        }
    }

    /**
     * Función para insertar resultados de misiones de prueba en la tabla de resultados de misiones.
     *
     * @throws SQLException si ocurre un error durante la inserción.
     */
    private void insertTestQuestResults() throws SQLException {
        String sql = """
                INSERT INTO QuestResult (questId, PartyId, DungeonId, report, success)
                VALUES (?, ?, ?, ?, ?)
                ON DUPLICATE KEY UPDATE questId = questId;
                """;

        try (PreparedStatement ps = c.getConnection().prepareStatement(sql)) {
            ps.setInt(1, 1);
            ps.setString(2, karzougDefeat.getPartyId());
            ps.setString(3, karzougDefeat.getDungeonId());
            ps.setString(4, karzougDefeat.getReport());
            ps.setBoolean(5, karzougDefeat.isSuccess());
            ps.executeUpdate();

        }
    }

    /**
     * Función para crear los procedimientos almacenados necesarios en la base de datos.
     *
     * @throws CreateTableException si ocurre un error durante la creación de los procedimientos almacenados.
     */
    private void createStoredProcedures() throws CreateTableException {
        try {
            createGetPartyMembers();
            createGetQuestResultsProcedure();

        } catch (SQLException e) {
            throw new CreateTableException("Error creating stored procedures.", e);

        }
    }

    /**
     * Función para crear el procedimiento almacenado GetPartyMembers.
     *
     * @throws SQLException si ocurre un error durante la creación del procedimiento almacenado.
     */
    private void createGetPartyMembers() throws SQLException {
        String dropSql = "DROP PROCEDURE IF EXISTS GetPartyMembers;";

        try (PreparedStatement dropPs = c.getConnection().prepareStatement(dropSql)) {
            dropPs.executeUpdate();
        }

        String sql = """
                CREATE PROCEDURE GetPartyMembers(IN partyName VARCHAR(50))
                BEGIN
                    SELECT c.name, c.class, c.ancestry,
                        c.dexMod, c.strMod, c.conMod, c.intMod, c.wisMod, c.chaMod
                    FROM Party p
                    JOIN Charactera c ON c.name IN (p.ptLeader, p.ptStriker, p.ptTank, p.ptHealer)
                    WHERE p.name = partyName;
                END;
                """;

        try (PreparedStatement ps = c.getConnection().prepareStatement(sql)) {
            ps.executeUpdate();

        }
    }

    /**
     * Función para crear el procedimiento almacenado GetQuestResultById.
     *
     * @throws SQLException si ocurre un error durante la creación del procedimiento almacenado.
     */
    private void createGetQuestResultsProcedure() throws SQLException {
        String dropSql = "DROP PROCEDURE IF EXISTS GetQuestResultById;";

        try (PreparedStatement dropPs = c.getConnection().prepareStatement(dropSql)) {
            dropPs.executeUpdate();
        }

        String sql = """
                CREATE PROCEDURE GetQuestResultById(IN questId INT)
                BEGIN
                    SELECT qr.questId,
                           d.name AS dungeonName,
                           d.difficulty AS dungeonDifficulty,
                           d.pointsToBeat AS dungeonPoints,
                           p.name AS partyName,
                           p.ptPower
                    FROM QuestResult qr
                    LEFT JOIN Dungeon d ON qr.DungeonId = d.name
                    LEFT JOIN Party p ON qr.PartyId = p.name
                    WHERE qr.questId = questId;
                END;
                """;

        try (PreparedStatement ps = c.getConnection().prepareStatement(sql)) {
            ps.executeUpdate();
        }
    }
}