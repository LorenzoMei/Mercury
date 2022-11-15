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
		
		int operation = 0;
		
		switch(operation) {
			
			case 2: 
				String nome = (String) request.getParameter("nome");
				String descrizione = (String) request.getParameter("descrizione");
				
				int regione = Integer.parseInt(request.getParameter("regione"));
				int provincia = Integer.parseInt(request.getParameter("provincia"));
				int comune = Integer.parseInt(request.getParameter("comune"));
				Zona zona = new Zona(regione, provincia, comune);
				
				String tipo = (String) request.getParameter("tipo");
				LocalDate dataInizio = (LocalDate) request.getAttribute("dataInizio");
				LocalDate dataFine = (LocalDate) request.getAttribute("dataFine");
				
				String nomeEnte = (String) request.getParameter("nomeEnte");
				String email = (String) request.getParameter("email");
				
				Ente ente = new Ente(nomeEnte, email);
				
				Evento evento = new Evento(nome, descrizione, zona, tipo, dataInizio, dataFine, ente);
				Utilities.aggiungiEvento(evento);
				
				break;
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
		doGet(request, response);
	}

}
