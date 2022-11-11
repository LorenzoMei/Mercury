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
import java.sql.Date;
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
		
		int operation = (int) request.getAttribute("operation");
		
		switch(operation) {
			case 1:
				List<Evento> listaEventi = Utilities.listaEventi();
				
				break;
			case 2: 
				//Evento evento = Utilities.mostraEvento();
				RequestDispatcher dispatcher = request.getRequestDispatcher("evento.jsp");
				dispatcher.forward(request, response);
				
				break;
			case 3: 
				String nome = (String) request.getAttribute("nome");
				String descrizione = (String) request.getAttribute("descrizione");
				
				String regione = (String) request.getAttribute("regione");
				String provincia = (String) request.getAttribute("provincia");
				String comune = (String) request.getAttribute("comune");
				Zona zona = new Zona(regione, provincia, comune);
				
				String tipo = (String) request.getAttribute("tipo");
				Date dataInizio = (Date) request.getAttribute("dataInizio");
				Date dataFine = (Date) request.getAttribute("dataFine");
				//Ente ente = new Ente(Utilities.caricaEnte(request.getAttribute("email")));
				
				//Evento evento = new Evento(nome, descrizione, zona, tipo, dataInizio, dataFine);
				//Utilities.aggiungiEvento(evento);
				
				break;
			case 4:
				//Utilities.filtraData(request.getAttribute("dataInizio"),
					//request.getAttribute("dataFine"), request.getAttribute("listaEventi"));
				//Utilities.filtraZona(request.getAttribute("zona"));
				//Utilities.filtraTipo(request.getAttribute("filtraTipo"));
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
