package es.cheste.classes;

import es.cheste.exceptions.DAOException;
import es.cheste.classes.Cliente;
import es.cheste.interfaces.ClienteDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ImplementaClienteDAO implements ClienteDAO {

    ConexionBD c = new ConexionBD();
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
                throw new DAOException("Inserción de cliente fallida.", null);
            }

        } catch (SQLException e) {
            throw new DAOException("Error al insertar el vehiculo.", e);
        }
    }

    @Override
    public Vehiculo obtenerPorId(int id) throws DAOException {
        Vehiculo vehiculo = null;

        try (Connection conexion = obtenerConexion();
             PreparedStatement ps = conexion.prepareStatement(OBTENER_POR_ID)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    vehiculo = mapearVehiculo(rs);
                }
            }

        } catch (SQLException e) {
            throw new DAOException("Error al obtener el vehiculo por ID.", e);
        }

        return vehiculo;
    }

    @Override
    public List<Vehiculo> obtenerTodos() throws DAOException {
        List<Vehiculo> vehiculos = new ArrayList<>();

        try (Connection conexion = obtenerConexion();
             PreparedStatement ps = conexion.prepareStatement(OBTENER_TODOS);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Vehiculo vehiculo = mapearVehiculo(rs);
                vehiculos.add(vehiculo);
            }

        } catch (SQLException e) {
            throw new DAOException("Error al obtener todos los vehiculos.", e);
        }

        return vehiculos;
    }

    @Override
    public void actualizar(Vehiculo vehiculo) throws DAOException {
        try (Connection conexion = obtenerConexion();
             PreparedStatement ps = conexion.prepareStatement(ACTUALIZAR)) {

            ps.setString(1, vehiculo.getMarca());
            ps.setString(2, vehiculo.getModelo());
            ps.setInt(3, vehiculo.getNumPuertas());
            ps.setInt(4, vehiculo.getId());

            int filasAfectadas = ps.executeUpdate();

            if (filasAfectadas == 0) {
                throw new DAOException("Actualización de vehiculo fallida, no se afectaron filas.", null);
            }

        } catch (SQLException e) {
            throw new DAOException("Error al actualizar el vehiculo.", e);
        }
    }

    @Override
    public void eliminar(int id) throws DAOException {
        try (Connection conexion = obtenerConexion();
             PreparedStatement ps = conexion.prepareStatement(ELIMINAR)) {

            ps.setInt(1, id);

            int filasAfectadas = ps.executeUpdate();

            if (filasAfectadas == 0) {
                throw new DAOException("Eliminación de vehiculo fallida, no se afectaron filas.", null);
            }

        } catch (SQLException e) {
            throw new DAOException("Error al eliminar el vehiculo.", e);
        }
    }

    // Método auxiliar para mapear ResultSet a Vehiculo
    private Vehiculo mapearVehiculo(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String marca = rs.getString("marca");
        String modelo = rs.getString("modelo");
        int numPuertas = rs.getInt("num_puertas");

        return new Vehiculo(id, marca, modelo, numPuertas);
    }
}
