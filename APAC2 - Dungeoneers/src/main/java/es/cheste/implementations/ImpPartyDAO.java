package es.cheste.implementations;

import es.cheste.exceptions.DAOException;
import es.cheste.classes.DBConnection;
import es.cheste.classes.Party;
import es.cheste.enums.Ancestry;
import es.cheste.enums.CharaClass;
import es.cheste.interfaces.PartyDAO;
import es.cheste.classes.Character;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ImpPartyDAO implements PartyDAO {

    DBConnection c = new DBConnection();
    ImpCharacterDAO charImpMethod = new ImpCharacterDAO();

    // Cadenas finales
    private static final String NOT_OBTAINED = "Party not obtained.";
    private static final String LIST_NOT_OBTAINED = "Party list not obtained.";

    // Consultas SQL
    private static final String INSERT = "INSERT INTO Party (name, ptLeader, ptStriker, ptTank, ptHealer, ptPower) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String OBTAIN_BY_NAME = "SELECT * FROM Party WHERE name = ?";
    private static final String OBTAIN_STRONGEST = "SELECT * FROM Party ORDER BY ptPower DESC LIMIT 1";
    private static final String OBTAIN_ALL = "SELECT * FROM Party";
    private static final String OBTAIN_ALL_THAT_CONTAIN_MEMBER = "SELECT * FROM Party WHERE ptLeader = ? OR ptStriker = ? OR ptTank = ? OR ptHealer = ?";
    private static final String UPDATE = "UPDATE Party SET name = ?, ptLeader = ?, ptStriker = ?, ptTank = ?, ptHealer = ?, ptPower = ? WHERE name = ?";
    private static final String DELETE = "DELETE FROM Party WHERE name = ?";

    /**
     * Función para insertar un nuevo grupo en la base de datos.
     *
     * @param party El grupo a insertar.
     * @throws DAOException si ocurre un error durante la inserción.
     */
    @Override
    public void insert(Party party) throws DAOException {
        try (PreparedStatement ps = c.getConnection().prepareStatement(INSERT)) {
            ps.setString(1, party.getPartyName());
            ps.setString(2, party.getPtLeader().getName());
            ps.setString(3, party.getPtStriker().getName());
            ps.setString(4, party.getPtTank().getName());
            ps.setString(5, party.getPtHealer().getName());
            ps.setInt(6, party.getPtPower());

            int affectedRows = ps.executeUpdate();

            if (affectedRows == 0) {
                throw new DAOException("Party insertion failed, no lines affected.", null);
            }

        } catch (SQLException e) {
            throw new DAOException("Party not inserted.", e);
        }
    }

    /**
     * Función para obtener un grupo por su nombre.
     *
     * @param partyName El nombre del grupo.
     * @return El grupo obtenido.
     * @throws DAOException si ocurre un error durante la obtención.
     */
    @Override
    public Party obtainByName(String partyName) throws DAOException {
        Party party = null;

        try (PreparedStatement ps = c.getConnection().prepareStatement(OBTAIN_BY_NAME)) {
            ps.setString(1, partyName);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    party = mapParty(rs);
                }
            }

        } catch (SQLException e) {
            throw new DAOException(NOT_OBTAINED, e);
        }

        return party;
    }

    /**
     * Función para obtener los miembros de un grupo.
     *
     * @param partyName El nombre del grupo.
     * @return Un arreglo de los miembros del grupo.
     * @throws DAOException si ocurre un error durante la obtención.
     */
    @Override
    public Character[] getMembers(String partyName) throws DAOException {
        Character[] members = new Character[4];

        try (CallableStatement stmt = c.getConnection().prepareCall("{CALL GetPartyMembers(?)}")) {
            stmt.setString(1, partyName);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String name = rs.getString("name");
                String className = rs.getString("class");
                String ancestry = rs.getString("ancestry");
                int dexMod = rs.getInt("dexMod");
                int strMod = rs.getInt("strMod");
                int conMod = rs.getInt("conMod");
                int intMod = rs.getInt("intMod");
                int wisMod = rs.getInt("wisMod");
                int chaMod = rs.getInt("chaMod");

                CharaClass chClass = identifyClass(className);
                Ancestry anc = identifyAncestry(ancestry);

                Character character = new Character(name, chClass, anc, dexMod, strMod, conMod, intMod, wisMod, chaMod);
                members[rs.getRow() - 1] = character;
            }

        } catch (SQLException e) {
            throw new DAOException("Error obtaining party members.", e);

        }

        return members;
    }

    /**
     * Función para obtener el grupo más fuerte.
     *
     * @return El grupo más fuerte.
     * @throws DAOException si ocurre un error durante la obtención.
     */
    @Override
    public Party obtainStrongest() throws DAOException {
        Party party = null;

        try (PreparedStatement ps = c.getConnection().prepareStatement(OBTAIN_STRONGEST)) {
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    party = mapParty(rs);
                }
            }

        } catch (SQLException e) {
            throw new DAOException(NOT_OBTAINED, e);
        }

        return party;
    }

    /**
     * Función para obtener todos los grupos.
     *
     * @return Una lista de todos los grupos.
     * @throws DAOException si ocurre un error durante la obtención.
     */
    @Override
    public List<Party> obtainAll() throws DAOException {
        List<Party> parties = new ArrayList<>();

        try (PreparedStatement ps = c.getConnection().prepareStatement(OBTAIN_ALL);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Party party = mapParty(rs);
                parties.add(party);
            }

        } catch (SQLException e) {
            throw new DAOException(LIST_NOT_OBTAINED, e);
        }

        return parties;
    }

    /**
     * Función para obtener todos los grupos que contienen un miembro específico.
     *
     * @param memberName El nombre del aventurero.
     * @return Una lista de grupos que contienen al miembro especificado.
     * @throws DAOException si ocurre un error durante la obtención.
     */
    @Override
    public List<Party> obtainAllThatContainMember(String memberName) throws DAOException {
        List<Party> parties = new ArrayList<>();

        try (PreparedStatement ps = c.getConnection().prepareStatement(OBTAIN_ALL_THAT_CONTAIN_MEMBER)) {
            ps.setString(1, memberName);
            ps.setString(2, memberName);
            ps.setString(3, memberName);
            ps.setString(4, memberName);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Party party = mapParty(rs);
                    parties.add(party);

                }

            } catch (SQLException e) {
                throw new DAOException(LIST_NOT_OBTAINED, e);
            }

        } catch (SQLException e) {
            throw new DAOException(LIST_NOT_OBTAINED, e);
        }

        return parties;
    }

    /**
     * Función para actualizar un grupo existente.
     *
     * @param party El grupo actualizado.
     * @param oldPart El nombre del grupo a actualizar.
     * @throws DAOException si ocurre un error durante la actualización.
     */
    @Override
    public void update(Party party, String oldPart) throws DAOException {
        try (PreparedStatement ps = c.getConnection().prepareStatement(UPDATE)) {

            ps.setString(1, party.getPartyName());
            ps.setString(2, party.getPtLeader().getName());
            ps.setString(3, party.getPtStriker().getName());
            ps.setString(4, party.getPtTank().getName());
            ps.setString(5, party.getPtHealer().getName());
            ps.setInt(6, party.getPtPower());
            ps.setString(7, oldPart);

            int filasAfectadas = ps.executeUpdate();

            if (filasAfectadas == 0) {
                throw new DAOException("Party update failed, no lines affected.", null);
            }
        } catch (SQLException e) {
            throw new DAOException("Party not updated.", e);
        }
    }

    /**
     * Función para eliminar un grupo por su nombre.
     *
     * @param partyName El nombre del grupo a eliminar.
     * @throws DAOException si ocurre un error durante la eliminación.
     */
    @Override
    public void delete(String partyName) throws DAOException {
        try (PreparedStatement ps = c.getConnection().prepareStatement(DELETE)) {
            ps.setString(1, partyName);
            int filasAfectadas = ps.executeUpdate();

            if (filasAfectadas == 0) {
                throw new DAOException("Party deletion failed, no lines affected.", null);
            }
        } catch (SQLException e) {
            throw new DAOException("Party not deleted.", e);
        }
    }

    /**
     * Función para mapear un ResultSet a un objeto Party.
     *
     * @param rs El ResultSet de la consulta.
     * @return El objeto Party mapeado.
     * @throws SQLException si ocurre un error durante el mapeo.
     * @throws DAOException si ocurre un error durante la obtención de personajes.
     */
    private Party mapParty(ResultSet rs) throws SQLException, DAOException {
        String name = rs.getString("name");
        Character ptLeader = charImpMethod.obtainByName(rs.getString("ptLeader"));
        Character ptStriker = charImpMethod.obtainByName(rs.getString("ptStriker"));
        Character ptTank = charImpMethod.obtainByName(rs.getString("ptTank"));
        Character ptHealer = charImpMethod.obtainByName(rs.getString("ptHealer"));

        return new Party(name, ptLeader, ptStriker, ptTank, ptHealer);
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