import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
@WebServlet("/playerRank2016")
public class PlayerRank2016Servlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
 
    public PlayerRank2016Servlet() {
        super();
    }
 
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Database database = new Database();
         
        try {
            ArrayList<PlayerRank> playerRanks = database.getPlayerRanks("2016");
             
            request.setAttribute("playerRanks", playerRanks);
             
            String page = "/PlayerRank2016.jsp";
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(page);
            requestDispatcher.forward(request, response);              
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
         
    }
}