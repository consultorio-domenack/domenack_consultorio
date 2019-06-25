package Controlador;

import Clases.Cita;
import Clases.Perfil;
import Modelo.M_Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class C_Usuario extends HttpServlet {
    
    private static final M_Usuario DAO_USUARIO = new M_Usuario();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String accion = request.getParameter("action");
        switch (accion) {
            //Iniciar mantenimiento de servicios
            case "Home_Usuarios": {
                listarUsuarios(request, response);
                break;
            }
            case "Profile_Usuario": {
                recuperarUsuario(request, response);
                break;
            }
            case "historial": {
                HistorialCitas(request, response);
                break;
            }
        }
    }

    protected void listarUsuarios(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            List<Perfil> usuarios = DAO_USUARIO.ListarUsuarios();
            request.setAttribute("lista_usuarios", usuarios);
            RequestDispatcher rd = request.getRequestDispatcher("/Lista_Usuarios.jsp");
            rd.forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(C_Usuario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    protected void recuperarUsuario(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();
            Perfil usuario = (Perfil) session.getAttribute("objetoUsuario");
            DAO_USUARIO.RecuperarUsuario(usuario);
            request.setAttribute("informacion", usuario);
            RequestDispatcher rd = request.getRequestDispatcher("/Usuario_Perfil.jsp");
            rd.forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(C_Usuario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    protected void HistorialCitas(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        try {
            HttpSession session = request.getSession();
            Perfil usuario = (Perfil) session.getAttribute("objetoUsuario");
            List<Cita> citas, citas_pendientes;
            citas = DAO_USUARIO.HistorialCitas(usuario);
            citas_pendientes = DAO_USUARIO.HistorialCitasPendientes(usuario);
            request.setAttribute("historial_cita", citas);
            request.setAttribute("historial_cita_pendiente", citas_pendientes);
            RequestDispatcher rd = request.getRequestDispatcher("/Historial_Citas.jsp");
            rd.forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(C_Usuario.class.getName()).log(Level.SEVERE, null, ex);
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
