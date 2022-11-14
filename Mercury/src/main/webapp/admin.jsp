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
            <c:forEach var="ente" items="${ente}">
            <tr>
            		<td><c:out value="${ente.email}" /></td>
                    <td><c:out value="${ente.nomeEnte}" /></td>
                    <td><c:out value="${ente.nomeResponsabile}" /></td>
                    <td><c:out value="${ente.cognomeResponsabile}" /></td>
                    <td><c:out value="${ente.stato}" /></td>
                     <td>
                     <a href="delete?id=<c:out value='${user.id}' />">Banna</a>                     
                    </td>
                </tr>
            </c:forEach>
        </table>
	</div>

</div>

</body>
</html>