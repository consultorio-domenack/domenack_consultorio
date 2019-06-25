package Controlador;

import Clases.Servicio;
import Modelo.M_Servicios;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class C_Informacion extends HttpServlet {

    private static final M_Servicios DAO = new M_Servicios();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String accion = request.getParameter("action");
        switch (accion) {
            case "Mostrar_Servicios": {
                MostrarServicios(request, response);
                break;
            }
            case "Mandar_Servicio": {
                MandarServicio(request, response);
                break;
            }
        }
    }

    protected void MostrarServicios(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            List<Servicio> servicios = DAO.ListarServicios();
            request.setAttribute("lista_servicios", servicios);
            RequestDispatcher rd = request.getRequestDispatcher("/Terapias.jsp");
            rd.forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(C_Servicios.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    protected void MandarServicio(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        try {
            String servicioID = request.getParameter("servicio");
            Servicio servicio = new Servicio();
            servicio.setId(servicioID);
            servicio = DAO.RecuperarServicio(servicio);
            String u = (String) request.getSession().getAttribute("usuario");
            out.println("<div class='cabecera_servicio'><h1>" + servicio.getNombre() + "</h1></div>");
            out.println("<div class='servicio_contenedor contenido_servicio'>");
            out.println("<div class='servicio_informacion'>");
            out.println("<h3>Informacion</h3>");
            out.println("<div class='servicio_descripcion'>");
            out.println("<p>"+servicio.getDescripcion()+"</p>");
            out.println("</div>");
            out.println("</div>");
            out.println("<div class='servicio_imagen'>");
            out.println("<img src='C_Servicios?action=Imagen&id=" + servicio.getId() + "'>");
            out.println("</div>");
            out.println("</div>");
            out.println("<div class='servicio_contenedor'>");
            out.println("<div class='servicio_detalle'>");
            out.println("<div class='servicio_costo'>");
            out.println("<p>");
            out.println("Precio: " + servicio.getCosto());
            out.println("</p>");
            out.println("</div>");
            if (u == null) {
                out.println("<form method='POST' action='login' class='servicio_boton'>");
            } else {
                out.println("<form method='POST' action='C_Citas?action=Horario' class='servicio_boton'>");
            }
            out.println("<input type='submit' align='center' class='button-consultorio-grande' value='Reserva tu cita ahora'>");
            out.println("<input type='hidden' value='" + servicio.getId() + "' name='servicio'>");
            out.println("</form>");
            out.println("</div>");
        } catch (Exception ex) {
            out.println("ERROR");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
