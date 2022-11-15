<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="model.Utilities" %>

 
<!DOCTYPE html>
<html>
<head>
<title>Page Title</title>

<link rel="stylesheet" href="css/styleHome.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script src="scripts.js"></script>

</head>

<%
	Utilities.connessione();
%>
<body>


<div class="header">
  <h1>MERCURY</h1>
  <h4>Portale eventi su tutto il territori nazionale</h>  
</div>


<div class="main">

    <div class="sidebar">

  <form method="get" action="######">
  <label for="filtroZona"> 
  	<input type="checkbox" id="checkFiltroZona" name="checkFiltroZona"> Filtra per zona:
  </label>
  <div class="date">
	<label for="regione">Regione:
	<select name="regione">
	  <option value="A">A</option>
	  <option value="B">B</option>
	  <option value="-">Null</option>
	</select>
	</label>
	<label for="provincia">Provincia:
	<select name="provincia">
	  <option value="A">A</option>
	  <option value="B">B</option>
	  <option value="-">Null</option>
	</select>
	</label>
	<label for="comune">Comune:
	<select name="comune">
	  <option value="A">A</option>
	  <option value="B">B</option>
	  <option value="-">Null</option>
	</select>
	</label>
   </div>
  <br>
  <label for="filtroTipo"> <input type="checkbox" id="filtroTipo" name="filtroTipo" value=true> Filtra per tipo d'evento</label>
  <div class="date">
	<label for="tipologia">Tipologia:
	<select name="tipologia">
	  <option value="A">A</option>
	  <option value="B">B</option>
	  <option value="-">Null</option>
	</select>
	</label>
   </div>
	<br>
  <label for="filtroData"> <input type="checkbox" id="filtroData" name="filtroData" value=true> Filtra per data</label>
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
  <br>
  <div class="buttonFiltro">
	  <input type="submit" value="Esegui filtro">
  </div>
  </form>
  </div>

  <div class="content" id=cloneEvent>	        
  </div>
  
  
</div>

<div class="footer">
  <h2>Footer</h2>
</div>

</body>
</html>
