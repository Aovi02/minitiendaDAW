package minitienda;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.*;
import javax.servlet.http.*;

import java.util.*;

public class PedidoServlet extends HttpServlet{

    private Pedido pedido;

    private Conexion conexion = new Conexion();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession sesion = request.getSession(true);

        String buttonClicked = request.getParameter("buttonClicked");

        if (buttonClicked != null) {
            if (buttonClicked.equals("Login")) {
                String correo = request.getParameter("correo");
                String contrasena = request.getParameter("contrasena");
                String tarjeta = request.getParameter("tarjeta");
                String tipoTarjeta = request.getParameter("tipo-tarjeta");

                Usuario user = new Usuario(correo, contrasena, tarjeta, tipoTarjeta);
                sesion.setAttribute("usuario", user);

                try {
                    boolean loginExito = loginUsuario(correo, contrasena);

                    if(loginExito){
                        sesion.setAttribute("correo", correo);
                        gotoPage("/pago.jsp", request, response);
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
            else if (buttonClicked.equals("Registrarse")) {
                String correo = request.getParameter("correo");
                String contrasena = request.getParameter("contrasena");
                String tarjeta = request.getParameter("tarjeta");
                String tipoTarjeta = request.getParameter("tipo-tarjeta");
                
                try {
                    registrarUsuario(correo, contrasena, tarjeta, tipoTarjeta);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                Usuario user = new Usuario(correo, contrasena, tarjeta, tipoTarjeta);
                sesion.setAttribute("usuario", user);
                sesion.setAttribute("correo", correo);
                gotoPage("/pago.jsp", request, response);
            }
        }
        
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    public void gotoPage(String address, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		RequestDispatcher disp = null;
		if(address != null && address.length() > 0)
			disp = getServletContext().getRequestDispatcher(address);
		
		if(disp != null)
			disp.forward(request, response);
	}

    private boolean loginUsuario(String correo, String contrasena) throws ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        // Usamos try-with-resources para manejar la conexión y el PreparedStatement
        try (Connection conexion = Conexion.getConnection()) {
            // Ejecutar la consulta SQL para seleccionar todos los usuarios
            String sql = "SELECT COUNT(*) FROM usuarios WHERE correo_electronico = ? AND contrasena = ?";
            PreparedStatement statement = conexion.prepareStatement(sql);
            statement.setString(1, correo);
            statement.setString(2, contrasena);

            // Ejecutar la sentencia de inserción
            ResultSet resultados = statement.executeQuery();

            if (resultados.next()) {
                int count = resultados.getInt(1);
                return count > 0;
            }
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public void registrarUsuario(String correo, String contrasena, String numeroTarjeta, String tipoTarjeta) throws ClassNotFoundException{
        Class.forName("org.postgresql.Driver");
        // Usamos try-with-resources para manejar la conexión y el PreparedStatement
        try (Connection conexion = Conexion.getConnection()) {
            // Ejecutar la consulta SQL para seleccionar todos los usuarios
            String sql = "INSERT INTO usuarios (correo_electronico, contrasena, numero_tarjeta, tipo_tarjeta) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = conexion.prepareStatement(sql);
            statement.setString(1, correo);
            statement.setString(2, contrasena);
            statement.setString(3, numeroTarjeta);
            statement.setString(3, tipoTarjeta);

            // Ejecutar la sentencia de inserción
            int filasInsertadas = statement.executeUpdate();

            // Verificar si la inserción fue exitosa
            if (filasInsertadas > 0) {
                System.out.println("Se ha insertado el nuevo usuario correctamente.");
            } else {
                
            }
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


}
