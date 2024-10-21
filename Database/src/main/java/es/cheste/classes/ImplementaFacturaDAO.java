package es.cheste.classes;

import es.cheste.exceptions.DAOException;
import es.cheste.interfaces.FacturaDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ImplementaFacturaDAO implements FacturaDAO {

    ConexionBD c = new ConexionBD();

    // Consultas SQL
    private static final String INSERTAR = "INSERT INTO Facturas (dni_cliente) VALUES ?";
    private static final String OBTENER_POR_NUM = "SELECT * FROM Facturas WHERE num_factura = ?";
    private static final String OBTENER_TODAS = "SELECT * FROM Facturas";
    private static final String ACTUALIZAR = "UPDATE Facturas SET num_factura = ?, dni_cliente = ? WHERE num_factura = ?";
    private static final String ELIMINAR = "DELETE FROM Facturas WHERE num_factura = ?";

    @Override
    public void insert(Factura factura) throws DAOException {
        try (PreparedStatement ps = c.getConnection().prepareStatement(INSERTAR, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, factura.getDniCliente());

            int filasAfectadas = ps.executeUpdate();

            if (filasAfectadas == 0) {
                throw new DAOException("Inserción de factura fallida. No se afectaron filas.", null);
            }

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    int id = rs.getInt(1);
                    factura.setNumFactura(id);
                } else {
                    throw new DAOException("No se generó ningún ID para la factura.", null);
                }
            }

        } catch (SQLException e) {
            throw new DAOException("Error al insertar la factura.", e);
        }
    }

    @Override
    public Factura obtenerPorNum(int numFactura) throws DAOException {
        Factura factura = null;

        try (PreparedStatement ps = c.getConnection().prepareStatement(OBTENER_POR_NUM)) {

            ps.setInt(1, numFactura);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    factura = transformarFactura(rs);
                }
            }

        } catch (SQLException e) {
            throw new DAOException("Error al obtener la factura.", e);
        }

        return factura;
    }

    @Override
    public List<Factura> obtenerTodas() throws DAOException {
        List<Factura> facturas = new ArrayList<>();

        try (PreparedStatement ps = c.getConnection().prepareStatement(OBTENER_TODAS);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Factura factura = transformarFactura(rs);
                facturas.add(factura);
            }

        } catch (SQLException e) {
            throw new DAOException("Error al obtener lista de facturas.", e);
        }

        return facturas;
    }

    @Override
    public void actualizar(Factura factura) throws DAOException {
        try (PreparedStatement ps = c.getConnection().prepareStatement(ACTUALIZAR)) {

            ps.setInt(1, factura.getNumFactura());
            ps.setString(2, factura.getDniCliente());

            int filasAfectadas = ps.executeUpdate();

            if (filasAfectadas == 0) {
                throw new DAOException("Actualización de factura fallida, no se afectaron filas.", null);

            }

        } catch (SQLException e) {
            throw new DAOException("Error al actualizar la factura.", e);
        }
    }

    @Override
    public void eliminar(int numFactura) throws DAOException {
        try (PreparedStatement ps = c.getConnection().prepareStatement(ELIMINAR)) {

            ps.setInt(1, numFactura);

            int filasAfectadas = ps.executeUpdate();

            if (filasAfectadas == 0) {
                throw new DAOException("Eliminación de factura fallida, no se afectaron filas.", null);

            }

        } catch (SQLException e) {
            throw new DAOException("Error al eliminar la factura.", e);
        }
    }

    // Método para mapear resultset a objeto Factura
    private Factura transformarFactura(ResultSet rs) throws SQLException {
        int id = rs.getInt("num_factura");
        String dni = rs.getString("dni_cliente");

        return new Factura(id, dni);
    }
}
