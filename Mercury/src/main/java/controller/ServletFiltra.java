package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Evento;
import model.Utilities;
import model.Zona;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.swing.plaf.synth.Region;

/**
 * Servlet implementation class ServletFiltra
 */
public class ServletFiltra extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletFiltra() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
				
		List<Evento> listaEventi = Utilities.listaEventi();
		List<Evento> listaEventiFiltrata = new ArrayList<Evento>();
		
		//filtro per tipo
		String tipo = request.getParameter("tipo");
						
		
		if(!tipo.equals("")) {
			for(int i = 0; i < listaEventi.size(); i++) {
				if(listaEventi.get(i).getTipo().equals(tipo)) {
					listaEventiFiltrata.add(listaEventi.get(i));
					listaEventi.set(i, null);
				}
			}
		}
		
		//filtra per data
		
		LocalDate dataInizio = null; 
		LocalDate dataFine = null;
		
		if((!request.getParameter("dataInizio").equals("")) && (!request.getParameter("dataFine").equals(""))) {
			dataInizio = LocalDate.parse(request.getParameter("dataInizio"));
			 dataFine = LocalDate.parse(request.getParameter("dataFine"));
		}
		
		if(dataInizio != null && dataFine != null) {
			for(int i = 0; i < listaEventi.size(); i++) {
				if(listaEventi.get(i) != null) {
					if((listaEventi.get(i).getDataInizio().isAfter(dataInizio) || listaEventi.get(i).getDataInizio().equals(dataInizio)) && 
							(listaEventi.get(i).getDataFine().isBefore(dataFine) || listaEventi.get(i).getDataFine().equals(dataFine))) {
						listaEventiFiltrata.add(listaEventi.get(i));
						listaEventi.set(i, null);
					}
				}
			}
		}
		
		//filtra per zona
		String regione = request.getParameter("regione");
		String provincia = request.getParameter("provincia");
		String comune = request.getParameter("comune");
		
		System.out.println("Regione " + regione);
		System.out.println("Provincia " + provincia);
		System.out.println("Comune " + comune);
		
		if(!(regione.equals("") || regione.equals("Seleziona Regione"))) {
			if(listaEventiFiltrata.size() != 0) {
				listaEventiFiltrata = Utilities.filtraEventiPerZona(regione, provincia, comune, listaEventiFiltrata);
			}
			else{
				listaEventiFiltrata = Utilities.filtraEventiPerZona(regione, provincia, comune, listaEventi);
			}
		}

		request.setAttribute("listaEventi", listaEventiFiltrata);
		request.getRequestDispatcher("homepage.jsp").forward(request, response);
		
	}

}
