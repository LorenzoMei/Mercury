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
import java.io.PrintWriter;
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
		PrintWriter out = response.getWriter();
		List<Ente> listaEnti = Utilities.listaEnti();
		request.setAttribute("listaEnti", listaEnti);
		List<Ente> listaEntiBannati = Utilities.listaEntiBannati();
		request.setAttribute("listaEntiBannati", listaEntiBannati);
		int operazione = 0;
		if(request.getParameter("operazione") != null) {
			operazione = Integer.parseInt(request.getParameter("operazione"));
		}
		
		switch(operazione) {
			case 3:
				Utilities.sbannaEnte((String) request.getParameter("email"));
				out.println("Ente Sbannato");
				response.sendRedirect("ServletGestione");
				break;
				
			case 4:
				Utilities.bannaEnte((String) request.getParameter("email"));
				out.println("Ente Bannato");
				response.sendRedirect("ServletGestione");
				break;
				
			default:
				request.getRequestDispatcher("admin.jsp").forward(request, response);
				break;
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		int operazione = Integer.parseInt(request.getParameter("operazione"));
		List<Ente> listaEnti = Utilities.listaEnti();
		request.setAttribute("listaEnti", listaEnti);
		List<Ente> listaEntiBannati = Utilities.listaEntiBannati();
		request.setAttribute("listaEntiBannati", listaEntiBannati);
		
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
				
				int idRegione;
				int idProvincia;
				int idComune;
				
				if(!regione.equals("Seleziona Regione")) {
					idRegione = Utilities.getIdRegione(regione);
					
					if(!provincia.equals("Seleziona Provincia")) {
						idProvincia = Utilities.getIdProvinciaFromRegione(idRegione, provincia);
						
						if(!comune.equals("Seleziona Comune")) {
							idComune = Utilities.getIdComuneFromProvincia(idProvincia, comune);
						}
						else {
							idComune = Utilities.getIdComune("0");
						}
					}
					else {
						idProvincia = Utilities.getIdProvincia("0");
						idComune = Utilities.getIdComune("0");
					}
				}
				else {
					idRegione = Utilities.getIdRegione("0"); 
					idProvincia = Utilities.getIdProvincia("0");
					idComune = Utilities.getIdComune("0");
				}
				
				Zona zona = new Zona(idRegione, idProvincia, idComune);
				
				String tipo = (String) request.getParameter("tipo");
				String cadenza = (String) request.getParameter("cadenza");
				
				UtenteRegistrato utenteRegistrato = new UtenteRegistrato(emailNews, zona, tipo, cadenza, LocalDate.now());
				Utilities.iscrizioneNews(utenteRegistrato);
				
				request.getRequestDispatcher("ServletEventi?operazione=0").forward(request, response);
				
				break;
			case 3:
				Utilities.sbannaEnte((String) request.getParameter("email"));
				request.getRequestDispatcher("admin.jsp").forward(request, response);
				break;
				
			case 4:
				Utilities.bannaEnte((String) request.getParameter("email"));
				request.getRequestDispatcher("admin.jsp").forward(request, response);
				break;
				
			
		}
	}

}
