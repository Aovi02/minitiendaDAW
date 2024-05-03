<%@ page import="minitienda.Producto" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.net.URLEncoder" %>

<%
    // Obtener el HashMap de productos del atributo de solicitud
    HashMap<Producto, Integer> productos = (HashMap<Producto, Integer>) request.getAttribute("productos");
    Float precioTotal = (Float) request.getAttribute("precioTotal");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD><TITLE>Minitienda</TITLE></HEAD>
<BODY BGCOLOR="#FDF5E6">
    <center> 
        <H1>Minitienda </H1>
        <table border="0" cellpadding="0" width="75%" bgcolor="#FFFFFF">
        <tr>
            <td><b>Titulo del CD</b></td>
            <td><b>Cantidad</b></td>
            <td><b>Importe</b></td>
            <td></td>
        </tr>
        <%-- Iterar sobre el HashMap de productos y mostrar cada producto en la tabla --%>
            <% for (Map.Entry<Producto, Integer> entry : productos.entrySet()) { %>
                <tr>
                    <td><b><%= entry.getKey().getNombre() %></b></td>
                    <td><b><%= entry.getValue() %></b></td>
                    <td><b><%= entry.getValue() * entry.getKey().getPrecio() %></b></td>
                    <td><a href="CarritoServlet?eliminar=<%= URLEncoder.encode(entry.getKey().getNombre(), "UTF-8") %>">Eliminar producto</a></td>
                </tr>
        <% } %>
        <tr>
            <td><b>Importe Total</b></td>
            <td><b> <%= precioTotal %> </b></td>
        </tr>
        </table>

        <button name= "botonPago" value="true">Pagar importe</button>
        <button name= "volver" value="true">Seguir comprando</button>
    </center>
</BODY>
</HTML>