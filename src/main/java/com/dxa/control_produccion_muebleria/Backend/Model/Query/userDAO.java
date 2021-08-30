package com.dxa.control_produccion_muebleria.Backend.Model.Query;

import com.dxa.control_produccion_muebleria.Backend.Model.Clases.user;
import com.dxa.control_produccion_muebleria.Backend.Model.Connection.DBconnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Artist
 */
public class userDAO {

    private Connection connection;
    private ResultSet resultSet;
    private PreparedStatement preparedStatement;

    private user user = null;
    private String nombreTabla = "USUARIO";

    private void createConnectionToDB() {
        DBconnection dBconnection = new DBconnection();
        this.connection = dBconnection.getConnection();
    }

    public userDAO() {
        createConnectionToDB();
    }

    public boolean validate(String username, String password, int userType) {
        boolean bandera = false;
        try {

            String query = "SELECT * FROM " + nombreTabla
                    + " WHERE nombre =? and password =? and tipo =?;";

            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            preparedStatement.setInt(3, userType);

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                bandera = true;
            } else {
                bandera = false;
            }

            resultSet.close();
            System.out.println((resultSet.isClosed()) ? "Resulset Cerrado" : "Resulset No Cerrado");
            return bandera;

        } catch (Exception e) {

            System.out.println("consulta " + nombreTabla + " dice: Ha ocurrido un error: " + e.toString());
            return false;
        } finally {
            preparedStatement = null;

        }

    }

}
