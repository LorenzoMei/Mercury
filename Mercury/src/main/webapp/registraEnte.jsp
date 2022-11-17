<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet" href="css/style.css">
	
</head>

<body>
<div id="absoluteCenteredDiv">
	<form action="ServletGestione?operazione=1" method="post">
	  <div class="box">
	  <br>
	    <h1>Registrazione Ente</h1>
	    <br>
	    
	    <label for="nomeEnte"><b>Nome Ente</b></label>
	    <br>
	    <input class="username" type="text" placeholder="Inserisci Nome Ente" name="nomeEnte" required>
		<br><br>
	    <label for="nome"><b>Nome Responsabile</b></label>
	    <br>
	    <input class="username" type="text" placeholder="Inserisci Nome Responsabile" name="nomeResponsabile" required>
	    <br>
	    <br><br>
	    <label for="cognome"><b>Cognome Responsabile</b></label>
	    <br>
	    <input class="username" type="text" placeholder="Inserisci Cognome Responsabile" name="cognomeResponsabile" required>
	    <br><br>
	    <label for="email"><b>Email</b></label>
	    <br>
	    <input class="username" type="text" placeholder="Inserisci Email" name="email" required>
		<br>
		<br>
	    <label for="psw"><b>Password</b></label>
	    <br>
	    <input class="username" type="password" placeholder="Inserisci Password" name="password" required>
	    <br><br>
      
      <button type="button" class="cancelbtn">Cancella</button>
      <button type="submit" class="signupbtn">Registra Ente</button>
	    
	  </div>
	</form>

	<form action="ServletEventi">
	<br>
<input type="submit" value="HOME">
	<br>
</form>
</div>

</body>
</html>
