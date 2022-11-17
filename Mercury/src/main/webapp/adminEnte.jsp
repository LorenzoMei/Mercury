<%@page import="model.Evento"%>
<%@page import="model.Ente"%>
<%@page import="java.util.List"%>
<%@page import="model.Utilities"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Pagina Amministratore</title>
<link rel="stylesheet" href="css/admin.css">
</head>
<body>
<div id="listaEnti" class="boxLista">
	<h2>Lista degli Eventi Inseriti</h2>
		<table border="1">
            
            <tr>
		
                <th width=10%>Nome Evento</th>
                <th width=10%>Descrizione Evento</th>
                <th width=10%>Tipo</th>
                <th width=10%>Data Inizio</th>
                <th width=10%>Data Fine</th>
            </tr>
            
            <%
            Ente ente = (Ente) request.getSession().getAttribute("ente");
            List<Evento> eventi = (List<Evento>)request.getAttribute("listaEventiEnte");
            
            for(Evento e : eventi){
            	%>
            <tr>
            		<td><% out.println(e.getNome()); %></td>
                    <td><% out.println(e.getDescrizione()); %></td>
                    <td><% out.println(e.getTipo()); %></td>
                    <td><%out.println(e.getDataInizio()); %></td>
                    <td><%out.println(e.getDataFine()); %></td>   
                </tr>
            	<%
            }
            %>
        </table>
       <a href="ente.jsp?nome=<% out.println(ente.getNomeEnte()); %>&email=<% out.println(ente.getEmail()); %>">
                    	Aggiungi Evento
						</a> 
	</div>


<footer>
<form action="ServletEventi">
<input type="submit" value="HOME">
</form>
</footer>
</body>
</html>
