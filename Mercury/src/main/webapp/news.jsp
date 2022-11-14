<%@page language="java" import="java.util.*" %>

<html>
<head>
<title>Ente</title>
<link rel="stylesheet" href="css/styleNews.css">

</head>
<body> 

<div class="aggiunta" id="absoluteCenteredDiv">
    
    <form method="get" action="">

	<h1>FORM ISCRIZIONE NEWSLETTER</h1>
	<br><br><br>
	<label for="tipo"> Vuoi selezionare una preferenza sulla tipologia dell'evento?
	<input type="checkbox" id="checkTipo" name="checkTipo">
	</label>
	<br><br>
	<label for="tipoEvento">Tipologia Evento Preferita:
	<select name="tipo">
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

	<label for="regione">Regione:
	<select name="regione">
	  <option value="A">A</option>
	  <option value="B">B</option>
	  <option value="-">Null</option>
	</select>
	</label>
	<br><br>
	<label for="regione">Provincia:
	<select name="regione">
	  <option value="A">A</option>
	  <option value="B">B</option>
	  <option value="-">Null</option>
	</select>
	</label>
	<br><br>
	<label for="regione">Comune:
	<select name="regione">
	  <option value="A">A</option>
	  <option value="B">B</option>
	  <option value="-">Other</option>
	</select>
	</label>
	<br><br>
	
	<label for="cadenza"> uoi selezionare una preferenza sulla ricezione della mail?
	<input type="checkbox" id="checkCadenza" name="checkCadenza">
	</label>
	<br><br>
	<label for="cadenzaC">Cadenza della newsletter:
	<select name="regione">
	  <option value="giorno">Giornaliera</option>
	  <option value="settimana">Settimanale</option>
	  <option value="mese">Mensile</option>
	</select>
	</label>
	
	</form>
</div>


</body>
</html>