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
	<br><br><br>
	<label for="tipo"> Vuoi selezionare una preferenza sulla tipologia dell'evento?
	<input type="checkbox" id="checkTipo" name="checkTipo">
	</label>
	<br><br>
	<label for="tipoEvento" id="labelTipo" style="display:none">Tipologia Evento Preferita:
	<select name="tipo" id="tipo" style="display:none">
	  <option value="A">A</option>
	  <option value="B">B</option>
	  <option value="-">Null</option>
	</select>
	</label>
	<br><br>
	<label for="Zona"> Vuoi selezionare una zona preferita?
	<input type="checkbox" id="checkZona" name="checkZona">
	</label>
	<br><br>

	<label for="regione" id="labelRegione" style="display:none">Regione:
	<select name="regione" id="regione" style="display:none">
	  <option value="0">-</option>
	  <option value="1">A</option>
	  <option value="2">B</option>
	</select>
	</label>
	<br><br>
	<label for="provincia" id="labelProvincia" style="display:none">Provincia:
	<select name="provincia" id ="provincia" style="display:none">
	  <option value="0">-</option>
	  <option value="1">A</option>
	  <option value="2">B</option>
	</select>
	</label>
	<br><br>
	<label for="comune" id="labelComune" style="display:none">Comune:
	<select name="comune" id="comune" style="display:none">
	  <option value="0">-</option>
	  <option value="1">A</option>
	  <option value="2">B</option>
	</select>
	</label>
	<br><br>
	
	<label for="cadenza"> Vuoi selezionare una preferenza sulla ricezione della mail?
	<input type="checkbox" id="checkCadenza" name="checkCadenza">
	</label>
	<br><br>
	<label for="cadenzaC" id="labelCadenza" style="display:none">Cadenza della newsletter:
	<select name="cadenza" id="cadenza" style="display:none">
	  <option value="giorno">Giornaliera</option>
	  <option value="settimana">Settimanale</option>
	  <option value="mese">Mensile</option>
	</select>
	</label>
	
	</form>
</div>

	<script>
      let checkboxTipo = document.getElementById("checkTipo");
      checkboxTipo.addEventListener( "change", () => {
         if ( checkboxTipo.checked ) {
        	 document.getElementById("labelTipo").style.display = "block";
        	 document.getElementById("tipo").style.display = "block";
         } else {
        	 document.getElementById("labelTipo").style.display = "none";
        	 document.getElementById("tipo").style.display = "none";
         }
      });
      
      let checkboxZona = document.getElementById("checkZona");
      checkboxZona.addEventListener( "change", () => {
         if ( checkboxZona.checked ) {
        	 document.getElementById("labelRegione").style.display = "block";
        	 document.getElementById("regione").style.display = "block";
         } else {
        	 document.getElementById("labelRegione").style.display = "none";
        	 document.getElementById("regione").style.display = "none";
         }
      });
      
      let checkboxCadenza = document.getElementById("checkCadenza");
      checkboxCadenza.addEventListener( "change", () => {
         if ( checkboxCadenza.checked ) {
        	 document.getElementById("labelCadenza").style.display = "block";
        	 document.getElementById("cadenza").style.display = "block";
         } else {
        	 document.getElementById("labelCadenza").style.display = "none";
        	 document.getElementById("cadenza").style.display = "none";
         }
      });
      
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
   </script>


</body>
</html>