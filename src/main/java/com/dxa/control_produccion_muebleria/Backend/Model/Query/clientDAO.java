/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dxa.control_produccion_muebleria.Backend.Model.Query;

import com.dxa.control_produccion_muebleria.Backend.Model.Clases.Exceptions.CustomException;
import com.dxa.control_produccion_muebleria.Backend.Model.Clases.client;
import com.dxa.control_produccion_muebleria.Backend.Model.Clases.user;
import com.dxa.control_produccion_muebleria.Backend.Model.Connection.DBconnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

/**
 *
 * @author Artist
 */
public class clientDAO {

    private Connection connection;
    private ResultSet resultSet;
    private PreparedStatement preparedStatement;

    private client client = null;
    private String nombreTabla = "CLIENTE";

    private void createConnectionToDB() {
        DBconnection dBconnection = new DBconnection();
        this.connection = dBconnection.getConnection();
    }

    public clientDAO() {
        createConnectionToDB();
    }

    public boolean create(client client) throws SQLException, CustomException {
        try {
            String query = "INSERT INTO " + nombreTabla
                    + " (nit,nombre,direccion,municipio,departamento) "
                    + "VALUES(?, ?, ?, ?, ?);";

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, client.getNit());
            preparedStatement.setString(2, client.getName());
            preparedStatement.setString(3, client.getAddress());
            preparedStatement.setString(4, client.getDepartamento());
            preparedStatement.setString(5, client.getMunicipio());

            preparedStatement.executeUpdate();
        } catch (SQLIntegrityConstraintViolationException e) {
            throw new CustomException("El nit: " + client.getNit() + " ya existe");
        } catch (SQLException e) {
            throw new CustomException("Consulta " + nombreTabla + " Error: " + e.getMessage());
        } finally {
            preparedStatement = null;
        }
        return true;
    }
}
