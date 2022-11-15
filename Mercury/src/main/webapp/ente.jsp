<%@page import="model.Utilities"%>
<%@page language="java" import="java.util.*" %>

<html>
<head>
<title>Ente</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
        <link rel="stylesheet" href="css/styleHome.css">
        <style type="text/css">
            
            .drop-down-list{
                margin: 150px auto;
                width: 50%;
                padding: 30px;
            }
	    .drop-down-list h3 {
		text-align:center;
	    }
	    .buttonAggiungi{
		  margin: 0;
		  position: absolute;
		  left: 50%;
		  bottom:2%;
		  -ms-transform: translateX(-50%);
		  transform: translateX(-50%);
	    }
        </style>

</head>
<body> 

<div class="aggiunta" id="absoluteCenteredDiv">
    
    
	
            <div class="drop-down-list card">
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
                
                <div class="divider"></div>
                <form>
                    <div class="input-field">
                        <select id="regione">
                            <option>Seleziona Regione</option>
                        </select>
                    </div>
                    <div class="input-field">
                        <select id="provincia">
                            <option>Seleziona Provincia</option>
                        </select>
                    </div>
                    <div class="input-field">
                        <select id="comune">
                            <option>Seleziona Comune</option>
                        </select>
                    </div>
            </form>
            
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
	<div class="buttonAggiungi">
		<input type="submit" value="Aggiungi">
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
                            $('#regione').append('<option value="' + ++i + '">' + value + '</option>')
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
                                $('#provincia').append('<option value="' + ++i + '">' + value + '</option>')
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
                                $('#comune').append('<option value="' + ++i + '">' + value + '</option>')
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
	<br>
<footer>
<form action="http://localhost:8080/Mercury">
<input type="submit" value="HOME">
</form>
</footer>
        </body>
</html>
