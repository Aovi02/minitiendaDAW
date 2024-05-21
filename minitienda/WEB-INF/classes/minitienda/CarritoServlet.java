package minitienda;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.*;
import javax.servlet.http.*;


public class CarritoServlet extends HttpServlet{

    private Carrito carrito;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession sesion = request.getSession(true);
        carrito = (Carrito)sesion.getAttribute("carrito");

        String buttonClicked = request.getParameter("buttonClicked");

        //Comprobamos qué botón se ha pulsado
        if (buttonClicked != null) {
            if (buttonClicked.equals("Pagar")) {
                //Si se quiere pagar, se guarda el importe sólo y nos vamos a la pantalla de pago
                sesion.setAttribute("importe_pedido", carrito.getImporte());
                gotoPage("/pago.jsp", request, response);
            }
            else if (buttonClicked.equals("Volver tienda")) {
                //Si se quiere volver, se vuelve, el carrito seguirá guardado en sesión
                gotoPage("/index.jsp", request, response);
            }
            else if(buttonClicked.equals("Hacer Pedido")){
                //Si se quiere hacer un pedido, primero vemos si el usuario está logeado con su correo
                if(sesion.getAttribute("correo") == null)
                    //Si no lo está, nos vamos a login
                    gotoPage("/login.jsp", request, response);
                else{
                    //Si está logeado, cogemos su carrito y le mostramos confirmación de pedido
                    sesion.setAttribute("confirmacion", true);
                    Usuario user = (Usuario)sesion.getAttribute("usuario");
                    carrito.setUsuario(user);
                    Float importe = (Float)sesion.getAttribute("importe_pedido");
                    //Hacemos un nuevo pedido
                    Pedido ped = new Pedido(user.getNombre(), importe);
                    try {
                        //Se guarda en la BD y el carrito se resetea para hacer otras compras
                        registrarPedido(ped);
                        carrito.eliminarCarrito();
                        sesion.setAttribute("carrito", carrito);
                    } catch (Exception e) {
                
                    }
                    //Se refresca la página para mostrar la confirmación
                    gotoPage("/pago.jsp", request, response);
                    sesion.setAttribute("confirmacion", false);
                }
            }
        }
        //Si se quiere eliminar algún producto cogemos este parámetro
        String botonEliminar = request.getParameter("Eliminar");

        if(botonEliminar != null){
            //Cogemos el id de la selección a borrar y la borramos del carrito
            Integer id = Integer.valueOf(botonEliminar);

            for(Seleccion s : carrito.getSelecciones()){
                if(s.getId() == id){
                    try {
                        //Hay que actualizar la BD al eliminar un producto, devolviéndolo a la BD
                        actualizarStock(s);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    
                }
            }
            //Se actualiza el carrito y se devuelve a la página del carrito
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

    public void registrarPedido(Pedido ped) throws ClassNotFoundException{
        Class.forName("org.postgresql.Driver");
        // Usamos try-with-resources para manejar la conexión y el PreparedStatement
        try (Connection conexion = Conexion.getConnection()) {
            // Ejecutar la consulta SQL para insertar los pedidos
		    String sql = "INSERT INTO pedidos (id_pedido, usuario, importe) VALUES (?, ?, ?)";
		    PreparedStatement statement = conexion.prepareStatement(sql);
		    statement.setString(1, ped.getId());
		    statement.setString(2, ped.getCorreoUsuario());
		    statement.setFloat(3, ped.getImportePedido());

		    // Ejecutar la sentencia de inserción
		    int filasInsertadas = statement.executeUpdate();

		    // Verificar si la inserción fue exitosa
		    if (filasInsertadas > 0) {
		        System.out.println("Se ha insertado el nuevo pedido correctamente.");
		    } else {
		        System.out.println("No se pudo insertar el nuevo pedido.");
		    }
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
