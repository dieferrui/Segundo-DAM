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

    // Cadenas finales
    private static final String NOT_OBTAINED = "Dungeon not obtained.";
    private static final String LIST_NOT_OBTAINED = "Dungeon list not obtained.";
    private static final String STATEMENT_ERROR = "Statement error.";

    // Consultas SQL
    private static final String INSERT = "INSERT INTO Dungeon (name, biome, difficulty, floors, hasBoss, pointsToBeat) VALUES (?, ?, ?, ?, ? ,?)";
    private static final String OBTAIN_BY_NAME = "SELECT * FROM Dungeon WHERE name = ?";
    private static final String OBTAIN_HARDEST = "SELECT * FROM Dungeon WHERE pointsToBeat = (SELECT MAX(pointsToBeat) FROM Dungeon)";
    private static final String OBTAIN_ALL = "SELECT * FROM Dungeon";
    private static final String OBTAIN_ALL_BY_BIOME = "SELECT * FROM Dungeon WHERE biome = ?";
    private static final String OBTAIN_ALL_BY_DIFFICULTY = "SELECT * FROM Dungeon WHERE difficulty = ?";
    private static final String UPDATE = "UPDATE Dungeon SET name = ?, biome = ?, difficulty = ?, floors = ?, hasBoss = ?," +
            " pointsToBeat = ? WHERE name = ?";
    private static final String DELETE = "DELETE FROM Dungeon WHERE name = ?";

    /**
     * Función para insertar una nueva mazmorra en la base de datos.
     *
     * @param dungeon La mazmorra a insertar.
     * @throws DAOException si ocurre un error durante la inserción.
     */
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

    /**
     * Función para obtener una mazmorra por su nombre.
     *
     * @param name El nombre de la mazmorra.
     * @return La mazmorra obtenida.
     * @throws DAOException si ocurre un error durante la obtención.
     */
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

    /**
     * Función para obtener la mazmorra más difícil.
     *
     * @return La mazmorra más difícil.
     * @throws DAOException si ocurre un error durante la obtención.
     */
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

    /**
     * Función para obtener todas las mazmorras.
     *
     * @return Una lista de todas las mazmorras.
     * @throws DAOException si ocurre un error durante la obtención.
     */
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

    /**
     * Función para obtener todas las mazmorras por su bioma.
     *
     * @param biome El bioma de la mazmorra.
     * @return Una lista de mazmorras del bioma especificado.
     * @throws DAOException si ocurre un error durante la obtención.
     */
    @Override
    public List<Dungeon> obtainAllByBiome(Biome biome) throws DAOException {
        List<Dungeon> dungeons = new ArrayList<>();

        try (PreparedStatement ps = c.getConnection().prepareStatement(OBTAIN_ALL_BY_BIOME)) {
            ps.setString(1, biome.getBiomeName());

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Dungeon dungeon = mapDungeon(rs);
                    dungeons.add(dungeon);
                }

            } catch (SQLException e) {
                throw new DAOException(LIST_NOT_OBTAINED, e);
            }

        } catch (SQLException e) {
            throw new DAOException(STATEMENT_ERROR, e);
        }

        return dungeons;
    }

    /**
     * Función para obtener todas las mazmorras por su dificultad.
     *
     * @param diff La dificultad de la mazmorra.
     * @return Una lista de mazmorras de la dificultad especificada.
     * @throws DAOException si ocurre un error durante la obtención.
     */
    @Override
    public List<Dungeon> obtainAllByDifficulty(Difficulty diff) throws DAOException {
        List<Dungeon> dungeons = new ArrayList<>();

        try (PreparedStatement ps = c.getConnection().prepareStatement(OBTAIN_ALL_BY_DIFFICULTY)) {
            ps.setString(1, diff.getDifficultyName());

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Dungeon dungeon = mapDungeon(rs);
                    dungeons.add(dungeon);
                }

            } catch (SQLException e) {
                throw new DAOException(LIST_NOT_OBTAINED, e);
            }

        } catch (SQLException e) {
            throw new DAOException(STATEMENT_ERROR, e);
        }

        return dungeons;
    }

    /**
     * Función para actualizar una mazmorra existente.
     *
     * @param dungeon La mazmorra actualizada.
     * @param oldDun El nombre de la mazmorra a actualizar.
     * @throws DAOException si ocurre un error durante la actualización.
     */
    @Override
    public void update(Dungeon dungeon, String oldDun) throws DAOException {
        try (PreparedStatement ps = c.getConnection().prepareStatement(UPDATE)) {

            ps.setString(0, dungeon.getName());
            ps.setString(1, dungeon.getBiome().getBiomeName());
            ps.setString(2, dungeon.getDifficulty().getDifficultyName());
            ps.setInt(3, dungeon.getFloors());
            ps.setBoolean(4, dungeon.isHasBoss());
            ps.setInt(5, dungeon.getPointsToBeat());
            ps.setString(6, oldDun);

            int filasAfectadas = ps.executeUpdate();

            if (filasAfectadas == 0) {
                throw new DAOException("Dungeon update failed, no lines affected.", null);

            }

        } catch (SQLException e) {
            throw new DAOException("Dungeon not updated.", e);
        }
    }

    /**
     * Función para eliminar una mazmorra por su nombre.
     *
     * @param name El nombre de la mazmorra a eliminar.
     * @throws DAOException si ocurre un error durante la eliminación.
     */
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

    /**
     * Función para mapear un ResultSet a un objeto Dungeon.
     *
     * @param rs El ResultSet de la consulta.
     * @return El objeto Dungeon mapeado.
     * @throws SQLException si ocurre un error durante el mapeo.
     */
    private Dungeon mapDungeon(ResultSet rs) throws SQLException {
        String name = rs.getString("name");
        String biome = rs.getString("biome");
        String difficulty = rs.getString("difficulty");
        int floors = rs.getInt("floors");
        boolean hasBoss = rs.getBoolean("hasBoss");

        return new Dungeon(name, identifyBiome(biome), identifyDifficulty(difficulty), floors, hasBoss);
    }

    /**
     * Función para identificar el bioma de una mazmorra a partir de una cadena.
     *
     * @param biome La cadena que representa el bioma.
     * @return El bioma identificado.
     */
    private Biome identifyBiome(String biome) {
        return switch (biome) {
            case "Forest" -> Biome.FOREST;
            case "Mountain" -> Biome.MOUNTAIN;
            case "Plains" -> Biome.PLAINS;
            case "Swamp" -> Biome.SWAMP;
            case "Tundra" -> Biome.TUNDRA;
            case "Cave" -> Biome.CAVE;
            case "City" -> Biome.CITY;
            default -> Biome.DESERT;
        };
    }

    /**
     * Función para identificar la dificultad de una mazmorra a partir de una cadena.
     *
     * @param diff La cadena que representa la dificultad.
     * @return La dificultad identificada.
     */
    private Difficulty identifyDifficulty(String diff) {
        return switch (diff) {
            case "D" -> Difficulty.LOW;
            case "C" -> Difficulty.MODERATE;
            case "B" -> Difficulty.MEDIUM;
            case "A" -> Difficulty.HARD;
            case "S" -> Difficulty.VERY_HARD;
            case "S+" -> Difficulty.EXTREME;
            case "S++" -> Difficulty.IMPOSSIBLE;
            default -> Difficulty.TRIVIAL;
        };
    }
}