/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dxa.control_produccion_muebleria.Backend.Model.Query;

import com.dxa.control_produccion_muebleria.Backend.Model.Clases.Exceptions.CustomException;
import com.dxa.control_produccion_muebleria.Backend.Model.Clases.assembleFurniture;
import com.dxa.control_produccion_muebleria.Backend.Model.Clases.sortPiece;
import com.dxa.control_produccion_muebleria.Backend.Model.Connection.DBconnection;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Artist
 */
public class assembleFornitureDAO {

    private Connection connection;
    private ResultSet resultSet;
    private PreparedStatement preparedStatement;

    private assembleFurniture assembleFurniture = null;
    private String nombreTabla = "ENSAMBLAR_MUEBLE";

    public assembleFornitureDAO() {
        createConnectionToDB();
    }

    private void createConnectionToDB() {
        DBconnection dBconnection = new DBconnection();
        this.connection = dBconnection.getConnection();
    }

    public boolean create(assembleFurniture assembleFurniture) throws SQLException, CustomException {
        try {

            String query = "INSERT INTO " + nombreTabla
                    + " (usuario,mueble,costo,fecha) "
                    + "VALUES(?,?,?,?);";
            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, assembleFurniture.getUser());
            preparedStatement.setString(2, assembleFurniture.getFurniture());
            preparedStatement.setDouble(3, assembleFurniture.getCost());
            preparedStatement.setDate(4, assembleFurniture.getDateSQL());
            preparedStatement.executeUpdate();

            return true;
        } catch (SQLIntegrityConstraintViolationException e) {
            throw new CustomException("Esta tratando de crear un tipo de mueble ya existente");
        } catch (SQLException e) {
            throw new CustomException("Consulta " + nombreTabla + " Error: " + e.toString());
        } catch (Exception e) {
            throw new CustomException("Consulta " + nombreTabla + " Error: " + e.toString());
        } finally {
            preparedStatement = null;
        }

    }

    public List sortJoinDate(String typeSort) throws CustomException {
        ArrayList<assembleFurniture> arrayList = new ArrayList<assembleFurniture>();
        try {
            String query;
            switch (typeSort) {
                case "MyMn":
                    query = "SELECT usuario,mueble, costo,fecha"
                            + " FROM " + nombreTabla
                            + " ORDER BY fecha DESC;";
                    break;
                case "MnMy":
                    query = "SELECT usuario,mueble, costo,fecha"
                            + " FROM " + nombreTabla
                            + " ORDER BY fecha ASC;";
                    break;
                default:
                    throw new CustomException("El tipo de dato no es compatible");
            }
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                assembleFurniture = new assembleFurniture();
                assembleFurniture.setUser(resultSet.getString(1));
                assembleFurniture.setFurniture(resultSet.getString(2));
                assembleFurniture.setCost(resultSet.getDouble(3) + "");
                assembleFurniture.setDate(resultSet.getDate(4) + "");
                arrayList.add(assembleFurniture);
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
}
