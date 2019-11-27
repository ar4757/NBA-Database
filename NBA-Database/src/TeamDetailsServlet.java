import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
@WebServlet("/teamDetails")
public class TeamDetailsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
 
    public TeamDetailsServlet() {
        super();
    }
 
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Database database = new Database();
         
        try {
        	String team_abbreviation = request.getParameter("team_abbreviation");
        	Team team = database.getTeamWithAbbreviation(team_abbreviation);
            ArrayList<Player> players = database.getPlayersOnTeam(team);
            request.setAttribute("team", team);
            request.setAttribute("players", players);
             
            String page = "/teamDetails.jsp";
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(page);
            requestDispatcher.forward(request, response);              
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
         
    }
}