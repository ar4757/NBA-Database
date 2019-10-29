import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
@WebServlet(urlPatterns={"/index.html", "/index"})
public class IndexServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
 
    public IndexServlet() {
        super();
    }
 
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Database database = new Database();
         
        //try {
            //ArrayList<String> playerNames = database.getPlayers();
             
            //request.setAttribute("test", 10);
             
            String page = "/index.jsp";
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(page);
            requestDispatcher.forward(request, response);              
        //} //catch (SQLException ex) {
            //throw new ServletException(ex);
        //}
         
    }
}