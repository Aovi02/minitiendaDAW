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
                <td>
                    <form action="/minitienda/CarritoServlet" method="post">
                        <input type="submit" value="Eliminar" onclick="setEliminar('${s.id}')">
                        <input type="hidden" name="Eliminar" value="">
                    </form>
                </td>
            </tr>
        </c:forEach>
        <tr>
            <td><b>Importe Total</b></td>
            <td colspan="4"><b>${carrito.importe}</b></td>
            <td></td>
        </tr>
        </table>

        <form action="/minitienda/CarritoServlet" method="post">
            <input type="hidden" name="buttonClicked" id="buttonClicked" value="">

            <input type="submit" value="Pagar" onclick="setButtonClicked(this.value)">
            <input type="submit" value="Volver tienda" name="botonVolver" onclick="setButtonClicked(this.value)">

			<script>
				function setButtonClicked(buttonValue) {
					document.getElementById('buttonClicked').value = buttonValue;
				}

                function setEliminar(seleccion) {
					document.getElementsByName('Eliminar')[0].value = seleccion;
				}
			</script>
        </form>
    </center>
</BODY>
</HTML>