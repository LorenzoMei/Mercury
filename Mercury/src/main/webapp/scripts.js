//SCRIPT PER DUPLICARE LA DIV EVENTI
$(document).ready(function(){
	const contenuto = ["EVENTO N.X", "TIPOLOGIA", "Descrizione"];
	var txt = "<div class='event'>"+
	          "<a href='login.html'></a>"+
	          "<h2>"+contenuto[0]+"</h2>"+
	          "<h4>"+contenuto[1]+"</h4>"+
	          "<p>"+contenuto[2]+"</p>"+
	          "</div><br>"; 
	for(var i = 0; i< 5; i++)
	   $("#cloneEvent").append(txt);  
});

