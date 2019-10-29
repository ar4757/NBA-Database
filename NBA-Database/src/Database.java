import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
 
public class Database {
    String databaseURL = "jdbc:oracle:thin:@oracle.cise.ufl.edu:1521:orcl";
    String user = "linyang";
    String password = "82730430";
     
    public ArrayList<String> getPlayers() throws SQLException {
        String sql = "SELECT * FROM PLAYER";
        ArrayList<String> playerNames = new ArrayList<String>();
        try (Connection connection = DriverManager.getConnection(databaseURL, user, password)) {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
            	playerNames.add(result.getString("PLAYER_NAME"));
            }
             
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        }      
        return playerNames;
    }
    
    public ArrayList<String> getTeams() throws SQLException {
        String sql = "SELECT * FROM TEAM";
        ArrayList<String> teamNames = new ArrayList<String>();
        try (Connection connection = DriverManager.getConnection(databaseURL, user, password)) {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
            	teamNames.add(result.getString("TEAM_NAME"));
            }
             
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        }      
        return teamNames;
    }
}