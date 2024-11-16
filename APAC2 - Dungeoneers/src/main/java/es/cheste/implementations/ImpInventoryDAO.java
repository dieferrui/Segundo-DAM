package es.cheste.implementations;

import es.cheste.exceptions.DAOException;
import es.cheste.classes.DBConnection;
import es.cheste.classes.Inventory;
import es.cheste.interfaces.InventoryDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ImpInventoryDAO implements InventoryDAO {

    DBConnection c = new DBConnection();

    // Cadenas finales
    private static final String NOT_OBTAINED = "Inventory not obtained.";
    private static final String LIST_NOT_OBTAINED = "Inventory list not obtained.";
    private static final String STATEMENT_ERROR = "Statement error.";

    // Consultas SQL
    private static final String INSERT = "INSERT INTO Inventory (characterName, itemName, quantity) VALUES (?, ?, ?)";
    private static final String OBTAIN_BY_SLOT = "SELECT * FROM Inventory WHERE slotNumber = ?";
    private static final String OBTAIN_CHARACTER_INVENTORY = "SELECT * FROM Inventory WHERE characterName = ?";
    private static final String OBTAIN_CHARACTER_EQUIPMENT = "SELECT i.slotNumber, i.characterName, i.itemName, i.quantity " +
            "FROM Inventory i LEFT JOIN Item it ON i.itemName = it.name WHERE i.characterName = ? AND it.consumable = false";
    private static final String OBTAIN_CHARACTER_CONSUMABLES = "SELECT i.slotNumber, i.characterName, i.itemName, i.quantity " +
            "FROM Inventory i LEFT JOIN Item it ON i.itemName = it.name WHERE i.characterName = ? AND it.consumable = true";
    private static final String UPDATE = "UPDATE Inventory SET characterName = ?, itemName = ?, quantity = ? WHERE slotNumber = ?";
    private static final String DELETE = "DELETE FROM Inventory WHERE slotNumber = ?";

    /**
     * Función para insertar una nueva entrada de inventario en la base de datos.
     *
     * @param inventory La entrada de inventario a insertar.
     * @throws DAOException si ocurre un error durante la inserción.
     */
    @Override
    public void insert(Inventory inventory) throws DAOException {
        try (PreparedStatement ps = c.getConnection().prepareStatement(INSERT)) {
            ps.setString(1, inventory.getCharacterName());
            ps.setString(2, inventory.getItemName());
            ps.setInt(3, inventory.getQuantity());

            int affectedRows = ps.executeUpdate();

            if (affectedRows == 0) {
                throw new DAOException("Inventory entry insertion failed, no lines affected.", null);
            }

        } catch (SQLException e) {
            throw new DAOException("Inventory entry not inserted.", e);
        }
    }

    /**
     * Función para obtener una entrada de inventario por su número de ranura.
     *
     * @param slotNumber El número de ranura de la entrada de inventario.
     * @return La entrada de inventario obtenida.
     * @throws DAOException si ocurre un error durante la obtención.
     */
    @Override
    public Inventory obtainBySlotNumber(int slotNumber) throws DAOException {
        Inventory inventory = null;

        try (PreparedStatement ps = c.getConnection().prepareStatement(OBTAIN_BY_SLOT)) {
            ps.setInt(1, slotNumber);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    inventory = mapInventory(rs);
                }
            }

        } catch (SQLException e) {
            throw new DAOException(NOT_OBTAINED, e);
        }

        return inventory;
    }

    /**
     * Función para obtener el inventario de un personaje.
     *
     * @param charName El nombre del personaje.
     * @return Una lista de entradas de inventario del personaje.
     * @throws DAOException si ocurre un error durante la obtención.
     */
    @Override
    public List<Inventory> obtainCharacterInventory(String charName) throws DAOException {
        List<Inventory> entries = new ArrayList<>();

        try (PreparedStatement ps = c.getConnection().prepareStatement(OBTAIN_CHARACTER_INVENTORY)) {
            ps.setString(1, charName);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Inventory inventory = mapInventory(rs);
                    entries.add(inventory);
                }

            } catch (SQLException e) {
                throw new DAOException(LIST_NOT_OBTAINED, e);
            }

        } catch (SQLException e) {
            throw new DAOException(STATEMENT_ERROR, e);
        }

        return entries;
    }

    /**
     * Función para obtener el equipo de un personaje.
     *
     * @param charName El nombre del personaje.
     * @return Una lista de entradas de inventario del equipo del personaje.
     * @throws DAOException si ocurre un error durante la obtención.
     */
    @Override
    public List<Inventory> obtainCharacterEquipment(String charName) throws DAOException {
        List<Inventory> inventories = new ArrayList<>();

        try (PreparedStatement ps = c.getConnection().prepareStatement(OBTAIN_CHARACTER_EQUIPMENT)) {
            ps.setString(1, charName);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Inventory inventory = mapInventory(rs);
                    inventories.add(inventory);
                }

            } catch (SQLException e) {
                throw new DAOException(LIST_NOT_OBTAINED, e);
            }

        } catch (SQLException e) {
            throw new DAOException(STATEMENT_ERROR, e);
        }

        return inventories;
    }

    /**
     * Función para obtener los consumibles de un personaje.
     *
     * @param charName El nombre del personaje.
     * @return Una lista de entradas de inventario de los consumibles del personaje.
     * @throws DAOException si ocurre un error durante la obtención.
     */
    @Override
    public List<Inventory> obtainCharacterConsumables(String charName) throws DAOException {
        List<Inventory> inventories = new ArrayList<>();

        try (PreparedStatement ps = c.getConnection().prepareStatement(OBTAIN_CHARACTER_CONSUMABLES)) {
            ps.setString(1, charName);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Inventory inventory = mapInventory(rs);
                    inventories.add(inventory);
                }

            } catch (SQLException e) {
                throw new DAOException(LIST_NOT_OBTAINED, e);
            }

        } catch (SQLException e) {
            throw new DAOException(STATEMENT_ERROR, e);
        }

        return inventories;
    }

    /**
     * Función para actualizar una entrada de inventario existente.
     *
     * @param slotNumber El número de ranura de la entrada de inventario a actualizar.
     * @param inventory La entrada de inventario actualizada.
     * @throws DAOException si ocurre un error durante la actualización.
     */
    @Override
    public void update(int slotNumber, Inventory inventory) throws DAOException {
        try (PreparedStatement ps = c.getConnection().prepareStatement(UPDATE)) {

            ps.setString(1, inventory.getCharacterName());
            ps.setString(2, inventory.getItemName());
            ps.setInt(3, inventory.getQuantity());
            ps.setInt(4, slotNumber);

            int filasAfectadas = ps.executeUpdate();

            if (filasAfectadas == 0) {
                throw new DAOException("Inventory update failed, no lines affected.", null);
            }

        } catch (SQLException e) {
            throw new DAOException("Inventory not updated.", e);

        }
    }

    /**
     * Función para eliminar una entrada de inventario por su número de slot.
     *
     * @param slotNumber El número de slot de la entrada de inventario a eliminar.
     * @throws DAOException si ocurre un error durante la eliminación.
     */
    @Override
    public void delete(int slotNumber) throws DAOException {
        try (PreparedStatement ps = c.getConnection().prepareStatement(DELETE)) {
            ps.setInt(1, slotNumber);
            int filasAfectadas = ps.executeUpdate();

            if (filasAfectadas == 0) {
                throw new DAOException("Inventory deletion failed, no lines affected.", null);
            }
        } catch (SQLException e) {
            throw new DAOException("Inventory not deleted.", e);
        }
    }

    /**
     * Función para mapear un ResultSet a un objeto Inventory.
     *
     * @param rs El ResultSet de la consulta.
     * @return El objeto Inventory mapeado.
     * @throws SQLException si ocurre un error durante el mapeo.
     */
    private Inventory mapInventory(ResultSet rs) throws SQLException {
        int slotNumber = rs.getInt("slotNumber");
        String characterName = rs.getString("characterName");
        String itemName = rs.getString("itemName");
        int quantity = rs.getInt("quantity");

        return new Inventory(slotNumber, characterName, itemName, quantity);
    }
}