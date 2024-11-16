package es.cheste.implementations;

import es.cheste.exceptions.DAOException;
import es.cheste.classes.DBConnection;

import es.cheste.interfaces.CharacterDAO;
import es.cheste.classes.Character;
import es.cheste.enums.CharaClass;
import es.cheste.enums.Ancestry;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ImpCharacterDAO implements CharacterDAO {

    DBConnection c = new DBConnection();

    // Cadenas finales
    private static final String NOT_OBTAINED = "Character not obtained.";
    private static final String LIST_NOT_OBTAINED = "Character list not obtained.";
    private static final String STATEMENT_ERROR = "Statement error.";

    // Consultas SQL
    private static final String INSERT = "INSERT INTO Charactera (name, class, ancestry, dexMod, strMod, conMod, " +
            "intMod, wisMod, chaMod) VALUES (?, ?, ?, ?, ? ,? ,? ,? ,?)";
    private static final String OBTAIN_BY_NAME = "SELECT * FROM Charactera WHERE name = ?";
    private static final String OBTAIN_STRONGEST = "SELECT *, (dexMod + strMod + conMod + intMod + wisMod + chaMod) AS totalStrength " +
            "FROM Charactera ORDER BY totalStrength DESC";
    private static final String OBTAIN_ALL = "SELECT * FROM Charactera";
    private static final String OBTAIN_ALL_BY_CLASS = "SELECT * FROM Charactera WHERE class = ?";
    private static final String OBTAIN_ALL_BY_ANCESTRY = "SELECT * FROM Charactera WHERE ancestry = ?";
    private static final String UPDATE = "UPDATE Charactera SET name = ?, class = ?, ancestry = ?, dexMod = ?, strMod = ?," +
            " conMod = ?, intMod = ?, wisMod = ?, chaMod = ? WHERE name = ?";
    private static final String DELETE = "DELETE FROM Charactera WHERE name = ?";

    /**
     * Función para insertar un nuevo personaje en la base de datos.
     *
     * @param character El personaje a insertar.
     * @throws DAOException si ocurre un error durante la inserción.
     */
    @Override
    public void insert(Character character) throws DAOException {
        try (PreparedStatement ps = c.getConnection().prepareStatement(INSERT)) {

            ps.setString(1, character.getName());
            ps.setString(2, character.getChClass().getClassName());
            ps.setString(3, character.getAncestry().getAncestryName());
            ps.setInt(4, character.getDexMod());
            ps.setInt(5, character.getStrMod());
            ps.setInt(6, character.getConMod());
            ps.setInt(7, character.getIntMod());
            ps.setInt(8, character.getWisMod());
            ps.setInt(9, character.getChaMod());

            int changedLines = ps.executeUpdate();

            if (changedLines == 0) {
                throw new DAOException("Character insertion failed, no lines affected.", null);
            }

        } catch (SQLException e) {
            throw new DAOException("Character not inserted.", e);
        }

    }

    /**
     * Función para obtener un personaje por su nombre.
     *
     * @param name El nombre del personaje.
     * @return El personaje obtenido.
     * @throws DAOException si ocurre un error durante la obtención.
     */
    @Override
    public Character obtainByName(String name) throws DAOException {
        Character character = null;

        try (PreparedStatement ps = c.getConnection().prepareStatement(OBTAIN_BY_NAME)) {
            ps.setString(1, name);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    character = mapCharacter(rs);
                }
            }

        } catch (SQLException e) {
            throw new DAOException(NOT_OBTAINED, e);
        }

        return character;
    }

    /**
     * Función para obtener el personaje más fuerte.
     *
     * @return El personaje más fuerte.
     * @throws DAOException si ocurre un error durante la obtención.
     */
    @Override
    public Character obtainStrongest() throws DAOException {
        Character character = null;

        try (PreparedStatement ps = c.getConnection().prepareStatement(OBTAIN_STRONGEST)) {
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    character = mapCharacter(rs);
                }
            }

        } catch (SQLException e) {
            throw new DAOException(NOT_OBTAINED, e);
        }

        return character;
    }

    /**
     * Función para obtener todos los personajes.
     *
     * @return Una lista de todos los personajes.
     * @throws DAOException si ocurre un error durante la obtención.
     */
    @Override
    public List<Character> obtainAll() throws DAOException {
        List<Character> characters = new ArrayList<>();

        try (PreparedStatement ps = c.getConnection().prepareStatement(OBTAIN_ALL);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Character character = mapCharacter(rs);
                characters.add(character);
            }

        } catch (SQLException e) {
            throw new DAOException(LIST_NOT_OBTAINED, e);
        }

        return characters;
    }

    /**
     * Función para obtener todos los personajes por su clase.
     *
     * @param chClass La clase del personaje.
     * @return Una lista de personajes de la clase especificada.
     * @throws DAOException si ocurre un error durante la obtención.
     */
    @Override
    public List<Character> obtainAllByClass(CharaClass chClass) throws DAOException {
        List<Character> characters = new ArrayList<>();

        try (PreparedStatement ps = c.getConnection().prepareStatement(OBTAIN_ALL_BY_CLASS)) {
            ps.setString(1, chClass.getClassName());

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Character character = mapCharacter(rs);
                    characters.add(character);
                }

            } catch (SQLException e) {
                throw new DAOException(LIST_NOT_OBTAINED, e);
            }

        } catch (SQLException e) {
            throw new DAOException(STATEMENT_ERROR, e);
        }

        return characters;
    }

    /**
     * Función para obtener todos los personajes por su ascendencia.
     *
     * @param ancestry La ascendencia del personaje.
     * @return Una lista de personajes de la ascendencia especificada.
     * @throws DAOException si ocurre un error durante la obtención.
     */
    @Override
    public List<Character> obtainAllByAncestry(Ancestry ancestry) throws DAOException {
        List<Character> characters = new ArrayList<>();

        try (PreparedStatement ps = c.getConnection().prepareStatement(OBTAIN_ALL_BY_ANCESTRY)) {
            ps.setString(1, ancestry.getAncestryName());

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Character character = mapCharacter(rs);
                    characters.add(character);
                }

            } catch (SQLException e) {
                throw new DAOException(LIST_NOT_OBTAINED, e);
            }

        } catch (SQLException e) {
            throw new DAOException(LIST_NOT_OBTAINED, e);
        }

        return characters;
    }

    /**
     * Función para actualizar un personaje existente.
     *
     * @param character El personaje actualizado.
     * @param oldChar El nombre del personaje a actualizar.
     * @throws DAOException si ocurre un error durante la actualización.
     */
    @Override
    public void update(Character character, String oldChar) throws DAOException {
        try (PreparedStatement ps = c.getConnection().prepareStatement(UPDATE)) {

            ps.setString(1, character.getName());
            ps.setString(2, character.getChClass().getClassName());
            ps.setString(3, character.getAncestry().getAncestryName());
            ps.setInt(4, character.getDexMod());
            ps.setInt(5, character.getStrMod());
            ps.setInt(6, character.getConMod());
            ps.setInt(7, character.getIntMod());
            ps.setInt(8, character.getWisMod());
            ps.setInt(9, character.getChaMod());
            ps.setString(10, oldChar);

            int changedLines = ps.executeUpdate();

            if (changedLines == 0) {
                throw new DAOException("Character update failed, no lines affected.", null);

            }

        } catch (SQLException e) {
            throw new DAOException("Character not updated.", e);

        }
    }

    /**
     * Función para eliminar un personaje por su nombre.
     *
     * @param name El nombre del personaje a eliminar.
     * @throws DAOException si ocurre un error durante la eliminación.
     */
    @Override
    public void delete(String name) throws DAOException {
        try (PreparedStatement ps = c.getConnection().prepareStatement(DELETE)) {

            ps.setString(1, name);

            int filasAfectadas = ps.executeUpdate();

            if (filasAfectadas == 0) {
                throw new DAOException("Character deletion failed, no lines affected.", null);

            }

        } catch (SQLException e) {
            throw new DAOException("Character not deleted.", e);
        }
    }

    /**
     * Función para mapear un ResultSet a un objeto Character.
     *
     * @param rs El ResultSet de la consulta.
     * @return El objeto Character mapeado.
     * @throws SQLException si ocurre un error durante el mapeo.
     */
    private Character mapCharacter(ResultSet rs) throws SQLException {
        String name = rs.getString("name");
        String chClass = rs.getString("class");
        String ancestry = rs.getString("ancestry");
        int dexMod = rs.getInt("dexMod");
        int strMod = rs.getInt("strMod");
        int conMod = rs.getInt("conMod");
        int intMod = rs.getInt("intMod");
        int wisMod = rs.getInt("wisMod");
        int chaMod = rs.getInt("chaMod");

        return new Character(name, identifyClass(chClass), identifyAncestry(ancestry), dexMod, strMod, conMod, intMod, wisMod, chaMod);
    }

    /**
     * Función para identificar la clase de un personaje a partir de una cadena.
     *
     * @param chClass La cadena que representa la clase.
     * @return La clase identificada.
     */
    private CharaClass identifyClass(String chClass) {
        return switch (chClass) {
            case "Alchemist" -> CharaClass.ALCHEMIST;
            case "Barbarian" -> CharaClass.BARBARIAN;
            case "Bard" -> CharaClass.BARD;
            case "Champion" -> CharaClass.CHAMPION;
            case "Cleric" -> CharaClass.CLERIC;
            case "Druid" -> CharaClass.DRUID;
            case "Wizard" -> CharaClass.WIZARD;
            case "Monk" -> CharaClass.MONK;
            case "Ranger" -> CharaClass.RANGER;
            case "Rogue" -> CharaClass.ROGUE;
            case "Sorcerer" -> CharaClass.SORCERER;
            case "Witch" -> CharaClass.WITCH;
            default -> CharaClass.FIGHTER;
        };
    }

    /**
     * Función para identificar la ascendencia de un personaje a partir de una cadena.
     *
     * @param ancestry La cadena que representa la ascendencia.
     * @return La ascendencia identificada.
     */
    private Ancestry identifyAncestry(String ancestry) {
        return switch (ancestry) {
            case "Dwarf" -> Ancestry.DWARF;
            case "Elf" -> Ancestry.ELF;
            case "Gnome" -> Ancestry.GNOME;
            case "Goblin" -> Ancestry.GOBLIN;
            case "Halfling" -> Ancestry.HALFLING;
            case "Orc" -> Ancestry.ORC;
            case "Leshy" -> Ancestry.LESHY;
            default -> Ancestry.HUMAN;
        };
    }
}