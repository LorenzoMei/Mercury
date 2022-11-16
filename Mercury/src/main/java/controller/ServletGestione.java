package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Ente;
import model.UtenteRegistrato;
import model.Utilities;
import model.Zona;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

/**
 * Servlet implementation class ServletGestione
 */
public class ServletGestione extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletGestione() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		Utilities.bannaEnte((String) request.getParameter("email"));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		int operazione = Integer.parseInt(request.getParameter("operazione"));
		
		switch(operazione) {
			case 1:
				
				String email = (String) request.getParameter("email");
				String password = (String) request.getParameter("password");
				String nomeEnte = (String) request.getParameter("nomeEnte");
				String nomeResponsabile = (String) request.getParameter("nomeResponsabile");
				String cognomeResponsabile = (String) request.getParameter("cognomeResponsabile");
				
				Ente ente = new Ente(email, password, nomeEnte, nomeResponsabile, cognomeResponsabile);
				Utilities.aggiungiEnte(ente);
				
				request.getRequestDispatcher("ServletEventi").forward(request, response);
				break;
			case 2:
				String emailNews = (String) request.getParameter("emailNews");
				
				String regione = (String)(request.getParameter("regione"));
				String provincia = (String)(request.getParameter("provincia"));
				String comune = (String)(request.getParameter("comune"));
				
				int idRegione = Utilities.getIdRegione(regione);
				int idProvincia = Utilities.getIdProvinciaFromRegione(idRegione, provincia);
				int idComune = Utilities.getIdComuneFromProvincia(idProvincia, comune);
				
				Zona zona = new Zona(idRegione, idProvincia, idComune);
				
				String tipo = (String) request.getParameter("tipo");
				String cadenza = (String) request.getParameter("cadenza");
				
				UtenteRegistrato utenteRegistrato = new UtenteRegistrato(emailNews, zona, tipo, cadenza, LocalDate.now());
				Utilities.iscrizioneNews(utenteRegistrato);
				
				request.getRequestDispatcher("ServletEventi?operazione=0").forward(request, response);
				
				break;
		}
	}

}
