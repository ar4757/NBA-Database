import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
 
public class Database {
    String databaseURL = "jdbc:oracle:thin:@oracle.cise.ufl.edu:1521:orcl";
    String user = "linyang";
    String password = "82730430";
     
    public ArrayList<Player> getPlayers() throws SQLException {
        String sql = "SELECT * FROM PLAYER";
        ArrayList<Player> players = new ArrayList<Player>();
        try (Connection connection = DriverManager.getConnection(databaseURL, user, password)) {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
            	Player newPlayer = new Player(result.getString("PLAYER_NAME"), result.getString("PLAYER_HEIGHT"), result.getString("PLAYER_WEIGHT"), result.getString("PLAYER_BIRTHDAY"));
            	players.add(newPlayer);
            }
             
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        }      
        return players;
    }
    
    public ArrayList<String> getTeams() throws SQLException {
        String sql = "SELECT * FROM TEAM";
        ArrayList<String> teamNames = new ArrayList<String>();
        try (Connection connection = DriverManager.getConnection(databaseURL, user, password)) {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
            	teamNames.add(result.getString("TEAM_ABBREVIATION"));
            }
             
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        }      
        return teamNames;
    }
    
    public ArrayList<String> getGames() throws SQLException {
        String sql = "SELECT * FROM GAME";
        ArrayList<String> games = new ArrayList<String>();
        try (Connection connection = DriverManager.getConnection(databaseURL, user, password)) {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
            	games.add(result.getString("GAME_DATE") + " " + result.getString("GAME_TIME") + " " + result.getString("TEAM_ABBREVIATION") + " " + result.getString("TEAM_RESULT") + " against " + result.getString("OPPOSE_ABBREVIATION"));
            }
             
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        }      
        return games;
    }
}