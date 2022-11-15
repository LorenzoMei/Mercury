package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Utilities;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.google.gson.Gson;

/**
 * Servlet implementation class ServletFiltro
 */
public class ServletFiltro extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	
		try (PrintWriter out = response.getWriter()) {
            
            String op = request.getParameter("operation");
            
            if (op.equals("regione")) {
                List<String> regioni = Utilities.getRegione();
                Gson json = new Gson();
                String regione = json.toJson(regioni);
                System.out.print(regione);
                response.setContentType("text/html");
                response.getWriter().write(regione);
            }

            if (op.equals("provincia")) {
                int id = Integer.parseInt(request.getParameter("id"));
                List<String> slist = Utilities.getProvincia(id);
                Gson json = new Gson();
                String countryList = json.toJson(slist);
                response.setContentType("text/html");
                response.getWriter().write(countryList);
            }

            if (op.equals("comune")) {
                String id = request.getParameter("id");
                List<String> comuni = Utilities.getComune(id);
                Gson json = new Gson();
                String comune = json.toJson(comuni);
                response.setContentType("text/html");
                response.getWriter().write(comune);
            }
        }
    }
		

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
