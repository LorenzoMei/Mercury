<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="css/styleSidebar.css">
<title>Insert title here</title>
</head>
<body>

<div class="sidebar">
  
  <form method="get" action="index.jsp">
  
  <label for="filtroZona"> 
  	<input type="checkbox" id="checkFiltroZona" name="checkFiltroZona" value=true> Filtra per zona:
	<select name="FiltroZona" id="FiltroZona">

            <c:forEach var="category" items="${listaRegioni}" >
                <option value="${category}">
                    ${category}
                </option>
            </c:forEach>
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

</body>
</html>