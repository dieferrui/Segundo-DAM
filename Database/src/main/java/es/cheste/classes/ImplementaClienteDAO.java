package es.cheste.classes;

import es.cheste.exceptions.DAOException;
import es.cheste.interfaces.ClienteDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ImplementaClienteDAO implements ClienteDAO {

    ConexionBD c = new ConexionBD();

    // Consultas SQL
    private static final String INSERTAR = "INSERT INTO Clientes (dni, apellidos, cp) VALUES (?, ?, ?)";
    private static final String OBTENER_POR_DNI = "SELECT * FROM Clientes WHERE dni = ?";
    private static final String OBTENER_TODOS = "SELECT * FROM Clientes";
    private static final String ACTUALIZAR = "UPDATE Clientes SET dni = ?, apellidos = ?, cp = ? WHERE dni = ?";
    private static final String ELIMINAR = "DELETE FROM Clientes WHERE dni = ?";

    @Override
    public void insert(Cliente cliente) throws DAOException {
        try (PreparedStatement ps = c.getConnection().prepareStatement(INSERTAR, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, cliente.getDni());
            ps.setString(2, cliente.getApellidos());
            ps.setString(3, cliente.getCp());

            int filasAfectadas = ps.executeUpdate();

            if (filasAfectadas == 0) {
                throw new DAOException("Inserción de cliente fallida.", null);
            }

        } catch (SQLException e) {
            throw new DAOException("Error al insertar el cliente.", e);
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

            }

        } catch (SQLException e) {
            throw new DAOException("Error al eliminar el cliente.", e);
        }
    }

    // Método para mapear resultset a objeto Cliente
    private Cliente transformarCliente(ResultSet rs) throws SQLException {
        String dni = rs.getString("dni");
        String apellidos = rs.getString("apellidos");
        String cp = rs.getString("cp");

        return new Cliente(dni, apellidos, cp);
    }
}
