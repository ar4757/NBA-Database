import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
@WebServlet("/tuples")
public class TuplesServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
 
    public TuplesServlet() {
        super();
    }
 
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Database database = new Database();
         
        try {
        	int playerTupleCount = database.getTupleCountForTable("PLAYER");
            request.setAttribute("playerTupleCount", playerTupleCount);
            
            int teamTupleCount = database.getTupleCountForTable("TEAM");
            request.setAttribute("teamTupleCount", teamTupleCount);
            
            int gameTupleCount = database.getTupleCountForTable("GAME");
            request.setAttribute("gameTupleCount", gameTupleCount);
            
            int playerStatsTupleCount = database.getTupleCountForTable("PLAYER_STATS");
            request.setAttribute("playerStatsTupleCount", playerStatsTupleCount);
            
            int totalTupleCount = database.getTupleCount();
            request.setAttribute("totalTupleCount", totalTupleCount);
            
            String page = "/tuples.jsp";
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(page);
            requestDispatcher.forward(request, response);              
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
         
    }
}