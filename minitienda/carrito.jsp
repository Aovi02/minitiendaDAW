<%@page import="minitienda.*" %>
<%@page import="java.util.HashMap" %>
<%@page import="java.util.Map" %>
<%@page isELIgnored="false" %>
<%@page session="true" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD><TITLE>Minitienda</TITLE></HEAD>
<BODY BGCOLOR="#FDF5E6">
    <center> 
        <H1>Minitienda </H1>
        <table border="0" cellpadding="0" width="75%" bgcolor="#FFFFFF">
        <tr>
            <td><b>Titulo</b></td>
            <td><b>Autor</b></td>
            <td><b>Pais</b></td>
            <td><b>Cantidad</b></td>
            <td><b>Importe</b></td>
            <td></td>
        </tr>
        <c:forEach items="${carrito.selecciones}" var="s">
            <tr>
                <td><b>${s.cd.nombre}</b></td>
                <td><b>${s.cd.autor}</b></td>
                <td><b>${s.cd.pais}</b></td>
                <td><b>${s.cantidad}</b></td>
                <td><b>${s.cd.precio * s.cantidad}</b></td>
            </tr>
        </c:forEach>
        <tr>
            <td><b>Importe Total</b></td>
            <td><b> ${carrito.importe} </b></td>
        </tr>
        </table>

        <button name= "botonPago" value="true">Pagar importe</button>
        <button name= "volver" value="true">Seguir comprando</button>
    </center>
</BODY>
</HTML>