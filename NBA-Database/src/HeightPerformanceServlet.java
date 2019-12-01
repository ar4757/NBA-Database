import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
@WebServlet("/HeightPerformance")
public class HeightPerformanceServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
 
    public HeightPerformanceServlet() {
        super();
    }
 
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Database database = new Database();
         
        try {
            ArrayList<HeightPerform> heightPerforms = database.getHeightPerform();
             
            request.setAttribute("heightPerforms", heightPerforms);
             
            String pheight = "/HeightPerformance.jsp";
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(pheight);
            requestDispatcher.forward(request, response);              
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
         
    }
}