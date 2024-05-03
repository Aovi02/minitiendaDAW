package minitienda;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.net.URLEncoder;

import java.util.*;

public class CarritoServlet extends HttpServlet {

    //Aquí se guardan los productos
    //Cada producto irá con la cantidad que haya, así permito que se seleccione el mismo producto varias veces
    Carrito carrito = new Carrito();

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //Variables del Servlet para la sesión y el contexto
        HttpSession sesion = request.getSession(true);
        ServletContext contexto = getServletContext();

        //Datos es el string completo del formulario, la cantidad ya viene dada por el otro text field
        String datos = request.getParameter("datos");
        String cant = request.getParameter("cantidad");

        //Recuperamos informacion del precio del CD
        if(datos != null){
            StringTokenizer t = new StringTokenizer(datos,"|");
            String titulo = t.nextToken();
            String autor = t.nextToken();
            String pais = t.nextToken();
            String precioString = t.nextToken();
            precioString = precioString.replace('$',' ').trim();

            //Pasamos los campos al formato deseado
            Float precio = Float.parseFloat(precioString);
            Integer cantidad = Integer.parseInt(cant);

            //Creamos un CD
            CD cd = new CD(titulo, autor, pais, precio);

            //Creamos Seleccion
            Seleccion seleccion = new Seleccion(cd, cantidad);

            //Añadimos seleccion a carrito
            carrito.addSeleccion(seleccion);

            //Guardo el CD
            request.setAttribute("carrito", carrito);
            request.setAttribute("added", true);

            gotoPage("/index.jsp", request, response);
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
