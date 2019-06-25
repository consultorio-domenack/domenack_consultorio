<%@page import="Clases.Servicio"%>
<%@page import="java.time.LocalDate"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@include file="Template/Redireccionamiento.jsp"%>
<html lang="es">
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <link rel="stylesheet" href="CSS/Cabecera.css">
        <link rel="stylesheet" href="CSS/Citas.css">
        <link rel="stylesheet" href="CSS/Miscelanea.css">
        <link rel="stylesheet" href="CSS/EstiloPieDePagina.css">
        <link href="https://fonts.googleapis.com/css?family=Asap&display=swap" rel="stylesheet">
        <title>Domenack - Seleccionar Horario</title>
        <script src="JS/JQuery.js"></script>
        <script src="JS/Horario.js"></script>
    </head>
    <body>
        <%@include file="Template/Cabecera.jsp"%>
        <div class="container_document">
            <h1>Seleccione el dia que quieres reservar tu cita</h1>
            <%                if (session.getAttribute("diaActual") == null) {
                    LocalDate diaActual = LocalDate.now();
                    session.setAttribute("diaActual", diaActual);
                }
                Servicio servicio = (Servicio) session.getAttribute("servicioCita");
            %>
            <div class="selecionar_horario">
                <h3 id="diaActual"></h3>
                <h3>Servicio requerido: <%=servicio.getNombre()%></h3>
                <div class="horario_semana" id="horario_semana">

                </div>
            </div>
        </div>
        <jsp:include page="Template/PiePagina.jsp" flush="true"/>
    </body>
</html>
