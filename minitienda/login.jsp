<%-- JSP para la pagina de pago --%>
<%@page session="true" %>

<html>
<head>
    <title>Registro de Usuario</title>
</head>
<body>
    <h2>Registro de Usuario</h2>
    <form method="post" action="/minitienda/TiendaServlet">
        <label for="correo">Correo:</label>
        <input type="text" id="correo" name="correo">
        <br>
        <label for="id">ID:</label>
        <input type="text" id="id" name="id">
        <br>
        <label for="contrasena">Contraseña:</label>
        <input type="password" id="contrasena" name="contrasena">
        <br>       
        <input type="hidden" name="buttonClicked" id="buttonClicked" value="">
        <input type="submit" value="LOGIN" name="LOGIN" onclick="setButtonClicked(this.value)">
        <script>
                function setButtonClicked(buttonValue) {
                    document.getElementById('buttonClicked').value = buttonValue;
                }
        </script>
    </form>
</body>
</html>
