package Controlador;

import Clases.Perfil;
import Clases.Usuario;
import Modelo.M_Login;
import Modelo.M_Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class C_Login extends HttpServlet {

    private static final M_Login DAO = new M_Login();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("action");
        switch (accion) {
            case "Iniciar_Sesion": {
                IniciarSesion(request, response);
                break;
            }
            case "Registrarse": {
                CrearCuenta(request, response);
                break;
            }
            case "Cerrar_Sesion": {
                CerrarSesion(request, response);
                break;
            }
        }
    }

    protected void IniciarSesion(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        try {
            Perfil perfil = new Perfil();
            String dni = request.getParameter("dni");
            String clave = request.getParameter("clave");
            perfil.getUsuario().setDni(dni);
            perfil.getUsuario().setClave(clave);
            DAO.iniciarSesion(perfil);
            String nivel = perfil.getUsuario().getNivel();
            if (nivel != null) {
                switch (nivel) {
                    case "U": {
                        HttpSession sesionUsuario = request.getSession();
                        sesionUsuario.setAttribute("nivel", perfil.getUsuario().getNivel());
                        sesionUsuario.setAttribute("usuario", perfil.getNombre());
                        sesionUsuario.setAttribute("objetoUsuario", perfil);
                        response.sendRedirect("home");
                        break;
                    }
                    case "A": {
                        HttpSession sesionUsuario = request.getSession();
                        sesionUsuario.setAttribute("nivel", perfil.getUsuario().getNivel());
                        sesionUsuario.setAttribute("usuario", perfil.getNombre());
                        sesionUsuario.setAttribute("objetoUsuario", perfil);
                        response.sendRedirect("admin");
                        break;
                    }
                }
            } else {
                response.sendRedirect("login?Ingreso=error");
                out.print("DNI o clave de usuario incorrecto");
            }
        } catch (SQLException ex) {
            response.sendRedirect("login?Ingreso='error'");
            out.print("DNI o clave de usuario incorrecto");
        }
    }

    protected void CrearCuenta(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        try {
            //Recuperamos parametros de registro
            String dni = request.getParameter("dni");
            String clave = request.getParameter("clave");
            String correo = request.getParameter("correo_registro");
            String nombre = request.getParameter("nombre_registro");
            String apellidoPaterno = request.getParameter("apellido_paterno");
            String apellidoMaterno = request.getParameter("apellido_materno");
            String dia = request.getParameter("fecha_nacimiento_dia");
            String mes = request.getParameter("fecha_nacimiento_mes");
            String anho = request.getParameter("fecha_nacimiento_anho");
            String fechaNacimiento = anho+"-"+mes+"-"+dia;
            //Dia de nacimiento
            LocalDate diaFormato = LocalDate.parse(fechaNacimiento);
            Date diaNacimiento = Date.valueOf(diaFormato);

            //Llenamos el usuario
            Perfil perfil = new Perfil();
            perfil.setNombre(nombre);
            perfil.setApellidoPaterno(apellidoPaterno);
            perfil.setApellidoMaterno(apellidoMaterno);
            perfil.setFechaNacimiento(diaNacimiento);
            perfil.getUsuario().setDni(dni);
            perfil.getUsuario().setClave(clave);
            perfil.getUsuario().setCorreo(correo);
            out.print(DAO.crearUsuario(perfil));
            IniciarSesion(request,response);
        } catch (SQLException ex) {
            out.print("no");
        }
    }
    protected void CerrarSesion(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession sesionUsuario = request.getSession();
        sesionUsuario.invalidate();
        response.sendRedirect("login");
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
