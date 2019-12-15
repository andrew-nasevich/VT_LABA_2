package controller;

import bean.Client;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.sql.*;

public class DBController {

    private static final Logger logger = Logger.getLogger(DBController.class);

    private static final String PUT_CLIENT_IN_HOTEL_QUERY = "INSERT INTO clients (id,name,birthday,medicalHistory) VALUES (?,?,?,?);";
    private static final String UPDATE_CLIENT_IN_HOTEL_QUERY = "UPDATE clients SET name = ? WHERE id = ?";
    private static final String DELETE_CLIENT_FROM_HOTEL_QUERY = "DELETE FROM clients WHERE id = ?";

    public boolean addInDB(Client client){
        boolean result = false;

        PropertyConfigurator.configure("C:\\Users\\Lenovo\\IdeaProjects\\VT_JAVA_2\\src\\resources\\log4j.properties");
        String url = "jdbc:mysql://localhost/java?serverTimezone=Europe/Moscow&useSSL=false";
        String username = "root";
        String password = "";

        try (Connection conn = DriverManager.getConnection(url, username, password)){
            PreparedStatement stmt = null;
            try {
                stmt = conn.prepareStatement(PUT_CLIENT_IN_HOTEL_QUERY);
            }
            catch (SQLException e) {
                logger.error(e.getMessage());
            }
            try {
                stmt.setInt(1, client.getId());
                stmt.setString(2, client.getName());
                stmt.setString(3, client.getBirthday());
                stmt.setString(4, client.getMedicalFile().getMedicalHistory());


                if(stmt.executeUpdate() == 1){
                    result = true;
                }

            }
            catch (SQLException e) {
                logger.error(e.getMessage());
                throw new RuntimeException(e);
            }
            finally{
                conn.close();
            }
        }
        catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return result;
    }

    public boolean updateInDB(Client client){
        boolean result = false;
        String url = "jdbc:mysql://localhost/java";
        String username = "root";
        String password = "";
        try (Connection conn = DriverManager.getConnection(url, username, password)){
            PreparedStatement stmt = null;
            try {
                stmt = conn.prepareStatement(UPDATE_CLIENT_IN_HOTEL_QUERY);
            }
            catch (SQLException e) {
                logger.error(e.getMessage());
            }
            try {
                stmt.setString(1, client.getName());
                stmt.setInt(2, client.getId());

                if(stmt.executeUpdate() == 1){
                    result = true;
                }
            }
            catch (SQLException e) {
                logger.error(e.getMessage());
                throw new RuntimeException(e);
            }
            finally{
                conn.close();
            }
        }
        catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return result;
    }

    public boolean deleteFromDB(int id){
        boolean result = false;
        String url = "jdbc:mysql://localhost/java";
        String username = "root";
        String password = "";
        try (Connection conn = DriverManager.getConnection(url, username, password)){
            PreparedStatement stmt = null;
            try {
                stmt = conn.prepareStatement(DELETE_CLIENT_FROM_HOTEL_QUERY);
            }
            catch (SQLException e) {
                logger.error(e.getMessage());
            }
            try {
                stmt.setInt(1, id);

                if(stmt.executeUpdate() == 1){
                    result = true;
                }
            }
            catch (SQLException e) {
                logger.error(e.getMessage());
                throw new RuntimeException(e);
            }
            finally{
                conn.close();
            }
        }
        catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return result;
    }
}