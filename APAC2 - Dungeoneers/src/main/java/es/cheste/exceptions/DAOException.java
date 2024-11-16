package es.cheste.exceptions;

/**
 * Excepción personalizada que se lanza cuando ocurre un error en la capa de acceso a datos (DAO).
 */
public class DAOException extends Exception {
    /**
     * Constructor para DAOException.
     *
     * @param mensaje El mensaje de error.
     * @param causa La causa del error.
     */
    public DAOException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
}