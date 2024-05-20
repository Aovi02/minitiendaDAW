<%-- JSP para la pagina de pago --%>
<%@page session="true" %>

<html>
<head>
    <title>Registro de Usuario</title>
</head>
<body>
    <h2>Registro de Usuario</h2>
    <form action="/minitienda/TiendaServlet" method="post">
        <label for="correo">Correo:</label>
        <input type="text" id="correo" name="correo" required>
        <br>
        <label for="id">ID:</label>
        <input type="text" id="id" name="id" required>
        <br>
        <label for="contrasena">Contraseña:</label>
        <input type="password" id="contrasena" name="contrasena" required>
        <br>
        <input type="hidden" name="LOGIN" id="LOGIN" value="">
        <input type="submit" value="LOGIN" name="LOGIN" onclick="setButtonClicked(this.value)">
        <script>
                function setButtonClicked(buttonValue) {
                    document.getElementById('LOGIN').value = buttonValue;
                }
        </script>
    </form>
</body>
</html>
