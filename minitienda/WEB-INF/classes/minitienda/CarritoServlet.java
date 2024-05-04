package minitienda;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import java.util.*;

public class CarritoServlet extends HttpServlet{

    private Carrito carrito;

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession sesion = request.getSession(true);
        carrito = (Carrito)sesion.getAttribute("carrito");

        String buttonClicked = request.getParameter("buttonClicked");

        if (buttonClicked != null) {
            if (buttonClicked.equals("Pagar")) {
                gotoPage("/login.jsp", request, response);
            } else if (buttonClicked.equals("Volver tienda")) {
                gotoPage("/index.jsp", request, response);
            }
        }

        String botonEliminar = request.getParameter("Eliminar");

        if(botonEliminar != null){
            carrito.eliminarSeleccion(Integer.valueOf(botonEliminar));
        }
        
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    public void gotoPage(String address, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		RequestDispatcher disp = null;
		if(address != null && address.length() > 0)
			disp = getServletContext().getRequestDispatcher(address);
		
		if(disp != null)
			disp.forward(request, response);
	}
}
