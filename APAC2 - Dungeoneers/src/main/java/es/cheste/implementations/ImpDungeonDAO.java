package es.cheste.implementations;

import es.cheste.exceptions.DAOException;
import es.cheste.classes.DBConnection;

import es.cheste.interfaces.DungeonDAO;
import es.cheste.classes.Dungeon;
import es.cheste.enums.Biome;
import es.cheste.enums.Difficulty;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ImpDungeonDAO implements DungeonDAO {

    DBConnection c = new DBConnection();

    // Final Strings
    private static final String NOT_OBTAINED = "Dungeon not obtained.";
    private static final String LIST_NOT_OBTAINED = "Dungeon list not obtained.";

    // SQL Queries
    private static final String INSERT = "INSERT INTO Dungeon (name, biome, difficulty, floors, hasBoss, pointsToBeat) VALUES (?, ?, ?, ?, ? ,?)";
    private static final String OBTAIN_BY_NAME = "SELECT * FROM Dungeon WHERE name = ?";
    private static final String OBTAIN_HARDEST = "SELECT name FROM Dungeon WHERE pointsToBeat = (SELECT MAX(pointsToBeat) FROM Dungeon)";
    private static final String OBTAIN_ALL = "SELECT * FROM Dungeon ORDER BY name ASC";
    private static final String OBTAIN_ALL_BY_BIOME = "SELECT * FROM Dungeon WHERE biome = ? ORDER BY name ASC";
    private static final String OBTAIN_ALL_BY_DIFFICULTY = "SELECT * FROM Dungeon WHERE difficulty = ? ORDER BY name ASC";
    private static final String UPDATE = "UPDATE dungeon SET name = ?, biome = ?, difficulty = ?, floors = ?, hasBoss = ?," +
                                            " pointsToBeat = ? WHERE name = ?";
    private static final String DELETE = "DELETE FROM Dungeon WHERE name = ?";

    @Override
    public void insert(Dungeon dungeon) throws DAOException {
        try (PreparedStatement ps = c.getConnection().prepareStatement(INSERT)) {

            ps.setString(1, dungeon.getName());
            ps.setString(2, dungeon.getBiome().getBiomeName());
            ps.setString(3, dungeon.getDifficulty().getDifficultyName());
            ps.setInt(4, dungeon.getFloors());
            ps.setBoolean(5, dungeon.isHasBoss());
            ps.setInt(6, dungeon.getPointsToBeat());

            int changedLines = ps.executeUpdate();

            if (changedLines == 0) {
                throw new DAOException("Dungeon insertion failed, no lines affected.", null);
            }

        } catch (SQLException e) {
            throw new DAOException("Dungeon not inserted.", e);
        }

    }

    @Override
    public Dungeon obtainByName(String name) throws DAOException {
        Dungeon dungeon = null;

        try (PreparedStatement ps = c.getConnection().prepareStatement(OBTAIN_BY_NAME)) {

            ps.setString(1, name);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    dungeon = mapDungeon(rs);
                }
            }

        } catch (SQLException e) {
            throw new DAOException(NOT_OBTAINED, e);
        }

        return dungeon;
    }

    @Override
    public Dungeon obtainHardest() throws DAOException {
        Dungeon dungeon = null;

        try (PreparedStatement ps = c.getConnection().prepareStatement(OBTAIN_HARDEST)) {

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    dungeon = mapDungeon(rs);
                }
            }

        } catch (SQLException e) {
            throw new DAOException(NOT_OBTAINED, e);
        }

        return dungeon;
    }

    @Override
    public List<Dungeon> obtainAll() throws DAOException {
        List<Dungeon> dungeons = new ArrayList<>();

        try (PreparedStatement ps = c.getConnection().prepareStatement(OBTAIN_ALL);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Dungeon dungeon = mapDungeon(rs);
                dungeons.add(dungeon);
            }

        } catch (SQLException e) {
            throw new DAOException(LIST_NOT_OBTAINED, e);
        }

        return dungeons;
    }

    @Override
    public List<Dungeon> obtainAllByBiome(Biome biome) throws DAOException {
        List<Dungeon> dungeons = new ArrayList<>();

        try (PreparedStatement ps = c.getConnection().prepareStatement(OBTAIN_ALL_BY_BIOME);
             ResultSet rs = ps.executeQuery()) {

            ps.setString(1, biome.getBiomeName());

            while (rs.next()) {
                Dungeon dungeon = mapDungeon(rs);
                dungeons.add(dungeon);
            }

        } catch (SQLException e) {
            throw new DAOException(LIST_NOT_OBTAINED, e);
        }

        return dungeons;
    }

    @Override
    public List<Dungeon> obtainAllByDifficulty(Difficulty diff) throws DAOException {
        List<Dungeon> dungeons = new ArrayList<>();

        try (PreparedStatement ps = c.getConnection().prepareStatement(OBTAIN_ALL_BY_DIFFICULTY);
             ResultSet rs = ps.executeQuery()) {

            ps.setString(1, diff.getDifficultyName());

            while (rs.next()) {
                Dungeon dungeon = mapDungeon(rs);
                dungeons.add(dungeon);
            }

        } catch (SQLException e) {
            throw new DAOException(LIST_NOT_OBTAINED, e);
        }

        return dungeons;
    }

    @Override
    public void update(Dungeon dungeon, String oldDun) throws DAOException {
        try (PreparedStatement ps = c.getConnection().prepareStatement(UPDATE)) {
            Dungeon oldDungeon = obtainByName(oldDun);

            ps.setString(0, dungeon.getName());
            ps.setString(1, dungeon.getBiome().getBiomeName());
            ps.setString(2, dungeon.getDifficulty().getDifficultyName());
            ps.setInt(3, dungeon.getFloors());
            ps.setBoolean(4, dungeon.isHasBoss());
            ps.setInt(5, dungeon.getPointsToBeat());
            ps.setString(6, oldDungeon.getName());

            int filasAfectadas = ps.executeUpdate();

            if (filasAfectadas == 0) {
                throw new DAOException("Dungeon update failed, no lines affected.", null);

            }

        } catch (SQLException e) {
            throw new DAOException("Dungeon not updated.", e);
        }
    }

    @Override
    public void delete(String name) throws DAOException {
        try (PreparedStatement ps = c.getConnection().prepareStatement(DELETE)) {

            ps.setString(1, name);

            int filasAfectadas = ps.executeUpdate();

            if (filasAfectadas == 0) {
                throw new DAOException("Dungeon deletion failed, no lines affected.", null);

            }

        } catch (SQLException e) {
            throw new DAOException("Dungeon not deleted.", e);
        }
    }

    // Mapping method
    private Dungeon mapDungeon(ResultSet rs) throws SQLException {
        String name = rs.getString("name");
        String biome = rs.getString("biome");
        String difficulty = rs.getString("difficulty");
        int floors = rs.getInt("floors");
        boolean hasBoss = rs.getBoolean("hasBoss");

        return new Dungeon(name, identifyBiome(biome), identifyDifficulty(difficulty), floors, hasBoss);
    }

    // Enum identification method (Biome)
    private Biome identifyBiome(String biome) {
        switch (biome) {
            case "Forest":
                return Biome.FOREST;
            case "Mountain":
                return Biome.MOUNTAIN;
            case "Plains":
                return Biome.PLAINS;
            case "Swamp":
                return Biome.SWAMP;
            case "Tundra":
                return Biome.TUNDRA;
            case "Cave":
                return Biome.CAVE;
            case "City":
                return Biome.CITY;
            default:
                return Biome.DESERT;
        }
    }

    // Enum identification method (Difficulty)
    private Difficulty identifyDifficulty(String diff) {
        switch (diff) {
            case "D":
                return Difficulty.LOW;
            case "C":
                return Difficulty.MODERATE;
            case "B":
                return Difficulty.MEDIUM;
            case "A":
                return Difficulty.HARD;
            case "S":
                return Difficulty.VERY_HARD;
            case "S+":
                return Difficulty.EXTREME;
            case "S++":
                return Difficulty.IMPOSSIBLE;
            default:
                return Difficulty.TRIVIAL;
        }
    }
}