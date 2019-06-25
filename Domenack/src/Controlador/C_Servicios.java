package Controlador;

import Clases.Servicio;
import Modelo.M_Servicios;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@MultipartConfig(maxFileSize = 16177215) 
public class C_Servicios extends HttpServlet {

    private static final M_Servicios DAO = new M_Servicios();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String accion = request.getParameter("action");
        switch (accion) {
            //Iniciar mantenimiento de servicios
            case "Home_Servicio": {
                MostrarServicios(request, response);
                break;
            }
            case "Imagen": {
                MostrarImagen(request, response);
                break;
            }
            case "Agregar": {
                AgregarServicio(request, response);
                break;
            }
            case "Cambiar": {
                RecuperarServicio(request, response);
                break;
            }
            case "Modificar": {
                ModificarServicio(request, response);
                break;
            }
            case "Inhabilitar_Servicio": {
                InhabilitarServicio(request, response);
                break;
            }
            case "ggizi": {
                MostrarServicios2(request, response);
                break;
            }
        }
    }

    protected void MostrarServicios(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            List<Servicio> servicios = DAO.ListarServicios();
            request.setAttribute("lista_servicios", servicios);
            RequestDispatcher rd = request.getRequestDispatcher("/Mantenimiento_Servicio.jsp");
            rd.forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(C_Servicios.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    protected void MostrarServicios2(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            PrintWriter out = response.getWriter();
            List<Servicio> servicios = DAO.ListarServicios();
            for(Servicio s : servicios){
                out.println("<img src='C_Servicios?action=Imagen&id="+s.getId()+"'>");
            }
        } catch (SQLException ex) {
            Logger.getLogger(C_Servicios.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    protected void MostrarImagen(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            //Señalar que el response será de tipo imagen
            response.setContentType("image/jpeg");
            //Señalar la imagen segun id
            Servicio servicio_imagen = new Servicio();
            servicio_imagen.setId(request.getParameter("id"));
            //Recuperar imagen
            servicio_imagen = DAO.RecuperarImagen(servicio_imagen);
            //Mandar imagen via IOStream
            InputStream bos = new ByteArrayInputStream(servicio_imagen.getRecuperarImagen());
            int tamanoInput = bos.available();
            byte[] datosIMAGEN = new byte[tamanoInput];
            bos.read(datosIMAGEN, 0, tamanoInput);
            bos.close();
            response.getOutputStream().write(datosIMAGEN);
            response.getOutputStream().close();
        } catch (SQLException ex) {
            
        }
    }

    protected void AgregarServicio(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        //Declaración de variables
        Servicio servicio_nuevo;
        InputStream inputStream = null;
        //Llenar los datos del servicio
        Part filePart = request.getPart("imagen");
        String id = request.getParameter("id_servicio");
        String nombre = request.getParameter("nombre_servicio");
        String descripcion = request.getParameter("descripcion");
        String costo = request.getParameter("costo");
        //Verificar que la imagen no este vacia
        if (filePart.getSize() > 0) {
            System.out.println(filePart.getName());
            System.out.println(filePart.getSize());
            System.out.println(filePart.getContentType());
            inputStream = filePart.getInputStream();
        }
        out.println(filePart);
        servicio_nuevo = new Servicio(id, nombre, descripcion, Float.parseFloat(costo), inputStream, null, 1);
        //Agregar servicio y redireccionar la tabla
        try {
            out.println(DAO.AgregarServicios(servicio_nuevo));
            response.sendRedirect("C_Servicios?action=Home_Servicio");
        } catch (SQLException ex) {
            out.print("Cagué :'u"+ex.getMessage());
        }
    }
    
    protected void InhabilitarServicio(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            Servicio servicio_inhabilitar = new Servicio();
            servicio_inhabilitar.setId(request.getParameter("id"));
            servicio_inhabilitar.setDisponibilidad(0);
            DAO.InhabilitarHabilitar(servicio_inhabilitar);
            response.sendRedirect("C_Servicios?action=Home_Servicio");
        } catch (SQLException ex) {
            
        }
    }
    protected void RecuperarServicio(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            Servicio servicio_modificar = new Servicio();
            servicio_modificar.setId(request.getParameter("id"));
            servicio_modificar = DAO.RecuperarServicio(servicio_modificar);
            request.setAttribute("servicio", servicio_modificar);
            RequestDispatcher rd = request.getRequestDispatcher("/Modificar_Servicio.jsp");
            rd.forward(request, response);
        } catch (SQLException ex) {
            
        }
    }
    protected void ModificarServicio(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        //Declaración de variables
        Servicio servicio_nuevo;
        InputStream inputStream = null;
        //Llenar los datos del servicio
        Part filePart = request.getPart("imagen");
        String id = request.getParameter("id_servicio");
        String nombre = request.getParameter("nombre_servicio");
        String descripcion = request.getParameter("descripcion");
        String costo = request.getParameter("costo");
        //Verificar que la imagen no este vacia
        if (filePart.getSize() > 0) {
            System.out.println(filePart.getName());
            System.out.println(filePart.getSize());
            System.out.println(filePart.getContentType());
            inputStream = filePart.getInputStream();
        }
        servicio_nuevo = new Servicio(id, nombre, descripcion, Float.parseFloat(costo), inputStream, null, 1);
        //Agregar servicio y redireccionar la tabla
        try {
            out.println(DAO.ModificarServicio(servicio_nuevo));
            response.sendRedirect("C_Servicios?action=Home_Servicio");
        } catch (SQLException ex) {
            out.print("Cagué :'u"+ex.getMessage());
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
