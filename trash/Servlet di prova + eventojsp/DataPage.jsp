<%@page language="java" import="java.util.*" %>
<%@page import = "Model.Evento" %>
<html>
<head>
<title>Evento</title>
</head>
<body> 
<table border="1" width="303">
<tr>
<td width="119"><b>NOME></td>
<td width="300"><b>DESCRIZIONE</b></td>
<td width="119"><b>TIPO</b><td>
<td width="200"><b>DATA INIZIO</b><td>
<td width="200"><b>DATA FINE</b><td>
</tr>

<%Iterator itr;%>
<% List datiEvento= (List)request.getAttribute("evento");
for (itr=datiEvento.iterator(); itr.hasNext(); )
{
%>
<tr>
<td width="119"><%=itr.next()%></td>
<td width="300"><%=itr.next()%></td>
<td width="119"><%=itr.next()%></td>
<td width="200"><%=itr.next()%></td>
<td width="200"><%=itr.next()%></td>

</tr>
<%}%>
</table>
</body>
</html>
