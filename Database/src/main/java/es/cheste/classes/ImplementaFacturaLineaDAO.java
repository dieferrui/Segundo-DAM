package es.cheste.classes;

import es.cheste.exceptions.DAOException;
import es.cheste.interfaces.LineaFacturaDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ImplementaFacturaLineaDAO implements LineaFacturaDAO {
    ConexionBD c = new ConexionBD();

    // Consultas SQL
    private static final String INSERTAR = "INSERT INTO Lineas_factura (num_factura, linea_factura, concepto, cantidad) VALUES (?, ?, ?, ?)";
    private static final String OBTENER_POR_LINEA = "SELECT * FROM Lineas_factura WHERE num_factura = ? AND linea_factura = ?";
    private static final String OBTENER_TODAS = "SELECT * FROM Lineas_factura WHERE num_factura = ?";
    private static final String ACTUALIZAR = "UPDATE Lineas_factura SET num_factura = ?, linea_factura = ?, concepto = ?, cantidad = ? WHERE num_factura = ? and linea_factura = ?";
    private static final String ELIMINAR = "DELETE FROM Lineas_factura WHERE num_factura = ? AND linea_factura = ?";

    @Override
    public void insert(LineaFactura linea) throws DAOException {
        try (PreparedStatement ps = c.getConnection().prepareStatement(INSERTAR, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, linea.getNumFactura());
            ps.setShort(2, linea.getLineaFactura());
            ps.setString(3, linea.getConcepto());
            ps.setShort(4, linea.getCantidad());

            int filasAfectadas = ps.executeUpdate();

            if (filasAfectadas == 0) {
                throw new DAOException("Inserción de línea fallida.", null);
            }

        } catch (SQLException e) {
            throw new DAOException("Error al insertar la línea. ¿Error en el número de factura?.", e);
        }

    }

    @Override
    public LineaFactura obtenerPorLinea(int numFact, short linea) throws DAOException {
        LineaFactura lineaFact = null;

        try (PreparedStatement ps = c.getConnection().prepareStatement(OBTENER_POR_LINEA)) {

            ps.setInt(1, numFact);
            ps.setShort(2, linea);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    lineaFact = transformarLinea(rs);
                }
            }

        } catch (SQLException e) {
            throw new DAOException("Error al obtener la línea especificada.", e);
        }

        return lineaFact;
    }

    @Override
    public List<LineaFactura> obtenerLineas(int numFactura) throws DAOException {
        List<LineaFactura> lineas = new ArrayList<>();

        try (PreparedStatement ps = c.getConnection().prepareStatement(OBTENER_TODAS)) {

            ps.setInt(1, numFactura);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    LineaFactura linea = transformarLinea(rs);
                    lineas.add(linea);
                }
            }

        } catch (SQLException e) {
            throw new DAOException("Error al obtener lista de líneas.", e);
        }

        return lineas;
    }


    @Override
    public void actualizar(LineaFactura linea, int numFact, short lineaFact) throws DAOException {
        try (PreparedStatement ps = c.getConnection().prepareStatement(ACTUALIZAR)) {

            ps.setInt(1, linea.getNumFactura());
            ps.setShort(2, linea.getLineaFactura());
            ps.setString(3, linea.getConcepto());
            ps.setShort(4, linea.getCantidad());
            ps.setInt(5, numFact);
            ps.setShort(6, lineaFact);

            int filasAfectadas = ps.executeUpdate();

            if (filasAfectadas == 0) {
                throw new DAOException("Actualización de línea fallida, no se afectaron filas.", null);

            }

        } catch (SQLException e) {
            throw new DAOException("Error al actualizar la línea.", e);
        }
    }

    @Override
    public void eliminar(short linea) throws DAOException {
        try (PreparedStatement ps = c.getConnection().prepareStatement(ELIMINAR)) {

            ps.setShort(1, linea);

            int filasAfectadas = ps.executeUpdate();

            if (filasAfectadas == 0) {
                throw new DAOException("Eliminación de linea fallida, no se afectaron filas.", null);

            }

        } catch (SQLException e) {
            throw new DAOException("Error al eliminar la línea.", e);
        }
    }

    // Método para mapear resultset a objeto LineaFactura
    private LineaFactura transformarLinea(ResultSet rs) throws SQLException {
        int numfact = rs.getInt("num_factura");
        short linea_factura = rs.getShort("linea_factura");
        String concepto = rs.getString("concepto");
        short cantidad = rs.getShort("cantidad");

        return new LineaFactura(numfact, linea_factura, concepto, cantidad);
    }
}

