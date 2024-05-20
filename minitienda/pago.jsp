<%-- JSP para la pagina de pago --%>
<%@page session="true" %>

<html>
    <head>
      <title>Musica para DAA</title>
    </head>
    <body bgcolor="#FDF5E6">
    <form action="/minitienda/CarritoServlet" method="get">
        <h2 align="center">La cantidad total a pagar es <%= session.getAttribute("pagoTotal") %></h2>
        <input type="hidden" name="Eliminar" id="Eliminar" value="">
        <input type="hidden" name="buttonClicked" id="buttonClicked" value="">
        <input type="submit" value="Pagar" onclick="setButtonClicked(this.value)">
        <input type="submit" value="Volver tienda" name="botonVolver" onclick="setButtonClicked(this.value)">
            <script>
                function setButtonClicked(buttonValue) {
                    document.getElementById('buttonClicked').value = buttonValue;
                }
            </script>
    </form>
    </body>
</html>
