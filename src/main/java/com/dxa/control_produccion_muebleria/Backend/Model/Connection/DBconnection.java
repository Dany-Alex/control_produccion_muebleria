package com.dxa.control_produccion_muebleria.Backend.Model.Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Artist
 */
public class DBconnection {
    // Librería de MySQL

    //final public static String driver = "com.mysql.jdbc.Driver";
    final private static String driver = "com.mysql.cj.jdbc.Driver";

    // Nombre de la base de datos
    public static String database = "muebleria";

    // Host
    public static String hostname = "localhost";

    // Puerto
    public static String port = "3306";
    private static String zonaHoraria = "&serverTimezone=UTC";
    // Ruta de nuestra base de datos (desactivamos el uso de SSL con "?useSSL=false")
    // public String url = "jdbc:mysql://" + hostname + ":" + port + "/" + database;
    private static String url = "jdbc:mysql://" + hostname + ":" + port + "/" + database + "?" + zonaHoraria;
    // Nombre de usuario
    public static String username = "root2";

    // Clave de usuario
    public static String password = "root2";

    //Variables
    Connection connection = null;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public Connection getConnection() {

        try {
            Class.forName(driver);//declare a driver
            // Conectamos con la base de datos
            //System.out.println("url = " + url);
            connection = DriverManager.getConnection(
                    url,
                    username,
                    password);
            boolean valid = connection.isValid(50000);
            System.out.println(valid ? "Estado de conexion con " + connection.getCatalog() + " BD: OK" : "Estado de conexion con la BD: FALLO");
            return connection;
        } catch (Exception e) {
            System.out.println("Error al conectar con la base de datos de MySQL (" + url + "): " + e);
            return null;
        }

    }

    public ResultSet getPreparedStatementExecuteQuery(String query) {
        try {
            this.preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            return resultSet;
        } catch (SQLException ex) {
            System.out.println("managerDB diece: Error de conexion con la base de datos: " + ex.toString());
            return null;
        } finally {
            preparedStatement = null;
        }
    }

    public void closeConnection() throws SQLException {

        if (connection != null) {
            connection.close();
            System.out.println("Conexion cerrada exitosamente");
        }
    }

    public void closeConnection(Connection conexion) {
        if (conexion != null) {
            try {
                conexion.close();
                conexion = null;
            } catch (SQLException ex) {
                System.out.println(ex.getMessage()
                        + ". >>> Error de Desconexion!!");
            }
        }
    }

}
