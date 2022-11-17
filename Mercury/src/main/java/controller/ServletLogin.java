package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import model.Amministratore;
import model.Ente;
import model.Utilities;

/**
 * Servlet implementation class ServletLogin
 */
public class ServletLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletLogin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		request.getRequestDispatcher("login.html").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		Object utente = Utilities.login(email, password);
		
		if(utente instanceof Amministratore){
			List<Ente> listaEnti = Utilities.listaEnti();
			List<Ente> listaEntiBannati = Utilities.listaEntiBannati();
			request.setAttribute("listaEnti", listaEnti);
			request.setAttribute("listaEntiBannati", listaEntiBannati);
			request.getRequestDispatcher("admin.jsp").forward(request, response);
		}
		else if(utente instanceof Ente) {
			//request.setAttribute("ente", (Ente)utente);
			request.getSession().setAttribute("ente", (Ente) utente);
			request.getRequestDispatcher("ServletEventi").forward(request, response);
		}
		else {
			out.println("Login errato");
		}
	
	}

}
