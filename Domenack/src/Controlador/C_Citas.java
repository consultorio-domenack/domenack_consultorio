/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Clases.Cita;
import Clases.Perfil;
import Clases.ProgramacionCita;
import Clases.Servicio;
import Modelo.M_Citas;
import Modelo.M_Servicios;
import Utils.Algoritmo;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class C_Citas extends HttpServlet {

    private static final M_Servicios DAO_SERVICIO = new M_Servicios();
    private static final M_Citas DAO_CITA = new M_Citas();
    private static final Algoritmo ALGORITMO = new Algoritmo();
    private static List<ProgramacionCita> programaciones;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String accion = request.getParameter("action");
        switch (accion) {
            case "Servicio": {
                MostrarServicios(request, response);
                break;
            }
            case "Horario": {
                SeleccionarHorario(request, response);
                break;
            }
            case "llenar_tabla": {
                Llenar_Tabla(request, response);
                break;
            }
            case "dia_Actual": {
                Dia_Actual(request, response);
                break;
            }
            case "avanzar_semana": {
                Avanzar_Semana(request, response);
                break;
            }
            case "retroceder_semana": {
                Retroceder_Semana(request, response);
                break;
            }
            case "confirmar_cita": {
                Confirmar_Cita(request, response);
                break;
            }
            case "registrar_cita": {
                Registrar_Cita(request, response);
                break;
            }
            case "mostrar_citas":{
                MostrarListaDeCitas(request, response);
                break;
            }
            case "atender":{
                AtenderCita(request, response);
                break;
            }
        }
    }

    protected void MostrarServicios(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            List<Servicio> servicios = DAO_SERVICIO.ListarServicios();
            request.setAttribute("lista_servicios", servicios);
            RequestDispatcher rd = request.getRequestDispatcher("/Cita_Terapia.jsp");
            rd.forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(C_Servicios.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    protected void SeleccionarHorario(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Date dia = Date.valueOf(LocalDate.now());
            if(DAO_CITA.verificarProgramacion(dia)){
                dia = DAO_CITA.recuperarUltimaFecha();
                dia = Date.valueOf(dia.toLocalDate().plusDays(1));
                ALGORITMO.generarProgramacionDeCitas(dia);
            }
            String servicioSelecionado = request.getParameter("servicio");
            Servicio servicio = new Servicio();
            servicio.setId(servicioSelecionado);
            servicio = DAO_SERVICIO.RecuperarServicio(servicio);
            HttpSession session = request.getSession();
            session.setAttribute("servicioCita", servicio);
            response.sendRedirect("Cita_Horario.jsp");
        } catch (SQLException ex) {
            Logger.getLogger(C_Citas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    protected void Llenar_Tabla(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html; charset=utf-8");
        PrintWriter out = response.getWriter();

        //Primer y ultimo dia de la semana
        HttpSession session = request.getSession();
        LocalDate diaActual = (LocalDate) session.getAttribute("diaActual");
        int valorInicial = diaActual.getDayOfWeek().getValue();
        int valorFinal = 5 - valorInicial;
        LocalDate diaInicial = diaActual.minusDays(valorInicial - 1);
        Date diaInicialSQL = Date.valueOf(diaInicial);
        LocalDate diaFinal = diaActual.plusDays(valorFinal);
        Date diaFinalSQL = Date.valueOf(diaFinal);
        //Guardar el dia inicial y final
        session.setAttribute("diaInicial", diaInicialSQL);
        session.setAttribute("diaFinal", diaFinalSQL);

        try {
            programaciones = DAO_CITA.recuperarHorarioSemana(diaInicialSQL, diaFinalSQL);
        } catch (SQLException ex) {
            Logger.getLogger(C_Citas.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Mostrar semana actual
        LocalDate dia = diaInicialSQL.toLocalDate();
        int diaInicio = dia.getDayOfMonth();
        String mesInicio = dia.getMonth().getDisplayName(TextStyle.FULL, Locale.getDefault());
        mesInicio = Character.toUpperCase(mesInicio.charAt(0)) + mesInicio.substring(1, mesInicio.length());
        dia = diaFinalSQL.toLocalDate();
        int diaFin = dia.getDayOfMonth();
        String mesFinal = dia.getMonth().getDisplayName(TextStyle.FULL, Locale.getDefault());
        mesFinal = Character.toUpperCase(mesFinal.charAt(0)) + mesFinal.substring(1, mesFinal.length());

        out.println("<div class='seleccion_semana'>");
        if (diaActual.compareTo(LocalDate.now()) == 0) {
            out.println("<div><input type='button' id='semana-anterior' value='Anterior' class='button-consultorio button-desactivado button-semana' disabled></div>");
        } else {
            out.println("<div><input type='button' id='semana-anterior' value='Anterior' class='button-consultorio button-semana'></div>");
        }
        out.println("<div id='seleccion_semana' class='mostrar_semana'>");
        out.println(diaInicio + " de " + mesInicio + " al " + diaFin + " de " + mesFinal);
        out.println("</div>");
        out.println("<div><input type='button' id='semana-siguiente' value='Siguiente' class='button-consultorio button-semana'></div>");
        out.println("</div>");

        //Imprimir las horas del horario
        //Imprimir dias segun el rango del mes
        LocalDate diaSemana;
        String nombreDiaSemana;
        int valorDiaSemana;
        Date iterador = diaInicialSQL;
        out.println("<div class='tabla-horario'>");
        out.println("<div class='dia-horario'>");
        out.println("<div class='dia-semana-horario'>");
        out.println("Horas");
        out.println("</div>");
        out.println("<div class='hora-tabla'>17:00</div>");
        out.println("<div class='hora-tabla'>17:20</div>");
        out.println("<div class='hora-tabla'>17:40</div>");
        out.println("<div class='hora-tabla'>18:00</div>");
        out.println("<div class='hora-tabla'>18:20</div>");
        out.println("<div class='hora-tabla'>18:40</div>");
        out.println("<div class='hora-tabla'>19:00</div>");
        out.println("<div class='hora-tabla'>19:20</div>");
        out.println("<div class='hora-tabla'>19:40</div>");
        out.println("</div>");

        LocalDate ahora = LocalDate.now();
        Date ahoraSQL = Date.valueOf(ahora);

        for (int i = 0; i < 5; i++) {
            diaSemana = iterador.toLocalDate();
            nombreDiaSemana = diaSemana.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.getDefault());
            nombreDiaSemana = Character.toUpperCase(nombreDiaSemana.charAt(0)) + nombreDiaSemana.substring(1, nombreDiaSemana.length());
            //Valor del dia
            valorDiaSemana = diaSemana.getDayOfMonth();
            out.println("<div class='dia-horario'>");
            out.println("<div class='dia-semana-horario'>");
            out.println(nombreDiaSemana + " " + valorDiaSemana);
            out.println("</div>");
            out.println("<div>");
            for (ProgramacionCita p : programaciones) {
                if (iterador.compareTo(p.getFecha()) == 0) {
                    //Nombre del día
                    out.println("<form action='C_Citas?action=confirmar_cita' method='post' class='form-reservar-cita'>");
                    out.println("<input type='hidden' value='" + p.getId() + "' name='id'>");
                    out.println("<input type='hidden' value='" + p.getFecha() + "' name='fecha'>");
                    out.println("<input type='hidden' value='" + p.getHora_ini_valor() + "' name='horaInicio'>");
                    out.println("<input type='hidden' value='" + p.getHora_fin_valor() + "' name='horaFin'>");
                    if (p.getEstado().compareTo("A") == 0) {
                        if (p.getFecha().compareTo(ahoraSQL) < 0) {
                            out.println("<input type='submit' value='Ocupado' class='button-consultorio button-desactivado button-celular-off' disabled>");
                        } else {
                            out.println("<input type='submit' value='Disponible' class='button-consultorio button-celular-on'>");
                        }
                    } else {
                        out.println("<input type='submit' value='Ocupado' class='button-consultorio button-desactivado button-celular-off' disabled>");
                    }
                    out.println("</form>");
                }
            }
            out.println("</div>");
            out.println("</div>");
            LocalDate temporal = iterador.toLocalDate();
            temporal = temporal.plusDays(1);
            iterador = Date.valueOf(temporal);
        }
        out.println("</div>");

    }

    protected void Dia_Actual(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html; charset=utf-8");
        PrintWriter out = response.getWriter();
        LocalDate diaActual = LocalDate.now();
        String diaSemana = diaActual.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.getDefault());
        diaSemana = Character.toUpperCase(diaSemana.charAt(0)) + diaSemana.substring(1, diaSemana.length());
        String mes = diaActual.getMonth().getDisplayName(TextStyle.FULL, Locale.getDefault());
        mes = Character.toUpperCase(mes.charAt(0)) + mes.substring(1, mes.length());
        int dia = diaActual.getDayOfMonth();
        int anho = diaActual.getYear();

        out.println("Hoy es " + diaSemana + ", " + dia + " de " + mes + " del " + anho);
    }

    protected void Avanzar_Semana(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession sesionUsuario = request.getSession();
        LocalDate diaActual = (LocalDate) sesionUsuario.getAttribute("diaActual");
        diaActual = diaActual.plusDays(7);
        sesionUsuario.setAttribute("diaActual", diaActual);
        Llenar_Tabla(request, response);
    }

    protected void Retroceder_Semana(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession sesionUsuario = request.getSession();
        LocalDate diaActual = (LocalDate) sesionUsuario.getAttribute("diaActual");
        diaActual = diaActual.minusDays(7);
        sesionUsuario.setAttribute("diaActual", diaActual);
        Llenar_Tabla(request, response);
    }
    protected void Confirmar_Cita(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        //Dia y hora de la programacion
        Date dia = Date.valueOf(request.getParameter("fecha"));
        Time tiempo = Time.valueOf(request.getParameter("horaInicio"));
        Time tiempoFin = Time.valueOf(request.getParameter("horaFin"));
        int id = Integer.parseInt(request.getParameter("id"));
        ProgramacionCita programacion = new ProgramacionCita();
        programacion.setFecha(dia);
        programacion.setHora_ini_valor(tiempo);
        programacion.setHora_fin_valor(tiempoFin);
        programacion.setId(id);
        String banderaCita = "bandera";
        session.setAttribute("programacionCita", programacion);
        session.setAttribute("banderaCita", banderaCita);
        response.sendRedirect("Cita_Resumen.jsp");
    }

    protected void Registrar_Cita(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            PrintWriter out = response.getWriter();
            HttpSession session = request.getSession();
            Perfil usuario = (Perfil) session.getAttribute("objetoUsuario");
            Servicio servicio = (Servicio) session.getAttribute("servicioCita");
            ProgramacionCita programacion = (ProgramacionCita) session.getAttribute("programacionCita");
            Cita cita = new Cita();
            cita.setPerfilUsuario(usuario);
            cita.setServicio(servicio);
            cita.setProgramacionCita(programacion);
            DAO_CITA.generarCita(cita);
            session.removeAttribute("banderaCita");
            RequestDispatcher rd = request.getRequestDispatcher("/Cita_Confirmacion.jsp");
            rd.forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(C_Citas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    protected void MostrarListaDeCitas(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html; charset=utf-8");
        PrintWriter out = response.getWriter();
        try {
            Date diaActual = Date.valueOf(LocalDate.now());
            List<Cita> citas = DAO_CITA.recuperarCitas(diaActual);
            List<Cita> citas_atendidas = DAO_CITA.recuperarCitasAtendidas(diaActual);
            for(Cita c : citas){
                out.println("<div class='listaAtencionCitas'>");
                out.println("<div><span><strong>Paciente: </strong></span><span>"+c.getPerfilUsuario().getNombre()+"</span></div>");
                out.println("<div><span><strong>DNI: </strong></span><span>"+c.getPerfilUsuario().getUsuario().getDni()+"</span></div>");
                out.println("<div><span><strong>Terapia: </strong></span><span>"+c.getServicio().getNombre()+"</span></div>");
                out.println("<div><span><strong>Desde: </strong></span><span>"+c.getProgramacionCita().getHora_ini_valor()+"</span></div>");
                out.println("<div><span><strong>Hasta: </strong></span><span>"+c.getProgramacionCita().getHora_fin_valor()+"</span></div>");
                out.println("<form action='C_Citas?action=atender' method='POST'><input type='hidden' value='"+c.getId()+"' name='id_cita'><input type='submit' value='Atender'></form>");
                out.println("</div>");
            }
            if(citas.isEmpty()){
                out.println("<div class='listaAtencionCitas'>");
                out.println("No hay citas por atender");
                out.println("</div>");
            }
            out.println("<h1 class=\"titulo-nivel-1\" style=\"color: #555\">Citas atendidas</h1>");
            for(Cita c : citas_atendidas){
                out.println("<div class='listaAtencionCitas'>");
                out.println("<div><span><strong>Paciente: </strong></span><span>"+c.getPerfilUsuario().getNombre()+"</span></div>");
                out.println("<div><span><strong>DNI: </strong></span><span>"+c.getPerfilUsuario().getUsuario().getDni()+"</span></div>");
                out.println("<div><span><strong>Terapia: </strong></span><span>"+c.getServicio().getNombre()+"</span></div>");
                out.println("<div><span><strong>Desde: </strong></span><span>"+c.getProgramacionCita().getHora_ini_valor()+"</span></div>");
                out.println("<div><span><strong>Hasta: </strong></span><span>"+c.getProgramacionCita().getHora_fin_valor()+"</span></div>");
                out.println("</div>");
            }
        } catch (SQLException ex) {
            Logger.getLogger(C_Citas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    protected void AtenderCita(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Cita cita = new Cita();
            cita.setId(Integer.parseInt(request.getParameter("id_cita")));
            DAO_CITA.AtenderCita(cita);
            response.sendRedirect("Lista_Atencion.jsp");
        } catch (SQLException ex) {
            Logger.getLogger(C_Citas.class.getName()).log(Level.SEVERE, null, ex);
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
