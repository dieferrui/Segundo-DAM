package es.cheste.interfaces;

import es.cheste.exceptions.DAOException;
import es.cheste.classes.Cliente;

import java.util.List;

public interface ClienteDAO {
    void insert(Cliente cliente) throws DAOException;
    Cliente obtenerPorDNI(int dni) throws DAOException;
    List<Cliente> obtenerTodos() throws DAOException;
    void actualizar(Cliente cliente) throws DAOException;
    void eliminar(int dni) throws DAOException;
}
