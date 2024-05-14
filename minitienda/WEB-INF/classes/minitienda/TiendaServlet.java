package minitienda;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.*;

public class TiendaServlet extends HttpServlet {

    //Aquí se guardan los productos
    //Cada producto irá con la cantidad que haya, así permito que se seleccione el mismo producto varias veces
    Carrito carrito = new Carrito();

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //Variables del Servlet para la sesión y el contexto
        HttpSession sesion = request.getSession(true);
        //ServletContext contexto = getServletContext();

        String buttonClicked = request.getParameter("buttonClicked");
        if (buttonClicked != null && buttonClicked.equals("Ver carrrito")) {
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
                String titulo = t.nextToken();
                String autor = t.nextToken();
                String pais = t.nextToken();
                String precioString = t.nextToken();
                precioString = precioString.replace('$',' ').trim();

		// Datos de conexión a la base de datos
		String url = "jdbc:postgresql://localhost:5432/postgres";
		String usuario = "postgres";
		String contraseña = "postgres";
		String correoUsuario = "algo";
		String contrasena = null;
		String tipoTarjeta = null;
		String numeroTarjeta = null;
		// Intentar establecer la conexión y ejecutar la consulta
		try {
		    // Cargar el controlador de la base de datos
		    Class.forName("org.postgresql.Driver");

		    // Establecer la conexión
		    Connection conexion = DriverManager.getConnection(url, usuario, contraseña);

		    // Crear una declaración para ejecutar la consulta
		    Statement statement = conexion.createStatement();

		    // Ejecutar la consulta SQL para seleccionar todos los usuarios
		    String sql = "SELECT * FROM usuarios";
		    ResultSet resultado = statement.executeQuery(sql);

		    // Iterar sobre el resultado y imprimir los datos de cada usuario
		    while (resultado.next()) {
		        correoUsuario = resultado.getString("correo_usuario");
		        contrasena = resultado.getString("contrasena");
		        tipoTarjeta = resultado.getString("tipo_tarjeta");
		        numeroTarjeta = resultado.getString("numero_tarjeta");
		    }

		    // Cerrar la conexión
		    conexion.close();
		} catch (ClassNotFoundException e) {
		    System.out.println("No se pudo cargar el controlador: " + e.getMessage());
		    correoUsuario = "ERROR1";
		} catch (SQLException e) {
		    System.out.println("Error al conectar a la base de datos: " + e.getMessage());
		    correoUsuario = "ERROR2";
		}

                //Pasamos los campos al formato deseado
                Float precio = Float.valueOf(precioString);
                Integer cantidad = Integer.valueOf(cant);


                //Creamos un CD
                CD cd = new CD(correoUsuario, autor, tipoTarjeta, precio);

                //Creamos Seleccion
                Seleccion seleccion = new Seleccion(cd, cantidad);

                //Añadimos seleccion a carrito
                carrito.addSeleccion(seleccion);

                request.setAttribute("added", true);

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
}
