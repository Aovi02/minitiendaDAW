package minitienda;

import java.io.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.*;
import javax.servlet.http.*;

import java.util.*;
import javax.naming.Context;

public class TiendaServlet extends HttpServlet {

    //Aquí se guardan los productos
    //Cada producto irá con la cantidad que haya, así permito que se seleccione el mismo producto varias veces
    Carrito carrito = new Carrito();

    ArrayList<String> cds_vista = new ArrayList<>();

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //Variables del Servlet para la sesión y el contexto
        HttpSession sesion = request.getSession(true);
        ServletContext contexto = getServletContext();

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

                //Pasamos los campos al formato deseado
                Float precio = Float.valueOf(precioString);
                Integer cantidad = Integer.valueOf(cant);

                //Creamos un CD
                CD cd = new CD(titulo, autor, pais, precio);

                //Creamos Seleccion
                Seleccion seleccion = new Seleccion(cd, cantidad);

                //Añadimos seleccion a carrito
                carrito.addSeleccion(seleccion);

                request.setAttribute("added", true);

                gotoPage("/index.jsp", request, response);
            }
        }
        else{
            try (Connection conexion = Conexion.getConnection()) {
                // Cogemos la lista de CDs y la guardamos en 
                Statement statement = conexion.createStatement();

                String consulta = "SELECT * FROM cds";
                ResultSet resultado = statement.executeQuery(consulta);

                while (resultado.next()) {
                    String titulo = resultado.getString("titulo");
                    String autor = resultado.getString("autor");
                    String pais = resultado.getString("pais");
                    String precio = resultado.getString("precio");

                    cds_vista.add(titulo+" | "+autor+" | "+pais+" | "+precio);
                }

                contexto.setAttribute("cds", cds_vista);
                gotoPage("/index.jsp", request, response);

            } catch (SQLException e) {
                e.printStackTrace();
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
