package minitienda;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import java.util.*;

public class CarritoServlet extends HttpServlet{

    private Carrito carrito;

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String buttonClicked = request.getParameter("buttonClicked");

        if (buttonClicked != null) {
            if (buttonClicked.equals("Pagar")) {
                // Redirect to the payment page (e.g., login.jsp)
                gotoPage("/login.jsp", request, response);
                //response.sendRedirect(request.getContextPath() + "/login.jsp");
            } else if (buttonClicked.equals("Volver tienda")) {
                // Redirect back to the shop (e.g., index.jsp)
                gotoPage("/index.jsp", request, response);
                //response.sendRedirect(request.getContextPath() + "/index.jsp");
            }
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
