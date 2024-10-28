package es.cheste.exceptions;

public class CreateTableException extends Exception {
    public CreateTableException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
}
