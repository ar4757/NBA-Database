import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
@WebServlet("/teamRank2016")
public class TeamRank2016Servlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
 
    public TeamRank2016Servlet() {
        super();
    }
 
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Database database = new Database();
         
        try {
            ArrayList<TeamRank> teamRanks = database.getTeamRanks("2016");
             
            request.setAttribute("teamRanks", teamRanks);
             
            String page = "/TeamRank2016.jsp";
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(page);
            requestDispatcher.forward(request, response);              
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
         
    }
}