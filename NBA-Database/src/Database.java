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
    
    public ArrayList<Team> getTeams() throws SQLException {
        String sql = "SELECT * FROM TEAM";
        ArrayList<Team> teams = new ArrayList<Team>();
        try (Connection connection = DriverManager.getConnection(databaseURL, user, password)) {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
            	Team newTeam = new Team(result.getString("TEAM_NAME"), result.getString("TEAM_ABBREVIATION"));
            	teams.add(newTeam);
            }
             
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        }      
        return teams;
    }
    
    // Get team's rank in 2012
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
    
    //Get player's rank in 2012
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
    
    // Get the MVP of each match, the example match at 2012-10-30-20:00 MIA VS ??, 
    //the match information should change with html input 
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
    
    // Get the improvement of each player, the example player is 'LeBron James';
    // Input changes it;
    // to achieve that, a new view Improvement is created.
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

    // Get the winning percentage of first team in history, the example teams are 'TOR' and 'BKN'
    // Input can change the two teams.
    public ArrayList<String> getwinpercen() throws SQLException {
        String sql = "SELECT team_abbreviation, \r\n" + 
        		"    round(count(team_result)/(SELECT count(team_result) \r\n" + 
        		"                              FROM game \r\n" + 
        		"                              WHERE team_abbreviation = 'TOR' \r\n" + 				// TEAM1 can be changed
        		"                              AND oppose_abbreviation = 'BKN'), 3) as HIS_WIN_PERCENTAGE\r\n" + //TEAM2 can be changed
        		"FROM game\r\n" + 
        		"WHERE team_abbreviation = 'TOR'\r\n" + 
        		"    AND oppose_abbreviation = 'BKN'\r\n" + 
        		"    AND team_result = 'Win'\r\n" + 
        		"GROUP BY team_abbreviation";
        ArrayList<String> winpercen = new ArrayList<String>();
        try (Connection connection = DriverManager.getConnection(databaseURL, user, password)) {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
            	winpercen.add(result.getString("HIS_WIN_PERCENTAGE"));
            }
             
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        }      
        return winpercen;
    }
    
    // Get the Cooperation of each team
    // A new table player_in_team is created to finish query Cooperation and Team Style
    public ArrayList<String> getCooperation() throws SQLException {
        String sql = "SELECT distinct team_abbreviation, round(teamwinscore - AVG(rank_score), 3) as Cooperation_rank\r\n" + 
        		"FROM team_rank natural join player_in_team natural join player_rank\r\n" + 
        		"GROUP BY team_abbreviation, teamwinscore\r\n" + 
        		"ORDER BY Cooperation_rank desc";
        ArrayList<String> Cooperation = new ArrayList<String>();
        try (Connection connection = DriverManager.getConnection(databaseURL, user, password)) {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
            	Cooperation.add(result.getString("TEAM_ABBREVIATION")+ "" + result.getString("COOPERATION_RANK"));
            }
             
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        }      
        return Cooperation;
    }
    
    // Get figure of the team style of each team, the example team is 'CLE';
    // Two new view TEAM_RANK and PLAYER_RANK are created in this part job
    public ArrayList<String> getTeamstyle() throws SQLException {
        String sql = "SELECT game_date, game_time, team_abbreviation, ROUND(avg(playorb), 3) AS OFFENSIVE,\r\n" + 
        		"    ROUND(avg(playdrb), 3) AS DEFENSIVE, ROUND(avg(playorb)/(avg(playdrb)+0.001), 2) AS OFFENSIVILITY\r\n" + 
        		"FROM player_in_team\r\n" + 
        		"WHERE team_abbreviation = 'CLE'\r\n" +    //The team_abbreviation can be changed
        		"GROUP BY game_date, game_time, team_abbreviation\r\n" + 
        		"ORDER BY game_date, game_time";
        ArrayList<String> Teamstyle = new ArrayList<String>();
        try (Connection connection = DriverManager.getConnection(databaseURL, user, password)) {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
            	Teamstyle.add(result.getString("GAME_DATE")+ "" + result.getString("GAME_TIME") + "" + result.getString("TEAM_ABBREVIATION") + "" + result.getString("OFFENSIVILITY"));
            }
             
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        }      
        return Teamstyle;
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