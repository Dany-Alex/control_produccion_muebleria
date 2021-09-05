package com.dxa.control_produccion_muebleria.Backend.Model.Query;

import com.dxa.control_produccion_muebleria.Backend.Model.Clases.Exceptions.CustomException;
import com.dxa.control_produccion_muebleria.Backend.Model.Clases.Exceptions.exceptionPiece;
import com.dxa.control_produccion_muebleria.Backend.Model.Clases.typePiece;
import com.dxa.control_produccion_muebleria.Backend.Model.Clases.user;
import com.dxa.control_produccion_muebleria.Backend.Model.Connection.DBconnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

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

    public List listAllData() throws CustomException {
        ArrayList<user> arrayList = new ArrayList<user>();
        try {
            String query = "SELECT * FROM " + nombreTabla + ";";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                user = new user();
                user.setName(resultSet.getString(1));
                user.setPassword(resultSet.getString(2));
                user.setType(resultSet.getInt(3) + "");
                arrayList.add(user);
            }
            resultSet.close();
            System.out.println((resultSet.isClosed()) ? "Resulset Cerrado" : "Resulset No Cerrado");
            return arrayList;
        } catch (Exception e) {
            throw new CustomException("Consulta " + nombreTabla + " Error: " + e.getMessage().replace("'", ""));
        } finally {
            preparedStatement = null;
        }
    }

    public List listDataWithoutUserCancel() throws CustomException {
        ArrayList<user> arrayList = new ArrayList<user>();
        try {
            String query = "SELECT * FROM " + nombreTabla
                    + " WHERE tipo!=0;";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                user = new user();
                user.setName(resultSet.getString(1));
                user.setPassword(resultSet.getString(2));
                user.setType(resultSet.getInt(3) + "");
                arrayList.add(user);
            }
            resultSet.close();
            System.out.println((resultSet.isClosed()) ? "Resulset Cerrado" : "Resulset No Cerrado");
            return arrayList;
        } catch (Exception e) {
            throw new CustomException("Consulta " + nombreTabla + " Error: " + e.getMessage().replace("'", ""));
        } finally {
            preparedStatement = null;
        }
    }

    /**
     * *
     *
     * @param user recibe un objeto typePiece, que se utiliza para obtener los
     * datos para la creacion de un nuevo usuario
     * @return
     * @throws SQLException sirve para cuando se este tratando de crear un
     * usuario ya existente
     * @throws exceptionPiece
     */
    public boolean create(user user) throws SQLException, CustomException {
        try {
            String query = "INSERT INTO " + nombreTabla
                    + " (nombre,password,tipo) "
                    + "VALUES(?, ?, ?);";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setInt(3, user.getType());
            preparedStatement.executeUpdate();
            System.out.println("crear ejecutado");
        } catch (SQLIntegrityConstraintViolationException e) {
            throw new CustomException("El usuario: " + user.getName() + " no esta diponible, intente con otro nombre de usuario.");
        } catch (SQLException e) {
            throw new CustomException("Consulta " + nombreTabla + " Error: " + e.getMessage());
        } finally {
            preparedStatement = null;
        }
        return true;
    }

    public void update(user user) throws SQLException, CustomException {
        try {
            String query = "UPDATE " + nombreTabla
                    + " SET password=?,tipo=?"
                    + " WHERE nombre = '" + user.getName() + "';";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, user.getPassword());
            preparedStatement.setInt(2, user.getType());
            preparedStatement.executeUpdate();

        } catch (SQLIntegrityConstraintViolationException e) {
            throw new CustomException("No puede modificar" + e.getMessage().replace("'", ""));
        } catch (SQLException e) {
            throw new CustomException("Consulta " + nombreTabla + " Error: " + e.getMessage().replace("'", ""));
        } finally {
            preparedStatement = null;
        }
    }

    public void updateTypeUser(user user) throws SQLException, CustomException {
        try {
            String query = "UPDATE " + nombreTabla
                    + " SET tipo=?"
                    + " WHERE nombre = '" + user.getName() + "';";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, user.getType());
            preparedStatement.executeUpdate();

        } catch (SQLIntegrityConstraintViolationException e) {
            throw new CustomException("No puede modificar" + e.getMessage().replace("'", ""));
        } catch (SQLException e) {
            throw new CustomException("Consulta " + nombreTabla + " Error: " + e.getMessage().replace("'", ""));
        } finally {
            preparedStatement = null;
        }
    }

    public boolean searchForName(String username) throws CustomException {
        boolean bandera = false;
        try {

            String query = "SELECT nombre FROM " + nombreTabla
                    + " WHERE nombre =?;";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
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
            throw new CustomException("consulta " + nombreTabla + " dice: Ha ocurrido un error: " + e.toString());
        } finally {
            preparedStatement = null;
        }

    }

    public boolean validate(String username, String password, int userType) throws CustomException {
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

            throw new CustomException("consulta " + nombreTabla + " dice: Ha ocurrido un error: " + e.toString());

        } finally {
            preparedStatement = null;

        }

    }

}
