/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dxa.control_produccion_muebleria.Backend.Model.Query;

import com.dxa.control_produccion_muebleria.Backend.Model.Clases.assemblagePiece;
import com.dxa.control_produccion_muebleria.Backend.Model.Connection.DBconnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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

}
