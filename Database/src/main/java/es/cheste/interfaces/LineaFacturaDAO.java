package es.cheste.interfaces;

import es.cheste.exceptions.DAOException;
import es.cheste.classes.LineaFactura;

import java.util.List;

public interface LineaFacturaDAO {
    void insert(LineaFactura linFact) throws DAOException;
    LineaFactura obtenerPorLinea(int numFactura, short linea) throws DAOException;
    List<LineaFactura> obtenerLineas(int numFact) throws DAOException;
    void actualizar(LineaFactura linea, int numFact, short lineaFact) throws DAOException;
    void eliminar(short linea) throws DAOException;
}
