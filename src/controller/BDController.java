package controller;

/*import bean.Client;
import org.apache.log4j.Logger;
import java.sql.*;

public class DBController {
    private static final Logger logger = Logger.getLogger(DBController.class);

    private static final String PUT_CLIENT_IN_HOTEL_QUERY = "INSERT INTO clients (id_client,name,surname,phone,room_number,room_cost) VALUES (?,?,?,?,?,?);";
    private static final String UPDATE_CLIENT_IN_HOTEL_QUERY = "UPDATE clients SET surname = ? WHERE id_client = ?";
    private static final String DELETE_CLIENT_FROM_HOTEL_QUERY = "DELETE FROM clients WHERE id_client = ?";

    public boolean addInDB(Client client){
        boolean result = false;

        String url = "jdbc:mysql://alionashm/hotel_clients?serverTimezone=Europe/Minsk&useSSL=false";
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
                stmt.setString(3, client.getSurname());
                stmt.setString(4, client.getMobilePhone());
                stmt.setInt(5, client.getRoom().getNumberOfRoom());
                stmt.setDouble(6, client.getRoom().getPrice());


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
        String url = "jdbc:mysql://alionashm/hotel_clients?serverTimezone=Europe/Minsk&useSSL=false";
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
                stmt.setString(1, client.getSurname());
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
        String url = "jdbc:mysql://alionashm/hotel_clients?serverTimezone=Europe/Minsk&useSSL=false";
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
}*/