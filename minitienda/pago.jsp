<%-- JSP para la pagina de pago --%>
<%@page session="true" %>

<html>
    <head>
      <title>Musica para DAA</title>
    </head>
    <body bgcolor="#FDF5E6">
        <h2 align="center">La cantidad total a pagar es <%= request.getParameter("precioTotal") %></h2>
        <button name="botonFinal" value="true">Pagar</button>
        <button name="botonSeguir" value="true">Seguir Comprando</button>
    </body>
</html>