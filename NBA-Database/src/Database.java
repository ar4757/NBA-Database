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
    
    // Get team rank query
    public ArrayList<String> getTeamranks() throws SQLException {
        String sql = "SELECT team_abbreviation, count(team_result) as teamWin\r\n" + 
        		"FROM game\r\n" + 
        		"WHERE team_result = 'Win'\r\n" + 
        		"    AND game_date like '2012%'\r\n" + 
        		"GROUP BY team_abbreviation\r\n" + 
        		"ORDER BY teamwin DESC";
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
    
    //get player's rank query
    public ArrayList<String> getPlayerranks() throws SQLException {
        String sql = "SELECT player_name, play_position, round(sum(playast)/\r\n" + 
        		"    (SELECT max(sum(playast)) \r\n" + 
        		"    FROM player_stats \r\n" + 
        		"    WHERE game_date like '2012%' \r\n" + 
        		"    GROUP BY player_name, play_position) +\r\n" + 
        		"    sum(playpts)/\r\n" + 
        		"    (SELECT max(sum(playpts)) \r\n" + 
        		"    FROM player_stats \r\n" + 
        		"    WHERE game_date like '2012%' \r\n" + 
        		"    GROUP BY player_name, play_position) +\r\n" + 
        		"    sum(playFGM)/(sum(playFGA)+0.001),3) as rank_score\r\n" + 
        		"FROM player_stats\r\n" + 
        		"WHERE game_date like '2012%'\r\n" + 
        		"GROUP BY player_name, play_position\r\n" + 
        		"ORDER BY rank_score desc";
        ArrayList<String> playerNames = new ArrayList<String>();
        try (Connection connection = DriverManager.getConnection(databaseURL, user, password)) {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
            	playerNames.add(result.getString("player_name"));
            }
             
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        }      
        return playerNames;
    } 
    
    // Get the MVP of each match
    public ArrayList<String> getMVP() throws SQLException {
        String sql = "SELECT *\r\n" + 
        		"FROM\r\n" + 
        		"(SELECT player_name, round(\r\n" + 
        		"    0.25*playast/(SELECT max(playast) from player_stats )+\r\n" + 
        		"    0.5*playpts/(SELECT max(playpts) from player_stats )+\r\n" + 
        		"    0.25*play3pp/(SELECT max(play3pp) from player_stats ), 3) as MVAPRank\r\n" + 
        		"FROM player_stats2\r\n" + 
        		"WHERE game_date = '2012-10-30'\r\n" +   //the game date should change with input
        		"    AND game_time = '20:00'\r\n" + 	 //the game time should change
        		"    AND (team_abbreviation = 'MIA' OR oppose_abbreviation = 'MIA')\r\n" + //the team should change
        		"ORDER BY mvaprank desc)\r\n" + 
        		"WHERE rownum = 1";
        ArrayList<String> MVP = new ArrayList<String>();
        try (Connection connection = DriverManager.getConnection(databaseURL, user, password)) {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
            	MVP.add(result.getString("player_name"));
            }
             
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        }      
        return MVP;
    } 
    
    // Get the improvement of each player
    public ArrayList<String> getPlayerimpro() throws SQLException {
        String sql = "SELECT seasonyear, \r\n" + 
        		"    round(avg(playpts),3) as average_point, \r\n" + 
        		"    round(avg(playast),3) as average_assistant, \r\n" + 
        		"    round(avg(play3pp),3) as average_3point_precentage\r\n" + 
        		"FROM Improvement\r\n" + 
        		"WHERE player_name = 'LeBron James'\r\n" + 		//the name should be change with different input
        		"GROUP BY seasonyear\r\n" + 
        		"ORDER BY seasonyear";
        ArrayList<String> impro = new ArrayList<String>();
        try (Connection connection = DriverManager.getConnection(databaseURL, user, password)) {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
            	impro.add(result.getString("SEASONYEAR") + " " + result.getString("AVERAGE_POINT") + " " + result.getString("AVERAGE_ASSISTANT") + " " + result.getString("AVERAGE_3POINT_PRECENTAGE"));
            }
             
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        }      
        return impro;
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