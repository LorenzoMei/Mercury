<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.*" import = "model.Evento" %>
<!DOCTYPE html>
<html>
<head>
<title>Evento</title>
<link rel="stylesheet" href="css/styleEvento.css">

</head>
<body> 
<table border="1" >
<tr>
<td width="300"><b>NOME</b></td>
<td width="300"><b>DESCRIZIONE</b></td>
<td width="300"><b>TIPO</b></td>
<td width="300"><b>DATA INIZIO</b></td>
<td width="300"><b>DATA FINE</b></td>
<td width="300"><b>NOME ENTE</b></td>
</tr>

<% 
List<Evento> eventi = (List<Evento>) request.getSession().getAttribute("e");
String index = request.getParameter(("index"));
int i = Integer.parseInt(index);
Evento e = eventi.get(i);
request.getSession().setAttribute("evento", e);


%>
<tr>
<td width="119"><%=e.getNome()%></td>
<td width="300"><%=e.getDescrizione()%></td>
<td width="119"><%=e.getTipo()%></td>
<td width="200"><%=e.getDataInizio()%></td>
<td width="200"><%=e.getDataFine()%></td>
<td width="200"><%=e.getEnte().getNomeEnte()%></td>


</tr>

</table>
</body>
</html>