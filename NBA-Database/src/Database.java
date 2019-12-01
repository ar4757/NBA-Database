import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;


/*
 * Several VIEW table are created to simplify the SQL language:
 * Player_rank: it has attribute PLAYER_RANK, PLAYER_NAME, PLAY_POSITION, RANK_SCORE
 * 		SELECT * FROM PLAYER_RANK_2012
 * 		SELECT * FROM PLAYER_RANK_2013
 *  	SELECT * FROM PLAYER_RANK_2014
 * 		SELECT * FROM PLAYER_RANK_2015
 * 		SELECT * FROM PLAYER_RANK_2016
 * 		SELECT * FROM PLAYER_RANK_2017
 * 		SELECT * FROM PLAYER_RANK_2018
 * 		SELECT * FROM PLAYER_RANK_SEVENYEAR
 * Team_rank: it has attribute TEAM_RANK, TEAM_ABBREVIATION, TEAMWINSCORE
 * 		SELECT * FROM TEAM_RANK_2012
 * 		SELECT * FROM TEAM_RANK_2013
 * 		SELECT * FROM TEAM_RANK_2014
 * 		SELECT * FROM TEAM_RANK_2015
 * 		SELECT * FROM TEAM_RANK_2016
 * 		SELECT * FROM TEAM_RANK_2017
 * 		SELECT * FROM TEAM_RANK_2018
 * 		SELECT * FROM TEAM_RANK_SEVENYEAR
 * MVP: it has the attributes PLAYER_NAME, MVAPRANK
 * 		SELECT * FROM MVP2012
 * 		SELECT * FROM MVP2013
 * 		SELECT * FROM MVP2014
 * 		SELECT * FROM MVP2015
 * 		SELECT * FROM MVP2016
 * 		SELECT * FROM MVP2017
 * 		SELECT * FROM MVP2018
 * 
 * The relation of age and performance: it has attributes AGE, PERFORMANCE
 * 		SELECT * FROM AGE_PERFORM
 * 
 * The offensivility of one team in the whole seven year: it has attributes TEAM_ABBREVIATION, OFFENSIVE, DEFENSIVE, OFFENSIVILITY
 * When the offensivility > 0.5, it means the team prefer offensive, when offensivility < 0.5, means defensive
 * 
 * 		SELECT team_abbreviation, ROUND(avg(playorb), 3) AS OFFENSIVE,
 *			ROUND(avg(playdrb), 3) AS DEFENSIVE, ROUND(avg(playorb)/(avg(playdrb)+0.001), 2) AS OFFENSIVILITY
 *		FROM player_in_team
 *		WHERE team_abbreviation = 'CLE'
 *		GROUP BY team_abbreviation
 * 
 * The rank change of each team: it has attributes TEAM_ABBREVIATION, TEAMRANK2012, TEAMRANK2013, 
 * 								 TEAMRANK2014, TEAMRANK2015, TEAMRANK2016, TEAMRANK2017, TEAMRANK2017
 * 
 * 		SELECT t1.team_abbreviation, t1.TEAM_RANK as teamrank2012, t2.TEAM_RANK as teamrank2013, 
        	   t3.TEAM_RANK as teamrank2014, t4.TEAM_RANK as teamrank2015, t5.TEAM_RANK as teamrank2016, 
       		   t6.TEAM_RANK as teamrank2017, t7.TEAM_RANK as teamrank2018
		FROM team_rank_2012 t1, team_rank_2013 t2, team_rank_2014 t3, 
     		 team_rank_2015 t4, team_rank_2016 t5, team_rank_2017 t6, 
     		 team_rank_2018 t7
		WHERE   t1.team_abbreviation = t2.team_abbreviation
    		AND t2.team_abbreviation = t3.team_abbreviation
    		AND t3.team_abbreviation = t4.team_abbreviation
    		AND t4.team_abbreviation = t5.team_abbreviation
    		AND t5.team_abbreviation = t6.team_abbreviation
    		AND t6.team_abbreviation = t7.team_abbreviation
 * 
 * 
*/








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
        String sql = "SELECT team_name, team_abbreviation, \r\n" + 
        		"       round(offen/(SELECT max(offen) from team_style), 3) as OFFENSIVE, \r\n" + 
        		"       round(defen/(SELECT max(defen) FROM team_style), 3) as DEFENSIVE, \r\n" + 
        		"       ROUND(\r\n" + 
        		"             ((offen/(SELECT max(offen) from team_style))\r\n" + 
        		"              /\r\n" + 
        		"             ((defen/(SELECT max(defen) FROM team_style))+0.001))\r\n" + 
        		"            , 2)/2 AS OFFENSIVILITY\r\n" + 
        		"FROM team_style";
        ArrayList<Team> teams = new ArrayList<Team>();
        try (Connection connection = DriverManager.getConnection(databaseURL, user, password)) {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
            	Team newTeam = new Team(result.getString("TEAM_NAME"), result.getString("TEAM_ABBREVIATION"), result.getString("OFFENSIVILITY"));
            	teams.add(newTeam);
            }
             
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        }      
        return teams;
    }
    
    public Team getTeamWithAbbreviation(String team_abbreviation) throws SQLException {
        String sql = "SELECT * FROM TEAM WHERE TEAM_ABBREVIATION = '" + team_abbreviation + "'";
        Team team;
        try (Connection connection = DriverManager.getConnection(databaseURL, user, password)) {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet result = statement.executeQuery();
            result.next();
            team = new Team(result.getString("TEAM_NAME"), result.getString("TEAM_ABBREVIATION"), "");             
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        }      
        return team;
    }
    
    public GamePrediction predictGame(String team_away, String team_home) throws SQLException {
    	String sql = "SELECT TEAM_ABBREVIATION, OPPOSE_ABBREVIATION,\n" + 
    			"    count(case when TEAM_RESULT = 'Win' then 1 else null end) as wins,\n" + 
    			"    count(case when TEAM_RESULT = 'Loss' then 1 else null end) as losses\n" + 
    			"FROM GAME\n" + 
    			"WHERE TEAM_ABBREVIATION = '" + team_away + "'\n" + 
    			"    AND TEAM_LOCATION = 'Away'\n" + 
    			"    AND OPPOSE_ABBREVIATION = '" + team_home + "'\n" + 
    			"GROUP BY TEAM_ABBREVIATION, OPPOSE_ABBREVIATION";
        GamePrediction gamePrediction;
        try (Connection connection = DriverManager.getConnection(databaseURL, user, password)) {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet result = statement.executeQuery();
            result.next();
            String wins = result.getString("wins");
            String losses = result.getString("losses");
            int winInt = Integer.parseInt(wins);
            int lossInt = Integer.parseInt(losses);
            System.out.println("Win: " + winInt + " " + "Loss: " + lossInt);
            //Away team wins
            if (winInt > lossInt) {
                Double certainty = 100.0 * winInt / (winInt + lossInt);
                certainty = (double) Math.round(certainty * 10) / 10;
                gamePrediction = new GamePrediction(team_away, team_home, certainty);
            }
            //Home team wins
            else if (lossInt > winInt) {
                Double certainty = 100.0 * lossInt / (winInt + lossInt);
                certainty = (double) Math.round(certainty * 10) / 10;
                gamePrediction = new GamePrediction(team_home, team_away, certainty);
            }
            //Tie
            else {
            	Double certainty = 50.0;
            	certainty = (double) Math.round(certainty * 10) / 10;
                gamePrediction = new GamePrediction(team_away, team_home, certainty);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        }      
        return gamePrediction;
    }
    
    public ArrayList<Player> getPlayersOnTeam(Team team) throws SQLException {
        String sql = "SELECT DISTINCT PLAYER_NAME, PLAYER_HEIGHT, PLAYER_WEIGHT, PLAYER_BIRTHDAY FROM PLAYER_STATS NATURAL JOIN PLAYER WHERE TEAM_ABBREVIATION = '" + team.getAbbreviation() + "'";
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
    

    
    // Get team's rank in 2012
    public ArrayList<TEAM_RANK_2012> getTeamranks() throws SQLException {
        String sql = "SELECT * FROM TEAM_RANK_2012";
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
    public ArrayList<PlayerImprovement> getPlayersWithImprovement() throws SQLException {
        String sql = "SELECT * FROM\n" + 
        		"    (SELECT player_name as before_player_name,\n" + 
        		"    round(avg(playpts),3) as before_average_point,\n" + 
        		"    round(avg(playast),3) as before_average_assist,\n" + 
        		"    round(avg(play3pp),3) as before_average_3pt\n" + 
        		"    FROM Improvement\n" + 
        		"    WHERE seasonyear = '2012'\n" + 
        		"    GROUP BY player_name),\n" + 
        		"    (SELECT player_name as after_player_name,\n" + 
        		"    round(avg(playpts),3) as after_average_point,\n" + 
        		"    round(avg(playast),3) as after_average_assist,\n" + 
        		"    round(avg(play3pp),3) as after_average_3pt\n" + 
        		"    FROM Improvement\n" + 
        		"    WHERE seasonyear = '2018'\n" + 
        		"    GROUP BY player_name)\n" + 
        		"WHERE before_player_name = after_player_name";
        ArrayList<PlayerImprovement> impro = new ArrayList<PlayerImprovement>();
        try (Connection connection = DriverManager.getConnection(databaseURL, user, password)) {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
            	PlayerImprovement newPlayer = new PlayerImprovement(result.getString("after_player_name"), result.getString("before_average_point"), result.getString("before_average_assist"), result.getString("before_average_3pt"), result.getString("after_average_point"), result.getString("after_average_assist"), result.getString("after_average_3pt"));
            	impro.add(newPlayer);
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
    
    public ArrayList<AgePerform> getAgePerform() throws SQLException {
        String sql = "SELECT * FROM AGE_PERFORM";
        ArrayList<AgePerform> agePerforms = new ArrayList<AgePerform>();
        try (Connection connection = DriverManager.getConnection(databaseURL, user, password)) {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
            	AgePerform new_age_perform = new AgePerform(result.getString("AGE"), result.getString("PERFORMANCE"));
            	agePerforms.add(new_age_perform);
            }
             
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        }      
        return agePerforms;
    }
    
    public ArrayList<HeightPerform> getHeightPerform() throws SQLException {
        String sql = "SELECT * FROM HEIGHT_PERFORM";
        ArrayList<HeightPerform> heightPerforms = new ArrayList<HeightPerform>();
        try (Connection connection = DriverManager.getConnection(databaseURL, user, password)) {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
            	HeightPerform new_height_perform = new HeightPerform(result.getString("HEIGHT"), result.getString("PERFORMANCE"));
            	heightPerforms.add(new_height_perform);
            }
             
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        }      
        return heightPerforms;
    }
    
    public int getTupleCount() throws SQLException {
    	int tupleCount = 0;
		tupleCount += getTupleCountForTable("PLAYER");
		tupleCount += getTupleCountForTable("TEAM");
		tupleCount += getTupleCountForTable("GAME");
		tupleCount += getTupleCountForTable("PLAYER_STATS");
    	return tupleCount;
    }
    
    private int getTupleCountForTable(String tableName) throws SQLException {
    	int tupleCount = 0;
    	try (Connection connection = DriverManager.getConnection(databaseURL, user, password)) {
	    	String sql = "SELECT COUNT(*) AS ROW_COUNT FROM " + tableName;
	        PreparedStatement statement = connection.prepareStatement(sql);
	        ResultSet result = statement.executeQuery();
	        result.next();
	        tupleCount = result.getInt("ROW_COUNT");
    	}
    	catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        }
    	return tupleCount;
    }
}