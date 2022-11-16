<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet" href="css/style.css">
	
</head>

<body>
<div id="absoluteCenteredDiv">
	<form action="ServletGestione?operazione=1" style="border:1px solid #ccc" method="post">
	  <div class="box">
	    <h1>Registrazione Ente</h1>
	    <hr>
	    
	    <label for="nomeEnte"><b>Nome Ente</b></label>
	    <br>
	    <input class="username" type="text" placeholder="Inserisci Nome Ente" name="nomeEnte" required>
		<hr>
	    <label for="nome"><b>Nome Responsabile</b></label>
	    <br>
	    <input class="username" type="text" placeholder="Inserisci Nome Responsabile" name="nomeResponsabile" required>
	    <br>
	    <br>
	    <label for="cognome"><b>Cognome Responsabile</b></label>
	    <br>
	    <input class="username" type="text" placeholder="Inserisci Cognome Responsabile" name="cognomeResponsabile" required>
	    <hr>
	    <label for="email"><b>Email</b></label>
	    <br>
	    <input class="username" type="text" placeholder="Inserisci Email" name="email" required>
		<br>
		<br>
	    <label for="psw"><b>Password</b></label>
	    <br>
	    <input class="username" type="password" placeholder="Inserisci Password" name="password" required>
	    
	      <button type="button" class="cancelbtn">Cancella</button>
	      <button type="submit" class="signupbtn">Registra Ente</button>
	    
	  </div>
	</form>
</div>
<br>
<footer>
<form action="ServletEventi">
<input type="submit" value="HOME">
</form>
</footer>
</body>
</html>
