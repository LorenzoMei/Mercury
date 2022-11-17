<%@page language="java" import="java.util.*, model.Utilities"%>

<html>
<head>
<title>News</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
        <link rel="stylesheet" href="css/styleHome.css">
        <style type="text/css">
            body{
            	background: #1abc9c;
            }
            .drop-down-list{
                margin: 150px auto;
                width: 50%;
                padding: 30px;
            }
        </style>
</head>
<body> 

<div class="aggiunta" id="absoluteCenteredDiv">
     <div class="drop-down-list card">
    <form method="post" action="ServletGestione?operazione=2">

		<h4>FORM ISCRIZIONE NEWSLETTER</h4>
		<br>	
	
	  <br>
		<label for="tipo">Tipologia<br>
		  <% ArrayList<String> tipi = new ArrayList<String>(); 
		tipi.add("teatro");
		tipi.add("mostra");
		tipi.add("film");
		tipi.add("concerto");
		tipi.add("sagra");
		%>
		<select name="tipo">
		<% for(String s : tipi){
			%>	
		<option value="<%out.print(s);%>"><% out.print(s); %></option>
		<%
		}
	%>
		</select>
		</label>
	 
	  
	<br>
	
	<label for="zona"> Vuoi selezionare una preferenza sulla zona? </label>
	
	 
                
              <div class="divider"></div>
           
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
            
	   
	<br>
	
	  <div class="cadenzaDiv" id="cadenzaDiv">
	  <br>
		<label for="cadenza">Cadenza della newsletter<br>
		<select name="cadenza">
		  	<option value="settimana">Settimanale (default)</option>
		  	<option value="mese">Mensile</option>
		</select>
		</label>
	  </div>
	  <div class="cadenza" id="cadenzaDiv">
	  <br>
		<label for="emailNews">Email<br>
		<input type="email" name="emailNews" required>
		</label>
	  </div>
  
  <br>
  
  <div class="buttonIscriviti">
	  <input type="submit" value="Iscriviti">
  </div>
	</form>
</div>
</div>

  <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
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

                    let cid = $('#regione option:selected').text();
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

                    let sid = $('#provincia option:selected').text();
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


</body>
</html>
