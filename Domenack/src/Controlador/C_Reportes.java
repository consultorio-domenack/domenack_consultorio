package Controlador;

import Clases.Perfil;
import Clases.ProgramacionCita;
import Clases.Servicio;
import Modelo.M_Reportes;
import java.io.IOException;
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

public class C_Reportes extends HttpServlet {
    
    private static M_Reportes DAO_REPORTES = new M_Reportes();
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String accion = request.getParameter("action");
        switch (accion) {
            case "ranking-servicio": {
                RankingServicios(request, response);
                break;
            }
            case "ganancia-servicio": {
                GananciaSegunServicio(request, response);
                break;
            }
            case "dias-servicio": {
                DiaMasPedido(request, response);
                break;
            }
            case "usuarios-servicio": {
                CitasPorUsuario(request, response);
                break;
            }
        }
    }
    protected void RankingServicios(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            List<Servicio> servicios = new ArrayList<>();
            DAO_REPORTES.RankingServicios(servicios);
            RequestDispatcher rd = request.getRequestDispatcher("ranking-servicio");
            request.setAttribute("lista_reporte", servicios);
            rd.forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(C_Reportes.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    protected void GananciaSegunServicio(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            List<Servicio> servicios = new ArrayList<>();
            DAO_REPORTES.GananciaSegunServicio(servicios);
            RequestDispatcher rd = request.getRequestDispatcher("ganancia-servicio");
            request.setAttribute("lista_reporte", servicios);
            rd.forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(C_Reportes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    protected void DiaMasPedido(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            List<ProgramacionCita> dias = new ArrayList<>();
            DAO_REPORTES.DiaMasPedido(dias);
            RequestDispatcher rd = request.getRequestDispatcher("dias-servicio");
            request.setAttribute("lista_reporte", dias);
            rd.forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(C_Reportes.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    protected void CitasPorUsuario(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            List<Perfil> usuarios = new ArrayList<>();
            DAO_REPORTES.CitasPorUsuario(usuarios);
            RequestDispatcher rd = request.getRequestDispatcher("usuarios-servicio");
            request.setAttribute("lista_reporte", usuarios);
            rd.forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(C_Reportes.class.getName()).log(Level.SEVERE, null, ex);
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
