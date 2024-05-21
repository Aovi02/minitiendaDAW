package minitienda;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.*;
import javax.servlet.http.*;

import java.util.*;

public class TiendaServlet extends HttpServlet {

    //Aquí se guardan los productos
    Carrito carrito = new Carrito();

    //Clase conexión para conectarse a la BD
    Conexion conexion = new Conexion();

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //Variables del Servlet para la sesión y el contexto
        HttpSession sesion = request.getSession(true);
        //ServletContext contexto = getServletContext();

        //Comprobamos cuál de los botones se ha pulsado
        String buttonClicked = request.getParameter("buttonClicked");
        if (buttonClicked != null && buttonClicked.equals("Ver carrrito")) {
            //Si se quiere ver el carrito, se guarda y nos vamos al carrito
            sesion.setAttribute("carrito", carrito);
            gotoPage("/carrito.jsp", request, response);
        }
        else if(buttonClicked != null && buttonClicked.equals("Seleccionar Producto")){
            //Datos es el string completo del formulario, la cantidad ya viene dada por el otro text field
            String datos = request.getParameter("datos");
            String cant = request.getParameter("cantidad");

            //Recuperamos informacion del precio del CD
            if(datos != null && cant != null){
                StringTokenizer t = new StringTokenizer(datos,"|");
                String titulo = t.nextToken().trim();
                String autor = t.nextToken().trim();
                String pais = t.nextToken().trim();
                String precioString = t.nextToken();
                precioString = precioString.replace('$',' ').trim();

                //Pasamos los campos al formato deseado
                Float precio = Float.valueOf(precioString);
                Integer cantidad = Integer.valueOf(cant);

                //Creamos un CD
                CD cd = new CD(titulo, autor, pais, precio);

                //Creamos Seleccion
                Seleccion seleccion = new Seleccion(cd, cantidad);

                //Comprobamos si se puede efectuar dicha selección (si hay suficientes CDs disponibles)
                boolean sePuedeComprar = false;
                try {
                    sePuedeComprar = checkDisponibilidad(seleccion);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }

                //Añadimos seleccion a carrito si se puede comprar
                //La variable added es para mostrarle al usuario si se ha añadido o no el CD
                if(sePuedeComprar){
                    carrito.addSeleccion(seleccion);
                    request.setAttribute("added", true);
                }
                else{
                    request.setAttribute("added", false);
                }
                //Refrescamos la página para mostrar el mensaje
                gotoPage("/index.jsp", request, response);
            }
        }
    }

    public void gotoPage(String address, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		RequestDispatcher disp = null;
		if(address != null && address.length() > 0)
			disp = getServletContext().getRequestDispatcher(address);
		
		if(disp != null)
			disp.forward(request, response);
	}

    private boolean checkDisponibilidad(Seleccion sel) throws ClassNotFoundException{
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

                        // Verificamos si la cantidad disponible es mayor que 0
                        if (disponible > 0) {
                            // Restamos 1 a la cantidad disponible
                            int nuevoDisponible = disponible - sel.getCantidad();

                            if(nuevoDisponible >= 0){
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
                                        return true;
                                    } else {
                                        System.out.println("No se pudo actualizar la cantidad.");
                                        return false;
                                    }
                                }
                            }
                            else{
                                System.out.println("Hay menos CDs disponibles de los que se pidio.");
                                return false;
                            }
                        } else {
                            return false;
                        }
                    } else {
                        return false;
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
}
