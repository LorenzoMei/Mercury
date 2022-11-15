<%@page language="java" import="java.util.*" %>

<html>
<head>
<title>Ente</title>
<link rel="stylesheet" href="css/styleEnte.css">

</head>
<body> 

<div class="aggiunta" id="absoluteCenteredDiv">
    
    <form method="get" action="">

	<h1>FORM AGGIUNTA EVENTI</h1>
	<h3>ENTE: XXXXXX</h3>
	<br><br><br>
	<label for="nomeEvento"> Nome Evento:
		<input type="text" id="nomeEvento" name="nomeEvento"><br>
	</label>
	<br>
	<label for="tipoEvento">Tipologia Evento:
	<select name="tipo">
	  <option value="A">A</option>
	  <option value="B">B</option>
	  <option value="-">Null</option>
	</select>
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
	<label for="provincia">Provincia:
	<select name="provincia">
	  <option value="A">A</option>
	  <option value="B">B</option>
	  <option value="-">Null</option>
	</select>
	</label>
	<br><br>
	<label for="comune">Comune:
	<select name="comune">
	  <option value="A">A</option>
	  <option value="B">B</option>
	  <option value="-">Other</option>
	</select>
	</label>
	<br><br>
  	<label for="dataInizio"> Data inizio:
		<input type=date id=today name="dataInizio" min="2015-01-01" max="2030-12-31">
		<script>
			document.getElementById('today').value = new Date().toISOString().substring(0, 10);
	    </script>
	</label>
	<br><br>
  	<label for="dataFine"> Data fine:
		<input type=date id=today1 name="dataFine" min="2015-01-01" max="2030-12-31">
		<script>
			document.getElementById('today1').value = new Date().toISOString().substring(0, 10);
	    </script>
	</label>
	<br><br>
	<label for="nomeEvento"> Descrizione Evento:
		  <textarea id="nomeEvento" name="nomeEvento" rows="4" cols="65"></textarea>
	</label>
	<br><br>
	<input type="submit" value="Aggiungi">
	
	</form>
</div>


</body>
</html>
