<%@page import="model.Utilities"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import ="java.util.List"
    import ="model.Evento"
    %>
 
<!DOCTYPE html>
<html>
<head>
<title>Page Title</title>

<link rel="stylesheet" href="css/styleHome.css">
<script src="scripts.js"></script>

</head>
<body>

<div class="header">
  <h1>MERCURY</h1>
  <h4>Portale eventi su tutto il territori nazionale</h4>  
</div>


<div class="main">

  <div class="sidebar">

  <form method="get" action="index.jsp">
  <label for="filtroZona"> 
  	<input type="checkbox" id="checkFiltroZona" name="checkFiltroZona" value=true> Filtra per zona:
	<select name="FiltroZona" id="FiltroZona">
                <option value="${category}">
                    ${category}
                </option>
	</select>
  </label><br>
  
  <label for="filtroData"> <input type="checkbox" id="filtroTipo" name="filtroTipo" value=true> Filtra per tipologia d'evento</label><br>
  <label for="filtroData"> <input type="checkbox" id="filtroData" name="filtroData" value=true> Filtra per data</label><br>
  <div class="date">
  	<label for="dataInizio"> Data inizio:
		<input type=date id=today name="dataInizio" min="2015-01-01" max="2030-12-31">
	</label>
	<label for="dataFine"> Data fine:
		<input type=date id=today2 name="dataFine" min="2015-01-01" max="2030-12-31">
	</label>
	<script>
		document.getElementById('today').value = new Date().toISOString().substring(0, 10);
	</script>
		<script>
		document.getElementById('today2').value = new Date().toISOString().substring(0, 10);
	</script>
  </div>
  
  <label for="filtroFinal">
  <input type="submit" value="Esegui filtro">
  </label>
  </form>
  </div>

  <div class="content">
 	
 <%

  	List<Evento> list = (List<Evento>) request.getAttribute("listaEventi");
		  for(int i=0;i<list.size();i++){		  
%>
			   <div class="event">
			   <a href="evento.jsp?index=<%out.println(i); %>" <%
			   	request.getSession().setAttribute("e", list);
			   %>>
			   </a>
			   
			   <h2>
			   <%
			   	out.println(list.get(i).getNome());
				  %>
			   </h2>
			   <h4>
			   <%
			   out.println(list.get(i).getTipo());
			   %></h4>
			   
				  
				  </div>
				  <br>
				  <%
		  }
		  
		   
			   %>
			 
		 
 
  
        
  </div>
  
  
  
</div>

<div class="footer">
  <h2>Footer</h2>
</div>

</body>
</html>
