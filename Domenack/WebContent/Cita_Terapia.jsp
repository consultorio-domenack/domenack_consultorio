<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<%@include file="Template/Redireccionamiento.jsp"%>
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
        <title>Domenack - Seleccionar Terapia</title>
    </head>
    <body>
        <%@include file="Template/Cabecera.jsp"%>
        <div class="container_document">
            <header class="titulo-nivel-1">Seleccionar Terapia</header>
            <div class="formulario_cita">
                <c:forEach items = "${lista_servicios}" var = "Dato">
                    <form action="C_Citas?action=Horario" method="POST" class="formulario_servicio_cita" name="servicio_info" id="servicio_info">
                        <div class="cabecera_servicio"><p>${Dato.nombre}<input type="hidden" value="${Dato.id}" name="servicio"></p><p> - </p><p>S/.${Dato.costo}0</p><input type="submit" value="Seleccionar terapia" class="boton_servicio"></div>
                        <div class="contenido_servicio"><p>${Dato.descripcion}<p></div>
                    </form>
                </c:forEach>
            </div>
            <div id="servicio_informacion"></div>
        </div>
        <jsp:include page="Template/PiePagina.jsp" flush="true"/>
    </body>
    <script>
        $(document).ready(function () {
            $(document).on("click", ".cabecera_servicio", function () {
                var comprobacion = $(this).next().css("display");
                if ($(window).width() <= 850) {
                    if (comprobacion == 'none') {
                        $(".cabecera_servicio").next().css("display", "none");
                        $(this).next().css("display", "block");
                    } else {
                        $(this).next().css("display", "none");
                    }
                }
            });
            $(document).on("click", ".boton_servicio", function (e) {
                e.stopPropagation();
            });
            $(window).on("resize", function () {
                if ($(window).width() >= 850) {
                    $(".cabecera_servicio").next().css("display", "block");
                }
                else if($(window).width() < 850){
                    $(".cabecera_servicio").next().css("display", "none");
                }
            });
        });
    </script>
</html>
