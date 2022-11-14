package controller;

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
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
		int operazione = (int)request.getAttribute("operazione");
		
		switch(operazione) {
			case 1:
				String email = (String) request.getAttribute("email");
				String password = (String) request.getAttribute("password");
				String nomeEnte = (String) request.getAttribute("nomeEnte");
				String nomeResponsabile = (String) request.getAttribute("nomeResponsabile");
				String cognomeResponsabile = (String) request.getAttribute("cognomeResponsabile");
				
				Ente ente = new Ente(email, password, nomeEnte, nomeResponsabile, cognomeResponsabile);
				Utilities.aggiungiEnte(ente);
				
				break;
			case 2:
				Utilities.bannaEnte((String) request.getAttribute("email"));
				
				break;
			case 3:
				String emailNews = (String) request.getAttribute("emailNews");
				
				String regione = (String) request.getAttribute("regione");
				String provincia = (String) request.getAttribute("provincia");
				String comune = (String) request.getAttribute("comune");
				Zona zona = new Zona(regione, provincia, comune);
				
				String tipo = (String) request.getAttribute("tipo");
				String cadenza = (String) request.getAttribute("cadenza");
				
				UtenteRegistrato utenteRegistrato = new UtenteRegistrato(emailNews, zona, tipo, cadenza, null);
				Utilities.iscrizioneNews(utenteRegistrato);
				
				break;
		}
	}

}
