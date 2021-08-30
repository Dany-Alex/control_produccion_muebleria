package com.dxa.control_produccion_muebleria.Backend.Model.Query;

import com.dxa.control_produccion_muebleria.Backend.Model.Clases.Exceptions.exceptionPiece;
import com.dxa.control_produccion_muebleria.Backend.Model.Clases.piece;
import com.dxa.control_produccion_muebleria.Backend.Model.Clases.typePiece;
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
public class typePieceDAO {

    private Connection connection;
    private ResultSet resultSet;
    private PreparedStatement preparedStatement;

    private typePiece typePiece = null;
    private String nombreTabla = "TIPO_PIEZA";

    private void createConnectionToDB() {
        DBconnection dBconnection = new DBconnection();
        this.connection = dBconnection.getConnection();
    }

    public typePieceDAO() {
        createConnectionToDB();
    }

    public List listAllData() {
        ArrayList<typePiece> arrayList = new ArrayList<typePiece>();
        try {
            String query = "SELECT * FROM " + nombreTabla + ";";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                typePiece = new typePiece();
                typePiece.setNameTypePiece(resultSet.getString(1));
                typePiece.setStock(resultSet.getInt(2));
                arrayList.add(typePiece);
            }
            resultSet.close();
            System.out.println((resultSet.isClosed()) ? "Resulset Cerrado" : "Resulset No Cerrado");
            return arrayList;

        } catch (Exception e) {
            System.out.println("consulta " + nombreTabla + " dice: Ha ocurrido un error: " + e.toString());
            return null;
        } finally {
            preparedStatement = null;
        }
    }

    public boolean searchNameTypePiece(String nameTypePiece) {
        boolean existe = false;
        try {

            String query = "SELECT nombre FROM " + nombreTabla
                    + " WHERE nombre =?;";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, nameTypePiece);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                existe = true;
            } else {
                existe = false;
            }
            resultSet.close();
            System.out.println((resultSet.isClosed()) ? "Resulset Cerrado" : "Resulset No Cerrado");
            return existe;
        } catch (Exception e) {
            System.out.println("consulta " + nombreTabla + " dice: Ha ocurrido un error: " + e.toString());

        } finally {
            preparedStatement = null;
        }
        return existe;
    }

    public int searchStockTypePiece(String nameTypePiece) {
        try {

            String query = "SELECT stock FROM " + nombreTabla
                    + " WHERE nombre =?;";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, nameTypePiece);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                typePiece = new typePiece();
                typePiece.setStock(resultSet.getInt(1));
            }
            resultSet.close();
            System.out.println((resultSet.isClosed()) ? "Resulset Cerrado" : "Resulset No Cerrado");
            return typePiece.getStock();
        } catch (Exception e) {
            System.out.println("consulta " + nombreTabla + " dice: Ha ocurrido un error: " + e.toString());
            return 0;
        } finally {
            preparedStatement = null;
        }

    }

    /**
     * *
     *
     * @param typePiece recibe un objeto typePiece, que se utiliza para obtener
     * los datos para la creacion de un nuevo tipo de piza
     * @return
     * @throws SQLException
     * @throws exceptionPiece
     */
    public boolean create(typePiece typePiece) throws SQLException, exceptionPiece {
        try {

            String query = "INSERT INTO " + nombreTabla
                    + " (nombre,stock) "
                    + "VALUES(?, ?);";

            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, typePiece.getNameTypePiece());
            preparedStatement.setDouble(2, typePiece.getStock());
            preparedStatement.executeUpdate();

        } catch (SQLIntegrityConstraintViolationException e) {
            throw new exceptionPiece("Esta tratando de crear un tipo de pieza ya existente");
        } catch (SQLException e) {
            throw new exceptionPiece("Consulta " + nombreTabla + " Error: " + e.toString());
        } finally {
            preparedStatement = null;
        }
        return true;
    }

    /**
     * *
     *
     * @param typePiece recibe un objeto typePiece, que se utiliza para obtener
     * los datos de la pieza a la que se le actualizara el stock
     * @param quantity recibe la cantidad a actualizar en el stock, en este caso
     * se incremetara el stock dependiendo de la cantida porque esta funcion es
     * usada para la creacion de nuevas piezas
     * @return retorna un booleano que indica si se completo o no la operacion
     * realizada
     * @throws exceptionPiece crea un excepcion si algo sale mal al mumenro de
     * realizar la actulizacion del stock
     */
    public boolean updateStockInsert(typePiece typePiece, int quantity) throws exceptionPiece {
        try {

            String query = "UPDATE " + nombreTabla
                    + " SET stock=stock+?"
                    + " WHERE nombre = '" + typePiece.getNameTypePiece() + "';";

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, quantity);
            preparedStatement.executeUpdate();
            return true;
        } catch (Exception e) {
            throw new exceptionPiece("Consulta " + nombreTabla + " Error: " + e.toString());
        } finally {

            preparedStatement = null;
        }

    }

    /**
     * *
     *
     * @param typePiece recibe un objeto typePiece, que se utiliza para obtener
     * los datos de la pieza a la que se le actualizara el stock
     * @param quantity recibe la cantidad a actualizar en el stock, en este caso
     * se restara la cantidad ya que esta funcion se utiliza para cuando se
     * elimina alguna pieza o si es utilizada
     * @return retorna un booleano que indica si se completo o no la operacion
     * realizada
     * @throws exceptionPiece crea un excepcion si algo sale mal al mumenro de
     * realizar la actulizacion del stock
     */
    public boolean updateStockUseOrDelete(typePiece typePiece, int quantity) throws exceptionPiece {
        try {

            String query = "UPDATE " + nombreTabla
                    + " SET stock=stock-?"
                    + " WHERE nombre = '" + typePiece.getNameTypePiece() + "';";

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, quantity);
            preparedStatement.executeUpdate();
            return true;
        } catch (Exception e) {
            throw new exceptionPiece("Consulta " + nombreTabla + " Error: " + e.toString());
        } finally {

            preparedStatement = null;
        }

    }

    /**
     * *
     *
     * @param name recibe un string que es el nombre del tipo de pieza a
     * eliminar
     * @throws SQLException
     * @throws exceptionPiece
     */
    public void delete(String name) throws SQLException, exceptionPiece {
        try {
            String query = "DELETE FROM " + nombreTabla
                    + " WHERE nombre = ? ;";

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.executeUpdate();

        } catch (SQLIntegrityConstraintViolationException e) {
            throw new exceptionPiece("Consulta " + nombreTabla + " Error: " + e.getMessage().replace("'", ""));
        } catch (SQLException e) {
            throw new exceptionPiece("Consulta " + nombreTabla + " Error: " + e.getMessage().replace("'", ""));
        } finally {
            preparedStatement = null;
        }
    }

}
