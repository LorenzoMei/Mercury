package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Ente;
import model.Evento;
import model.Utilities;
import model.Zona;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

/**
 * Servlet implementation class ServletEventi
 */
public class ServletEventi extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletEventi() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		
		int operazione = 0;
		if(request.getParameter("operazione") != null) {
			operazione = Integer.parseInt(request.getParameter("operazione"));
		}
		
		switch(operazione) {
			case 3:
				Evento e = (Evento) request.getAttribute("evento");
				request.setAttribute("evento", e);
				request.getRequestDispatcher("evento.jsp").forward(request, response);
				break;
				
			
				
				
			default:
				Utilities.connessione();
				List<Evento> listaEventi = Utilities.listaEventi();
				request.setAttribute("listaEventi", listaEventi);
				request.getRequestDispatcher("homepage.jsp").forward(request, response);
				
				break;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Ente e = (Ente) request.getSession().getAttribute("ente");
		List<Evento> eventi = Utilities.listaEventiPerEnte(e);
		request.setAttribute("listaEventiEnte", eventi);
		request.getSession().setAttribute("ente", e);
		request.getRequestDispatcher("adminEnte.jsp").forward(request, response);
	}

}
