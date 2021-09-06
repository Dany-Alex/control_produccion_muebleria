/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dxa.control_produccion_muebleria.Backend.Model.Query;

import com.dxa.control_produccion_muebleria.Backend.Model.Clases.Exceptions.CustomException;
import com.dxa.control_produccion_muebleria.Backend.Model.Clases.piece;
import com.dxa.control_produccion_muebleria.Backend.Model.Clases.sortPiece;
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
    private sortPiece sortPiece = null;
    private String nombreTabla = "PIEZA";

    private void createConnectionToDB() {
        DBconnection dBconnection = new DBconnection();
        this.connection = dBconnection.getConnection();
    }

    public pieceDAO() {
        createConnectionToDB();
    }

    /**
     * *
     *
     * @return retorna una lista con todas la piezas que tenemos almacenados en
     * nuestra tabla
     * @throws CustomException
     */
    public List listAllData() throws CustomException {
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
                piece.setAvailable(resultSet.getInt(4) + "");
                arrayList.add(piece);
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

    public List listAllDataAvailable() throws CustomException {
        ArrayList<piece> arrayList = new ArrayList<piece>();
        try {
            String query = "SELECT * FROM " + nombreTabla
                    + " WHERE utilizado!=1;";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                piece = new piece();
                piece.setId(resultSet.getInt(1) + "");
                piece.setType(resultSet.getString(2));
                piece.setCost(resultSet.getDouble(3) + "");
                piece.setAvailable(resultSet.getInt(4) + "");
                arrayList.add(piece);
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

    public List sortJoinTypePiece(String typeSort) throws CustomException {
        ArrayList<sortPiece> arrayList = new ArrayList<sortPiece>();
        try {
            String query;
            switch (typeSort) {
                case "MyMn":
                    query = "SELECT pieza.id_pieza,pieza.tipo_piezas,pieza.costo,tipo_pieza.stock"
                            + " FROM " + nombreTabla
                            + " INNER JOIN tipo_pieza"
                            + " ON pieza.tipo_piezas = tipo_pieza.nombre"
                            + " ORDER BY stock DESC;";
                    break;
                case "MnMy":
                    query = "SELECT pieza.id_pieza,pieza.tipo_piezas,pieza.costo,tipo_pieza.stock"
                            + " FROM " + nombreTabla
                            + " INNER JOIN tipo_pieza"
                            + " ON pieza.tipo_piezas = tipo_pieza.nombre"
                            + " ORDER BY stock ASC;";
                    break;
                default:
                    throw new CustomException("El tipo de dato no es compatible");
            }
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                sortPiece = new sortPiece();
                sortPiece.setId(resultSet.getInt(1));
                sortPiece.setType(resultSet.getString(2));
                sortPiece.setCost(resultSet.getDouble(3));
                sortPiece.setStock(resultSet.getInt(4));
                arrayList.add(sortPiece);
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
     *
     * @param type recibe el nombre del tipo de pieza a crear
     * @param cost recibe el costo de pieza a crear
     * @param typePiece recibe un objeto del tipo typePiece
     * @param quantity recibe la cantidad de piezas a crear
     * @throws SQLException
     * @throws CustomException
     */
    public void insert(String type, double cost, typePiece typePiece, int quantity) throws SQLException, CustomException {
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
            throw new CustomException("Esta tratando de crear una pieza ya existente");
        } catch (SQLException e) {
            int noError = e.getErrorCode();
            switch (noError) {
                case 1054:
                    throw new CustomException("Consulta " + nombreTabla + " Error: " + e.getMessage().replace("'", ""));
                default:
                    throw new CustomException("Consulta " + nombreTabla + " Error: " + e.getMessage() + " Estado SQL:" + e.getSQLState() + " Numero Error:" + e.getErrorCode());

            }

        } finally {
            preparedStatement = null;
        }
    }

    public void create(piece piece) throws SQLException, CustomException {
        typePiece = new typePiece();
        typePiece.setNameTypePiece(piece.getType());
        insert(piece.getType(), piece.getCost(), typePiece, 1);
    }

    /**
     * *
     *
     * @param id recibe el id de la pieza la cual deseamos buscar
     * @return retorna un objeto de tipo piece si en caso existe la pieza con el
     * id buscado
     * @throws CustomException devuelve un erro si en caso existe algun problema
     * al momento de ejecutar la busqueda
     */
    public piece searchByCode(String id) throws CustomException {
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
                piece.setAvailable(resultSet.getInt(4) + "");

            }
            resultSet.close();
            System.out.println((resultSet.isClosed()) ? "Resulset Cerrado" : "Resulset No Cerrado");
            return piece;
        } catch (Exception e) {
            throw new CustomException("Consulta " + nombreTabla + " Error: " + e.getMessage().replace("'", ""));
        } finally {
            preparedStatement = null;
        }

    }

    public void executeUpdate() throws SQLException {
        String query = "UPDATE " + nombreTabla
                + " SET tipo_piezas=?,costo=?,utilizado=?"
                + " WHERE id_pieza = '" + piece.getId() + "';";

        preparedStatement = connection.prepareStatement(query);

        preparedStatement.setString(1, piece.getType());
        preparedStatement.setDouble(2, piece.getCost());
        preparedStatement.setInt(3, piece.getAvailable());
        preparedStatement.executeUpdate();
    }

    /**
     * *
     *
     * @param piece recibe un objeto piece, que se utiliza para obtener los
     * datos de la pieza a la que se desea actulizar
     * @throws SQLException
     * @throws CustomException
     */
    public void update(piece piece) throws SQLException, CustomException {
        try {
            executeUpdate();
        } catch (SQLIntegrityConstraintViolationException e) {
            throw new CustomException("No puede modificar el id de la pieza");
        } catch (SQLException e) {
            throw new CustomException("Consulta " + nombreTabla + " Error: " + e.getMessage().replace("'", ""));
        } finally {
            preparedStatement = null;
        }
    }

    public void updateAviable(piece piece) throws SQLException, CustomException {
        try {
            typePiece = new typePiece();
            typePiece.setNameTypePiece(piece.getType());

            executeUpdate();

            typePieceDAO.updateStockUseOrDelete(typePiece, 1);
        } catch (SQLIntegrityConstraintViolationException e) {
            throw new CustomException("No puede modificar el id de la pieza");
        } catch (SQLException e) {
            throw new CustomException("Consulta " + nombreTabla + " Error: " + e.getMessage().replace("'", ""));
        } finally {
            preparedStatement = null;
        }
    }

    public void updateAviableRollback(piece piece) throws SQLException, CustomException {
        try {
            typePiece = new typePiece();
            typePiece.setNameTypePiece(piece.getType());

            executeUpdate();

            typePieceDAO.updateStockInsert(typePiece, 1);
        } catch (SQLIntegrityConstraintViolationException e) {
            throw new CustomException("No puede modificar el id de la pieza");
        } catch (SQLException e) {
            throw new CustomException("Consulta " + nombreTabla + " Error: " + e.getMessage().replace("'", ""));
        } finally {
            preparedStatement = null;
        }
    }

    /**
     * *
     *
     * @param id recibe un string que es el nombre de la pieza a eliminar
     * @throws SQLException
     * @throws CustomException
     */
    public void delete(String id) throws SQLException, CustomException {
        try {

            String query = "DELETE FROM " + nombreTabla
                    + " WHERE id_pieza = ? ;";
            typePiece = new typePiece();
            typePiece.setNameTypePiece(searchByCode(id).getType());
            System.out.println("++++" + typePiece.getNameTypePiece());

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, id);
            preparedStatement.executeUpdate();
            typePieceDAO.updateStockUseOrDelete(typePiece, 1);
        } catch (SQLIntegrityConstraintViolationException e) {
            throw new CustomException("Consulta " + nombreTabla + " Error: " + e.getMessage().replace("'", ""));
        } catch (SQLException e) {
            throw new CustomException("Consulta " + nombreTabla + " Error: " + e.getMessage().replace("'", ""));
        } finally {
            preparedStatement = null;
        }
    }
}
