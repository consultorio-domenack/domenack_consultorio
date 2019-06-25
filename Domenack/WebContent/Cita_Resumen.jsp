<%@page import="Clases.Servicio"%>
<%@page import="Clases.Perfil"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="Template/Redireccionamiento.jsp"%>
<%                
    String banderaCita = (String) session.getAttribute("banderaCita");
    if (banderaCita == null) {
%>
    <jsp:forward page="C_Citas?action=Servicio"/> 
<%
    }
%>
<%
    //Datos del usuario
    Perfil objetoUsuario = (Perfil) session.getAttribute("objetoUsuario");
    Servicio servicio = (Servicio) session.getAttribute("servicioCita");
    String email = objetoUsuario.getUsuario().getCorreo();
    String telefono = objetoUsuario.getTelefono();
    if (email == null) {
        email = new String();
    }
    if (telefono == null) {
        telefono = new String();
    }

%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <link rel="stylesheet" href="CSS/Cabecera.css">
        <link rel="stylesheet" href="CSS/Citas.css">
        <link rel="stylesheet" href="CSS/Miscelanea.css">
        <link rel="stylesheet" href="CSS/EstiloPieDePagina.css">
        <link href="https://fonts.googleapis.com/css?family=Asap&display=swap" rel="stylesheet">
        <script src="JS/JQuery.js"></script>


        <title>Domenack - Resumen de Cita</title>
    </head>
    <body>
        <%@include file="Template/Cabecera.jsp"%>
        <div class="container_document">
            <form class="contenedor" action='C_Citas?action=registrar_cita' method='post'>
                <div class="titulo-nivel-1">
                    Resumen de la cita
                </div>
                <div class="informacion-paciente-cita">
                    <div><label>Terapeuta: </label> Doctor Domenack</div>
                    <div><label>Paciente: </label> <%out.println(objetoUsuario.getNombre() + " " + objetoUsuario.getApellidoPaterno() + " " + objetoUsuario.getApellidoMaterno());%></div>
                    <div><label>Terapia: </label> <%=servicio.getNombre()%></div>
                    <div><label>DNI:</label> <%=objetoUsuario.getUsuario().getDni()%></div>
                </div>
                <div class="informacion-mensaje-cita">
                    <div><strong>Confirma si la información está actualizada</strong></div>
                    <div>Los siguientes campos serán necesarios para contactar con usted antes de la cita o si ocurre algún percance</div>
                    <div class='informacion-campos-cita'>
                        <div><strong>Correo:</strong> <input type="text" class="input_datos" value="<%=email%>" requeried></div>
                        <div><strong>Telefono:</strong> <input type="text" class="input_datos" value="<%=telefono%>" requeried></div>
                    </div>
                </div>
                <div class='informacion-control'>
                    <a href="Cita_Horario.jsp">Regresar</a>
                    <input type="submit" value="Confirmar" class="button-consultorio-grande">
                </div>
            </form>
        </div>
        <jsp:include page="Template/PiePagina.jsp" flush="true"/>
    </body>
</html>
