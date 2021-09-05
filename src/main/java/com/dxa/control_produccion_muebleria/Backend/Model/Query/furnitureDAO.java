/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dxa.control_produccion_muebleria.Backend.Model.Query;

import com.dxa.control_produccion_muebleria.Backend.Model.Clases.Exceptions.CustomException;
import com.dxa.control_produccion_muebleria.Backend.Model.Clases.furniture;
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
public class furnitureDAO {

    private Connection connection;
    private ResultSet resultSet;
    private PreparedStatement preparedStatement;

    private user user = null;
    private String nombreTabla = "MUEBLE";
    furniture furniture;

    private void createConnectionToDB() {
        DBconnection dBconnection = new DBconnection();
        this.connection = dBconnection.getConnection();
    }

    public furnitureDAO() {
        createConnectionToDB();
    }

    public List listAllData() throws CustomException {
        ArrayList<furniture> arrayList = new ArrayList<furniture>();
        try {
            String query = "SELECT * FROM " + nombreTabla + ";";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                furniture = new furniture();
                furniture.setName(resultSet.getString(1));
                furniture.setPrice(resultSet.getDouble(2) + "");
                arrayList.add(furniture);
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

    public boolean create(furniture furniture) throws SQLException, CustomException {
        try {

            String query = "INSERT INTO " + nombreTabla
                    + " (nombre,precio) "
                    + "VALUES(?, ?);";

            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, furniture.getName());
            preparedStatement.setDouble(2, furniture.getPrice());
            preparedStatement.executeUpdate();

        } catch (SQLIntegrityConstraintViolationException e) {
            throw new CustomException("Esta tratando de crear un tipo de mueble ya existente");
        } catch (SQLException e) {
            throw new CustomException("Consulta " + nombreTabla + " Error: " + e.toString());
        } finally {
            preparedStatement = null;
        }
        return true;
    }
}
