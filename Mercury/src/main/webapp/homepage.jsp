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
  <h4>Portale eventi su tutto il territorio nazionale</h4>  
</div>


<div class="main">

  <div class="sidebar">

  <form method="get" action="">
  
  <label for="filtroZona"> 
  	<input type="checkbox" id="checkFiltroZona" name="checkFiltroZona"> Filtra per zona:
  </label>

  <div class="regioneDiv" id="regioneDiv">
	<label for="regione" id="labelRegione">Regione:<br>
	<select name="regione" id="regione">
	  <option value="1">A</option>
	  <option value="2">B</option>
	</select>
	</label>
	<label for="provincia" id="labelProvincia" style="display:none">Provincia:
	<select name="provincia" id ="provincia" style="display:none">
	  <option value="0">-none-</option>
	  <option value="1">A</option>
	  <option value="2">B</option>
	</select>
	</label>
	<label for="comune" id="labelComune" style="display:none">Comune:
	<select name="comune" id="comune" style="display:none">
	  <option value="0">-none-</option>
	  <option value="1">A</option>
	  <option value="2">B</option>
	</select>
	</label>
   </div>
  <br>
  
  <label for="filtroTipo"> 
  	<input type="checkbox" id="checkFiltroTipo" name="checkFiltroTipo"> Filtra per tipo d'evento
  </label>
  
  <div class="tipoDiv" id="tipoDiv">
	<label for="tipologia">Tipologia:<br>
	<select name="tipologia">
	  <option value="A">A</option>
	  <option value="B">B</option>
	</select>
	</label>
  </div>
  <br>
	
  <label for="filtroData"> 
  	<input type="checkbox" id="checkFiltroData" name="checkFiltroData"> Filtra per data
  </label>
  <div class="dataDiv" id="dataDiv">
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
	
  <script>
  //Check per ZONA
  let checkboxFiltroZona = document.getElementById("checkFiltroZona");
  checkboxFiltroZona.addEventListener( "change", () => {
     if ( checkboxFiltroZona.checked ) {
    	 document.getElementById("regioneDiv").style.display = "block";
     } else {
    	 document.getElementById("regioneDiv").style.display = "none";
     }
  });
  
  //Check per provincia
  var selectRegione = document.getElementById("regione");
  selectRegione.addEventListener("change", () => {
	  if(selectRegione.value != 0){
	  	 document.getElementById("labelProvincia").style.display = "block";
     	 document.getElementById("provincia").style.display = "block";
	  }
	  else{
		  document.getElementById("labelProvincia").style.display = "none";
      	 document.getElementById("provincia").style.display = "none";
	  }
  //Check per comune
  })
  var selectProvincia = document.getElementById("provincia");
  selectProvincia.addEventListener("change", () => {
	  if(selectProvincia.value != 0){
		 document.getElementById("labelComune").style.display = "block";
      	 document.getElementById("comune").style.display = "block";
	  }
	  else{
		  document.getElementById("labelComune").style.display = "none";
       	 document.getElementById("comune").style.display = "none";
	  }
  })
  
  //Check per TIPOLOGIA
  let checkboxFiltroTipo = document.getElementById("checkFiltroTipo");
  checkboxFiltroTipo.addEventListener( "change", () => {
     if ( checkboxFiltroTipo.checked ) {
    	 document.getElementById("tipoDiv").style.display = "block";
     } else {
    	 document.getElementById("tipoDiv").style.display = "none";
     }
  });
  
  //Check per DATA
  let checkboxFiltroData = document.getElementById("checkFiltroData");
  checkboxFiltroData.addEventListener( "change", () => {
     if ( checkboxFiltroData.checked ) {
    	 document.getElementById("dataDiv").style.display = "block";
     } else {
    	 document.getElementById("dataDiv").style.display = "none";
     }
  });
  </script>

</body>
</html>
