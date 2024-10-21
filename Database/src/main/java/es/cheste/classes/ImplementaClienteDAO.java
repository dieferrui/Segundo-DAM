package es.cheste.classes;

import es.cheste.exceptions.DAOException;
import es.cheste.interfaces.ClienteDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ImplementaClienteDAO implements ClienteDAO {

    ConexionBD c = new ConexionBD();
    public static final Logger LOGGER = LogManager.getLogger();

    // Consultas SQL
    private static final String INSERTAR = "INSERT INTO Cliente (dni, apellidos, cp) VALUES (?, ?, ?)";
    private static final String OBTENER_POR_DNI = "SELECT * FROM Cliente WHERE dni = ?";
    private static final String OBTENER_TODOS = "SELECT * FROM Cliente";
    private static final String ACTUALIZAR = "UPDATE Cliente SET dni = ?, apellidos = ?, cp = ? WHERE dni = ?";
    private static final String ELIMINAR = "DELETE FROM Cliente WHERE dni = ?";

    @Override
    public void insert(Cliente cliente) throws DAOException {
        try (PreparedStatement ps = c.getConnection().prepareStatement(INSERTAR, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, cliente.getDni());
            ps.setString(2, cliente.getApellidos());
            ps.setString(3, cliente.getCp());

            int filasAfectadas = ps.executeUpdate();

            if (filasAfectadas == 0) {
                LOGGER.error("Inserción de cliente fallida.");
                throw new DAOException("Inserción de cliente fallida.", null);
            }

        } catch (SQLException e) {
            LOGGER.error("Error al insertar el cliente.", e);
            throw new DAOException("Error al insertar el vcliente.", e);
        }

    }

    @Override
    public Cliente obtenerPorDNI(String dni) throws DAOException {
        Cliente cliente = null;

        try (PreparedStatement ps = c.getConnection().prepareStatement(OBTENER_POR_DNI)) {

            ps.setString(1, dni);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    cliente = transformarCliente(rs);
                }
            }

        } catch (SQLException e) {
            throw new DAOException("Error al obtener el cliente por DNI.", e);
        }

        return cliente;
    }

    @Override
    public List<Cliente> obtenerTodos() throws DAOException {
        List<Cliente> clientes = new ArrayList<>();

        try (PreparedStatement ps = c.getConnection().prepareStatement(OBTENER_TODOS);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Cliente cliente = transformarCliente(rs);
                clientes.add(cliente);
            }

        } catch (SQLException e) {
            throw new DAOException("Error al obtener lista de clientes.", e);
        }

        return clientes;
    }

    @Override
    public void actualizar(Cliente cliente) throws DAOException {
        try (PreparedStatement ps = c.getConnection().prepareStatement(ACTUALIZAR)) {

            ps.setString(1, cliente.getDni());
            ps.setString(2, cliente.getApellidos());
            ps.setString(3, cliente.getCp());

            int filasAfectadas = ps.executeUpdate();

            if (filasAfectadas == 0) {
                throw new DAOException("Actualización de cliente fallida, no se afectaron filas.", null);

            } else {
                LOGGER.info("Cliente actualizado correctamente (filas afectadas: " + filasAfectadas + ")");
            }

        } catch (SQLException e) {
            throw new DAOException("Error al actualizar el cliente.", e);
        }
    }

    @Override
    public void eliminar(String dni) throws DAOException {
        try (PreparedStatement ps = c.getConnection().prepareStatement(ELIMINAR)) {

            ps.setString(1, dni);

            int filasAfectadas = ps.executeUpdate();

            if (filasAfectadas == 0) {
                throw new DAOException("Eliminación de cliente fallida, no se afectaron filas.", null);

            } else {
                LOGGER.info("Cliente eliminado correctamente (filas afectadas: " + filasAfectadas + ")");
            }

        } catch (SQLException e) {
            throw new DAOException("Error al eliminar el cliente.", e);
        }
    }

    // Método auxiliar para mapear Cliente a Vehiculo
    private Cliente transformarCliente(ResultSet rs) throws SQLException {
        String dni = rs.getString("dni");
        String apellidos = rs.getString("apellidos");
        String cp = rs.getString("cp");

        return new Cliente(dni, apellidos, cp);
    }
}
