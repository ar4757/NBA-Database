import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
@WebServlet("/GamePrediction")
public class GamePredictionServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
 
    public GamePredictionServlet() {
        super();
    }
 
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
 
    	Database database = new Database();
        
        try {
        	String team_away = request.getParameter("team_away");
        	String team_home = request.getParameter("team_home");
        	System.out.println(team_away);
        	System.out.println(team_home);
        	
            GamePrediction gamePrediction = database.predictGame(team_away, team_home);   
            
        	// get response writer
            PrintWriter writer = response.getWriter();
            
        	// build HTML code
            String htmlRespone = "<html>";
            htmlRespone += "<h2>" + gamePrediction.getWinner() + " are expected to win against " + gamePrediction.getLoser() + "<br/>"; 
            htmlRespone += "with a certainty of " + gamePrediction.getCertainty() + "%" + "<br/>";
            htmlRespone += "</html>";
             
            // return response
            writer.println(htmlRespone);
            
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
 
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Database database = new Database();
         
        try {
            ArrayList<Team> teams = database.getTeams();
            request.setAttribute("teams", teams);
             
            String page = "/GamePrediction.jsp";
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(page);
            requestDispatcher.forward(request, response);              
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
         
    }
}