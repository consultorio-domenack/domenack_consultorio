<%@page import="Clases.ProgramacionCita"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@include file="Template/Redireccionamiento.jsp"%>
<%
    ProgramacionCita programacion = (ProgramacionCita) session.getAttribute("programacionCita");
    if (programacion == null) {
%>
    <jsp:forward page="C_Citas?action=Servicio"/> 
<%
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
        <title>Domenack - Confirmación de Cita/title>
    </head>
    <body>
        <%@include file="Template/Cabecera.jsp"%>
        <div class="container_document">
            <div class="titulo-nivel-1">
                Confirmacion de la cita
            </div>
            <div class='informacion-paciente-cita'>
                <div>Tu cita ha sido reservada con exito</div>
                <div><label>Dia: </label><%=programacion.getFecha()%></div>
                <div><label>Hora: </label><%=programacion.getHora_ini_valor()%></div>
                <div><label>Lugar: </label>Consultorio Terapeutico Domenack</div>
                <a href="home">Volver al inicio</a>
            </div>
        </div>
        <jsp:include page="Template/PiePagina.jsp" flush="true"/>
    </body>
</html>
