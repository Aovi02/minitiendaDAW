package minitienda;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import java.util.*;

public class CarritoServlet extends HttpServlet{

    private Carrito carrito;
    private SQL secuela;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession sesion = request.getSession(true);
        carrito = (Carrito)sesion.getAttribute("carrito");
        secuela = new SQL();

        String buttonClicked = request.getParameter("buttonClicked");
	
	Float pagoTotal = carrito.getImporte();
        if (buttonClicked != null) {
            if (buttonClicked.equals("Pagar")) {
            	sesion.setAttribute("pagoTotal", pagoTotal);
                gotoPage("/pago.jsp", request, response);//gotoPage("/login.jsp", request, response);
                buttonClicked = request.getParameter("buttonClicked");
            } else if (buttonClicked.equals("Volver tienda")) {
                	gotoPage("/index.jsp", request, response);
            } else if (buttonClicked.equals("PagarFinal")) {
            		//AQUI METER SQL
            		secuela.meterPedido(session.getAttribute("id"), session.getAttribute("correo"), pagoTotal);
                	gotoPage("/index.jsp", request, response);
            }
        }

        String botonEliminar = request.getParameter("Eliminar");

        if(botonEliminar != null){
            Integer id = Integer.valueOf(botonEliminar);
            carrito.eliminarSeleccion(id);
            secuela.meterPedido("prueba1","prueba1",(float) 3.0);
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
}
