<%@page language="java" import="java.util.*" %>
<html>
<head>
<title>Data Page</title>
</head>
<body> 
<table border="1" width="303">
<tr>
<td width="119"><b>NOME<b></td>
<td width="300"><b>DESCRIZIONE</b></td>
<td width="200"><b>TIPO</b></td>
<td width="250"><b>DATA INIZIO</b></td>
<td width="250"><b>DATA </b></td>

</tr>
<%Iterator itr;%>
<% List data= (List)request.getAttribute("data");
for (itr=data.iterator(); itr.hasNext(); )
{
%>
<tr>
<td width="119"><%=itr.next()%></td>
<td width="300"><%=itr.next()%></td>
<td width="200"><%=itr.next()%></td>
<td width="250"><%=itr.next()%></td>
<td width="250"><%=itr.next()%></td>




</tr>
<%}%>
</table>
</body>
</html>
