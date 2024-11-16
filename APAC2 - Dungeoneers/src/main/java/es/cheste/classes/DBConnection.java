package es.cheste.classes;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Clase que maneja la conexión a la base de datos.
 */
public class DBConnection {

    private static final Logger LOGGER = LogManager.getRootLogger();
    private static final String FILENAME = "src/main/resources/application.properties";

    private Connection connection = null;

    /**
     * Función privada que establece la conexión a la base de datos.
     */
    private void conectar() {
        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream(FILENAME));

            String url = properties.getProperty("db.url");
            String user = properties.getProperty("db.user");
            String password = properties.getProperty("db.pass");

            connection = DriverManager.getConnection(url, user, password);

        } catch (IOException e) {
            LOGGER.error("Error loading properties file", e);

        } catch (SQLException e) {
            LOGGER.error("Error connecting database", e);

        }
    }

    /**
     * Función que cierra la conexión a la base de datos.
     */
    public void desconectar() {
        try {
            connection.close();

        } catch (SQLException e) {
            LOGGER.error("Error disconnecting database", e);

        }
    }

    /**
     * Función que devuelve la conexión a la base de datos.
     * Si la conexión no está establecida, la establece.
     *
     * @return La conexión a la base de datos.
     */
    public Connection getConnection() {
        if(connection == null) {
            conectar();

        }

        return connection;
    }
}
