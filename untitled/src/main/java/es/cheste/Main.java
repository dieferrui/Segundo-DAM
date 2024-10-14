package es.cheste;

import java.sql.*;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {

    private static final Scanner SC = new Scanner(System.in);
    public static final Logger LOGGER = LogManager.getLogger();

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String server = "localhost";
        int port = 3306;
        String user = "root";
        String pwd = "root";
        String DBName = "Empresa";
        String urlConnection = "jdbc:mysql://" + server + ":" + port;
        urlConnection += "/" + DBName;
        urlConnection += "?useUnicode=true&characterEncoding=UTF-8";

        Connection c = DriverManager.getConnection(urlConnection, user, pwd);

    }

    private static void preparedStatement(Connection c) {
        try {
            PreparedStatement sInsert = c.prepareStatement("INSERT INTO CLIENTES1(DNI,APELLIDOS,CP) VALUES(?,?,?)");

            c.setAutoCommit(false);

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

            c.commit();

        } catch (SQLException e) {
            LOGGER.error("Error al insertar en la base de datos.");

            try {
                c.rollback();
                LOGGER.error("Se hace ROLLBACK");

            } catch (SQLException err) {
                LOGGER.error("Error haciendo ROLLBACK");

            }

        } catch (Exception e) {
            e.printStackTrace(System.err);

        }
    }
}