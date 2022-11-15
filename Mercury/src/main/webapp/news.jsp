<%@page language="java" import="java.util.*" %>

<html>
<head>
<title>Ente</title>
<link rel="stylesheet" href="css/styleNews.css">

</head>
<body> 

<div class="aggiunta" id="absoluteCenteredDiv">
    
    <form method="post" action="">

	<h1>FORM ISCRIZIONE NEWSLETTER</h1>
	<br>	
	
	<label for="tipo"> 
	Vuoi selezionare una preferenza sulla tipologia dell'evento? 
	<input type="checkbox" id="checkTipo" name="checkTipo">
	</label>
	  
	  <div class="tipoDiv" id="tipoDiv">
	  <br>
		<label for="tipologia">Tipologia:<br>
		<select name="tipologia">
		  <option value="A">A</option>
		  <option value="B">B</option>
		  <option value="C">C</option>
		</select>
		</label>
	  </div>
	  
	<br>
	
	<label for="zona"> 
	Vuoi selezionare una preferenza sulla zona? 
	<input type="checkbox" id="checkZona" name="checkZona">
	</label>
	
	  <div class="regioneDiv" id="regioneDiv">
	  <br>
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
	
	<label for="cadenza">
	 Vuoi selezionare una preferenza sulla ricezione della mail?
	<input type="checkbox" id="checkCadenza" name="checkCadenza">
	</label>
	  <div class="cadenzaDiv" id="cadenzaDiv">
	  <br>
		<label for="cadenza">Cadenza della newsletter:<br>
		<select name="tipologia">
		  <option value="giorno">Giornaliera</option>
		  <option value="settimana">Settimanale</option>
		  <option value="mese">Mensile</option>
		</select>
		</label>
	  </div>
  
  <br>
  
  <div class="buttonIscriviti">
	  <input type="submit" value="Iscriviti">
  </div>
	</form>
</div>

  <script>
  //Check per ZONA
  let checkboxZona = document.getElementById("checkZona");
  checkboxZona.addEventListener( "change", () => {
     if ( checkboxZona.checked ) {
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
  let checkboxTipo = document.getElementById("checkTipo");
  checkboxTipo.addEventListener( "change", () => {
     if ( checkboxTipo.checked ) {
    	 document.getElementById("tipoDiv").style.display = "block";
     } else {
    	 document.getElementById("tipoDiv").style.display = "none";
     }
  });
  
  //Check per CADENZA
  let checkboxCadenza = document.getElementById("checkCadenza");
  checkboxCadenza.addEventListener( "change", () => {
     if ( checkboxCadenza.checked ) {
    	 document.getElementById("cadenzaDiv").style.display = "block";
     } else {
    	 document.getElementById("cadenzaDiv").style.display = "none";
     }
  });
  </script>


</body>
</html>
