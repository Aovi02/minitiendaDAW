<%@page session="true" %>
<%@page isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <head>
      <title>Musica para DAA</title>
    </head>
    <body bgcolor="#FDF5E6">
    <form action="/minitienda/CarritoServlet" method="get">
        <c:if test="${correo != null}">
          <h2 align="center">Hola ${correo}</h2>
        </c:if>
        <h2 align="center">La cantidad total a pagar es ${importe_pedido}</h2>
        <input type="hidden" name="Eliminar" id="Eliminar" value="">
        <input type="hidden" name="buttonClicked" id="buttonClicked" value="">
        <center>
          <input type="submit" value="Hacer Pedido" onclick="setButtonClicked(this.value)">
          <input type="submit" value="Volver tienda" name="botonVolver" onclick="setButtonClicked(this.value)">
        <c:if test="${confirmacion == true}">
          <h2 align="center">Pedido hecho con éxito</h2>
          <p align="center">Usuario: ${correo}</p>
          <p align="center">Importe: ${importe_pedido}</p>
        </c:if>
        </center>
            <script>
                function setButtonClicked(buttonValue) {
                    document.getElementById('buttonClicked').value = buttonValue;
                }
            </script>
    </form>
    </body>
</html>