<%@page import="Clases.Perfil"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    response.setHeader("Pragma", "no-cache");
    response.addHeader("Cache-Control", "must-revalidate");
    response.addHeader("Cache-Control", "no-cache");
    response.addHeader("Cache-Control", "no-store");
    response.setDateHeader("Expires", 0);
%>

<header class='header'>
    <img src="Imagenes/logo.png" alt="cabecera_logo">
    <input type="checkbox" id="btn-menu">
    <label for="btn-menu" class="label-cbx">#</label>
    <nav class="menu">
        <ul>
            <li><a href="admin">Inicio</a></li>
            <li class="submenu">
                <a href="#">Mantenimiento</a>
                <ul>
                    <li><a href="C_Servicios?action=Home_Servicio">Mantenimiento Servicios</a></li>
                    <li><a href="C_Usuario?action=Home_Usuarios">Lista de Usuarios</a></li>
                    <li><a href="Reportes.jsp">Reportes</a></li>
                </ul>
            </li>
            <li><a href="Lista_Atencion.jsp">Ver Citas del dia</a></li>
            <li><a href='C_Login?action=Cerrar_Sesion'>Cerrar Sesion</a></li>
        </ul>
    </nav>
</header>
<script src="JS/JQuery.js"></script>
<script src="JS/Cabecera.js"></script>