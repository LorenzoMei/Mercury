<%@page import="model.Ente"%>
<%@page import="java.util.List"%>
<%@page import="model.Utilities"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Pagina Amministratore</title>
<link rel="stylesheet" href="css/admin.css">
</head>
<body>
<div id="listaEnti">
<div class="boxLista">
	<h2>Lista degli Enti Autorizzati</h2>
		<table border="1">
            
            <tr>
                <th width=10%>Nome Ente</th>
                <th width=10%>Nome Responsabile</th>
                <th width=10%>Cognome Responsabile</th>
                <th width=10%>Email</th>
                <th width=10%>Stato</th>
                <th width=10%>Azione</th>
            </tr>
            
            <%
            List<Ente> enti = (List<Ente>)request.getAttribute("listaEnti");
            
            for(Ente e : enti){
            	%>
            
            <tr>
            		<td><% out.println(e.getEmail()); %></td>
                    <td><% out.println(e.getNomeEnte()); %></td>
                    <td><% out.println(e.getNomeResponsabile()); %></td>
                    <td><%out.println(e.getCognomeResponsabile()); %></td>
                    
                     <td>
                     <a href="delete?id=<c:out value='${user.id}' />">Banna</a>                     
                    </td>
                </tr>
            	<%
            }
            %>
        </table>
	</div>

</div>

</body>
</html>