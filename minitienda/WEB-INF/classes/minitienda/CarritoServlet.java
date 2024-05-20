package minitienda;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.*;
import javax.servlet.http.*;

import java.util.*;

public class CarritoServlet extends HttpServlet{

    private Carrito carrito;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession sesion = request.getSession(true);
        carrito = (Carrito)sesion.getAttribute("carrito");

        String buttonClicked = request.getParameter("buttonClicked");

        if (buttonClicked != null) {
            if (buttonClicked.equals("Pagar")) {
                sesion.setAttribute("importe_pedido", carrito.getImporte());
                gotoPage("/pago.jsp", request, response);
            }
            else if (buttonClicked.equals("Volver tienda")) {
                gotoPage("/index.jsp", request, response);
            }
            else if(buttonClicked.equals("Hacer Pedido")){
                if(sesion.getAttribute("correo") == null)
                    gotoPage("/login.jsp", request, response);
                else{
                    sesion.setAttribute("confirmacion", true);
                    gotoPage("/pago.jsp", request, response);
                }
            }
        }

        String botonEliminar = request.getParameter("Eliminar");

        if(botonEliminar != null){
            Integer id = Integer.valueOf(botonEliminar);

            for(Seleccion s : carrito.getSelecciones()){
                if(s.getId() == id){
                    try {
                        actualizarStock(s);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    
                }
            }

            carrito.eliminarSeleccion(id);
            sesion.setAttribute("carrito", carrito);
            gotoPage("/carrito.jsp", request, response);
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

    private void actualizarStock(Seleccion sel) throws ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        // Usamos try-with-resources para manejar la conexión y el PreparedStatement
        try (Connection conexion = Conexion.getConnection()) {
            // Preparamos la consulta SQL usando parámetros para evitar inyección SQL
            String consulta = "SELECT * FROM cds WHERE titulo = ? AND autor = ? AND pais = ?";
            try (PreparedStatement statement = conexion.prepareStatement(consulta)) {
                // Establecemos los valores de los parámetros
                statement.setString(1, sel.getCd().getNombre());
                statement.setString(2, sel.getCd().getAutor());
                statement.setString(3, sel.getCd().getPais());

                // Ejecutamos la consulta
                try (ResultSet resultado = statement.executeQuery()) {
                    if (resultado.next()) {
                        // Obtenemos la cantidad disponible
                        int disponible = resultado.getInt("disponible");
                        // Restamos 1 a la cantidad disponible
                        int nuevoDisponible = disponible + sel.getCantidad();

                        // Preparamos la actualización SQL
                        String actualizacion = "UPDATE cds SET disponible = ? WHERE titulo = ? AND autor = ? AND pais = ?";
                        try (PreparedStatement updateStatement = conexion.prepareStatement(actualizacion)) {
                            // Establecemos los valores de los parámetros para la actualización
                            updateStatement.setInt(1, nuevoDisponible);
                            updateStatement.setString(2, sel.getCd().getNombre());
                            updateStatement.setString(3, sel.getCd().getAutor());
                            updateStatement.setString(4, sel.getCd().getPais());

                            // Ejecutamos la actualización
                            int filasActualizadas = updateStatement.executeUpdate();
                            if (filasActualizadas > 0) {
                                System.out.println("Cantidad actualizada exitosamente. Nuevo disponible: " + nuevoDisponible);
                            } else {
                                System.out.println("No se pudo actualizar la cantidad.");
                            }
                        }
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
