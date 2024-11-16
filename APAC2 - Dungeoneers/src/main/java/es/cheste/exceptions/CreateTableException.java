package es.cheste.exceptions;

/**
 * Excepción personalizada que se lanza cuando ocurre un error al crear una tabla.
 */
public class CreateTableException extends Exception {
    /**
     * Constructor para CreateTableException.
     *
     * @param mensaje El mensaje de error.
     * @param causa La causa del error.
     */
    public CreateTableException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
}
