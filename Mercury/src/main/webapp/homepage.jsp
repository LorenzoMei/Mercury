<%@page import="model.Utilities"%>
<%@page import="java.util.ArrayList"%>
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

  <form method="post" action="ServletFiltra">
  
  <label for="filtroZona"> 
  	<input type="checkbox" id="checkFiltroZona" name="checkFiltroZona"> Filtra per zona:
  </label>
  <div class="regioneDiv" id="regioneDiv">
                    <div class="input-field">
                        <select id="regione" name="regione">
                            <option>Seleziona Regione</option>
                        </select>
                    </div>
                    <div class="input-field">
                        <select id="provincia" name="provincia">
                            <option>Seleziona Provincia</option>
                        </select>
                    </div>
                    <div class="input-field">
                        <select id="comune" name="comune">
                            <option>Seleziona Comune</option>
                        </select>
                    </div>

 </div>
  <br>
  
  <label for="filtroTipo"> 
  	<input type="checkbox" id="checkFiltroTipo" name="checkFiltroTipo"> Filtra per tipo d'evento
  </label>
  
  <div class="tipoDiv" id="tipoDiv">
	<label for="tipologia">Tipologia:<br>
	<select name="tipo">
		<option value="">-</option>
	<%
	ArrayList<String> listaTipo = Utilities.getTipo();
	for( int i = 0; i < listaTipo.size(); i++){
		out.println("<option value=\""+listaTipo.get(i)+"\">" + listaTipo.get(i) + "</option>" );
	}
	%>	

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
  
</div>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.js"></script>
        <script type="text/javascript">
            $(document).ready(function () {
                $.ajax({
                    url: "ServletFiltro",
                    method: "GET",
                    data: {operation: 'regione'},
                    success: function (data, textStatus, jqXHR) {
                        console.log(data);
                        let obj = $.parseJSON(data);
                        var i=0;
                        $.each(obj, function (key,value) {
                            $('#regione').append('<option value="' + value + '">' + value + '</option>')
                        });
                        $('select').formSelect();
                    },
                    error: function (jqXHR, textStatus, errorThrown) {
                        $('#regione').append('<option>Regione non disponibile!</option>');
                    },
                    cache: false
                });
                $('#regione').change(function () {
                    $('#provincia').find('option').remove();
                    $('#provincia').append('<option>Seleziona Provincia</option>'); 
                    $('#comune').find('option').remove();
                    $('#comune').append('<option>Seleziona Comune</option>');
                    let cid = $('#regione').val();
                    let data = {
                        operation: "provincia",
                        id: cid
                    };
                    $.ajax({
                        url: "ServletFiltro",
                        method: "GET",
                        data: data,
                        success: function (data, textStatus, jqXHR) {
                            console.log(data);
                            let obj = $.parseJSON(data);
                            var i=0;
                            $.each(obj, function (key, value) {
                                $('#provincia').append('<option value="' + value + '">' + value + '</option>')
                            });
                            $('select').formSelect();
                        },
                        error: function (jqXHR, textStatus, errorThrown) {
                            $('#provincia').append('<option>Provincia non disponibile</option>');
                        },
                        cache: false
                    });
                });
                
                $('#provincia').change(function () {
                    $('#comune').find('option').remove();
                    $('#comune').append('<option>Seleziona Comune</option>');
                    let sid = $( "#provincia option:selected" ).text();
                    let data = {
                        operation: "comune",
                        id: sid
                    };
                    $.ajax({
                        url: "ServletFiltro",
                        method: "GET",
                        data: data,
                        success: function (data, textStatus, jqXHR) {
                            console.log(data);
                            let obj = $.parseJSON(data);
                            var i=0;
                            $.each(obj, function (key, value) {
                                $('#comune').append('<option value="' + value + '">' + value + '</option>')
                            });
                            $('select').formSelect();
                        },
                        error: function (jqXHR, textStatus, errorThrown) {
                            $('#comune').append('<option>Comune non disponibile</option>');
                        },
                        cache: false
                    });
                });
            });
        </script>	
        
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
