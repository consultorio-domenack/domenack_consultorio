<!DOCTYPE html>
<%
    response.setHeader("Pragma", "no-cache");
    response.addHeader("Cache-Control", "must-revalidate");
    response.addHeader("Cache-Control", "no-cache");
    response.addHeader("Cache-Control", "no-store");
    response.setDateHeader("Expires", 0);

    Boolean bandera = false;
    String usuario;
    String direccion = "";
    String direccion2 = "";
    if (session.getAttribute("usuario") == null) {
        usuario = "Iniciar Sesion";
        direccion = "login";
    } else {
        usuario = (String) session.getAttribute("usuario");
        direccion = "#";
        bandera = true;
    }
%>
<header class='header'>
    <img src="Imagenes/logo.png" alt="cabecera_logo">
    <input type="checkbox" id="btn-menu">
    <label for="btn-menu" class="label-cbx">#</label>
    <nav class="menu">
        <ul>
            <li><a href="home">Inicio</a></li>
            <li class="submenu">
                <a href="#">Nosotros</a>
                <ul>
                    <li><a href="informacion">¿Quienes somos?</a></li>
                    <li><a href="consultorio">Consultorio</a></li>
                </ul>
            </li>
            <li>
                <a href="C_Informacion?action=Mostrar_Servicios">Terapias</a>
            </li>
            <li><a href="<%if (bandera) {
                    out.println("C_Citas?action=Servicio");
                } else {
                    out.println(direccion);
                }%>">Reservar Cita</a></li>
                <%
                    if (bandera) {
                        out.println("<li class='submenu'><a href='#'>" + usuario + "</a>");
                        out.println("<ul>");
                        out.println("<li><a href='C_Usuario?action=Profile_Usuario'>Perfil</a></li>");
                        out.println("<li><a href='C_Usuario?action=historial'>Historial de Citas</a></li>");
                        out.println("<li><a href='C_Login?action=Cerrar_Sesion'>Cerrar Sesion</a></li>");
                        out.println("</ul>");
                        out.println("</li>");
                    }
                %>
        </ul>
    </nav>
</header>
<script src="JS/JQuery.js"></script>
<script src="JS/Cabecera.js"></script>