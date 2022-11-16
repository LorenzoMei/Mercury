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
import java.time.LocalDate;
import java.util.List;

import com.google.gson.Gson;

/**
 * Servlet implementation class ServletFiltro
 */
public class ServletAggiungiEvento extends HttpServlet {
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
                response.setContentType("text/html");
                response.getWriter().write(regione);
            }

            if (op.equals("provincia")) {
                String id = request.getParameter("id");
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
            Utilities.close();
        }
    }
		

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if (request.getParameter("email") == null || request.getParameter("email").equals("")) {
	        request.getRequestDispatcher("ServletLogin").forward(request, response);
	    }
		else {
			String nome = (String) request.getParameter("nomeEvento");
			String descrizione = (String) request.getParameter("descrizioneEvento");
			
			String regione = request.getParameter("regione");
			String provincia = request.getParameter("provincia");
			String comune = request.getParameter("comune");
			
			int reg = Utilities.getIdRegione(regione);
			int prov = Utilities.getIdProvinciaFromRegione(reg, provincia);
			int com = Utilities.getIdComuneFromProvincia(prov, comune);
			Zona zona = new Zona(reg, prov, com);
			
			String tipo = (String) request.getParameter("tipo");
			String dataI =  request.getParameter("dataInizio");
			LocalDate dataInizio = LocalDate.parse(dataI);
			
			String dataF = request.getParameter("dataFine");
			LocalDate dataFine = LocalDate.parse(dataF);
			
			String nomeEnte = (String) request.getParameter("nomeEnte");
			String email = (String) request.getParameter("email");
			
			Ente ente = new Ente(email, nomeEnte);
			
			Evento evento = new Evento(nome, descrizione, zona, tipo, dataInizio, dataFine, ente);
			Utilities.aggiungiEvento(evento);
			RequestDispatcher disp = request.getRequestDispatcher("ServletEventi");
			disp.forward(request, response);
		}
	}

}
