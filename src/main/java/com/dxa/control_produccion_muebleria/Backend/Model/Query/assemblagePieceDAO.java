/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dxa.control_produccion_muebleria.Backend.Model.Query;

import com.dxa.control_produccion_muebleria.Backend.Model.Clases.Exceptions.CustomException;
import com.dxa.control_produccion_muebleria.Backend.Model.Clases.assemblagePiece;
import com.dxa.control_produccion_muebleria.Backend.Model.Clases.piece;
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
public class assemblagePieceDAO {

    private Connection connection;
    private ResultSet resultSet;
    private PreparedStatement preparedStatement;

    private assemblagePiece assemblagePiece = null;
    private String nombreTabla = "ENSAMBLE_PIEZAS";

    private void createConnectionToDB() {
        DBconnection dBconnection = new DBconnection();
        this.connection = dBconnection.getConnection();
    }

    public assemblagePieceDAO() {
        createConnectionToDB();
    }

    public List listAllData() {
        ArrayList<assemblagePiece> arrayList = new ArrayList<assemblagePiece>();
        try {
            String query = "SELECT * FROM " + nombreTabla + ";";

            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                assemblagePiece = new assemblagePiece();
                assemblagePiece.setNamefurniture(resultSet.getString(1));
                assemblagePiece.setTypePiece(resultSet.getString(2));
                assemblagePiece.setAmountPieces(resultSet.getString(3));

                arrayList.add(assemblagePiece);
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

    public List listMaterials(String furnitureName) {
        ArrayList<assemblagePiece> arrayList = new ArrayList<assemblagePiece>();
        try {
            String query = "SELECT * FROM " + nombreTabla
                    + " WHERE mueble_nombre=?;";

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, furnitureName);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                assemblagePiece = new assemblagePiece();
                assemblagePiece.setNamefurniture(resultSet.getString(1));
                assemblagePiece.setTypePiece(resultSet.getString(2));
                assemblagePiece.setAmountPieces(resultSet.getString(3));

                arrayList.add(assemblagePiece);
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

    public boolean create(assemblagePiece assemblagePiece) throws SQLException, CustomException {
        try {

            String query = "INSERT INTO " + nombreTabla
                    + " (mueble_nombre,tipo_pieza,cantidad) "
                    + "VALUES(?, ?, ?);";
            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, assemblagePiece.getNamefurniture());
            preparedStatement.setString(2, assemblagePiece.getTypePiece());
            preparedStatement.setInt(3, assemblagePiece.getAmountPieces());

            preparedStatement.executeUpdate();

        } catch (SQLIntegrityConstraintViolationException e) {
            throw new CustomException("Esta tratando de crear un tipo de pieza ya existente");
        } catch (SQLException e) {
            throw new CustomException("Consulta " + nombreTabla + " Error: " + e.toString());
        } finally {
            preparedStatement = null;
        }
        return true;
    }

}
