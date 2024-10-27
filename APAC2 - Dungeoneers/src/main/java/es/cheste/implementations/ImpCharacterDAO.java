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

    // Final Strings
    private static final String NOT_OBTAINED = "Character not obtained.";
    private static final String LIST_NOT_OBTAINED = "Character list not obtained.";

    // SQL Queries
    private static final String INSERT = "INSERT INTO Character (name, class, ancestry, dexMod, strMod, conMod, " +
                                            "intMod, wisMod, chaMod) VALUES (?, ?, ?, ?, ? ,? ,? ,? ,?)";
    private static final String OBTAIN_BY_NAME = "SELECT * FROM Character WHERE name = ?";
    private static final String OBTAIN_STRONGEST = "SELECT name, (dexMod + strMod + conMod + intMod + wisMod + chaMod)" +
                                                    " AS totalStrength FROM Character ORDER BY totalStrength DESC, name ASC LIMIT 1";
    private static final String OBTAIN_ALL = "SELECT * FROM Character ORDER BY name ASC";
    private static final String OBTAIN_ALL_BY_CLASS = "SELECT * FROM Character WHERE class = ? ORDER BY name ASC";
    private static final String OBTAIN_ALL_BY_ANCESTRY = "SELECT * FROM Character WHERE ancestry = ? ORDER BY name ASC";
    private static final String UPDATE = "UPDATE Character SET name = ?, class = ?, ancestry = ?, dexMod = ?, strMod = ?," +
                                            " conMod = ?, intMod = ?, wisMod = ?, chaMod = ? WHERE name = ?";
    private static final String DELETE = "DELETE FROM Character WHERE name = ?";

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

    @Override
    public List<Character> obtainAllByClass(CharaClass chClass) throws DAOException {
        List<Character> characters = new ArrayList<>();

        try (PreparedStatement ps = c.getConnection().prepareStatement(OBTAIN_ALL_BY_CLASS);
             ResultSet rs = ps.executeQuery()) {

            ps.setString(1, chClass.getClassName());

            while (rs.next()) {
                Character character = mapCharacter(rs);
                characters.add(character);
            }

        } catch (SQLException e) {
            throw new DAOException(LIST_NOT_OBTAINED, e);
        }

        return characters;
    }

    @Override
    public List<Character> obtainAllByAncestry(Ancestry ancestry) throws DAOException {
        List<Character> characters = new ArrayList<>();

        try (PreparedStatement ps = c.getConnection().prepareStatement(OBTAIN_ALL_BY_ANCESTRY);
             ResultSet rs = ps.executeQuery()) {

            ps.setString(1, ancestry.getAncestryName());

            while (rs.next()) {
                Character character = mapCharacter(rs);
                characters.add(character);
            }

        } catch (SQLException e) {
            throw new DAOException(LIST_NOT_OBTAINED, e);
        }

        return characters;
    }

    @Override
    public void update(Character character) throws DAOException {
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
            ps.setString(10, character.getName());

            int filasAfectadas = ps.executeUpdate();

            if (filasAfectadas == 0) {
                throw new DAOException("Character update failed, no lines affected.", null);

            }

        } catch (SQLException e) {
            throw new DAOException("Character not updated.", e);
        }
    }

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

    // Mapping method
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

    // Enum identification method (Class)
    private CharaClass identifyClass(String chClass) {
        switch (chClass) {
            case "Alchemist":
                return CharaClass.ALCHEMIST;
            case "Barbarian":
                return CharaClass.BARBARIAN;
            case "Bard":
                return CharaClass.BARD;
            case "Champion":
                return CharaClass.CHAMPION;
            case "Cleric":
                return CharaClass.CLERIC;
            case "Druid":
                return CharaClass.DRUID;
            case "Wizard":
                return CharaClass.WIZARD;
            case "Monk":
                return CharaClass.MONK;
            case "Ranger":
                return CharaClass.RANGER;
            case "Rogue":
                return CharaClass.ROGUE;
            case "Sorcerer":
                return CharaClass.SORCERER;
            case "Witch":
                return CharaClass.WITCH;
            default:
                return CharaClass.FIGHTER;
        }
    }

    // Enum identification method (Ancestry)
    private Ancestry identifyAncestry(String ancestry) {
        switch (ancestry) {
            case "Dwarf":
                return Ancestry.DWARF;
            case "Elf":
                return Ancestry.ELF;
            case "Gnome":
                return Ancestry.GNOME;
            case "Goblin":
                return Ancestry.GOBLIN;
            case "Halfling":
                return Ancestry.HALFLING;
            case "Orc":
                return Ancestry.ORC;
            case "Leshy":
                return Ancestry.LESHY;
            default:
                return Ancestry.HUMAN;
        }
    }
}
