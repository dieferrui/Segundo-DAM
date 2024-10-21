package es.cheste.interfaces;

import es.cheste.exceptions.DAOException;
import es.cheste.classes.Factura;

import java.util.List;

public interface FacturaDAO {
    void insert(Factura factura) throws DAOException;
    Factura obtenerPorNum(int numFactura) throws DAOException;
    List<Factura> obtenerTodas() throws DAOException;
    void actualizar(Factura factura) throws DAOException;
    void eliminar(int numFactura) throws DAOException;
}
