package minitienda;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.net.URLEncoder;

import java.util.*;

public class CarritoServlet extends HttpServlet {

    //Aquí se guardan los productos
    //Cada producto irá con la cantidad que haya, así permito que se seleccione el mismo producto varias veces
    HashMap<CD, Integer> productos = new HashMap<CD, Integer>();

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
        String precioString = null;
        Float precio = null;
        Integer cantidad = null;
        //Variable del precio total para imprimirlo
        Float precioTotal = 0.f;

        //Recuperamos informacion del precio del CD
        if(datos != null){
            StringTokenizer t = new StringTokenizer(datos,"|");
            t.nextToken();
            t.nextToken();
            t.nextToken();
            precioString = t.nextToken();
            precioString = precioString.replace('$',' ').trim();

            //Pasamos los campos al formato deseado
            precio = Float.parseFloat(precioString);
            cantidad = Integer.parseInt(cant);

            //Creamos un Producto
            CD p = new CD(datos, precio);

            //Lo añadimos al HashMap si no existe, y si existe es añadir por encima la cantidad nueva
            if(productos.containsKey(p))
                productos.put(p, cantidad + productos.get(p));
            else
                productos.put(p, cantidad);

            //Guardo los productos
            request.setAttribute("productos", productos);

            //Calculamos el precio total
            for(CD q : productos.keySet()){
                precioTotal += q.getPrecio();
            }

            //Guardo el precio total antes de proceder
            request.setAttribute("precioTotal", precioTotal);
            
            gotoPage("/carrito.jsp", request, response);
        }


        String productoAEliminar = request.getParameter("eliminar");
        if (productoAEliminar != null && !productoAEliminar.isEmpty()) {
            eliminarProducto(productoAEliminar);
        }
    }

    public void gotoPage(String address, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		RequestDispatcher disp = null;
		if(address != null && address.length() > 0)
			disp = getServletContext().getRequestDispatcher(address);
		
		if(disp != null)
			disp.forward(request, response);
	}

    private void eliminarProducto(String nombreProducto) {
        for(Iterator<Map.Entry<CD, Integer>> iterator = productos.entrySet().iterator(); iterator.hasNext();) {
            Map.Entry<CD, Integer> entry = iterator.next();
            if(entry.getKey().getNombre().equals(nombreProducto)) {
                iterator.remove(); // Remove the entry from the HashMap
                break;
            }
        }
    }
}
