/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
public class pieceDAO {

    private Connection connection;
    private ResultSet resultSet;
    private PreparedStatement preparedStatement;
    private typePiece typePiece;
    private typePieceDAO typePieceDAO = new typePieceDAO();
    private piece piece = null;
    private String nombreTabla = "PIEZA";

    private void createConnectionToDB() {
        DBconnection dBconnection = new DBconnection();
        this.connection = dBconnection.getConnection();
    }

    public pieceDAO() {
        createConnectionToDB();
    }

    public List listAllData() throws exceptionPiece {
        ArrayList<piece> arrayList = new ArrayList<piece>();
        try {
            String query = "SELECT * FROM " + nombreTabla + ";";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                piece = new piece();
                piece.setId(resultSet.getInt(1) + "");
                piece.setType(resultSet.getString(2));
                piece.setCost(resultSet.getDouble(3) + "");
                arrayList.add(piece);
            }
            resultSet.close();
            System.out.println((resultSet.isClosed()) ? "Resulset Cerrado" : "Resulset No Cerrado");
            return arrayList;
        } catch (Exception e) {
            throw new exceptionPiece("Consulta " + nombreTabla + " Error: " + e.getMessage().replace("'", ""));
        } finally {
            preparedStatement = null;
        }
    }

    /**
     *
     * @param type recibe el nombre del tipo de pieza a crear
     * @param cost recibe el costo de pieza a crear
     * @param typePiece recibe un objeto del tipo typePiece
     * @param quantity recibe la cantidad de piezas a crear
     * @throws SQLException
     * @throws exceptionPiece
     */
    public void insert(String type, double cost, typePiece typePiece, int quantity) throws SQLException, exceptionPiece {
        try {
            for (int i = 0; i < quantity; i++) {
                String query = "INSERT INTO " + nombreTabla
                        + " (tipo_piezas,costo) "
                        + "VALUES(?, ?);";
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, type);
                preparedStatement.setDouble(2, cost);
                preparedStatement.executeUpdate();
                typePieceDAO.updateStockInsert(typePiece, 1);
            }

        } catch (SQLIntegrityConstraintViolationException e) {
            throw new exceptionPiece("Esta tratando de crear una pieza ya existente");
        } catch (SQLException e) {
            int noError = e.getErrorCode();
            switch (noError) {
                case 1054:
                    throw new exceptionPiece("Consulta " + nombreTabla + " Error: " + e.getMessage().replace("'", ""));
                default:
                    throw new exceptionPiece("Consulta " + nombreTabla + " Error: " + e.getMessage() + " Estado SQL:" + e.getSQLState() + " Numero Error:" + e.getErrorCode());

            }

        } finally {
            preparedStatement = null;
        }
    }

    public void create(piece piece) throws SQLException, exceptionPiece {
        typePiece = new typePiece();
        typePiece.setNameTypePiece(piece.getType());
        insert(piece.getType(), piece.getCost(), typePiece, 1);
    }

    public piece searchByCode(String id) throws exceptionPiece {
        try {

            String query = "SELECT * FROM " + nombreTabla
                    + " WHERE id_pieza =?;";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                piece = new piece();
                piece.setId(resultSet.getInt(1) + "");
                piece.setType(resultSet.getString(2));
                piece.setCost(resultSet.getDouble(3) + "");
            }
            resultSet.close();
            System.out.println((resultSet.isClosed()) ? "Resulset Cerrado" : "Resulset No Cerrado");
            return piece;
        } catch (Exception e) {
            throw new exceptionPiece("Consulta " + nombreTabla + " Error: " + e.getMessage().replace("'", ""));
        } finally {
            preparedStatement = null;
        }

    }

    public void update(piece piece) throws SQLException, exceptionPiece {
        try {

            String query = "UPDATE " + nombreTabla
                    + " SET tipo_piezas=?,costo=?"
                    + " WHERE id_pieza = '" + piece.getId() + "';";

            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, piece.getType());
            preparedStatement.setDouble(2, piece.getCost());
            preparedStatement.executeUpdate();

        } catch (SQLIntegrityConstraintViolationException e) {
            throw new exceptionPiece("No puede modificar el id de la pieza");
        } catch (SQLException e) {
            throw new exceptionPiece("Consulta " + nombreTabla + " Error: " + e.getMessage().replace("'", ""));
        } finally {
            preparedStatement = null;
        }
    }

    public void delete(String codigo) throws SQLException, exceptionPiece {
        try {
            String query = "DELETE FROM " + nombreTabla
                    + " WHERE id_pieza = ? ;";

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, codigo);
            preparedStatement.executeUpdate();

        } catch (SQLIntegrityConstraintViolationException e) {
            throw new exceptionPiece("Consulta " + nombreTabla + " Error: " + e.getMessage().replace("'", ""));
        } catch (SQLException e) {
            throw new exceptionPiece("Consulta " + nombreTabla + " Error: " + e.getMessage().replace("'", ""));
        } catch (Exception e) {
            throw new exceptionPiece("Consulta " + nombreTabla + " Error: " + e.getMessage().replace("'", ""));
        } finally {
            preparedStatement = null;
        }
    }
}
