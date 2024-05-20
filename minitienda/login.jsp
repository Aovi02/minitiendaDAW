<%@page session="true" %>
<%@page isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Registro de Usuario</title>
</head>
<body bgcolor="#FDF5E6">
    <h2>Registro de Usuario</h2>
    <center>
        <form action="/minitienda/PedidoServlet" method="post">
            <label for="correo">Correo:</label>
            <input type="text" id="correo" name="correo" required>
            <br>
            <label for="contrasena">Contraseña:</label>
            <input type="password" id="contrasena" name="contrasena" required>
            <br>
            <label for="tarjeta">Tarjeta:</label>
            <input type="text" id="tarjeta" name="tarjeta" required>
            <br>
            <input type="hidden" name="buttonClicked" id="buttonClicked" value="">
            <input type="submit" value="Login" name="Login" onclick="setButtonClicked(this.value)">
            <input type="submit" value="Registrarse" name="Registrarse" onclick="setButtonClicked(this.value)">
            <script>
                    function setButtonClicked(buttonValue) {
                        document.getElementById('buttonClicked').value = buttonValue;
                    }
            </script>
        </form>
    </center>
</body>
</html>