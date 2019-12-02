import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
@WebServlet("/AgePerformance")
public class AgePerformanceServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
 
    public AgePerformanceServlet() {
        super();
    }
 
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Database database = new Database();
         
        try {
            ArrayList<AgePerform> agePerforms = database.getAgePerform();
             
            request.setAttribute("agePerforms", agePerforms);
            
            ArrayList<Double> performance = new ArrayList<Double>();
            for (int i = 0; i < agePerforms.size(); i++) {
            	performance.add(Double.parseDouble(agePerforms.get(i).getPerformance()));
            }
            request.setAttribute("performance", performance);
             
            String page = "/AgePerformance.jsp";
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(page);
            requestDispatcher.forward(request, response);              
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
         
    }
}