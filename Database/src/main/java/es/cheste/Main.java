package es.cheste;

import java.sql.*;
import java.util.Scanner;

import es.cheste.classes.ConexionBD;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {

    private static final Scanner SC = new Scanner(System.in);
    public static final Logger LOGGER = LogManager.getLogger();

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        ConexionBD c = new ConexionBD();

        /*
        Métodos ya ejecutados:

        generarTablas(c);
        preparedStatement(c);
        preparedStatement2(c);

        */

        c.desconectar();

    }

    private static void generarTablas(ConexionBD c) {
        try(Statement statement = c.getConnection().createStatement()) {
            statement.execute("CREATE TABLE CLIENTES (DNI CHAR(9)NOT NULL , APELLIDOS VARCHAR(32) NOT NULL, CP CHAR(5), PRIMARY KEY (DNI))");
            statement.execute("CREATE TABLE FACTURAS (\n" +
                    "    NUM_FACTURA INT AUTO_INCREMENT,\n" +
                    "    DNI_CLIENTE CHAR(9) NOT NULL,\n" +
                    "    PRIMARY KEY (NUM_FACTURA),\n" +
                    "    CONSTRAINT FK_FACT_DNI_CLIENTES FOREIGN KEY (DNI_CLIENTE)\n" +
                    "        REFERENCES CLIENTES (DNI)\n" +
                    ");\n");
            statement.execute("CREATE TABLE LINEAS_FACTURA (" +
                    "NUM_FACTURA INTEGER NOT  NULL, " +
                    "LINEA_FACTURA SMALLINT NOT NULL, " +
                    "CONCEPTO VARCHAR(32) NOT NULL, " +
                    "CANTIDAD SMALLINT NOT NULL, " +
                    "PRIMARY KEY (NUM_FACTURA, LINEA_FACTURA), " +
                    "FOREIGN KEY FK_LINEAFACT_NUM_FACTURA (NUM_FACTURA)" +
                    "REFERENCES FACTURAS (NUM_FACTURA))");

        } catch (SQLException e){
            LOGGER.error(e.getMessage());

        }
    }

    private static void preparedStatement(ConexionBD c) {
        try {
            PreparedStatement sInsert = c.getConnection().prepareStatement("INSERT INTO CLIENTES(DNI,APELLIDOS,CP) VALUES(?,?,?)");

            c.getConnection().setAutoCommit(false);

            int i = 1;
            sInsert.setString(i++, "78901234X");
            sInsert.setString(i++, "NADALES");
            sInsert.setInt(i, 44126);
            sInsert.executeUpdate();

            i = 1;
            sInsert.setString(i++, "89012345E");
            sInsert.setString(i++, "ROJAS");
            sInsert.setNull(i, Types.INTEGER);
            sInsert.executeUpdate();

            i = 1;
            sInsert.setString(i++, "56789012B");
            sInsert.setString(i++, "SAMPER");
            sInsert.setInt(i, 29730);
            sInsert.executeUpdate();

            c.getConnection().commit();

        } catch (SQLException e) {
            LOGGER.error("Error al insertar en la base de datos.");

            try {
                c.getConnection().rollback();
                LOGGER.error("Se hace ROLLBACK");

            } catch (SQLException err) {
                LOGGER.error("Error haciendo ROLLBACK");

            }

        } catch (Exception e) {
            e.printStackTrace(System.err);

        }
    }

    private static void preparedStatement2 (ConexionBD c) {
        try (PreparedStatement sInsertFact = c.getConnection().prepareStatement("INSERT INTO FACTURAS (DNI_CLIENTE) VALUES (?)",
                Statement.RETURN_GENERATED_KEYS);
            PreparedStatement sInsertLineaFact = c.getConnection().prepareStatement(
                    "INSERT INTO LINEAS_FACTURA (NUM_FACTURA, LINEA_FACTURA, CONCEPTO, CANTIDAD)" +
                            "VALUES (?, ?, ?, ?)")) {
            c.getConnection().setAutoCommit(false);

            int i = 1;
            sInsertFact.setString(i++, "78901234X");
            sInsertFact.executeUpdate();

            ResultSet rs = sInsertFact.getGeneratedKeys();
            rs.next();
            int numFact = rs.getInt(1);

            int lineaFact = 1;
            i = 1;
            sInsertLineaFact.setInt(i++, numFact);
            sInsertLineaFact.setInt(i++, lineaFact++);
            sInsertLineaFact.setString(i++, "TUERCAS");
            sInsertLineaFact.setInt(i++, 25);
            sInsertLineaFact.executeUpdate();

            i = 1;
            sInsertLineaFact.setInt(i++, numFact);
            sInsertLineaFact.setInt(i++, lineaFact);
            sInsertLineaFact.setString(i++, "TORNILLOS");
            sInsertLineaFact.setInt(i++, 250);
            sInsertLineaFact.executeUpdate();

            c.getConnection().commit();

        } catch (SQLException e) {
            LOGGER.error("Error al insertar en la base de datos.");
            e.printStackTrace();

            try {
                c.getConnection().rollback();
                LOGGER.error("Se hace ROLLBACK");

            } catch (SQLException err) {
                LOGGER.error("Error haciendo ROLLBACK");
                e.printStackTrace();

            }

        } catch (Exception e) {
            e.printStackTrace(System.err);

        }
    }
}