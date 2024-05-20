<html>
    <head>
      <title>Musica para DAA</title>
    </head>
    <body bgcolor="#FDF5E6">
      <table align="center" border="0">
	<tr> 
	  <th><IMG SRC="" ALIGN="CENTER"></th>
	  <th><font face="Times New Roman,Times" size="+3">M�sica para DAA</font></th>
	  <th><IMG SRC="" ALIGN="CENTER"></th>
	</tr>
      </table>
      <hr>
	<p>
	  <center>
	    <form method="post" action="/minitienda/TiendaServlet">
	      <b>CD:</b> 
	      <select name="datos">
		<option>Yuan | The Guo Brothers | China | $14.95</option>
		<option>Drums of Passion | Babatunde Olatunji | Nigeria | $16.95</option>
		<option>Kaira | Tounami Diabate| Mali | $16.95</option>
		<option>The Lion is Loose | Eliades Ochoa | Cuba | $13.95</option>
		<option>Dance the Devil Away | Outback | Australia | $14.95</option>
		<option>Record of Changes | Samulnori | Korea | $12.95</option>
		<option>Djelika | Tounami Diabate | Mali | $14.95</option>
		<option>Rapture | Nusrat Fateh Ali Khan | Pakistan | $12.95</option>
		<option>Cesaria Evora | Cesaria Evora | Cape Verde | $16.95</option>
		<option>DAA | GSTIC | Spain | $50.00</option>
	      </select>
	      <b>Cantidad:</b>
	      <input type="text" name="cantidad" value="1">
		<p>
		  <input type="hidden" name="buttonClicked" id="buttonClicked" value="">
		  <center>
		    <input type="submit" value="Seleccionar Producto" onclick="setButtonClicked(this.value)">
		  </center>
		  <center>
			<br><br>
			<input type="submit" value="Ver carrrito" name="botonCarrito" onclick="setButtonClicked(this.value)">
			<input type="submit" value="LOGEAR" onclick="setButtonClicked(this.value)">
			<input type="hidden" name="LOGEAR" id="LOGEAR" value="">
			<script>
				function setButtonClicked(buttonValue) {
					document.getElementById('buttonClicked').value = buttonValue;
				}
			</script>
		  </center>
	    </form>

		<%
        // Check if a CD has been added to the cart
        Boolean added = (Boolean) request.getAttribute("added");
        if (added != null && added) {
		%>
		<div id="message" style="color:green; text-align:center;">
            CD añadido al carrito con éxito!
        </div>
        <script>
            // JavaScript code to hide the message after 1 second
            setTimeout(function() {
                var message = document.getElementById('message');
                if (message) {
                    message.style.display = 'none';
                }
            }, 1000); // Hide after 1 second (1000 milliseconds)
        </script>
		<%
			}
		%>
	  </center>
	  <hr>
    </body>
</html>
